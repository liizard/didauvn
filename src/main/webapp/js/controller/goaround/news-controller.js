function GoAroundNewsCtrl($scope, $http) {

	$scope.cats = [];
	$scope.news = [];
	$scope.idx = [];
	$scope.more = [];
	$scope.lastId = [];
	$scope.lastCat = {};
	
	var timeoutId = [];

	$scope.getNews = function(catId) {
		var div = '#category' + catId;
		$http.get(
				DOMAIN + '/data/goaround/getnews/' + catId + '/'
						+ $scope.idx[catId]).success(function(data) {
			if (data.length == NEWS_PER_PAGE + 1) {
				data.splice(data.length - 1, 1);
				$scope.more[catId] = 1;
			} else {
				$scope.more[catId] = 0;
			}
			
			for(var i=0; i<data.length; i++) {
				$scope.news[catId].push(data[i]);
				$scope.lastId[catId] = data[i].id + "" + data[i].pageNum;
				
			}

			if ($scope.idx[catId] == 0) {			// INIT LAYOUT
				timeoutId[catId] = setTimeout(function() {
					if ($('#news'+$scope.lastId[catId]).index() > -1) {
						
						$('#news'+$scope.lastId[catId]).imagesLoaded(function() {
							$(div).masonry({
								itemSelector : '.news'
							});
						});
						clearTimeout(timeoutId[catId]);
					}
				}, 100);
			} else { 								// ADD TO LAYOUT
					timeoutId[catId] = setTimeout(function() {
							var lid = data[data.length -1].id + '' + data[data.length -1].pageNum;
							if ($('#news'+ lid).index() > -1) {
								$(div).imagesLoaded(function() {
									for(var i=0; i<data.length; i++) {
										var id = data[i].id + '' + data[i].pageNum;
										var $boxes = $('#news' + id);
										$boxes.css({
											opacity : 0
										});
										$boxes.animate({
											opacity : 1
										}, 1000);
										$(div).masonry('appended', $boxes);
									
									}
							});
							clearTimeout(timeoutId[catId]);
						}
					}, 0);
			}
			
			if (data.length > 0)
				$scope.idx[catId]++;
		});
	};

	$scope.initAll = function() {
		for ( var i = 0; i < $scope.cats.length; i++) {
			$scope.news[$scope.cats[i].id] = [];
			$scope.idx[$scope.cats[i].id] = 0;
			$scope.getNews($scope.cats[i].id);
		}
//		$scope.idx[1] = 0;
//		$scope.getNews(1);
	};

	$scope.getCategory = function() {
		$http.get(DOMAIN + '/data/businesstype/gets').success(function(data) {
			$scope.cats = data;
			$scope.initAll();
			
			var catTimeoutID = 0;
			catTimeoutID = setTimeout(function() {
				if($('#cat' + data[data.length-1].id).index() > -1) {
					$('#divCat').localScroll(800);
				clearTimeout(catTimeoutID);
				}
			} ,100);
		});
	};

	$scope.getCategory();

	$scope.binding = function() {
		$(document).bind('scroll', onScroll);
	};

	function onScroll(event) {
		// Check if we're within 100 pixels of the bottom edge of the broser
		// window.
		var closeToBottom = ($(window).scrollTop() + $(window).height() > $(
				document).height() - 100);
		if (closeToBottom) {
			// Get the first ten items from the grid, clone them, and add them
			// to
			// the bottom of the grid.
			var cat = $scope.cats[$scope.cats.length - 1].id;
			$scope.initNews(cat);
		}
	}
	;
}