package io.renren.dao;

import io.renren.entity.StatisticsCustomerDayInfoEntity;

import java.util.Date;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-04 15:30:26
 */
public interface StatisticsCustomerDayInfoDao extends BaseDao<StatisticsCustomerDayInfoEntity> {
	
	public int statisticsCountCustomer(Map<String, Date> map);
}
