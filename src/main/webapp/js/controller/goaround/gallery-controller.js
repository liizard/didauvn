function GoAroundGalleryCtrl($scope, $http) {
	
	$scope.NPGUTTERWITDH = 15;
	
	$scope.newViews = [];
	$scope.triggerEvent = false;
	$scope.idxNewPlace = 1;
	
	
	$scope.idxCat1 = 1;
	$scope.idxCat5 = 1;
	
	//Create Layout of Grid Object
	
	$scope.createNewDiv = function(placeId, title, content, img) {
		var div = $('<div>')
        .addClass( 'news' )
        .append($('<table>')
        		.attr('border','0')
    	        .attr('cellpadding','0')
    	        .attr('cellspacing','0')
    	        .append($('<tr>')
    	        		.addClass( 'avatarHolder' )
    	        		.append($('<td>')
    	        				.append($('<img>')
        						.addClass( 'placeAvatar' )
    	        				.attr('src', DOMAIN + '/img/place/' + placeId + '.jpg')
    	        				)
    	        		)
    	        		.append($('<td>')
    	        				.addClass( 'placeName' )
    	    	    			.append(title)
    	        		)
    	        )
    	        
    	        .append($('<tr>')
    	        		.append($('<td>')
    	        				.addClass( 'newsContent' )
    	        				.attr('colspan', '2')
    	    	    			.append(content)
    	        		)
    	        )
    	        
    	        .append($('<tr>')
    	        		.append($('<td>')
    	        				.attr('colspan', '2')
    	        				.addClass( 'newsContent' )
    	    	    			.append($('<img>')
    	    	    					.attr('src', DOMAIN + '/img/news/' + placeId + '.jpg')
    	    	    					.attr('width', '230')
    	        				)
    	        		)
    	        )
    	        );
	        
	    return div;
	};
	
	$scope.createNewPlaceMainDiv = function(place, size, sizeheight) {
		var div = $('<div>')
		.css('width', size + 'px')
		.css('height', sizeheight*2 + $scope.NPGUTTERWITDH + 6 + 'px')
        .addClass( 'newplaces' )
        .append($('<img>')
        		
				.attr('src', DOMAIN + '/img/newplaces/' + place.placeView.id + '.jpg')
				.attr('height', '100%')
    	        )
    	 .append($('<div>')
    			 .addClass( 'npdesc' )
    			 .append($('<div>')
    					 .addClass( 'npcontent' )
    					 .html(
    							 place.placeView.name + ' | ' +  place.placeView.tagName + '<br/>'
    							 + place.placeView.address +' ' + place.placeView.streetName  + ', ' + place.placeView.wardName + ', ' + 
    								place.placeView.districtName + ', ' + place.placeView.cityName
    							 )
    					 
    			 	)
				)
    	        
    	        ;
	        
	    return div;
	};
	
	$scope.createNewPlaceSubDiv = function(imgId, size) {
		var div = $('<div>')
		.css('width', size + 'px')
		.css('height', size + 'px')
        .addClass( 'newplaces' )
        .append($('<img>')
				.attr('src', DOMAIN + '/img/gallery/thumb/' + imgId + '.jpg')
    	        );
	        
	    return div;
	};
	
	
	
	
	//Create new jQuery Object to put in Grid
	$scope.createLayoutObject = function(type, divId, data) {
		var $boxes = $('<div>');
		$scope.length = data.length;
		if(type=='news') {
			for ( var i = 0; i < $scope.length; i++) {
				$boxes.append($scope.createNewDiv(data[i].placeId, data[i].title, data[i].content, data[i].img[0]));
			};
		}
		
		if(type=='newplaces') {
			
			var size = $('#categoryNewPlace').width();
			var ran = 1 + Math.floor(Math.random() * 4);
			//case 1: big img left
			if (ran == 1) {
				$boxes.append($scope.createNewPlaceMainDiv(data, size/2 - $scope.NPGUTTERWITDH, size/6 - $scope.NPGUTTERWITDH));
				for ( var i = 0; i < data.imgs.length ; i++) {
					$boxes.append($scope.createNewPlaceSubDiv(data.imgs[i], size/6 - $scope.NPGUTTERWITDH));
				};
			} else if (ran==2) { //case 2: big img middle
				for ( var i = 0; i < data.imgs.length -5 ; i++) {
					$boxes.append($scope.createNewPlaceSubDiv(data.imgs[i], size/6 - $scope.NPGUTTERWITDH));
				};
				
				$boxes.append($scope.createNewPlaceMainDiv(data, size/2 - $scope.NPGUTTERWITDH, size/6 - $scope.NPGUTTERWITDH));
				
				for ( var i = data.imgs.length -5; i < data.imgs.length ; i++) {
					$boxes.append($scope.createNewPlaceSubDiv(data.imgs[i], size/6 - $scope.NPGUTTERWITDH));
				};
			} else if (ran==3) { //case 3: big img middle
				for ( var i = 0; i < data.imgs.length -4 ; i++) {
					$boxes.append($scope.createNewPlaceSubDiv(data.imgs[i], size/6 - $scope.NPGUTTERWITDH));
				};
				
				$boxes.append($scope.createNewPlaceMainDiv(data, size/2 - $scope.NPGUTTERWITDH, size/6 - $scope.NPGUTTERWITDH));
				
				for ( var i = data.imgs.length -4; i < data.imgs.length ; i++) {
					$boxes.append($scope.createNewPlaceSubDiv(data.imgs[i], size/6 - $scope.NPGUTTERWITDH));
				};
			} else { //case 4: big img right
				for ( var i = 0; i < data.imgs.length -3 ; i++) {
					$boxes.append($scope.createNewPlaceSubDiv(data.imgs[i], size/6 - $scope.NPGUTTERWITDH));
				};
				$boxes.append($scope.createNewPlaceMainDiv(data, size/2 - $scope.NPGUTTERWITDH, size/6 - $scope.NPGUTTERWITDH));
				for ( var i = data.imgs.length -3; i < data.imgs.length ; i++) {
					$boxes.append($scope.createNewPlaceSubDiv(data.imgs[i], size/6 - $scope.NPGUTTERWITDH));
				};
			}
			$boxes.attr('id','newPlace' + data.placeView.id + $scope.idxNewPlace);
			
		}
		return $boxes;
	};
	
	//End of Create Layout of Grid Object
	
	//Initial Grid Layout
	$scope.initGridLayout = function(type, divId, data) {
		var $boxes = $scope.createLayoutObject (type, divId, data);
		$boxes.css({
			opacity : 0
		});
		$boxes.animate({
			opacity : 1
		}, 500);
		
		$(divId).append($boxes);
		
		if (type == 'news') {
			$(divId).imagesLoaded(function(){
				$(divId).masonry({
					itemSelector : '.' + type
				});
			});
		}
		
		if (type == 'newplaces') {
			$('#newPlace' + data.placeView.id + $scope.idxNewPlace).imagesLoaded(function(){
				$('#newPlace' + data.placeView.id + $scope.idxNewPlace).masonry({
					itemSelector : '.' + type,
					isFitWidth: true,
					columnWidth: function( containerWidth ) {
					    return containerWidth / 6;
					  }
				});
			});
		}
		
	};
	
	//Add more Item Grid Layout
	$scope.addToGridLayout = function(type, divId, data) {
		
		var $boxes = $scope.createLayoutObject (type, divId, data);
		
		$boxes.css({
			opacity : 0
		});
		$boxes.animate({
			opacity : 1
		}, 1000);
		
		if (type == 'news') {
			$(divId).append($boxes).masonry('appended', $boxes, true);
		}
		if (type == 'newplaces') {
			$(divId).html($boxes);
			$('#newPlace' + data.placeView.id + $scope.idxNewPlace).imagesLoaded(function(){
				$('#newPlace' + data.placeView.id + $scope.idxNewPlace).masonry({
					itemSelector : '.' + type,
					columnWidth: function( containerWidth ) {
					    return containerWidth / 6;
					  }
				});
			});
		}

	};
	
	
	//Button get more processing
	$scope.getmore = function(cat) {
		if(cat == 'cat1') {
			$scope.getMoreNews("#category1", ++$scope.idxCat1, cat);
		}
		if(cat == 'cat5') {
			$scope.getMoreNews("#category5",  ++$scope.idxCat5, cat);
		}
	};
	
	$scope.getprevious = function() {
			$scope.getPageNewPlace("#categoryNewPlace", --$scope.idxNewPlace);

	};
	
	$scope.getnext = function() {
		$scope.getPageNewPlace("#categoryNewPlace", ++$scope.idxNewPlace);

};
	
	
	//Receive data from server
	
	//News
	$scope.getMoreNews = function(divId, idx, cat) {
		$http.post(
			DOMAIN + '/data/goaround/getnews/'
				+ idx).success(function(data) {
					$scope.addToGridLayout('news', divId, data);
				});
	};
	
	//New Places
	$scope.initNewPlace = function(divId) {
		$http.post(
			DOMAIN + '/data/goaround/getnewplaces/'
				+ 1).success(function(data) {
					for ( var i = 0; i < data.length; i++)
						$scope.initGridLayout('newplaces', divId, data[i]);
				});
	};
	
	$scope.initNews = function(divId, cat) {
		$http.post(
			DOMAIN + '/data/goaround/getnews/'
				+ 1).success(function(data) {
					$scope.initGridLayout('news', divId, data);
				});
	};
	
	$scope.getPageNewPlace = function(divId, idx) {
		$http.post(
			DOMAIN + '/data/goaround/getnewplaces/'
				+ idx).success(function(data) {
					for ( var i = 0; i < data.length; i++) {
						$scope.addToGridLayout('newplaces', divId, data[i]);
					}
				});
	};
	
	//End of receive data from server
	
	//Initial Go Around
	$scope.initNewPlace("#categoryNewPlace");
	var size = $('#categoryNewPlace').width();
	$('#categoryNewPlace').css('height', (size/6 - $scope.NPGUTTERWITDH)*2 + $scope.NPGUTTERWITDH + 6 + 20 + 'px');
	
	
	$("#categoryGallery").imagesLoaded(function(){
		$("#categoryGallery").masonry({
			itemSelector : '.box',
			isFitWidth: true
		});
	});
	
	//Infinite scroll in the end of page
	$(function() {
		
		$(document).bind('scroll', onScroll);
		
		$('#divCat').localScroll(800);
	});


	function onScroll(event) {
		// Check if we're within 100 pixels of the bottom edge of the broser
		// window.
		var closeToBottom = ($(window).scrollTop() + $(window).height() > $(
				document).height() - 100);
		if (closeToBottom) {
			$("#loading").css("display", "block");
			// Get the first then items from the grid, clone them, and add them
			// to
			// the bottom of the grid.
			var items = $('#categoryGallery .box');
			var firstTen = items.slice(0, 10);
			var $boxes = $(firstTen.clone());
			$boxes.css({
				opacity : 0
			});
			$boxes.animate({
				opacity : 1
			}, 1000);
			$('#categoryGallery').append($boxes).masonry('appended', $boxes, true);
			$("#loading").css("display", "none");
		}
	}
	;

}