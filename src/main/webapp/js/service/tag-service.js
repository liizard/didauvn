module.factory('tagService', [
		'$http',
		function($http) {

			function addTag(item, divName, listStore) {
				var tag = {
					"id" : item.id,
					"name" : item.name
				};
				if (existed(listStore, tag.id) != -1) {
					divName.val("");
					return;
				}
				listStore.push(tag);
				divName.val("");
				$http.get();
			}

			var instance = {
				initAutocomplete : function(divName, tagRq, listStore, addFunction) {
					divName.autocomplete(
							{
								minLength : 1,
								source : function(request, response) {
									$http.post(DOMAIN + '/data/attr/placetag/gets',
											tagRq).success(
											function(data) {
												response(data);
											});
								},
								focus : function(event, ui) {
									divName.val(ui.item.name);
									return false;
								},
								select : function(event, ui) {
									if(addFunction != null || addFunction != undefined) {
										addFunction(ui.item);
									} else {
										addTag(ui.item, divName, listStore);
									}
									return false;
								}
							}).data("autocomplete")._renderItem = function(ul,
							item) {
						return $("<li></li>").data("item.autocomplete", item)
								.append("<a>" + item.name + "</a>")
								.appendTo(ul);
					};

					divName.on('keydown', function(e) {
						if (listStore == undefined) return;
						var keyCode = e.keyCode || e.which;
						if (keyCode == 8 && divName.val() == "") {
							var len = listStore.length;
							listStore.splice(len - 1, 1);
							$http.get();
						}
					});
				},

				// delete tag from request list function
				deleteTag : function(id, listStore) {
					var index = existed(listStore, id);
					if (index != -1) {
						listStore.splice(index, 1);
						$http.get();
					}
				}
			};

			return instance;
		} ]);