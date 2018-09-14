package com.yunkouan.excel.impt;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunkouan.excel.convert.ExcelConvert;
import com.yunkouan.excel.impt.entity.ExcelImptCol;
import com.yunkouan.excel.impt.entity.ExcelObjEp;
import com.yunkouan.exception.BizException;
import com.yunkouan.util.StringUtil;

/**
 * Excel导入
 * <P>ExcelImpt.java</P>
 * <P>Description: </P>
 * <P>Copyright: Copyright(c) 2016</P>
 * <P>Company: yunkouan.com</P>
 * <P>Date: 2016年12月19日 上午8:47:53</P>
 * <P>@author andy</P>
 */
public class ExcelImpt {
	
	private static Logger log = LoggerFactory.getLogger(ExcelImpt.class);
	
	public static final String EC_SQLSESSION = "sst" ;
	public static final String EC_VALUE = "val" ;
	public static final String EC_COL = "colName" ;
	
	/**
	 * obj标签
	 * key - alias属性别名
	 * value - class属性类
	 * add by andy
	 * modify : 改由excelObj代替
	 */
	private ExcelObjEp excelObj = new ExcelObjEp();
	/**
	 * 起始行（默认起始行号为0）
	 * add by andy 
	 */
	private Integer startRow = 0;
	/**
	 * 起始列（默认起始行号为0）
	 * add by andy 
	 */
	private Integer startCol = 0;
	/**
	 * column标签集合
	 * add by andy 
	 */
	private List<ExcelImptCol> list_column = new ArrayList<ExcelImptCol>();
	
