$(function () {
    $("#jqGrid").jqGrid({
        url: '../basedict/list',
        datatype: "json",
        colModel: [			
            { label: 'id', name: 'id', width: 50, key: true },
//			{ label: 'dictId', name: 'dictId', width: 50},
			{ label: '数据字典类别代码', name: 'dictTypeCode', width: 80 }, 			
			{ label: '数据字典类别名称', name: 'dictTypeName', width: 80 }, 			
			{ label: '数据字典项目名称', name: 'dictItemName', width: 80 }, 			
			{ label: '数据字典项目值', name: 'dictItemValue', width: 80 }, 			
			{ label: '数据字典项目代码(可为空)', name: 'dictItemCode', width: 80 }, 			
			{ label: '排序字段', name: 'dictSort', width: 80 }, 			
			{ label: '1:使用 0:停用', name: 'dictEnable', width: 80 }, 			
			{ label: '备注', name: 'dictMemo', width: 80 }			
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
		baseDict: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.baseDict = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			var url =  "../basedict/save"  ;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.baseDict),
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
			var dictIds = getSelectedRows();
			if(dictIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../basedict/delete",
				    data: JSON.stringify(dictIds),
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
			$.get("../basedict/infoById/"+id, function(r){
                vm.baseDict = r.baseDict;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});