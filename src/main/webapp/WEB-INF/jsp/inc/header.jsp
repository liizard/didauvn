<div id="header">
	<div style="clear: both; height: 50px;">
		<div id="logo" title="">
			<img alt="" src="<spring:message code="domain"/>/img/res/logo2.png">
		</div>
		<div id="menu">
			<a onclick="showSearch();" title="{{langCommon.search}}"><img
				alt="" src="<spring:message code="domain"/>/img/res/icon/search.png">
			</a><a href="<spring:message code="domain"/>/goaround/"
				title="{{langCommon.goaround}}"><img id="iconCat" alt=""
				src="<spring:message code="domain"/>/img/res/icon/cat.png"> </a><a
				href="<spring:message code="domain"/>/home/"
				title="{{langCommon.home}}"><img alt=""
				src="<spring:message code="domain"/>/img/res/icon/home.png"> </a><a
				title="{{langCommon.visitedPlace}}" href="javascript:void(0)"
				onclick="loadPlaceVisited()"><img id="iconPlace" alt=""
				src="<spring:message code="domain"/>/img/res/icon/place.png">
			</a>
		</div>
		<div id="user">
			<a ng-show="userSession.user.uid==0"
				href="<spring:message code="domain"/>/user/#/login"
				title="{{langCommon.login}}"><img alt=""
				src="<spring:message code="domain"/>/img/res/icon/login.png">
			</a> <a ng-show="userSession.user.uid==0"
				href="<spring:message code="domain"/>/user/#/register"
				title="{{langCommon.register}}"><img alt=""
				src="<spring:message code="domain"/>/img/res/icon/signup.png">
			</a><a ng-show="userSession.user.uid>0"
				href="<spring:message code="domain"/>/place/#/new"
				title="{{langCommon.createplace}}"><img alt=""
				src="<spring:message code="domain"/>/img/res/icon/addPlace.png">
			</a> <a ng-show="userSession.user.uid>0"
				href="<spring:message code="domain"/>/user/#/general"
				title="{{langCommon.setting}}"><img alt=""
				src="<spring:message code="domain"/>/img/res/icon/setting.png">
			</a> <a ng-show="userSession.user.uid>0"
				href="<spring:message code="domain"/>/logout"
				title="{{langCommon.logout}}"><img alt=""
				src="<spring:message code="domain"/>/img/res/icon/logout.png">
			</a>
		</div>
	</div>
	<div id="divSearch">
		<table class="modify">
			<tr>
				<td class="cont" style="vertical-align: middle;">{{langCommon.keyword}}:</td>
				<td class="cont">
					<div class="tag" style="width: 300px;"
						onclick="$('#searchtag').focus();">
						<span ng-repeat="searchtag in placeSearchRq.tags" id="tag"
							class="ui-state-default ui-corner-all ui-ptags-tag"> <span
							class="ui-ptags-tag-text ui-ptags-tag-text-icon">{{searchtag.name}}</span>
							<a href="" ng-click="deleteTag(searchtag.id)"
							class="ui-ptags-tag-remover ui-icon ui-icon-close"></a> </span> <input
							id="searchtag" type="text" class="tag" style="width: 20px;" ng-model="tagRq.name"
							onkeydown="setWidthTag(this);" />
					</div></td>
				<td class="cont" style="vertical-align: middle;">{{langCommon.location}}:</td>
				<td class="cont">
					<div class="tag" style="width: 300px;"
						onclick="$('#searchlocation').focus();">
						<span ng-show="placeSearchRq.locationDisplay.length>0"
							style="width: 290px; text-align: left;"
							class="ui-state-default ui-corner-all ui-ptags-tag"><span
							class="ui-ptags-tag-text ui-ptags-tag-text-icon">{{placeSearchRq.locationDisplay}}</span><a
							href="" ng-click="deleteLocation();"
							class="ui-ptags-tag-remover ui-icon ui-icon-close"></a> </span> <input
							ng-show="placeSearchRq.locationDisplay.length==0"
							id="searchlocation" type="text" class="tag" style="width: 20px;"
							ng-change="searchLocation()" ng-model="locationSearchRq.location"
							onkeydown="setWidthTag(this);" />
					</div></td>
				<td valign="middle"><a id="aSearch" href="javascript:void(0)"
					ng-click="goSearch()" title="{{langCommon.search}}"><img alt=""
						src="<spring:message code="domain"/>/img/res/startSearch.png">
				</a></td>
			</tr>
		</table>
	</div>
</div>

<div id="placeVisited" class="hidden">
	<table cellpadding="0" cellspacing="0" border="0">
		<tr ng-repeat="p in placeStore">
			<td><a
				href="<spring:message code="domain"/>/place/#/view/{{p.id}}"><img
					alt="" width="50" height="50"
					ng-src="<spring:message code="domain"/>/img/gallery/thumb/{{p.avatar}}.jpg">
			</a></td>
			<td><a class="action"
				href="<spring:message code="domain"/>/place/#/view/{{p.id}}">{{p.name}}
					<span ng-show="p.tagName!=null"> &nbsp;|&nbsp;{{p.tagName}}</span>
			</a><br> {{p.address}} {{p.streetName}}, {{p.wardName}},
				{{p.districtName}}, {{p.cityName}}</td>
		</tr>
	</table>
</div>