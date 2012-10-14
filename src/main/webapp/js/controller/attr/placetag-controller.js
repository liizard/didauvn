function PlaceTagCtrl($scope, $http, $routeParams, $location, tagService) {

	$scope.placeId = $routeParams.placeId;
	$scope.placetags = [];
	$scope.tagUpdate = {};
	$scope.tagRq = {};

	$scope.getPlaceTags = function() {
		$http.get(DOMAIN + '/data/attr/placetag/' + $scope.placeId).success(
				function(data) {
					$scope.placetags = data;
				});
	};

	$scope.getEmptyTags = function() {
		$http.post(DOMAIN + '/data/attr/placetag/emptyupdatetag').success(
				function(data) {
					$scope.tagUpdate = data;
					$scope.getMainTag();
				});
	};

	$scope.getMainTag = function() {
		$http.get(DOMAIN + '/data/attr/placetag/maintag/' + $scope.placeId)
				.success(function(data) {
					if(data.id != undefined) {
						$scope.tagUpdate.mainTag = data;
					}
				});
	};

	$scope.saveTags = function() {
		$http.post(DOMAIN + '/data/attr/placetag/updatetag/' + $scope.placeId,
				$scope.tagUpdate).success(function(data) {
					deleteById($scope.placeStore, $scope.placeId);
					$scope.tagUpdate.add = [];
					$scope.tagUpdate.remove = [];
					$.pnotify({
						delay : 1000,
						title : $scope.langCommon.updateSuccess,
						type : 'success',
					});
		});
		
	};

	// Add new tag
	$scope.addTag = function(item) {
		$http.get();
		var newTag = {
			"id" : item.id,
			"name" : item.name
		};
		var index;

		// Check existed in Current List
		index = existed($scope.placetags, newTag.id);
		if (index != -1) {
			alert($scope.langPlace.tagexisted);
			$scope.clearPlaceTag();
			return;
		}

		// Check existed in Delete List
		index = existed($scope.tagUpdate.remove, newTag.id);
		if (index != -1) {
			$scope.tagUpdate.remove.splice(index, 1); // If existed, pop out
		} else {
			$scope.tagUpdate.add.push(newTag); // if not existed , push into list
		}

		$scope.placetags.push(newTag); // push into current list
		$scope.clearPlaceTag();
	};

	// Delete existed tag
	$scope.deleteTag = function(tag) {

		// Check existed in Add list
		var index = existed($scope.tagUpdate.add, tag.id);
		if (index != -1) {
			$scope.tagUpdate.add.splice(index, 1); // if existed, pop out
		} else {
			$scope.tagUpdate.remove.push(tag); // if nor existed, push into list
		}

		var index = $scope.placetags.indexOf(tag);
		// Main tag jump to other
		if ($scope.tagUpdate.mainTag.id == tag.id) {
			if ($scope.placetags.length == 1) {
				$scope.tagUpdate.mainTag.id = 0;
			} else if (index == $scope.placetags.length - 1) {
				$scope.tagUpdate.mainTag.id = $scope.placetags[index - 1].id;
			} else {
				$scope.tagUpdate.mainTag.id = $scope.placetags[index + 1].id;
			}
		}
		$scope.placetags.splice(index, 1); // pop out of current list
	};

	$scope.clearPlaceTag = function() {
		$("#newid").val("");
		$("#newtag").val("");
	};

	$scope.getPlaceTags();
	$scope.getEmptyTags();
	//$scope.getMainTag();
	
	$scope.getTagRq = function() {
		$http.post(DOMAIN + '/data/attr/placetag/gettagrq').success(
				function(data) {
					$scope.tagRq = data;
					tagService.initAutocomplete($("#newtag"),
							$scope.tagRq, null, $scope.addTag);
				});
	};

	$scope.getTagRq();

}
PlaceTagCtrl.$inject = ['$scope', '$http', '$routeParams', '$location', 'tagService'];