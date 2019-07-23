package io.renren.controller;

import java.util.Date;
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

import io.renren.entity.CustomerRemarkEntity;
import io.renren.entity.CustomerUserTypeEnum;
import io.renren.entity.SysUserEntity;
import io.renren.service.CustomerRemarkService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:31
 */
@Controller
@RequestMapping("customerremark")
public class CustomerRemarkController extends AbstractController {  
	@Autowired
	private CustomerRemarkService customerRemarkService;
	
	@RequestMapping("/customerremark.html")
	public String list(){
		return "customerremark/customerremark.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("customerremark:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		SysUserEntity sysuser = getUser(); 
		 //非管理员只能查询 自己创建的用户
		 map.put("createUserId", sysuser.getUserId());
		//查询列表数据
		List<CustomerRemarkEntity> customerRemarkList = customerRemarkService.queryList(map);
		int total = customerRemarkService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(customerRemarkList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	@ResponseBody
	@RequestMapping("/allList")
	public  R AllList(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		SysUserEntity sysuser = getUser(); 
		map.put("createUserId", sysuser.getUserId());
		//查询列表数据
		List<CustomerRemarkEntity> customerRemarkList = customerRemarkService.queryList(map);
		return R.ok().put("list", customerRemarkList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("customerremark:info")
	public R info(@PathVariable("id") Integer id){
		CustomerRemarkEntity customerRemark = customerRemarkService.queryObject(id);
		
		return R.ok().put("customerRemark", customerRemark);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("customerremark:save")
	public R save(@RequestBody CustomerRemarkEntity customerRemark){
		customerRemark.setCreateTime(new Date());
		customerRemark.setCreateUserId(getUserId().intValue());
		customerRemarkService.save(customerRemark);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("customerremark:update")
	public R update(@RequestBody CustomerRemarkEntity customerRemark){
		customerRemark.setLastUpdateTime(new Date());
		customerRemark.setLastUpdateUserId(getUserId().intValue());
		customerRemarkService.update(customerRemark);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("customerremark:delete")
	public R delete(@RequestBody Integer[] ids){
		
		customerRemarkService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
