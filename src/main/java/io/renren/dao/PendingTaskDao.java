package io.renren.dao;

import java.util.Map;

import io.renren.entity.PendingTaskEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:31
 */
public interface PendingTaskDao extends BaseDao<PendingTaskEntity> {
	
	int updateStatus(Map<String, Object> map);
}
