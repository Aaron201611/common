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

/**
 * 区县VO
 * @version 2017年6月9日 下午1:46:17<br/>
 * @author 王通<br/>
 */
public class CountyVO extends BaseVO {

	private static final long serialVersionUID = 6023457494532134420L;

	/**
	 * 区县名称
	 * @version 2017年6月9日 下午1:39:31<br/>
	 * @author 王通<br/>
	 */
	private String countyName;

	/**
	 * 属性 countyName getter方法
	 * @return 属性countyName
	 * @author 王通<br/>
	 */
	public String getCountyName() {
		return countyName;
	}

	/**
	 * 属性 countyName setter方法
	 * @param countyName 设置属性countyName的值
	 * @author 王通<br/>
	 */
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}





}
