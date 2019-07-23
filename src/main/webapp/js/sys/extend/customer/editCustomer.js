function queryCache(type) {
	// queryDictEntityByCode
	$.ajax({
		type : "POST",
		url : "../basedict/queryBaseDictMapByCodes",
		data : JSON.stringify([ "plastic_project", "info_source","customer_status" ]),
		success : function(r) {
			console.log(r);
			vm.cacheInfo = r;
		}
	});
};
$(function() {
	// vm.getInfo(3);
	queryCache();
	var regionCode = "";
// 		regionCode = GetQueryString('customerId');	
// 		vm.customerBaseInfo.input_province;
	//	alert(vm.customerExtendInfo.intentionAreaCode);
		$("#jqGrid").jqGrid({
	        url: '../hospitalinfo/list',
	        datatype: "json",
	        colModel: [			
					{ label: '选择的值', name: 'selectValue', width: 50, key: true ,hidden:true },
					{ label: '医院名称', name: 'name', width: 80  }, 			
// 					{ label: '状态 0 为启用 1 为禁用', name: 'statue', width: 80 }, 			
					{ label: '地区编码', name: 'regionCode', width: 100, formatter: function(value, options, row){
							return  getCityShowName(value);
					}},	
					
					{ label: '公立', name: 'statue', width: 40, formatter: function(value, options, row){
						return value == 0 ? 
							 '公立' : 
							 '私立';
					}},
					{ label: '医院网站', name: 'url', width: 80 
						, formatter: function(value, options, row){
							return '<a href="editCustomerInfo.html"  target="_blank" >'+value+'</a>';
						}	
					}, 			
					{ label: '返点', name: 'rebate', width: 100 }, 			
// 					{ label: '项目', name: 'project', width: 80 }, 
					{ label: '项目', name: 'project', width: 150 }, 	
					{ label: '医生信息', name: 'doctorInfo', width: 150 ,hidden:true  }, 
					{ label: '医生信息', name: 'doctorInfoIntr', width: 150  }, 
					{ label: '医生信息', name: 'describe', width: 150 ,hidden:true  }, 
					{ label: '医院描述', name: 'describeIntr', width: 200  }, 			
					{ label: '更新时间', name: 'lastUpdateTime', width: 150 }, 			
	        ],
			viewrecords: true,
	        height: 385,
	        rowNum: 10,
			rowList : [10,30,50],
	        rownumbers: true, 
	        rownumWidth: 25, 
	        autowidth:true,
	        multiselect: true,
	        pager: "#jqGridPager",
	        jsonReader : {
	            root: "page.list",
	            page: "page.currPage",
	            total: "page.totalPage",
	            records: "page.totalCount"
	        },
	        prmNames : {
	            page:"page", 
	            rows:"limit", 
	            order: "order"
	        },
	        gridComplete:function(){
	        	//隐藏grid底部滚动条
	        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
	        }
	        ,
	        loadComplete: function(xhr) {
	        	//获取所有行的id，其中“card_list”为表格名称
	        	var rowIds = jQuery("#jqGrid").jqGrid('getDataIDs');
	        	for(var k=0; k<rowIds.length; k++) {
	        	//获取对应行的数据
	        	   var curRowData = jQuery("#jqGrid").jqGrid('getRowData', rowIds[k]);
	        	   //获取对应行的所有子元素td，返回数组的形式
	        	   var cur = $("#"+rowIds[k] +">td");
	        	   if(!curRowData){
	        		   continue ;
	        	   }
	        	   //获取某个子元素并设置它的属性title
	        	   //医生描述
	        	   if(cur[10]){
	        		   var data = cur[10].setAttribute('title',curRowData.doctorInfo)
	        	   }
	        	   if( cur[12]){
	        		   //医院描述
	 	        	   var data = cur[12].setAttribute('title',curRowData.describe)
	        	   }
 	        	 
	        	}
	        }
	        
	    });
	 
});

function setCity(code, key, value2) {
	if (value2) {
		vm.customerBaseInfo[value2] = code;
	}
	vm.setCityList(code, key);
}

