var module = angular.module('home', [ 'ngCookies' ]);

module.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('', {
		template : DOMAIN + '/home/view'
	}).when('/tos', {
		template : DOMAIN + '/home/tos'
	}).when('/guide', {
		template : DOMAIN + '/home/guide'
	}).otherwise({
		redirectTo : ''
	});
} ]);
