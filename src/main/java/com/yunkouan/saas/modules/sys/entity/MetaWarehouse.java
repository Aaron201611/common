/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月11日 上午9:53:59<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.yunkouan.base.BaseEntity;
import com.yunkouan.valid.ValidSave;
import com.yunkouan.valid.ValidUpdate;


/**
 * 仓库实体类<br/><br/>
 * @version 2017年3月11日 上午9:53:59<br/>
 * @author andy wang<br/>
 */
public class MetaWarehouse extends BaseEntity {
	private static final long serialVersionUID = -186670555388156173L;
	
	/**
	 * 仓库id
	 * @version 2017年3月11日上午9:51:39<br/>
	 * @author andy wang<br/>
	 */
	@Id
	private String warehouseId;

	/**
	 * 仓库编号
	 * @version 2017年3月11日上午9:51:39<br/>
	 * @author andy wang<br/>
	 */
	@NotNull(message = "{valid_main_wrh_warehouseNo_notnull}",groups={ValidSave.class,ValidUpdate.class})
    @Length(max=16, message="{valid_main_wrh_warehouseNo_length}",groups={ValidSave.class,ValidUpdate.class})
	private String warehouseNo;

    /**
	 * 仓库名
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
	@NotNull(message="{valid_main_wrh_warehouseName_notnull}",groups={ValidSave.class,ValidUpdate.class})
	@Length(max=16, message="{valid_main_wrh_warehouseName_length}",groups={ValidSave.class,ValidUpdate.class})
	private String warehouseName;

    /**
	 * 仓库类型
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
	private Integer warehouseType;

    /**
	 * 仓库状态
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    private Integer warehouseStatus;

    /**
	 * 企业id
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    private String orgId;

    /**
	 * 联系地址
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=512, message="{valid_main_wrh_contactAddress_length}",groups={ValidSave.class,ValidUpdate.class})
	private String contactAddress;

    /**
	 * 联系人
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=16, message="{valid_main_wrh_contactPerson_length}",groups={ValidSave.class,ValidUpdate.class})
	private String contactPerson;

    /**
	 * 联系电话
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=16, message="{valid_main_wrh_contactPhone_length}",groups={ValidSave.class,ValidUpdate.class})
	private String contactPhone;

    /**
	 * 传真
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=16, message="{valid_main_wrh_fax_length}",groups={ValidSave.class,ValidUpdate.class})
	private String fax;

    /**
	 * 邮箱地址
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=32, message="{valid_main_wrh_email_length}",groups={ValidSave.class,ValidUpdate.class})
	@Email(message="{valid_main_wrh_email_regex}",groups={ValidSave.class,ValidUpdate.class})
    private String email;

    /**
	 * 经度
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    @DecimalMax(value="999999999999999",message="{valid_main_wrh_longitude_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private Double longitude;

    /**
	 * 纬度
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    @DecimalMax(value="999999999999999",message="{valid_main_wrh_latitude_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private Double latitude;

    /**
	 * 备注
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=512, message="{valid_main_wrh_note_length}",groups={ValidSave.class,ValidUpdate.class})
	private String note;

    /**
	 * 备用id
	 * @version 2017年3月11日上午9:52:00<br/>
	 * @author andy wang<br/>
	 */
    private Integer warehouseId2;
    
    /**
	 * 使用人
	 * @version 2017年4月3日上午10:54:19<br/>
	 * @author andy wang<br/>
	 */
    private String usePerson;

	/**
	 * 属性 warehouseId getter方法
	 * @return 属性warehouseId
	 * @author andy wang<br/>
	 */
	public String getWarehouseId() {
		return warehouseId;
	}

	/**
	 * 属性 warehouseId setter方法
	 * @param warehouseId 设置属性warehouseId的值
	 * @author andy wang<br/>
	 */
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**
	 * 属性 usePerson getter方法
	 * @return 属性usePerson
	 * @author andy wang<br/>
	 */
	public String getUsePerson() {
		return usePerson;
	}

	/**
	 * 属性 usePerson setter方法
	 * @param usePerson 设置属性usePerson的值
	 * @author andy wang<br/>
	 */
	public void setUsePerson(String usePerson) {
		this.usePerson = usePerson;
	}

