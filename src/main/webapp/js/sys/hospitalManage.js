$(function () {
    $("#jqGrid").jqGrid({
        url: '../customerhospital/queryHospitalCountInfo',
        datatype: "json",
       // mtype:'POST',
        colModel: [			
			//{ label: '医院id', name: 'id', width: 50, key: true },
			{ label: '医院名称', name: 'name', width: 80, sortable: false}, 			
			{ label: '客户数量', name: 'customerNum', width: 50, sortable: false }, 
			{ label: '地区', name: 'region_code', width: 60, formatter: function(value, options, row){
				return  getCityShowName(value);
			}},	
			{ label: '医院派单项目', name: 'projectNames', width: 200 , sortable: false}, 			
			{ label: '定金金额', name: 'earnestMoneySum', width: 50, sortable: false }, 			
			{ label: '成交金额', name: 'turnoverAmountSum', width: 50 , sortable: false}	,
			{ label: '最后跟单时间', name: 'lastUpdateTime', width: 50 , sortable: false}	,
			/*{ label: '', name: 'createTime', width: 80 }, 			
			{ label: '', name: 'createUserId', width: 80 }, 			
			{ label: '', name: 'lastUpdateTime', width: 80 }, 			
			{ label: '', name: 'lastUpdateUserId', width: 80 }	*/		
        ],
        
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
       // multiselect: true,
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
    
    $("#beginTime").datetimepicker({
        format: 'yyyy-mm-dd',   //时间格式是年-月-日
        language: 'zh-CN',   // 以中文显示
        minView: 2,   // 以日显示
       　	todayBtn: 1,//显示‘今日’按钮
        autoclose: true,   // 选完后自动关闭
        startView: 2   // 开始是显示到日
    });
    $("#endTime").datetimepicker({
        format: 'yyyy-mm-dd',   //时间格式是年-月-日
        language: 'zh-CN',   // 以中文显示
        minView: 2,   // 以日显示
       　	todayBtn: 1,//显示‘今日’按钮
        autoclose: true,   // 选完后自动关闭
        startView: 2   // 开始是显示到日
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		customerHospital: {},
		q:{},
		provinceList : getProvinceList()
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.customerHospital = {};
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
			var url = vm.customerHospital.id == null ? "../customerhospital/save" : "../customerhospital/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.customerHospital),
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
				    url: "../customerhospital/delete",
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
			$.get("../customerhospital/info/"+id, function(r){
                vm.customerHospital = r.customerHospital;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			//alert(page);
			var postData =vm.q;
			;
			if($("#beginTime").val()){
				postData.beginTime = $("#beginTime").val() ;
			}
			if($("#endTime").val()){
				postData.endTime = $("#endTime").val();
			}
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:postData,
				page:page 
            }).trigger("reloadGrid");
		},
		
		 
	}
});