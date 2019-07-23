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


var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		customerBaseInfo: {},
		customerExtend: {},
		q:{},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.customerBaseInfo = {};
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
			var url = vm.customerBaseInfo.id == null ? "../customerbaseinfo/save" : "../customerbaseinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.customerBaseInfo),
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
				    url: "../customerbaseinfo/delete",
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
			$.get("../customerbaseinfo/info/"+id, function(r){
                vm.customerBaseInfo = r.customerBaseInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'name':vm.q.name,'telephone':vm.q.telephone,'qq' : vm.q.qq},
				page:page,
				type: "POST"
				}).trigger("reloadGrid");
		},
		tt22:function (code){
			var c = getCityShowName(code);
			console.log(c);
		}
	}
});