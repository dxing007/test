$(function () {
	vm.init();
    $("#jqGrid").jqGrid({
        url: '../customerremark/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '客户id', name: 'customerId', width: 80 }, 			
			{ label: '下次跟进时间', name: 'nextFollowTime', width: 80 }, 			
			{ label: '最近跟进时间', name: 'lastFollowTime', width: 80 }, 			
			{ label: '是否需要下次跟进', name: 'isfollow', width: 80 }, 			
			{ label: '状态', name: 'status', width: 80 }, 			
			{ label: '权重', name: 'weight', width: 80 }, 			
			{ label: '当前情况', name: 'currentRemark', width: 80 }, 			
			{ label: '下次情况', name: 'nextRemark', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', width: 80 }, 			
			{ label: '', name: 'createUserId', width: 80 }, 			
			{ label: '', name: 'lastUpdateTime', width: 80 }, 			
			{ label: '', name: 'lastUpdateUserId', width: 80 }			
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
		customerRemark: {},
		cacheInfo:{},
	},
	methods: {
		init:function(){
			vm.queryCache();	
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.customerRemark = {};
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
			var url = vm.customerRemark.id == null ? "../customerremark/save" : "../customerremark/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.customerRemark),
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
				    url: "../customerremark/delete",
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
			$.get("../customerremark/info/"+id, function(r){
                vm.customerRemark = r.customerRemark;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
		,queryCache : function (){
			//queryDictEntityByCode
			$.ajax({
				type: "POST",
			    url: "../basedict/queryBaseDictMapByCodes",
			    data:JSON.stringify( ["remark_weight"]),
			    success: function(r){
			    	console.log(r);
			    	alert(12);
			    	vm.cacheInfo = r ;
				}
			});
		},
		
		
	}
});