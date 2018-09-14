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

import java.util.ArrayList;
import java.util.List;

import com.yunkouan.base.BaseVO;

/**
 * 省份VO
 * @version 2017年6月9日 下午1:46:17<br/>
 * @author 王通<br/>
 */
public class ProvinceVO extends BaseVO {

	private static final long serialVersionUID = 6023457494532134420L;

	/**
	 * 省份名称
	 * @version 2017年6月9日 下午1:39:31<br/>
	 * @author 王通<br/>
	 */
	private String provinceName;

	/**
	 * 城市列表
	 * @version 2017年6月9日 下午1:39:31<br/>
	 * @author 王通<br/>
	 */
	private List<CityVO> cityList = new ArrayList<CityVO>();

	/**
	 * 属性 provinceName getter方法
	 * @return 属性provinceName
	 * @author 王通<br/>
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * 属性 provinceName setter方法
	 * @param provinceName 设置属性provinceName的值
	 * @author 王通<br/>
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * 属性 cityList getter方法
	 * @return 属性cityList
	 * @author 王通<br/>
	 */
	public List<CityVO> getCityList() {
		return cityList;
	}

	/**
	 * 属性 cityList setter方法
	 * @param cityList 设置属性cityList的值
	 * @author 王通<br/>
	 */
	public void setCityList(List<CityVO> cityList) {
		this.cityList = cityList;
	}

	/**
	 * @param nowCityVo
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月9日 下午2:24:24<br/>
	 * @author 王通<br/>
	 */
	public void addCity(CityVO cityVo) {
		this.cityList.add(cityVo);
	}



}
