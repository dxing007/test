var cacheInfo ={};

function queryCache (){
			$.ajax({
				type: "POST",
			    url: "../basedict/queryBaseDictMapByCodes",
			    data:JSON.stringify( ["remark_weight"]),
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

$(function () {
	queryCache();
	
    $("#jqGrid").jqGrid({
        url: '../pendingtask/list',
        datatype: "json",
        postData:{'task':'task'},
        colModel: [			
//			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '客户名称', name: 'customerName', width: 80 }, 			
			{ label: '电话号码', name: 'telephone', width: 80 }, 			
			{ label: '性别', name: 'sex', width: 25, formatter: function(value, options, row){
				return value === 0 ? 
					'女' : 
					'男';
			}},
			{ label: '下次跟进时间', name: 'nextFollowTime', width: 80 }, 			
			{ label: '最近跟进时间', name: 'lastUpdateTime', width: 80 }, 			
			
//			{ label: '状态', name: 'status', width: 80 }, 
			{ label: '状态', name: 'status', width: 25, formatter: function(value, options, row){
				return value == '0' ?  '待办' :  '已办';
			}},
			{ label: '客户状态', name: 'weight', width: 80 , formatter: function(value, options, row){
				return getCacheTypeName('remark_weight',value);;
			}},
			{ label: '创建时间', name: 'createTime', width: 80 }, 
			{label: '操作', name: "customerId", width: 60,
				formatter: function(value, options, row){
					// 	<a class="btn btn-default" @click="query">查询</a>
					return '<a class="btn btn-primary" href="customer_detail.html?id='+value+'" >&nbsp;跟进</a>';
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
		pendingTask: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.pendingTask = {};
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
			var url = vm.pendingTask.id == null ? "../pendingtask/save" : "../pendingtask/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.pendingTask),
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
				    url: "../pendingtask/delete",
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
			$.get("../pendingtask/info/"+id, function(r){
                vm.pendingTask = r.pendingTask;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		toDetail:function(id){
			
		}
	}
});