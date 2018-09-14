package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;
import com.yunkouan.base.BaseEntity;

/**
* @Description: 【企业授权实体类】
* @author tphe06
* @date 2017年3月15日
*/
public class SysOrgAuth extends BaseEntity {
	private static final long serialVersionUID = -1221825864812201565L;

	/**企业授权id**/
	@Id
	private String orgAuthId;
	/**权限id**/
    private String authId;
    /**组织id**/
    private String orgId;

    private Integer authId2;

    public String getOrgAuthId() {
        return orgAuthId;
    }

    public SysOrgAuth setOrgAuthId(String orgAuthId) {
        this.orgAuthId = orgAuthId == null ? null : orgAuthId.trim();
        return this;
    }

    public String getAuthId() {
        return authId;
    }

    public SysOrgAuth setAuthId(String authId) {
        this.authId = authId == null ? null : authId.trim();
        return this;
    }

    public String getOrgId() {
        return orgId;
    }

    public SysOrgAuth setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
        return this;
    }

    public Integer getAuthId2() {
        return authId2;
    }

    public SysOrgAuth setAuthId2(Integer authId2) {
        this.authId2 = authId2;
        return this;
    }
}