

avalonApp.controller('profileController', function(APIService, $scope, $routeParams, $window, $location){
	$scope.user = {};

    $scope.roles = [];

    APIService.getProfile(function(response) {
        $scope.user = response.data;
    });
//	$http.get('/api/profile/',
//	    { headers: {'x-access-token': $window.localStorage['jwtToken']}
//    })
//	.then(function successCallback(response) {
//        $scope.user = response.data;
//    }, function errorCallback(response) {
//        console.log(response);
//        $location.path('/error');
//    });

    $scope.save = function() {
        APIService.editProfile($scope.user, function(response) {
            $scope.user = response.data;
        });

//        $http.put('/api/profile/',
//            $scope.user,
//            { headers: {'x-access-token': $window.localStorage['jwtToken']}
//        })
//        .then(function successCallback(response) {
//            $scope.user = response.data;
//        }, function errorCallback(response) {
//            console.log(response);
//            $location.path('/error');
//        });
    }

});