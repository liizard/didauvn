<%@ include file="../inc/taglib.jsp"%>
<div id="divUserPlaceUpdate">
	<table class="modify">
		<tr>
			<th colspan="2">{{langCommon.manager}}&nbsp;|&nbsp;<a
				class="action" href="<spring:message code="domain"/>/user/#/place">{{langCommon.owner}}</a>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				&nbsp;|&nbsp;<a
				class="action" href="<spring:message code="domain"/>/user/#/admin">{{langCommon.admin}}</a>
				</sec:authorize>
			</th>
		</tr>
		<tr>
			<td class="title">{{langCommon.manager}}:</td>
			<td class="cont">
				<div ng-repeat="place in placemanagers">
					<div id="wrapperPlaceOverview">
						<div id="placeAvatar">
							<a href="<spring:message code="domain"/>place/#/view/{{place.id}}"><img
								alt="{{place.name}}" 
								ng-src="<spring:message code="domain"/>/img/gallery/thumb/{{place.avatar}}.jpg">
							</a>
						</div>
						<div id="placeOverview">
							<b>{{place.name}}<span ng-show="place.tagName!=null">
									&nbsp;|&nbsp;{{place.tagName}}</span> </b>
							<div id="placeAddress">
								<p>
									<img style="vertical-align: middle;" alt=""
										src="<spring:message code="domain"/>/img/res/icon/address.png" />
									{{place.address}} {{place.streetName}}, {{place.wardName}},
									{{place.districtName}}, {{place.cityName}}
								</p>
								<br> <a
									href="<spring:message code="domain"/>/place/#/update/general/{{place.id}}">{{langCommon.update}}</a>
							</div>
						</div>
					</div>
				</div></td>
		</tr>
	</table>

</div>