<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="inc/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="site">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>didau.vn</title>

<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/site.css" />
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/goaround.css" />
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/search.css" />
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/place.css" />
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/welcome.css" />
<link rel="stylesheet"
	href="<spring:message code="domain"/>/css/lib/jquery-ui.css" />
<link rel="stylesheet"
	href="<spring:message code="domain"/>/css/lib/jquery.pnotify.default.css" />
<link rel="stylesheet"
	href="<spring:message code="domain"/>/css/lib/jquery.pnotify.default.icons.css" />
<link rel="stylesheet"
	href="<spring:message code="domain"/>/css/lib/jquery.ptags.default.css" />

</head>
<body ng-controller="SiteCtrl">

	<div id="wrapper">
		<div id="header">
			<div id="logo" title="">
				<a><img alt=""
					src="<spring:message code="domain"/>/img/res/logo.png"> </a>
			</div>

			<div id="menu">
				<a href="#/search" title="{{langCommon.search}}"><img
					id="iconSearch" alt=""
					src="<spring:message code="domain"/>/img/res/icon/search.png">
				</a> <a href="#/goaround" title="{{langCommon.goaround}}"><img
					id="iconCat" alt=""
					src="<spring:message code="domain"/>/img/res/icon/cat.png"> </a>
				<a href="javascript:void(0)" onclick="showWelcome();" title=""><img
					alt="" src="<spring:message code="domain"/>/img/res/icon/home.png">
				</a> <a title="{{langCommon.visitedPlace}}" href="javascript:void(0)"
					onclick="loadPlaceVisited()"> <img id="iconPlace" alt=""
					src="<spring:message code="domain"/>/img/res/icon/place.png">
				</a>
			</div>

			<div id="user">
				<a ng-show="userSession.user.uid==0" href="#/login"
					title="{{langCommon.login}}"><img alt=""
					src="<spring:message code="domain"/>/img/res/icon/login.png">
				</a> <a ng-show="userSession.user.uid==0" href="#/register"
					title="{{langCommon.register}}"><img alt=""
					src="<spring:message code="domain"/>/img/res/icon/signup.png">
				</a><a ng-show="userSession.user.uid>0" href="#/placenew"
					title="{{langCommon.createplace}}"><img alt=""
					src="<spring:message code="domain"/>/img/res/icon/addPlace.png">
				</a> <a ng-show="userSession.user.uid>0" href="#/user/general"
					title="{{langCommon.setting}}"><img alt=""
					src="<spring:message code="domain"/>/img/res/icon/setting.png">
				</a> <a ng-show="userSession.user.uid>0" href="/didauvn/logout"
					title="{{langCommon.logout}}"><img alt=""
					src="<spring:message code="domain"/>/img/res/icon/logout.png">
				</a>
			</div>
		</div>

		<div id="container" ng-view></div>
	</div>
