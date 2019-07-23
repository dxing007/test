package io.renren.controller;

import io.renren.entity.CustomerBaseInfoEntity;
import io.renren.entity.CustomerUserTypeEnum;
import io.renren.entity.SysUserEntity;
import io.renren.service.CustomerBaseInfoService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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
 * 客户基础信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-29 16:56:55
 */
@Controller
@RequestMapping("customerbaseinfo")
public class CustomerBaseInfoController extends AbstractController {
	@Autowired
	private CustomerBaseInfoService customerBaseInfoService;
	
	@RequestMapping("/customerbaseinfo.html")
	public String list(){
		return "customerbaseinfo/customerbaseinfo.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("customerbaseinfo:list")
	public R list(Integer page, Integer limit,String  name,String  status,String telephone,Integer sex ,String qq){
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
		SysUserEntity sysuser = getUser();
		//如果不是管理员只能查询自己相关的客户
		if(CustomerUserTypeEnum.HOSPITAL_ADMIN.getValue().equals(sysuser.getUserType())){ //医院跟单人员
			map.put("hospitalAdminId", sysuser.getUserId());
		}else {
			//非管理员只能查询 自己创建的用户
			if(!CustomerUserTypeEnum.ADMIN.getValue().equals(sysuser.getUserType())){
				map.put("createUserId", sysuser.getUserId());
			}
		}
		map.put("status", status);
		map.put("telephone", telephone);
		map.put("qq", qq);
		map.put("sex", sex);
		//查询列表数据
		List<CustomerBaseInfoEntity> customerBaseInfoList = customerBaseInfoService.queryList(map);
		int total = customerBaseInfoService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(customerBaseInfoList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	@ResponseBody
	@RequestMapping("/checkPhone")
	public R checkInfo(String telephone){
		Map<String, Object> map = new HashMap<>();
		map.put("telephone", telephone);
		//查询列表数据
		List<CustomerBaseInfoEntity> customerBaseInfoList = customerBaseInfoService.queryList(map);
		if(customerBaseInfoList!=null&&customerBaseInfoList.size()>0){
			return R.ok().put("valid", "false");
		}else{
			return R.ok().put("valid", "true");
		}
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("customerbaseinfo:info")
	public R info(@PathVariable("id") Integer id){
		CustomerBaseInfoEntity customerBaseInfo = customerBaseInfoService.queryObject(id);
		
		return R.ok().put("customerBaseInfo", customerBaseInfo);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("customerbaseinfo:save")
	public R save(@RequestBody CustomerBaseInfoEntity customerBaseInfo){
		customerBaseInfo.setCreateTime(new Date());
		customerBaseInfo.setCreateUserId(getUserId().intValue());
		customerBaseInfo.setLastUpdateTime(new Date());
		customerBaseInfo.setLastUpdateUserId(getUserId().intValue());
		customerBaseInfoService.save(customerBaseInfo);
		return R.ok().put("id",customerBaseInfo.getId());
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("customerbaseinfo:update")
	public R update(@RequestBody CustomerBaseInfoEntity customerBaseInfo){
		customerBaseInfo.setLastUpdateTime(new Date());
		customerBaseInfo.setLastUpdateUserId(getUserId().intValue());
		customerBaseInfoService.update(customerBaseInfo);
		return R.ok().put("id",customerBaseInfo.getId());
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("customerbaseinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		customerBaseInfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
