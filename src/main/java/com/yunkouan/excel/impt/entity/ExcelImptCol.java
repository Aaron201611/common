package com.yunkouan.excel.impt.entity;

import java.util.ArrayList;
import java.util.List;

import com.yunkouan.excel.convert.ExcelConvert;
import com.yunkouan.util.StringUtil;

/**
 * 
 * <P>ExcelImptCol.java</P>
 * <P>Description: </P>
 * <P>Copyright: Copyright(c) 2016</P>
 * <P>Company: yunkouan.com</P>
 * <P>Date: 2016年12月18日 下午11:55:03</P>
 * @author andy
 */
public class ExcelImptCol {
	/**
	 * column标签内容，代表对应对象的属性
	 * add by andy
	 */
	private String colName;
	/**
	 * 转换时的日期格式
	 * add by andy
	 */
	private String format;
	/**
	 * mapping标签对象
	 * add by andy
	 */
	private List<ExcelImptCol> map_mapping;
	/**
	 * 转换对应的对象
	 * add by andy
	 */
	private String obj;
	/**
	 * 是否可为空
	 * add by andy
	 */
	private Boolean nullable;
	
	/**
	 * Excel内容转换
	 */
	private ExcelConvert ec;
	
	/**
	 * 缓存查询
	 */
	private String cache;
	
	
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getFormat() {
		if ( StringUtil.isEmpty(this.format) ) {
			return "yyyy-MM-dd HH:mm:ss";
		}
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public Boolean getNullable() {
		if ( this.nullable == null ) {
			return true;
		}
		return nullable;
	}
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}
	public List<ExcelImptCol> getMap_mapping() {
		return map_mapping;
	}
	public void setMap_mapping(List<ExcelImptCol> map_mapping) {
		this.map_mapping = map_mapping;
	}
	public ExcelConvert getEc() {
		return ec;
	}
	public void setEc(ExcelConvert ec) {
		this.ec = ec;
	}
	public String getCache() {
		return cache;
	}
	public void setCache(String cache) {
		this.cache = cache;
	}
	
	
	/**
	 * 存放mapping标签信息
	 * @param key - mapping标签中的obj属性
	 * @param value - mapping标签对象
	 * <P>@author andy</P>
	 * <P>Date 2016年12月19日 下午8:30:00</P>
	 */
	public void addMapping(ExcelImptCol value ) {
		if ( this.map_mapping == null ) {
			map_mapping = new ArrayList<ExcelImptCol>();
		}
		map_mapping.add(value);
	}
}
