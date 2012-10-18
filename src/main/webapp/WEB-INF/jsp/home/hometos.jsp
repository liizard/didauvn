<div id="divTOS">
	{{langTos.introduction}}<br>
	<br>
	<div id="bodyTos" ng-repeat="content in langTos.body">
		<p>
			<b>{{content.content_head}}</b>
		</p>
		<p>{{content.content_body}}</p>
		<br />
	</div>

</div>