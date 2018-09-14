package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;

import com.yunkouan.base.BaseEntity;

/**
* @Description: 【平台管理员、企业管理员-角色】实体类
* @author tphe06
* @date 2017年3月15日
*/
public class SysAdministratorRole extends BaseEntity {
	private static final long serialVersionUID = 8260555663022955760L;

	/**主键**/
	@Id
	private String adminRoleId;
	/**帐号id**/
    private String adminId;
    /**角色id**/
    private String roleId;

    private Integer adminRoleId2;

    public String getAdminRoleId() {
        return adminRoleId;
    }

    public SysAdministratorRole setAdminRoleId(String adminRoleId) {
        this.adminRoleId = adminRoleId == null ? null : adminRoleId.trim();
        return this;
    }

    public String getAdminId() {
        return adminId;
    }

    public SysAdministratorRole setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public SysAdministratorRole setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
        return this;
    }

    public Integer getAdminRoleId2() {
        return adminRoleId2;
    }

    public SysAdministratorRole setAdminRoleId2(Integer adminRoleId2) {
        this.adminRoleId2 = adminRoleId2;
        return this;
    }
}