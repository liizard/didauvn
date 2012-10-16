<div ng-controller="SiteFeedBacksCtrl">
	<table id="allFeedBacks">
		<tr>
			<td>Username</td>
			<td>CreateDate</td>
			<td>Action</td>
		</tr>
		<tr ng-repeat="siteFeedback in siteFeedbacks">
			<td>{{siteFeedback.userName}}</td>
			<td>{{siteFeedback.createDate}}</td>
			<td></td>
		</tr>
	</table>
	<div>
		{{siteFeedback.content}}
	</div>
</div>
