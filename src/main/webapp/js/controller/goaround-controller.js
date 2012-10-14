function GoAroundCtrl($scope, $http) {

	$scope.news = [];
	$scope.newsUI = [];
	$scope.idxCat1 = 1;

	$scope.getmore = function(cat) {
		if (cat == 'cat1') {
			$scope.getMoreNews("#category1", ++$scope.idxCat1, cat);
		}
	};

	$scope.getMoreNews = function(divId, idx, cat) {
		$http.post(DOMAIN + '/data/goaround/getnews/' + idx).success(
				function(data) {
					var lastId;
					$scope.newsUI = [];
					for ( var i = 0; i < data.length; i++) {
						$scope.news.push(data[i]);
						lastId = data[i].newsId + "" + data[i].pageNum;
						$scope.newsUI.push(lastId);
					}
					$scope.addMore('#test' + lastId);
				});
	};
$scope.lastId=0;
	$scope.initNews = function(divId, cat) {
		$http.post(DOMAIN + '/data/goaround/getnews/' + 1).success(
				function(data) {
					// $scope.initGridLayout(divId, data);
					var lastId;
					$scope.news = [];
					for ( var i = 0; i < data.length; i++) {
						$scope.news.push(data[i]);
						$scope.lastId= data[i].newsId + "" + data[i].pageNum;
					}
					//$scope.getNew('#test' + lastId);
					alert($scope.lastId+'-'+$('#test' + $scope.lastId).index());
		//			setInterval($scope.getNew, 2000);
				});
	};

	$scope.initNews("#category1", 'cat1');

	$scope.getNew = function() {
		if ($('#test'+$scope.lastId).length) {
			$('#category1').imagesLoaded(function() {
				$('#category1').masonry({
					itemSelector : '.news'
				});
			});
			clearInterval($scope.getNew);
		}
		alert($('#test'+$scope.lastId).index());
	};

	$scope.addMore = function(divId) {
		if ($(divId).index() > -1) {
			for ( var i = 0; i < $scope.newsUI.length; i++) {
				var $boxes = $('#test' + $scope.newsUI[i]);
				$boxes.css({
					opacity : 0
				});
				$boxes.animate({
					opacity : 1
				}, 1000);
				$('#category1').masonry('appended', $boxes);
			}
		}
		;
	};
}