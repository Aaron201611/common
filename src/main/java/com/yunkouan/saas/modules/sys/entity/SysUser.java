package com.yunkouan.saas.modules.sys.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.yunkouan.base.BaseEntity;
import com.yunkouan.valid.ValidSave;
import com.yunkouan.valid.ValidSearch;
import com.yunkouan.valid.ValidUpdate;

/**
* @Description: 【企业用户】实体类
* @author tphe06
* @date 2017年3月15日
*/
public class SysUser extends BaseEntity {
	private static final long serialVersionUID = -5258890381962552117L;

	/**用户id*/
	@Id
	private String userId;

	/**用户编号*/
	@Length(max=32,message="{valid_user_userNo_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
	@NotNull(message="{valid_user_userNo_notnull}",groups={ValidUpdate.class})
    private String userNo;

	/**用户姓名*/
	@Length(max=16,message="{valid_user_userName_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
	@NotNull(message="{valid_user_userName_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private String userName;

	/**组织id*/
    private String orgId;

	/**工作区域id*/
    private String workAreaId;

	/**邮箱地址*/
	@Length(max=32,message="{valid_user_email_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String email;

	/**用户状态*/
	@NotNull(message="{valid_user_userStatus_notnull}",groups={ValidUpdate.class})
    private Integer userStatus;

	/**工作组id*/
    private String groupId;

	/**是否员工*/
	@NotNull(message="{valid_user_isEmployee_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private Integer isEmployee;

	/**联系电话*/
	@Length(max=24,message="{valid_user_phone_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String phone;

	@Length(max=256,message="{valid_user_note_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String note;

	/**@Fields 绑定的RFID标签ID */
	private String rfidId;
	/**jobQuantity:单量**/
	private Integer jobQuantity;
	/**lastJobTime:最后作业时间**/
	private Date lastJobTime;

	private Integer userId2;

    @Override
	public void clear() {
    	/**不能由前端修改，添加修改时候都赋空值**/
    	this.userId2 = null;
    	/**添加时候由程序赋值，修改时候置空不更新数据库的值，生效/激活/取消时候才更新数据库的值**/
//    	this.userStatus = null;
    	/**不能由前端修改，添加时候取当前登录人的，修改时候置空不更新数据库的值**/
//    	this.orgId = null;
		super.clear();
	}

    public String getUserId() {
        return userId;
    }

    public SysUser setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
        return this;
    }

    public String getUserNo() {
        return userNo;
    }

    public SysUser setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public SysUser setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
        return this;
    }

    public String getWorkAreaId() {
        return workAreaId;
    }

    public SysUser setWorkAreaId(String workAreaId) {
        this.workAreaId = workAreaId == null ? null : workAreaId.trim();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SysUser setEmail(String email) {
        this.email = email == null ? null : email.trim();
        return this;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public SysUser setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public SysUser setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public Integer getUserId2() {
        return userId2;
    }

    public SysUser setUserId2(Integer userId2) {
        this.userId2 = userId2;
        return this;
    }

    public Integer getIsEmployee() {
        return isEmployee;
    }

    public SysUser setIsEmployee(Integer isEmployee) {
        this.isEmployee = isEmployee;
        return this;
    }

	public String getPhone() {
		return phone;
	}

	public SysUser setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getNote() {
		return note;
	}

	public SysUser setNote(String note) {
		this.note = note;
		return this;
	}

	public String getOrgId() {
		return orgId;
	}

	public SysUser setOrgId(String orgId) {
		this.orgId = orgId;
		return this;
	}

	public String getRfidId() {
		return rfidId;
	}

	public SysUser setRfidId(String rfidId) {
		this.rfidId = rfidId;
		return this;
	}

	public Integer getJobQuantity() {
		return jobQuantity;
	}

	public Date getLastJobTime() {
		return lastJobTime;
	}

	public SysUser setJobQuantity(Integer jobQuantity) {
		this.jobQuantity = jobQuantity;
		return this;
	}

	public SysUser setLastJobTime(Date lastJobTime) {
		this.lastJobTime = lastJobTime;
		return this;
	}
}