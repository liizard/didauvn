function GoAroundOfferWeekCtrl($scope, $http) {
	
	$scope.idxCat1 = 1;
	$scope.idxCat2 = 1;
	$scope.idxCat3 = 1;
	$scope.idxCat4 = 1;
	$scope.idxCat5 = 1;
	
	$scope.createOfferWeekDiv = function(img, title, content) {
		var div = $('<div>')
        .addClass( 'offer' )
        .append($('<table>')
        		.attr('border','0')
    	        .attr('cellpadding','0')
    	        .attr('cellspacing','0')
    	        .append($('<tr>')
    	        		.append($('<td>')
    	        				.addClass('offerPhoto')
    	        				.append($('<div>')
    	        						.addClass('offerTag promotion')
    	        						.append($('<img>')
    	        								.attr('src', DOMAIN + '/img/res/icon/promotion.png')
    	        						)
    	        						.append($('<div>')
    	        								.addClass('offerDay')
    	        								.append('<span>20</span> ngày')
    	        						)
    	        				)
    	        				.append($('<img>')
    	        						.attr('src', DOMAIN + '/img/news/' + img + '.jpg')
    	        				)
    	        		)
    	        )
    	        
    	        .append($('<tr>')
    	        		.append($('<td>')
    	        				.addClass('placeName')
    	        				.append($('<p>')
    	        						.append(title)
    	        				)
    	        		)
    	        )
    	        
    	        .append($('<tr>')
    	        		.append($('<td>')
    	        				.addClass( 'offerContent' )
    	    	    			.append(content)
    	        		)
    	        )
        );
	        
	    return div;
	};
	
	//Initial Grid Layout
	$scope.initGridLayout = function(divId, data) {
		var list = new Array();
		var str = "";
		$scope.length = data.length;
		for ( var i = 0; i < $scope.length; i++) {
			list.push($scope.createOfferWeekDiv(data[i].imgId, data[i].title, data[i].content));
			str += list[i].prop('outerHTML');
		};
		
		var $boxes = $(str);
		$boxes.css({
			opacity : 0
		});
		$boxes.animate({
			opacity : 1
		}, 500);
		
		$(divId).append($boxes);
		
		$(divId).imagesLoaded(function(){
			$(divId).masonry({
				itemSelector : '.offer'
			});
		});
	};
	
	// INIT Offer Of The Week
	$scope.initOfferWeek = function(divId) {
		$http.post(
			DOMAIN + '/data/goaround/getofferweeks/'
				+ 1).success(function(data) {
					$scope.initGridLayout(divId, data);
				});
	};
	
	$scope.initOfferWeek('#offerContainer');
}