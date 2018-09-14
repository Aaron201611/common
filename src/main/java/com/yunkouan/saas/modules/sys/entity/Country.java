package com.yunkouan.saas.modules.sys.entity;

import java.math.BigDecimal;

import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yunkouan.base.BaseObj;

public class Country extends BaseObj {
    /**
	 * 
	 * @version 2017年2月20日 上午9:59:07<br/>
	 * @author 王通<br/>
	 */
	private static final long serialVersionUID = 8326054933532182882L;

	@Id
	private String countryCode;
	
	private String isoE;
	
	private String counCName;
	
	private String abbrC;
	
	private String examMark;
	
	private String highLow;
	
	private String tdsHighLow;
	
	private String continent;
	
	private BigDecimal longitude;
	
	private BigDecimal latitude;

	/**
	 * 属性 countryCode getter方法
	 * @return 属性countryCode
	 * @author 王通<br/>
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * 属性 countryCode setter方法
	 * @param countryCode 设置属性countryCode的值
	 * @author 王通<br/>
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * 属性 isoE getter方法
	 * @return 属性isoE
	 * @author 王通<br/>
	 */
	public String getIsoE() {
		return isoE;
	}

	/**
	 * 属性 isoE setter方法
	 * @param isoE 设置属性isoE的值
	 * @author 王通<br/>
	 */
	public void setIsoE(String isoE) {
		this.isoE = isoE;
	}

	/**
	 * 属性 counCName getter方法
	 * @return 属性counCName
	 * @author 王通<br/>
	 */
	public String getCounCName() {
		return counCName;
	}

	/**
	 * 属性 counCName setter方法
	 * @param counCName 设置属性counCName的值
	 * @author 王通<br/>
	 */
	public void setCounCName(String counCName) {
		this.counCName = counCName;
	}

	/**
	 * 属性 abbrC getter方法
	 * @return 属性abbrC
	 * @author 王通<br/>
	 */
	public String getAbbrC() {
		return abbrC;
	}

	/**
	 * 属性 abbrC setter方法
	 * @param abbrC 设置属性abbrC的值
	 * @author 王通<br/>
	 */
	public void setAbbrC(String abbrC) {
		this.abbrC = abbrC;
	}

	/**
	 * 属性 examMark getter方法
	 * @return 属性examMark
	 * @author 王通<br/>
	 */
	public String getExamMark() {
		return examMark;
	}

	/**
	 * 属性 examMark setter方法
	 * @param examMark 设置属性examMark的值
	 * @author 王通<br/>
	 */
	public void setExamMark(String examMark) {
		this.examMark = examMark;
	}

	/**
	 * 属性 highLow getter方法
	 * @return 属性highLow
	 * @author 王通<br/>
	 */
	public String getHighLow() {
		return highLow;
	}

	/**
	 * 属性 highLow setter方法
	 * @param highLow 设置属性highLow的值
	 * @author 王通<br/>
	 */
	public void setHighLow(String highLow) {
		this.highLow = highLow;
	}

	/**
	 * 属性 tdsHighLow getter方法
	 * @return 属性tdsHighLow
	 * @author 王通<br/>
	 */
	public String getTdsHighLow() {
		return tdsHighLow;
	}

	/**
	 * 属性 tdsHighLow setter方法
	 * @param tdsHighLow 设置属性tdsHighLow的值
	 * @author 王通<br/>
	 */
	public void setTdsHighLow(String tdsHighLow) {
		this.tdsHighLow = tdsHighLow;
	}

	/**
	 * 属性 continent getter方法
	 * @return 属性continent
	 * @author 王通<br/>
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * 属性 continent setter方法
	 * @param continent 设置属性continent的值
	 * @author 王通<br/>
	 */
	public void setContinent(String continent) {
		this.continent = continent;
	}

	/**
	 * 属性 longitude getter方法
	 * @return 属性longitude
	 * @author 王通<br/>
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}

	/**
	 * 属性 longitude setter方法
	 * @param longitude 设置属性longitude的值
	 * @author 王通<br/>
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	/**
	 * 属性 latitude getter方法
	 * @return 属性latitude
	 * @author 王通<br/>
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * 属性 latitude setter方法
	 * @param latitude 设置属性latitude的值
	 * @author 王通<br/>
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/* method ********************************************/
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}