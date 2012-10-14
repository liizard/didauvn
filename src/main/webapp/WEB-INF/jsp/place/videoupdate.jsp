<%@ include file="../inc/taglib.jsp"%>
<div id="divVideoUpdate" ng-controller="VideoUpdateCtrl">
	<table class="modify">
		<tr>
			<th colspan="2">{{langPlace.video}}&nbsp;|&nbsp; <a
				class="action"
				href="<spring:message code="domain"/>/place/#/update/general/{{placeId}}">{{langPlace.generalInfo}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/additional/{{placeId}}">{{langPlace.additionalInfo}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/news/{{placeId}}">{{langPlace.news}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/gallery/{{placeId}}">{{langPlace.image}}</a>
			</th>
		</tr>
		<tr>
			<td class="title">{{langPlace.vlink}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="video.href" style="width: 400px;" /> (Ex:
				http://www.youtube.com/watch?v=bVAQ0rvtxaI&feature=g-vrec)</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.caption}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="video.caption" style="width: 400px;" /></td>
		</tr>
		<tr>
			<td></td>
			<td style="vertical-align: top;"><input type="button"
				class="button" ng-click="addVideo()" value="{{langCommon.add}}" />&nbsp;&nbsp;<input
				type="button" class="button" ng-click="goPlace();"
				value="{{langCommon.cancel}} & {{langCommon.back}}" /><br> <br>
			</td>
		</tr>

		<tr>
			<td colspan="2">
				<div class="paging" ng-show="pageList.length>1">
					{{langCommon.page}}:&nbsp; <a ng-repeat="page in pageList"
						ng-show="page!=selectedPage" ng-click="getVideos(page)">{{page}}&nbsp;</a>
				</div>
			</td>
		</tr>

		<tr>
			<td></td>
			<td>
				<div class="video" ng-repeat="v in videos">
					<div id="ytplayer{{v.id}}">
						<iframe id="ytplayer" type="text/html" width="400" height="250"
							src="http://www.youtube.com/embed/{{v.href}}?autoplay=0&autohide=1&modestbranding=1&rel=0&showinfo=0&origin=http://localhost:8080"
							frameborder="0"></iframe>
					</div>
					<br /> <input type="text" class="text" ng-model="v.caption"
						style="width: 280px;" /> <input type="button" class="button"
						ng-click="updateVideo(v)" value="{{langCommon.save}}" /> <input
						type="button" class="button" ng-click="delVideo(v)"
						value="{{langCommon.delete}}" />
				</div>
			</td>
		</tr>
	</table>

</div>