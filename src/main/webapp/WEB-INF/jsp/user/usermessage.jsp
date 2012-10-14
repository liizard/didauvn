<%@ include file="../inc/taglib.jsp"%>
<div id="divUserMessage" ng-controller="NotifyCtrl">
	<div id="note">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="noteArrow"><img alt=""
						src="<spring:message code="domain"/>/img/res/noteArrow.png" /></td>
				</tr>
				<tr>
					<td id="noteContent">
						<p>{{langUser.gender}} : {{currentUser.gender}}</p>
						<p>{{langUser.birthday}} : {{currentUser.birthday}}</p>
						<p>{{langUser.createdate}} : {{currentUser.createdate}}</p>
						<p ng-show="currentUser.profileFB!=null">
							<a href="{{currentUser.profileFB}}" target="_blank">{{langUser.profilefb}}</a>
						</p>
					</td>
				</tr>
			</table>
	</div>
	<table class="modify">
		<tr>
			<th colspan="2">{{langUser.notify}} &nbsp;|&nbsp;<a
				class="action" href="<spring:message code="domain"/>/user/#/general">{{langUser.profile}}</a> &nbsp;|&nbsp; <a
				class="action" href="<spring:message code="domain"/>/user/#/connect">{{langUser.connection}}</a>&nbsp;|&nbsp;<a
				class="action" href="<spring:message code="domain"/>/user/#/place">{{langCommon.place}}</a></th>
		</tr>
		<tr ng-repeat="message in messages" >
			<td class="title"><img width="45" height="45" ng-src="<spring:message code="domain"/>/img/user/{{message.avatar}}.jpg" /></td>
			<td class="cont"><a class="personPopupTrigger" rel="{{message.userId}}">{{message.username}}</a> {{message.type}} <a href="<spring:message code="domain"/>/place/#/view/{{message.placeId}}">{{message.placename}}</a>
			</br>{{message.createDate}}
			</td>
		</tr>
		<tr ng-show="currentmessage > 0">
			<td class="title"><a ng-click="getMoreMessages()">{{langCommon.viewmore}}</a></td>
		</tr>
</div>