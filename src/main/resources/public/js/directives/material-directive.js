
    angular.module('avalonApp').directive('material', ['$http', '$window', '$uibModal', function ($http, $window, $uibModal) {
        return {
            restrict: 'E',
            templateUrl: 'pages/templates/material-template.html',
            replace: true,
            scope: {
                material: '=material',
                refreshCallback: '&refresh'
            },
            link: function (scope, element, attrs) {
//                scope.showForm = false;         // show the new effect form
//                scope.effectTypes = StaticData.effectTypes;
//                scope.equipmentSlots = StaticData.equipmentSlots;
//                scope.eff = {effectType: '', value: '', slot: '', matId: scope.material.id};

//                scope.showEffectForm = function () {
//                    scope.showForm = !scope.showForm;
//                };

//                scope.createEffect = function() {
////                    scope.showForm = !scope.showForm;
//
//                    $http({method:'POST',
//                           url: 'api/materials/'+scope.material.id+'/effect',
//                           data: scope.eff,
//                           headers: {'x-access-token': $window.localStorage['jwtToken']}
//                        })
//                        .success(function (data, status, headers, config) {
//                            scope.refreshCallback();
//                            console.log(data);
//                        })
//                        .error(function(data, status, headers, config) {
//                            console.log('Error:' + data);
//                        }
//                    );
////                    scope.eff = {effectType: '', value: '', slot: '', matId: scope.material.id};      // reset the eff
//                };

                scope.deleteMat = function(matId) {
                    console.log('jwt ' + $window.localStorage['jwtToken']);
                    $http.delete('api/materials/' + matId +'/',
                       { headers: {'x-access-token': $window.localStorage['jwtToken']}}
                    ).then(function successCallback(response) {
                            console.log(response);
                            $scope.refreshCallback();
                        }, function errorCallback(response) {
                            console.log('Error: ' + response);
                            $location.path('/error');
                        }
                    );
                };

                scope.editMat = function(mat) {
                                        var modalInstance = $uibModal.open({
                                            animation: false,
                                            templateUrl: 'pages/templates/material-edit-template.html',
                                            controller: 'editMaterialController',
                                            size: 'md',
                                            resolve: {
                                                mat: function () {
                                                    return mat;    // pass the character to delete
                                                }
                                            }
                                        });

                                        modalInstance.result.then(function (mat) {

                                        }, function () {
                                             console.log('Modal dismissed at: ' + new Date());
                                        });
                }
            }
        };
    }]);