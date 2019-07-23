package io.renren.entity.extend;

import io.renren.entity.CustomerExtendEntity;
import io.renren.entity.CustomerHospitalEntity;

import java.util.List;

public class CustomerListVo extends CustomerExtendEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7462138249860375172L;
	
	private String beautyItemCode;
	
	private List<CustomerHospitalEntity> hospitalList;

	public String getBeautyItemCode() {
		return beautyItemCode;
	}

	public void setBeautyItemCode(String beautyItemCode) {
		this.beautyItemCode = beautyItemCode;
	}

	public List<CustomerHospitalEntity> getHospitalList() {
		return hospitalList;
	}

	public void setHospitalList(List<CustomerHospitalEntity> hospitalList) {
		this.hospitalList = hospitalList;
	}
	
	
	
}
