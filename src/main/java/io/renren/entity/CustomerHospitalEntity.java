package io.renren.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * 客户派单表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-30 16:23:50
 */
public class CustomerHospitalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//客户id
	private Integer customerId;
	//医院id
	private Integer hospitalId;
	//医院跟单人员Id
	private Integer hospitalAdminId;
	//跟单状态
	private String documentaryState;
	//意向地区
	private String intentionProvince;
	//分配项目
	private String project;
	//分配人id
	private Integer distributionUserId;
	//创建时间
	private Date createTime;
	//创建人
	private Integer createUserId;
	//更新时间
	private Date lastUpdateTime;
	//更新人
	private Integer lastUpdateUserId;
	
	/**
	 * 医院信息
	 */
	private String hopitalName;
	
	private List<MessageEntity>  messageList;
	
	private String createName;
	
	/**
	 * 定金金额
	 */
	private Double earnestMoney;
	/**
	 * 成交金额
	 */
	private Double turnoverAmount;
	
	public String getStatusName(){
		return CustomerDocumentaryState.getName(documentaryState);
	}
	/**
	 * 设置：ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：客户id
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：客户id
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：医院id
	 */
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	/**
	 * 获取：医院id
	 */
	public Integer getHospitalId() {
		return hospitalId;
	}
	/**
	 * 设置：跟单状态
	 */
	public void setDocumentaryState(String documentaryState) {
		this.documentaryState = documentaryState;
	}
	/**
	 * 获取：跟单状态
	 */
	public String getDocumentaryState() {
		return documentaryState;
	}
	/**
	 * 设置：意向地区
	 */
	public void setIntentionProvince(String intentionProvince) {
		this.intentionProvince = intentionProvince;
	}
	/**
	 * 获取：意向地区
	 */
	public String getIntentionProvince() {
		return intentionProvince;
	}
	/**
	 * 设置：分配项目
	 */
	public void setProject(String project) {
		this.project = project;
	}
	/**
	 * 获取：分配项目
	 */
	public String getProject() {
		return project;
	}
	/**
	 * 设置：分配人id
	 */
	public void setDistributionUserId(Integer distributionUserId) {
		this.distributionUserId = distributionUserId;
	}
	/**
	 * 获取：分配人id
	 */
	public Integer getDistributionUserId() {
		return distributionUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：更新时间
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * 设置：更新人
	 */
	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：更新人
	 */
	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	
	public String getHopitalName() {
		return hopitalName;
	}
	public void setHopitalName(String hopitalName) {
		this.hopitalName = hopitalName;
	}
	public List<MessageEntity> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<MessageEntity> messageList) {
		this.messageList = messageList;
	}
	public Integer getHospitalAdminId() {
		return hospitalAdminId;
	}
	public void setHospitalAdminId(Integer hospitalAdminId) {
		this.hospitalAdminId = hospitalAdminId;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Double getEarnestMoney() {
		return earnestMoney;
	}
	public void setEarnestMoney(Double earnestMoney) {
		this.earnestMoney = earnestMoney;
	}
	public Double getTurnoverAmount() {
		return turnoverAmount;
	}
	public void setTurnoverAmount(Double turnoverAmount) {
		this.turnoverAmount = turnoverAmount;
	}
	
	
}
