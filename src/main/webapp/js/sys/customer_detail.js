function setCity(code, key, value2) {
	if (value2) {
		vm.customerBaseInfo[value2] = code;
	}
	vm.setCityList(code, key);
}

 

var vm = new Vue({
			el : '#rrapp',
			mounted: function() {
		          var vm = this;
		          setTimeout(function(){
						 vm.init();
		          },100);
		      },
			data : {
				cacheInfo : {},// 缓存对象
				customerBaseInfo:{},
				customerExtendInfo:{},
				message:'',
				hospitalList:[],
				messageMap:{},
				isAdmin:false, //是否为跟单人员操作
				isShowEarnestMoney:false, //是否能为定金信息
				isShowTurnoverAmount:false,//是否显示成交金额
				customerRemark:{}, //备注信息
				provinceList : getProvinceList(),
				cityList:[],
				userType:'',
				earnestMoney:'',
				statusList:[
				           {name:"已跟进",
				            value:"30"
				           },
				           {name:"已预约",
					            value: "35"
					       },
				           {name:"已上门",
					            value: "40"
					       },
				           {name:"交定金",
					        value:"50"
					       },
					       {name:"已成交",
						        value:"60"
						   }
				          ]
				,
				statusInfo:{
					
				},
				type:'',
			},
			methods : {
				init :function(){
					var customerId = GetQueryString('id');
					vm.queryCustomerDetail(customerId);
					vm.type = GetQueryString('type');
					this.queryCache();
				},
				queryCustomerDetail : function(customerId) {
					$.ajax({
								type : "POST",
								url : "../customerextend/getInfoByCustomerId/"+ customerId,
								success : function(r) {
									console.log(r);
									vm.customerExtendInfo = r;
									vm.customerBaseInfo = r.customerBaseInfoEntity;
									if(vm.customerBaseInfo){
										vm.customerBaseInfo.showLocatedRegionCode = getCityShowName(vm.customerBaseInfo.locatedRegionCode,'-');
										vm.customerExtendInfo.showIntentionAreaCode =  getCityShowName(vm.customerExtendInfo.intentionAreaCode,'-');
										vm.customerBaseInfo.input_province =vm.customerBaseInfo.locatedRegionCode.substring(0, 2) + '0000';
										setCity( vm.customerBaseInfo.input_province,'cityList');
									}
									vm.hospitalList = r.hospitalList;
									vm.userType = r.userType;
								}
					 });
				},
				getCityName: function(code){
					 console.log(code);
					 if(!code){
						 return "";
					 }
					 return getCityShowName(code);
				},
				sendMessage: function(message,distributionUserId,task){ //向跟单人员发送消息
					   if(!$.trim(message)){  
						   alert("发送信息不能为空");
					   }else{
						   var data = {
								   customerId:vm.customerExtendInfo.customerId,
								   content:message,
								   receiveId: distributionUserId, //接收人为派单人员
								   task:task
						   };
						    $.ajax({
								type : "POST",
								url : "../message/save/",
								data: JSON.stringify(data),
								success : function(r) {
									console.log(r);
									if(r.code!='0'){
										alert(r.msg);
									}else{
										alert("发送成功");
										//vm.message ="";
										vm.queryCustomerDetail(vm.customerBaseInfo.id);
									}
									
								}
						   	});
					   }
				},
				showRemark:function (){
					self.location="testElModel.html?id="+vm.customerBaseInfo.id;
					//$('#myModal').modal('show');
				},
				saveRemark: function(){
					console.log($("#weightId").val());
					console.log($("#nextFollowTime").val());
					console.log( $("#isFollowId").attr("checked","checked").val());
					
					console.log(vm.customerRemark);
					return ;
				},
				queryCache : function (){
					//queryDictEntityByCode
					$.ajax({
						type: "POST",
					    url: "../basedict/queryBaseDictMapByCodes",
					    data:JSON.stringify( ["user_type","remark_weight","customer_status","plastic_project"]),
					    dataType: 'json',contentType:'application/json;charset=UTF-8',   
					    success: function(r){
					    	console.log(r);
					    	vm.cacheInfo = r ;
						}
					});
				},
				testMessage: function (val){
					console.log($("#weightId").val());
					alert(val);
				},
				toUpdateBaseInfo:function(){
					$('#customerBaseModal').modal('show');
				},
				tochangeStateModel:function(id,documentaryState,earnestMoney,turnoverAmount){
					vm.statusInfo.id= id ;
					vm.statusInfo.documentaryState =documentaryState;
					vm.statusInfo.earnestMoney =earnestMoney;
					vm.statusInfo.turnoverAmount =turnoverAmount;
					vm.changeCustomerStatue();
					$('#changeStateModal').modal('show');
					this.$forceUpdate();
				},
				setCityList : function(value, key, value2) {
					console.log(value);
					var _cityList = getCityList(value);
					vm[key] = [];
					for ( var k in _cityList) {
						vm[key].push(_cityList[k]);
					}
				},
				
				saveCustomerBaseInfo:function(){
					console.log(vm.customerBaseInfo);
					var url =   "../customerbaseinfo/update";
					$.ajax({
						type : "POST",
						url : url,
						data : JSON.stringify(vm.customerBaseInfo),
						success : function(r) {
							if (r.code === 0) {
								alert('保存成功', function(index) {
									vm.queryCustomerDetail(r.id);
									$('#customerBaseModal').modal('hide');
								});
							} else {
								alert(r.msg);
							}
						}
					});
					
				},
				returnMonitorList:function(){
					if(vm.type){
						self.location = vm.type+".html";
					}else{
						self.location="customer_monitor_list.html";
					}
					
				},
				updateCustomerStatue:function(){
					console.log(vm.statusInfo);
					if(!vm.statusInfo.documentaryState){
						alert('你选择跟进状态');
						return ;
					}
					if(vm.statusInfo.documentaryState==50){
						if(isNaN(vm.statusInfo.earnestMoney)){
							alert('请填写正确的定金金额');
							return ;
						}
					}
					if(vm.statusInfo.documentaryState==60){
						if(isNaN(vm.statusInfo.turnoverAmount)){
							alert('请填写正确成交金额');
							return ;
						}
					}
					$.ajax({
						type : "POST",
						url : "../customerhospital/update",
						data : JSON.stringify(vm.statusInfo),
						success : function(r) {
							if (r.code === 0) {
								alert('保存成功', function(index) {
									vm.queryCustomerDetail(vm.customerBaseInfo.id);
									$('#changeStateModal').modal('hide');
								});
							} else {
								alert(r.msg);
							}
						}
					});
				},
				//改变变量
				changeCustomerStatue:function(){
					if(vm.statusInfo.documentaryState==50){
						vm.isShowEarnestMoney = true;
					}else{
						vm.isShowEarnestMoney = false;
					}
					if(vm.statusInfo.documentaryState==60){
						vm.isShowTurnoverAmount = true;
					}else{
						vm.isShowTurnoverAmount =false;
					}
					
				},
				getCacheTypeName:function(type,value){
					   if(value){
						   for(var k in vm.cacheInfo[type]){
							   if(vm.cacheInfo[type][k].dictItemValue == value){
								   return vm.cacheInfo[type][k].dictItemName ;
							   }
						   }
					   }
					   return value;
				},
				
				
			}
		});
