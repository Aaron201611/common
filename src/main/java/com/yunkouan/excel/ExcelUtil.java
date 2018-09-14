/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * 创建日期:<br/> 2017年2月8日 下午8:11:23<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.excel;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.yunkouan.excel.impt.ExcelImpt;
import com.yunkouan.util.XmlUtil;

/**
 * Excel工具类<br/><br/>
 * <b>创建日期</b>:<br/> 2017年2月8日 下午8:11:23<br/>
 * @author andy wang<br/>
 */
public class ExcelUtil {
	
	/**
	 * 日志对象
	 * @version 2017年2月20日上午10:47:21<br/>
	 * @author andy wang<br/>
	 */
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 导入模板
	 * @author andy wang<br/>
	 */
	private static Map<String,ExcelImpt> map_impt;
	
	/**
	 * 数据库查询对象
	 * @author andy wang<br/>
	 */
	private static SqlSessionTemplate sqlSession;
	
	/**
	 * 导出模板的地址
	 * @author andy wang<br/>
	 */
	private String exptUrl;
	
	/**
	 * 导入模板的地址
	 * @author andy wang<br/>
	 */
	private String imptUrl;
	
	
	/**
	 * 读取模板
	 * @throws DocumentException
	 * <P>@author andy</P>
	 * <P>Date 2016年12月15日 下午2:35:17</P>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public void loadModel() throws Exception {
		log.debug("loading Excel model...");
		log.debug("start loading Excel import model...");
		map_impt = new HashMap<String,ExcelImpt>();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] imptRes = resolver.getResources(imptUrl);
		if ( imptRes != null ) {
			for (int i = 0; i < imptRes.length; i++) {
				Resource resource = imptRes[i];
				String filename = resource.getFilename();
				File file = resource.getFile();
				String key = filename.substring(0,filename.indexOf(".xml"));
				try {
					map_impt.put(key, ExcelImpt.readRoot(XmlUtil.readXML(file)));
					log.debug("import model %s loading success...",filename);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("import model %s loading fail...",filename);
				}
			}
		}
		log.debug("complete loading Excel import model...");
		log.debug("loaded Excel model...");
	}
	
	/**
	 * 解析Excel
	 * @param name - 模板名称
	 * @param fileName - Excel位置
	 * @return 不能解析时，返回null
	 * @throws Exception
	 * <P>@author andy</P>
	 * <P>Date 2017年1月13日 下午11:29:32</P>
	 */
	public static List<Object> parseExcel( String name , String fileName ) throws Exception {
		ExcelImpt excelImpt = map_impt.get(name);
		if ( excelImpt == null ) {
			return null;
		}
		List<Object> importExcel = excelImpt.importExcel(fileName,ExcelUtil.sqlSession);
		return importExcel;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		ExcelUtil.sqlSession = sqlSession;
	}

	
	
	/* getset **************************************************************/

	public String getExptUrl() {
		return exptUrl;
	}

	public void setExptUrl(String exptUrl) {
		this.exptUrl = exptUrl;
	}

	public String getImptUrl() {
		return imptUrl;
	}

	public void setImptUrl(String imptUrl) {
		this.imptUrl = imptUrl;
	}
	
}
