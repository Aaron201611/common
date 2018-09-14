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

import java.util.List;

import com.yunkouan.base.BaseVO;

/**
 * 国家VO
 * @version 2017年6月9日 下午1:46:17<br/>
 * @author 王通<br/>
 */
public class CountryVO extends BaseVO {

	private static final long serialVersionUID = 6023457494532134420L;

	/**
	 * 国家名称
	 * @version 2017年6月9日 下午1:39:31<br/>
	 * @author 王通<br/>
	 */
	private String countryName;

	/**
	 * 省份名称
	 * @version 2017年6月9日 下午1:39:31<br/>
	 * @author 王通<br/>
	 */
	private List<ProvinceVO> provinceList;

	/**
	 * 构造方法
	 * @param counCName
	 * @version 2017年6月9日 下午2:06:01<br/>
	 * @author 王通<br/>
	 */
	public CountryVO(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * 属性 countryName getter方法
	 * @return 属性countryName
	 * @author 王通<br/>
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * 属性 countryName setter方法
	 * @param countryName 设置属性countryName的值
	 * @author 王通<br/>
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * 属性 provinceList getter方法
	 * @return 属性provinceList
	 * @author 王通<br/>
	 */
	public List<ProvinceVO> getProvinceList() {
		return provinceList;
	}

	/**
	 * 属性 provinceList setter方法
	 * @param provinceList 设置属性provinceList的值
	 * @author 王通<br/>
	 */
	public void setProvinceList(List<ProvinceVO> provinceList) {
		this.provinceList = provinceList;
	}

	/* getset *********************************************/


}
