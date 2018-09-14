package com.yunkouan.excel.impt.entity;

import com.yunkouan.util.StringUtil;


/**
 * Excel导出对象
 * <P>ExcelAttrEp.java</P>
 * <P>Description: </P>
 * <P>Copyright: Copyright(c) 2016</P>
 * <P>Company: yunkouan.com</P>
 * <P>Date: 2016年12月15日 下午3:27:50</P>
 * @author andy
 */
public class ExcelAttrEp {
	
	private String colName;
	private String format;

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

	
	
	
}