	/**
	 * 从根元素开始读取xml
	 * @param name - xml名
	 * @param root - 根元素
	 * <P>@author andy</P>
	 * <P>Date 2016年12月19日 下午8:45:54</P>
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unchecked")
	public static ExcelImpt readRoot(Element root) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ExcelImpt excelImpt = new ExcelImpt();
		List<Element> list_obj = root.elements("object");
		if ( list_obj == null || list_obj.isEmpty() ) {
			log.error("没有object标签");
			return excelImpt;
		}
		for (int i = 0; i < list_obj.size(); i++) {
			Element element = list_obj.get(i);
			ExcelObjEp excelObjEp = new ExcelObjEp();
			excelObjEp.setClazzName(element.attributeValue("class"));
			excelObjEp.setAlias(element.attributeValue("alias"));
			//excelImpt.map_obj.put(excelObjEp.getAlias(), excelObjEp);
			excelImpt.excelObj = excelObjEp;
		}
		
		Element ignore = root.element("ignore");
		if ( ignore != null ) {
			Attribute attr_ignoreRow = ignore.attribute("ignoreRow");
			if ( attr_ignoreRow != null ) {
				excelImpt.startRow = Integer.valueOf(attr_ignoreRow.getStringValue());
			}
			Attribute attr_ignoreCol = ignore.attribute("ignoreCol");
			if ( attr_ignoreCol != null ) {
				excelImpt.startCol = Integer.valueOf(attr_ignoreCol.getStringValue());
			}
		}
		List<Element> list_column = root.elements("column");
		if ( list_column == null || list_column.isEmpty() ) {
			log.error("没有column标签");
			return excelImpt;
		}
		for (int i = 0; i < list_column.size(); i++) {
			Element element = list_column.get(i);
			List<Element> list_ele_mapping = element.elements("mapping");
			ExcelImptCol col = new ExcelImptCol();
			String nullableString = element.attributeValue("nullable");
			String convertString = element.attributeValue("convert");
			String cacheString = element.attributeValue("cache");
			if ( list_ele_mapping == null || list_ele_mapping.isEmpty() ) {
				col.setColName(element.getStringValue());
				col.setObj(element.attributeValue("obj"));
				col.setFormat(element.attributeValue("format"));
				if ( !StringUtil.isTrimEmpty(convertString) ) {
					Class<ExcelConvert> clz = (Class<ExcelConvert>) Class.forName(convertString);
					ExcelConvert newInstance = clz.newInstance();
					col.setEc(newInstance);
				}
				if ( !StringUtil.isTrimEmpty(cacheString) ) {
					col.setCache(cacheString);
				}
			} else {
				for (int j = 0; j < list_ele_mapping.size(); j++) {
					Element ele_mapping = list_ele_mapping.get(j);
					ExcelImptCol col_mapping = new ExcelImptCol();
					col_mapping.setColName(ele_mapping.getStringValue());
					col_mapping.setObj(ele_mapping.attributeValue("obj"));
					col_mapping.setFormat(ele_mapping.attributeValue("format"));
					if ( !StringUtil.isTrimEmpty(nullableString) ) {
						col_mapping.setNullable(Boolean.valueOf(nullableString));
					}
					if ( !StringUtil.isTrimEmpty(convertString) ) {
						Class<ExcelConvert> clz = (Class<ExcelConvert>) Class.forName(convertString);
						ExcelConvert newInstance = clz.newInstance();
						col_mapping.setEc(newInstance);
					}
					if ( !StringUtil.isTrimEmpty(cacheString) ) {
						col_mapping.setCache(cacheString);
					}
					col.addMapping(col_mapping);
				}
			}
			if ( !StringUtil.isTrimEmpty(nullableString) ) {
				col.setNullable(Boolean.valueOf(nullableString));
			}
			excelImpt.list_column.add(col);
		}
		return excelImpt;
	}
	
	
	/**
	 * 根据xml解析excel
	 * @param fileName - excel文件物理地址
	 * @return
	 * <P>@author andy</P>
	 * <P>Date 2016年12月19日 下午9:05:08</P>
	 * @throws Exception 
	 */
	public List<Object> importExcel(String fileName , SqlSessionTemplate sqlSession ) throws Exception {
		List<Object> list_result = new ArrayList<Object>();
		// 读取文件信息
		FileInputStream inputStream = null;
		Workbook wb = null;
		try {
			inputStream = new FileInputStream(new File(fileName));
			wb = WorkbookFactory.create(inputStream); //这种方式 Excel 2003/2007/2010 都是可以处理的  
			Sheet sheetAt = wb.getSheetAt(0);
			int maxRowNum = sheetAt.getLastRowNum()+1;
			for (int i = 0; i < maxRowNum-this.startRow; i++) {
				Row row = sheetAt.getRow(this.startRow+i);
				Class<?> clz = Class.forName(excelObj.getClazzName());
				Object newInstance = clz.newInstance();
				if ( row == null ) {
					list_result.add(newInstance);
					continue;
				}
				Map<String,Object> map_result = new HashMap<String,Object>();
				map_result.put(excelObj.getAlias(),newInstance);
				Boolean existsVal = false;
				for (int j = 0; j < this.list_column.size()-this.startCol; j++) {
					ExcelImptCol excelImptCol = this.list_column.get(j);
					List<ExcelImptCol> map_mapping = excelImptCol.getMap_mapping();
					if ( map_mapping == null || map_mapping.isEmpty() ) {
						map_mapping = new ArrayList<ExcelImptCol>();
						map_mapping.add(excelImptCol);
					}
					// 设置属性值
					Cell cell = row.getCell(j+this.startCol);
					if ( cell != null && !StringUtil.isTrimEmpty(getCellValue(cell)) ) {
						existsVal = true;
					}
					this.setFieldByMapping(map_result, map_mapping,cell,sqlSession);
				}
				if ( existsVal ) {
					list_result.add(newInstance);
				}
			}
			return list_result;
		} catch (Exception e) {
			throw e;
		} finally {
			if ( inputStream != null ) {
				inputStream.close();
			}
			if ( wb != null ) {
				wb.close();
			}
		}
	}
	public static void main(String[] args) {
		String str = "assBillbill";
		System.out.println(str.split("\\.")[0]);
	}
	
	
	/**
	 * 获取单元格的值
	 * @param cell 单元格对象
	 * @return
	 * @version 2017年3月22日 下午4:05:39<br/>
	 * @author andy wang<br/>
	 */
	private static String getCellValue ( Cell cell ) {
		String cellValue = null;  
		DecimalFormat df = new DecimalFormat("#");
		switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_STRING:  
            cellValue = cell.getRichStringCellValue().getString().trim();  
            break;  
        case HSSFCell.CELL_TYPE_NUMERIC:  
        	if (HSSFDateUtil.isCellDateFormatted(cell)) {    //判断是日期类型  
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");  
                Date dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());//获取成DATE类型     
                cellValue = dateformat.format(dt);   
            }else{  
            	//下面的方法会丢失精度，modify by 王通  
//                cellValue = df.format(cell.getNumericCellValue());  
                cellValue = String.valueOf(cell.getNumericCellValue());  
            }  
            break;  
        case HSSFCell.CELL_TYPE_BOOLEAN:  
            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
            break;  
        case HSSFCell.CELL_TYPE_FORMULA:  
            cellValue = cell.getCellFormula();  
            break;  
        default:  
            cellValue = "";  
        }  
        return cellValue;  
	}
	
	/**
	 * 设置属性值
	 * @param map_obj - 对象map
	 * @param map_mapping - 对应xml的column元素信息
	 * @param cell - Excel单元格
	 * @return
	 * @throws Exception
	 * <P>@author andy</P>
	 * <P>Date 2016年12月19日 下午9:41:14</P>
	 */
	private Map<String,Object> setFieldByMapping( Map<String,Object> map_obj , List<ExcelImptCol> map_mapping 
			, Cell cell , SqlSessionTemplate sqlSession ) throws Exception {
		for (int i = 0; i < map_mapping.size(); i++) {
			try {
				ExcelImptCol excelImptCol = map_mapping.get(i);
				Boolean nullable = excelImptCol.getNullable();
				if ( !nullable ) {
					if ( cell == null ) {
						// 不允许为空的字段，数据却为空的情况
						throw new BizException(excelImptCol.getColName() + "数据不能为空");
					}
				} else if ( cell == null ) {
					return map_obj;
				}
				String stringCellValue = null;
				try {
					stringCellValue = getCellValue(cell);
				} catch (Exception e) {
					log.error("Excel " + excelImptCol.getColName() + " 字段读取错误");
					throw new Exception("Excel load fail");
				}
				// 转换
				ExcelConvert ec = excelImptCol.getEc();
				if ( ec != null ) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put(EC_COL, excelImptCol.getColName());
					map.put(EC_VALUE, stringCellValue);
					map.put(EC_SQLSESSION, sqlSession);
					stringCellValue = ec.Convert(map);
				}
				// 获取mapping对象
				Object object = this.getMappingObj(map_obj, excelImptCol.getObj());
				
				Field field = this.getField(object, excelImptCol.getColName());
				if ( field == null ) {
					log.error("Excel导入 【" + excelImptCol.getColName() + "】字段 不存在");
				}
				field.setAccessible(true);
				
				Class<?> type = field.getType();
				try {
					if ( StringUtil.isTrimEmpty(stringCellValue) ) {
						continue;
					}
					if ( type.getName().equals(Integer.class.getName()) ) {
						int index = stringCellValue.indexOf(".");
						if (index != -1) {
							stringCellValue = stringCellValue.substring(0, index);
						}
						field.set(object, Integer.valueOf(stringCellValue));
					} else if ( type.getName().equals(Double.class.getName())) {
						field.set(object, Double.valueOf(stringCellValue));
					} else if ( type.getName().equals(Long.class.getName())) {
						int index = stringCellValue.indexOf(".");
						if (index != -1) {
							stringCellValue = stringCellValue.substring(0, index);
						}
						field.set(object, Long.valueOf(stringCellValue));
					} else if ( type.getName().equals(String.class.getName())) {
						int index = stringCellValue.indexOf(".0");
						if (index != -1) {
							stringCellValue = stringCellValue.substring(0, index);
						}
						field.set(object, String.valueOf(stringCellValue));
					} else if ( type.getName().equals(Date.class.getName())) {
						Date date = this.parseDate(excelImptCol.getFormat(),stringCellValue);
						field.set(object, date);
					}
				} catch (Exception e) {
					continue;
				}
			} catch (Exception e) {
				throw e;
			}
		}
		return map_obj;
	}
	
	
	/**
	 * 获取mapping的对象（最多只能用两层）
	 * @param map_obj - 对象map
	 * @param objString - 
	 * @return
	 * <P>@author andy</P>
	 * <P>Date 2017年1月8日 下午8:16:19</P>
	 * @throws Exception 
	 */
	private Object getMappingObj ( Map<String,Object> map_obj , String objString ) throws Exception {
		String[] split = objString.split("\\.");
		Object object = null;
		if ( split.length > 1 ) {
			String objKey = split[split.length-1];
			String objParentKey = split[split.length-2];
			object = map_obj.get(objKey);
			if ( object == null ) {
				Object parentObj = map_obj.get(objParentKey);
				Field field = this.getField(parentObj, objKey);
				field.setAccessible(true);
				Class<?> type = field.getType();
				object = type.newInstance();
				map_obj.put(objKey, object);
				field.set(parentObj, object);
			}
		} else {
			object = map_obj.get(objString);
		}
		return object;
	}
	
	/**
	 * 格式化日期
	 * @param format - 日期格式
	 * @param value - 单元格内容
	 * @return 单元格内容转换成日期值
	 * <P>@author andy</P>
	 * <P>Date 2016年12月18日 下午11:27:25</P>
	 */
	private Date parseDate(String format, String value) {
		Date result_date = null;
		String sourceFormat = format;
		String[] fmt = format.split(",");
		SimpleDateFormat sdf = null;
		for (int i = 0; i < fmt.length; i++) {
			sdf = new SimpleDateFormat(fmt[i]);
			try {
				result_date = sdf.parse(value);
			} catch (ParseException e) {
			}
		}
		if ( result_date == null ) {
			throw new BizException(String.format("%s格式不正确，请按 %s 日期格式输入",value,format));
		}
		return result_date;
	}

	/**
	 * 获取属性值
	 * @param newInstance - 属性对应的对象
	 * @param fieldName - 属性名
	 * @return 对象（参数）对应的属性对象
	 * <P>@author andy</P>
	 * <P>Date 2016年12月18日 下午11:02:25</P>
	 */
	private Field getField( Object newInstance , String fieldName ) {
		Field field = null;
		for (Class<?> clazz = newInstance.getClass();clazz != null && clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				if ( field != null ) {
					break;
				}
			} catch (NoSuchFieldException e) {
				continue;
			}
		}
		return field;
	}
}
