module.factory('langService', [
		'$rootScope',
		'$http',
		function($rootScope, $http) {

			$rootScope.lang = 'vi';

			// Reset languages
			$rootScope.resetLang = function() {
				$rootScope.langCommon = null;
				$rootScope.langSearch = null;
				$rootScope.langPlace = null;
				$rootScope.langLocation = null;
				$rootScope.langUser = null;
				$rootScope.days = null;
				$rootScope.businessTypes = null;
				$rootScope.langMessage = null;
				$rootScope.errMsg = null;
				$rootScope.langAdmin = null;
				$rootScope.langTos = null;
			};

			// Get languages
			$rootScope.getLangCommon = function() {
				if ($rootScope.langCommon == null) {
					$http.get(
							DOMAIN + '/json/lang/common_' + $rootScope.lang
									+ '.json').success(function(data) {
						$rootScope.langCommon = data;
					});
				}
				if ($rootScope.errMsg == null) {
					$http.get(
							DOMAIN + '/json/data/err_' + $rootScope.lang
									+ '.json').success(function(data) {
						$rootScope.errMsg = data;
					});
				}
			};

			$rootScope.getLangSearch = function() {
				if ($rootScope.langSearch == null)
					$http.get(
							DOMAIN + '/json/lang/search_' + $rootScope.lang
									+ '.json').success(function(data) {
						$rootScope.langSearch = data;
					});
			};

			$rootScope.getLangPlace = function() {
				if ($rootScope.langPlace == null)
					$http.get(
							DOMAIN + '/json/lang/place_' + $rootScope.lang
									+ '.json').success(function(data) {
						$rootScope.langPlace = data;
					});
			};

			$rootScope.getLangLocation = function() {
				if ($rootScope.langLocation == null)
					$http.get(
							DOMAIN + '/json/lang/location_' + $rootScope.lang
									+ '.json').success(function(data) {
						$rootScope.langLocation = data;
					});
			};

			$rootScope.getLangUser = function() {
				if ($rootScope.langUser == null)
					$http.get(
							DOMAIN + '/json/lang/user_' + $rootScope.lang
									+ '.json').success(function(data) {
						$rootScope.langUser = data;
					});
			};

			$rootScope.getDays = function() {
				if ($rootScope.days == null)
					$http.get(
							DOMAIN + '/json/data/day_' + $rootScope.lang
									+ '.json').success(function(data) {
						$rootScope.days = data;
					});
			};

			$rootScope.getBusinessTypes = function() {
				if ($rootScope.businessTypes == null)
					$http.get(
							DOMAIN + '/json/data/businesstype_'
									+ $rootScope.lang + '.json').success(
							function(data) {
								$rootScope.businessTypes = data;
							});
			};

			$rootScope.getLangMessage = function() {
				if ($rootScope.langUser == null)
					$http.get(
							DOMAIN + '/json/lang/message_' + $rootScope.lang
									+ '.json').success(function(data) {
						$rootScope.langMessage = data;
					});
			};

			$rootScope.getLangAdmin = function() {
				if ($rootScope.langAdmin == null) {
					$http.get(
							DOMAIN + '/json/lang/admin_' + $rootScope.lang
									+ '.json').success(function(data) {
						$rootScope.langAdmin = data;
					});
				}
			};

			$rootScope.getLangTos = function() {
				if ($rootScope.langTos == null) {
					$http.get(
							DOMAIN + '/json/lang/tos_' + $rootScope.lang
									+ '.json').success(function(data) {
						$rootScope.langTos = data;
					});
				}
			};
			// Get specified language for current page
			$rootScope.chageLanguage = function() {
				$rootScope.resetLang();
				$rootScope.getLangCommon();
				$rootScope.getLangTos();
				
				var location = window.location.pathname;
				if (location.indexOf('search') > -1) {
					$rootScope.getLangSearch();
				} else if (location.indexOf('place') > -1) {
					$rootScope.getLangUser();
					$rootScope.getLangPlace();
					$rootScope.getLangLocation();
					$rootScope.getDays();
					$rootScope.getBusinessTypes();
				} else if (location.indexOf('goaround') > -1) {
					$rootScope.getLangPlace();
					$rootScope.getBusinessTypes();
				} else if (location.indexOf('user') > -1) {
					$rootScope.getLangUser();
					$rootScope.getLangMessage();
					$rootScope.getLangTos();
				} else if (location.indexOf('admin') > -1) {
					$rootScope.getLangAdmin();
				} else if (location.indexOf('home') > -1) {
					$rootScope.getLangTos();
				}

				
			};

			// Initial
			$http.get(DOMAIN + '/data/user/setting/lang/get').success(
					function(data) {
						data = data.substring(1, data.length - 1);
						$rootScope.lang = data;
						$rootScope.chageLanguage();
					});

			var instance = {
				setLanguage : function(lang) {
					$rootScope.lang = lang;
					$http.post(
							DOMAIN + '/data/user/setting/lang/update/' + lang)
							.success(function(data) {
							});
					$rootScope.chageLanguage();
				},

				getLanguage : function() {
					return $rootScope.lang;
				},
			};
			return instance;
		} ]);