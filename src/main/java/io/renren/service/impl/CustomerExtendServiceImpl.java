package io.renren.service.impl;

import io.renren.dao.CustomerBaseInfoDao;
import io.renren.dao.CustomerExtendDao;
import io.renren.dao.CustomerHospitalDao;
import io.renren.dao.HospitalInfoDao;
import io.renren.entity.CustomerBaseInfoEntity;
import io.renren.entity.CustomerDocumentaryState;
import io.renren.entity.CustomerExtendEntity;
import io.renren.entity.CustomerHospitalEntity;
import io.renren.entity.CustomerUserTypeEnum;
import io.renren.entity.HospitalInfoEntity;
import io.renren.entity.MessageEntity;
import io.renren.entity.PendingTaskEntity;
import io.renren.entity.SysUserEntity;
import io.renren.entity.extend.CustomerDetailVo;
import io.renren.service.CustomerBaseInfoService;
import io.renren.service.CustomerExtendService;
import io.renren.service.MessageService;
import io.renren.service.PendingTaskService;
import io.renren.service.SysUserService;
import io.renren.utils.RRException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("customerExtendService")
public class CustomerExtendServiceImpl implements CustomerExtendService {
	@Autowired
	private CustomerExtendDao customerExtendDao;
	
	@Autowired
	private CustomerHospitalDao customerHospitalDao;
	
	@Autowired
	private CustomerBaseInfoDao customerBaseInfoDao;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private PendingTaskService  pendingTaskService;
	
	@Autowired
	private CustomerBaseInfoService  customerBaseInfoService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private HospitalInfoDao hospitalInfoDao;
	
 
	
	@Override
	public CustomerExtendEntity queryObject(Integer id){
		return customerExtendDao.queryObject(id);
	}
	
	@Override
	public List<CustomerExtendEntity> queryList(Map<String, Object> map){
		List<CustomerExtendEntity> retList = customerExtendDao.queryList(map);
		if(retList!=null){
			for(CustomerExtendEntity e : retList){
				Map<String, Object> customerHostpital = new HashMap<>(); //查询这笔客户第的信息
				customerHostpital.put("customerId",e.getCustomerId());  
				List<CustomerHospitalEntity> hospitalList = customerHospitalDao.queryList(customerHostpital);
				e.setCustomerHospital(hospitalList);
			}
		} 
		return retList;
		
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return customerExtendDao.queryTotal(map);
	}
	
	@Override
	public void save(CustomerExtendEntity customerExtend){
		customerExtendDao.save(customerExtend);
	}
	
	@Override
	public void update(CustomerExtendEntity customerExtend){
		customerExtendDao.update(customerExtend);
	}
	
	@Override
	public void delete(Integer id){
		customerExtendDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		customerExtendDao.deleteBatch(ids);
	}
	
	@Override
	public void saveCustomerAndHistpital(CustomerDetailVo ustomerDetailVo,SysUserEntity sysUserEntity) {
	  Integer customerId =	ustomerDetailVo.getCustomerId(); 
	  if(customerId==null){
		  throw new RRException("客户信息不能为空");
	  }
	  CustomerExtendEntity customerExtendEntity  =  customerExtendDao.queryObjectByCustomerId(customerId);	  
	  Date nowDate = new Date();
	  if(customerExtendEntity!=null){
		  CustomerExtendEntity  newCustomerExtendEntity = new CustomerExtendEntity();
		  BeanUtils.copyProperties(ustomerDetailVo, newCustomerExtendEntity);
		  newCustomerExtendEntity.setLastUpdateTime(new Date());
		  newCustomerExtendEntity.setLastUpdateUserId(sysUserEntity.getUserId().intValue());
		  customerExtendDao.update(newCustomerExtendEntity);
	  }else{
		  CustomerExtendEntity  newCustomerExtendEntity = new CustomerExtendEntity();
		  BeanUtils.copyProperties(ustomerDetailVo, newCustomerExtendEntity);
		  newCustomerExtendEntity.setCreateTime(nowDate);
		  newCustomerExtendEntity.setCreateUserId(sysUserEntity.getUserId().intValue());
		  newCustomerExtendEntity.setLastUpdateUserId(sysUserEntity.getUserId().intValue());
		  newCustomerExtendEntity.setLastUpdateTime(nowDate);
		  newCustomerExtendEntity.setLastUpdateUserId(sysUserEntity.getUserId().intValue());
		  customerExtendDao.save(newCustomerExtendEntity);
		  CustomerBaseInfoEntity baseCustomer = new CustomerBaseInfoEntity();
		  baseCustomer.setId(customerId);
		  baseCustomer.setStatus(CustomerDocumentaryState.DISPATCHED.getValue());//修改主订单状态为派单中
		  baseCustomer.setLastUpdateTime(new Date());
		  baseCustomer.setLastUpdateUserId(sysUserEntity.getUserId().intValue());
		  customerBaseInfoDao.update(baseCustomer);
	  }
	  CustomerBaseInfoEntity customerBaseInfoEntity =  customerBaseInfoService.queryObject(ustomerDetailVo.getCustomerId());
	  
	  List<CustomerHospitalEntity>  hospitalList =  ustomerDetailVo.getHospitalList();
	  if(hospitalList!=null&&hospitalList.size()>0){
		  for(CustomerHospitalEntity h : hospitalList){
			  if(h.getHospitalId()==null){
				  continue;
			  }
			  
			  Map<String, Object> isExtendMap = new HashMap<>();
			  isExtendMap.put("customerId",ustomerDetailVo.getCustomerId());
			  isExtendMap.put("hospitalId",h.getHospitalId());
			  List<CustomerHospitalEntity>  customerhospitalList = customerHospitalDao.queryList(isExtendMap);
			  if(customerhospitalList!=null &&  customerhospitalList.size()>0){
				  throw new RRException("亲："+ customerhospitalList.get(0).getHopitalName()+ " 已派过单了哟！");
			  }
			  h.setCustomerId(customerId);
			  h.setDocumentaryState(CustomerDocumentaryState.DISPATCHED.getValue());
			  h.setIntentionProvince(ustomerDetailVo.getIntentionAreaCode());
			  h.setProject(ustomerDetailVo.getBeautyItemCode());
			  h.setDistributionUserId(sysUserEntity.getUserId().intValue()); //分配人
			  h.setCreateTime(nowDate);
			  h.setCreateUserId(sysUserEntity.getUserId().intValue());
			  h.setLastUpdateTime(nowDate);
			  h.setLastUpdateUserId(sysUserEntity.getUserId().intValue());
			  // 获取医院信息
			  SysUserEntity hospitalUser =  queryUserByHospitalId(h.getHospitalId());
			  h.setHospitalAdminId(hospitalUser.getUserId().intValue());//医院跟单人员
			  //生成医院待办
			  crateTask(hospitalUser, customerBaseInfoEntity, sysUserEntity);	  	
			  
		  }
		  customerHospitalDao.saveBatch(hospitalList);
	  }
	  
	  
	  
	}
	/**
	 * 生成待办任务
	 */
	private void crateTask(SysUserEntity hospitalUser,CustomerBaseInfoEntity customerBaseInfoEntity,SysUserEntity nowUserEntity ){
		PendingTaskEntity pendingTask = new PendingTaskEntity();
		pendingTask.setCustomerId(customerBaseInfoEntity.getId());
		pendingTask.setTelephone(customerBaseInfoEntity.getTelephone());
		pendingTask.setSex(customerBaseInfoEntity.getSex());
		pendingTask.setNextFollowTime(new Date());
		pendingTask.setStatus("0");//未读
		pendingTask.setWeight("remark_weight_paidan");//设置为派单
		pendingTask.setCreateTime(new Date());
		pendingTask.setCreateUserId(nowUserEntity.getUserId().intValue());
		pendingTask.setHandlerUserId(hospitalUser.getUserId().intValue());// 设置处理人
		pendingTaskService.save(pendingTask);
		
	}
	
