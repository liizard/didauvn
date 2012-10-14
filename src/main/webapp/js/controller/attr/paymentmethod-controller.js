function PaymentMethodCtrl($scope, $http, $routeParams) {
	$scope.placeId = $routeParams.placeId;
	$scope.getPaymentMethods = function() {
		$http.get(DOMAIN + '/data/paymentmethod/get/' + $scope.placeId)
				.success(function(data) {
					$scope.paymentMethods = data;
				});
	};

	$scope.updatePaymentMethods = function() {
		$http.post(DOMAIN + '/data/paymentmethod/update/' + $scope.placeId,
				$scope.paymentMethods).success(function() {
			$.pnotify({
				delay : 1000,
				title : $scope.langCommon.updateSuccess,
				type : 'success',
			});
		});
	};

	$scope.getPaymentMethods();
};