<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="inc/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="user">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>didau.vn</title>

<!-- CSS -->
<%@ include file="inc/css.jsp"%>
<link rel="stylesheet"
	href="<spring:message code="domain"/>/css/lib/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/user.css" />

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
		src="<spring:message code="domain"/>/js/lib/jquery-ui-datepicker.min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery-ui-datepicker-regional.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload-fp.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload-ui.js"></script>

	<!-- JS-APP/SERVICE -->
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/app/user-app.js"></script>
	<%@ include file="inc/jsservice.jsp"%>

	<!-- JS-CONTROLLER -->
	<%@ include file="inc/jscontroller.jsp"%>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/user-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/attr/notify-controller.js"></script>

	<!-- JS-COMPONENT -->

</body>

</html>