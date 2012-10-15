module
		.factory(
				'securityService',
				[
						'$rootScope',
						'$routeParams',
						'$location',
						'$http',
						function($rootScope, $routeParams, $location, $http) {
							var interval = null;
							$rootScope.routeSecChange = function() {

								// ADMIN ROLE can access anywhere
								if ($rootScope.userSession.admin) {
									return;
								}

								var location = window.location.toString();
								if (location.indexOf('/place/#/update') > -1) {

									if ($rootScope.userSession.ownerPlaces == undefined) {
										$location.path('view/'
												+ $routeParams.placeId);
										return;
									}

									for ( var i in $rootScope.userSession.ownerPlaces) {
										if ($rootScope.userSession.ownerPlaces[i] == $routeParams.placeId) {
											return;
										}
									}

									for ( var i in $rootScope.userSession.managerPlaces) {
										if ($rootScope.userSession.managerPlaces[i] == $routeParams.placeId) {
											return;
										}
									}
									$location.path('view/'
											+ $routeParams.placeId);
								}
								if (location.indexOf('/place/#/setting') > -1) {
									if ($rootScope.userSession.ownerPlaces == undefined) {
										$location.path('view/'
												+ $routeParams.placeId);
										return;

									}
									for ( var i in $rootScope.userSession.ownerPlaces) {
										if ($rootScope.userSession.ownerPlaces[i] == $routeParams.placeId) {
											return;
										}
									}
									$location.path('view/'
											+ $routeParams.placeId);
								}
								if (location.indexOf('/user/#/register') > -1) {
									return;
								}
								if (location.indexOf('/user/#/') > -1) {
									if ($rootScope.userSession.user.uid == 0) {
										$location.path('login');
									}
								}

								if (location.indexOf('/admin/#/') > -1) {
									if (!$rootScope.userSession.user.admin) {
										document.location.href = DOMAIN;
									}
								}

							};
							$rootScope.checkNotify = function() {
								if ($rootScope.userSession.user.uid > 0) {
									$http
											.get(DOMAIN + '/data/message/check')
											.success(
													function(data) {
														if (data.error != undefined) {
															clearInterval(interval);
															return;
														}
														if (data > 0) {
															$
																	.pnotify({
																		delay : 4000,
																		title : $rootScope.langCommon.notifications,
																		text : '<a href="'
																				+ DOMAIN
																				+ '/user/#/message?rs='
																				+ Math
																						.random()
																				+ '"> '
																				+ $rootScope.langCommon.have
																				+ data
																				+ $rootScope.langCommon.unreadmsg
																				+ ' </a>',
																		type : 'info',
																	});
														}
													});
								}
							};
							$rootScope.userSession = {};
							$rootScope.getUserSession = function() {
								$http
										.post(DOMAIN + '/data/user/current')
										.success(
												function(data) {
													$rootScope.userSession = data;
													interval = setInterval(
															function() {
																$rootScope
																		.checkNotify();
															}, 30000);
													$rootScope.routeSecChange();
													$rootScope
															.$on(
																	'$afterRouteChange',
																	function() {
																		$rootScope
																				.routeSecChange();
																	});
												});
							};

							$rootScope.getUserSession();

							var instance = {

							};

							return instance;
						} ]);
