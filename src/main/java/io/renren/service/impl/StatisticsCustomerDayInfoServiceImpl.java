package io.renren.service.impl;

import io.renren.dao.CustomerBaseInfoDao;
import io.renren.dao.StatisticsCustomerDayInfoDao;
import io.renren.dao.SysUserDao;
import io.renren.entity.StatisticsCustomerDayInfoEntity;
import io.renren.entity.SysUserEntity;
import io.renren.service.PendingTaskService;
import io.renren.service.StatisticsCustomerDayInfoService;
import io.renren.utils.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("statisticsCustomerDayInfoService")
public class StatisticsCustomerDayInfoServiceImpl implements StatisticsCustomerDayInfoService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StatisticsCustomerDayInfoDao statisticsCustomerDayInfoDao;
	
	@Autowired
	private PendingTaskService pendingTaskService;
	
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private CustomerBaseInfoDao customerBaseInfoDao;
	
	@Override
	public StatisticsCustomerDayInfoEntity queryObject(Integer id){
		return statisticsCustomerDayInfoDao.queryObject(id);
	}
	
	@Override
	public List<StatisticsCustomerDayInfoEntity> queryList(Map<String, Object> map){
		return statisticsCustomerDayInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return statisticsCustomerDayInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(StatisticsCustomerDayInfoEntity statisticsCustomerDayInfo){
		statisticsCustomerDayInfoDao.save(statisticsCustomerDayInfo);
	}
	
	@Override
	public void update(StatisticsCustomerDayInfoEntity statisticsCustomerDayInfo){
		statisticsCustomerDayInfoDao.update(statisticsCustomerDayInfo);
	}
	
	@Override
	public void delete(Integer id){
		statisticsCustomerDayInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		statisticsCustomerDayInfoDao.deleteBatch(ids);
	}
	public int statisticsStartTaskCountCustomer(Map<String, Date> map) {
		Map<String,Object> queryUser = new HashMap<>();
		queryUser.put("roleId", 3); //查询平台有派单权限的用户
		List<SysUserEntity> userList = sysUserDao.queryList(queryUser);
		if(userList==null || userList.size()==0){
			logger.info("查询派单权限的用户数为空，无需执行统计报表");
			return 0;
		}
		for(SysUserEntity e : userList){
			//查询待办数据
			Map<String,Object> queryCount = new HashMap<>();
			queryCount.put("date",DateUtils.format(map.get("startTime"),DateUtils.DATE_PATTERN));
			queryCount.put("userId", e.getUserId());
			int count = statisticsCustomerDayInfoDao.queryTotal(queryCount);
			if(count>0){//如果今天已经统计过了。不再查询
				logger.info("今天已经统计过"+e.getName()+"的数据"); 
				continue;
			}
			//早上的时候 查询 当天待办任务
			Map<String,Object> taskMap = new HashMap<>();
			taskMap.put("handlerUserId",e.getUserId());
			taskMap.put("status",0); //已处理的任务
			taskMap.put("startTime",DateUtils.getEndTime(map.get("endTime")));//查询到当前结束时间需要处理的待办数
			int taskCount  =  pendingTaskService.queryTotal(taskMap);
			StatisticsCustomerDayInfoEntity entity = new StatisticsCustomerDayInfoEntity();
			entity.setDate(map.get("startTime"));
			entity.setUserId(e.getUserId().intValue());
			entity.setTaskCount(taskCount);
			entity.setNewCreateCount(0);
			entity.setSuccessCount(0);
			entity.setFollowUpCount(0); //更新更进数
			entity.setTransactionAmount(0.0);
			entity.setCreateTime(new Date());
			statisticsCustomerDayInfoDao.save(entity);
			//statisticsCustomerDayInfoDao.statisticsCountCustomer(map);
		}
		return 0;
		
	}
	
	public int statisticsEndTaskCountCustomer(Map<String, Date> map) {
		Map<String,Object> queryUser = new HashMap<>();
		queryUser.put("roleId", 3); //查询平台有派单权限的用户
		List<SysUserEntity> userList = sysUserDao.queryList(queryUser);
		if(userList==null || userList.size()==0){
			logger.info("查询派单权限的用户数为空，无需执行统计报表");
			return 0;
		}
		for(SysUserEntity e : userList){
			Map<String,Object> queryCount = new HashMap<>();
			queryCount.put("date", map.get("startTime"));
			queryCount.put("userId", e.getUserId());
			
			List<StatisticsCustomerDayInfoEntity> list = statisticsCustomerDayInfoDao.queryList(queryCount);
			if(list==null || list.size()==0){
				logger.info("用户"+e.getName()+" 无待办任务，无需执行统计报表");
				continue; 
			}	
			//查询待办数据
			//早上的时候 查询 当天待办任务
			Map<String,Object> taskMap = new HashMap<>();
			taskMap.put("handlerUserId",e.getUserId());
			taskMap.put("status",0); //未处理的
			taskMap.put("startTime",DateUtils.getEndTime(map.get("endTime")));//查询到当前结束时间需要处理的待办数
			int taskCount  =  pendingTaskService.queryTotal(taskMap);
			//统计今日创建数量
			Map<String,Object> queryCreateCount = new HashMap<>();
			queryCreateCount.put("startTime", map.get("startTime"));  
			queryCreateCount.put("endTime", map.get("endTime"));  
			int createCount =  customerBaseInfoDao.queryTotal(queryCreateCount);
			//更新任务
			StatisticsCustomerDayInfoEntity entity =list.get(0);
			entity.setNewCreateCount(createCount);
			entity.setFollowUpCount(entity.getTaskCount()  - taskCount); //跟进数 为今天任务数 - 去已经处理的
			entity.setLastUpdateTime(new Date());
			statisticsCustomerDayInfoDao.update(entity);
		}
		return 0;
		
	}
	
}
