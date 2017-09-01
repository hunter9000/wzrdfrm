
wzrdfrmApp.controller('farmController', function(APIService, FarmService, $scope, $location, $uibModal, $log) {
    $scope.farm;

//    $scope.currTime;

    // get the character from the provided id
    ($scope.getFarm = function() {
        APIService.getFarm(function(response) {
            $scope.farm = response.data;        // might be null
            // map all the plots into a 2d array based on row/col of each plot
            if ($scope.farm) {
                $scope.farm.farmPlots = $scope.farm.farmPlots.reduce(function(accumulator, currentValue, currentIndex, array) {
                    // if the accumulator doesn't have an array at this position yet, create one
                    if (!accumulator[currentValue.row]) {
                        accumulator[currentValue.row] = [];
                    }
                    accumulator[currentValue.row][currentValue.col] = currentValue;
                    return accumulator;
                }, []);
            }
        });
    })();

//    $scope.getPlantDateFormatted = function(plantDate) {
//        return plantDate + 1000;
//    }

    $scope.plant = function(plot, plant) {
        var plantCropRequest = FarmService.getPlantCropRequest(plant);

        APIService.plantCrop(plot.id, plantCropRequest, function(response) {
            var farmPlot = response.data;
            $scope.farm.farmPlots[farmPlot.row][farmPlot.col] = farmPlot;
        });
    }

    $scope.harvest = function(plot) {
        APIService.harvestCrop(plot.id, function(response) {
            $scope.harvestedMaterials = response.data;
            $scope.getFarm();
        });
    }

    $scope.createFarm = function() {
        APIService.createFarm(function(response) {
            $scope.getFarm();
        });
    }

    $scope.deleteFarm = function() {
        APIService.deleteFarm(function(response) {
            $location.path('/');
        });
    }


    // opens a modal dialog to change char class
    $scope.changeCharClass = function () {
        var modalInstance = $uibModal.open({
            animation: false,
            templateUrl: 'pages/templates/char-class-change-template.html',
            controller: 'CharClassChangeController',
            size: 'lg',
            resolve: { }
        });

        modalInstance.result.then(function () {
            $log.debug('modal success');
        }, function () {
            $log.debug('Modal dismissed at: ' + new Date());
            APIService.getAllCharClasses(function(response) {
                $scope.farm.currCharClass = response.data.currCharClass;
            });
        });
    };


});