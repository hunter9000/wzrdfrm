
wzrdfrmApp.factory('FarmService', function() {

    return {
        getPlantCropRequest: function(plant) {
            return {
                'plantId': plant.id
            };
        },

        getCharClassRequest: function(charClassId) {
            return {
                'charClassId': charClassId
            }
        }
    };

});