$(function () {
    $("#jqGrid").jqGrid({
        url: '../hospitalinfo/list',
        datatype: "json",
        colModel: [			
//			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '医院名称', name: 'name', width: 80 }, 			
//			{ label: '状态 0 为启用 1 为禁用', name: 'statue', width: 80 }, 	
			{ label: '状态', name: 'statue', width: 40, formatter: function(value, options, row){
				return value == 0 ? 
					 '<span class="label label-success">启用</span>' : 
					 '<span class="label label-danger">禁用</span>';
			}},
//			{ label: '地区编码', name: 'regionCode', width: 80 }, 	
			{ label: '地区', name: 'regionCode', width: 60, formatter: function(value, options, row){
				return  getCityShowName(value);
			}},	
			
//			{ label: '是否公立医院 0 为公立 1 为私立', name: 'isPublic', width: 80 }, 	
			{ label: '是否公立', name: 'isPublic', width: 40, formatter: function(value, options, row){
				return value == 0 ? 
					 '公立' : 
					 '私立';
			}},
			{ label: '医院网站', name: 'url', width: 80 
				, formatter: function(value, options, row){
					return '<a href="'+value+'"  target="_blank" >'+value+'</a>';
				}	
			}, 			
			{ label: '返点', name: 'rebate', width: 70 }, 			
			{ label: '权重', name: 'weightCoefficient', width: 25 }, 			
			{ label: '项目', name: 'project', width: 80 },
			{ label: '医生详情', name: 'doctorInfo', width: 80,hidden:true }, 	
			{ label: '医生信息', name: 'doctorInfoIntr', width: 80 }, 
			{ label: '医院管理员', name: 'adminName', width: 50 }, 	
			{ label: '医院详情', name: 'describe', width: 80,hidden:true }, 	
			{ label: '医院描述', name: 'describeIntr', width: 80 
			}, 			
			{ label: '创建时间', name: 'createTime', width: 80 }, 			
			{label: '操作', name: "id", width: 60,
				formatter: function(value, options, row){
					// 	<a class="btn btn-default" @click="query">查询</a>
					return '<a class="btn btn-primary" href="edit_hospital.html?id='+value+'" >&nbsp;编辑</a>';
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
        }, 
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
        	   if(cur[11]){
        		   console.log("第11的值是：", cur[11]);
        		   cur[11].setAttribute('title',curRowData.doctorInfo);
        	   }
        	   if( cur[14]){
        		   //医院描述
        		   console.log("第14的值是：", cur[14]);
 	        	   cur[14].setAttribute('title',curRowData.describe);
        	   }
	        	 
        	}
        }
        
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		hospitalInfo: {},
		q:{},
		provinceList : getProvinceList(),
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			self.location='edit_hospital.html'; 
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
			var url = vm.hospitalInfo.id == null ? "../hospitalinfo/save" : "../hospitalinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.hospitalInfo),
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
				    url: "../hospitalinfo/delete",
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
			$.get("../hospitalinfo/info/"+id, function(r){
                vm.hospitalInfo = r.hospitalInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'name': vm.q.name,'regionCode':vm.q.regionCode ?  vm.q.regionCode.substring(0,2) :'' },
				page:page
            }).trigger("reloadGrid");
		}
	}
});