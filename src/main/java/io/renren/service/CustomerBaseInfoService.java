package io.renren.service;

import io.renren.entity.CustomerBaseInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户基础信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-29 16:56:55
 */
public interface CustomerBaseInfoService {
	
	CustomerBaseInfoEntity queryObject(Integer id);
	
	List<CustomerBaseInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CustomerBaseInfoEntity customerBaseInfo);
	
	void update(CustomerBaseInfoEntity customerBaseInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
