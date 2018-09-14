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

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * 用于创建业务UUID<br/>
 * 创建日期:<br/> 2017年2月8日 下午5:28:04<br/>
 * @author andy wang<br/>
 */
public class IdUtil {
	
	public static String getUUID () {
		return StringUtils.upperCase(UUID.randomUUID().toString().replaceAll("-", ""));
	}
}
