package io.renren.service;

import io.renren.entity.MessageEntity;
import io.renren.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:31
 */
public interface MessageService {
	
	MessageEntity queryObject(Integer id);
	
	List<MessageEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MessageEntity message,SysUserEntity sysUser);
	
	void update(MessageEntity message);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
