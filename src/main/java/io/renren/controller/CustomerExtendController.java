package io.renren.controller;

import io.renren.entity.CustomerExtendEntity;
import io.renren.entity.CustomerUserTypeEnum;
import io.renren.entity.SysUserEntity;
import io.renren.entity.extend.CustomerDetailVo;
import io.renren.service.CustomerExtendService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 客户信息扩展表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-01 12:49:29
 */
@Controller
@RequestMapping("customerextend")
public class CustomerExtendController  extends AbstractController {  
	@Autowired
	private CustomerExtendService customerExtendService;
	
	@RequestMapping("/customerextend.html")
	public String list(){
		return "customerextend/customerextend.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("customerextend:list")
	public R list(Integer page, Integer limit,String name,String telephone,String qq){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		try {
			if(!StringUtils.isEmpty(name)){
				map.put("name", new String(name.getBytes("iso8859-1"),"utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("telephone", telephone);
		map.put("qq", qq);
		SysUserEntity sysuser = getUser(); 
		 //非管理员只能查询 自己创建的用户
		 if(!CustomerUserTypeEnum.ADMIN.getValue().equals(sysuser.getUserType())){
				map.put("createUserId", sysuser.getUserId());
		 }
		//查询列表数据
		List<CustomerExtendEntity> customerExtendList = customerExtendService.queryList(map);
		int total = customerExtendService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(customerExtendList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 获取派单信息客户详情
	 */
	@ResponseBody
	@RequestMapping("/getInfoByCustomerId/{id}")
	//@RequiresPermissions("customerextend:getInfoByCustomerId")
	public CustomerDetailVo getInfoByCustomerId(@PathVariable("id") Integer id){
		return   customerExtendService.getInfoByCustomerId(id,getUser());
	 
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("customerextend:info")
	public R info(@PathVariable("id") Integer id){
		CustomerExtendEntity customerExtend = customerExtendService.queryObject(id);
		return R.ok().put("customerExtend", customerExtend);
	}
	
	
	
	
	
	/**
	 * 保存并派单
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("customerextend:save")
	public R save(@RequestBody CustomerDetailVo customerExtend){
		customerExtendService.saveCustomerAndHistpital(customerExtend,getUser());
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("customerextend:update")
	public R update(@RequestBody CustomerExtendEntity customerExtend){
		customerExtendService.update(customerExtend);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("customerextend:delete")
	public R delete(@RequestBody Integer[] ids){
		customerExtendService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
