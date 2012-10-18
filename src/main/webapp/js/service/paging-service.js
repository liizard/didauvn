module.factory('pagingService', [
		'$http',
		function($http) {

			var instance = {

				// -------- Init new Get More Reuqest variable
				initGetMoreRq : function(placeId) {
					var getMoreRq = {};
//					$http.post(DOMAIN + '/data/paging/getmorerq').success(
//							function(data) {
//								getMoreRq = data;
//							});
					getMoreRq.placeId = placeId;
					getMoreRq.lastId = 0;
					getMoreRq.viewMore = false;
					return getMoreRq;
				},

				// -------- Init new Page Request variable
				initPageRq : function(placeId) {
					var pageRq = {};
//					$http.post(DOMAIN + '/data/paging/pagerq').success(
//							function(data) {
//								pageRq = data;
//							});
					pageRq.placeId = placeId;
					pageRq.page = 0;
					return pageRq;
				},

				// -------- Get more function
				getMore : function(CONSTANT, link, getMoreRq, listStore) {
					$http.post(link, getMoreRq).success(function(data) {
						// check if return data is empty
						if(data.length == 0) {
							return;
						}
						
						var length = data.length;
						if (length == (CONSTANT + 1)) {
							length -= 1;
							getMoreRq.viewMore = true;
						} else {
							getMoreRq.viewMore = false;
						}

						for ( var i = 0; i < length; i++) {
							listStore.push(data[i]);
						}
						getMoreRq.lastId = data[length - 1].id;
					});
				},
				
				// ----------- Get by Page function
				getByPage : function(CONSTANT, link, pageRq, listStore) {
					$http.post(link, pageRq).success(function(data) {
						// check if return data is empty
						if(data.length == 0) {
							return;
						}
						
						var length = data.length;
						if (length == (CONSTANT + 1)) {
							length -= 1;
						}
						for ( var i = 0; i < length; i++) {
							listStore.push(data[i]);
						}
					});
				},
				
				// ----------- Get by Page function
				getByPageNo : function(CONSTANT, link, page, listStore) {
					$http.post(link + '/' + page).success(function(data) {
						// check if return data is empty
						if(data.length == 0) {
							return;
						}
						
						var length = data.length;
						if (length == (CONSTANT + 1)) {
							length -= 1;
						}
						for ( var i = 0; i < length; i++) {
							listStore.push(data[i]);
						}
					});
				}
				
			};
			return instance;
		} ]);