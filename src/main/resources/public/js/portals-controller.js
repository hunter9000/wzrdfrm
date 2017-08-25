
avalonApp.controller('portalsController', function(APIService, $scope, $location, $window, $routeParams) {

    $scope.enterPortal = function(portalId) {
        APIService.takePortal($routeParams.charId, portalId, function(response) {
//            console.log(response.data);
            $location.path('/dungeon/'+$routeParams.charId);
        });

//        $http({method:'POST',
//               url: '/api/char/' + $routeParams.charId + '/portal/' + portalId + '/',
//               headers: {'x-access-token': $window.localStorage['jwtToken']}
//        })
//        .success(function (data) {
//            console.log(data);
//            $location.path('/dungeon/'+$routeParams.charId);
//        })
//        .error(function(data) {
//            console.log('Error:' + data);
//        });
    }

});
