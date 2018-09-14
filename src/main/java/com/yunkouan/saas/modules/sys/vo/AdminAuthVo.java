package com.yunkouan.saas.modules.sys.vo;

import java.io.Serializable;

import com.yunkouan.saas.modules.sys.entity.SysAdmin;
import com.yunkouan.saas.modules.sys.entity.SysAuth;

/**
* @Description: 【管理员/权限】数据传输类
* @author tphe06
* @date 2017年3月10日
*/
public class AdminAuthVo implements Serializable {
	private static final long serialVersionUID = -528385857991646376L;

	private SysAdmin admin;
	private SysAuth auth;

	public SysAdmin getAdmin() {
		return admin;
	}
	public SysAuth getAuth() {
		return auth;
	}
	public AdminAuthVo setAdmin(SysAdmin admin) {
		this.admin = admin;
		return this;
	}
	public AdminAuthVo setAuth(SysAuth auth) {
		this.auth = auth;
		return this;
	}
}