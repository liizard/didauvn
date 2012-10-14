function ReportCtrl($scope, $rootScope, $http, pagingService) {
	$scope.reports = [];
	$scope.reportRq = {};
	$scope.pageRq = pagingService.initPageRq(0);
	$scope.types = [];
	$scope.flag = false;

	$scope.getType = function() {
		$http.get(DOMAIN + '/admin/data/report/reporttype', $scope.reportRq)
				.success(function(data) {
					$scope.types = data;
				});
	};
	
	$scope.submitReportRequest = function(page) {
		$scope.selectedPage = page;
		page = page - 1;
		$scope.reports = [];
		$scope.reportRq.page = page;

		$http.post(DOMAIN + '/admin/data/report/getbyfilter', $scope.reportRq).success(
		function(data) {
			if (data.error == undefined) {
				$scope.reports = data.data;
				$scope.pageList = [];
				for ( var i = 1; i <= data.pageCount; i++) {
					$scope.pageList.push(i);
				}
				$scope.flag = true;
			}
		});
	};

	$scope.getReportRequestTemplate = function() {
		$http.get(DOMAIN + '/admin/data/report/template').success(
				function(data) {
					if (data.error == undefined) {
						$scope.reportRq = data;
					}
				});
	};

	$scope.processItem = function(idx) {
		$http
				.post(
						DOMAIN + '/admin/data/report/process/'
								+ $scope.reports[idx].id).success(
						function(data) {
							if (data.error == undefined) {
								$scope.reports[idx].processed = true;
							}
						});
	};

	$scope.deleteItem = function(idx) {
		$http.post(
				DOMAIN + '/admin/data/report/delete/' + $scope.reports[idx].id)
				.success(function(data) {
					if (data.error == undefined) {
						$scope.reports.splice(idx, 1);
					}
				});
	};

	
	$scope.getReports = function(page) {
		if($scope.flag == true) {
			$scope.submitReportRequest(page);
			return;
		}
		// Set selected page when change page
		$scope.selectedPage = page;

		page = page - 1;
		$scope.pageRq.page = page;
		var link = DOMAIN + '/admin/data/report/getbypage';
		$scope.reports = [];
		pagingService.getByPage(IMAGE_PER_PAGE, link, $scope.pageRq,
				$scope.reports);
	};
	
	$scope.getPageNum = function() {
		$http.post(DOMAIN + '/admin/data/report/getpagecount')
				.success(function(data) {
					$scope.pageList = [];
					for ( var i = 1; i <= data; i++) {
						$scope.pageList.push(i);
					}
				});
	};
	
	$scope.clear = function() {
		$scope.flag = false;
		$scope.getPageNum();
		$scope.getReports(1);
	};

	// initialization
	$scope.getType();
	$scope.getReportRequestTemplate();
	$scope.getPageNum();
	$scope.getReports(1);

	$(function() {
		$.datepicker.setDefaults($.datepicker.regional[$rootScope.lang]);
		$(".datepk").datepicker({
			onSelect : function(dateText) {
				var a = $(this).attr('id');
				if (a == 'from') {
					$scope.reportRq.from = dateText;
				} else {
					$scope.reportRq.to = dateText;
				}
				$scope.$apply();
			}
		});
	});
}
ReportCtrl.$inject = [ '$scope', '$rootScope', '$http', 'pagingService' ];