var module = angular.module('search', [ 'ngCookies' ]);

module.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('', {
		template : DOMAIN + '/search/result'
	}).otherwise({
		template : DOMAIN + '/search/result'
	});
} ]);
