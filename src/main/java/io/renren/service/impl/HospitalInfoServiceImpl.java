package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.HospitalInfoDao;
import io.renren.entity.HospitalInfoEntity;
import io.renren.service.HospitalInfoService;



@Service("hospitalInfoService")
public class HospitalInfoServiceImpl implements HospitalInfoService {
	@Autowired
	private HospitalInfoDao hospitalInfoDao;
	
	@Override
	public HospitalInfoEntity queryObject(Integer id){
		return hospitalInfoDao.queryObject(id);
	}
	
	@Override
	public List<HospitalInfoEntity> queryList(Map<String, Object> map){
		return hospitalInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hospitalInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(HospitalInfoEntity hospitalInfo){
		hospitalInfoDao.save(hospitalInfo);
	}
	
	@Override
	public void update(HospitalInfoEntity hospitalInfo){
		hospitalInfoDao.update(hospitalInfo);
	}
	
	@Override
	public void delete(Integer id){
		hospitalInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hospitalInfoDao.deleteBatch(ids);
	}
	
}
