<%@ include file="../inc/taglib.jsp"%>
ADMIN USER MANAGEMENT
<div id="divBanAdmin" ng-controller="BanAdminCtrl">

	<table>
		<tr>
			<th>User name</th>
			<th>Banned Date</th>
			<th>Number of Day</th>
			<th>Reason</th>
			<th>Admin</th>
			<th>Action</th>
		</tr>
		<tr ng-repeat="banView in banViews">
			<td>{{banView.user}}</td>
			<td>{{banView.banDate}}</td>
			<td>{{banView.noDay}}</td>
			<td>{{banView.reason}}</td>
			<td>{{banView.admin}}</td>
			<td><input type="button" class="button"
				ng-click="editBan(banView)" value="Edit" />&nbsp;&nbsp;<input
				type="button" class="button" ng-click="delBan(banView)"
				value="Remove" /></td>
		</tr>
	</table>
	<div class="paging" ng-show="pageList.length>1">
					Page:&nbsp; <a ng-repeat="page in pageList"
						class="pageSelected-{{page==selectedPage}}"
						ng-click="getBans(page)">{{page}}&nbsp;</a>
				</div>
	<br /> <input type="button" class="button" ng-click="addBan()"
		value="Add New" />

	<h2>{{title}}</h2>
	<table>
		<tr>
			<td class="title">User name</td>
			<td class="cont"><input type="text" class="text" ng-model="banView.user" /></td>
		</tr>
		<tr>
			<td class="title">Banned Date</td>
			<td class="cont"><input type="text" class="text" ng-model="banView.banDate" id="datepicker" /></td>
		</tr>
		<tr>
			<td class="title">Number of Day</td>
			<td class="cont"><input type="text" class="text" ng-model="banView.noDay" /></td>
		</tr>
		<tr>
			<td class="title">Reason</td>
			<td class="cont"><textarea ng-model="banView.reason"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button"
				class="button" ng-click="saveBan()" value="Save" /></td>
		</tr>
	</table>
</div>