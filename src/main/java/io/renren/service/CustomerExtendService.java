package io.renren.service;

import io.renren.entity.CustomerExtendEntity;
import io.renren.entity.SysUserEntity;
import io.renren.entity.extend.CustomerDetailVo;

import java.util.List;
import java.util.Map;

/**
 * 客户信息扩展表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-01 12:49:29
 */
public interface CustomerExtendService {
	
	CustomerExtendEntity queryObject(Integer id);
	
	List<CustomerExtendEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CustomerExtendEntity customerExtend);
	
	void update(CustomerExtendEntity customerExtend);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	/**
	 * 派单信息
	 * @param ustomerDetailVo
	 */
	 void saveCustomerAndHistpital(CustomerDetailVo   ustomerDetailVo,SysUserEntity sysUserEntity);
	 
	 CustomerDetailVo getInfoByCustomerId(Integer customerId,SysUserEntity sysUserEntity);
}
