package io.renren.controller;

import io.renren.entity.CustomerUserTypeEnum;
import io.renren.entity.MessageEntity;
import io.renren.entity.SysUserEntity;
import io.renren.service.MessageService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:31
 */
@Controller
@RequestMapping("message")
public class MessageController extends AbstractController {   
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/message.html")
	public String list(){
		return "message/message.html";
	}
	
	

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/readMessage/{id}")
	public R readMessage(@PathVariable("id") Integer id){
		MessageEntity message = messageService.queryObject(id);
		//更新消息
		message.setStatus(1);
		message.setLastUpdateTime(new Date());
		message.setLastUpdateUserId(getUserId().intValue());
		messageService.update(message);
		return R.ok().put("message", message);
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("message:list")
	public R list(Integer page, Integer limit,Integer status){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("status", status);
		
		SysUserEntity user = getUser();
		if(!CustomerUserTypeEnum.ADMIN.getValue().equals(user.getUserType())){ //不为管理员的时候 
			map.put("myself", user.getUserId());  //只能查询接收人为自己的
		}
		//查询列表数据
		List<MessageEntity> messageList = messageService.queryList(map);
		int total = messageService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(messageList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("message:info")
	public R info(@PathVariable("id") Integer id){
		MessageEntity message = messageService.queryObject(id);
		
		return R.ok().put("message", message);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("message:save")
	public R save(@RequestBody MessageEntity message){
		Integer userId = getUserId().intValue();
		message.setSenderId(userId);
		message.setStatus(0);
		message.setCreateTime(new Date());
		message.setCreateUserId(userId);
		messageService.save(message,getUser());		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("message:update")
	public R update(@RequestBody MessageEntity message){
		message.setLastUpdateTime(new Date());
		message.setLastUpdateUserId(getUserId().intValue());
		messageService.update(message);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("message:delete")
	public R delete(@RequestBody Integer[] ids){
		messageService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
