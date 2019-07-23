package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-04 15:30:26
 */
public class StatisticsCustomerDayInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//统计日期
	private Date date;
	//新增客户数
	private Integer newCreateCount;
	//成交数
	private Integer successCount;
	//跟进数
	private Integer followUpCount;
	//成交金额
	private Double transactionAmount;
	//创建时间
	private Date createTime;
	//用户id
	private Integer userId;
	
	private String userName;
	
	//待办任务数
	private Integer taskCount;
	//更新时间
	private Date lastUpdateTime;
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
	 * 设置：统计日期
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * 获取：统计日期
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * 设置：新增客户数
	 */
	public void setNewCreateCount(Integer newCreateCount) {
		this.newCreateCount = newCreateCount;
	}
	/**
	 * 获取：新增客户数
	 */
	public Integer getNewCreateCount() {
		return newCreateCount;
	}
	/**
	 * 设置：成交数
	 */
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	/**
	 * 获取：成交数
	 */
	public Integer getSuccessCount() {
		return successCount;
	}
	/**
	 * 设置：跟进数
	 */
	public void setFollowUpCount(Integer followUpCount) {
		this.followUpCount = followUpCount;
	}
	/**
	 * 获取：跟进数
	 */
	public Integer getFollowUpCount() {
		return followUpCount;
	}
	/**
	 * 设置：成交金额
	 */
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	/**
	 * 获取：成交金额
	 */
	public Double getTransactionAmount() {
		return transactionAmount;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
		
}	
