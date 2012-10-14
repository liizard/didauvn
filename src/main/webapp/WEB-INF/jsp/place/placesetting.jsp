<div id="divPlaceSetting" ng-controller="PlaceSettingController">
	<table class="modify">
		<tr>
			<th colspan="2">{{langCommon.manager}}</th>
		</tr>
		<tr>
			<td class="title">{{langCommon.manager}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="newManager.email" style="width: 300px;">&nbsp;&nbsp;<input
				type="button" class="button" value="{{langCommon.add}}"
				ng-click="addManager()">&nbsp;<input type="button"
				class="button" value="{{langCommon.delete}}"
				ng-click="deleteManagers()">&nbsp;&nbsp;<input type="button"
				class="button" ng-click="goPlace();"
				value="{{langCommon.cancel}} & {{langCommon.back}}" /></td>
		</tr>
		<tr>
			<td></td>
			<td class="cont">
				<table class="data">
					<tr>
						<td><b>{{langPlace.email}}</b></td>
						<td><b>{{langPlace.name}}</b>
						</td>
						<td><input type="checkbox" ng-change="updateMaster()"
							ng-model="master" />
						</td>
					</tr>
					<tr ng-repeat="manager in managers">
						<td class="cont">{{manager.email}}</td>
						<td class="cont">{{manager.name}}</td>
						<td class="cont"><input type="checkbox"
							ng-model="manager.checked">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>