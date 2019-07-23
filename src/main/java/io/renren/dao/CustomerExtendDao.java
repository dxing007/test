package io.renren.dao;

import io.renren.entity.CustomerExtendEntity;

/**
 * 客户信息扩展表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-01 12:49:29
 */
public interface CustomerExtendDao extends BaseDao<CustomerExtendEntity> {
	
	
  public	CustomerExtendEntity queryObjectByCustomerId(Integer customerId);
}
