var module = angular.module('user', [ 'ngCookies' ]);

module.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('', {
		template : DOMAIN + '/user/login'
	}).when('/view/:userId', {
		template : DOMAIN + '/user/view'
	})
//	.when('/general', {
//		template : DOMAIN + '/user/general'
//	})
	.when('/place', {
		template : DOMAIN + '/user/place'
	}).when('/manager', {
		template : DOMAIN + '/user/manager'
	}).when('/login', {
		template : DOMAIN + '/user/login'
	}).when('/register', {
		template : DOMAIN + '/user/register'
	}).when('/logout', {
		template : DOMAIN + '/logout'
	})
//	.when('/connect', {
//		template : DOMAIN + '/connect'
//	})
//	.when('/message', {
//		template : DOMAIN + '/user/message'
//	})
	.otherwise({
		redirectTo : ''
	});
} ]);
