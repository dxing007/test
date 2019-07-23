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
public class CustomerRemarkEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//客户id
	private Integer customerId;
	//下次跟进时间
	private Date nextFollowTime;
	//最近跟进时间
	private Date lastFollowTime;
	//是否需要下次跟进
	private Integer isfollow;
	//状态
	private String status;
	//权重
	private String weight;
	//当前情况
	private String currentRemark;
	//下次情况
	private String nextRemark;
	//创建时间
	private Date createTime;
	//
	private Integer createUserId;
	//
	private Date lastUpdateTime;
	//
	private Integer lastUpdateUserId;

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
	 * 设置：下次跟进时间
	 */
	public void setNextFollowTime(Date nextFollowTime) {
		this.nextFollowTime = nextFollowTime;
	}
	/**
	 * 获取：下次跟进时间
	 */
	public Date getNextFollowTime() {
		return nextFollowTime;
	}
	/**
	 * 设置：最近跟进时间
	 */
	public void setLastFollowTime(Date lastFollowTime) {
		this.lastFollowTime = lastFollowTime;
	}
	/**
	 * 获取：最近跟进时间
	 */
	public Date getLastFollowTime() {
		return lastFollowTime;
	}
	/**
	 * 设置：是否需要下次跟进
	 */
	public void setIsfollow(Integer isfollow) {
		this.isfollow = isfollow;
	}
	/**
	 * 获取：是否需要下次跟进
	 */
	public Integer getIsfollow() {
		return isfollow;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：权重
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/**
	 * 获取：权重
	 */
	public String getWeight() {
		return weight;
	}
	/**
	 * 设置：当前情况
	 */
	public void setCurrentRemark(String currentRemark) {
		this.currentRemark = currentRemark;
	}
	/**
	 * 获取：当前情况
	 */
	public String getCurrentRemark() {
		return currentRemark;
	}
	/**
	 * 设置：下次情况
	 */
	public void setNextRemark(String nextRemark) {
		this.nextRemark = nextRemark;
	}
	/**
	 * 获取：下次情况
	 */
	public String getNextRemark() {
		return nextRemark;
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
	 * 设置：
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：
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
}
