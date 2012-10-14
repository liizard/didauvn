function WowCtrl($rootScope, $scope, $http, $routeParams) {
	$scope.placeId = $routeParams.placeId;

	// determine whether user wow or not
	$scope.checkWow = function() {
		if ($rootScope.userSession.user != undefined) {
			if ($rootScope.userSession.user.uid != 0) {
				for ( var i in $rootScope.userSession.wowPlaces) {
					if ($rootScope.userSession.wowPlaces[i] == $scope.placeId) {
						$scope.Wowed();
						return;
					}
				}
			}
		}
	};

	// wow function
	$scope.WowPlace = function() {

		if ($rootScope.userSession.user == undefined) {
			$.pnotify({
				delay : 2000,
				text : $scope.langUser.notlogin,
				type : 'error',
			});
		} else {
			if ($rootScope.userSession.user.uid == 0) {
				$.pnotify({
					delay : 2000,				
					text : $scope.langUser.notlogin,
					type : 'error',
				});
			} else {
				$http.post(DOMAIN + '/data/wow/new/' + $scope.placeId).success(
						function(data) {
							if (data.error == undefined) {
								$rootScope.userSession.wowPlaces
										.push($scope.placeId);
								$scope.Wowed();
							}
						});
			}
		}
	};

// un wow function
	$scope.HaizPlace = function() {
		if ($rootScope.userSession.user == undefined) {
			$.pnotify({
				delay : 2000,
				text : $scope.langUser.notlogin,
				type : 'error',
			});
		} else {
			if ($rootScope.userSession.user.uid == 0) {
				$.pnotify({
					delay : 2000,
					text : $scope.langUser.notlogin,
					type : 'error',
				});
			} else {
				$http
						.post(DOMAIN + '/data/wow/delete/' + $scope.placeId)
						.success(
								function(data) {
									if (data.error == undefined) {
										for ( var i in $rootScope.userSession.wowPlaces) {
											if ($rootScope.userSession.wowPlaces[i] == $scope.placeId) {
												$rootScope.userSession.wowPlaces
														.splice(i, 1);
												break;
											}
										}
										$("#wowbt").on("click", function() {
											$scope.WowPlace();
										});
										$("#wowbt").off('mouseover');
										$("#wowbt").off('mouseout');
										$("#wowbt").attr("src",
												DOMAIN + "/img/res/icon/wow.png");
										$("#haiz").css('display', 'none');
									}
								});
					}	
		}
	};

	$scope.Wowed = function() {
		var timer = null;
		var delay = 500;

		$("#wowbt").attr("src", DOMAIN + "/img/res/icon/wow1.png");
		$("#wowbt").off("click");

		var pos = $("#wowbt").offset();
		var h = $("#wowbt").height();
		$("#haiz").css("left", pos.left);
		$("#haiz").css("top", pos.top + h + 10);
		$("#wowbt").mouseover(function() {
			if (timer)
				clearTimeout(timer);
			$("#haiz").css("display", "block");
		});
		$("#wowbt").mouseout(function() {
			if (timer)
				clearTimeout(timer);
			timer = setTimeout(function() {
				$("#haiz").css('display', 'none');
			}, delay);

		});
		$('#haiz').mouseover(function() {
			if (timer)
				clearTimeout(timer);
		});
		$('#haiz').mouseout(function() {
			if (timer)
				clearTimeout(timer);
			timer = setTimeout(function() {
				$("#haiz").css('display', 'none');
			}, delay);
		});
	};

	// Initial
	$scope.checkWow();
};
