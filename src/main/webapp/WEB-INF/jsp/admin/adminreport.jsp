<div ng-controller="ReportCtrl">
	<table id="example">
		<tr>
			<td>Link to</td>
			<td>Of place</td>
			<td>Type</td>
			<td>Description</td>
			<td>CreateDate</td>
			<td>Action</td>
			<td></td>
		</tr>

		<tr ng-repeat="report in reports | filter:search" ng-init="idx = $index">
			<td><a href="{{report.linkTo}}">{{report.linkTo}}</a></td>
			<td><a href="{{report.ofPlace}}">{{report.ofPlace}}</a></td>
			<td><span>{{report.reportType}}</span></td>
			<td><span>{{report.dcrp}}</span></td>
			<td><span>{{report.createDate}}</span></td>
			<td><input type="button" value="Process" class="button"
				ng-show="report.processed == false" ng-click="processItem(idx)" /></td>
			<td><input type="button" value="Delete" class="button"
				ng-click="deleteItem(idx)" /></td>
		</tr>
		<tr>
			<td colspan="7">
				<div class="paging" ng-show="pageList.length>1">
					{{langCommon.page}}:&nbsp; <a ng-repeat="page in pageList"
						class="pageSelected-{{page==selectedPage}}"
						ng-click="getReports(page)">{{page}}&nbsp;</a>
				</div>
			</td>
		</tr>
		
	</table>
	<br/>
	<div>
		<span style="margin:0 20px">Items:</span>
		<span style="margin:0 20px">Unprocessed</span>
		<input type="checkbox" ng-model="reportRq.unProcessedItemSelected" />		
		<span style="margin: 0 20px">Processed</span>
		<input type="checkbox" ng-model="reportRq.processedItemSelected" /><br/>
		<span style="margin:0 20px">Report type:</span>
		<select ng-model="reportRq.reportTypeValue" 
			ng-options="reportType.key as reportType.name for reportType in types">
       	</select>
       	{{reportRq.reportTypeValue}}
       	<br/>
       	<span style="margin:0 20px">From:</span>
       	<input type="text" class="text datepk" ng-model="reportRq.from" id="from"/>{{reportRq.from}}
		<span style="margin:0 20px">To:</span>
		<input type="text" class="text datepk" ng-model="reportRq.to" id="to"/>{{reportRq.to}}
		<br/>
		<input type="button" value="Submit" class="button" ng-click="submitReportRequest(1)">
		<input type="button" value="Clear" class="button" ng-click="clear()">
	</div>
</div>