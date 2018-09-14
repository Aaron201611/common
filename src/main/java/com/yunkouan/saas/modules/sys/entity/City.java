package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yunkouan.base.BaseObj;

public class City extends BaseObj {
    /**
	 * 
	 * @version 2017年2月20日 上午9:59:07<br/>
	 * @author 王通<br/>
	 */
	private static final long serialVersionUID = 8326054933532182882L;

	@Id
	private String id;
	@Column(name="c_level")
	private String level;
	private String cnName;
	private String enName;
	/**
	 * 属性 id getter方法
	 * @return 属性id
	 * @author 王通<br/>
	 */
	public String getId() {
		return id;
	}
	/**
	 * 属性 id setter方法
	 * @param id 设置属性id的值
	 * @author 王通<br/>
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 属性 level getter方法
	 * @return 属性level
	 * @author 王通<br/>
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 属性 level setter方法
	 * @param level 设置属性level的值
	 * @author 王通<br/>
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 属性 cnName getter方法
	 * @return 属性cnName
	 * @author 王通<br/>
	 */
	public String getCnName() {
		return cnName;
	}
	/**
	 * 属性 cnName setter方法
	 * @param cnName 设置属性cnName的值
	 * @author 王通<br/>
	 */
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	/**
	 * 属性 enName getter方法
	 * @return 属性enName
	 * @author 王通<br/>
	 */
	public String getEnName() {
		return enName;
	}
	/**
	 * 属性 enName setter方法
	 * @param enName 设置属性enName的值
	 * @author 王通<br/>
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}

	/* method ********************************************/
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}