
wzrdfrmApp.factory('FarmService', function() {

    return {
        getPlantCropRequest: function(plantType) {
            return {
                'plantType': plantType
            };
        }
    };

});