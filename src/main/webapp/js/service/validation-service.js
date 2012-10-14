module.factory('validationService', [ '$rootScope', '$http',
		function($rootScope, $http) {
	
			function isExistingEmail(emails, email) {
				for ( var i in emails) {
					if (emails[i].email == email)
						return true;
				}
				return false;
			}
			
			var instance = {
				checkEmpty:  function(prop, errKey, title) {
					if (prop == null || prop=="") {
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[errKey],
							type : 'error',
						});
						throw errKey;
					}
				},
				
				checkEmail: function(email,errKey,title){
					if(email == null || email == ""){
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[errKey],
							type : 'error',
						});
						throw errKey;
					}
					var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
					if(!reg.test(email)){
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[errKey],
							type : 'error',
						});
						throw errKey;
					}
				},
				checkDuplicateEmail: function(emaillist,newemail,errKey,title){
					if (isExistingEmail(emaillist,newemail)){
						$.pnotify({
							delay : 1500,
							title : title,
							text : $rootScope.errMsg[errKey],
							type : 'error',
						});
						throw errKey;
					}
				},
				checkSmaller:function(prop,length, errKey,title){
					if (prop.length < length) {
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[errKey],
							type : 'error',
						});
						throw errKey;
					}
				},
				
				checkLarger:function(prop,length, errKey,title){
					if (prop.length > length) {
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[errKey],
							type : 'error',
						});
						throw errKey;
					}
				},
				
				checkEqual: function(prop1,prop2,errKey,title){
					if (prop1 != prop2) {
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[errKey],
							type : 'error',
						});
						throw errKey;
					}
				},
				
				checkDate: function(date,errKey,title){
					var reg = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
					var reg2 = /^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/;
					if(!reg.test(date) && !reg2.test(date)){
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[errKey],
							type : 'error',
						});
						throw errKey;
					}
				},
				
				checkId : function(id, errKey, title) {
					if(id < 1) {
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[errKey],
							type: 'error',
						});
						throw errKey;
					}
				},
				
				checkGreater : function(id, value, errKey, title) {
					if(id <= value) {
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[errKey],
							type: 'error',
						});
						throw errKey;
					}
				},
				
				checkLogin : function(errKey, title) {
					if ($rootScope.userSession.user == undefined
							|| $rootScope.userSession.user.uid == 0) {
						$.pnotify({
							delay : 4000,
							title : title,
							text : $rootScope.errMsg[errKey],
							type: 'error',
						});
						throw errKey;
					}
				}
			};

			return instance;
		} ]);