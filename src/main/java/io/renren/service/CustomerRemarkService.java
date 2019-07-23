package io.renren.service;

import io.renren.entity.CustomerRemarkEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:31
 */
public interface CustomerRemarkService {
	
	CustomerRemarkEntity queryObject(Integer id);
	
	List<CustomerRemarkEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CustomerRemarkEntity customerRemark);
	
	void update(CustomerRemarkEntity customerRemark);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
