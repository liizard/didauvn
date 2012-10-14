<div id="divPlaceTagEdit" ng-controller="PlaceTagCtrl">
	<table>
		<tr>
			<td><input id="newtag" type="text" class="text" size="30"
				placeholder="new tag"> <input type="hidden" class="text"
				id="newid" />
			</td>
			<td></td>
		</tr>
		<tr ng-repeat="placetag in placetags">
			<td width="100%"><input type="radio" value="{{placetag.id}}"
				ng-model="maintag.id" /> {{placetag.name}}</td>
			<td><a href ng-click="deleteTag(placetag)">{{langCommon.delete}}</a>
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" class="button"
				value="{{langCommon.save}}" ng-click="saveTags()"></td>
		</tr>
	</table>
</div>