<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="inc/taglib.jsp"%>
<!DOCTYPE html>
<html ng-app="place" xmlns:fb="http://ogp.me/ns/fb#">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>didau.vn</title>

<!-- CSS -->
<%@ include file="inc/css.jsp"%>
<link rel="stylesheet"
	href="<spring:message code="domain"/>/css/lib/jquery-ui.css" />
<link rel="stylesheet"
	href="<spring:message code="domain"/>/css/lib/jquery.ptags.default.css" />
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/place.css" />
<!-- Bootstrap CSS fixes for IE6 -->
<!--[if lt IE 7]><link rel="stylesheet" href="<spring:message code="domain"/>/css/lib/bootstrap-ie6.min.css"><![endif]-->
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet"
	href="<spring:message code="domain"/>/css/lib/jquery.fileupload-ui.css">

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
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/load-image.min.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/canvas-to-blob.min.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.iframe-transport.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload-fp.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload-ui.js"></script>
	<!--[if gte IE 8]><script src="<spring:message code="domain"/>/js/lib/fileupload/cors/jquery.xdr-transport.js"></script><![endif]-->

	<!-- JS-APP/SERVICE -->
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/app/place-app.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/service/paging-service.js"></script>
	<%@ include file="inc/jsservice.jsp"%>

	<!-- JS-CONTROLLER -->
	<%@ include file="inc/jscontroller.jsp"%>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/place-controller.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/controller/attr/wow-controller.js"></script>
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
		src="<spring:message code="domain"/>/js/controller/attr/report-controller.js"></script>

	<!-- JS-COMPONENT -->
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/nicEdit.js"></script>
	<script type="text/javascript" src="http://www.youtube.com/player_api"></script>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/comp/map.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/locale.js"></script>


	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/vi_VN/all.js#xfbml=1&appId=279086642209963";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>



</body>
</html>
