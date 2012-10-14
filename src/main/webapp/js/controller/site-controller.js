function SiteCtrl($cookieStore, $rootScope, $scope, $http, $location,
		langService, locationService, securityService, tagService, locationAuto) {

	// * Search
	$scope.placeSearchRq = {};

	$scope.locationSearchRq = {
		"location" : ''
	};

	$scope.searchOption = {
		"viewmore" : 0,
		"sort" : 0
	};

	$scope.searchPlaces = [];
	$scope.locations = [];
	$scope.tagRq = {};

	$scope.changeLanguage = function(lang) {
		langService.setLanguage(lang);
	};

	// *Tag functions
	$scope.initTags = function(tags) {
		var i;
		for (i = 0; i < tags.length; i++) {
			tags[i].label = tags[i].name + " " + normalize(tags[i].name);
		}
	};

	// get Tags from server
	$scope.getTagRq = function() {
		$http.post(DOMAIN + '/data/attr/placetag/gettagrq').success(
				function(data) {
					$scope.tagRq = data;
					tagService.initAutocomplete($("#searchtag"),
							$scope.tagRq, $scope.placeSearchRq.tags, null);
				});
	};

	$scope.deleteTag = function(id) {
		tagService.deleteTag(id, $scope.placeSearchRq.tags);
	};
	
	$scope.deleteLocation = function() {
		locationAuto.deleteLocation($scope.placeSearchRq);
	};

	// SEARCH
	$scope.goSearch = function() {
		// Save placeSearchRq to contextHolder for further refer
		$http.post(DOMAIN + '/data/search/place/save', $scope.placeSearchRq)
				.success(
						function(data) {
							window.location = DOMAIN + '/search#/'
									+ $scope.searchParam();
						});
	};

	$scope.searchParam = function() {
		var params = '';
		if ($scope.placeSearchRq.districtId > 0) {
			params = 'd=' + $scope.placeSearchRq.districtId;
		} else if ($scope.placeSearchRq.wardId > 0) {
			params = 'w=' + $scope.placeSearchRq.wardId;
		} else if ($scope.placeSearchRq.streetId > 0) {
			params = 's=' + $scope.placeSearchRq.streetId;
		} else {
			params = 'c=' + $scope.placeSearchRq.cityId;
		}
		if ($scope.placeSearchRq.tags.length > 0) {
			params += ',t=';
			for ( var i in $scope.placeSearchRq.tags) {
				params += $scope.placeSearchRq.tags[i].id + ',';
			}
		}
		return params;
	};

	$scope.getPlaceSearchRq = function() {
		$http.get(DOMAIN + '/data/search/place/get').success(function(data) {
			$scope.placeSearchRq = data;
			// Initial tags list
			if ($scope.placeSearchRq.tags == null) {
				$scope.placeSearchRq.tags = [];
			}
			// Init Autocomplete for Location
			locationAuto.initAutocomplete($("#searchlocation"), $scope.locationSearchRq, $scope.placeSearchRq);
			// Init Autocomplete for Tag
			$scope.getTagRq();
		});
	};

	// *Initial
	$scope.getPlaceSearchRq();
}

SiteCtrl.$inject = [ '$cookieStore', '$rootScope', '$scope', '$http',
		'$location', 'langService', 'locationService', 'securityService',
		'tagService', 'locationAuto' ];
