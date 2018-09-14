package com.yunkouan.excel.convert;

import java.util.Map;

public interface ExcelConvert {
	
	/**
	 * Excel导入类型装换
	 *  sst - 数据库会话类
	 *  value - excel数据
	 *  colName - 对象属性
	 * @return
	 * <P>@author andy</P>
	 * <P>Date 2017年1月14日 下午11:26:33</P>
	 */
	public String Convert ( Map<String,Object> map );
	
}
