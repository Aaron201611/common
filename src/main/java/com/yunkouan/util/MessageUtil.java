/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年2月14日 下午8:47:55<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 信息工具类<br/><br/>
 * @version 2017年2月14日 下午8:47:55<br/>
 * @author andy wang<br/>
 */
public class MessageUtil {
	
	/**
	 * 获取国际化Properties
	 * @param request 请求对象
	 * @param key properties的key
	 * @param args 参数
	 * @return properties的值
	 * @version 2017年2月20日 上午10:39:26<br/>
	 * @author andy wang<br/>
	 */
	public static String getMessage ( HttpServletRequest request , String key , Object... args ) {
		WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
		String message = ac.getMessage(key,null, RequestContextUtils.getLocale(request));
		return message;
	}

}
