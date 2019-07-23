

/*******************************************对ueditor做了部分封装********************************
 *DM.ui.default_config 迪蒙默认的配置，可以在实例化的时候，根据需求自定义配置信息
 *
 *详细配置请看/js/ueditor/editor_config.js
 *
 *在页面可以通过new DM.ui.Editor()或者new DM.ui.Editor({配置信息}) 创建实例 返回的是ueditor对象
 *
 *请不要去改editor_config.js配置信息，如果是要自定义整站编辑的特性在这配置整站的默认配置，如果每个页面根据需求
 *要自定义特性，可以在实例化的时候配置。
 ***********************************************************************************************/

var DM = {};//构建一个迪蒙对象

DM.ui = {
	"default_config" :{
	 		DM_EDITOR_ID:"myEditor"          //我们自己开发时默认编辑器的id,在实例化的编辑器对象的时候，可以自己制定
			,UEDITOR_HOME_URL:"/js/ueditor/" //ueditor在我们项目里的目录,实例化的时候可以自己配置
	    	,imageManagerUrl:"/getFileToUeditor.do?var="+new Date().getTime()  //默认管理图片的url
	    	,imageManagerPath:""   //默认管理图片修正的url   http://img1.taojindi.com/cms/2012/
	    	,initialFrameWidth:649    //默认初始化编辑器宽度,默认694
        	,initialFrameHeight:410   //默认初始化编辑器高度,默认320
        	,autoHeightEnabled:false
        	,autoFloatEnabled:false
        	,imagePath:"" //图片修正地址,本地上传显示图片然后编辑器显示
        	,focus:false
        	,initialContent:"",
        	initialStyle:'body{font-size:12px}'
	},
	
	"Editor" : function(config){//迪蒙实例化编辑器构造函数，返回的既不是UE.ui.Editor实例，也不是我们自身的DM.ui.Editor实例,而是baidu.editor.Editor实例
		if(config && typeof config == "object"){
			for(var attr in config){
				DM.ui.default_config[attr] = config[attr];
			}
		}
		
		//初始化编辑器
		var editor = new UE.ui.Editor(DM.ui.default_config);
		editor.render(DM.ui.default_config.DM_EDITOR_ID); 
		
		editor.ready(function(){//解决ueditor 和后台数据交互时，返回数据到编辑器，页面跳到编辑器位置的缺陷
			scroll(0,0);
		});
		
		return editor;//返回编辑器对象实例
	},
	
	"getContent":function(ueditorObj){//获取编辑器内容，DM.ui对象的静态方法
	 	ueditorObj.ready(function(){
	 		return ueditorObj.getPlainTxt();
	 	});
	},
	
	"setContent":function(ueditorObj,content){//设置编辑器内容，DM.ui对象的静态方法
	 	ueditorObj.ready(function(){
	 		ueditorObj.setContent(content);
	 	});
	}
};