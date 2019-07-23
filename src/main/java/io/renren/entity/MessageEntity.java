package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:31
 */
public class MessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//客户id
	private Integer customerId;
	//状态 0 未读 1 已读
	private Integer status;
	//内容
	private String content;
	//发送人 0 为系统消息
	private Integer senderId;
	//接收人
	private Integer receiveId;
	//创建日期
	private Date createTime;
	//创建人
	private Integer createUserId;
	//
	private Date lastUpdateTime;
	//
	private Integer lastUpdateUserId;
	private String senderName;
	private  String receiveName;
	
	
	/**
	 * 医院id
	 */
	private Integer hospitalId;
	/**
	 * 清除待办
	 */
	private String task;
	
	private String customerName;
	
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
	 * 设置：状态 0 未读 1 已读
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0 未读 1 已读
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：发送人 0 为系统消息
	 */
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	/**
	 * 获取：发送人 0 为系统消息
	 */
	public Integer getSenderId() {
		return senderId;
	}
	/**
	 * 设置：接收人
	 */
	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}
	/**
	 * 获取：接收人
	 */
	public Integer getReceiveId() {
		return receiveId;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建日期
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
	 * 设置：
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * 获取：
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * 设置：
	 */
	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：
	 */
	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
