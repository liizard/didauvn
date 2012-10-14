<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../inc/taglib.jsp"%>

<div id="goaroundnews" ng-controller="GoAroundNewsCtrl">
	<div id="divCat">
		<a ng-repeat="cat in cats" ng-href="#category{{cat.id}}"
			id="cat{{cat.id}}" title="{{businessTypes[$index].name}}{{check}}">
			<div class="catName" style="display: table;">
				<%-- <div style="display: table-cell; vertical-align: middle;">
					<img alt=""
						ng-src="<spring:message code="domain"/>/img/res/icon/cat/{{cat.id}}.png">
				</div> --%>
				<div
					style="display: table-cell; vertical-align: middle; width: 130px; height: 30px;">{{businessTypes[$index].name}}</div>
				<div
					style="display: table-cell; vertical-align: middle; text-align: center; width: 50px; color: #aaaaaa;">
					<span
						style="padding: 3px; font-size: 10px; -moz-border-radius: 7px; -webkit-border-radius: 7px; border-radius: 7px;">20</span>
				</div>
			</div>
		</a>
	</div>
	<div id="goaround">
		<div class="GaTitle" id="posNews">
			{{langPlace.recentnews}}
			<!-- &nbsp;|&nbsp;<a href="#/goaround/offers">{{langPlace.offersweek}} -->
			</a>
		</div>
		<div ng-repeat="cat in cats">
			<div ng-show="news[cat.id].length>0">
				<div id="category{{cat.id}}" class="category">
					<div id="news{{news.id}}{{news.pageNum}}" class="news"
						ng-repeat="news in news[cat.id]">

						<div class="placeAvatar">
							<a
								href="<spring:message code="domain"/>/place/#/view/{{news.placeId}}"><img
								alt=""
								ng-src="<spring:message code="domain"/>/img/gallery/thumb/{{news.avatar}}.jpg" />
							</a>
						</div>
						<div class="placeName">
							<a
								href="<spring:message code="domain"/>/place/#/view/{{news.placeId}}">{{news.placeName}}<span
								ng-show="news.tagName!=null">
									&nbsp;|&nbsp;{{news.tagName}}</span>
							</a>
						</div>
						<div class="newsContent">
							<p class="title">{{news.title}}</p>
							{{news.dcrp}}
						</div>
						<div ng-show="news.image>0" class="newsPhoto">
							<img alt=""
								ng-src="<spring:message code="domain"/>/img/news/{{news.image}}.jpg">
						</div>

					</div>
				</div>
				<div class="getmore" ng-hide="more[cat.id] == 0"
					ng-click="getNews(cat.id)">{{langCommon.viewmore}}</div>
				<div class="seperateLine"></div>
			</div>
		</div>
	</div>
</div>

<script>
$(function(){
	if (screen.width > 1200) {
		$(".news").css('width', '240px');
		$(".newsPhoto img").css('width', '285px');
	} else {
		$(".news").css('width', '220px');
		$(".newsPhoto img").css('width', '220px');
	}
});
</script>