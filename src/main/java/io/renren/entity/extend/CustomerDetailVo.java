package io.renren.entity.extend;

import io.renren.entity.CustomerBaseInfoEntity;
import io.renren.entity.CustomerExtendEntity;
import io.renren.entity.CustomerHospitalEntity;
import io.renren.entity.MessageEntity;

import java.util.List;
import java.util.Map;

public class CustomerDetailVo extends CustomerExtendEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8701235822029190839L;
	
	/**
	 * 客户基础信息
	 */
	private CustomerBaseInfoEntity customerBaseInfoEntity;
	
	private List<CustomerHospitalEntity> hospitalList ;
		
	private Map<Integer,List<MessageEntity>>  messageMap;
	
	private String userType;
	
	public List<CustomerHospitalEntity> getHospitalList() {
		return hospitalList;
	}


	public void setHospitalList(List<CustomerHospitalEntity> hospitalList) {
		this.hospitalList = hospitalList;
	}
	

	public CustomerBaseInfoEntity getCustomerBaseInfoEntity() {
		return customerBaseInfoEntity;
	}


	public void setCustomerBaseInfoEntity(
			CustomerBaseInfoEntity customerBaseInfoEntity) {
		this.customerBaseInfoEntity = customerBaseInfoEntity;
	}


	public Map<Integer, List<MessageEntity>> getMessageMap() {
		return messageMap;
	}


	public void setMessageMap(Map<Integer, List<MessageEntity>> messageMap) {
		this.messageMap = messageMap;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	
}
