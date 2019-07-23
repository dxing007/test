package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-24 23:39:32
 */
public class BaseDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	//数据字典id(主键)
	private String dictId;
	//数据字典类别代码
	private String dictTypeCode;
	//数据字典类别名称
	private String dictTypeName;
	//数据字典项目名称
	private String dictItemName;
	//数据字典项目值
	private String dictItemValue;
	//数据字典项目代码(可为空)
	private String dictItemCode;
	//排序字段
	private Integer dictSort;
	//1:使用 0:停用
	private String dictEnable;
	//备注
	private String dictMemo;

	/**
	 * 设置：数据字典id(主键)
	 */
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	/**
	 * 获取：数据字典id(主键)
	 */
	public String getDictId() {
		return dictId;
	}
	/**
	 * 设置：数据字典类别代码
	 */
	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;
	}
	/**
	 * 获取：数据字典类别代码
	 */
	public String getDictTypeCode() {
		return dictTypeCode;
	}
	/**
	 * 设置：数据字典类别名称
	 */
	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}
	/**
	 * 获取：数据字典类别名称
	 */
	public String getDictTypeName() {
		return dictTypeName;
	}
	/**
	 * 设置：数据字典项目名称
	 */
	public void setDictItemName(String dictItemName) {
		this.dictItemName = dictItemName;
	}
	/**
	 * 获取：数据字典项目名称
	 */
	public String getDictItemName() {
		return dictItemName;
	}
	/**
	 * 设置：数据字典项目值
	 */
	public void setDictItemValue(String dictItemValue) {
		this.dictItemValue = dictItemValue;
	}
	/**
	 * 获取：数据字典项目值
	 */
	public String getDictItemValue() {
		return dictItemValue;
	}
	/**
	 * 设置：数据字典项目代码(可为空)
	 */
	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}
	/**
	 * 获取：数据字典项目代码(可为空)
	 */
	public String getDictItemCode() {
		return dictItemCode;
	}
	/**
	 * 设置：排序字段
	 */
	public void setDictSort(Integer dictSort) {
		this.dictSort = dictSort;
	}
	/**
	 * 获取：排序字段
	 */
	public Integer getDictSort() {
		return dictSort;
	}
	/**
	 * 设置：1:使用 0:停用
	 */
	public void setDictEnable(String dictEnable) {
		this.dictEnable = dictEnable;
	}
	/**
	 * 获取：1:使用 0:停用
	 */
	public String getDictEnable() {
		return dictEnable;
	}
	/**
	 * 设置：备注
	 */
	public void setDictMemo(String dictMemo) {
		this.dictMemo = dictMemo;
	}
	/**
	 * 获取：备注
	 */
	public String getDictMemo() {
		return dictMemo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
}
