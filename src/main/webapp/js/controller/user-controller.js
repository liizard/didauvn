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

	$scope.changePass = function() {
		validationService.checkEmpty($scope.password.oldpass,'err251',$scope.langCommon.updateFail);
		validationService.checkEmpty($scope.password.newpass,'err252',$scope.langCommon.updateFail);
		validationService.checkSmaller($scope.password.oldpass,PASSWORD_LENGTH,'err226',$scope.langCommon.updateFail);
		validationService.checkSmaller($scope.password.newpass,PASSWORD_LENGTH,'err226',$scope.langCommon.updateFail);
		validationService.checkEqual($scope.password.newpass,$scope.password2,'err2201',$scope.langCommon.updateFail);
		$http
				.post(DOMAIN + '/data/user/password/change',
						$scope.password)
				.success(
						function(data) {
							errorHandlerService.handle(data,
									$scope.langCommon.updateFail);
							
							$scope.password.oldpass = "";
							$scope.password.newpass = "";
							$scope.password2 = "";
							document.getElementById('light').style.display = 'none';
							document.getElementById('fade').style.display = 'none';
							$
									.pnotify({
										delay : 2000,
										title : $scope.langCommon.updateSuccess,
										type : 'success',
									});

						});
	};
	$scope.updateProfile = function() {
		// Upload Image
		$('#fileupload').fileupload('option', {
			url : DOMAIN + '/data/user/upimg/'
		});
		for ( var i = 0; i < $scope.upload.length; i++) {
			$scope.upload[i].submit();
		}
		;
		validationService.checkEmpty($scope.userSession.user.birthday,'err224',$scope.langCommon.updateFail);
		validationService.checkDate($scope.userSession.user.birthday,'err2200',$scope.langCommon.updateFail);
		validationService.checkEmpty($scope.userSession.user.name,'err222',$scope.langCommon.updateFail);
		validationService.checkLarger($scope.userSession.user.name,USERNAME_LENGTH,'err223',$scope.langCommon.updateFail);
		$http.post(DOMAIN + '/data/user/update', $scope.userSession.user)
				.success(
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
	};
	// Initial
	$scope.getNewPass();
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

	$('#fileupload').fileupload();
	$('#fileupload').fileupload('option', 'redirect',
			window.location.href.replace(/\/[^\/]*$/, '/cors/result.html?%s'));
	$('#fileupload').fileupload('option', {
		maxFileSize : 5000000,
		acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
		done : function(e, data) {
			$scope.notify($scope.langCommon.updateSuccess);
			var imageId = ($.isArray(data.result) && data.result[0]);
			$scope.userSession.user.avatar = imageId;
			$http.get();
		},

		fail : function(e, data) {
			$scope.notify($scope.langCommon.uploadImageFail);
		}
	});

	$('#fileupload').bind('fileuploadadd', function(e, data) {
		$scope.images = [];
		$scope.upload = [];
		$scope.images.push(data.files[0].name);
		$http.get();
		$scope.upload.push(data);
	});

	$scope.goPlace = function() {
		$location.path('place/' + $scope.placeId);
	};
}
UserCtrl.$inject = [ '$rootScope', '$scope', '$http', 'errorHandlerService','validationService'];

function RegisterCtrl($rootScope, $scope, $http, $location, errorHandlerService,validationService) {
	$scope.user = [];
	$scope.confirmpassword = '';

	$scope.getUser = function() {
		$http.get(DOMAIN + '/data/user/new').success(function(data) {
			$scope.user = data;
		});
	};
	$scope.register = function() {
		validationService.checkEmail($scope.user.email,'err229',$scope.langUser.registerfail);
		validationService.checkEmpty($scope.user.name,'err222',$scope.langUser.registerfail);
		validationService.checkLarger($scope.user.name,USERNAME_LENGTH,'err223',$scope.langUser.registerfail);
		validationService.checkEmpty($scope.user.birthday,'err224',$scope.langUser.registerfail);
		validationService.checkDate($scope.user.birthday,'err2200',$scope.langUser.registerfail);
		validationService.checkEmpty($scope.user.password,'err225',$scope.langUser.registerfail);
		validationService.checkSmaller($scope.user.password,PASSWORD_LENGTH,'err226',$scope.langUser.registerfail);
		validationService.checkEqual($scope.user.password,$scope.confirmpassword,'err2201',$scope.langUser.registerfail);
		
		$http.post(DOMAIN + '/data/user/new', $scope.user).success(
		function(data) {
			// Check error
			errorHandlerService.handle(data,
					$scope.langUser.registerfail);

			// Redirect to user page login
			$location.path('login');
		});
	};
	// *Initial
	$scope.getUser();
	$(function() {
		$.datepicker.setDefaults($.datepicker.regional[$rootScope.lang]);
		$("#datepicker").datepicker({
			onSelect : function(dateText) {
				$scope.user.birthday = dateText;
				$scope.$apply();
			}
		});
	});
}
RegisterCtrl.$inject = [ '$rootScope', '$scope', '$http', '$location',
		'errorHandlerService','validationService' ];

function connectfb() {
	document.getElementById("fb_signin").submit();
}
