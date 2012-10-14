function OperationHourCtrl($scope, $http, $routeParams, $location) {

	$scope.placeId = $routeParams.placeId;
	$scope.hours = [];
	$scope.times = [];
	// $scope.days = [];

	$scope.getOperationHours = function() {
		$http.get(DOMAIN + '/data/operationhour/get/' + $scope.placeId)
				.success(function(data) {
					$scope.hours = data;
				});
	};

	$scope.getTimes = function() {
		$http.get(DOMAIN + '/json/data/time.json').success(function(data) {
			$scope.times = data;
		});
	};

	$scope.getTimes();
	$scope.getOperationHours();
	$scope.getDays();

	// --------------- Update Operation Hour Controller------------------
	$scope.hour = [];

	$scope.saveOperationHour = function(id) {
		$http.post(DOMAIN + '/data/operationhour/update/' + $scope.placeId,
				$scope.hours).success(function(data) {
			$.pnotify({
				delay : 1000,
				title : $scope.langCommon.updateSuccess,
				type : 'success',
			});
		});
	};

	$scope.applyAll = function(id) {
		for ( var i in $scope.hours) {
			$scope.hours[i].openHour = $scope.hours[id].openHour;
			$scope.hours[i].closeHour = $scope.hours[id].closeHour;
			$scope.hours[i].active = $scope.hours[id].active;
		}
	};
}