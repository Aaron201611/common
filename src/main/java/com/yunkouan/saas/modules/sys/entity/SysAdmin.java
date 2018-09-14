package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.yunkouan.base.BaseEntity;
import com.yunkouan.valid.ValidSave;
import com.yunkouan.valid.ValidSearch;
import com.yunkouan.valid.ValidUpdate;

/**
* @Description: 【平台管理员/企业管理员】实体类
* @author tphe06
* @date 2017年3月15日
*/
public class SysAdmin extends BaseEntity {
	private static final long serialVersionUID = 1221997073058770625L;

	/**帐号id*/
	@Id
	private String adminId;

	/**登录帐号*/
	@Length(max=32,message="{valid_account_accountNo_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
	@NotNull(message="{valid_account_accountNo_notnull}",groups={ValidUpdate.class})
    private String adminNo;

	/**帐号名称*/
	@Length(max=32,message="{valid_account_accountName_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
//	@NotNull(message="{valid_account_accountName_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private String adminName;

	/**组织id*/
	@Length(max=64,message="{valid_account_orgId_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String orgId;

	/**登录密码*/
	@Length(max=64,message="{valid_account_accountPwd_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
	@NotNull(message="{valid_account_accountPwd_notnull}",groups={ValidSave.class})
    private String loginPwd;

	/**帐号状态*/
//	@NotNull(message="{valid_account_accountStatus_notnull}",groups={ValidUpdate.class})
    private Integer adminStatus;

    private Integer adminId2;

	/**用户id*/
	@Length(max=64,message="{valid_account_userId_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
//	@NotNull(message="{valid_account_userId_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private String userId;

    @Length(max=2048,message="{valid_account_note_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String note;

	@Override
	public void clear() {
    	/**不能由前端修改，添加修改时候都赋空值**/
    	this.adminId2 = null;
    	/**添加时候由程序赋值，修改时候置空不更新数据库的值，生效/激活/取消时候才更新数据库的值**/
    	this.adminStatus = null;
    	/**不能由前端修改，添加时候取当前登录人的，修改时候置空不更新数据库的值**/
//    	this.orgId = null;
		super.clear();
	}

    public String getAdminId() {
        return adminId;
    }

    public SysAdmin setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
        return this;
    }

    public String getAdminNo() {
        return adminNo;
    }

    public SysAdmin setAdminNo(String adminNo) {
        this.adminNo = adminNo == null ? null : adminNo.trim();
        return this;
    }

    public String getAdminName() {
    	return adminName;
    }

    public SysAdmin setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
        return this;
    }

    public String getOrgId() {
        return orgId;
    }

    public SysAdmin setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
        return this;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public SysAdmin setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
        return this;
    }

    public Integer getAdminStatus() {
        return adminStatus;
    }

    public SysAdmin setAdminStatus(Integer adminStatus) {
        this.adminStatus = adminStatus;
        return this;
    }

    public Integer getAdminId2() {
        return adminId2;
    }

    public SysAdmin setAdminId2(Integer adminId2) {
        this.adminId2 = adminId2;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public SysAdmin setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
        return this;
    }

	public String getNote() {
		return note;
	}

	public SysAdmin setNote(String note) {
		this.note = note;
		return this;
	}
}