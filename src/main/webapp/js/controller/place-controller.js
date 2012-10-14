function PlaceCtrl($rootScope, $scope, $http, $routeParams) {
	$scope.place = {};
	$scope.placeId = $routeParams.placeId;
	
	$scope.getcomment=function(){
		FB.XFBML.parse();
	};

	$scope.getPlace = function() {
		$http.get(DOMAIN + '/data/place/get/' + $scope.placeId).success(
				function(data) {
					$scope.place = data;
					$scope.getcomment();
					$scope.initDirectionLink();
				});
	};

	$scope.sharePlace = function() {
		var fbDcrp = clearHTMLTag($scope.place.dcrp);
		shareFacebook(DOMAIN + '/img/gallery/thumb/' + $scope.place.avatar,
				$scope.place.name, $scope.place.address + ' '
						+ $scope.place.streetName + ',' + $scope.place.wardName
						+ ',' + $scope.place.districtName + ','
						+ $scope.place.cityName, fbDcrp);
	};
	
	$scope.directionLink = "";
	$scope.initDirectionLink = function() {
		getCurrentLocation();
		setTimeout(function() {
		$scope.directionLink = " http://maps.google.com/maps?" + "saddr="
				+ curLocation + "&daddr=" + $scope.place.address + " "
				+ $scope.place.streetName + " "
				+ $scope.place.wardName + " "
				+ $scope.place.districtName + " "
				+ $scope.place.cityName + "&z=15";
		$http.get();
		},1000);
	};
	
	// *Initial
	$scope.getPlace();
}

var locationDone;

function PlaceNewCtrl($scope, $http, $location, businessTypeService,
		locationService, errorHandlerService, validationService) {
	$scope.place = {};
	$scope.businessTypes = [];
	$scope.districts = [];
	$scope.wards = [];
	$scope.streets = [];

	$scope.getPlace = function() {
		$http.get(DOMAIN + '/data/place/new').success(function(data) {
			$scope.place = data;
		});
	};

	$scope.getWards = function() {
		$scope.wards = [];
		locationService.getWards($scope.place.districtId, $scope.wards);
	};

	$scope.getStreets = function() {
		$scope.streets = [];
		locationService.getStreets($scope.place.wardId, $scope.streets);
	};

	$scope.postPlace = function() {
		$scope.place.dcrp = nicEditors.findEditor('dcrp').getContent();
		
		// Validate information
		validationService.checkEmpty($scope.place.name,'err201', $scope.langCommon.createFailed);
		validationService.checkLarger($scope.place.name, PLACE_NAME_LENGTH,'err202', $scope.langCommon.createFailed);
		validationService.checkId($scope.place.businessTypeId, 'err203', $scope.langCommon.createFailed);
		validationService.checkId($scope.place.districtId, 'err204', $scope.langCommon.createFailed);
		validationService.checkId($scope.place.wardId, 'err205', $scope.langCommon.createFailed);
		validationService.checkId($scope.place.streetId, 'err206', $scope.langCommon.createFailed);
		validationService.checkEmpty($scope.place.address,'err207', $scope.langCommon.createFailed);
		validationService.checkLarger($scope.place.address, PLACE_ADDRESS_LENGTH,'err208', $scope.langCommon.createFailed);
		validationService.checkLarger($scope.place.dcrp, PLACE_DESCRIPTION_LENGTH,'err209', $scope.langCommon.createFailed);
		
		// Get Geo code for place
		var address = $scope.place.address + ' '
				+ $("#cmbStreet option:selected").text() + ', '
				+ $("#cmbWard option:selected").text() + ', '
				+ $("#cmbDistrict option:selected").text() + ', Ho Chi Minh';

		locationDone = false;
		getGeoCodeAddress(address, $scope.place);

		var timeout = null;
		timeout = setInterval(function() {
			if (locationDone == true) {
				$http.post(DOMAIN + '/data/place/new', $scope.place).success(
						function(data) {
							// Check error
							errorHandlerService.handle(data,
									$scope.langCommon.createFailed);

							// Redirect to update page
							$location.path('placeupdate/additional/' + data);
						});
				clearTimeout(timeout);
			}
		}, 100);
	};

	$scope.cancel = function() {
		window.location = DOMAIN + '/search#/';
	};

	// * Initial
	$scope.getPlace();
	businessTypeService.getList($scope.businessTypes);
	locationService.getDistricts(1, $scope.districts);
	locationService.getWards(1, $scope.wards);
	locationService.getStreets(1, $scope.streets);
	initnicEditor('dcrp', '');
}
PlaceNewCtrl.$inject = [ '$scope', '$http', '$location', 'businessTypeService',
		'locationService', 'errorHandlerService', 'validationService' ];

