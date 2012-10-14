module.factory('errorHandlerService', [ '$rootScope', '$http',
		function($rootScope, $http) {

			var instance = {
				handle : function(data, title) {
					if (data.error != undefined) {
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[data.error],
							type : 'error',
						});
						throw data.error;
					}
				},
			};

			return instance;
		} ]);