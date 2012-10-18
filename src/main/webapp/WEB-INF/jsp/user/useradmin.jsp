<%@ include file="../inc/taglib.jsp"%>

<table class="modify">
	<tr>
		<th colspan="2">{{langCommon.admin}}&nbsp;|&nbsp;<a
				class="action" href="<spring:message code="domain"/>/user/#/place">{{langCommon.owner}}</a>
			&nbsp;|&nbsp;<a
			class="action" href="<spring:message code="domain"/>/user/#/manager">{{langCommon.manager}}</a>
		</th>
	</tr>
</table>

<a href="<spring:message code="domain"/>/admin/#/user">Report
	Management</a>
<br />
<a href="<spring:message code="domain"/>/admin/#/report">Ban
	Management</a>
<br />
<a href="<spring:message code="domain"/>/admin/#/user">Feedback
	Management</a>
<br />

