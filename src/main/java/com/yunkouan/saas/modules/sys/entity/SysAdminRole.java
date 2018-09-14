package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.yunkouan.base.BaseEntity;
import com.yunkouan.valid.ValidSave;
import com.yunkouan.valid.ValidSearch;
import com.yunkouan.valid.ValidUpdate;

/**
* @Description: 【平台管理员、企业管理员角色】
* @author tphe06
* @date 2017年3月15日
*/
public class SysAdminRole extends BaseEntity {
	private static final long serialVersionUID = -7056059501913029188L;

	/**角色id*/
	@Id
	private String roleId;

	/**角色编号*/
	@Length(max=32,message="{valid_role_roleNo_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
	@NotNull(message="{valid_role_roleNo_notnull}",groups={ValidUpdate.class})
	private String roleNo;

	/**角色名称*/
	@Length(max=32,message="{valid_role_roleName_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
	@NotNull(message="{valid_role_roleName_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private String roleName;

	/**角色状态*/
	@NotNull(message="{valid_role_roleStatus_notnull}",groups={ValidUpdate.class})
    private Integer roleStatus;

	/**角色描述*/
	@Length(max=1024,message="{valid_role_roleDesc_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String roleDesc;

	private Integer roleId2;

    @Override
	public void clear() {
    	/**不能由前端修改，添加修改时候都赋空值**/
    	this.roleId2 = null;
    	/**添加时候由程序赋值，修改时候置空不更新数据库的值，生效/激活/取消时候才更新数据库的值**/
    	this.roleStatus = null;
		super.clear();
	}

    public String getRoleId() {
        return roleId;
    }

    public SysAdminRole setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public SysAdminRole setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
        return this;
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public SysAdminRole setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
        return this;
    }

    public Integer getRoleId2() {
        return roleId2;
    }

    public SysAdminRole setRoleId2(Integer roleId2) {
        this.roleId2 = roleId2;
        return this;
    }

	public String getRoleNo() {
		return roleNo;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public SysAdminRole setRoleNo(String roleNo) {
		this.roleNo = roleNo;
		return this;
	}

	public SysAdminRole setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
		return this;
	}
}