var module = angular.module('place', [ 'ngSanitize', 'ngCookies' ]);

module.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('', {
		template : DOMAIN + '/placenew'
	}).when('/view/:placeId', {
		template : DOMAIN + '/placeview'
	}).when('/new', {
		template : DOMAIN + '/placenew'
	}).when('/setting/:placeId', {
		template : DOMAIN + '/placesetting'
	}).when('/update/general/:placeId', {
		template : DOMAIN + '/placeupdate/general'
	}).when('/update/additional/:placeId', {
		template : DOMAIN + '/placeupdate/additional'
	}).when('/update/gallery/:placeId', {
		template : DOMAIN + '/placeupdate/image'
	}).when('/update/video/:placeId', {
		template : DOMAIN + '/placeupdate/video'
	}).otherwise({
		redirectTo : ''
	});
} ]);

