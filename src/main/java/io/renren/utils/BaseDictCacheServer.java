package io.renren.utils;

import io.renren.entity.BaseDictEntity;
import io.renren.service.BaseDictService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;

public class BaseDictCacheServer {
	   private BaseDictCacheServer() {}  
	   private static List<BaseDictEntity> baseDictEntityList = null;  
	    //静态工厂方法   
	   public static  synchronized List<BaseDictEntity> getBaseDictEntityList() {  
	         if (baseDictEntityList == null) {    
	        	 baseDictEntityList = new ArrayList<BaseDictEntity>();  
	             //获取spring bean
	        	 BaseDictService baseDictService = (BaseDictService) SpringContextUtils.getBean("baseDictService");
	        	 List<BaseDictEntity> _baseDictEntityList = baseDictService.queryList(new HashMap<String,Object>());
	        	 if(_baseDictEntityList!=null&&_baseDictEntityList.size()>0){
	        		 baseDictEntityList = _baseDictEntityList;
	        	 }//如果为空
	         }    
	        return baseDictEntityList;  
	   }  
	   /**
	    * 清绑缓存
	    */
	   public static void cleanCache(){
		   if(baseDictEntityList!=null){
			   baseDictEntityList.clear();
			   baseDictEntityList = null;
		   }
	   }
	   
	   public static List<BaseDictEntity> getDictListByCode(String code){
		   if(StringUtils.isEmpty(code)) return null;
		   List<BaseDictEntity>  retList = new ArrayList<>();
		   for(BaseDictEntity  b : getBaseDictEntityList()){
			   if(b.getDictTypeCode().equals(code)){
				   retList.add(b);
			   }
		   }
		   return  retList;
	   }
	   
	   public static Map<String,List<BaseDictEntity>>  getDictMapByCode(String[] codes){
		   if(codes==null ||codes.length==0) return null;
		   Map<String,List<BaseDictEntity>>  retMap = new HashMap<>();
		   for(String code : codes){
			   List<BaseDictEntity>  retList = new ArrayList<>();
			   for(BaseDictEntity  b : getBaseDictEntityList()){
				   if(b.getDictTypeCode().equals(code)){
					   retList.add(b);
				   }
			   }
			   retMap.put(code, retList);
			   
		   }
		   return  retMap;
	   }
	   
}
