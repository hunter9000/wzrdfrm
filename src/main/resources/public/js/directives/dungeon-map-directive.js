/** Map object is passed in. Begin by calling init(), which preloads the images. When images are loaded, processes
  * the map object with loadMap() */

avalonApp.directive('dungeonMap', function(APIService, $window, $location, $routeParams, $log) {
    return {
        restrict: 'E',
        scope: {
            map: '=',
        },
        template: `<div class="dungeon-wrapper">
                       <canvas class="dungeon-canvas" id="demoCanvas" width="400" height="400"
                               style="margin-left:50px; margin-top:50px; border: 1px solid #ccc"
                               oncontextmenu="return false;">
                       </canvas>
                       <div class="canvas-hider" ng-hide="map"></div>
                   </div>`,
        controller: function (ImageService, $scope) {

            $scope.stage = null;
            $scope.container = null;

            $scope.tileSize = 40;

            $scope.images = {};
            $scope.preload = null;
            $scope.manifest = [
               {src: 'grass-tile.png', id: 'GRASS', x: $scope.tileSize, y: $scope.tileSize},
               {src: 'blank.png', id: 'blank', x: $scope.tileSize, y: $scope.tileSize},
               {src: 'backing.png', id: 'backing', x: $scope.tileSize, y: $scope.tileSize},
               {src: 'icon.png', id: 'icon', x: $scope.tileSize, y: $scope.tileSize}
            ];

            $scope.offset = {x:null, y:null};     // vector from mouse point to corner of container
            $scope.allowHorizontalMove;
            $scope.allowVerticalMove;

            $scope.movingIcon;

            $scope.gameState = GameStateEnum.WAITING;

            // TODO instead of loading canvas and container on directive load, just preload images. load canvas stuff with loadMap after the map has been loaded

            // on page load, sets up canvas, and starts image preload. calls $scope.loadMap() when complete
            $scope.init = function() {
                $scope.stage = new createjs.Stage("demoCanvas");
                $scope.stage.setBounds(0, 0, $scope.stage.canvas.getAttribute('width'), $scope.stage.canvas.getAttribute('height'));
                $scope.stage.mouseMoveOutside = true;

                console.log('stage bounds '+ $scope.stage.getBounds());

                // create and setup container
                $scope.container = new createjs.Container();
                $scope.stage.addChild($scope.container);
                $scope.container.addEventListener('mousedown', function(event) {
                    if (event.nativeEvent.button != 2) {        // restrict to right button
                        return;
                    }

                    $scope.offset = {x: event.target.parent.x - event.rawX,
                              y: event.target.parent.y - event.rawY };
                    console.log($scope.offset);
                });
                // add event listener for right mouse button button clicks
                $scope.container.addEventListener('pressmove', function(event) {
                    if (event.nativeEvent.button != 2) {        // restrict to right button
                        return;
                    }

                    //console.log('container pressmove');
                    if (allowHorizontalMove) {
                        $scope.container.x = event.rawX + $scope.offset.x;
                    }
                    if (allowVerticalMove) {
                        $scope.container.y = event.rawY + $scope.offset.y;
                    }

                    var stageRectangle = $scope.stage.getTransformedBounds();
                    var contRectangle = $scope.container.getTransformedBounds();

                    if (!contRectangle.contains(stageRectangle.x, stageRectangle.y, stageRectangle.width, stageRectangle.height)) {
                        //console.log('OUT ' + 'stageRect ' + stageRectangle + ' containerRect ' + contRectangle);
                        // adjust
                        if (allowHorizontalMove) {
                            if (contRectangle.x > stageRectangle.x) {
                                $scope.container.x = stageRectangle.x;
                            }
                            if (contRectangle.x + contRectangle.width < stageRectangle.width) {
                                $scope.container.x = stageRectangle.width - contRectangle.width;
                            }
                        }

                        if (allowVerticalMove) {
                            if (contRectangle.y > stageRectangle.y) {
                                $scope.container.y = stageRectangle.y;
                            }
                            if (contRectangle.y + contRectangle.height < stageRectangle.height) {
                                $scope.container.y = stageRectangle.height - contRectangle.height;
                            }
                        }
                    }
                });

                // start timer
                createjs.Ticker.setFPS(60);
                createjs.Ticker.addEventListener("tick", $scope.stage);

                // start preloading images
                // handleFileComplete and handleComplete are callbacks when done
                $scope.preload = new createjs.LoadQueue(true, 'img/tiles/');
                $scope.preload.addEventListener("fileload", $scope.handleFileComplete);
                $scope.preload.addEventListener("complete", $scope.handleComplete);
                $scope.preload.loadManifest($scope.manifest);
            }

            $scope.handleFileComplete = function(event) {
                console.log(event.item);
            }

            $scope.handleComplete = function() {
                // add watch to initialize maps stuff when the map gets loaded
                var unbind = $scope.$watch(
                    'map',
                    function(newVal, oldVal) {
                        $scope.initMap()
                        unbind();
                    }
                );
            }

            $scope.instantiateImage = function(imgId) {
                var image = $scope.preload.getResult(imgId);

                // instantiate a bitmap based on this image
                var bitmap = new createjs.Bitmap(image);
                //stage.addChild(bitmap);
                return bitmap;
            }

            $scope.gridCoordsToWorldCoords = function(x, y) {
                return {'x':x*$scope.tileSize, 'y':y*$scope.tileSize};
            }

            $scope.initMap = function() {
                $log.debug('beginning initMap after map has changed');

                if (!$scope.map) {
                    return;
                }

                // create 2d array of cells
                // find dimensions of cell array
                var array = [];         // col, row
                var maxRow = 0;
                var maxCol = 0;
                for (i=0; i<$scope.map.cells.length; i++) {
                    if ($scope.map.cells[i].x > maxCol) {
                        maxCol = $scope.map.cells[i].x;
                    }
                    if ($scope.map.cells[i].y > maxRow) {
                        maxRow = $scope.map.cells[i].y;
                    }
                }

                // create array with correct dimensions
                for (i=0; i<=maxRow; i++) {
                    array.push(new Array(maxCol+1));
                }

                // populate array
                for (i=0; i<$scope.map.cells.length; i++) {
                    array[$scope.map.cells[i].x][$scope.map.cells[i].y] = $scope.map.cells[i];
                }

                // replace the 1d array of cells with the 2d array of cells
                $scope.map.cells = array;

                // create bitmaps for each tile, adding blank placeholders for empty spaces
                x = 0;
                y = 0;
                for (col=0; col<$scope.map.cells.length; col++) {
                    for (row=0; row<$scope.map.cells[col].length; row++) {
                        var bitmap;
                        var backing;

                        bitmap = $scope.instantiateImage($scope.map.cells[col][row].groundType);

                        bitmap.x = x;
                        bitmap.y = y;
                        bitmap.data = {coordCol: col, coordRow: row}
                        bitmap.addEventListener('click', $scope.tileClick);
                        $scope.container.addChild(bitmap);

                        // for testing, create a little dude at 2,2
                        if (col == 2 && row == 2) {
                            var iconBitmap = $scope.instantiateImage('icon');
                            iconBitmap.x = x;
                            iconBitmap.y = y;
                            $scope.container.addChild(iconBitmap);

                            $scope.movingIcon = iconBitmap;
                            console.log('set movingIcon at 2,2');
                        }

                        backing = $scope.instantiateImage('backing');
                        bitmap.hitArea = backing;

                        y += $scope.tileSize;
                    }
                    x+= $scope.tileSize;
                    y = 0;
                }
                $scope.container.setChildIndex( $scope.movingIcon, $scope.container.getNumChildren()-1);

                console.log('container bounds ' + $scope.container.getBounds());

                var stageRectangle = $scope.stage.getTransformedBounds();
                var contRectangle = $scope.container.getTransformedBounds();
                allowHorizontalMove = contRectangle.width > stageRectangle.width;
                allowVerticalMove = contRectangle.height > stageRectangle.height;

                // center the grid
                if (!$scope.allowHorizontalMove) {     // need to center horizontally
                    $scope.container.x = (stageRectangle.width - contRectangle.width) / 2;
                }
                if (!$scope.allowVerticalMove) {       // // need to center vertically
                    $scope.container.y = (stageRectangle.height - contRectangle.height) / 2;
                }
            }

            $scope.init();

            /** TODO Send this to the outer controller through callback */
            $scope.tileClick = function(event) {
                if (event.nativeEvent.button != 0 || $scope.gameState != GameStateEnum.WAITING) {        // only left click
                    return;
                }
                $scope.gameState = GameStateEnum.MOVING;

                console.log('bitmap click');

                x = event.target.data.coordCol;
                y = event.target.data.coordRow;

                APIService.moveTo($routeParams.charId, x, y, function(response) {
                    if (response.data.success) {
                        createjs.Tween.get($scope.movingIcon).to($scope.gridCoordsToWorldCoords(x, y), 1000).call(function() {
                            //Tween complete
                            $scope.gameState = GameStateEnum.WAITING;
                        });
                    }
                });

        //        $http({
        //            method: 'POST',
        //            url: '/api/map/moveto/x/' + x + '/y/' + y,
        //            headers: {'x-access-token': $window.localStorage['jwtToken']}
        //        })
        //        .success(function(data) {
        //            if (data.success) {
        //                createjs.Tween.get($scope.movingIcon).to($scope.gridCoordsToWorldCoords(x, y), 1000).call(function() {
        //                    //Tween complete
        //                    $scope.gameState = GameStateEnum.WAITING;
        //                });
        //            }
        //        })
        //        .error(function(data) {
        //            console.log(data);
        //        });
            }
        }
    }
});