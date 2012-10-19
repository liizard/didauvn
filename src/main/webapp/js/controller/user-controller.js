function UserCtrl($rootScope, $scope, $http, errorHandlerService,validationService) {
	$scope.name = '';
	$scope.password = [];
	$scope.password2 = '';
	$scope.placeowners = [];
	$scope.placemanagers = [];
	$scope.images = [];
	$scope.upload = [];

	$scope.getNewPass = function() {
		$http.get(DOMAIN + '/data/user/password/new').success(function(data) {
			$scope.password = data;
		});
	};

	$scope.getNewUserSession = function() {
		if ($rootScope.userSession.user != undefined) {
			if ($rootScope.userSession.user.uid == 0) {
				$rootScope.getUserSession();
			}
		}
	};

	$scope.getPlaceOwner = function() {
		$http.get(DOMAIN + '/data/user/getplaceowner').success(function(data) {
			$scope.placeowners = data;
		});
	};
	$scope.getPlaceManager = function() {
		$http.get(DOMAIN + '/data/user/getplacemanager').success(
				function(data) {
					$scope.placemanagers = data;
				});
	};

		// Initial
	$scope.getNewUserSession();
	$scope.getPlaceOwner();
	$scope.getPlaceManager();

	$(function() {
		$.datepicker.setDefaults($.datepicker.regional[$rootScope.lang]);
		$("#datepicker").datepicker({
			onSelect : function(dateText) {
				$scope.userSession.user.birthday = dateText;
				$scope.$apply();
			}

		});

	});

	// Upload avatar

	$scope.notify = function(cont) {
		$.pnotify({
			delay : 2000,
			title : cont,
			type : 'success'
		});
	};

	$scope.goPlace = function() {
		$location.path('place/' + $scope.placeId);
	};
}
UserCtrl.$inject = [ '$rootScope', '$scope', '$http', 'errorHandlerService','validationService'];

function connectfb() {
	document.getElementById("fb_signin").submit();
}
