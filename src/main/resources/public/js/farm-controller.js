
wzrdfrmApp.controller('farmController', function(APIService, $scope, $routeParams, $location) {
    $scope.farm;

    // get the character from the provided id
    ($scope.getFarm = function() {
        APIService.getFarm(function(response) {
           $scope.farm = response.data;
        });
    })();

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