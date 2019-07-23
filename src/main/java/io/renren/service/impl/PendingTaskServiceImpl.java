package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.PendingTaskDao;
import io.renren.entity.PendingTaskEntity;
import io.renren.service.PendingTaskService;



@Service("pendingTaskService")
public class PendingTaskServiceImpl implements PendingTaskService {
	@Autowired
	private PendingTaskDao pendingTaskDao;
	
	@Override
	public PendingTaskEntity queryObject(Integer id){
		return pendingTaskDao.queryObject(id);
	}
	
	@Override
	public List<PendingTaskEntity> queryList(Map<String, Object> map){
		return pendingTaskDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return pendingTaskDao.queryTotal(map);
	}
	
	@Override
	public void save(PendingTaskEntity pendingTask){
		pendingTaskDao.save(pendingTask);
	}
	
	@Override
	public void update(PendingTaskEntity pendingTask){
		pendingTaskDao.update(pendingTask);
	}
	
	@Override
	public void delete(Integer id){
		pendingTaskDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		pendingTaskDao.deleteBatch(ids);
	}
	
}
