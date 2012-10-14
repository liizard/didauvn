<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="inc/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="admin">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>didau.vn</title>

<!-- CSS -->
<%@ include file="inc/css.jsp"%>

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
		src="<spring:message code="domain"/>/js/app/admin-app.js"></script>
	<%@ include file="inc/jsservice.jsp"%>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/service/paging-service.js"></script>

	<!-- JS-CONTROLLER -->
	<%@ include file="inc/jscontroller.jsp"%>
	
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/admin/admin-report-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/admin/ban-controller.js"></script>

	<!-- JS-COMPONENT -->
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery-ui-datepicker.min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery-ui-datepicker-regional.js"></script>
</body>

</html>
