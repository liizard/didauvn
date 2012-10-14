var module = angular.module('site', [ 'ngSanitize', 'ngCookies' ]);
module.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('', {
		template : DOMAIN + '/search'
	}).when('/search', {
		template : DOMAIN + '/search'
	}).when('/goaround', {
		template : DOMAIN + '/goaround/news'
	}).when('/goaround/news', {
		template : DOMAIN + '/goaround/news'
	}).when('/goaround/offers', {
		template : DOMAIN + '/goaround/offers'
	}).when('/goaround/gallery', {
		template : DOMAIN + '/goaround/gallery'
	}).when('/place/:placeId', {
		template : DOMAIN + '/place'
	}).when('/placenew', {
		template : DOMAIN + '/placenew'
	}).when('/placeupdate/general/:placeId', {
		template : DOMAIN + '/placeupdate/general'
	}).when('/placeupdate/additional/:placeId', {
		template : DOMAIN + '/placeupdate/additional'
	}).when('/placeupdate/gallery/:placeId', {
		template : DOMAIN + '/placeupdate/image'
	}).when('/placeupdate/video/:placeId', {
		template : DOMAIN + '/placeupdate/video'
	}).when('/placeupdate/news/:placeId', {
		template : DOMAIN + '/placeupdate/news'
	}).when('/placesetting/:placeId', {
		template : DOMAIN + '/placesetting'
	}).when('/user/info/:userId', {
		template : DOMAIN + '/user'
	}).when('/user/general', {
		template : DOMAIN + '/user/general'
	}).when('/user/connect', {
		template : DOMAIN + '/connect'
	}).when('/user/place', {
		template : DOMAIN + '/user/place'
	}).when('/login', {
		template : DOMAIN + '/login'
	}).when('/logout', {
		template : DOMAIN + '/logout'
	}).when('/langGallery/:placeId', {
		template : DOMAIN + '/langGallery'
	}).when('/register', {
		template : DOMAIN + '/register'
	}).otherwise({
		redirectTo : ''
	});
} ]);

