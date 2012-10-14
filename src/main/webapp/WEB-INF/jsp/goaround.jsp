<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="inc/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="goaround">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>didau.vn</title>

<!-- CSS -->
<%@ include file="inc/css.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/goaround.css" />

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
		src="<spring:message code="domain"/>/js/lib/jquery.ui.dialog.js"></script>

	<!-- JS-APP/SERVICE -->
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/app/goaround-app.js"></script>
	<%@ include file="inc/jsservice.jsp"%>

	<!-- JS-CONTROLLER -->
	<%@ include file="inc/jscontroller.jsp"%>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/goaround-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/goaround-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/goaround/gallery-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/goaround/news-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/goaround/offerweek-controller.js"></script>

	<!-- JS-COMPONENT -->

</body>

</html>