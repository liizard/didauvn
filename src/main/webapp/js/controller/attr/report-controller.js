function ReportSubmitCtrl($scope, $rootScope, $http,  errorHandlerService, validationService) {
	$scope.report = {};
	$scope.report.user = 0;
	
	$scope.reportSubmit = function(type) {
		validationService.checkLogin('err261', $scope.langUser.notlogin);
		$scope.popup();
		$scope.report.reportType = type;
		$scope.report.user = $rootScope.userSession.user.uid;
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
				});
	};
	
	$scope.popup = function() {
		$scope.report.dcrp = prompt($scope.langCommon.reason + ": ", "");
		if($scope.report.dcrp == null) {
			$scope.report.dcrp = "";
		}
	};
	
	$scope.reportPlace = function() {
		$scope.report.itemId = $scope.placeId;
		$scope.reportSubmit('PLACE');
	};
	
	$scope.reportImage = function(id) {
		$scope.report.itemId = id;
		$scope.reportSubmit('IMAGE');
	};
	
	$scope.reportVideo = function(id) {
		$scope.report.itemId = id;
		$scope.reportSubmit('VIDEO');
	};
}
ReportSubmitCtrl.$inject = ['$scope', '$rootScope', '$http', 'errorHandlerService', 'validationService'];