	/**
	 * 属性 warehouseNo getter方法
	 * @return 属性warehouseNo
	 * @author andy wang<br/>
	 */
	public String getWarehouseNo() {
		return warehouseNo;
	}

	/**
	 * 属性 warehouseNo setter方法
	 * @param warehouseNo 设置属性warehouseNo的值
	 * @author andy wang<br/>
	 */
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	/**
	 * 属性 warehouseName getter方法
	 * @return 属性warehouseName
	 * @author andy wang<br/>
	 */
	public String getWarehouseName() {
		return warehouseName;
	}

	/**
	 * 属性 warehouseName setter方法
	 * @param warehouseName 设置属性warehouseName的值
	 * @author andy wang<br/>
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	/**
	 * 属性 warehouseType getter方法
	 * @return 属性warehouseType
	 * @author andy wang<br/>
	 */
	public Integer getWarehouseType() {
		return warehouseType;
	}

	/**
	 * 属性 warehouseType setter方法
	 * @param warehouseType 设置属性warehouseType的值
	 * @author andy wang<br/>
	 */
	public void setWarehouseType(Integer warehouseType) {
		this.warehouseType = warehouseType;
	}

	/**
	 * 属性 warehouseStatus getter方法
	 * @return 属性warehouseStatus
	 * @author andy wang<br/>
	 */
	public Integer getWarehouseStatus() {
		return warehouseStatus;
	}

	/**
	 * 属性 warehouseStatus setter方法
	 * @param warehouseStatus 设置属性warehouseStatus的值
	 * @author andy wang<br/>
	 */
	public void setWarehouseStatus(Integer warehouseStatus) {
		this.warehouseStatus = warehouseStatus;
	}

	/**
	 * 属性 orgId getter方法
	 * @return 属性orgId
	 * @author andy wang<br/>
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 属性 orgId setter方法
	 * @param orgId 设置属性orgId的值
	 * @author andy wang<br/>
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 属性 contactAddress getter方法
	 * @return 属性contactAddress
	 * @author andy wang<br/>
	 */
	public String getContactAddress() {
		return contactAddress;
	}

	/**
	 * 属性 contactAddress setter方法
	 * @param contactAddress 设置属性contactAddress的值
	 * @author andy wang<br/>
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * 属性 contactPerson getter方法
	 * @return 属性contactPerson
	 * @author andy wang<br/>
	 */
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * 属性 contactPerson setter方法
	 * @param contactPerson 设置属性contactPerson的值
	 * @author andy wang<br/>
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	/**
	 * 属性 contactPhone getter方法
	 * @return 属性contactPhone
	 * @author andy wang<br/>
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * 属性 contactPhone setter方法
	 * @param contactPhone 设置属性contactPhone的值
	 * @author andy wang<br/>
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * 属性 fax getter方法
	 * @return 属性fax
	 * @author andy wang<br/>
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * 属性 fax setter方法
	 * @param fax 设置属性fax的值
	 * @author andy wang<br/>
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * 属性 email getter方法
	 * @return 属性email
	 * @author andy wang<br/>
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 属性 email setter方法
	 * @param email 设置属性email的值
	 * @author andy wang<br/>
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 属性 longitude getter方法
	 * @return 属性longitude
	 * @author andy wang<br/>
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * 属性 longitude setter方法
	 * @param longitude 设置属性longitude的值
	 * @author andy wang<br/>
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 属性 latitude getter方法
	 * @return 属性latitude
	 * @author andy wang<br/>
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * 属性 latitude setter方法
	 * @param latitude 设置属性latitude的值
	 * @author andy wang<br/>
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 属性 note getter方法
	 * @return 属性note
	 * @author andy wang<br/>
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 属性 note setter方法
	 * @param note 设置属性note的值
	 * @author andy wang<br/>
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 属性 warehouseId2 getter方法
	 * @return 属性warehouseId2
	 * @author andy wang<br/>
	 */
	public Integer getWarehouseId2() {
		return warehouseId2;
	}

	/**
	 * 属性 warehouseId2 setter方法
	 * @param warehouseId2 设置属性warehouseId2的值
	 * @author andy wang<br/>
	 */
	public void setWarehouseId2(Integer warehouseId2) {
		this.warehouseId2 = warehouseId2;
	}

}