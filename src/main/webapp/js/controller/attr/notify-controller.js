function NotifyCtrl($rootScope,$scope, $http, $routeParams) {
	$scope.messages = [];
	$scope.currentmessage = 0;
	$scope.currentUser = {};
	$scope.userStore=[];
	
	$scope.getMessages = function() {
		$http.post(DOMAIN + '/data/message/gets').success(function(data) {
			var len = data.length;
			for(var i=0;i<len;i++){
				data[i].type = $rootScope.langMessage[data[i].type];
			}
			for(i in data){
				$scope.messages.push(data[i]);
			}
			if(len == MESSAGE_PER_PAGE){
				$scope.currentmessage = $scope.messages[len-1].messageId;
			}
			else{
				$scope.currentmessage = 0;
			}			
		});
	};
	$scope.getMoreMessages = function() {
		$http.post(DOMAIN + '/data/message/gets/'+$scope.currentmessage).success(function(data) {
			var len = data.length;
			for(var i=0;i<len;i++){
				$.each($rootScope.langMessage, function(key, value) {
					if (key == data[i].type) {
						data[i].type = value;
					}
				});
			}
			for(i in data){
				$scope.messages.push(data[i]);
			}
			if(len == MESSAGE_PER_PAGE){
				$scope.currentmessage = $scope.messages[$scope.messages.length-1].messageId;
			}
			else{
				$scope.currentmessage = 0;
			}					
		});
	};
	$scope.getMessages();
	$(function() {
		var hideDelay = 500;
		var currentID;
		var hideTimer = null;
		var container = $("#note");

		$('.personPopupTrigger').live(
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
						$http.get(DOMAIN + '/data/user/get/' + currentID)
								.success(function(data) {
									$scope.currentUser = data;
									$scope.userStore.push($scope.currentUser);
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
	});
	function userIndexOf(users, uid) {
		for ( var i in users) {
			if (users[i].uid == uid)
				return i;
		}
		return -1;
	}
}
