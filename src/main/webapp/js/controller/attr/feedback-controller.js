function FeedbackCtrl($rootScope, $scope, $http, $routeParams, pagingService, validationService) {
	$scope.shareemailreq = {};
	$scope.placeId = $routeParams.placeId;
	$scope.feedbacks = [];
	$scope.feedbackCont = [];
	$scope.error = '';
	$scope.currentUser = {};
	$scope.userStore = [];
	$scope.getMoreRq = pagingService.initGetMoreRq($scope.placeId);

	$scope.getFeedbacks = function() {
		var link = DOMAIN + '/data/feedback/getmore';
		pagingService.getMore(FEED_BACK_PER_PAGE, link, $scope.getMoreRq,
				$scope.feedbacks);
	};

	$scope.getFeedbackContent = function() {
		$http.get(DOMAIN + '/data/feedback/new').success(function(data) {
			$scope.feedbackCont = data;
		});
	};

	$scope.getShareEmailTemplate = function() {
		$http.get(DOMAIN + '/share/email/template').success(function(data) {
			$scope.shareemailreq = data;
			$scope.shareemailreq.emails = [];
		});
	};
	$scope.newFeedback = function() {
		if ($rootScope.userSession.user != undefined
				&& $rootScope.userSession.user.uid != 0) {
			if ($scope.feedbackCont.content != null
					&& $scope.feedbackCont.content.length > 0) {
				$http.post(DOMAIN + '/data/feedback/new/' + $scope.placeId,
						$scope.feedbackCont).success(function(data) {
					if (data.error == undefined) {
						$scope.feedbacks.splice(0, 0, data);
						$scope.feedbackCont = '';
					} else {
						$.pnotify({
							delay : 2000,
							title : $scope.langCommon.updateFail,
							type : 'error',
						});
					}
				});
			} else {
				$.pnotify({
					delay : 2000,
					title : $scope.langPlace.emptyfeedback,
					type : 'error',
				});
			}
		} else {
			$.pnotify({
				delay : 2000,
				title : $scope.langPlace.notlogin,
				type : 'error',
			});
		}
	};
	$scope.deleteFeedback = function(idx) {
		var realIdx = idx; // $scope.feedbacks.length-1-idx;`
		$http.get(
				DOMAIN + '/data/feedback/delete/'
						+ $scope.feedbacks[realIdx].id).success(function(data) {
			if (data.error == undefined) {
				$scope.feedbacks.splice(realIdx, 1);
				$scope.idx = $scope.feedbacks.length;
			}
		});
	};
	// *Initial
	$scope.getFeedbacks();
	$scope.getFeedbackContent();
	$scope.getShareEmailTemplate();
		
	$scope.addSharedEmail = function() {
		validationService.checkEmail($scope.newemail,'err229','');
		validationService.checkDuplicateEmail($scope.shareemailreq.emails, $scope.newemail, 'err230', '');
		$scope.shareemailreq.emails.push({
			email : $scope.newemail
		});	
	};

	$scope.deleteEmail = function(index) {
		for ( var i in $scope.shareemailreq.emails) {
			if ($scope.shareemailreq.emails[i].email == $scope.shareemailreq.emails[index].email) {
				$scope.shareemailreq.emails.splice(index, 1);
			}

		}
	};
	$scope.sendMailAll = function() {
		if ($rootScope.userSession.user != undefined
				&& $rootScope.userSession.user.uid != 0) {
			$http.post(DOMAIN + '/share/email/' + $scope.placeId,
					$scope.shareemailreq).success(function(data) {
				if (data.error == undefined) {
					$scope.shareemailreq.emails = [];
					$("#shareByEmail").css('display', 'none');
					$.pnotify({
						delay : 1500,
						title : $scope.langCommon.emailsent,
						type : 'success',
					});
				} else {
					$.pnotify({
						delay : 1500,
						title : $scope.langCommon.emailnotsent,
						type : 'error',
					});
				}
			});
		} else {
			$.pnotify({
				delay : 1500,
				title : $scope.langUser.notlogin,
				type : 'error',
			});
		}
	};

	$(function() {
		var hideDelay = 500;
		var currentID;
		var hideTimer = null;
		var container = $("#note");

		$('.personPopupTrigger')
				.live(
						'mouseover',
						function() {
							currentID = $(this).attr('rel');

							if (currentID <= 0)
								return;

							if (hideTimer)
								clearTimeout(hideTimer);

							var pos = $(this).offset();

							var h = $(this).height();
							container.css({
								left : (pos.left) + 'px',
								top : (pos.top + h) + 'px'
							});

							var idx = userIndexOf($scope.userStore, currentID);
							if (idx == -1) {
								$http
										.get(
												DOMAIN + '/data/user/get/'
														+ currentID)
										.success(
												function(data) {
													$scope.currentUser = data;
													$scope.userStore
															.push($scope.currentUser);
													if ($scope.currentUser.gender == 'male') {
														$scope.currentUser.gender = $scope.langUser.male;
													} else {
														$scope.currentUser.gender = $scope.langUser.female;
													}
												});
							} else {
								$scope.currentUser = $scope.userStore[idx];
								$http.get();
							}
							container.css('display', 'block');
						});

		$('.personPopupTrigger').live('mouseout', function() {
			if (hideTimer)
				clearTimeout(hideTimer);
			hideTimer = setTimeout(function() {
				container.css('display', 'none');
			}, hideDelay);
		});

		// Allow mouse over of details without hiding details
		$('#note').mouseover(function() {
			if (hideTimer)
				clearTimeout(hideTimer);
		});

		// Hide after mouseout
		$('#note').mouseout(function() {
			if (hideTimer)
				clearTimeout(hideTimer);
			hideTimer = setTimeout(function() {
				container.css('display', 'none');
			}, hideDelay);
		});

		$('#toogleEmailSharingDiv').on(
				'click',
				function() {
					if ($rootScope.userSession.user != undefined
							&& $rootScope.userSession.user.uid != 0) {
						if ($("#shareByEmail").css('display') == 'none') {
							$("#shareByEmail").css('display', 'block');
						} else {
							$("#shareByEmail").css('display', 'none');
						}

					} else {
						$.pnotify({
							delay : 1500,
							title : $scope.langUser.notlogin,
							type : 'error',
						});
					}
				});
	});

	function userIndexOf(users, uid) {
		for ( var i in users) {
			if (users[i].uid == uid)
				return i;
		}
		return -1;
	}
}
FeedbackCtrl.$inject = [ '$rootScope', '$scope', '$http', '$routeParams',
		'pagingService','validationService' ];