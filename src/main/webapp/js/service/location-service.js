module.factory('locationService', [
		'$http',
		'$cookieStore',
		function($http, $cookieStore) {

			// Read from cookies
			var wardStore = $cookieStore.get('wardStore');
			if (wardStore == undefined) {
				wardStore = [];
			}

			var streetStore = $cookieStore.get('streetStore');
			if (streetStore == undefined) {
				streetStore = [];
			}

			function wardIndexOf(wards, districtId) {
				for ( var i in wards) {
					if (wards[i].districtId == districtId)
						return i;
				}
				return -1;
			}

			function streetIndexOf(streets, wardId) {
				for ( var i in streets) {
					if (streets[i].wardId == wardId)
						return i;
				}
				return -1;
			}

			var instance = {
				getDistricts : function(cityId, obj) {
					$http.get(DOMAIN + '/data/location/districts/' + cityId)
							.success(function(data) {
								for ( var i in data) {
									obj.push(data[i]);
								}
							});
				},

				getWards : function(districtId, obj) {
					// Get from server if non exist else get from local store
					if (wardIndexOf(wardStore, districtId) == -1) {
						$http
								.get(
										DOMAIN + '/data/location/wards/'
												+ districtId).success(
										function(data) {
											for ( var i in data) {
												obj.push(data[i]);
												wardStore.push(data[i]);
												
												// Write to cookie
												$cookieStore.put('wardStore',
														wardStore);
											}
										});
						// Get from store
					} else {
						for ( var i in wardStore) {
							if (wardStore[i].districtId == districtId)
								obj.push(wardStore[i]);
						}
					}
				},

				getStreets : function(wardId, obj) {
					// Get from server if non exist else get from local store
					if (streetIndexOf(streetStore, wardId) == -1) {
						$http.get(DOMAIN + '/data/location/streets/' + wardId)
								.success(function(data) {
									for ( var i in data) {
										obj.push(data[i]);
										streetStore.push(data[i]);
										
										// Write to cookie
										$cookieStore.put('streetStore',
												streetStore);
									}
								});
						// Get from store
					} else {
						for ( var i in streetStore) {
							if (streetStore[i].wardId == wardId)
								obj.push(streetStore[i]);
						}
					}
				},
			};

			return instance;
		} ]);