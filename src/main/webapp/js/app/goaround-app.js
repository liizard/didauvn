var module = angular.module('goaround', [ 'ngSanitize', 'ngCookies' ]);
module.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('', {
		template : DOMAIN + '/goaround/news'
	}).when('/goaround/news', {
		template : DOMAIN + '/goaround/news'
	}).when('/goaround/offers', {
		template : DOMAIN + '/goaround/offers'
	}).when('/goaround/gallery', {
		template : DOMAIN + '/goaround/gallery'
	}).otherwise({
		redirectTo : ''
	});
} ]);
