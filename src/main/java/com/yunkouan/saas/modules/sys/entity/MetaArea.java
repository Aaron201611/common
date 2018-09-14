/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月11日 下午4:15:15<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.yunkouan.base.BaseEntity;
import com.yunkouan.valid.ValidSave;
import com.yunkouan.valid.ValidUpdate;


/**
 * 库区实体类<br/><br/>
 * @version 2017年3月11日 下午4:15:15<br/>
 * @author andy wang<br/>
 */
public class MetaArea extends BaseEntity {
	
	private static final long serialVersionUID = -754207221073480667L;

	/**
	 * 库区主键
	 * @version 2017年4月19日下午4:50:04<br/>
	 * @author andy wang<br/>
	 */
	@Id
	private String areaId;

	/**
	 * 库区代码
	 * @version 2017年4月19日下午4:50:13<br/>
	 * @author andy wang<br/>
	 */
	@NotNull(message = "{valid_main_area_areaNo_notnull}",groups={ValidSave.class,ValidUpdate.class})
    @Length(max=32, message="{valid_main_area_areaNo_length}",groups={ValidSave.class,ValidUpdate.class})
    private String areaNo;

    /**
	 * 库区名称
	 * @version 2017年4月19日下午4:50:22<br/>
	 * @author andy wang<br/>
	 */
	@NotNull(message = "{valid_main_area_areaName_notnull}",groups={ValidSave.class,ValidUpdate.class})
    @Length(max=16, message="{valid_main_area_areaName_length}",groups={ValidSave.class,ValidUpdate.class})
    private String areaName;

	/**skuTypeIid:货品类型id**/
//	@NotNull(message = "{valid_main_area_skuType_notnull}",groups={ValidSave.class,ValidUpdate.class})
	private String skuTypeId;

	/**
	 * 库区类型
	 * @version 2017年4月19日下午4:50:30<br/>
	 * @author andy wang<br/>
	 */
    private Integer areaType;

    
    /**
	 * 仓库id
	 * @version 2017年4月19日下午4:50:39<br/>
	 * @author andy wang<br/>
	 */
    private String warehouseId;

    /**
	 * 企业id
	 * @version 2017年4月19日下午4:50:47<br/>
	 * @author andy wang<br/>
	 */
    private String orgId;

    
    /**
	 * 最高温度
	 * @version 2017年4月19日下午4:50:56<br/>
	 * @author andy wang<br/>
	 */
    @DecimalMax(value="999999999999999",message="{valid_main_area_maxTemperature_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private Double maxTemperature;

    
    /**
	 * 最低温度
	 * @version 2017年4月19日下午4:51:04<br/>
	 * @author andy wang<br/>
	 */
    @DecimalMax(value="999999999999999",message="{valid_main_area_minTemperature_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private Double minTemperature;

    
    /**
	 * 备注
	 * @version 2017年4月19日下午4:51:16<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=512, message="{valid_main_area_areaName_length}",groups={ValidSave.class,ValidUpdate.class})
    private String note;

    /**
	 * 库区排序id
	 * @version 2017年4月19日下午4:51:47<br/>
	 * @author andy wang<br/>
	 */
    private Integer areaId2;

    /**
	 * 库区状态
	 * @version 2017年4月19日下午4:51:56<br/>
	 * @author andy wang<br/>
	 */
    private Integer areaStatus;

	/**
	 * 属性 areaId getter方法
	 * @return 属性areaId
	 * @author andy wang<br/>
	 */
	public String getAreaId() {
		return areaId;
	}

	/**
	 * 属性 areaId setter方法
	 * @param areaId 设置属性areaId的值
	 * @author andy wang<br/>
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	/**
	 * 属性 areaNo getter方法
	 * @return 属性areaNo
	 * @author andy wang<br/>
	 */
	public String getAreaNo() {
		return areaNo;
	}

	/**
	 * 属性 areaNo setter方法
	 * @param areaNo 设置属性areaNo的值
	 * @author andy wang<br/>
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	/**
	 * 属性 areaName getter方法
	 * @return 属性areaName
	 * @author andy wang<br/>
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * 属性 areaName setter方法
	 * @param areaName 设置属性areaName的值
	 * @author andy wang<br/>
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 属性 areaType getter方法
	 * @return 属性areaType
	 * @author andy wang<br/>
	 */
	public Integer getAreaType() {
		return areaType;
	}

	/**
	 * 属性 areaType setter方法
	 * @param areaType 设置属性areaType的值
	 * @author andy wang<br/>
	 */
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}

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
	 * 属性 maxTemperature getter方法
	 * @return 属性maxTemperature
	 * @author andy wang<br/>
	 */
	public Double getMaxTemperature() {
		return maxTemperature;
	}

	/**
	 * 属性 maxTemperature setter方法
	 * @param maxTemperature 设置属性maxTemperature的值
	 * @author andy wang<br/>
	 */
	public void setMaxTemperature(Double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	/**
	 * 属性 minTemperature getter方法
	 * @return 属性minTemperature
	 * @author andy wang<br/>
	 */
	public Double getMinTemperature() {
		return minTemperature;
	}

	/**
	 * 属性 minTemperature setter方法
	 * @param minTemperature 设置属性minTemperature的值
	 * @author andy wang<br/>
	 */
	public void setMinTemperature(Double minTemperature) {
		this.minTemperature = minTemperature;
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
	 * 属性 areaId2 getter方法
	 * @return 属性areaId2
	 * @author andy wang<br/>
	 */
	public Integer getAreaId2() {
		return areaId2;
	}

	/**
	 * 属性 areaId2 setter方法
	 * @param areaId2 设置属性areaId2的值
	 * @author andy wang<br/>
	 */
	public void setAreaId2(Integer areaId2) {
		this.areaId2 = areaId2;
	}

	/**
	 * 属性 areaStatus getter方法
	 * @return 属性areaStatus
	 * @author andy wang<br/>
	 */
	public Integer getAreaStatus() {
		return areaStatus;
	}

	/**
	 * 属性 areaStatus setter方法
	 * @param areaStatus 设置属性areaStatus的值
	 * @author andy wang<br/>
	 */
	public void setAreaStatus(Integer areaStatus) {
		this.areaStatus = areaStatus;
	}

	public String getSkuTypeId() {
		return skuTypeId;
	}

	public void setSkuTypeId(String skuTypeId) {
		this.skuTypeId = skuTypeId;
	}
}