
wzrdfrmApp.controller('farmController', function(APIService, FarmService, $scope, $routeParams, $location) {
    $scope.farm;

    // get the character from the provided id
    ($scope.getFarm = function() {
        APIService.getFarm(function(response) {
            $scope.farm = response.data;
            // map all the plots into a 2d array based on row/col of each plot
            $scope.farm.farmPlots = $scope.farm.farmPlots.reduce(function(accumulator, currentValue, currentIndex, array) {
                // if the accumulator doesn't have an array at this position yet, create one
                if (!accumulator[currentValue.row]) {
                    accumulator[currentValue.row] = [];
                }
                accumulator[currentValue.row][currentValue.col] = currentValue;
                return accumulator;
            }, []);
        });
    })();

    $scope.plant = function(plot) {
        var plantCropRequest = FarmService.getPlantCropRequest('GRASS');

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

});