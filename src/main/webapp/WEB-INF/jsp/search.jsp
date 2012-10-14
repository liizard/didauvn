<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="inc/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="search">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>didau.vn</title>

<!-- CSS -->
<%@ include file="inc/css.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/search.css" />

</head>
<body ng-controller="SiteCtrl">

	<!-- HEADER -->
	<%@ include file="inc/header.jsp"%>

	<!-- WRAPPER -->
	<%@ include file="inc/wrapper.jsp"%>

	<!-- FOOTER -->
	<%@ include file="inc/footer.jsp"%>

	<!-- JS-LIB -->
	<%@ include file="inc/jslib.jsp"%>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.ui.core.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.ui.position.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.ui.autocomplete.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.ui.dialog.js"></script>

	<!-- JS-APP/SERVICE -->
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/app/search-app.js"></script>
	<%@ include file="inc/jsservice.jsp"%>

	<!-- JS-CONTROLLER -->
	<%@ include file="inc/jscontroller.jsp"%>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/search-controller.js"></script>

	<!-- JS-COMPONENT -->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/comp/map.js"></script>

</body>

</html>