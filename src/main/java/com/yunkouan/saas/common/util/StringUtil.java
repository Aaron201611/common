/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * 创建日期:<br/> 2017年2月8日 下午5:28:04<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.common.util;

import java.math.BigDecimal;

/**
 * 用于格式化字符串<br/>
 * 创建日期:<br/> 2017年2月17日16:30:35<br/>
 * @author 王通<br/>
 */
public class StringUtil {
	
	/**
	 * 根据数字创造字符串
	 * @param number 给定的数字
	 * @param length 创造字符串的长度
	 * @return
	 * @Description 
	 * @version 2017年2月17日 下午5:36:42<br/>
	 * @author 王通<br/>
	 */
	public static String getString (BigDecimal number, Integer length) {
		String value = number.toString();
		Integer valueLength = value.length();
		if (valueLength >= length) {
			value = value.substring(valueLength - length);
		} else {
			for (int i = 0; i < length - valueLength; i++) {
				value = "0" + value;
			}
		}
		return value;
	}
}
