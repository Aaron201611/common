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
 * 城市VO
 * @version 2017年6月9日 下午1:46:17<br/>
 * @author 王通<br/>
 */
public class CityVO extends BaseVO {

	private static final long serialVersionUID = 6023457494532134420L;

	/**
	 * 城市名称
	 * @version 2017年6月9日 下午1:39:31<br/>
	 * @author 王通<br/>
	 */
	private String cityName;
	
	/**
	 * 区县名称
	 * @version 2017年6月9日 下午1:39:31<br/>
	 * @author 王通<br/>
	 */
	private List<CountyVO> countyList = new ArrayList<CountyVO>();

	/**
	 * 属性 cityName getter方法
	 * @return 属性cityName
	 * @author 王通<br/>
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * 属性 cityName setter方法
	 * @param cityName 设置属性cityName的值
	 * @author 王通<br/>
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 属性 countyList getter方法
	 * @return 属性countyList
	 * @author 王通<br/>
	 */
	public List<CountyVO> getcountyList() {
		return countyList;
	}

	/**
	 * 属性 countyList setter方法
	 * @param countyList 设置属性countyList的值
	 * @author 王通<br/>
	 */
	public void setcountyList(List<CountyVO> countyList) {
		this.countyList = countyList;
	}

	/**
	 * @param CountyVo
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月9日 下午2:27:47<br/>
	 * @author 王通<br/>
	 */
	public void addCounty(CountyVO CountyVo) {
		this.countyList.add(CountyVo);
	}




}
