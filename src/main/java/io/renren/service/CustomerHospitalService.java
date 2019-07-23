package io.renren.service;

import io.renren.entity.CustomerHospitalEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户派单表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-30 16:23:50
 */
public interface CustomerHospitalService {
	
	CustomerHospitalEntity queryObject(Integer id);
	
	List<CustomerHospitalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CustomerHospitalEntity customerHospital);
	
	void update(CustomerHospitalEntity customerHospital);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	public List<Map<String,Object>> queryHospitalCountInfo(Map<String,Object> parramMap);
	
	int queryHospitalCount(Map<String,Object> parramMap);
}
