<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../inc/taglib.jsp"%>

<div id="divSearchPlace" ng-controller="SearchCtrl">
	<div id="result">
		<div id="tabHeader" class="searchMenu">
			<a id="1" class="tab selectTabHeader">{{langSearch.list}}</a> <a
				id="2" class="tab" ng-click="loadMap()">{{langSearch.map}}</a>
		</div>

		<div id="tabData">
			<div id="tab-data1" class="tab-data selectedTab">
				<div id="searchResult">
					<div class="oneResultContainer"
						ng-repeat="searchPlace in searchPlaces">
						<div class="oneResult">
							<div class="oneResultAvatar">
								<a href="#/place/{{searchPlace.id}}"><img alt=""
									ng-src="<spring:message code="domain"/>/img/gallery/thumb/{{searchPlace.avatar}}.jpg" />
								</a>
							</div>
							<div class="oneResultName">
								<a
									href="<spring:message code="domain"/>/place#/view/{{searchPlace.id}}">{{searchPlace.name}}<span
									ng-show="searchPlace.tagName!=null">
										&nbsp;|&nbsp;{{searchPlace.tagName}}</span>
								</a>
							</div>
							<div class="oneResultInfo">
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td><img alt=""
											src="<spring:message code="domain"/>/img/res/icon/address.png" />
										</td>
										<td>{{searchPlace.address}} {{searchPlace.streetName}},
											{{searchPlace.wardName}}, {{searchPlace.districtName}},
											{{searchPlace.cityName}}</td>
									</tr>
									<tr>
										<td><img alt=""
											src="<spring:message code="domain"/>/img/res/icon/phone.png" />
										</td>
										<td>{{searchPlace.phone}}</td>
									</tr>
									<tr>
										<td><img alt=""
											src="<spring:message code="domain"/>/img/res/icon/web.png" />
										</td>
										<td>{{searchPlace.website}}</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div style="clear: both; padding-left: 15px;">
						<a class="action" ng-show="searchOption.viewmore==1" href
							ng-click="getMore()">{{langCommon.viewmore}}>></a>
					</div>
				</div>
			</div>

			<div id="tab-data2" class="tab-data">
				<div id="mapSearch">

					<div id="map_canvas"></div>

					<div>
						<table>
							<tr>
								<td style="padding-left: 30px;"><a href=""
									onclick="getCurrentLocation()">{{langSearch.currentLocation}}</a>
								</td>
								<td style="padding-left: 20px;"><a target="_blank"
									ng-href="{{directionLink}}">{{langSearch.getMeThere}}</a></td>
							</tr>
						</table>
					</div>

					<div id="oneMapResult" style="display: table-cell;"
						ng-hide="oneMapPlace == undefined">
						<div class="oneResultAvatar">
							<a href="<spring:message code="domain"/>/place/#/view/{{oneMapPlace.id}}"><img alt=""
								ng-src="<spring:message code="domain"/>/img/gallery/thumb/{{oneMapPlace.avatar}}.jpg" />
							</a>
						</div>
						<div class="oneResultName">
							<a href="<spring:message code="domain"/>/place/#/view/{{oneMapPlace.id}}">{{oneMapPlace.name}}<span
								ng-show="oneMapPlace.tagName!=null">
									&nbsp;|&nbsp;{{oneMapPlace.tagName}}</span>
							</a>
						</div>
						<div class="oneResultInfo">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><img alt=""
										src="<spring:message code="domain"/>/img/res/icon/address.png" />
									</td>
									<td>{{oneMapPlace.address}} {{oneMapPlace.streetName}},
										{{oneMapPlace.wardName}}, {{oneMapPlace.districtName}},
										{{oneMapPlace.cityName}}</td>
								</tr>
								<tr>
									<td><img alt=""
										src="<spring:message code="domain"/>/img/res/icon/phone.png" />
									</td>
									<td>{{oneMapPlace.phone}}</td>
								</tr>
								<tr>
									<td><img alt=""
										src="<spring:message code="domain"/>/img/res/icon/web.png" />
									</td>
									<td>{{oneMapPlace.website}}</td>
								</tr>
							</table>
						</div>
					</div>

					<!-- <div style="clear: both; padding-left: 15px; display: table-row;">
						<a class="action" ng-show="searchOption.viewmore==1" href
							ng-click="getMore()">{{langCommon.viewmore}}>></a>
					</div> -->

				</div>
			</div>
		</div>
	</div>
	<div style="clear: both"></div>
</div>