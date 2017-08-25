	// create the module and name it scotchApp
	var avalonApp = angular.module('avalonApp', ['ngRoute', 'ui.bootstrap', 'checklist-model', 'ngMaterial']);

	// configure our routes
	avalonApp.config(function($routeProvider) {
		$routeProvider
			// route for the home page
			// main-controller.js
			.when('/', {
				templateUrl : 'pages/home.html',
				controller  : 'mainController'
			})
			// route for the login page
			// login-controller.js
			.when('/login', {
				templateUrl : 'pages/login.html',
				controller  : 'loginController'
			})

			// USERS #######
			// admin/new-user-controller.js
            .when('/newuser', {
                templateUrl : 'pages/admin/newuser.html',
                controller  : 'newUserController'
            })
			// admin/users-controller.js
			.when('/users', {
			    templateUrl : 'pages/admin/users.html',
			    controller  : 'usersController'
			})
			// admin/edit-user-controller.js
            .when('/edituser/:userId', {
                templateUrl: 'pages/admin/edituser.html',
                controller: 'editUserController'
            })
            // profile-controller.js
            .when('/profile', {
                templateUrl : 'pages/profile.html',
                controller  : 'profileController'
            })

            // CHARACTERS #######
            // route for the char select page
            // char-controller.js
            .when('/charselect', {
                templateUrl : 'pages/charselect.html',
                controller  : 'charController'
            })
//			// route for the new char page
//			// new-char-controller.js
//			.when('/newchar', {
//				templateUrl : 'pages/newchar.html',
//				controller  : 'newCharController'
//			})
            // edit-char-controller.js
			.when('/editchar/:charId', {
				templateUrl: 'pages/editchar.html',
				controller: 'editCharController'
			})


            // admin/material-controller.js
			.when('/materials', {
				templateUrl: 'pages/admin/materials.html',
				controller: 'materialsController'
			})
			// hq-controller.js
			.when('/hq/:charId', {
				templateUrl: 'pages/hq.html',
				controller: 'hqController'
			})
			// crafting-controller.js
			.when('/crafting/:charId', {
				templateUrl: 'pages/crafting.html',
				controller: 'craftingController'
			})
			// inventory-controller.js
			.when('/inventory/:charId', {
				templateUrl: 'pages/inventory.html',
				controller: 'inventoryController'
			})
			// dungeon-controller.js
			.when('/dungeon/:charId', {
				templateUrl: 'pages/dungeon.html',
				controller: 'dungeonController'
			})
			// portals-controller.js
			.when('/portals/:charId', {
				templateUrl: 'pages/portals.html',
				controller: 'portalsController'
			})

            // generic error page
            .when('/error', {
                templateUrl: 'pages/error.html',
                controller: 'errorController'
            })
			.otherwise({redirectTo:'/'});
			;
	});

//	// create the controller and inject Angular's $scope
//	scotchApp.controller('mainController', function($scope, $location, $window) {
//		// create a message to display in our view
//		$scope.message = 'main';
//
//		$scope.logout = function() {
//			$window.localStorage['jwtToken'] = undefined;
//			$location.path('/');
//		};
//
//		$scope.home = function() {
//			$location.path('/');
//		};
//
//
//		var init = function() {
//			var token = $window.localStorage['jwtToken']
//			console.log('checking '+ token);
//			if (token === 'undefined' || token === null || token == null) {
//				$location.path('/login');
//				console.log('redirecting');
//			}
//			else {
//				$location.path('/charselect');
//			}
//		};
//		init();
//	});
//
//	// create the controller and inject Angular's $scope
//	scotchApp.controller('loginController', function($scope, $location, $http, $window) {
//		// create a message to display in our view
//		$scope.message = 'Everyone come and see how good I look!';
//
//		$scope.formData = {};
//
//		// when submitting the add form, send the text to the node API
//		$scope.login = function() {
//			$http.post('/api/authenticate', $scope.formData)
//				.success(function(data) {
//					/*$scope.formData = {}; // clear the form so our user is ready to enter another
//					$scope.todos = data;*/
//					console.log(data);
//
//					if (!data.success) {
//						$scope.message = 'invalid login';
//					}
//					else {
//						$window.localStorage['jwtToken'] = data.token;
//						$location.path("/charselect");
//					}
//				})
//				.error(function(data) {
//					console.log('Error: ' + data);
//					$scope.message = 'invalid login - error returned';
//				});
//		};
//
//
//	});
//
//
//
//	scotchApp.factory('StaticData', ['$http', '$window', function($http, $window) {
//		var serviceData = {
//			effectTypes: [],
//			equipmentSlots: []
//		};
//
//		$http({method:'GET',
//			   url: '/api/effectTypes',
//			   headers: {'x-access-token': $window.localStorage['jwtToken']}
//			})
//			.success(function (data) {
//				serviceData.effectTypes = data;
//				console.log(data);
//			})
//			.error(function(data) {
//				console.log('Error:' + data);
//			}
//		);
//
//		$http({method:'GET',
//			   url: '/api/equipmentSlots',
//			   headers: {'x-access-token': $window.localStorage['jwtToken']}
//			})
//			.success(function (data) {
//				serviceData.equipmentSlots = data;
//				console.log(data);
//			})
//			.error(function(data) {
//				console.log('Error:' + data);
//			}
//		);
//
//		return serviceData;
//	}]);






