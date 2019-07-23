

var cacheInfo ={};

function queryCache (){
			$.ajax({
				type: "POST",
			    url: "../basedict/queryBaseDictMapByCodes",
			    data:JSON.stringify( ["plastic_project"]),
			    dataType: 'json',contentType:'application/json;charset=UTF-8',   
			    success: function(r){
			    	console.log(r);
			    	cacheInfo = r ;
				}
			});
}

function getCacheTypeName(type,value){
	 	  if(!value){
	 		  return "-";
	 	  }
		  if(value){
			   for(var k in cacheInfo[type]){
				   if(cacheInfo[type][k].dictItemValue == value){
					   return cacheInfo[type][k].dictItemName ;
				   }
			   }
		   }
		   return value;
}

function getHospitalInfoByKey(hopitalInfoList,key){
	var retValue = "";
	if(hopitalInfoList && hopitalInfoList.length>0){
		
		for(var h in hopitalInfoList){
			if(key=='lastUpdateTime'){ 
				 retValue +="<div style='margin-top:2px;'border:2px solid #F00;border-top:2px solid #000;'>"+ fmDate(hopitalInfoList[h][key]) +"</div>";
			}else{
				retValue +="<div style='margin-top:2px;'border:2px solid #F00;border-top:2px solid #000;'>"+ hopitalInfoList[h][key] +"</div>";
			}
		}
	}
	return retValue;
}
$(function () {
	queryCache();
	
    $("#jqGrid").jqGrid({
        url: '../customerextend/list',
        datatype: "json",
        colModel: [			
 			{ label: '客户名称', name: 'name', width: 60 }, 		
 			{ label: '性别', name: 'sex', width: 20, formatter: function(value, options, row){
				return value === 0 ? 
					'女' : 
					'男';
			}},
			{ label: '电话号码', name: 'telephone', width: 50 }, 	
			{ label: '地区编码', name: 'locatedRegionCode', width: 30, formatter: function(value, options, row){
				return  getCityShowName(value);
			}},	
//			{ label: '状态', name: 'statusName', width: 40 }, 	
			
			{ label: '信息来源', name: 'infoSource', width: 35 }, 			
//			{ label: '意向地区', name: 'intentionAreaCode', width: 80 }, 
			{ label: '意向地区', name: 'intentionAreaCode', width: 40, formatter: function(value, options, row){
				return  getCityShowName(value);
			}},		
			{ label: '备选地区', name: 'bakAreaCode', width: 40, formatter: function(value, options, row){
				return  getCityShowName(value);
			}},	
			{ label: '项目', name: 'beautyItemCode', width: 30,
				 formatter: function(value, options, row){
						return  getCacheTypeName('plastic_project',value);
				 }		
			}, 		
//			{ label: '描述', name: 'describe', width: 80 }, 	
			{ label: '创建时间', name: 'createTime', width: 30, formatter: function(value, options, row){
				return  fmDate(value);
			}},	
			//{ label: '创建时间', name: 'createTime', width: 65  }, 			
			{ label: '创建人', name: 'createUserName', width: 30 }, 			
//			{ label: '最后更新时间', name: 'lastUpdateTime', width: 90 }, 			
//			{ label: '更新人', name: 'lastUpdateUserName', width: 50 },
			{ label: '医院信息', name: 'customerHospital', width: 60, formatter: function(value, options, row){
				return  getHospitalInfoByKey(value,'hopitalName');
			}},
			{ label: '医院最新状态', name: 'customerHospital', width: 45, formatter: function(value, options, row){
				return  getHospitalInfoByKey(value,'statusName');
			}},
			{ label: '跟进日期', name: 'customerHospital', width: 40, formatter: function(value, options, row){
				return  getHospitalInfoByKey(value,'lastUpdateTime');
			}},
			{ label: '派单人员', name: 'customerHospital', width: 40, formatter: function(value, options, row){
				return  getHospitalInfoByKey(value,'createName');
			}}, 
			{label: '操作', name: "customerId", width: 40,
				formatter: function(value, options, row){
					// 	<a class="btn btn-default" @click="query">查询</a>
					return '<a class="btn btn-primary" href="customer_detail.html?id='+value+'" >&nbsp;详情</a>';
				}
			}
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
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		customerExtend: {},
		q:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.customerExtend = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.customerExtend.id == null ? "../customerextend/save" : "../customerextend/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.customerExtend),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../customerextend/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../customerextend/info/"+id, function(r){
                vm.customerExtend = r.customerExtend;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page,
                postData:{'name':vm.q.name ,'telephone':vm.q.telephone,'qq':vm.q.qq}
            }).trigger("reloadGrid");
		},
		detail:function(id){
			
		}
	}
});