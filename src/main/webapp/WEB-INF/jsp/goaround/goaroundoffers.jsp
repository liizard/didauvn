<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../inc/taglib.jsp"%>

<div id="divCat">
	<a href="#category1"> <img alt=""
		src="<spring:message code="domain"/>/img/res/icon/cat/eat.png">
	</a> <a href="#category2"> <img alt=""
		src="<spring:message code="domain"/>/img/res/icon/cat/drink.png">
	</a> <a href="#category3"> <img alt=""
		src="<spring:message code="domain"/>/img/res/icon/cat/entertainment.png">
	</a> <a href="#category4"> <img alt=""
		src="<spring:message code="domain"/>/img/res/icon/cat/shopping.png">
	</a> <a href="#category5"> <img alt=""
		src="<spring:message code="domain"/>/img/res/icon/cat/service.png">
	</a>
</div>

<div id="goaround" ng-controller="GoAroundOfferWeekCtrl">
	<div class="GaTitle" id="posNews">
		Offers of Week&nbsp;|&nbsp;<a href="#/goaround/news">Recent News</a>
	</div>
	<div id="offerWeek" class="category" style="padding-top: 0px;">
		<div id="offerContainer" class="offerContainer"></div>
	</div>
</div>