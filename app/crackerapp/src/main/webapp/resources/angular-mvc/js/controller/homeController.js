/* 
===========================================
#  auther: RITESH SINGH                   #
#  User App Controller                    #
#                                         #
===========================================
*/ 

'use strict';

// Controller when the main page/view loads

angular.module('homeApp').controller("SpaCtrl", [ '$scope', function($scope) {

} ]);

angular.module('homeApp').controller("HeaderCtrl", [ '$scope', function($scope) {

} ]);

angular.module('homeApp').controller("BodyCtrl", [ '$scope', function($scope) {

} ]);

// Controller for All Users View

angular.module('homeApp').controller(
		"HomeCtrl",
		[ '$scope', 'homeservice', '$route', '$routeParams', '$location',
				function($scope, homeservice, $route, $routeParams, $location) {

					homeservice.getUsers($scope);

				} ]);

// Controller for New User View

angular.module('homeApp').controller(
		"NewHomeCtrl",
		[ '$scope', 'homeservice', '$route', '$routeParams', '$location',
				function($scope, homeservice, $route, $routeParams, $location) {

					console.log("new controller started. rout: "+$route+" routeParam: "+$routeParams);

					homeservice.getUsers($scope); 
					/*userservice.updateUser($scope);*/
					console.log($scope.users);

					$scope.createNewUser = function() {

						var newuser = {
							'firstname' : $scope.firstname,
							'lastname' : $scope.lastname,
							'address' : $scope.address,
							'email' : $scope.email
						};

						homeservice.createUser(newuser, $scope);

						$scope.users.push(newuser);

						$scope.firstname = '';

						$scope.lastname = '';

						$scope.address = '';

						$scope.email = '';

					};

				} ]);

// Controller for Individual User View

angular.module('homeApp').controller(
		"HomeByIdCtrl",
		[ '$scope', 'homeservice', '$routeParams',
				function($scope, homeservice, $routeParams) {

					homeservice.getUser($routeParams.userId, $scope);

				} ]);