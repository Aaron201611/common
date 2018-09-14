package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.yunkouan.base.BaseEntity;
import com.yunkouan.valid.ValidSave;
import com.yunkouan.valid.ValidSearch;
import com.yunkouan.valid.ValidUpdate;

/**
* @Description: 【企业】实体类
* @author tphe06
* @date 2017年3月15日
*/
public class SysOrg extends BaseEntity {
	private static final long serialVersionUID = -3054690606880389254L;

	/**企业id*/
	@Id
	private String orgId;

	/**企业代码*/
	@Length(max=32,message="{valid_org_orgNo_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
	@NotNull(message="{valid_org_orgNo_notnull}",groups={ValidUpdate.class})
    private String orgNo;

	/**企业名称*/
	@Length(max=128,message="{valid_org_orgName_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
	@NotNull(message="{valid_org_orgName_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private String orgName;

	/**企业简称*/
	@Length(max=64,message="{valid_org_orgShortName_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
//	@NotNull(message="{valid_org_orgShortName_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private String orgShortName;

	/**企业状态*/
//	@NotNull(message="{valid_org_orgStatus_notnull}",groups={ValidUpdate.class})
    private Integer orgStatus;

    /**@Fields 企业类型：0园区，1企业 */
    @NotNull(message="{valid_org_orgType_notnull}",groups={ValidSave.class,ValidUpdate.class})
    private String orgType;
	@Transient
	public static final String TYPE_PARK = "0";
	@Transient
	public static final String TYPE_ORG = "1";

    /**企业税号*/
	@Length(max=32,message="{valid_org_taxNo_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String taxNo;

	/**开户银行*/
	@Length(max=64,message="{valid_org_bank_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String bank;

	/**开户银行帐号*/
	@Length(max=24,message="{valid_org_bankAccount_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String bankAccount;

	/**所属城市*/
	@Length(max=64,message="{valid_org_city_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String city;

	/**联系人*/
	@Length(max=64,message="{valid_org_contactPerson_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String contactPerson;

	/**联系电话*/
	@Length(max=16,message="{valid_org_contactPhone_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String contactPhone;

	/**联系地址*/
	@Length(max=512,message="{valid_org_contactAddress_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String contactAddress;

	/**邮箱地址*/
	@Length(max=64,message="{valid_org_email_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String email;

	/**传真*/
	@Length(max=16,message="{valid_org_fax_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String fax;

	/**备注*/
	@Length(max=2048,message="{valid_org_note_length}",groups={ValidSave.class,ValidUpdate.class,ValidSearch.class})
    private String note;

	private Integer orgId2;

    @Override
	public void clear() {
    	/**不能由前端修改，添加修改时候都赋空值**/
    	this.orgId2 = null;
    	/**添加时候由程序赋值，修改时候置空不更新数据库的值，生效/激活/取消时候才更新数据库的值**/
    	this.orgStatus = null;
		super.clear();
	}

    public String getOrgId() {
        return orgId;
    }

    public SysOrg setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
        return this;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public SysOrg setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public SysOrg setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
        return this;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public SysOrg setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName == null ? null : orgShortName.trim();
        return this;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public SysOrg setTaxNo(String taxNo) {
        this.taxNo = taxNo == null ? null : taxNo.trim();
        return this;
    }

    public String getBank() {
        return bank;
    }

    public SysOrg setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
        return this;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public SysOrg setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
        return this;
    }

    public String getCity() {
        return city;
    }

    public SysOrg setCity(String city) {
        this.city = city == null ? null : city.trim();
        return this;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public SysOrg setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
        return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public SysOrg setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
        return this;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public SysOrg setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress == null ? null : contactAddress.trim();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SysOrg setEmail(String email) {
        this.email = email == null ? null : email.trim();
        return this;
    }

    public String getFax() {
        return fax;
    }

    public SysOrg setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
        return this;
    }

    public Integer getOrgId2() {
        return orgId2;
    }

    public SysOrg setOrgId2(Integer orgId2) {
        this.orgId2 = orgId2;
        return this;
    }

	public Integer getOrgStatus() {
		return orgStatus;
	}

	public SysOrg setOrgStatus(Integer orgStatus) {
		this.orgStatus = orgStatus;
		return this;
	}

	public String getOrgType() {
		return orgType;
	}

	public SysOrg setOrgType(String orgType) {
		this.orgType = orgType;
		return this;
	}

	public String getNote() {
		return note;
	}

	public SysOrg setNote(String note) {
		this.note = note;
		return this;
	}
}