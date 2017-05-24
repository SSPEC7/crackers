/* 
===========================================
#  auther: RITESH SINGH                   #
#  Auth Service                           #
#                                         #
===========================================
*/ 

'use strict';

angular.module('userApp').factory('AuthService',
		[ '$resource', function($resource) {
			return new Auth($resource);
		} ]);

function Auth(resource) {
	this.resource = resource;
	
	/*this.isAuthenticated = function() {
		console.log("Auth Check Point.");
		return false;
	}*/
	
	this.isAuthenticated = function(scope) {
		/*var User = resource('/angSpring/users/auth');
		User.get(function(userState) {
			scope.authStatus = userState.status;
		});*/
		/*return scope.authStatus;*/
		return true;
	}
}