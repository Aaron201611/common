/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年2月14日 下午5:23:58<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.yunkouan.base.BaseEntity;

/**
 * 系统参数表<br/><br/>
 * @version 2017年2月14日 下午5:23:58<br/>
 * @author andy wang<br/>
 */
public class SysSystemParam extends BaseEntity {
	
	private static final long serialVersionUID = 2596406162411131094L;
	
	/**
	 * 参数id
	 * @version 2017年2月14日下午5:24:36<br/>
	 * @author andy wang<br/>
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String paramId;

	/**
	 * 参数组
	 * @version 2017年2月14日下午5:24:45<br/>
	 * @author andy wang<br/>
	 */
    private String paramGroup;

    /**
	 * 参数类型
	 * @version 2017年2月14日下午5:24:57<br/>
	 * @author andy wang<br/>
	 */
    private String paramName;

    /**
	 * 参数索引键
	 * @version 2017年2月14日下午5:25:06<br/>
	 * @author andy wang<br/>
	 */
    private String paramKey;
    
    /**
	 * 参数值
	 * @version 2017年2月14日下午5:25:18<br/>
	 * @author andy wang<br/>
	 */
    private String paramValue;

    private String reverse;

    /**
	 * 备注
	 * @version 2017年2月14日下午5:25:39<br/>
	 * @author andy wang<br/>
	 */
    private String note;

    /**
	 * 序列ID
	 * @version 2017年2月14日下午5:30:14<br/>
	 * @author andy wang<br/>
	 */
    private Integer paramId2;
    
    
    /**
	 * 参数状态
	 * @version 2017年3月14日下午1:57:05<br/>
	 * @author andy wang<br/>
	 */
    private Integer paramStatus;
    

	/**
	 * 属性 paramId getter方法
	 * @return 属性paramId
	 * @author andy wang<br/>
	 */
	public String getParamId() {
		return paramId;
	}

	/**
	 * 属性 paramId setter方法
	 * @param paramId 设置属性paramId的值
	 * @author andy wang<br/>
	 */
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	/**
	 * 属性 paramStatus getter方法
	 * @return 属性paramStatus
	 * @author andy wang<br/>
	 */
	public Integer getParamStatus() {
		return paramStatus;
	}

	/**
	 * 属性 paramStatus setter方法
	 * @param paramStatus 设置属性paramStatus的值
	 * @author andy wang<br/>
	 */
	public void setParamStatus(Integer paramStatus) {
		this.paramStatus = paramStatus;
	}

	/**
	 * 属性 note getter方法
	 * @return 属性note
	 * @author andy wang<br/>
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 属性 note setter方法
	 * @param note 设置属性note的值
	 * @author andy wang<br/>
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 属性 paramId2 getter方法
	 * @return 属性paramId2
	 * @author andy wang<br/>
	 */
	public Integer getParamId2() {
		return paramId2;
	}

	/**
	 * 属性 paramId2 setter方法
	 * @param paramId2 设置属性paramId2的值
	 * @author andy wang<br/>
	 */
	public void setParamId2(Integer paramId2) {
		this.paramId2 = paramId2;
	}

	/**
	 * 属性 paramGroup getter方法
	 * @return 属性paramGroup
	 * @author andy wang<br/>
	 */
	public String getParamGroup() {
		return paramGroup;
	}

	/**
	 * 属性 paramGroup setter方法
	 * @param paramGroup 设置属性paramGroup的值
	 * @author andy wang<br/>
	 */
	public void setParamGroup(String paramGroup) {
		this.paramGroup = paramGroup;
	}

	/**
	 * 属性 paramName getter方法
	 * @return 属性paramName
	 * @author andy wang<br/>
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * 属性 paramName setter方法
	 * @param paramName 设置属性paramName的值
	 * @author andy wang<br/>
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * 属性 paramKey getter方法
	 * @return 属性paramKey
	 * @author andy wang<br/>
	 */
	public String getParamKey() {
		return paramKey;
	}

	/**
	 * 属性 paramKey setter方法
	 * @param paramKey 设置属性paramKey的值
	 * @author andy wang<br/>
	 */
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	/**
	 * 属性 paramValue getter方法
	 * @return 属性paramValue
	 * @author andy wang<br/>
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * 属性 paramValue setter方法
	 * @param paramValue 设置属性paramValue的值
	 * @author andy wang<br/>
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getReverse() {
		return reverse;
	}

	public void setReverse(String reverse) {
		this.reverse = reverse;
	}
	
	

}