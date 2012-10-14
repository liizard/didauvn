module.factory('locationAuto', [
		'$http',
		function($http) {

			// add location from request list function
			function addLocation(item, divName, rqStore) {
				rqStore.districtId = item.districtId;
				rqStore.wardId = item.wardId;
				rqStore.streetId = item.streetId;
				rqStore.locationDisplay = item.display;
				divName.val('');
				$http.get();
			}

			var instance = {};
			instance = {
				initAutocomplete : function(divName, locationSearchRq, rqStore) {
					divName.autocomplete(
							{
								minLength : 2,
								source : function(request, response) {
									$http.post(DOMAIN + '/data/search/location',
											locationSearchRq).success(
											function(data) {
												response(data);
											});
								},
								focus : function(event, ui) {
									divName.val(ui.item.display);
									return false;
								},
								// autoFocus: true,
								select : function(event, ui) {
									addLocation(ui.item, divName, rqStore);
									return false;
								}
							}).data("autocomplete")._renderItem = function(ul, item) {
						return $("<li></li>").data("item.autocomplete", item).append(
								"<a>" + item.display + "</a>").appendTo(ul);
					};

					divName.on('keydown', function(e) {
						var keyCode = e.keyCode || e.which;
						if (keyCode == 8 && divName.val() == "") {
							instance.deleteLocation(rqStore);
						}
					});
				},

				// delete location from request list function
				deleteLocation : function(rqStore) {
					rqStore.locationDisplay = '';
					rqStore.districtId = 0;
					rqStore.wardId = 0;
					rqStore.streetId = 0;
					$http.get();
				}
			};

			return instance;
		} ]);