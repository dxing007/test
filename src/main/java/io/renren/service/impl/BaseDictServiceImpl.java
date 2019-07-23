package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.BaseDictDao;
import io.renren.entity.BaseDictEntity;
import io.renren.service.BaseDictService;



@Service("baseDictService")
public class BaseDictServiceImpl implements BaseDictService {
	@Autowired
	private BaseDictDao baseDictDao;
	
	@Override
	public BaseDictEntity queryObject(String dictId){
		return baseDictDao.queryObject(dictId);
	}
	
	@Override
	public BaseDictEntity queryById(Integer id) {
		return baseDictDao.queryById(id);
	}
	
	@Override
	public List<BaseDictEntity> queryList(Map<String, Object> map){
		return baseDictDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return baseDictDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseDictEntity baseDict){
		baseDictDao.save(baseDict);
	}
	
	@Override
	public void update(BaseDictEntity baseDict){
		baseDictDao.update(baseDict);
	}
	
	@Override
	public void delete(String dictId){
		baseDictDao.delete(dictId);
	}
	
	@Override
	public void deleteBatch(Integer[] dictIds){
		baseDictDao.deleteBatch(dictIds);
	}
	
}
