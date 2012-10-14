function ImageCtrl($scope, $http, $routeParams, $location, pagingService) {
	$scope.placeId = $routeParams.placeId;
	$scope.imageViews = [];
	$scope.imageShow = {};
	$scope.imageShowIdx = 0;
	$scope.getMoreRq = pagingService.initGetMoreRq($scope.placeId); // ???

	$scope.getImage = function() {
		var link = DOMAIN + '/data/image/getmore';
		pagingService.getMore(IMAGE_PER_PAGE, link, $scope.getMoreRq,
				$scope.imageViews);
	};

	// MinhLe
	$scope.showImage = function(idx) {
		if (idx == $scope.imageViews.length - 1 && $scope.viewmore == 1) {
			$scope.getImage();
		}
		$scope.imageShowIdx = idx;
		$scope.imageShow = $scope.imageViews[idx];
		$("#divImgShow").fadeIn("fast");
	};

	// MinhLe
	$scope.showPrevImage = function() {
		if ($scope.imageShowIdx > 0) {
			$scope.imageShowIdx--;
			$scope.imageShow = $scope.imageViews[$scope.imageShowIdx];
		}
	};

	// MinhLe
	$scope.showNextImage = function() {
		if ($scope.imageShowIdx == $scope.imageViews.length - 2
				&& $scope.viewmore == 1) {
			$scope.getImage();
		}
		if ($scope.imageShowIdx < $scope.imageViews.length - 1) {
			$scope.imageShowIdx++;
			$scope.imageShow = $scope.imageViews[$scope.imageShowIdx];
		}
	};

	// MinhLe
	$scope.hideImage = function() {
		$("#divImgShow").fadeOut("fast");
	};

	$scope.getImage();
}
ImageCtrl.$inject = [ '$scope', '$http', '$routeParams', '$location',
		'pagingService' ];

