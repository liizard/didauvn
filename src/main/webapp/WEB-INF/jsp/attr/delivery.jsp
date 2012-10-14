<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<div style="margin: 50px" ng-controller="DeliveryCtrl">

	<select ng-model="area" ng-options="area.cityName for area in areas">
    </select>
    {{area.cityId}}
    <input type="button" class="button" ng-click="addDeliveryArea()" value="Add"/><br/>
      {{deliveryAreas}} <br/>
      
    <div ng-repeat="deliveryArea in deliveryAreas" ng-init="cityId=deliveryArea.cityId; deliveryAreaIndex = $index">
    	<input type="checkbox" ng-change="changeMaster(deliveryArea)" ng-model="master[cityId]"/>
          {{deliveryArea.cityId}}
          {{deliveryArea.cityName}}
          <a href ng-click="deleteDeliveryArea(deliveryAreaIndex)">Delete</a>
          <br/>  
          
          <ul style="padding-left:20px">
              <li ng-repeat="district in deliveryArea.districts">
                  <input type="checkbox"  ng-model="district.active"/>
                  {{district.id}}
                  {{district.name}}
                  
                  <input type="text" style="width:200px" ng-model="district.remark">
                  <a href ng-click="applyAll(deliveryArea,district.remark)">Apply all</a>
              </li>
          </ul>           
     </div>  
     <input type="button" class ="button" ng-click="saveDelivery()" value="Save" />
</div>