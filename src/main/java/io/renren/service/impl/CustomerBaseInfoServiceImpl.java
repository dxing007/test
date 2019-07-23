package io.renren.service.impl;

import io.renren.dao.CustomerBaseInfoDao;
import io.renren.dao.CustomerExtendDao;
import io.renren.entity.CustomerBaseInfoEntity;
import io.renren.entity.CustomerExtendEntity;
import io.renren.entity.extend.CustomerListVo;
import io.renren.service.CustomerBaseInfoService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("customerBaseInfoService")
public class CustomerBaseInfoServiceImpl implements CustomerBaseInfoService {
	@Autowired
	private CustomerBaseInfoDao customerBaseInfoDao;
	
	@Override
	public CustomerBaseInfoEntity queryObject(Integer id){
		return customerBaseInfoDao.queryObject(id);
	}
	
	@Override
	public List<CustomerBaseInfoEntity> queryList(Map<String, Object> map){
		return customerBaseInfoDao.queryList(map);
	}
	
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return customerBaseInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(CustomerBaseInfoEntity customerBaseInfo){
		customerBaseInfoDao.save(customerBaseInfo);
	}
	
	@Override
	public void update(CustomerBaseInfoEntity customerBaseInfo){
		customerBaseInfoDao.update(customerBaseInfo);
	}
	
	@Override
	public void delete(Integer id){
		customerBaseInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		customerBaseInfoDao.deleteBatch(ids);
	}
	
}
