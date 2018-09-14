package com.yunkouan.saas.common.vo;

import java.io.Serializable;

/**
 * 当前登录用户信息
 */
public class Principal implements Serializable {
	private static final long serialVersionUID = 6562144790746698769L;

	/**用户主键**/
	private String userId;
	/**用户编号**/
    private String userNo;
    /**用户姓名**/
    private String userName;
    /**帐号id*/
    private String accountId;
    /**登录帐号**/
	private String loginName;
	/**组织id**/
	private String orgId;
	/**是否手机登录**/
	private boolean mobileLogin;
	/**权限级别【1---平台管理员，2---企业管理员，3---企业普通用户】**/
	private int authLevel;

	/**邮箱地址**/
    private String email;
    /**用户状态**/
    private Integer userStatus;
    /**是否内部员工**/
    private Integer isEmployee;
    /**联系电话**/
    private String phone;

    public Principal(){}
    public Principal(String userId, String loginName, String userNo, String userName, 
		String email, Integer userStatus, 
		Integer isEmployee, String phone, boolean mobileLogin, int authLevel, String accountId, String orgId) {
		this.userId = userId;
		this.loginName = loginName;
		this.userNo = userNo;
		this.userName = userName;
		this.mobileLogin = mobileLogin;
		this.email = email;
		this.userStatus = userStatus;
		this.isEmployee = isEmployee;
		this.phone = phone;
		this.authLevel = authLevel;
		this.accountId= accountId;
		this.orgId = orgId;
	}

	@Override
	public String toString() {
		return loginName;
	}

	/**
	 * 用户id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 用户编号
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * 用户姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 当前登录帐号
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 是否手机登陆
	 */
	public boolean isMobileLogin() {
		return mobileLogin;
	}

	/**组织id*/
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 邮箱地址
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 用户状态
	 */
	public Integer getUserStatus() {
		return userStatus;
	}

	/**
	 * 是否员工
	 */
	public Integer getIsEmployee() {
		return isEmployee;
	}

	/**
	 * 联系电话
	 */
	public String getPhone() {
		return phone;
	}

	/**帐号id*/
	public String getAccountId() {
		return accountId;
	}

	/**权限级别【1---平台管理员，2---企业管理员，3---企业普通用户】**/
	public int getAuthLevel() {
		return authLevel;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public void setMobileLogin(boolean mobileLogin) {
		this.mobileLogin = mobileLogin;
	}
	public void setAuthLevel(int authLevel) {
		this.authLevel = authLevel;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public void setIsEmployee(Integer isEmployee) {
		this.isEmployee = isEmployee;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
