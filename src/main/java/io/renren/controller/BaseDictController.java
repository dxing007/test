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

import io.renren.entity.BaseDictEntity;
import io.renren.service.BaseDictService;
import io.renren.utils.BaseDictCacheServer;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:32
 */
@Controller
@RequestMapping("basedict")
public class BaseDictController {
	@Autowired
	private BaseDictService baseDictService;
	
	@RequestMapping("/basedict.html")
	public String list(){
		return "basedict/basedict.html";
	}
	
	

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/queryDictEntityByCode")
	public List<BaseDictEntity> list(String type){
		return  BaseDictCacheServer.getBaseDictEntityList();		
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/queryBaseDictMapByCodes")
	public  Map<String,List<BaseDictEntity>> list(@RequestBody String[] types){
		return  BaseDictCacheServer.getDictMapByCode(types);		
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("basedict:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		//查询列表数据
		List<BaseDictEntity> baseDictList = baseDictService.queryList(map);
		int total = baseDictService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(baseDictList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	

	
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{dictId}")
	@RequiresPermissions("basedict:info")
	public R info(@PathVariable("dictId") String dictId){
		BaseDictEntity baseDict = baseDictService.queryObject(dictId);
		return R.ok().put("baseDict", baseDict);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/infoById/{id}")
	@RequiresPermissions("basedict:info")
	public R infoById(@PathVariable("id") Integer id){
		BaseDictEntity baseDict = baseDictService.queryById(id);
		return R.ok().put("baseDict", baseDict);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("basedict:save")
	public R save(@RequestBody BaseDictEntity baseDict){
		BaseDictEntity _baseDict = baseDictService.queryById(baseDict.getId());
		if(_baseDict==null){
			baseDictService.save(baseDict);
		}else{
			baseDictService.update(baseDict);
		}
		BaseDictCacheServer.cleanCache();
		return R.ok();
	}
	/*
	*//**
	 * 修改
	 *//*
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("basedict:update")
	public R update(@RequestBody BaseDictEntity baseDict){
		baseDictService.update(baseDict);
		BaseDictCacheServer.cleanCache();
		return R.ok();
	}
	*/
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("basedict:delete")
	public R delete(@RequestBody Integer[] dictIds){
		baseDictService.deleteBatch(dictIds);
		BaseDictCacheServer.cleanCache();
		return R.ok();
	}
	
	
}
