package io.renren.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.renren.entity.StatisticsCustomerDayInfoEntity;
import io.renren.service.StatisticsCustomerDayInfoService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-04 15:30:26
 */
@Controller
@RequestMapping("statisticscustomerdayinfo")
public class StatisticsCustomerDayInfoController {
	@Autowired
	private StatisticsCustomerDayInfoService statisticsCustomerDayInfoService;
	
	@RequestMapping("/statisticscustomerdayinfo.html")
	public String list(){
		return "statisticscustomerdayinfo/statisticscustomerdayinfo.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("statisticscustomerdayinfo:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<StatisticsCustomerDayInfoEntity> statisticsCustomerDayInfoList = statisticsCustomerDayInfoService.queryList(map);
		int total = statisticsCustomerDayInfoService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(statisticsCustomerDayInfoList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("statisticscustomerdayinfo:info")
	public R info(@PathVariable("id") Integer id){
		StatisticsCustomerDayInfoEntity statisticsCustomerDayInfo = statisticsCustomerDayInfoService.queryObject(id);
		
		return R.ok().put("statisticsCustomerDayInfo", statisticsCustomerDayInfo);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("statisticscustomerdayinfo:save")
	public R save(@RequestBody StatisticsCustomerDayInfoEntity statisticsCustomerDayInfo){
		statisticsCustomerDayInfoService.save(statisticsCustomerDayInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("statisticscustomerdayinfo:update")
	public R update(@RequestBody StatisticsCustomerDayInfoEntity statisticsCustomerDayInfo){
		statisticsCustomerDayInfoService.update(statisticsCustomerDayInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("statisticscustomerdayinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		statisticsCustomerDayInfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
