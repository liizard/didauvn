<%@ include file="../inc/taglib.jsp"%>
<div id="divAdditionalUpdate" ng-controller="PlaceAdditionalCtrl">
	<table class="modify">
		<tr>
			<th colspan="2">{{langPlace.additionalInfo}}&nbsp;|&nbsp;<a
				class="action"
				href="<spring:message code="domain"/>/place/#/update/general/{{placeId}}">{{langPlace.generalInfo}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/gallery/{{placeId}}">{{langPlace.image}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/video/{{placeId}}">{{langPlace.video}}</a>
			</th>
		</tr>
		<tr>
			<td class="title">Tags:</td>
			<td class="cont"><div id="divPlaceTagEdit"
					ng-controller="PlaceTagCtrl">
					<table>
						<tr>
							<td><input id="newtag" type="text" class="text" size="30"
								placeholder="new tag" ng-model="tagRq.name"> <input type="hidden" class="text"
								id="newid" />
							</td>
							<td></td>
						</tr>
						<tr ng-repeat="placetag in placetags">
							<td width="100%"><input type="radio" value="{{placetag.id}}"
								ng-model="tagUpdate.mainTag.id" /> {{placetag.name}}</td>
							<td><a href ng-click="deleteTag(placetag)">{{langCommon.delete}}</a>
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" class="button"
								value="{{langCommon.save}}" ng-click="saveTags()">&nbsp;&nbsp;<input
								type="button" class="button" ng-click="goPlace();"
								value="{{langCommon.cancel}} & {{langCommon.back}}" />
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.paymentmethod}}:</td>
			<td class="cont"><div id="divPaymentMethod"
					ng-controller="PaymentMethodCtrl">
					<table>
						<tr>
							<td><input type="checkbox"
								ng-model="paymentMethods.payByCash" />&nbsp;<span>Cash</span>
							</td>
							<td><input type="checkbox"
								ng-model="paymentMethods.payByVisa" />&nbsp;<span>Visa</span>
							</td>
							<td><input type="checkbox"
								ng-model="paymentMethods.payByMasterCard" />&nbsp;<span>MasterCard</span>
							</td>
						</tr>
						<tr>
							<td colspan="3"><input type="button" class="button"
								ng-click="updatePaymentMethods()" value="{{langCommon.save}}" />&nbsp;&nbsp;<input
								type="button" class="button" ng-click="goPlace();"
								value="{{langCommon.cancel}} & {{langCommon.back}}" />
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td class="title">{{langPlace.operationhour}}:</td>
			<td class="cont"><div id="divPlaceOpenHourEdit"
					ng-controller="OperationHourCtrl">
					<table>
						<tr ng-repeat="hour in hours">
							<td>{{days[hour.day-1].day}}:</td>
							<td class="cont">
								<!-- Open Hour --> <select style="width: 80px;"
								ng-model="hour.openHour" ng-Disabled="!hour.active"
								ng-options="time.id as time.time for time in times">

							</select> - <!-- Close Hour --> <select style="width: 80px;"
								ng-model="hour.closeHour" ng-Disabled="!hour.active"
								ng-options="time.id as time.time for time in times">
							</select></td>

							<td>{{langCommon.remark}}: <input type="text" class="text"
								ng-Disabled="!hour.active" ng-model="hour.remark" /> <!-- Close Check -->
							<td class="cont"><input type="checkbox"
								ng-model="hour.active" />
							</td>
							<td><a href ng-click="applyAll($index)">{{langCommon.applyAll}}</a>
							</td>
						</tr>
						<tr>
							<td colspan="6"><input type="button" class="button"
								ng-click="saveOperationHour()" value="{{langCommon.save}}" />&nbsp;&nbsp;<input
								type="button" class="button" ng-click="goPlace();"
								value="{{langCommon.cancel}} & {{langCommon.back}}" />
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</div>