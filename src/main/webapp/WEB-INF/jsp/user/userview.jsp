<div id="divProfile" ng-controller="ProfileCtrl">
	<table class="data">
		<tr>
			<th colspan="2">{{langUser.profile}}&nbsp;|&nbsp;<a ng-show="userprofile.id != 0"
				href="http://www.facebook.com/{{userprofile.id}}" target="_blank">{{langUser.profilefb}}</a>
			</th>
		</tr>
		<tr>
			<td class="title">{{langUser.name}}:</td>
			<td class="cont">{{userprofile.name}}</td>
		</tr>
		<tr>
			<td class="title">{{langUser.birthday}}:</td>
			<td class="cont">{{userprofile.birthday}}</td>
		</tr>
		<tr>
			<td class="title">{{langUser.gender}}:</td>
			<td class="cont">{{userprofile.gender}}</td>
		</tr>
	</table>
</div>