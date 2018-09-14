/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年2月16日 下午1:21:57<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.formula.functions.T;

/**
 * 公共工具类<br/><br/>
 * @version 2017年2月16日 下午1:21:57<br/>
 * @author andy wang<br/>
 */
public class PubUtil {
	
	/**
	 * 保留小数后两位
	 * @param num 格式化的数字
	 * @param scale 截取小数后第几位
	 * @return
	 * @version 2017年2月16日 下午1:24:13<br/>
	 * @author andy wang<br/>
	 */
	public static Double round ( Double num , Integer scale ) {
		if ( num == null ) {
			return 0d;
		}
		BigDecimal bd = new BigDecimal(num).setScale(scale, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	
	/**
	 * set集合在转换List
	 * @param set set集合
	 * @return
	 * @version 2017年2月17日 下午1:33:12<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings("hiding")
	public static <T> List<T> set2List ( Set<T> set ) {
		if ( set == null || set.isEmpty() ) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		list.addAll(set);
		return list;
	}
	
	/**
	 * 判断集合是否为空
	 * @param collection 需要判断的集合
	 * @return 判断结果
	 * —— true 集合空
	 * —— false 集合不为空
	 * @version 2017年2月20日 上午11:50:57<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty ( Collection collection ) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * 判断Map集合是否为空
	 * @param map Map集合
	 * @return 判断结果
	 * —— true 集合空
	 * —— false 集合不为空
	 * @version 2017年3月3日 上午11:50:55<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty ( Map map ) {
		return map == null || map.isEmpty();
	}
	
}
