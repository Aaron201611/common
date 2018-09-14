package com.yunkouan.saas.common.constant;

public class Constant {
	/**平台管理员角色编号*/
	public static final String ROLE_PLATFORM = "superadmin";
	/**企业管理员角色编号*/
	public static final String ROLE_ORG = "admin";

	/**打开状态*/
	public static final int STATUS_OPEN = 10;
	/**生效状态*/
	public static final int STATUS_ACTIVE = 20;
	/**取消状态*/
	public static final int STATUS_CANCEL = 99;

	public static final String getStatus(Integer s) {
		if(s == null) return "";
		if(STATUS_OPEN == s) return "打开";
		if(STATUS_ACTIVE == s) return "生效";
		if(STATUS_CANCEL == s) return "取消";
		return "";
	}
}