<%@ include file="../inc/taglib.jsp"%>
<table class="modify">
	<tr>
		<th colspan="2">{{langUser.connection}}&nbsp;|&nbsp;<a
			class="action" href="<spring:message code="domain"/>/user/#/general">{{langUser.profile}}</a>&nbsp;|&nbsp;<a
			class="action" href="<spring:message code="domain"/>/user/#/place">{{langCommon.place}}</a>
		</th>
	</tr>
	<c:forEach var="providerId" items="${providerIds}">
		<tr>
			<c:set var="connections" value="${connectionMap[providerId]}" />
			<td class="title">${providerId}</td>
			<td class="cont"><c:url value="/connect/${providerId}"
					var="connectUrl" /> <c:if test="${empty connections}">
					<form action="${connectUrl}" method="POST">
						<input type="hidden" name="scope"
							value="publish_stream,email,user_birthday" />
						<div class="formInfo">You are not yet connected to
							${providerId}.</div>
						<br> <input type="submit" class="button" value="Connect" />
					</form>
				</c:if> <c:if test="${not empty connections}">
					<form id="${providerId}Disconnect" method="post"
						action="${connectUrl}">
						<p>
							{{langUser.connected}} ${providerId} <a
								href="${connectionMap[providerId][0].profileUrl}">${connectionMap[providerId][0].displayName}</a>.
						</p>
						<br> <input type="submit" class="button"
							value="{{langUser.disconnect}}" /> <input type="hidden"
							name="_method" value="delete" />
					</form>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
