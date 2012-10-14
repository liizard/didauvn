/*
 *	This Controller using function from 
 *		"webapp\js\comp\map.js"
 */

function SearchCtrl($cookieStore, $rootScope, $scope, $http) {
	$scope.oneMapPlace;
	var mapLoaded = false;

	$scope.searchPlace = function() {
		$scope.searchPlaces.splice(0, $scope.searchPlaces.length);
		$scope.placeSearchRq.page = 1;
		$scope.getMore();
		$scope.clearMarker();
	};

	$scope.getMore = function() {
		$http.post(DOMAIN + '/data/search/place', $scope.placeSearchRq)
				.success(function(data) {
					$scope.length = data.length;
					if ($scope.length == (SEARCH_RESULT_PER_PAGE + 1)) {
						$scope.length -= 1;
						$scope.searchOption.viewmore = 1;
					} else {
						$scope.searchOption.viewmore = 0;
					}
					for ( var i = 0; i < $scope.length; i++) {
						// searchPlaces declare on SiteCtrl
						$scope.searchPlaces.push(data[i]);

						// add markers
						$scope.addMarker(data[i]);
					}
					$scope.placeSearchRq.page += 1;
					// Set map center
					if (map != undefined)
						$scope.setCenter();
				});
	};

	$scope.loadOneMapPlace = function(id) {
		var index = existed($scope.searchPlaces, id);
		if (index != -1) {
			$scope.oneMapPlace = $scope.searchPlaces[index];
			$scope.initDirectionLink();
			$http.get();
		}
	};

	$scope.addMarker = function(data) {
		latlng = new google.maps.LatLng(data.locationX, data.locationY);
		var icon = DOMAIN + '/img/res/icon/map/map_' + data.businessTypeId
				+ '.png';
		var marker = new google.maps.Marker({
			position : latlng,
			title : data.name,
			zIndex : 1,
			map : map,
			icon : icon
		// animation: google.maps.Animation.DROP
		});
		$scope.addMarkerEvent(marker, data.id);
		markers.push(marker);
		markersId.push(data.id);
	};

	$scope.clearMarker = function() {
		for (i in markers) {
			markers[i].setMap(null);
		}
		markers = [];
	};

	$scope.setMarker = function() {
		for (i in markers) {
			markers[i].setMap(map);
			$scope.addMarkerEvent(markers[i], markersId[i]);
		}
	};

	$scope.addMarkerEvent = function(m, id) {
		google.maps.event.addListener(m, "click", function() {
			$scope.loadOneMapPlace(id);
			m.setZIndex(2);
		});
		google.maps.event.addListener(m, "click", function() {
			m.setZIndex(1);
		});
	};

	$scope.setCenter = function() {
		if ($scope.placeSearchRq.locationDisplay == '') {
			getCurrentLocation();
			if (curLocation != null) {
				map.setCenter(curLocation);
			} else {
				map.setCenter(defaultCenter);
			}
			return;
		}
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			'address' : $scope.placeSearchRq.locationDisplay + ", Ho Chi Minh"
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK
					&& $scope.placeSearchRq.locationDisplay != '') {
				map.setCenter(results[0].geometry.location);
			} else {
				map.setCenter(defaultCenter);
			}
		});
	};

	$scope.loadMap = function() {
		if (mapLoaded == false) {
			// if(map == null){
			mapLoaded = true;
			setTimeout(function() {
				initializeSearchMap();
				$scope.setMarker();
				$scope.setCenter();
				google.maps.event.trigger(map, 'resize');
			}, 10);
		}
	};

	$scope.directionLink = "";
	$scope.initDirectionLink = function() {
		$scope.directionLink = " http://maps.google.com/maps?" + "saddr="
				+ curLocation + "&daddr=" + $scope.oneMapPlace.address + " "
				+ $scope.oneMapPlace.streetName + " "
				+ $scope.oneMapPlace.wardName + " "
				+ $scope.oneMapPlace.districtName + " "
				+ $scope.oneMapPlace.cityName + "&z=15";
	};

	// * Initial
	downSearch();
	$scope.searchPlace();

}
