package io.renren.service;

import io.renren.entity.BaseDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:32
 */
public interface BaseDictService {
	
	BaseDictEntity queryObject(String dictId);
	
	BaseDictEntity queryById(Integer id);
	
	List<BaseDictEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BaseDictEntity baseDict);
	
	void update(BaseDictEntity baseDict);
	
	void delete(String dictId);
	
	void deleteBatch(Integer[] dictIds);
}