	private SysUserEntity queryUserByHospitalId( Integer hospitalId){
		HospitalInfoEntity hospitalInfo = hospitalInfoDao.queryObject(hospitalId);
		if(hospitalInfo==null){
			throw new RRException("id："+hospitalId+"的医院不存在！！！");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("userType", "hospital_admin");
		param.put("hospitalId", hospitalId);
		param.put("status", 1);//启用
		List<SysUserEntity> retList = sysUserService.queryList(param);
		if(retList!=null &&retList.size()>0){
			if(retList.size()>1){
				throw new RRException(hospitalInfo.getName() +" 存在多个启用的管理员帐号无法派单，请先设置管理员帐号！！！");
			}
			return retList.get(0);
		}else{
			 
			 throw new RRException(hospitalInfo.getName()+" 未分配启用的管理员无法派单，请先设置管理员帐号！！！");
		}
	}
	
	@Override
	public CustomerDetailVo getInfoByCustomerId(Integer customerId,SysUserEntity sysUserEntity) {
		CustomerDetailVo retVo  = new CustomerDetailVo();
		CustomerBaseInfoEntity baseInfo = customerBaseInfoDao.queryObject(customerId);
		if(baseInfo==null){
			 throw new RRException("客户基础信息为空");
		}
		CustomerExtendEntity customerExtendInfo  =  customerExtendDao.queryObjectByCustomerId(customerId);	  
		if(customerExtendInfo!=null){
			BeanUtils.copyProperties(customerExtendInfo, retVo);
		}
		retVo.setCustomerBaseInfoEntity(baseInfo);
		
		Map<String, Object> customerHostpital = new HashMap<>();
		customerHostpital.put("customerId",customerId);
		if(CustomerUserTypeEnum.HOSPITAL_ADMIN.getValue().equals(sysUserEntity.getUserType())){//如果是医院管理员只能查看分配给自己的医院信息
			customerHostpital.put("hospitalId",sysUserEntity.getHospitalId());
		}
		List<CustomerHospitalEntity> hospitalList = customerHospitalDao.queryList(customerHostpital);
		retVo.setHospitalList(hospitalList);
		Map<String,Object> messageParam = new HashMap<>();
		messageParam.put("customerId", customerId);
		if(CustomerUserTypeEnum.HOSPITAL_ADMIN.getValue().equals(sysUserEntity.getUserType())){//如果是医院管理员只能查看分配给自己的医院信息
			messageParam.put("receiveId", sysUserEntity.getUserId()); 
		}
		//发送消息
		List<MessageEntity> messageList = messageService.queryList(messageParam);
		for(CustomerHospitalEntity h : hospitalList){
			List<MessageEntity> mesList =new ArrayList<>();
			for(MessageEntity m : messageList ){ //如果发送人接收有一个是属于医院的id 
				if(m.getReceiveId()==h.getHospitalAdminId()||m.getSenderId() == h.getHospitalAdminId()){
					mesList.add(m);
				}
			}
			h.setMessageList(mesList);
		}
		retVo.setUserType(sysUserEntity.getUserType());
		return retVo;
	}
}

