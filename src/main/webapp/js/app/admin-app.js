var module = angular.module('admin', [ 'ngCookies' ]);

module.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('', {
		template : DOMAIN + '/admin/view'
	}).when('/user', {
		template : DOMAIN + '/admin/user'
	}).when('/feedback', {
		template : DOMAIN + '/admin/feedback'
	}).when('/report', {
		template : DOMAIN + '/admin/report'
	}).otherwise({
		redirectTo : ''
	});
} ]);
