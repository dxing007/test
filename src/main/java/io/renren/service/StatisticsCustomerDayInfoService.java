package io.renren.service;

import io.renren.entity.StatisticsCustomerDayInfoEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-04 15:30:26
 */
public interface StatisticsCustomerDayInfoService {
	
	StatisticsCustomerDayInfoEntity queryObject(Integer id);
	
	List<StatisticsCustomerDayInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(StatisticsCustomerDayInfoEntity statisticsCustomerDayInfo);
	
	void update(StatisticsCustomerDayInfoEntity statisticsCustomerDayInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	int statisticsStartTaskCountCustomer(Map<String,Date> map);
	int statisticsEndTaskCountCustomer(Map<String,Date> map);
}
