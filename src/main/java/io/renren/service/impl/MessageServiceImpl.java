package io.renren.service.impl;

import io.renren.dao.MessageDao;
import io.renren.dao.PendingTaskDao;
import io.renren.entity.MessageEntity;
import io.renren.entity.SysUserEntity;
import io.renren.service.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



@Service("messageService")
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private PendingTaskDao pendingTaskDao;
	
	@Override
	public MessageEntity queryObject(Integer id){
		return messageDao.queryObject(id);
	}
	
	@Override
	public List<MessageEntity> queryList(Map<String, Object> map){
		return messageDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return messageDao.queryTotal(map);
	}
	
	@Override
	public void save(MessageEntity message,SysUserEntity sysUser){
		if(!StringUtils.isEmpty(message.getTask())){
			Map<String,Object> parmer = new HashMap<>();
			parmer.put("customerId", message.getCustomerId()); //客户信息
			parmer.put("handlerUserId", sysUser.getUserId()); //处理人
			parmer.put("oldStatus", 0); // 把未处理的改为已处理
			
			parmer.put("status", 1); //改为已办
			parmer.put("lastUpdateTime", message.getCreateTime()); //更新时间
			parmer.put("lastUpdateUserId", sysUser.getUserId()); //更新人
			pendingTaskDao.updateStatus(parmer);
		}
		messageDao.save(message);
	}
	
	@Override
	public void update(MessageEntity message){
		messageDao.update(message);
	}
	
	@Override
	public void delete(Integer id){
		messageDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		messageDao.deleteBatch(ids);
	}
	
	
}
