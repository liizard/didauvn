module.factory('businessTypeService', [
		'$http',
		function($http) {
			var instance = {
				getList : function(obj) {
					$http.get(DOMAIN + '/data/businesstype/gets').success(
							function(data) {
								for ( var i in data) {
									obj.push(data[i]);
								}
							});
				}
			};

			return instance;
		} ]);