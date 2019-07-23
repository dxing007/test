package io.renren.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;



/**
 * 客户信息扩展表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-01 12:49:29
 */
public class CustomerExtendEntity    implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//客户id
	private Integer customerId;
	//信息来源
	private String infoSource;
	//意向地区
	private String intentionAreaCode;
	//备选地区 多个用逗号分隔
	private String bakAreaCode;
	//项目
	private String beautyItemCode;
	//是否发送短信 1 发送 0 不发送
	private Integer sendMessage;
	//描述
	private String describe;
	//创建时间
	private Date createTime;
	//创建人
	private Integer createUserId;
	//最后更新人
	private Date lastUpdateTime;
	//更新时间
	private Integer lastUpdateUserId;
	
	//名称
	private String name;
	//性别 0 为女  1 为男
	private Integer sex;
	//电话号码
	private String telephone;
	//客户主状态（跟单人员关心）
	private String status;
	 
	private  String locatedRegionCode;
	/**
	 * 创建时间
	 */
	private String createUserName;
	
	/**
	 * 创建时间
	 */
	private String lastUpdateUserName;
	
	/**
	 * 批派医院信息
	 */
	private List<CustomerHospitalEntity> customerHospital;
	
	public String getStatusName(){
		return CustomerDocumentaryState.getName(status);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	 * 设置：信息来源
	 */
	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}
	/**
	 * 获取：信息来源
	 */
	public String getInfoSource() {
		return infoSource;
	}
	/**
	 * 设置：意向地区
	 */
	public void setIntentionAreaCode(String intentionAreaCode) {
		this.intentionAreaCode = intentionAreaCode;
	}
	/**
	 * 获取：意向地区
	 */
	public String getIntentionAreaCode() {
		return intentionAreaCode;
	}
	/**
	 * 设置：备选地区 多个用逗号分隔
	 */
	public void setBakAreaCode(String bakAreaCode) {
		this.bakAreaCode = bakAreaCode;
	}
	/**
	 * 获取：备选地区 多个用逗号分隔
	 */
	public String getBakAreaCode() {
		return bakAreaCode;
	}
	/**
	 * 设置：项目
	 */
	public void setBeautyItemCode(String beautyItemCode) {
		this.beautyItemCode = beautyItemCode;
	}
	/**
	 * 获取：项目
	 */
	public String getBeautyItemCode() {
		return beautyItemCode;
	}
	/**
	 * 设置：是否发送短信 1 发送 0 不发送
	 */
	public void setSendMessage(Integer sendMessage) {
		this.sendMessage = sendMessage;
	}
	/**
	 * 获取：是否发送短信 1 发送 0 不发送
	 */
	public Integer getSendMessage() {
		return sendMessage;
	}
	/**
	 * 设置：描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 获取：描述
	 */
	public String getDescribe() {
		return describe;
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
	 * 设置：最后更新人
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * 获取：最后更新人
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：更新时间
	 */
	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public String getLocatedRegionCode() {
		return locatedRegionCode;
	}
	public void setLocatedRegionCode(String locatedRegionCode) {
		this.locatedRegionCode = locatedRegionCode;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getLastUpdateUserName() {
		return lastUpdateUserName;
	}
	public void setLastUpdateUserName(String lastUpdateUserName) {
		this.lastUpdateUserName = lastUpdateUserName;
	}

	public List<CustomerHospitalEntity> getCustomerHospital() {
		return customerHospital;
	}

	public void setCustomerHospital(List<CustomerHospitalEntity> customerHospital) {
		this.customerHospital = customerHospital;
	}
	
	
}
