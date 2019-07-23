package io.renren.service;

import io.renren.entity.PendingTaskEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:31
 */
public interface PendingTaskService {
	
	PendingTaskEntity queryObject(Integer id);
	
	List<PendingTaskEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PendingTaskEntity pendingTask);
	
	void update(PendingTaskEntity pendingTask);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
