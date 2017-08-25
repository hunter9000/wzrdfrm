
var GameStateEnum = Object.freeze({WAITING: 'waiting', MOVING: 'moving'});

avalonApp.controller('dungeonController', function(APIService, $scope, $window, $location, $routeParams) {

    $scope.char;
    $scope.map = null;

    // loads map from api, calls $scope.initMap() to initialize
    ($scope.loadMap = function() {
        APIService.getChar($routeParams.charId, function successCallback(response) {
            $scope.char = response.data;
            $scope.map = $scope.char.currentMap;
        });
    })();

    $scope.leaveDungeon = function() {
        APIService.leaveDungeon($routeParams.charId, function(response) {
            $location.path("/hq/"+$routeParams.charId);
        });
    }

});

		// creates all the scene objects based on the mapdata
//    	$scope.populate = function(mapData) {
//    		// create the scene objects here
//    		var circle = new createjs.Shape();
//			circle.graphics.beginFill("DeepSkyBlue").drawCircle(0, 0, 50);
//			circle.x = 100;
//			circle.y = 100;
//			$scope.stage.addChild(circle);
//			circle.addEventListener("click", function (event) {
//				console.log('circle click');
//			});
//			$scope.circle = circle;
//
//			// create map tiles, attach click listeners
//
//			// create
//
//			createjs.Ticker.setFPS(60);
//			createjs.Ticker.addEventListener("tick", $scope.stage);
//    	}
//
//		$scope.move = function() {
//			if (!createjs.Tween.hasActiveTweens($scope.circle)) {
//				var tween = createjs.Tween.get($scope.circle, { loop: false })
//					.to({ x: 400 }, 1000, createjs.Ease.getPowInOut(4))
//					.to({ alpha: 0, y: 175 }, 500, createjs.Ease.getPowInOut(2))
//					.to({ alpha: 0, y: 225 }, 100)
//					.to({ alpha: 1, y: 200 }, 500, createjs.Ease.getPowInOut(2))
//					.to({ x: 100 }, 800, createjs.Ease.getPowInOut(2));
//				$scope.tween = tween;
//			}
//		}


/////// MOVE A SHAPE ALONG A PATH
//            steps = [
//                {x:10, y:10},
//                {x:20, y:10},
//                {x:20, y:30},
//                {x:40, y:30},
//                {x:10, y:10}
//            ];
//
//            var tween = createjs.Tween.get(circle, { loop: false });
//            for (i=0; i<steps.length; i++) {
//                tween.to({ x: steps[i].x, y: steps[i].y }, 1000, createjs.Ease.getPowInOut(4));
//            }
