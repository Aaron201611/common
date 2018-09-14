package com.yunkouan.exception;

/**
 * @author tphe06 2017年2月15日
 */
public class DaoException extends Exception {
	private static final long serialVersionUID = 3269678070847983558L;

	public DaoException(String e) {
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