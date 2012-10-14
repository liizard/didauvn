<%@ include file="../inc/taglib.jsp"%>
<div id="divGeneraUpdate" ng-controller="PlaceUpdateCtrl">
	<table class="modify">
		<tr>
			<th colspan="2">{{langPlace.generalInfo}}&nbsp;|&nbsp;<a
				class="action"
				href="<spring:message code="domain"/>/place/#/update/additional/{{placeId}}">{{langPlace.additionalInfo}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/news/{{placeId}}">{{langPlace.news}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/gallery/{{placeId}}">{{langPlace.image}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/video/{{placeId}}">{{langPlace.video}}</a>

			</th>
		</tr>
		<tr>
			<td class="title">{{langPlace.place}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="place.name" />
			</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.business}}:</td>
			<td class="cont"><select ng-model="place.businessTypeId"
				ng-options="t.id as t.name for t in businessTypes">
			</select></td>
		</tr>
		<tr>
			<td class="title">{{langPlace.location}}:</td>
			<td class="cont">
				<table>
					<tr>
						<td>{{langLocation.district}}:</td>
						<td><select ng-change="getWards();"
							ng-model="place.districtId"
							ng-options="d.id as d.name for d in districts" id="cmbDistrict">
						</select>
						</td>
					</tr>
					<tr>
						<td>{{langLocation.ward}}:</td>
						<td><select ng-change="getStreets();" ng-model="place.wardId"
							ng-options="w.id as w.name for w in wards" id="cmbWard">
						</select>
						</td>
					</tr>
					<tr>
						<td>{{langLocation.street}}:</td>
						<td><select ng-model="place.streetId"
							ng-options="s.id as s.name for s in streets" id="cmbStreet">
						</select>
						</td>
					</tr>
					<tr>
						<td>{{langLocation.address}}:</td>
						<td><input type="text" class="text" ng-model="place.address" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.description}}:</td>
			<td class="cont"><textarea id="dcrp" ng-model="place.dcrp">
						</textarea></td>
		</tr>
		<tr>
			<td class="title">{{langPlace.phone}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="place.phone" />
			</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.website}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="place.website" />
			</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.fax}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="place.fax" />
			</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.email}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="place.email" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="button" class="button" ng-click="savePlace()"
				value="{{langCommon.save}}" />&nbsp;&nbsp;<input type="button"
				class="button" ng-click="goPlace();"
				value="{{langCommon.cancel}} & {{langCommon.back}}" /></td>
		</tr>

	</table>
</div>
