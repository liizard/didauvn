<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="footer">
	<div id="quickLink">
		<span style="float: left">Didau.vn © 2012</span> <a
			href="<spring:message code="domain"/>/home">{{langCommon.home}}</a>&nbsp;&nbsp;.&nbsp;&nbsp;<a
			href="<spring:message code="domain"/>/search">{{langCommon.search}}</a>&nbsp;&nbsp;.&nbsp;&nbsp;<a
			href="<spring:message code="domain"/>/home/#/tos">{{langCommon.tos}}</a>&nbsp;&nbsp;.&nbsp;&nbsp;<a
			href="<spring:message code="domain"/>/home/#/guide">{{langCommon.guide}}</a>&nbsp;&nbsp;.&nbsp;&nbsp;<a
			ng-click="changeLanguage('vi')">Việt Nam</a>&nbsp;|&nbsp;<a
			ng-click="changeLanguage('en')">English</a>&nbsp;&nbsp;
	</div>
</div>