function formToJson(formObj) {
	var serializeObj = {};
	var array = formObj.serializeArray();
	/* var str=this.serialize(); */
	$(array).each(
			function() {
				if (serializeObj[this.name]) {
					if ($.isArray(serializeObj[this.name])) {
						serializeObj[this.name].push(this.value);
					} else {
						serializeObj[this.name] = [ serializeObj[this.name],
								this.value ];
					}
				} else {
					serializeObj[this.name] = this.value;
				}
			});
	console.log("转换出来");
	console.log(serializeObj);
	return serializeObj;
}
var vm = new Vue(
		{
			mounted: function() {
		          var vm = this;
		          setTimeout(function(){
		        	  vm.init();
			   },100);
		      },	
			el : '#rrapp',
			data : {
				isSaveing:false, //是否在保存中
				cacheInfo : {},// 缓存对象
				showList : true,
				showExtendModel : false,
				title : '',
				baseInfoPanel : true, // 控制显示基础增加是否显示
				customerBaseInfo : {},
				customerExtendInfo : {
					describe:"【联系方式】:"
				},
				selectHosp : false,// 控制选择医院
				provinceList : getProvinceList(),
				cityList : [],
				locatedCityList : [],
				bakCityList : [],
				selectHospit : [],
			// 选择的医院
			},
			methods : {
				init :function(){
				 var  customerId = GetQueryString('customerId');	
				  if(customerId){
					  vm.queryCustomerDetail(customerId);
					  vm.isSaveing = false;
				  }
				},
				query : function() {
					vm.reload();
				},
				add : function() {
					vm.showList = false;
					vm.title = "新增";
					vm.customerBaseInfo = {};
				},
				update : function(event) {
					var id = getSelectedRow();
					if (id == null) {
						return;
					}
					vm.showList = false;
					vm.title = "修改";

					vm.queryCustomerDetail(id);
				},
				saveOrUpdate : function(event) {
					console.log(validatorForm);
					var flag = validatorForm.validate("baseCustomerFormId");
					console.log("flag=" + flag);
					var url = vm.customerBaseInfo.id == null ? "../customerbaseinfo/save"
							: "../customerbaseinfo/update";
					$.ajax({
						type : "POST",
						url : url,
						data : JSON.stringify(vm.customerBaseInfo),
						success : function(r) {
							if (r.code === 0) {
								alert('操作成功', function(index) {
									vm.queryCustomerDetail(r.id);
								});
							} else {
								alert(r.msg);
							}
						}
					});
				},
				del : function(event) {
					var ids = getSelectedRows();
					if (ids == null) {
						return;
					}

					confirm('确定要删除选中的记录？', function() {
						$.ajax({
							type : "POST",
							url : "../customerbaseinfo/delete",
							data : JSON.stringify(ids),
							success : function(r) {
								if (r.code == 0) {
									alert('操作成功', function(index) {
										$("#jqGrid").trigger("reloadGrid");
									});
								} else {
									alert(r.msg);
								}
							}
						});
					});
				},
				getInfo : function(id) {
									$.get("../customerbaseinfo/info/" + id,
									function(r) {
										vm.customerBaseInfo = r.customerBaseInfo;
										vm.customerBaseInfo.input_province = r.customerBaseInfo.locatedRegionCode
												.substring(0, 2)
												+ '0000';
										setCity( vm.customerBaseInfo.input_province,'cityList');
									});
				},
				reload : function(event) {
					vm.showList = true;
					var page = $("#jqGrid").jqGrid('getGridParam', 'page');
					$("#jqGrid").jqGrid('setGridParam', {
						page : page,
						postData:{'regionCode':vm.customerExtendInfo.intentionAreaCode.substring(0,2) }
					}).trigger("reloadGrid");
				},
				selectHospital : function() {
					var rows = getSelectedRows();
					console.log(rows);
					if (rows && rows.length > 0) {
						$('#myModal').modal('hide');
						vm.selectHospit = [];
						console.log('选择中的医院信息》》》',rows);
						for ( var hId in rows) {
							var rowInfo = rows[hId].split("_");
							vm.selectHospit.push({
								hospitalId : rowInfo[0],
								name : rowInfo[1] ? rowInfo[1] : '' 
							});
						}
					}
				},
				setCityList : function(value, key, value2) {
					console.log(value);
					var _cityList = getCityList(value);
					vm[key] = [];
					for ( var k in _cityList) {
						vm[key].push(_cityList[k]);
					}
				},
				queryCustomerDetail : function(customerId) {
					vm.showExtendModel = true; // 显示详情
					vm.baseInfoPanel = false; // 隐藏
					// getInfoByCustomerId
					$.ajax({
								type : "POST",
								url : "../customerextend/getInfoByCustomerId/"+ customerId,
								success : function(r) {
									console.log(r);
									vm.customerExtendInfo = r;
									vm.customerBaseInfo = r.customerBaseInfoEntity;
									// 所在地
									if(r.customerBaseInfoEntity.locatedRegionCode){
										vm.customerBaseInfo.input_province = r.customerBaseInfoEntity.locatedRegionCode.substring(0, 2) + '0000';
										setCity(vm.customerBaseInfo.input_province,'cityList');
									}
									// 意向地区
									if(r.intentionAreaCode){
										vm.customerExtendInfo.intention_province = r.intentionAreaCode.substring(0, 2) + '0000';
										setCity(vm.customerExtendInfo.intention_province,'locatedCityList');
									}
									// 悲选地区
									if(r.bakAreaCode){
										vm.customerExtendInfo.bak_province = r.bakAreaCode.substring(0, 2)+ '0000';
										setCity(vm.customerExtendInfo.bak_province,'bakCityList');
									}
									if (!vm.customerExtendInfo||!vm.customerExtendInfo.describe) {
										//vm.customerExtendInfo.describe = "【联系方式/时间 】:"
										 UE.getEditor('editor').setContent('<p>'+
												   '【联系方式/时间 】: <strong><span style="color:#ff0000;">方便接听电话</span></strong>'+
												   '</p>'+
												   '<p>'+
												   '【联系方式/时间 】：近期'+
												   '</p>'+
												   '<p>'+
												   '【预算】：一万以内'+
												   '</p>'+
												   '<p>'+
												   '【在意  】：名气、价格、环境、服务、规模、技术、口碑'+
												   '</p>'+
												   '<p>'+
												   '【咨询/面诊过的医院  】：没有面诊过'+
												   '</p>'+
												   '<p>'+
												   '【顾客情况  】：顾客年龄（是否成年），症状，需求、顾虑、意向、及需要重点强调的'+
												   '</p>'
												   );	
									}else{
										 UE.getEditor('editor').setContent(vm.customerExtendInfo.describe);
									}
								}
							});
				},
				saveExtendCustomer : function() {
					console.log(vm.customerExtendInfo);
					if(!vm.isSaveing){
						alert("亲 不要操作太频繁了！");
					}else{
						vm.isSaveing = true;
					}
					var describe = UE.getEditor('editor').getContent();
					console.log(vm.selectHospit);
					vm.customerExtendInfo.customerId=vm.customerBaseInfo.id;
					vm.customerExtendInfo.hospitalList = vm.selectHospit;
					vm.customerExtendInfo.describe = describe;
					console.log('发送了………………');
					console.log(vm.customerExtendInfo);
					var url = "../customerextend/save";
					$.ajax({
						type : "POST",
						url : url,
						data : JSON.stringify(vm.customerExtendInfo),
						success : function(r) {
							vm.isSaveing = false;
							if (r.code === 0) {
 								alert('保存成功', function(index) {
 									setTimeout(function(){return vm.returnCustomerList();},"500");
 								});
							} else {
								alert(r.msg);
							}
						}
					});

				},
				showHopit :function (){
					if(!vm.customerExtendInfo.intention_province){
						alert('请先选择意向地区');
						return ;
					}
					vm.reload();
					$('#myModal').modal('show');
				},
				returnCustomerList:function(){
					self.location='customerbaseinfo.html'; 
				},
				toUpdateBaseInfo:function(){
					//$('#myModal23').modal('show');
					$('#changeStateModal').modal('show');
					//self.location='add_customerl.html?id='+vm.customerBaseInfo.id; 
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
									$('#changeStateModal').modal('hide');
								});
							} else {
								alert(r.msg);
							}
						}
					});		
				} ,
				cloneCustomerBaseInfo:function(){
					vm.queryCustomerDetail(vm.customerBaseInfo.id);
					$('#changeStateModal').modal('hide');
				}
				 

			}
		});
