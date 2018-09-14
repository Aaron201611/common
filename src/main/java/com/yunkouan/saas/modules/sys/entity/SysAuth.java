package com.yunkouan.saas.modules.sys.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Transient;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.yunkouan.base.BaseEntity;
import com.yunkouan.valid.ValidSave;
import com.yunkouan.valid.ValidSearch;
import com.yunkouan.valid.ValidUpdate;

/**
* @Description: 【权限（菜单，按钮）】实体类
* @author tphe06
* @date 2017年3月15日
*/
public class SysAuth extends BaseEntity {
	private static final long serialVersionUID = 2672395821668823151L;

	/**权限id*/
	@Id
	private String authId;

	/**权限编号，唯一*/
	@Length(max=32,message="{valid_auth_authNo_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
	@NotNull(message="{valid_auth_authNo_notnull}",groups={ValidUpdate.class})
    private String authNo;

    /**权限名称*/
	@Length(max=64,message="{valid_auth_authName_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
	@NotNull(message="{valid_auth_authName_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private String authName;

    /**权限名称中文简称*/
	@Length(max=32,message="{valid_auth_authShortname_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
//	@NotNull(message="{valid_auth_authShortname_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private String authShortname;

    /**权限URL地址*/
	@Length(max=256,message="{valid_auth_authUrl_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String authUrl;

    /**权限状态*/
//	@NotNull(message="{valid_auth_authStatus_notnull}",groups={ValidUpdate.class})
    private Integer authStatus;

    /**上级权限id*/
	@Length(max=64,message="{valid_auth_parentId_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String parentId;

	/**权限类型---非菜单和按钮**/
	@Transient
	public static final int AUTH_TYPE_ZERO = 0;
	/**权限类型---一级菜单**/
	@Transient
	public static final int AUTH_TYPE_TOP = 1;
	/**权限类型---二级菜单**/
	@Transient
	public static final int AUTH_TYPE_SECOND = 2;
	/**权限类型---按钮**/
	@Transient
	public static final int AUTH_TYPE_BUTTON = 9;

	/**权限类型（0非菜单和按钮，1主菜单，2子菜单，9功能按钮）*/
	@NotNull(message="{valid_auth_authType_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private Integer authType;
	@Transient
	private String authTypeTxt;

	/**权限级别---平台管理员**/
	@Transient
	public static final int AUTH_LEVEL_PLATFORM_ADMIN = 1;
	/**权限级别---企业/园区管理员**/
	@Transient
	public static final int AUTH_LEVEL_ORG_ADMIN = 2;
	/**权限级别---企业普通用户**/
	@Transient
	public static final int AUTH_LEVEL_ORG_USER = 3;
	/**权限级别---园区普通用户**/
	@Transient
	public static final int AUTH_LEVEL_PARK_USER = 4;

	/**权限级别，分3级：1企业管理员，2平台管理员，3平台用户，下级权限不能操作上级权限*/
	@NotNull(message="{valid_auth_authLevel_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private Integer authLevel;
	@Transient
	private String authLevelTxt;

    private Integer authId2;
    private String note;

    public SysAuth clearNull() {
    	if(note == null) note = "";
    	if(authLevelTxt == null) note = "";
    	if(authId2 == null) authId2 = 0;
    	if(authLevel == null) authLevel = 0;
    	if(authTypeTxt == null) authTypeTxt = "";
    	if(authType == null) authType = 0;
    	if(parentId == null) parentId = "";
    	if(authStatus == null) authStatus = 0;
    	if(authUrl == null) authUrl = "";
    	if(authShortname == null) authShortname = "";
    	if(authName == null) authName = "";
    	if(authNo == null) authNo = "";
    	if(authId == null) authId = "";
    	if(this.getCreateTime() == null) this.setCreateTime(new Date());
    	if(this.getCreatePerson() == null) this.setCreatePerson("");
    	if(this.getUpdateTime() == null) this.setUpdateTime(new Date());
    	if(this.getUpdatePerson() == null) this.setUpdatePerson("");
    	return this;
    }

    @Override
	public void clear() {
    	/**不能由前端修改，添加修改时候都赋空值**/
    	this.authId2 = null;
    	/**添加时候由程序赋值，修改时候置空不更新数据库的值，生效/激活/取消时候才更新数据库的值**/
    	this.authStatus = null;
		super.clear();
	}

	public static final String getLevel(Integer s) {
		if(s == null) return "";
		if(AUTH_LEVEL_PARK_USER == s) return "园区用户";
		if(AUTH_LEVEL_PLATFORM_ADMIN == s) return "平台管理员";
		if(AUTH_LEVEL_ORG_ADMIN == s) return "企业/园区管理员";
		if(AUTH_LEVEL_ORG_USER == s) return "普通业务人员";
		return "";
	}
	public static final String getType(Integer s) {
		if(s == null) return "";
		if(AUTH_TYPE_TOP == s) return "一级菜单";
		if(AUTH_TYPE_SECOND == s) return "二级菜单";
		if(AUTH_TYPE_BUTTON == s) return "按钮";
		return "";
	}

    public String getAuthId() {
        return authId;
    }

    public SysAuth setAuthId(String authId) {
        this.authId = authId == null ? null : authId.trim();
        return this;
    }

    public String getAuthNo() {
        return authNo;
    }

    public SysAuth setAuthNo(String authNo) {
        this.authNo = authNo == null ? null : authNo.trim();
        return this;
    }

    public String getAuthName() {
        return authName;
    }

    public SysAuth setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
        return this;
    }

    public String getAuthShortname() {
        return authShortname;
    }

    public SysAuth setAuthShortname(String authShortname) {
        this.authShortname = authShortname == null ? null : authShortname.trim();
        return this;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public SysAuth setAuthUrl(String authUrl) {
        this.authUrl = authUrl == null ? null : authUrl.trim();
        return this;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public SysAuth setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public SysAuth setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
        return this;
    }

    public Integer getAuthType() {
        return authType;
    }

    public SysAuth setAuthType(Integer authType) {
        this.authType = authType;
        return this;
    }

    public Integer getAuthLevel() {
        return authLevel;
    }

    public SysAuth setAuthLevel(Integer authLevel) {
        this.authLevel = authLevel;
        return this;
    }

    public Integer getAuthId2() {
        return authId2;
    }

    public SysAuth setAuthId2(Integer authId2) {
        this.authId2 = authId2;
        return this;
    }

	public String getNote() {
		return note;
	}

	public SysAuth setNote(String note) {
		this.note = note;
		return this;
	}

	public String getAuthTypeTxt() {
		return authTypeTxt;
	}

	public String getAuthLevelTxt() {
		return authLevelTxt;
	}

	public SysAuth setAuthTypeTxt(String authTypeTxt) {
		this.authTypeTxt = authTypeTxt;
		return this;
	}

	public SysAuth setAuthLevelTxt(String authLevelTxt) {
		this.authLevelTxt = authLevelTxt;
		return this;
	}
}