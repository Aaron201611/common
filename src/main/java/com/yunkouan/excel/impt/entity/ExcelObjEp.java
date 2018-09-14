package com.yunkouan.excel.impt.entity;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.yunkouan.exception.BizException;


/**
 * Excel模板的Object标签对象
 * <P>ExcelObjEp.java</P>
 * <P>Description: </P>
 * <P>Copyright: Copyright(c) 2016</P>
 * <P>Company: yunkouan.com</P>
 * <P>Date: 2016年12月15日 下午3:54:24</P>
 * @author andy
 */
public class ExcelObjEp {
	private String clazzName;
	private Integer startRow;
	private Integer startCol;
	private List<ExcelAttrEp> list_attr;
	private String alias;
	
	
	/** getset ****************************************************************/

	public String getClazzName() {
		return clazzName;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public Integer getStartCol() {
		return startCol;
	}
	public List<ExcelAttrEp> getList_attr() {
		return list_attr;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public void setStartCol(Integer startCol) {
		this.startCol = startCol;
	}
	public void setList_attr(List<ExcelAttrEp> list_attr) {
		this.list_attr = list_attr;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/** 方法 *************************************************************/
	
	public static ExcelObjEp create(Element root){
		// 读取对象信息
		ExcelObjEp excelObjEp = new ExcelObjEp();
		Element ele_obj = root.element("object");
		Attribute attr_class = ele_obj.attribute("class");
		if ( attr_class == null ) {
			throw new BizException("缺少class元素");
		}
		excelObjEp.clazzName = attr_class.getStringValue();
		Attribute attr_ignoreRow = ele_obj.attribute("ignoreRow");
		if ( attr_ignoreRow != null ) {
			excelObjEp.startRow = Integer.valueOf(attr_ignoreRow.getStringValue());
		} else {
			excelObjEp.startRow = 0;
		}
		Attribute attr_ignoreCol = ele_obj.attribute("ignoreCol");
		if ( attr_ignoreCol != null ) {
			excelObjEp.startCol = Integer.valueOf(attr_ignoreCol.getStringValue());
		} else {
			excelObjEp.startCol = 0;
		}
		
		// 读取属性
		excelObjEp.list_attr = new ArrayList<ExcelAttrEp>();
		List<Element> list_ele_attr = ele_obj.elements("attr");
		for (int i = 0; i < list_ele_attr.size(); i++) {
			Element ele_attr = list_ele_attr.get(i);
			String colName = ele_attr.getStringValue();
			ExcelAttrEp e = new ExcelAttrEp();
			e.setColName(colName);
			
			Attribute attr_format = ele_attr.attribute("format");
			if ( attr_format != null ) {
				e.setFormat(attr_format.getStringValue());
			}
			excelObjEp.list_attr.add(e);
		}
		return excelObjEp;
	}


	
}
