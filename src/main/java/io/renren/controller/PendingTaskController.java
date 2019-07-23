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

import io.renren.entity.PendingTaskEntity;
import io.renren.service.PendingTaskService;
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
@RequestMapping("pendingtask")
public class PendingTaskController extends AbstractController {   
	@Autowired
	private PendingTaskService pendingTaskService;
	
	@RequestMapping("/pendingtask.html")
	public String list(){
		return "pendingtask/pendingtask.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	//@RequiresPermissions("pendingtask:list")
	public R list(Integer page, Integer limit,String task){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("handlerUserId", getUserId()); //只查询自己的待办
		map.put("status", 0); //只查询自己的待办
		map.put("task", task); //只查询时间小于今天的待办
		//查询列表数据
		List<PendingTaskEntity> pendingTaskList = pendingTaskService.queryList(map);
		int total = pendingTaskService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(pendingTaskList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("pendingtask:info")
	public R info(@PathVariable("id") Integer id){
		PendingTaskEntity pendingTask = pendingTaskService.queryObject(id);
		
		return R.ok().put("pendingTask", pendingTask);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("pendingtask:save")
	public R save(@RequestBody PendingTaskEntity pendingTask){
		pendingTaskService.save(pendingTask);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("pendingtask:update")
	public R update(@RequestBody PendingTaskEntity pendingTask){
		pendingTaskService.update(pendingTask);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("pendingtask:delete")
	public R delete(@RequestBody Integer[] ids){
		pendingTaskService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
