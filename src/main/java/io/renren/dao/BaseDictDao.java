package io.renren.dao;

import io.renren.entity.BaseDictEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:32
 */
public interface BaseDictDao extends BaseDao<BaseDictEntity> {
	BaseDictEntity queryById(Object id);
}
