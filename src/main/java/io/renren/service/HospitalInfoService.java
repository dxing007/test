package io.renren.service;

import io.renren.entity.HospitalInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 医院信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-29 17:09:03
 */
public interface HospitalInfoService {
	
	HospitalInfoEntity queryObject(Integer id);
	
	List<HospitalInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HospitalInfoEntity hospitalInfo);
	
	void update(HospitalInfoEntity hospitalInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
