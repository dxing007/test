package io.renren.task;

import io.renren.entity.SysUserEntity;
import io.renren.service.StatisticsCustomerDayInfoService;
import io.renren.service.SysUserService;
import io.renren.utils.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试定时任务(演示Demo，可删除)
 * 
 * testTask为spring bean的名称
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月30日 下午1:34:24
 */
@Component("testTask")
public class TestTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private StatisticsCustomerDayInfoService statisticsCustomerDayInfoService;
	
	public void test(String params){
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		SysUserEntity user = sysUserService.queryObject(1L);
		System.out.println(ToStringBuilder.reflectionToString(user));
		
	}
	
	
	public void test2(){
		logger.info("我是不带参数的test2方法，正在被执行");
	}
	/**
	 * 统计今天 客户数据
	 */
	public void statisticsCustomer(){
		Map<String,Date> parramMap = new HashMap<>();
		//每日 统计 前一天的客户数据
		Date time = new Date();
		logger.info("开始统计：" + DateUtils.format(time) +"客户数据" );
		parramMap.put("startTime", DateUtils.getStartTime(time));
		parramMap.put("endTime", DateUtils.getEndTime(time));
		int count =  statisticsCustomerDayInfoService.statisticsStartTaskCountCustomer(parramMap);
		if(count>0){
			logger.info("成功统计：" + DateUtils.format(time) +"客户数据" );
		}else{
			logger.info(  DateUtils.format(time) +"已经统计过过了" );
		}
	}
	public void statisticsEndCustomer(){
		Map<String,Date> parramMap = new HashMap<>();
		//每日 统计当天的客户数据
		Date time = new Date();
		logger.info("开始统计：" + DateUtils.format(time) +"客户数据" );
		parramMap.put("startTime", DateUtils.getStartTime(time));
		parramMap.put("endTime", DateUtils.getEndTime(time));
		int count =  statisticsCustomerDayInfoService.statisticsEndTaskCountCustomer(parramMap);
		if(count>0){
			logger.info("成功统计：" + DateUtils.format(time) +"客户数据" );
		}else{
			logger.info(  DateUtils.format(time) +"已经统计过过了" );
		}
	}
}
