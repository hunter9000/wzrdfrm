
wzrdfrmApp.factory('FarmService', function(APIService, NotificationService, $rootScope) {

    var getPlantCropRequest = function(plant) {
        return {
            'plantId': plant.id
        };
    };

    var broadcastFarmModifiedCallback = function() {
//        return function(response) {
        $rootScope.$broadcast('farm.modified');
//        }
    };

    return {

        plantCrop: function(plot, plant) {
            var plantCropRequest = getPlantCropRequest(plant);

            APIService.plantCrop(plot.id, plantCropRequest, function(response) {
//                var farmPlot = response.data;
//                $scope.farm.farmPlots[farmPlot.row][farmPlot.col] = farmPlot;
                broadcastFarmModifiedCallback();
            });
        },

        harvestCrop: function(plot) {
            APIService.harvestCrop(plot.id, function(response) {
//                $scope.harvestedMaterials = response.data;
                NotificationService.createHarvestRewardNotification(response.data);
//                $scope.getFarm();
                broadcastFarmModifiedCallback();
            });
        },

        getCharClassRequest: function(charClassId) {
            return {
                'charClassId': charClassId
            };
        },
    };

});