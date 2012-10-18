<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="inc/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="home">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>didau.vn</title>

<!-- CSS -->
<%@ include file="inc/css.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/home.css" />

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

	<!-- JS-APP/SERVICE -->
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/app/home-app.js"></script>
	<%@ include file="inc/jsservice.jsp"%>

	<!-- JS-CONTROLLER -->
	<%@ include file="inc/jscontroller.jsp"%>
	<!-- JS-COMPONENT -->

</body>

</html>