package io.renren.service.impl;

import io.renren.dao.CustomerRemarkDao;
import io.renren.entity.CustomerRemarkEntity;
import io.renren.entity.PendingTaskEntity;
import io.renren.service.CustomerRemarkService;
import io.renren.service.PendingTaskService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("customerRemarkService")
public class CustomerRemarkServiceImpl implements CustomerRemarkService {
	@Autowired
	private CustomerRemarkDao customerRemarkDao;
	
	@Autowired
	private PendingTaskService pendingTaskService;
	
	@Override
	public CustomerRemarkEntity queryObject(Integer id){
		return customerRemarkDao.queryObject(id);
	}
	
	@Override
	public List<CustomerRemarkEntity> queryList(Map<String, Object> map){
		return customerRemarkDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return customerRemarkDao.queryTotal(map);
	}
	
	@Override
	public void save(CustomerRemarkEntity customerRemark){
		
		//如果需要下次跟进生成一条待办
		if(customerRemark.getIsfollow()!=null &&customerRemark.getIsfollow().intValue()==1){
			PendingTaskEntity pt = new PendingTaskEntity();
			pt.setCustomerId(customerRemark.getCustomerId());
			pt.setNextFollowTime(customerRemark.getNextFollowTime());
			pt.setLastFollowTime(new Date());
			pt.setLastFollowTime(new Date());
			pt.setStatus("0");//设置为未读的待办
			pt.setWeight(customerRemark.getWeight());
			pt.setHandlerUserId(customerRemark.getCreateUserId());//给自已创建的待办任务
			pt.setCreateTime(customerRemark.getCreateTime());
			pt.setCreateUserId(customerRemark.getCreateUserId());
			pendingTaskService.save(pt);
		}
		
		customerRemarkDao.save(customerRemark);
	}
	
	@Override
	public void update(CustomerRemarkEntity customerRemark){
		customerRemarkDao.update(customerRemark);
	}
	
	@Override
	public void delete(Integer id){
		customerRemarkDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		customerRemarkDao.deleteBatch(ids);
	}
	
}
