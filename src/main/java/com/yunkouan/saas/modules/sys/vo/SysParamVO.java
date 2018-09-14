/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月14日下午1:47:21<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.vo;

import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.modules.sys.entity.SysSystemParam;
import com.yunkouan.util.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 参数VO<br/><br/>
 * @version 2017年3月14日下午1:47:21<br/>
 * @author andy wang<br/>
 */
public class SysParamVO extends BaseVO {

	private static final long serialVersionUID = 6023457494532134420L;

	/**
	 * 构造方法
	 * @version 2017年3月14日下午1:47:21<br/>
	 * @author andy wang<br/>
	 */
	public SysParamVO() {
		this(new SysSystemParam());
	}

	/**
	 * 构造方法
	 * @param sysParam 参数
	 * @version 2017年3月14日下午1:47:21<br/>
	 * @author andy wang<br/>
	 */
	public SysParamVO(SysSystemParam sysParam) {
		super();
		this.sysParam = sysParam;
	}

	/**
	 * 参数对象
	 * @version 2017年3月14日下午1:47:21<br/>
	 * @author andy wang<br/>
	 */
	private SysSystemParam sysParam;

	/**
	 * 参数状态中文描述
	 * @version 2017年3月14日下午1:47:21<br/>
	 * @author andy wang<br/>
	 */
	private String sysParamStatusComment;

	/* getset *********************************************/

	/**
	 * 属性 sysParam getter方法
	 * @return 属性sysParam
	 * @author andy wang<br/>
	 */
	public SysSystemParam getSysParam() {
		return sysParam;
	}

	/**
	 * 属性 sysParam setter方法
	 * @param sysParam 设置属性sysParam的值
	 * @author andy wang<br/>
	 */
	public void setSysParam(SysSystemParam sysParam) {
		this.sysParam = sysParam;
	}

	/**
	 * 属性 sysParamStatusComment getter方法
	 * @return 属性sysParamStatusComment
	 * @author andy wang<br/>
	 */
	public String getSysParamStatusComment() {
		return sysParamStatusComment;
	}

	/**
	 * 属性 sysParamStatusComment setter方法
	 * @param sysParamStatusComment 设置属性sysParamStatusComment的值
	 * @author andy wang<br/>
	 */
	public void setSysParamStatusComment(String sysParamStatusComment) {
		this.sysParamStatusComment = sysParamStatusComment;
	}

	/* method *********************************************/

	@Override
	public Example getExample() {
		if (this.sysParam == null) {
			return null;
		}
		Example example = new Example(SysSystemParam.class);
		Criteria criteria = example.createCriteria();
		if (!StringUtil.isTrimEmpty(this.sysParam.getParamId())) {
			criteria.andEqualTo("paramId", this.sysParam.getParamId());
		}
		if (!StringUtil.isTrimEmpty(this.sysParam.getParamName())) {
			criteria.andEqualTo("paramName", this.sysParam.getParamName());
		}
		if (!StringUtil.isTrimEmpty(this.sysParam.getParamKey())) {
			criteria.andEqualTo("paramKey", this.sysParam.getParamKey());
		}
		if (!StringUtil.isTrimEmpty(this.sysParam.getParamGroup())) {
			criteria.andEqualTo("paramGroup", this.sysParam.getParamGroup());
		}
		return example;
	}

}
