function SiteFeedBacksCtrl($scope, $rootScope, $http) {
	$scope.getSiteFeedbacks = function(){
		$http.get(DOMAIN + '/admin/data/sitefeedback/getall')
		.success(function(data) {
			if (data.error == undefined) {
				$scope.siteFeedbacks = data;
			}		
		});
	};
	
	$scope.getSiteFeedbacks();
}
SiteFeedBacksCtrl.$inject = [ '$scope', '$rootScope', '$http' ];