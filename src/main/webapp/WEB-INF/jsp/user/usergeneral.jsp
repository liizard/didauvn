<%@ include file="../inc/taglib.jsp"%>
<div id="divUserUpdate" ng-controller="UserCtrl">
	<table class="modify">
		<tr>
			<th colspan="2">{{langUser.profile}} &nbsp;|&nbsp; <a
				class="action" href="<spring:message code="domain"/>/user/#/connect">{{langUser.connection}}</a>&nbsp;|&nbsp;<a
				class="action" href="<spring:message code="domain"/>/user/#/place">{{langCommon.place}}</a>
			</th>
		</tr>
		<tr>
			<td class="title">{{langUser.avatar}}:</td>
			<td class="cont"><img alt="{{userSession.user.name}}" width="45"
				height="45"
				ng-src="<spring:message code="domain"/>/img/user/{{userSession.user.avatar}}.jpg" />
			</td>
		</tr>
		<tr>
			<td class="title"></td>
			<td class="cont"><input id="fileupload" type="file" name="file" />
			</td>
		</tr>
		<tr ng-repeat="image in images">
			<td class="title"></td>
			<td class="cont" style="vertical-align: middle;">{{image}}</td>
		</tr>
		<tr>
			<td class="title">{{langUser.email}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="userSession.user.email" disabled="disabled" /></td>
		</tr>
		<tr>
			<td class="title">{{langUser.name}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="userSession.user.name" />
			</td>
		</tr>
		<tr>
			<td class="title">{{langUser.birthday}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="userSession.user.birthday" id="datepicker" />&nbsp;(dd/mm/yyyy)</td>
		</tr>
		<tr>
			<td class="title">{{langUser.gender}}:</td>
			<td class="cont"><input type="radio"
				ng-model="userSession.user.gender" value="male">&nbsp;{{langUser.male}}
				<input type="radio" ng-model="userSession.user.gender"
				value="female">&nbsp;{{langUser.female}}</td>
		</tr>
		<tr>
			<td></td>
			<td><a href="javascript:void(0)"
				onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">{{langUser.change}}
			</a><br> <br> <input type="button" class="button"
				ng-click="updateProfile()" value="{{langCommon.save}}" /><br>
				<br>
			</td>
		</tr>
	</table>


	<div id="light" class="white_content">
		<table class="modify">
			<tr>
				<th colspan="2">{{langUser.change}}</th>
			</tr>
			<tr>
				<td class="title">{{langUser.oldpass}}:</td>
				<td class="cont"><input type="password" class="text"
					ng-model="password.oldpass" /></td>
			</tr>
			<tr>
				<td class="title">{{langUser.newpass}}:</td>
				<td class="cont"><input type="password" class="text"
					ng-model="password.newpass" />
				</td>
			</tr>
			<tr>
				<td class="title">{{langUser.confirm}}:</td>
				<td class="cont"><input type="password" class="text"
					ng-model="password2" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button" class="button" ng-click="changePass()"
					value="{{langCommon.submit}}" />
				</td>
			</tr>
		</table>
	</div>

	<div id="fade" class="black_overlay"
		onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none';document.getElementById('light2').style.display='none'";></div>
</div>