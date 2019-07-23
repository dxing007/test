package io.renren.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.CustomerHospitalDao;
import io.renren.entity.CustomerHospitalEntity;
import io.renren.service.CustomerHospitalService;



@Service("customerHospitalService")
public class CustomerHospitalServiceImpl implements CustomerHospitalService {
	@Autowired
	private CustomerHospitalDao customerHospitalDao;
	
	@Override
	public CustomerHospitalEntity queryObject(Integer id){
		return customerHospitalDao.queryObject(id);
	}
	
	@Override
	public List<CustomerHospitalEntity> queryList(Map<String, Object> map){
		return customerHospitalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return customerHospitalDao.queryTotal(map);
	}
	
	@Override
	public void save(CustomerHospitalEntity customerHospital){
		customerHospitalDao.save(customerHospital);
	}
	
	@Override
	public void update(CustomerHospitalEntity customerHospital){
		customerHospitalDao.update(customerHospital);
	}
	
	@Override
	public void delete(Integer id){
		customerHospitalDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		customerHospitalDao.deleteBatch(ids);
	}
	
	@Override
	public List<Map<String,Object>> queryHospitalCountInfo(Map<String,Object> parramMap) {
		return customerHospitalDao.queryHospitalCountInfo(parramMap);
	}
	@Override
	public int queryHospitalCount(Map<String, Object> parramMap) {
		return customerHospitalDao.queryHospitalCount(parramMap);
	}
	
}