<div id="placeVisited" class="hidden">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td class="haizArrow">
					<img alt="" src="<spring:message code="domain"/>/img/res/haizArrow.png" />
				</td>
			</tr>
			<tr ng-repeat="p in placeStore">
				<td><a href="#/place/{{p.id}}"><img alt="" width="50"
						height="50"
						ng-src="<spring:message code="domain"/>/img/gallery/thumb/{{p.avatar}}.jpg">
				</a></td>
				<td><a class="action" href="#/place/{{p.id}}">{{p.name}} <span
						ng-show="p.tagName!=null"> &nbsp;|&nbsp;{{p.tagName}}</span>
				</a><br> {{p.address}} {{p.streetName}}, {{p.wardName}},
					{{p.districtName}}, {{p.cityName}}</td>
			</tr>
		</table>
	</div>

	<div id="welcome">
		<div id="divLogo">
			<img alt="" src="<spring:message code="domain"/>/img/res/wlogo.png" />
		</div>
		<!-- <div id="searchZone">
			<table>
				<tr>
					<td>
						<div class="tag" style="width: 200px;"
							onclick="$('#searchtagW').focus();">
							<span ng-repeat="searchtag in placeSearchRq.tags" id="tag"
								class="ui-state-default ui-corner-all ui-ptags-tag"> <span
								class="ui-ptags-tag-text ui-ptags-tag-text-icon">{{searchtag.name}}</span>
								<a href="" ng-click="deleteTag(searchtag)"
								class="ui-ptags-tag-remover ui-icon ui-icon-close"></a> </span> <input
								id="searchtagW" type="text" class="tag" style="width: 20px;"
								onkeydown="setWidthTag(this);" />
						</div></td>
					<td style="width: 5px; vertical-align: middle;">{{langLocation.at}}</td>
					<td>
						<div class="tag" style="width: 200px;"
							onclick="$('#searchlocationW').focus();">
							<span class="">{{locationDisplay}}</span> <input
								id="searchlocationW" type="text" class="tag"
								style="width: 20px;" ng-change="searchLocation()"
								ng-model="locationSearchRq.location"
								onkeydown="setWidthTag(this);" />
						</div></td>
					<td style="width: 10px; vertical-align: middle;"><input
						type="button" class="button" value="{{langSearch.search}}"
						ng-click="welcomeSearchPlace()" /></td>
				</tr>
			</table>
		</div> -->
		<div id="overview" onclick="hideWelcome();">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="iconLine"><img
						"
						src="<spring:message code="domain"/>/img/res/logo.png" />
					</td>
					<td class="iconLine"><a
						href="<spring:message code="domain"/>/#goaround"> <img
							title="{{langCommon.goaround}}"
		src=" <spring:message code="domain"/>/img/res/icon/cat.png" />
					</a></td>
					<td class="iconLine"><a
						href="<spring:message code="domain"/>/#search"><img
							title="{{langCommon.search}}"
							src=" <spring:message code="domain"/>/img/res/icon/search.png" />
					</a></td>
					<td class="iconLine"><a
						href="<spring:message code="domain"/>/#login"><img
							title="{{langCommon.login}}"
							src=" <spring:message code="domain"/>/img/res/icon/login.png" />
					</a></td>
					<td class="iconLine"><a
						href="<spring:message code="domain"/>/#register"><img
							title="{{langCommon.register}}"
							src=" <spring:message code="domain"/>/img/res/icon/signup.png" />
					</a></td>
				</tr>
				<tr>
					<td>Chào mừng!<br> Bạn đang vào trang mạng địa điểm Việt
						Nam.<br> Khám phá ngay!
					<td>Bạn phân vân không biết nên đi đâu chơi? Hãy vào đây.</td>
					<td>Tìm kiếm địa điểm, thông tin các quán ăn, cửa hàng, khu
						vui chơi giải trí... nhanh và chính xác nhất.</td>
					<td>Đăng nhập để có thể ghi nhớ các địa điểm yêu thích, đánh
						giá, bình luận, rủ rê bạn bè...</td>
					<td>Bạn chưa có tài khoản? Đăng ký tại đây</td>
				</tr>
			</table>
			<div id="quickLink">
				<span style="float: left">Didau.vn © 2012</span>
				{{langCommon.guide}} &nbsp;&nbsp; . &nbsp;&nbsp; {{langCommon.home}}
				&nbsp;&nbsp; . &nbsp;&nbsp; {{langCommon.search}} &nbsp;&nbsp; .
				&nbsp;&nbsp; {{langCommon.login}} &nbsp;&nbsp; . &nbsp;&nbsp;
				{{langCommon.register}} &nbsp;&nbsp; . &nbsp;&nbsp;
				{{langCommon.language}}: <a ng-click="changeLanguage('vi')">Vietnam</a>&nbsp;|&nbsp;<a
					ng-click="changeLanguage('en')">English</a> &nbsp;&nbsp; <select
					style="display: none; width: 100px; background-color: #111111; border: 0px; color: #666666;"
					ng-model="language" ng-change="changeLanguage()">
					<option ng-repeat="l in languages" ng-selected="l.id==language"
						value="{{l.id}}">{{l.name}}</option>
				</select>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/angular.min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/angular-sanitize.min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/angular-cookies.min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.masonry.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.scrollTo-1.4.2-min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.localscroll-1.2.7-min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.imagesloaded.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.ui.core.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.ui.position.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.ui.autocomplete.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery-ui-datepicker.min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery-ui-datepicker-regional.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.ui.dialog.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.pnotify.min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/nicEdit.js"></script>
	<script type="text/javascript" src="http://www.youtube.com/player_api"></script>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.iframe-transport.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload-fp.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload-ui.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/config.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/common.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/gui.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/app/site-app.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/service/language-service.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/service/location-service.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/service/errorhandler-service.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/service/businesstype-service.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/service/security-service.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/site-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/search-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/place-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/user-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/attr/feedback-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/attr/image-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/attr/video-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/attr/operationhour-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/attr/paymentmethod-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/attr/placetag-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/attr/news-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/goaround-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/goaround/gallery-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/goaround/news-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/goaround/offerweek-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/comp/map.js"></script>
</body>

</html>