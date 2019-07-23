package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 客户基础信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-29 16:56:55
 */
public class CustomerBaseInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//客户id
	private Integer id;
	//名称
	private String name;
	//性别 0 为女  1 为男
	private Integer sex;
	//电话号码
	private String telephone;
	//客户主状态（跟单人员关心）
	private String status;
	//是否充许电话联  0 充许 1 不能
	private Integer telephoneContact;
	//QQ
	private String qq;
	//是否充许QQ联  0 充许 1 不能
	private Integer qqContact;
	//邮箱
	private String email;
	//是否充许联系
	private Integer emailContact;
	//微信
	private String wechat;
	//是否充许联系
	private Integer wechatContact;
	//微博
	private String microblog;
	//是否充许联系
	private Integer microblogContact;
	//地区编码
	private String locatedRegionCode;
	//创建人
	private Date createTime;
	//创建人
	private Integer createUserId;
	//更新时间
	private Date lastUpdateTime;
	//更新人
	private Integer lastUpdateUserId;
	
	private String beautyItemCode;
	/**
	 * 更新时间
	 */
	private Date exLastUpdateTime;
	
	
	private String createName;
	/**
	 * 设置：客户id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：客户id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：性别 0 为女  1 为男
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别 0 为女  1 为男
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：电话号码
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：电话号码
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：客户主状态（跟单人员关心）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：客户主状态（跟单人员关心）
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：是否充许电话联  0 充许 1 不能
	 */
	public void setTelephoneContact(Integer telephoneContact) {
		this.telephoneContact = telephoneContact;
	}
	/**
	 * 获取：是否充许电话联  0 充许 1 不能
	 */
	public Integer getTelephoneContact() {
		return telephoneContact;
	}
	/**
	 * 设置：QQ
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 获取：QQ
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * 设置：是否充许QQ联  0 充许 1 不能
	 */
	public void setQqContact(Integer qqContact) {
		this.qqContact = qqContact;
	}
	/**
	 * 获取：是否充许QQ联  0 充许 1 不能
	 */
	public Integer getQqContact() {
		return qqContact;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：是否充许联系
	 */
	public void setEmailContact(Integer emailContact) {
		this.emailContact = emailContact;
	}
	/**
	 * 获取：是否充许联系
	 */
	public Integer getEmailContact() {
		return emailContact;
	}
	/**
	 * 设置：微信
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	/**
	 * 获取：微信
	 */
	public String getWechat() {
		return wechat;
	}
	/**
	 * 设置：是否充许联系
	 */
	public void setWechatContact(Integer wechatContact) {
		this.wechatContact = wechatContact;
	}
	/**
	 * 获取：是否充许联系
	 */
	public Integer getWechatContact() {
		return wechatContact;
	}
	/**
	 * 设置：微博
	 */
	public void setMicroblog(String microblog) {
		this.microblog = microblog;
	}
	/**
	 * 获取：微博
	 */
	public String getMicroblog() {
		return microblog;
	}
	/**
	 * 设置：是否充许联系
	 */
	public void setMicroblogContact(Integer microblogContact) {
		this.microblogContact = microblogContact;
	}
	/**
	 * 获取：是否充许联系
	 */
	public Integer getMicroblogContact() {
		return microblogContact;
	}
	/**
	 * 设置：地区编码
	 */
	public void setLocatedRegionCode(String locatedRegionCode) {
		this.locatedRegionCode = locatedRegionCode;
	}
	/**
	 * 获取：地区编码
	 */
	public String getLocatedRegionCode() {
		return locatedRegionCode;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建人
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
	public String getBeautyItemCode() {
		return beautyItemCode;
	}
	public void setBeautyItemCode(String beautyItemCode) {
		this.beautyItemCode = beautyItemCode;
	}
	public Date getExLastUpdateTime() {
		return exLastUpdateTime;
	}
	public void setExLastUpdateTime(Date exLastUpdateTime) {
		this.exLastUpdateTime = exLastUpdateTime;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	
}
