<%@ include file="../inc/taglib.jsp"%>
<div id="divNewsUpdate" ng-controller="NewsUpdateCtrl">
	<table class="modify">
		<tr>
			<th colspan="2">{{langPlace.news}}&nbsp;|&nbsp;<a class="action"
				href="<spring:message code="domain"/>/place/#/update/general/{{placeId}}">{{langPlace.generalInfo}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/additional/{{placeId}}">{{langPlace.additionalInfo}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/gallery/{{placeId}}">{{langPlace.image}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/video/{{placeId}}">{{langPlace.video}}</a>
			</th>
		</tr>
		<tr>
			<td class="title"></td>
			<td class="cont"><input id="createNew" type="button"
				class="button" value="{{langCommon.add}}" ng-click="getEmptyNews()" />
			</td>
		</tr>
		<tr ng-show="news.image>0">
			<td class="title"></td>
			<td class="cont"><img alt="" width="230"
				ng-src="<spring:message code="domain"/>/img/news/{{news.image}}.jpg">
			</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.title}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="news.title" style="width: 600px" /></td>
		</tr>
		<tr>
			<td class="title">{{langPlace.description}}:</td>
			<td class="cont"><textarea id="desc" ng-model="news.desc"
					style="width: 600px; height: 100px;"></textarea></td>
		</tr>
		<tr>
			<td class="title">{{langPlace.content}}:</td>
			<td class="cont"><textarea id="newsCont" ng-model="news.cont">
						</textarea>
			</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.image}}:</td>
			<td class="cont"><input id="fileupload" type="file" name="file"
				multiple="multiple" /></td>
		</tr>
		<tr ng-repeat="image in images">
			<td class="title"></td>
			<td class="cont" style="vertical-align: middle;">{{image}}</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.promotion}}:</td>
			<td class="cont"><input type="checkbox" class="checkbox"
				ng-model="news.promotion" />
			</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.event}}:</td>
			<td class="cont"><input type="checkbox" class="checkbox"
				ng-model="news.event" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="button" class="button" ng-click="saveNews()"
				value="{{langCommon.save}}" />&nbsp;&nbsp;<input type="button"
				class="button" ng-click="goPlace();"
				value="{{langCommon.cancel}} & {{langCommon.back}}" /></td>
		</tr>
	</table>
</div>
