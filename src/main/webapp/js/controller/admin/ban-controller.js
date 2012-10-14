function BanAdminCtrl($rootScope, $scope, $http, pagingService,
		errorHandlerService, validationService) {
	$scope.banViews = [];
	$scope.banView = {};
	$scope.selectedPage = 1;

	var BAN_PER_PAGE = 10;

	var addTitle = 'Insert Ban User';
	var editTitle = 'Edit Ban User';

	$scope.title = addTitle;

	$scope.getBanPageNum = function() {
		$http.post(DOMAIN + '/admin/data/ban/getpagecount').success(
				function(data) {
					$scope.pageList = [];
					for ( var i = 1; i <= data; i++) {
						$scope.pageList.push(i);
					}
				});
	};

	$scope.getBanPageNum();

	$scope.getBans = function() {
		$http.post(DOMAIN + '/admin/data/ban/gets').success(function(data) {
			$scope.banViews = data;
		});
	};

	$scope.getBans = function(idx) {
		// Set selected page when change page
		$scope.selectedPage = idx;

		idx = idx - 1;
		var link = DOMAIN + '/admin/data/ban/getbypage';
		$scope.banViews = [];
		pagingService.getByPageNo(BAN_PER_PAGE, link, idx, $scope.banViews);
	};

	$scope.getNewBan = function() {
		$http.post(DOMAIN + '/admin/data/ban/get/new').success(
				function(data) {
					$scope.banView = data;
					$scope.banView.banDate = $.datepicker.formatDate(
							'yy-mm-dd', new Date());
				});
	};

	$scope.delBan = function(banView) {
		$http.post(DOMAIN + '/admin/data/ban/del/' + banView.id).success(
				function(data) {
					errorHandlerService.handle(data,
							$scope.langAdmin.removeBanFail);

					$.pnotify({
						delay : 2000,
						title : $scope.langAdmin.removeBanSuccess,
						type : 'success'
					});

					deleteById($scope.banViews, banView.id);
				});
	};

	$scope.editBan = function(banView) {
		$scope.banView = banView;
		$scope.title = editTitle;
	};

	$scope.addBan = function() {
		$scope.getNewBan();
		$scope.title = addTitle;
	};

	$scope.saveBan = function() {
		validationService.checkGreater($scope.banView.noDay, 0, 'err281',
				$scope.langAdmin.saveBanFail);
		validationService.checkDate($scope.banView.banDate, 'err282',
				$scope.langAdmin.saveBanFail);
		if ($scope.banView.id == 0) {
			$http.post(DOMAIN + '/admin/data/ban/insert', $scope.banView)
					.success(
							function(data) {
								errorHandlerService.handle(data,
										$scope.langAdmin.saveBanFail);

								$.pnotify({
									delay : 2000,
									title : $scope.langAdmin.saveBanSuccess,
									type : 'success'
								});
								$scope.banViews.push(data);

							});
		} else {
			$http.post(DOMAIN + '/admin/data/ban/update', $scope.banView)
					.success(
							function(data) {
								errorHandlerService.handle(data,
										$scope.langAdmin.saveBanFail);

								$.pnotify({
									delay : 2000,
									title : $scope.langAdmin.saveBanSuccess,
									type : 'success'
								});
							});
		}
	};

	$(function() {
		$("#datepicker").datepicker({
			onSelect : function(dateText) {
				$scope.banView.banDate = dateText;
				$scope.$apply();
			},
			dateFormat : 'yy-mm-dd',
			gotoCurrent : true

		});
	});

	$scope.getBans(1);
	$scope.getNewBan();
}

BanAdminCtrl.$inject = [ '$rootScope', '$scope', '$http', 'pagingService',
		'errorHandlerService', 'validationService' ];