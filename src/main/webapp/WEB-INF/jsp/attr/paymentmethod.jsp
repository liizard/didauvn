<div id="divPaymentMethod" ng-controller="PaymentMethodCtrl">
	<table>
		<tr>
			<td><input type="checkbox" ng-model="paymentMethods.payByCash" />&nbsp;<span>Cash</span>
			</td>
			<td><input type="checkbox" ng-model="paymentMethods.payByVisa" />&nbsp;<span>Visa</span>
			</td>
			<td><input type="checkbox"
				ng-model="paymentMethods.payByMasterCard" />&nbsp;<span>MasterCard</span>
			</td>
		</tr>
		<tr>
			<td colspan="3"><input type="button" class="button"
				ng-click="updatePaymentMethods()" value="{{langCommon.save}}" /></td>
		</tr>
	</table>
</div>