package io.renren.controller;

import io.renren.entity.HospitalInfoEntity;
import io.renren.service.HospitalInfoService;
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
 * 医院信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-29 17:09:03
 */
@Controller
@RequestMapping("hospitalinfo")
public class HospitalInfoController extends AbstractController {
	@Autowired
	private HospitalInfoService hospitalInfoService;
	
	@RequestMapping("/hospitalinfo.html")
	public String list(){
		return "hospitalinfo/hospitalinfo.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("hospitalinfo:list")
	public R list(Integer page, Integer limit,String name,String regionCode){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		try {
			if (!StringUtils.isEmpty(name)) {
				map.put("name", new String(name.getBytes("iso8859-1"), "utf-8"));
			}
			if(!StringUtils.isEmpty(regionCode)){
				map.put("regionCode", regionCode);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询列表数据
		List<HospitalInfoEntity> hospitalInfoList = hospitalInfoService.queryList(map);
		int total = hospitalInfoService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(hospitalInfoList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hospitalinfo:info")
	public R info(@PathVariable("id") Integer id){
		HospitalInfoEntity hospitalInfo = hospitalInfoService.queryObject(id);
		return R.ok().put("hospitalInfo", hospitalInfo);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("hospitalinfo:save")
	public R save(@RequestBody HospitalInfoEntity hospitalInfo) {
		hospitalInfo.setCreateTime(new Date());
		hospitalInfo.setCreateUserId(getUserId().intValue());
		hospitalInfoService.save(hospitalInfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("hospitalinfo:update")
	public R update(@RequestBody HospitalInfoEntity hospitalInfo){
		hospitalInfo.setLastUpdateTime(new Date());
		hospitalInfo.setLastUpdateUserId(getUserId().intValue());
		hospitalInfoService.update(hospitalInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("hospitalinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		hospitalInfoService.deleteBatch(ids);
		return R.ok();
	}
	
}
