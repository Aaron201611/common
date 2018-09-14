package com.yunkouan.exception;

/**
 * @author tphe06 2017年2月15日
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = -3949665579270284104L;

	public ServiceException(String e) {
		super(e);
	}

	/**
	 * 提升性能：
	 * 1 覆盖方法
	 * 2 去掉同步
	 */
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
}