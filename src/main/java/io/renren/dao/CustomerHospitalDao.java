package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.CustomerHospitalEntity;

/**
 * 客户派单表
 * 
 * @author duxing
 * @email 163duxing@163.com
 * @date 2018-04-30 16:23:50
 */
public interface CustomerHospitalDao extends BaseDao<CustomerHospitalEntity> {
		
	public List<Map<String,Object>> queryHospitalCountInfo(Map<String,Object> parramMap);
	
	public int queryHospitalCount(Map<String,Object> parramMap);
}
