<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../inc/taglib.jsp"%>

<div ng-controller="PlaceCtrl">
	<div id="wrapperFeedback">
		<div id="note">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="noteArrow"><img alt=""
						src="<spring:message code="domain"/>/img/res/noteArrow.png" /></td>
				</tr>
				<tr>
					<td id="noteContent">
						<p>{{langUser.gender}} : {{currentUser.gender}}</p>
						<p>{{langUser.birthday}} : {{currentUser.birthday}}</p>
						<p>{{langUser.createdate}} : {{currentUser.createdate}}</p>
						<p ng-show="currentUser.profileFB!=null">
							<a href="{{currentUser.profileFB}}" target="_blank">{{langUser.profilefb}}</a>
						</p>
					</td>
				</tr>
			</table>
		</div>
		<fb:comments href="http://9gag.com/gag/5582966" num_posts="2"
			width="470"></fb:comments>
		<fb:like
			href="http://developers.facebook.com/docs/reference/plugins/like/"
			send="true" width="450" show_faces="true"></fb:like>

	</div>

	<div id="placeInfo">

		<div id="wrapperPlaceOverview">
			<div id="placeAvatar">
				<img alt=""
					ng-src="<spring:message code="domain"/>/img/gallery/thumb/{{place.avatar}}.jpg">
			</div>
			<div id="placeOverview">
				<p id="placeName">
					{{place.name}}<span ng-show="place.tagName!=null">
						&nbsp;|&nbsp;{{place.tagName}}</span>
				</p>
				<div id="placeAddress">
					<p>
						<img style="vertical-align: middle;" alt=""
							src="<spring:message code="domain"/>/img/res/icon/address.png" />
						{{place.address}} {{place.streetName}}, {{place.wardName}},
						{{place.districtName}}, {{place.cityName}}
					</p>
					<p>
						<img style="vertical-align: middle;" alt=""
							src="<spring:message code="domain"/>/img/res/icon/phone.png" />
						{{place.phone}}
					</p>
				</div>
			</div>
			<div ng-controller="ReportSubmitCtrl">
				<a href="" ng-click="reportPlace()">{{langCommon.report}}</a>
			</div>
			<div id="placeOption">
				<a
					ng-show="userSession.ownerPlaces.indexOf(place.id)>-1 || userSession.admin==true"
					href="<spring:message code="domain"/>/place/#/setting/{{place.id}}">{{langCommon.admin}}</a>&nbsp;&nbsp;<a
					ng-show="userSession.ownerPlaces.indexOf(place.id)>-1 || userSession.managerPlaces.indexOf(place.id)>-1|| userSession.admin==true"
					href="<spring:message code="domain"/>/place/#/update/general/{{place.id}}">{{langCommon.update}}</a>
			</div>
		</div>


		<div id="placeNews" ng-controller="NewsCtrl" ng-show="newsExist">
			<div id="placeNewsTitle">
				<img width="35" height="35" ng-show="news.promotion"
					ng-src="<spring:message code="domain"/>/img/res/icon/white/discount.png"><img
					width="35" height="35" ng-show="news.event"
					ng-src="<spring:message code="domain"/>/img/res/icon/white/event.png">
				&nbsp;{{news.title}}
			</div>
			<div id="placeNewsDesc">{{news.desc}}</div>
			<img ng-show="news.image>0"
				ng-src="<spring:message code="domain"/>/img/news/{{news.image}}.jpg" />
			<div id="placeNewsCont" ng-bind-html="news.cont"></div>
			<div style="clear: both;"></div>
		</div>

		<div id="placeDetail">
			<div id="tabHeader" class="placeMenu">
				<a id="1" class="tab selectTabHeader">{{langPlace.description}}</a>
				<a id="2" class="tab">{{langPlace.info}}</a> <a id="3" class="tab"
					onclick="">{{langPlace.map}}</a>
			</div>

			<div id="placeDetailContent">
				<div id="tabData">
					<div id="tab-data1" class="tab-data selectedTab">
						<div ng-bind-html="place.dcrp"></div>
					</div>
					<div id="tab-data2" class="tab-data">
						<table class="data">
							<tr>
								<td class="title">{{langPlace.location}}:</td>
								<td class="cont">{{place.address}},{{location.Street}}
									{{place.streetName}}, {{location.Ward}} {{place.wardName}},
									{{location.District}} {{place.districtName}}, {{location.City}}
									{{place.cityName}}</td>
							</tr>
							<tr>
								<td class="title">{{langPlace.phone}}:</td>
								<td class="cont">{{place.phone}}</td>
							</tr>
							<tr>
								<td class="title">{{langPlace.website}}:</td>
								<td class="cont">{{place.website}}</td>
							</tr>
							<tr>
								<td class="title">{{langPlace.fax}}:</td>
								<td class="cont">{{place.fax}}</td>
							</tr>
							<tr>
								<td class="title">{{langPlace.email}}:</td>
								<td class="cont">{{place.email}}</td>
							</tr>
						</table>
						<br>
						<div id="divPaymentMethod" ng-controller="PaymentMethodCtrl">
							<table class="data">
								<tr>
									<th colspan="3">{{langPlace.paymentmethod}}</th>
								</tr>
								<tr>
									<td><input type="checkbox" disabled="disabled"
										ng-model="paymentMethods.payByCash" />&nbsp;<span>Cash</span>
									</td>
									<td><input type="checkbox" disabled="disabled"
										ng-model="paymentMethods.payByVisa" />&nbsp;<span>Visa</span>
									</td>
									<td><input type="checkbox" disabled="disabled"
										ng-model="paymentMethods.payByMasterCard" />&nbsp;<span>MasterCard</span>
									</td>
								</tr>
							</table>
						</div>
						<br>
						<div id="divOperationHour" ng-controller="OperationHourCtrl">
							<table class="data">
								<tr>
									<th colspan="3">{{langPlace.operationhour}}</th>
								</tr>
								<tr ng-repeat="hour in hours" ng-show="hour.active">
									<td class="title">{{days[hour.day-1].day}}:</td>
									<td class="cont">{{times[hour.openHour-1].time}} -
										{{times[hour.closeHour-1].time}}</td>
									<td class="cont">{{hour.remark}}</td>
								</tr>
							</table>
						</div>
					</div>
					<div id="tab-data3" class="tab-data">
						<img alt="Static map"
							ng-src="http://maps.googleapis.com/maps/api/staticmap?center={{place.locationX}},{{place.locationY}}&zoom=16&size=600x400&maptype=roadmap&markers=color:blue%7Clabel:S%7C{{place.locationX}},{{place.locationY}}&sensor=false" />
						<br> <a target="_blank" ng-href=" {{directionLink}}">{{langPlace.direction}}</a>
					</div>
				</div>
			</div>
		</div>

		<div id="placeMedia" style="padding-bottom: 1px;">
			<div id="tabHeader" class="placeMenu">
				<a id="1" class="tab selectTabHeader">{{langPlace.image}}</a> <a
					id="2" class="tab">{{langPlace.video}}</a>
			</div>
			<div id="tabData">
				<div id="tab-data1" class="tab-data selectedTab"
					ng-controller="ImageCtrl">
					<div id="placeImage">
						<img ng-repeat="imageView in imageViews" alt=""
							title="{{imageView.caption}}" ng-click="showImage($index);"
							ng-src="<spring:message code="domain"/>/img/gallery/thumb/{{imageView.id}}.jpg" />
					</div>
					<div class="viewMore">
						<a class="action" ng-show="getMoreRq.viewMore==true" href
							ng-click="getImage()">{{langCommon.viewmore}}>></a>
					</div>

					<div id="divImgShow" align="center">
						<img id="imgShow" style="border: none;" ng-click="hideImage();"
							ng-src="<spring:message code="domain"/>/img/gallery/{{imageShow.id}}.jpg" />
						<div id="divImgCaptionShow">{{imageShow.caption}}</div>
						<div ng-controller="ReportSubmitCtrl">
							<a href="" ng-click="reportImage(imageShow.id)">{{langCommon.report}}</a>
						</div>
						<div id="divImgNevigatorShow">
							<a ng-click="showPrevImage();"><img
								title="{{langCommon.prev}}"
								ng-src="<spring:message code="domain"/>/img/res/icon/left.png" />
							</a><a ng-click="showNextImage();"><img
								title="{{langCommon.next}}"
								ng-src="<spring:message code="domain"/>/img/res/icon/right.png" />
							</a><a ng-click="hideImage();"><img title="{{langCommon.close}}"
								ng-src="<spring:message code="domain"/>/img/res/icon/close.png" />
							</a>
						</div>
					</div>

				</div>
				<div id="tab-data2" class="tab-data" ng-controller="VideoCtrl">
					<div id="placeVideo" ng-repeat="v in videos">
						<div class="video">
							<iframe title="{{v.caption}}" id="ytplayer" type="text/html"
								width="400" height="250"
								src="http://www.youtube.com/embed/{{v.href}}?autoplay=0&autohide=1&modestbranding=1&rel=0&showinfo=0&origin=http://localhost:8080"
								frameborder="0"></iframe>
							<p>{{v.caption}}</p>
							<div ng-controller="ReportSubmitCtrl">
								<a href="" ng-click="reportVideo(v.id)">{{langCommon.report}}</a>
							</div>
						</div>
					</div>
					<div class="viewMore">
						<a class="action" ng-show="getMoreRq.viewMore==true" href
							ng-click="getVideos()">{{langCommon.viewmore}}>></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>




