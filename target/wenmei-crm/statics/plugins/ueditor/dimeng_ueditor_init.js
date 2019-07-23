/***********************************************************************************
*
*����ueditor�༭���õ���js�͵���Ĭ�����õ�js
*
************************************************************************************/

(function(js){
	if(js && typeof js == "object"){
		for(var attr in js){
			document.write('<scr'+ 'ipt type="text/javascript"  src="'+js[attr]+'" charset="gbk"></scr' + 'ipt>');
		}
	}
})(["../statics/plugins/ueditor/editor_config.js?v=2013081111","../statics/plugins/ueditor/editor_all.js?v=20130810","../statics/plugins/ueditor/dimeng_ueditor.js?v=20130810"]);
