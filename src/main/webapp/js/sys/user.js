$(function () {
	vm.init();
    $("#jqGrid").jqGrid({
        url: '../sys/user/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'userId', width: 45, key: true },
			{ label: '名称', name: 'name', width: 75 },
			{ label: '用户名', name: 'username', width: 75 },
			{ label: 'QQ', name: 'email', width: 90 },
			{ label: '手机号', name: 'mobile', width: 100 },
			{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '用户类型', name: 'userType', width: 80
				,formatter: function(value, options, row){
					// 	<a class="btn btn-default" @click="query">查询</a>
					return   vm.getCacheTypeName('user_type',value);
				}
			},  
			{ label: '医院信息', name: 'hospitalName', width: 100 },
			{ label: '创建时间', name: 'createTime', width: 80}     
			,
			{label: '操作', name: "userId", width: 80,
				formatter: function(value, options, row){
					// 	<a class="btn btn-default" @click="query">查询</a>
					return  '<a class="btn btn-primary"  onclick=vm.update('+value+') ><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>'
					+' <a class="btn btn-primary"  onclick=vm.del('+value+') ><i class="fa fa-trash-o"></i>&nbsp;删除</a>'
					;
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
		q:{
			username: null
		},
		showList: true,
		title:null,
		roleList:{},
		user:{
			status:1,
			roleIdList:[]
		},
		hospitalList:[],
		cacheInfo:{} // 缓存
	},
	methods: {
		init: function (){
			//查询缓存
			this.queryCache();
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = {};
			vm.user = {status:1,roleIdList:[]};
			
			//获取角色信息
			this.getRoleList();
			//获取医院列表
			this.getHospList();
		},
		update: function (userId) {
			//var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
			
			vm.getUser(userId);
			//获取角色信息
			this.getRoleList();
			//获取医院列表
			this.getHospList();
			
		},
		del: function (userIds) {
			//var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/user/delete",
				    data: JSON.stringify(userIds),
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
		saveOrUpdate: function (event) {
			var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.user),
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
		getUser: function(userId){
			$.get("../sys/user/info/"+userId, function(r){
				vm.user = r.user;
			});
		},
		getRoleList: function(){
			$.get("../sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
		},
		getHospList : function(){
			$.get("../hospitalinfo/list?page=1&limit=1000", function(r){
				console.log(r);
				vm.hospitalList = r.page.list;
			});
		},
		queryCache : function (){
			//queryDictEntityByCode
			$.ajax({
				type: "POST",
			    url: "../basedict/queryBaseDictMapByCodes",
			    data:JSON.stringify( ["user_type"]),
			    success: function(r){
			    	console.log(r);
			    	vm.cacheInfo = r ;
				}
			});
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
		unloadData :function (){
			this.$forceUpdate();
		}
		
	}
});