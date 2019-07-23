package io.renren.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.renren.entity.CustomerHospitalEntity;
import io.renren.service.CustomerHospitalService;
import io.renren.utils.DateUtils;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 客户派单表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-30 16:23:50
 */
@Controller
@RequestMapping("customerhospital")
public class CustomerHospitalController  extends AbstractController {  
	@Autowired
	private CustomerHospitalService customerHospitalService;
	
	@RequestMapping("/customerhospital.html")
	public String list(){
		return "customerhospital/customerhospital.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("customerhospital:list")
	public R list(Integer page, Integer limit,Integer customerId){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("customerId",customerId);
		//查询列表数据
		List<CustomerHospitalEntity> customerHospitalList = customerHospitalService.queryList(map);
		int total = customerHospitalService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(customerHospitalList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("customerhospital:info")
	public R info(@PathVariable("id") Integer id){
		CustomerHospitalEntity customerHospital = customerHospitalService.queryObject(id);
		
		return R.ok().put("customerHospital", customerHospital);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("customerhospital:save")
	public R save(@RequestBody CustomerHospitalEntity customerHospital){
		customerHospital.setCreateTime(new Date());
		customerHospital.setCreateUserId(getUserId().intValue());
		customerHospitalService.save(customerHospital);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("customerhospital:update")
	public R update(@RequestBody CustomerHospitalEntity customerHospital){
		customerHospital.setLastUpdateTime(new Date());
		customerHospital.setLastUpdateUserId(getUserId().intValue());
		customerHospitalService.update(customerHospital);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("customerhospital:delete")
	public R delete(@RequestBody Integer[] ids){
		customerHospitalService.deleteBatch(ids);
		
		return R.ok();
	}
	
	@RequestMapping("/hospitalManage.html")
	public String hospitalManage(){
		return "customerhospital/hospitalManage.html";
	}
	
	@ResponseBody
	@RequestMapping("/queryHospitalCountInfo")
	@RequiresPermissions("customerhospital:queryHospitalCountInfo")
	public R queryHospitalCountInfo(Integer page, Integer limit,String regionCode,String beginTime,String endTime){
		Map<String,Object> parramMap = new HashMap<>();
		parramMap.put("offset", (page - 1) * limit);
		parramMap.put("limit", limit);
		if(beginTime!=null) {
			parramMap.put("beginTime", DateUtils.getStartTime(DateUtils.strToDateLong(beginTime)));
		}
		if(endTime!=null) {
			parramMap.put("endTime", DateUtils.getEndTime(DateUtils.strToDateLong(endTime)));
		}
		if(StringUtils.isNotEmpty(regionCode)) {
			parramMap.put("regionCode",regionCode);
		}
		List<Map<String,Object>> list = customerHospitalService.queryHospitalCountInfo(parramMap);
		int total = customerHospitalService.queryHospitalCount(parramMap);
		PageUtils pageUtil = new PageUtils(list, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
}
