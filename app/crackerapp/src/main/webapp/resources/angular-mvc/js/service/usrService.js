/* 
===========================================
#  auther: RITESH SINGH                   #
#  User App Service                       #
#                                         #
===========================================
*/ 

'use strict';

angular.module('userApp').factory('userservice',
		[ '$resource', function($resource) {
			return new User($resource);
		} ]);

function User(resource) {
	this.resource = resource;
	
	this.createUser = function(user, scope) {
		/*var User = resource('/users/new');
		User.save(user, function(response) {
			scope.message = response.message;
		});*/
	}

	this.getUser = function(id, scope) {
		/*var User = resource('/users/:userId', {
			userId : '@userId'
		});
		User.get({userId : id}, function(user) {
			scope.user = user;
		})*/
	}

	this.getUsers = function(scope) {
		/*var Users = resource('/angSpring/users/all');
		Users.query(function(users) {
			scope.users = users;
		});*/
	}

	this.updateUser = function(scope) {
		/*var Users = resource('/angSpring/users/all', null, {
			update : {
				method : 'PUT'
			}
		});
		Users.update(function(users) {
			scope.users = users;
		});*/
	}
}