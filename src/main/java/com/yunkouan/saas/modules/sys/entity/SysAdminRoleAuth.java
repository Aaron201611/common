package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;
import com.yunkouan.base.BaseEntity;

/**
* @Description: 【平台管理员、企业管理员角色授权】
* @author tphe06
* @date 2017年3月15日
*/
public class SysAdminRoleAuth extends BaseEntity {
	private static final long serialVersionUID = 470959978058450636L;

	/**企业角色授权id**/
	@Id
	private String roleAuthId;
	/**角色id**/
    private String roleId;
    /**权限id**/
    private String authId;

    private Integer roleAuthId2;

    public String getRoleAuthId() {
        return roleAuthId;
    }

    public SysAdminRoleAuth setRoleAuthId(String roleAuthId) {
        this.roleAuthId = roleAuthId == null ? null : roleAuthId.trim();
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public SysAdminRoleAuth setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
        return this;
    }

    public String getAuthId() {
        return authId;
    }

    public SysAdminRoleAuth setAuthId(String authId) {
        this.authId = authId == null ? null : authId.trim();
        return this;
    }

    public Integer getRoleAuthId2() {
        return roleAuthId2;
    }

    public SysAdminRoleAuth setRoleAuthId2(Integer roleAuthId2) {
        this.roleAuthId2 = roleAuthId2;
        return this;
    }
}