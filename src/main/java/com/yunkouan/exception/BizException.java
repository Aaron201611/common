/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2016年12月12日 下午2:09:19<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.exception;

import com.yunkouan.util.StringUtil;

/**
 * 业务异常类<br/><br/>
 * @version 2016年12月12日 下午2:09:19<br/>
 * @author andy wang<br/>
 */
public class BizException extends RuntimeException {
	private static final long serialVersionUID = -7028676216145109754L;
	private String message;
	private Object[] param;
	
	public BizException(){}
	
	public BizException( String msg ) {
		if ( StringUtil.isEmpty(msg) ) {
			msg = "";
		}
		this.message = msg;
	}
	
	public BizException(String msg, Object... args) {
		this(msg);
		param = args;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 设置错误提示
	 * @param message
	 * @version 2017年2月21日 下午2:47:51<br/>
	 * @author andy wang<br/>
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 属性 param getter方法
	 * @return 属性param
	 * @author andy wang<br/>
	 */
	public Object[] getParam() {
		return param;
	}

	/**
	 * 属性 param setter方法
	 * @param param 设置属性param的值
	 * @author andy wang<br/>
	 */
	public void setParam(Object[] param) {
		this.param = param;
	}

//	/**
//	 * 提升性能：
//	 * 1 覆盖方法
//	 * 2 去掉同步
//	 */
//	@Override
//	public Throwable fillInStackTrace() {
//		return this;
//	}
}