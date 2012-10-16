function ReportSubmitCtrl($scope, $rootScope, $http,  errorHandlerService, validationService) {
	$scope.report = {};
	$scope.report.user = 0;
	
	$scope.submit = function() {
		//$scope.report.user = $rootScope.userSession.user.uid;
		$scope.report.processed = false;
		$http.post(DOMAIN + '/admin/data/report/submit', $scope.report).success(
				function(data) {
					// Check error
					errorHandlerService.handle(data,
							$scope.langCommon.updateFail);
					
					$.pnotify({
						delay : 2000,
						title : $scope.langCommon.updateSuccess,
						type : 'success',
					});
					$scope.report.dcrp = "";
					dialog.dialog("close");
				});
	};
	
	$scope.reportPlace = function() {
		$scope.report.itemId = $scope.placeId;
		$scope.report.reportType = 'PLACE';
		$scope.popup();
	};
	
	$scope.reportImage = function(id) {
		$scope.report.itemId = id;
		$scope.report.reportType = 'IMAGE';
		$scope.popup();
	};
	
	$scope.reportVideo = function(id) {
		$scope.report.itemId = id;
		$scope.report.reportType = 'VIDEO';
		$scope.popup();
	};
	
	var dialog;
	
	$scope.popup = function() {
		validationService.checkLogin('err261', $scope.langUser.notlogin);
		if (dialog == null) {
			dialog = $("#popupReport").dialog({
				title : $scope.langCommon.reason,
				autoOpen : false,
				height : 100,
				width : 100
			});
		}
		dialog.dialog("open");
	};
}
ReportSubmitCtrl.$inject = ['$scope', '$rootScope', '$http', 'errorHandlerService', 'validationService'];