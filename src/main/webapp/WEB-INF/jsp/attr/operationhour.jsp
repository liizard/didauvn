<div id="divPlaceOpenHourEdit" ng-controller="OperationHourCtrl">
	<table>
		<tr ng-repeat="hour in hours">
			<td>{{days[hour.day-1].day}}:</td>
			<td class="cont">
				<!-- Open Hour --> <select style="width: 80px;"
				ng-model="hour.openHour" ng-Disabled="!hour.active">
					<option ng-repeat="time in times" value="{{time.id}}"
						ng-selected="time.id==hour.openHour">{{time.time}}</option>
			</select> - <!-- Close Hour --> <select style="width: 80px;"
				ng-model="hour.closeHour" ng-Disabled="!hour.active">
					<option ng-repeat="time in times" value="{{time.id}}"
						ng-selected="time.id==hour.closeHour">{{time.time}}</option>
			</select></td>

			<td>{{langCommon.remark}}: <input type="text" class="text"
				ng-Disabled="!hour.active" ng-model="hour.remark" /> <!-- Close Check -->
			<td class="cont"><input type="checkbox" ng-model="hour.active" />
			</td>
			<td><a href ng-click="applyAll($index)">{{langCommon.applyAll}}</a>
			</td>
		</tr>
		<tr>
			<td colspan="6"><input type="button" class="button"
				ng-click="saveOperationHour()" value="{{langCommon.save}}" /></td>
		</tr>
	</table>
</div>