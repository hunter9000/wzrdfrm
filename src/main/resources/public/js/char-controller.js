

	avalonApp.controller('charController', function(APIService, $scope, $window, $uibModal, $location) {
		$scope.message;// = 'token: ' + $window.localStorage['jwtToken'];

		$scope.chars = [];

        $scope.fetchChars = function() {
            APIService.getChars(function(response) {
                $scope.chars = response.data;
                console.log('got these chars back: ');
                console.log(response.data);
            });
//            $http({
//              method: 'GET',
//              url: '/api/char/',
//              headers: {'x-access-token': $window.localStorage['jwtToken']}  })
//                .success(function(data) {
//                    $scope.chars = data;
//                    console.log('got these chars back: ');
//                    console.log(data);
//                })
//                .error(function(data) {
//                    console.log('Error:');
//                    console.log(data);
//                }
//            );
        }

        $scope.fetchChars();

        $scope.newChar = function() {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'pages/templates/new-char-modal.html',
                controller: 'NewCharCtrl',
                size: 'md',
                resolve: {}
            });

            modalInstance.result.then(
                function (newChar) {		// ok selected
                    APIService.createChar(newChar, function(response) {
                        $scope.fetchChars();
                        console.log(response.data);
                    });

//                    $http({
//                        method:'POST',
//                        url:'/api/char/',
//                        headers: {'x-access-token': $window.localStorage['jwtToken']},
//                        data: newChar  })
//                    .success(function(data) {
////                        $scope.formData = data;
//                        $scope.fetchChars();
//                        console.log(data);
//                    })
//                    .error(function(data) {
//                        console.log('Error: ' + data);
//                    });
                },
                function () {					// closed

                }
            );
        }

        $scope.selectChar = function(charId) {
//            $http({
//                method:'POST',
//                url:'/api/char/' + charId + '/',
//                headers: {'x-access-token': $window.localStorage['jwtToken']}
//            })
//            .success(function(data) {
//                $window.localStorage['jwtToken'] = data.token;
//                $location.path("/hq/");
//            })
//            .error(function(data) {
//                console.log('Error: ' + data);
//            });
            $location.path("/hq/" + charId);
        }

	});


// original
    angular.module('avalonApp').controller('NewCharCtrl', function ($scope, $uibModalInstance) {
        $scope.submitForm = function () {
            if ($scope.charForm.$valid) {
//                console.log('user form is in scope');
                var char = {
                    name: $scope.name
                };
                $uibModalInstance.close(char);
            } else {
                console.log('userform is not in scope');
            }
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    });