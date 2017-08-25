

avalonApp.controller('hqController', function(APIService, $scope, $routeParams, $window, $location) {
    $scope.char;

    $scope.scopeOptions = [{'label': 'hello', 'filter': 'BODY'}, {'label': 'world', 'filter': 'LEFT_HAND'}];

    $scope.scopeFilter = [];

    // get the character from the provided id
    APIService.getChar($routeParams.charId, function(response) {
       $scope.char = response.data;
    });

    $scope.goToInventory = function() {
       $location.path('/inventory/' + $routeParams.charId);
    }
    $scope.goToCrafting = function() {
       $location.path('/crafting/' + $routeParams.charId);
    }
    $scope.goToDungeon = function() {
       $location.path('/dungeon/' + $routeParams.charId);
    }
    $scope.goToPortals = function() {
       $location.path('/portals/' + $routeParams.charId);
    }

});
