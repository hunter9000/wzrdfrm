//    angular.module('avalonApp').directive('materialadd', ['$http', '$window', function ($http, $window) {
//        return {
//            restrict: 'E',
//            templateUrl: 'pages/templates/material-add-template.html',
//            replace: true,
//            scope: {
//                refreshCallback: '&refresh'
//            },
//            link: function (scope, element, attrs) {
////                scope.showForm = false;
//                scope.mat = {name: '', icon: ''};
//
////                scope.clickMe = function () {
////                    scope.showForm = !scope.showForm;
////                };
//
//                scope.createNew = function() {
////                    scope.showForm = !scope.showForm;
//                    console.log(scope.mat);
//                    // post scope.mat to the server
//                    $http({method:'POST',
//                           url: 'api/materials/',
//                           data: scope.mat,
//                           headers: {'x-access-token': $window.localStorage['jwtToken']}
//                        })
//                        .success(function (data, status, headers, config) {
////                            scope.fetchMats();
//                            scope.refreshCallback();
//                            console.log(data);
//                        })
//                        .error(function(data, status, headers, config) {
//                            console.log('Error:' + data);
//                        }
//                    );
//                }
//            }
//        };
//    }]);