function ImageUpdateCtrl($scope, $http, $routeParams, $location, pagingService,
		errorHandlerService, validationService) {
	$scope.placeId = $routeParams.placeId;
	$scope.imageViews = [];
	$scope.pageList = [];
	$scope.selectedPage = 1;
	$scope.pageRq = pagingService.initPageRq($scope.placeId);

	$scope.getImagePageNum = function() {
		$http.post(DOMAIN + '/data/image/getpagecount/' + $scope.placeId)
				.success(function(data) {
					$scope.pageList = [];
					for ( var i = 1; i <= data; i++) {
						$scope.pageList.push(i);
					}
				});
	};

	$scope.getImagePageNum();

	$scope.getImage = function(idx) {
		// Set selected page when change page
		$scope.selectedPage = idx;

		idx = idx - 1;
		$scope.pageRq.page = idx;
		var link = DOMAIN + '/data/image/getbypage';
		$scope.imageViews = [];
		pagingService.getByPage(IMAGE_PER_PAGE, link, $scope.pageRq,
				$scope.imageViews);
	};

	$scope.updateImage = function(image) {
		// validate data
		validationService.checkLarger(image.caption, IMAGE_CAPTION_LENGTH, 'err244', $scope.langCommon.updateFail);
		
		$http.post(DOMAIN + '/data/image/update/' + image.id, image).success(
				function(data) {
					errorHandlerService.handle(data, $scope.langCommon.updateFail);	// check error
					$.pnotify({
						delay : 2000,
						title : $scope.langCommon.updateSuccess,
						type : 'success',
					});
				});
	};

	$scope.delImage = function(image) {
		$http.post(DOMAIN + '/data/image/del/' + image.id).success(
				function(data) {
					deleteById($scope.imageViews, image.id);
					$scope.getImagePageNum(); // need
					// ????????????????????????????????
				});
	};

	$scope.setAvatar = function(image) {
		$http.post(
				DOMAIN + '/data/image/setAva/' + $scope.placeId + '/'
						+ image.id).success(function(data) {
			$.pnotify({
				delay : 1000,
				title : $scope.langCommon.updateSuccess,
				type : 'success',
			});
		});
	};

	$("#uploadFrame").attr("src",
			DOMAIN + '/component/upload/' + $scope.placeId);

	var dialog;

	$("#uploadFrame")
			.load(
					function() {
						document.getElementById('uploadFrame').contentWindow.RunCallbackFunction = $scope.receiveUploadJson;
					});

	$scope.popup = function() {
		$(".download-table").find("tr").remove();
		if (dialog == null) {
			dialog = $("#popup").dialog({
				title : $scope.langPlace.upload,
				autoOpen : false,
				height : 500,
				width : 650
			});
		}
		dialog.dialog("open");
	};

	$scope.receiveUploadJson = function(json) {
		$http.get();
		errorHandlerService.handle(json, $scope.langCommon.updateFail);	// check error
		$scope.imageViews.splice(0, 0, json);
		$scope.imageViews = $scope.imageViews.slice(0, IMAGE_PER_PAGE);
		$scope.getImagePageNum(); // need ???????????????????
		$scope.getImage(1);
	};

	$scope.goPlace = function() {
		$location.path('view/' + $scope.placeId);
	};

	$(function() {
		'use strict';
		$('#fileupload').fileupload();

		$('#fileupload').fileupload(
				'option',
				'redirect',
				window.location.href.replace(/\/[^\/]*$/,
						'/cors/result.html?%s'));

		$('#fileupload').fileupload('option', {
			url : DOMAIN + '/data/image/upimg/' + $scope.placeId,
			maxFileSize : 5000000,
			acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
			process : [ {
				action : 'load',
				fileTypes : /^image\/(gif|jpeg|png)$/,
				maxFileSize : 20000000
			}, {
				action : 'resize',
				maxWidth : 1024,
				maxHeight : 768
			}, {
				action : 'save'
			} ]
		});

		$('#fileupload')
				.fileupload(
						{
							uploadTemplateId : null,
							downloadTemplateId : null,
							uploadTemplate : function(o) {
								var rows = $();
								$
										.each(
												o.files,
												function(index, file) {
													var row = $('<tr class="template-upload fade">'
															+ '<td class="preview"><span class="fade"></span></td>'
															+ '<td class="title"><label>Caption: <input name="caption" required></label></td>'
															+ '<td class="name"></td>'
															+ '<td class="size"></td>'
															+ (file.error ? '<td class="error" colspan="2"></td>'
																	: '<td><div class="progress progress-success progress-striped active">'
																			+ '<div class="bar" style="width:0%;"></div></div></td>'
																			+ '<td class="start"><button class="btn btn-primary">'
																			+ '<i class="icon-upload icon-white"></i><span>'
																			+ $scope.langPlace.start
																			+ '</span>'
																			+ '</button></td>')
															+ '<td class="cancel"><button class="btn btn-warning">'
															+ '<i class="icon-ban-circle icon-white"></i>'
															+ '<span>'
															+ $scope.langPlace.cancel
															+ '</span>'
															+ '</button></td></tr>');
													row.find('.name').text(
															file.name);
													row
															.find('.size')
															.text(
																	o
																			.formatFileSize(file.size));
													if (file.error) {
														row
																.find('.error')
																.text(
																		locale.fileupload.errors[file.error]
																				|| file.error);
													}
													rows = rows.add(row);
												});
								return rows;
							},
							downloadTemplate : function(o) {
								var rows = $();
								$
										.each(
												o.files,
												function(index, file) {
													var row = $('<tr class="template-download fade">'
															+ (file.error ? '<td></td><td class="name"></td>'
																	+ '<td class="size"></td><td class="error" colspan="5">'
																	+ '<span class="label label-important">'
																	+ $scope.langPlace.error
																	+ '</span>'
																	+ '<span class="u-content"></span></td>'
																	: '<td></td><td class="name"></td>'
																			+ '<td class="size"></td><td class="error" colspan="5">'
																			+ '<span class="label label-success">'
																			+ $scope.langPlace.success
																			+ '</span>'
																			+ '<span class="u-content"></span></td>')
															+ '</tr>');
													if (file.error) {
														row.find('.name').text(
																file.name);
														row
																.find(
																		'.u-content')
																.text(
																		locale.fileupload.errors[file.error]
																				|| file.error);
													} else {
														row
																.find(
																		'.u-content')
																.text(
																		$scope.langPlace.uploadsuccess);

													}
													rows = rows.add(row);
												});
								return rows;
							}
						});

		$('#fileupload').bind('fileuploadsubmit', function(e, data) {
			var inputs = data.context.find(':input');
			data.formData = inputs.serializeArray();
			data.formData.push({
				"name" : "placeId",
				"value" : $scope.placeId,
			});
		});

		$('#fileupload').bind('fileuploadcompleted', function(e, data) {
			data.context.each(function(index) {
				var file = ($.isArray(data.result) && data.result[index]);
				$scope.receiveUploadJson(file);
			});
		});
	});
	$scope.getImage(1);
}
ImageUpdateCtrl.$inject = [ '$scope', '$http', '$routeParams', '$location',
		'pagingService', 'errorHandlerService', 'validationService' ];

// ------------- IMAGE CONSTANT --------------
var IMAGE_CAPTION_LENGTH = 200;