/* 
===========================================
#  auther: RITESH SINGH                   #
#  User App Router                        #
#                                         #
===========================================
*/ 


'use strict';

var userApp = angular.module("userApp", [ 'ngRoute', 'ngResource', 'ui.router' ]);

/*userApp.config([
		'$routeProvider',
		function($routeProvider) {

			$routeProvider.when(
					'/users/new',
					{
						controller : 'NewUserCtrl',
						templateUrl : templateUrlConfig.appTemplateEntryPoint
								+ templateUrlConfig.userTemplateGroup
								+ 'launchUser'
						redirectTo: '/check'
					}).when('/check', {
				template : 'This is the default Route'
			}).otherwise({
				controller : 'SpaCtrl',
				templateUrl : 'views/spahome.html'
			});
		} ]);*/


userApp.config(function($stateProvider, $urlRouterProvider){
	  $stateProvider
	    .state('forms', {
	      url: '/users/new',
	      views: userViewsConfig.indexView,
	      controller: 'NewUserCtrl',
	      authenticate: true
	    })
	    .state('login', {
	      url: '/check',
	      template: 'This is the default Route',
	      authenticate: false
	    })
	    .state('page', {
	      url: '/checkPage',
	      views: userViewsConfig.homeView,
	      authenticate: false
	    });
	  // Send to login if the URL was not found
	  $urlRouterProvider.otherwise("/check");
	});

userApp.run(function ($rootScope, $state, AuthService) {
	  $rootScope.$on("$stateChangeStart", function(event, toState, toParams, fromState, fromParams){
	    if (toState.authenticate && !AuthService.isAuthenticated()){
	      // User isn’t authenticated
	      $state.transitionTo("login");
	      event.preventDefault(); 
	    }
	  });
	});


