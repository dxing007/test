package io.renren.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.util.StringUtils;



/**
 * 医院信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-29 17:09:03
 */
public class HospitalInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//医院名称
	private String name;
	
	public String getSelectValue(){
		return id +"_"+name;
	}
	
	//状态 0 为启用 1 为禁用
	private Integer statue;
	//地区编码
	private String regionCode;
	//是否公立医院 0 为公立 1 为私立
	private String isPublic;
	//医院网站
	private String url;
	//返点
	private String rebate;
	//权重影响排名
	private Integer weightCoefficient;
	//项目
	private String project;
	//医生信息
	private String doctorInfo;
	
	public String getDoctorInfoIntr(){
		return getIntr(getDoctorInfo(), 10);
	}
	
	private String getIntr(String value,int length){
		if(StringUtils.isEmpty(value) || value.length() <= length ){
			return "";
		}
		
		return value.substring(0,length) +"……";
	}
	
	//医院描述
	private String describe;
	
	public String getDescribeIntr(){
		return getIntr(getDescribe(), 10);
	}
	//创建时间
	private Date createTime;
	//创建人
	private Integer createUserId;
	//更新时间
	private Date lastUpdateTime;
	//更新人
	private Integer lastUpdateUserId;

	private String adminName;
	
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
	 * 设置：医院名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：医院名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：状态 0 为启用 1 为禁用
	 */
	public void setStatue(Integer statue) {
		this.statue = statue;
	}
	/**
	 * 获取：状态 0 为启用 1 为禁用
	 */
	public Integer getStatue() {
		return statue;
	}
	/**
	 * 设置：地区编码
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * 获取：地区编码
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * 设置：是否公立医院 0 为公立 1 为私立
	 */
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	/**
	 * 获取：是否公立医院 0 为公立 1 为私立
	 */
	public String getIsPublic() {
		return isPublic;
	}
	/**
	 * 设置：医院网站
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：医院网站
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：返点
	 */
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	/**
	 * 获取：返点
	 */
	public String getRebate() {
		return rebate;
	}
	/**
	 * 设置：权重影响排名
	 */
	public void setWeightCoefficient(Integer weightCoefficient) {
		this.weightCoefficient = weightCoefficient;
	}
	/**
	 * 获取：权重影响排名
	 */
	public Integer getWeightCoefficient() {
		return weightCoefficient;
	}
	/**
	 * 设置：项目
	 */
	public void setProject(String project) {
		this.project = project;
	}
	/**
	 * 获取：项目
	 */
	public String getProject() {
		return project;
	}
	/**
	 * 设置：医生信息
	 */
	public void setDoctorInfo(String doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	/**
	 * 获取：医生信息
	 */
	public String getDoctorInfo() {
		return doctorInfo;
	}
	/**
	 * 设置：医院描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 获取：医院描述
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
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	
}
