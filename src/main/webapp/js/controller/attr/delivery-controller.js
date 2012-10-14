function DeliveryCtrl($scope, $http, $routeParams, $location) {
	$scope.placeId = $routeParams.placeId;
	// Yeu cầu cityID Ascending
	$scope.areas = [{cityId:'1',cityName:'Hồ Chí Minh',districts:[	{id:'1',name:'Quận 1',active:false,remark:''},
	                                                              	{id:'2',name:'Quận 2',active:false,remark:''},
	                                                              	{id:'3',name:'Quận 3',active:false,remark:''},
	                                                              	{id:'4',name:'Quận 4',active:false,remark:''},
	                                                              	{id:'5',name:'Quận 5',active:false,remark:''}
	                                                              	]},
						{cityId:'2',cityName:'Hà Nội',districts:[	{id:'26',name:'Quận Ba Ðình',active:false,remark:''},
						                                         	{id:'27',name:'Quận Hoàn Kiếm',active:false,remark:''},
						                                         	{id:'28',name:'Quận Tây Hồ',active:false,remark:''}
						                                         	]},
						{cityId:'3',cityName:'Cần Thơ',districts:[	{id:'41',name:'Quận Tào Lao',active:false,remark:''},
																	{id:'42',name:'Quận Từa Lưa',active:false,remark:''},
																	{id:'43',name:'Quận Tùm Lum',active:false,remark:''}
																	]},		
						{cityId:'4',cityName:'Đà Nẵng',districts:[	{id:'53',name:'Quận Hải Châu',active:false,remark:''},
																	{id:'54',name:'Quận Thanh Khê',active:false,remark:''},
																	{id:'55',name:'Quận Sơn Trà',active:false,remark:''},
																	{id:'56',name:'Quận Ngũ Hành Sơn',active:false,remark:''}
																]}
						];
						
	$scope.deliveryAreas = [{cityId:'1',cityName:'Hồ Chí Minh',districts:[	{id:'1',name:'Quận 1',active:true,remark:'50k'},
	                                                              	{id:'2',name:'Quận 2',active:false,remark:''},
	                                                              	{id:'3',name:'Quận 3',active:true,remark:'Giao hàng tận nhà cho nữ!'},
	                                                              	{id:'4',name:'Quận 4',active:false,remark:''},
	                                                              	{id:'5',name:'Quận 5',active:false,remark:''}
	                                                              	]},
	                                                              	
						{cityId:'2',cityName:'Hà Nội',districts:[	{id:'26',name:'Quận Ba Ðình',active:true,remark:'Giao hàng cho hóa đơn từ 500k'},
						                                         	{id:'27',name:'Quận Hoàn Kiếm',active:false,remark:''},
						                                         	{id:'28',name:'Quận Tây Hồ',active:true,remark:'Vui lòng cộng thêm 50k phí giao hàng'}
						                                         	]},
	
						{cityId:'4',cityName:'Đà Nẵng',districts:[	{id:'53',name:'Quận Hải Châu',active:true,remark:'Giao hàng sau 10:00 sáng'},
																	{id:'54',name:'Quận Thanh Khê',active:true,remark:'Giao hàng sau 10:00 sáng'},
																	{id:'55',name:'Quận Sơn Trà',active:true,remark:'Giao hàng sau 10:00 sáng'},
																	{id:'56',name:'Quận Ngũ Hành Sơn',active:true,remark:'Giao hàng sau 10:00 sáng'}
																]}
						];

	$scope.area = $scope.areas[0];
	$scope.master = [];
	
	$scope.applyAll = function(deliveryArea,remark) {
		for ( var i in deliveryArea.districts) {
			deliveryArea.districts[i].remark = remark;	
		}

	};
	
	$scope.changeMaster = function (deliveryArea){
		if ($scope.master[deliveryArea.cityId])
			for (var i in deliveryArea.districts) {
				deliveryArea.districts[i].active = true;
			}
		else
			for (var i in deliveryArea.districts) {
				deliveryArea.districts[i].active = false;
			}
	}
	
	$scope.addDeliveryArea = function(){
		if (!isAlreadyDelivered($scope.deliveryAreas,$scope.area)){
			$scope.deliveryAreas.push($scope.area);	
		}
	}
	
	$scope.deleteDeliveryArea = function(deliveryAreaIndex){
		$scope.deliveryAreas.splice(deliveryAreaIndex,1);
	}
}

function isAlreadyDelivered(deliveryAreas, area) {
	for ( var i in deliveryAreas) {
		if (deliveryAreas[i].cityId == area.cityId)
			return true;
	}
	return false;
}
