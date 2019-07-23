$(function () {
    $("#jqGrid").jqGrid({
        url: '../message/list',
        datatype: "json",
        postData:{'status':0},
        colModel: [			
//			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '客户', name: 'customerName', width: 80 }, 			
//			{ label: '状态 0 未读 1 已读', name: 'status', width: 80 }, 
			{ label: '状态 ', name: 'status', width: 30, formatter: function(value, options, row){
				return value === 0 ? 
					'未读' : 
					'已读';
			}},
			{ label: '内容', name: 'content', width: 80 }, 			
			{ label: '发送人 ', name: 'senderName', width: 80 }, 			
//			{ label: '接收人', name: 'receiveId', width: 80 }, 			
			{ label: '创建日期', name: 'createTime', width: 80 }, 			
//			{ label: '创建人', name: 'createUserId', width: 80 }, 			
//			{ label: '', name: 'lastUpdateTime', width: 80 }, 			
//			{ label: '', name: 'lastUpdateUserId', width: 80 }			
			{label: '操作', name: "id", width: 60,
				formatter: function(value, options, row){
					// 	<a class="btn btn-default" @click="query">查询</a>
					return '<a class="btn btn-primary" onclick="vm.readMessage('+value+')" >&nbsp;查看</a>';
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
        multiselect: false,
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
		message: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.message = {};
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
			var url = vm.message.id == null ? "../message/save" : "../message/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.message),
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
				    url: "../message/delete",
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
			$.get("../message/info/"+id, function(r){
                vm.message = r.message;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'status':0},
				page:page
            }).trigger("reloadGrid");
		},
		readMessage:function(id){
			$.get("../message/readMessage/"+id, function(r){
                if(r.code === 0){
					alert(r.message.content, function(index){
						vm.reload();
					});
				}else{
					alert(r.msg);
				}
                
            });
		}
	}
});