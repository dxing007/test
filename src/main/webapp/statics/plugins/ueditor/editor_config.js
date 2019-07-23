/**
 *  ueditor����������
 *  �������������������༭��������
 */
/**************************��ʾ********************************
 * ���б�ע�͵��������ΪUEditorĬ��ֵ��
 * �޸�Ĭ������������ȷ���Ѿ���ȫ��ȷ�ò�������ʵ��;��
 * ��Ҫ�������޸ķ�����һ����ȡ���˴�ע�ͣ�Ȼ���޸ĳɶ�Ӧ��������һ������ʵ�����༭��ʱ�����Ӧ������
 * �������༭��ʱ����ֱ��ʹ�þɰ������ļ��滻�°������ļ�,���õ��ľɰ������ļ�����ȱ���¹�������Ĳ��������½ű�����
 **************************��ʾ********************************/


(function () {
    /**
     * �༭����Դ�ļ���·����������ʾ�ĺ����ǣ��Ա༭��ʵ����ҳ��Ϊ��ǰ·����ָ��༭����Դ�ļ�����dialog���ļ��У���·����
     * ���ںܶ�ͬѧ��ʹ�ñ༭����ʱ����ֵ�����·�����⣬�˴�ǿ�ҽ�����ʹ��"�������վ��Ŀ¼�����·��"�������á�
     * "�������վ��Ŀ¼�����·��"Ҳ������б�ܿ�ͷ������"/myProject/ueditor/"������·����
     * ���վ�����ж������ͬһ�㼶��ҳ����Ҫʵ�����༭������������ͬһUEditor��ʱ�򣬴˴���URL���ܲ�������ÿ��ҳ��ı༭����
     * ��ˣ�UEditor�ṩ����Բ�ͬҳ��ı༭���ɵ������õĸ�·����������˵������Ҫʵ�����༭����ҳ�����д�����´��뼴�ɡ���Ȼ����Ҫ��˴���URL���ڶ�Ӧ�����á�
     * window.UEDITOR_HOME_URL = "/xxxx/xxxx/";
     */
    var URL;

    /**
     * �˴�����д��������UEditorС���Ա����ʹ�ã��ⲿ�����û��밴������˵����ʽ���ü��ɣ����鱣���������У��Լ��ݿ��ھ���ÿ��ҳ������window.UEDITOR_HOME_URL�Ĺ��ܡ�
     */
    var tmp = location.protocol.indexOf("file")==-1 ? location.pathname : location.href;
    URL = window.UEDITOR_HOME_URL||tmp.substr(0,tmp.lastIndexOf("\/")+1).replace("_examples/","").replace("website/","");//������������ó�ueditorĿ¼������վ�����·�����߾���·����ָ��http��ͷ�ľ���·����

    /**
     * ���������塣ע�⣬�˴������漰��·�������ñ���©URL������
     */
    window.UEDITOR_CONFIG = {

        //Ϊ�༭��ʵ�����һ��·����������ܱ�ע��
        UEDITOR_HOME_URL : URL
        //ͼƬ�ϴ�������
        ,imageUrl:URL+"jsp/imageUp.jsp"             //ͼƬ�ϴ��ύ��ַ
        ,imagePath:URL + "jsp/"                     //ͼƬ������ַ��������fixedImagePath,�����������󣬿���������
       //,imageFieldName:"upfile"                   //ͼƬ���ݵ�key,���˴��޸ģ���Ҫ�ں�̨��Ӧ�ļ��޸Ķ�Ӧ����
        ,compressSide:1                            //�ȱ�ѹ���Ļ�׼��ȷ��maxImageSideLength�����Ĳ��ն���0Ϊ������ߣ�1Ϊ���տ�ȣ�2Ϊ���ո߶�
        ,maxImageSideLength:680                    //�ϴ�ͼƬ�������ı߳����������Զ��ȱ�����,�����ž�����һ���Ƚϴ��ֵ������������image.html��

        //ͿѻͼƬ������
        ,scrawlUrl:URL+"jsp/scrawlUp.jsp"           //Ϳѻ�ϴ���ַ
        ,scrawlPath:URL+"jsp/"                            //ͼƬ������ַ��ͬimagePath

        //�����ϴ�������
        ,fileUrl:URL+"jsp/fileUp.jsp"               //�����ϴ��ύ��ַ
        ,filePath:URL + "jsp/"                   //����������ַ��ͬimagePath
        //,fileFieldName:"upfile"                    //�����ύ�ı��������˴��޸ģ���Ҫ�ں�̨��Ӧ�ļ��޸Ķ�Ӧ����

        //ͼƬ���߹���������
        ,imageManagerUrl:URL + "jsp/imageManager.jsp"       //ͼƬ���߹���Ĵ����ַ
        ,imageManagerPath:URL + "jsp/"                                    //ͼƬ������ַ��ͬimagePath

        //��Ļ��ͼ������
        ,snapscreenHost: '127.0.0.1'                                  //��Ļ��ͼ��server���ļ����ڵ���վ��ַ����ip���벻Ҫ��http://
        ,snapscreenServerUrl: URL +"jsp/imageUp.jsp" //��Ļ��ͼ��server�˱������UEditor�ķ�������Ϊ��URL +"server/upload/php/snapImgUp.php"��
        ,snapscreenPath: URL + "jsp/"
        //,snapscreenServerPort: 80                                    //��Ļ��ͼ��server�˶˿�
        //,snapscreenImgAlign: 'center'                                //��ͼ��ͼƬĬ�ϵ��Ű淽ʽ

        //wordת��������
        ,wordImageUrl:URL + "jsp/imageUp.jsp"             //wordת���ύ��ַ
        ,wordImagePath:URL + "jsp/"                       //
        //,wordImageFieldName:"upfile"                     //wordת��������˴��޸ģ���Ҫ�ں�̨��Ӧ�ļ��޸Ķ�Ӧ����

        //��ȡ��Ƶ���ݵĵ�ַ
        ,getMovieUrl:URL+"jsp/getMovie.jsp"                   //��Ƶ���ݻ�ȡ��ַ

        //�������ϵ����еĹ��ܰ�ť�������򣬿�����new�༭����ʵ��ʱѡ���Լ���Ҫ�Ĵ��¶���
        ,toolbars:[["source","undo","redo","insertunorderedlist","insertorderedlist","unlink","link","cleardoc","selectall","searchreplace","separate","pagebreak","insertimage","horizontal","anchor","spechars","blockquote","bold","italic","underline","strikethrough","forecolor","backcolor","superscript","subscript","justifyleft","justifycenter","justifyright","justifyjustify","touppercase","tolowercase","directionalityltr","directionalityrtl","indent","removeformat","formatmatch","autotypeset","pasteplain","paragraph","rowspacingbottom","rowspacingtop","lineheight","fontfamily","fontsize","imagenone","imageleft","imageright","imagecenter","inserttable","deletetable","mergeright","mergedown","splittorows","splittocols","splittocells","mergecells","insertcol","insertrow","deletecol","deleterow","insertparagraphbeforetable"]]
        //�������ڹ�������ʱ��ʾ��tooltip��ʾ,����֧���Զ����������ã�����������ֵΪ׼
        ,labelMap:{
            'anchor':'', 'undo':''
        }
        //webAppKey
        //�ٶ�Ӧ�õ�APIkey��ÿ��վ����������ȥ�ٶȹ���ע��һ��key��������ʹ��app����
        ,webAppKey:""


        //����������,Ĭ����zh-cn������Ҫ�Ļ�Ҳ����ʹ�����������ķ�ʽ���Զ��������л�����Ȼ��ǰ��������lang�ļ����´��ڶ�Ӧ�������ļ���
        //langֵҲ����ͨ���Զ���ȡ (navigator.language||navigator.browserLanguage ||navigator.userLanguage).toLowerCase()
    //,lang:'zh-cn'

        //,langPath:URL +"lang/"

        //����������,Ĭ����default������Ҫ�Ļ�Ҳ����ʹ�����������ķ�ʽ���Զ��������л�����Ȼ��ǰ��������themes�ļ����´��ڶ�Ӧ�������ļ���
        //��������Ƥ��:default,modern,gorgeous
        //,theme:'default'
        //,themePath:URL +"themes/"

        //��ʵ�����༭����ҳ���ֶ��޸ĵ�domain���˴���Ҫ����Ϊtrue
        //,customDomain:false

        //���getAllHtml���������ڶ�Ӧ��head��ǩ�����Ӹñ������á�
        //,charset:"utf-8"

        //����������Ŀ
        //,isShow : true    //Ĭ����ʾ�༭��

        //,initialContent:'��ӭʹ��ueditor!'    //��ʼ���༭��������,Ҳ����ͨ��textarea/script��ֵ������������

        //,initialFrameWidth:1000  //��ʼ���༭�����,Ĭ��1000
        //,initialFrameHeight:320  //��ʼ���༭���߶�,Ĭ��320

        //,autoClearinitialContent:true //�Ƿ��Զ�����༭����ʼ���ݣ�ע�⣺���focus��������Ϊtrue,���ҲΪ�棬��ô�༭��һ�����ͻᴥ�����³�ʼ�������ݿ�������

        //,iframeCssUrl: URL + '/themes/iframe.css' //���༭���ڲ�����һ��css�ļ�
        //,textarea:'editorValue' // �ύ��ʱ����������ȡ�༭���ύ���ݵ����õĲ�������ʵ��ʱ���Ը�����name���ԣ��Ὣname������ֵ��Ϊÿ��ʵ���ļ�ֵ������ÿ��ʵ������ʱ���������ֵ
        //,focus:true //��ʼ��ʱ���Ƿ��ñ༭����ý���true��false

        //,autoClearEmptyNode : true //getContentʱ���Ƿ�ɾ���յ�inlineElement�ڵ㣨����Ƕ�׵������

        //,fullscreen : false //�Ƿ�����ʼ��ʱ��ȫ����Ĭ�Ϲر�

        //,readonly : false /�༭����ʼ��������,�༭�����Ƿ���ֻ���ģ�Ĭ����false

        //,zIndex : 900     //�༭���㼶�Ļ���,Ĭ����900

        //,imagePopup:true      //ͼƬ�����ĸ��㿪�أ�Ĭ�ϴ�

        //,initialStyle:'body{font-size:18px}'   //�༭���ڲ���ʽ,���������ı������

        //,emotionLocalization:false //�Ƿ������鱾�ػ���Ĭ�Ϲرա���Ҫ������ȷ��emotion�ļ����°��������ṩ��images�����ļ���

        //,pasteplain:false  //�Ƿ��ı�ճ����falseΪ��ʹ�ô��ı�ճ����trueΪʹ�ô��ı�ճ��

        //,allHtmlEnabled:false //�ύ����̨�������Ƿ��������html�ַ���
        //iframeUrlMap
        //dialog���ݵ�·�� ���ᱻ�滻��URL,������һ���򿪣����������е�dialog��Ĭ��·��
        //,iframeUrlMap:{
        // 'anchor':'~/dialogs/anchor/anchor.html',
        // }
        //insertorderedlist
        //�����б����������,ֵ����ʱ֧�ֶ������Զ�ʶ��������ֵ�����Դ�ֵΪ׼
//        ,'insertorderedlist':{
//             'decimal' : '' ,         //'1,2,3...'
//             'lower-alpha' : '' ,    // 'a,b,c...'
//             'lower-roman' : '' ,    //'i,ii,iii...'
//             'upper-alpha' : '' , lang   //'A,B,C'
//             'upper-roman' : ''      //'I,II,III...'
//        }
        //insertunorderedlist
        //�����б���������ã�ֵ����ʱ֧�ֶ������Զ�ʶ��������ֵ�����Դ�ֵΪ׼
        //,insertunorderedlist : {
        //    'circle' : '',  // '�� СԲȦ'
        //    'disc' : '',    // '�� СԲ��'
        //    'square' : ''   //'�� С����'
        //}
        //fontfamily
        //�������� label����֧�ֶ������Զ��л��������ã���������ֵΪ׼
//        ,'fontfamily':[
//            { label:'',name:'songti',val:'����,SimSun'},
//            { label:'',name:'kaiti',val:'����,����_GB2312, SimKai'},
//            { label:'',name:'yahei',val:'΢���ź�,Microsoft YaHei'},
//            { label:'',name:'heiti',val:'����, SimHei'},
//            { label:'',name:'lishu',val:'����, SimLi'},
//            { label:'',name:'andaleMono',val:'andale mono'},
//            { label:'',name:'arial',val:'arial, helvetica,sans-serif'},
//            { label:'',name:'arialBlack',val:'arial black,avant garde'},
//            { label:'',name:'comicSansMs',val:'comic sans ms'},
//            { label:'',name:'impact',val:'impact,chicago'},
//            { label:'',name:'timesNewRoman',val:'times new roman'}
//          ]
        //fontsize
        //�ֺ�
        //,'fontsize':[10, 11, 12, 14, 16, 18, 20, 24, 36]
        //paragraph
        //�����ʽ ֵ����ʱ֧�ֶ������Զ�ʶ�������ã���������ֵΪ׼
        //,'paragraph':{'p':'', 'h1':'', 'h2':'', 'h3':'', 'h4':'', 'h5':'', 'h6':''}
        //rowspacingtop
        //�μ�� ֵ����ʾ��������ͬ
        //,'rowspacingtop':['5', '10', '15', '20', '25']
        //rowspacingBottom
        //�μ�� ֵ����ʾ��������ͬ
        //,'rowspacingbottom':['5', '10', '15', '20', '25']
        //lineheight
        //���ڼ�� ֵ����ʾ��������ͬ
        //,'lineheight':['1', '1.5','1.75','2', '3', '4', '5']
        //�Ҽ��˵������ݣ����Բο�plugins/contextmenu.js��ߵ�Ĭ�ϲ˵������ӣ�label����֧�ֹ��ʻ��������Դ�����Ϊ׼
//        ,contextMenu:[
//            {
//                label:'',       //��ʾ������
//                cmdName:'selectall',//ִ�е�command������������Ҽ��˵�ʱ
//                //exec��ѡ������exec�ͻ��ڵ��ʱִ�����function�����ȼ�����cmdName
//                exec:function () {
//                    //this�ǵ�ǰ�༭����ʵ��
//                    //this.ui._dialogs['inserttableDialog'].open();
//                }
//            }
//           ]
        //wordCount
        //,wordCount:1          //�Ƿ�������ͳ��
        //,maximumWords:10000       //���������ַ���
    //����ͳ����ʾ��{#count}����ǰ������{#leave}����������������ַ���,����֧�ֶ������Զ��л������򰴴�������ʾ
        //,wordCountMsg:''   //��ǰ������ {#count} ���ַ���������������{#leave} ���ַ�
        //��������������ʾ  ����֧�ֶ������Զ��л������򰴴�������ʾ
        //,wordOverFlowMsg:''    //<span style="color:red;">��������ַ������Ѿ������������ֵ�����������ܻ�ܾ����棡</span>

        //elementPathEnabled
        //�Ƿ�����Ԫ��·����Ĭ������ʾ
        //,elementPathEnabled : true
        //removeFormat
        //�����ʽʱ����ɾ���ı�ǩ������
        //removeForamtTags��ǩ
        //,removeFormatTags:'b,big,code,del,dfn,em,font,i,ins,kbd,q,samp,small,span,strike,strong,sub,sup,tt,u,var'
        //removeFormatAttributes����
        //,removeFormatAttributes:'class,style,lang,width,height,align,hspace,valign'
        //undo
        //���������˵Ĵ���,Ĭ��20
        //,maxUndoCount:20
        //��������ַ���������ֵʱ������һ���ֳ�
        //,maxInputCount:1
        //autoHeightEnabled
        // �Ƿ��Զ�����,Ĭ��true
        //,autoHeightEnabled:true
        //,minFrameWidth:800    //�༭���϶�ʱ��С���,Ĭ��800
        //,minFrameHeight:220  //�༭���϶�ʱ��С�߶�,Ĭ��220
        //autoFloatEnabled
        //�Ƿ񱣳�toolbar��λ�ò���,Ĭ��true
        //,autoFloatEnabled:true
        //����ʱ��������������������ĸ߶ȣ�����ĳЩ���й̶�ͷ����ҳ��
        //,topOffset:30
        //indentValue
        //������������,Ĭ����2em
        //,indentValue:'2em'
        //pageBreakTag
        //��ҳ��ʶ��,Ĭ����_baidu_page_break_tag_
        //,pageBreakTag:'_baidu_page_break_tag_'

        //autotypeset
        //  //�Զ��Ű����
        //  ,autotypeset:{
        //      mergeEmptyline : true,         //�ϲ�����
        //      removeClass : true,           //ȥ�������class
        //      removeEmptyline : false,      //ȥ������
        //      textAlign : "left" ,           //������Ű淽ʽ�������� left,right,center,justify ȥ��������Ա�ʾ��ִ���Ű�
        //      imageBlockLine : 'center',      //ͼƬ�ĸ�����ʽ����ռһ�о���,���Ҹ�����Ĭ��: center,left,right,none ȥ��������Ա�ʾ��ִ���Ű�
        //      pasteFilter : false,            //���ݹ������û��ճ������������
        //      clearFontSize : false,          //ȥ�����е���Ƕ�ֺţ�ʹ�ñ༭��Ĭ�ϵ��ֺ�
        //      clearFontFamily : false,        //ȥ�����е���Ƕ���壬ʹ�ñ༭��Ĭ�ϵ�����
        //      removeEmptyNode : false ,       // ȥ���սڵ�
        //      //����ȥ���ı�ǩ
        //      removeTagNames : {��ǩ����:1},
        //      indent : false,                 // ��������
        //      indentValue : '2em'             //���������Ĵ�С
        //  }

    };
})();
