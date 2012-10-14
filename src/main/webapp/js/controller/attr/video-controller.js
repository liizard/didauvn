function VideoCtrl($scope, $http, $routeParams, $location, pagingService) {

	$scope.placeId = $routeParams.placeId;
	$scope.videos = [];
	$scope.idx = 0;
	$scope.getMoreRq = pagingService.initGetMoreRq($scope.placeId);

	$scope.getVideos = function() {
		var link = DOMAIN + '/data/video/getmore';
		pagingService.getMore(VIDEO_PER_PAGE, link, $scope.getMoreRq,
				$scope.videos);
	};

	$scope.getVideos();
}
VideoCtrl.$inject = [ '$scope', '$http', '$routeParams', '$location',
		'pagingService' ];

function VideoUpdateCtrl($scope, $http, $routeParams, $location, pagingService,
		errorHandlerService, validationService) {

	$scope.placeId = $routeParams.placeId;
	$scope.videos = [];
	$scope.video = {};
	$scope.pageList = [];
	$scope.selectedPage = 1;
	$scope.pageRq = pagingService.initPageRq($scope.placeId);

	$scope.getVideoPageNum = function() {
		$http.get(DOMAIN + '/data/video/getpage/' + $scope.placeId).success(
				function(data) {
					$scope.pageList = [];
					for ( var i = 1; i <= data; i++) {
						$scope.pageList.push(i);
					}
				});
	};

	$scope.getVideos = function(idx) {
		// Set selected page when change page
		$scope.selectedPage = idx;

		idx = idx - 1;
		$scope.pageRq.page = idx;
		var link = DOMAIN + '/data/video/getbypage';
		$scope.videos = [];
		pagingService.getByPage(VIDEO_PER_PAGE, link, $scope.pageRq,
				$scope.videos);
	};

	$scope.getVideo = function() {
		$http.get(DOMAIN + '/data/video/new').success(function(data) {
			$scope.video = data;
		});
	};

	$scope.addVideo = function() {
		// Validate data
		validationService.checkLarger(v.caption, VIDEO_CAPTION_LENGTH, 'err233', $scope.langCommon.updateFail);
		
		$http.post(DOMAIN + '/data/video/add/' + $scope.placeId, $scope.video)
				.success(function(data) {
					errorHandlerService.handle(data, $scope.langCommon.updateFail); // check error
					$scope.videos.splice(0, 0, data);
					$scope.videos = $scope.videos.slice(0, VIDEO_PER_PAGE);
					$scope.getVideoPageNum(); // need????????????????????/
					$scope.getVideos(1);
				});
	};

	$scope.updateVideo = function(v) {
		// Validate data
		validationService.checkLarger(v.caption, VIDEO_CAPTION_LENGTH, 'err233', $scope.langCommon.updateFail);
		
		$http.post(DOMAIN + '/data/video/update', v).success(function(data) {
			errorHandlerService.handle(data, $scope.langCommon.updateFail);	// check error
			$.pnotify({
				delay : 1000,
				title : $scope.langCommon.updateSuccess,
				type : 'success',
			});
		});
	};

	$scope.delVideo = function(v) {
		$http.post(DOMAIN + '/data/video/del/' + v.id).success(function(data) {
			deleteById($scope.videos, v.id);
			$scope.getVideoPageNum(); // need????????????????????/
		});
	};

	$scope.goPlace = function() {
		$location.path('view/' + $scope.placeId);
	};

	$scope.getVideoPageNum();
	$scope.getVideos(1);
	$scope.getVideo();
}
VideoUpdateCtrl.$inject = [ '$scope', '$http', '$routeParams', '$location',
		'pagingService', 'errorHandlerService', 'validationService' ];

function loadVideo(video) {
	player = new YT.Player('ytplayer' + video.id, {
		height : '390',
		width : '640',
		videoId : video.href
	});
}

// -------------- VIDEO CONSTANT --------------
var VIDEO_CAPTION_LENGTH = 200;