function NewsCtrl($scope, $http, $routeParams, $location) {
	$scope.placeId = $routeParams.placeId;
	$scope.news = {};
	$scope.newsExist = false;

	$scope.getNews = function() {
		$http.post(DOMAIN + '/data/news/getbyplace/' + $scope.placeId).success(
				function(data) {
					$scope.news = data;
					if (data) {
						$scope.newsExist = true;
					} else {
						$scope.newsExist = false;
					}
				});
	};

	// *Initial
	$scope.getNews();
}

function NewsUpdateCtrl($scope, $http, $routeParams, $location,
		errorHandlerService, validationService) {
	$scope.placeId = $routeParams.placeId;
	$scope.news = {};
	$scope.images = [];
	$scope.update = true;
	$scope.upload = [];

	$scope.getNews = function() {
		$http.post(DOMAIN + '/data/news/getbyplace/' + $scope.placeId).success(
				function(data) {
					if (data == []) {
						$http.post(DOMAIN + '/data/news/new/0').success(
								function(data) {
									$scope.news = data;
									initnicEditor('newsCont', '');
								});
						$scope.update = false;
					} else {
						$scope.news = data;
						$scope.update = true;
						initnicEditor('newsCont', $scope.news.cont);
					}
					// if News didn't existed
				});
	};

	$scope.getEmptyNews = function() {
		if ($scope.news.id != undefined) {
			var date = $scope.news.date;
			$http.post(DOMAIN + '/data/news/new/' + $scope.news.id).success(
					function(data) {
						$scope.news = data;
						$scope.news.date = date;
					});
		}
	};

	$scope.saveNews = function() {
		$scope.news.cont = nicEditors.findEditor('newsCont').getContent();
		
		// validate data
		validationService.checkEmpty($scope.news.title, "err210", $scope.langCommon.updateFail);
		validationService.checkLarger($scope.news.title, NEWS_TITLE_LENGTH, "err211", $scope.langCommon.updateFail);
		validationService.checkEmpty($scope.news.desc, "err212", $scope.langCommon.updateFail);
		validationService.checkLarger($scope.news.desc, NEWS_DESCRIPTION_LENGTH, "err213", $scope.langCommon.updateFail);
		validationService.checkLarger($scope.news.cont, NEWS_CONTENT_LENGTH, "err214", $scope.langCommon.updateFail);
		
		var link = '';
		if ($scope.update) {
			link = '/data/news/update/' + $scope.news.id; // Update News
		} else {
			link = '/data/news/add/' + $scope.placeId; // Insert new News
		}
		$http.post(DOMAIN + link, $scope.news).success(function(data) {
			$('#fileupload').fileupload('option', {
				url : DOMAIN + '/data/news/upimg/' + data.id
			});
			for ( var i = 0; i < $scope.upload.length; i++) {
				$scope.upload[i].submit();
			}

			// Check error
			errorHandlerService.handle(data, $scope.langCommon.updateFail);
			
			if(!$scope.update) $scope.update = true;
			notify('success', $scope.langCommon.updateSuccess);
		});
	};

	$scope.checkInput = function() {
		if ($scope.news.title.length > NEWS_TITLE_LENGTH)
			return false;
		return true;
	};

	function notify(type, cont) {
		$.pnotify({
			delay : 2000,
			title : cont,
			type : type
		});
	}
	;

	$('#fileupload').fileupload();
	$('#fileupload').fileupload('option', 'redirect',
			window.location.href.replace(/\/[^\/]*$/, '/cors/result.html?%s'));
	$('#fileupload').fileupload('option', {
		maxFileSize : 5000000,
		acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
		done : function(e, data) {
			// $scope.notify($scope.langCommon.updateSuccess);
			var imageId = ($.isArray(data.result) && data.result[0]);
			$scope.news.image = imageId;
			$http.get();
		},

		fail : function(e, data) {
			// $scope.notify($scope.langCommon.uploadImageFail);
		}
	});

	$('#fileupload').bind('fileuploadadd', function(e, data) {
		$scope.images = [];
		$scope.upload = [];
		$scope.images.push(data.files[0].name);
		$http.get();
		$scope.upload.push(data);
	});

	$('#fileupload').bind('fileuploadcompleted', function(e, data) {
		alert(124);

	});

	$scope.goPlace = function() {
		$location.path('place/' + $scope.placeId);
	};

	// -------------Initial--------------------------------------
	$scope.getNews();

}
NewsUpdateCtrl.$inject = [ '$scope', '$http', '$routeParams', '$location',
		'errorHandlerService', 'validationService' ];

// -------------- NEWS CONSTANT -----------
var NEWS_TITLE_LENGTH = 100;
var NEWS_DESCRIPTION_LENGTH = 500;
var NEWS_CONTENT_LENGTH = 2000;