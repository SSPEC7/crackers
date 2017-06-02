/* 
===========================================
#  auther: RITESH SINGH                   #
#  User App Router                        #
#                                         #
===========================================
*/ 


'use strict';

var homeApp = angular.module("homeApp",
		[ 'ngRoute',
		  'ngResource',
		  'ui.router'
		 ]);

var templateUrlConfig = {
		appTemplateEntryPoint : "/crackerapp/app/"
	};

homeApp.config(function($stateProvider, $urlRouterProvider,
							userUrls
							){
	  $stateProvider
	    .state('forms', {
	      url: userUrls.newUser,
	      views: {
					header: viewHeader,
					content:{
				      templateUrl: templateUrlConfig.appTemplateEntryPoint+"product-list",
					  controller: 'BodyCtrl'
				     }
				},
	      authenticate: true
	    })
	    .state('login', {
	    	url : '/check',
	    	views : {
	    			header : {
	    					template : ''
	    					},
	    			content : {
	    					template : '404',
	    					controller : 'CheckCtrl'
	    					}
	    			},
	    	authenticate : false
	    	});
	  // Send to login if the URL was not found
	  $urlRouterProvider.otherwise("/check");
	});

homeApp.run(function ($rootScope, $state, AuthService) {
	  $rootScope.$on("$stateChangeStart", function(event, toState, toParams, fromState, fromParams){
	    if (toState.authenticate && !AuthService.isAuthenticated()){
	      // User isn’t authenticated
	      $state.transitionTo("login");
	      event.preventDefault(); 
	    }
	  });
	});