function PlaceUpdateCtrl($scope, $http, $routeParams, $location,
		businessTypeService, locationService, errorHandlerService, validationService) {

	$scope.place = {};
	$scope.businessTypes = [];
	$scope.districts = [];
	$scope.wards = [];
	$scope.streets = [];

	$scope.placeId = $routeParams.placeId;

	$scope.getPlace = function() {
		$http.post(DOMAIN + '/data/place/update/' + $scope.placeId).success(
				function(data) {
					$scope.place = data;
					locationService.getDistricts($scope.place.cityId,
							$scope.districts);
					locationService.getWards($scope.place.districtId,
							$scope.wards);
					locationService.getStreets($scope.place.wardId,
							$scope.streets);
					initnicEditor('dcrp', $scope.place.dcrp);
				});
	};

	$scope.getWards = function() {
		$scope.wards = [];
		locationService.getWards($scope.place.districtId, $scope.wards,
				$scope.wardStore);
	};

	$scope.getStreets = function() {
		$scope.streets = [];
		locationService.getStreets($scope.place.wardId, $scope.streets,
				$scope.streetStore);
	};

	$scope.savePlace = function() {
		$scope.place.dcrp = nicEditors.findEditor('dcrp').getContent();
		
		// Validate data
		validationService.checkEmpty($scope.place.name,'err201', $scope.langCommon.updateFail);
		validationService.checkLarger($scope.place.name, PLACE_NAME_LENGTH,'err202', $scope.langCommon.updateFail);
		validationService.checkId($scope.place.businessTypeId, 'err203', $scope.langCommon.updateFail);
		validationService.checkId($scope.place.districtId, 'err204', $scope.langCommon.updateFail);
		validationService.checkId($scope.place.wardId, 'err205', $scope.langCommon.updateFail);
		validationService.checkId($scope.place.streetId, 'err206', $scope.langCommon.updateFail);
		validationService.checkEmpty($scope.place.address,'err207', $scope.langCommon.updateFail);
		validationService.checkLarger($scope.place.address, PLACE_ADDRESS_LENGTH,'err208', $scope.langCommon.updateFail);
		validationService.checkLarger($scope.place.dcrp, PLACE_DESCRIPTION_LENGTH,'err209', $scope.langCommon.updateFail);

		// Get Geo Code
		var address = $scope.place.address + ' '
				+ $("#cmbStreet option:selected").text() + ', '
				+ $("#cmbWard option:selected").text() + ', '
				+ $("#cmbDistrict option:selected").text() + ', Ho Chi Minh';

		locationDone = false;
		getGeoCodeAddress(address, $scope.place);

		var timeout = null;
		timeout = setInterval(function() {
			if (locationDone == true) {
				$http.post(DOMAIN + '/data/place/save/' + $scope.placeId,
						$scope.place).success(
						function(data) {
							// Check error
							errorHandlerService.handle(data,
									$scope.langCommon.updateFail);

							$.pnotify({
								delay : 2000,
								title : $scope.langCommon.updateSuccess,
								type : 'success',
							});
						});
				clearTimeout(timeout);
			}
		}, 100);
	};

	$scope.goPlace = function() {
		$location.path('view/' + $scope.placeId);
	};

	// * Initial
	$scope.getPlace();
	businessTypeService.getList($scope.businessTypes);
}
PlaceUpdateCtrl.$inject = [ '$scope', '$http', '$routeParams', '$location',
		'businessTypeService', 'locationService', 'errorHandlerService', 'validationService' ];

function PlaceAdditionalCtrl($scope, $http, $routeParams, $location) {
	$scope.placeId = $routeParams.placeId;
	$scope.goPlace = function() {
		$location.path('view/' + $scope.placeId);
	};
}

function PlaceSettingController($scope, $http, $routeParams, $location) {
	$scope.placeId = $routeParams.placeId;
	$scope.managers = [];

	$scope.getManagerTemplate = function() {
		$http.get(DOMAIN + '/data/place/setting/manager/new').success(
				function(data) {
					$scope.newManager = data;
				});
	};

	$scope.updateMaster = function() {
		if ($scope.master == true)
			for ( var i in $scope.managers) {
				$scope.managers[i].checked = true;
			}
		else
			for ( var i in $scope.managers) {
				$scope.managers[i].checked = false;
			}
	};

	$scope.deleteManagers = function() {
		$http
				.post(
						DOMAIN + '/data/place/setting/manager/delete/'
								+ $scope.placeId, $scope.managers).success(
						function(data) {
							for ( var i = 0; i < $scope.managers.length; i++) {
								if ($scope.managers[i].checked) {
									$scope.managers.splice(i, 1);
									i--;
								}
							}
						});
	};

	$scope.addManager = function() {
		$scope.newManager.userId = 0;
		$scope.newManager.name = "";
		$http
				.post(
						DOMAIN + '/data/place/setting/manager/new/'
								+ $scope.placeId, $scope.newManager)
				.success(
						function(data) {
							if (data.email == undefined || data.email == null) {
								alert('User này không tồn tại hoặc đã có trong danh sách');
							} else {
								if (!managerIndexOf($scope.managers,
										data.userId))
									$scope.managers.push(data);
							}
						});
	};
	$scope.getManagers = function() {
		$http
				.post(
						DOMAIN + '/data/place/setting/manager/get/'
								+ $scope.placeId).success(function(data) {
					$scope.managers = data;
				});
	};

	$scope.goPlace = function() {
		$location.path('view/' + $scope.placeId);
	};

	$scope.getManagerTemplate();
	$scope.getManagers();
};

function managerIndexOf(managers, userId) {
	for ( var i in managers) {
		if (managers[i].userId == userId)
			return true;
	}
	return false;
};

function getGeoCodeAddress(address, place) {
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			// return results[0].geometry.location;
			var location = results[0].geometry.location;
			place.locationX = location.lat();
			place.locationY = location.lng();
			locationDone = true;
		} else {
			alert("Geocode was not successful for the following reason: "
					+ status);
			// return null;
		}
	});

};

// ------------- PLACE CONSTANT --------------
var PLACE_NAME_LENGTH = 100;
var PLACE_ADDRESS_LENGTH = 200;
var PLACE_DESCRIPTION_LENGTH = 10000;