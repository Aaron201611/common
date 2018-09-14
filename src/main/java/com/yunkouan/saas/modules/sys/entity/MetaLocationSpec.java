/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年4月19日 下午4:56:36<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.entity;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.yunkouan.base.BaseEntity;
import com.yunkouan.valid.ValidSave;
import com.yunkouan.valid.ValidUpdate;

/**
 * 库位规格实体类<br/><br/>
 * @version 2017年4月19日 下午4:56:36<br/>
 * @author andy wang<br/>
 */
public class MetaLocationSpec extends BaseEntity {
	
	private static final long serialVersionUID = 4536958832211940766L;

	/**
	 * 库位规格主键
	 * @version 2017年4月19日下午4:57:01<br/>
	 * @author andy wang<br/>
	 */
	@Id
	private String specId;

	/**
	 * 库位规格名称
	 * @version 2017年4月19日下午4:57:40<br/>
	 * @author andy wang<br/>
	 */
	@NotNull(message = "{valid_main_spec_specName_notnull}",groups={ValidSave.class,ValidUpdate.class})
    @Length(max=16, message="{valid_main_spec_specName_length}",groups={ValidSave.class,ValidUpdate.class})
    private String specName;
    
    
    /**
	 * 库位规格的长度
	 * @version 2017年4月19日下午4:57:52<br/>
	 * @author andy wang<br/>
	 */
	@DecimalMax(value="999999999999999",message="{valid_main_spec_specLength_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private Double specLength;

    
    /**
	 * 库位规格的宽度
	 * @version 2017年4月19日下午4:58:01<br/>
	 * @author andy wang<br/>
	 */
	@DecimalMax(value="999999999999999",message="{valid_main_spec_specWidth_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private Double specWidth;

    /**
	 * 库位规格的高度
	 * @version 2017年4月19日下午4:58:10<br/>
	 * @author andy wang<br/>
	 */
	@DecimalMax(value="999999999999999",message="{valid_main_spec_specHeight_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private Double specHeight;

    /**
	 * 库位规格的最大载重
	 * @version 2017年4月19日下午4:58:19<br/>
	 * @author andy wang<br/>
	 */
	@DecimalMax(value="999999999999999",message="{valid_main_spec_specWeight_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private Double specWeight;

    /**
	 * 备注
	 * @version 2017年4月19日下午4:58:30<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=512, message="{valid_main_spec_note_length}",groups={ValidSave.class,ValidUpdate.class})
    private String note;

    
    /**
	 * 企业id
	 * @version 2017年4月19日下午4:58:38<br/>
	 * @author andy wang<br/>
	 */
    private String orgId;

    /**
	 * 仓库id
	 * @version 2017年4月19日下午4:58:46<br/>
	 * @author andy wang<br/>
	 */
    private String warehouseId;

    /**
	 * 库位规格排序id
	 * @version 2017年4月19日下午4:58:53<br/>
	 * @author andy wang<br/>
	 */
    private Integer specId2;
    
    
    /**
	 * 库位规格的最大库容量
	 * @version 2017年4月19日下午4:59:13<br/>
	 * @author andy wang<br/>
	 */
    @DecimalMax(value="99999999999",message="{valid_main_spec_maxCapacity_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private BigDecimal maxCapacity;
    
    /**
	 * 库位规格状态
	 * @version 2017年4月19日下午4:59:25<br/>
	 * @author andy wang<br/>
	 */
    private Integer specStatus;
    
    /**
	 * 库位规格代码
	 * @version 2017年4月19日下午4:59:33<br/>
	 * @author andy wang<br/>
	 */
//	@NotNull(message = "{valid_main_spec_specNo_notnull}",groups={ValidSave.class,ValidUpdate.class})
//    @Length(max=32, message="{valid_main_spec_specNo_length}",groups={ValidSave.class,ValidUpdate.class})
    private String specNo;

	/**
	 * 属性 specId getter方法
	 * @return 属性specId
	 * @author andy wang<br/>
	 */
	public String getSpecId() {
		return specId;
	}

	/**
	 * 属性 specId setter方法
	 * @param specId 设置属性specId的值
	 * @author andy wang<br/>
	 */
	public void setSpecId(String specId) {
		this.specId = specId;
	}

	/**
	 * 属性 specName getter方法
	 * @return 属性specName
	 * @author andy wang<br/>
	 */
	public String getSpecName() {
		return specName;
	}

	/**
	 * 属性 specName setter方法
	 * @param specName 设置属性specName的值
	 * @author andy wang<br/>
	 */
	public void setSpecName(String specName) {
		this.specName = specName;
	}

	/**
	 * 属性 specLength getter方法
	 * @return 属性specLength
	 * @author andy wang<br/>
	 */
	public Double getSpecLength() {
		return specLength;
	}

	/**
	 * 属性 specLength setter方法
	 * @param specLength 设置属性specLength的值
	 * @author andy wang<br/>
	 */
	public void setSpecLength(Double specLength) {
		this.specLength = specLength;
	}

	/**
	 * 属性 specWidth getter方法
	 * @return 属性specWidth
	 * @author andy wang<br/>
	 */
	public Double getSpecWidth() {
		return specWidth;
	}

	/**
	 * 属性 specWidth setter方法
	 * @param specWidth 设置属性specWidth的值
	 * @author andy wang<br/>
	 */
	public void setSpecWidth(Double specWidth) {
		this.specWidth = specWidth;
	}

	/**
	 * 属性 specHeight getter方法
	 * @return 属性specHeight
	 * @author andy wang<br/>
	 */
	public Double getSpecHeight() {
		return specHeight;
	}

	/**
	 * 属性 specHeight setter方法
	 * @param specHeight 设置属性specHeight的值
	 * @author andy wang<br/>
	 */
	public void setSpecHeight(Double specHeight) {
		this.specHeight = specHeight;
	}

	/**
	 * 属性 specWeight getter方法
	 * @return 属性specWeight
	 * @author andy wang<br/>
	 */
	public Double getSpecWeight() {
		return specWeight;
	}

	/**
	 * 属性 specWeight setter方法
	 * @param specWeight 设置属性specWeight的值
	 * @author andy wang<br/>
	 */
	public void setSpecWeight(Double specWeight) {
		this.specWeight = specWeight;
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
	 * 属性 specId2 getter方法
	 * @return 属性specId2
	 * @author andy wang<br/>
	 */
	public Integer getSpecId2() {
		return specId2;
	}

	/**
	 * 属性 specId2 setter方法
	 * @param specId2 设置属性specId2的值
	 * @author andy wang<br/>
	 */
	public void setSpecId2(Integer specId2) {
		this.specId2 = specId2;
	}

	/**
	 * 属性 maxCapacity getter方法
	 * @return 属性maxCapacity
	 * @author andy wang<br/>
	 */
	public BigDecimal getMaxCapacity() {
		return maxCapacity;
	}

	/**
	 * 属性 maxCapacity setter方法
	 * @param maxCapacity 设置属性maxCapacity的值
	 * @author andy wang<br/>
	 */
	public void setMaxCapacity(BigDecimal maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	/**
	 * 属性 specStatus getter方法
	 * @return 属性specStatus
	 * @author andy wang<br/>
	 */
	public Integer getSpecStatus() {
		return specStatus;
	}

	/**
	 * 属性 specStatus setter方法
	 * @param specStatus 设置属性specStatus的值
	 * @author andy wang<br/>
	 */
	public void setSpecStatus(Integer specStatus) {
		this.specStatus = specStatus;
	}

	/**
	 * 属性 specNo getter方法
	 * @return 属性specNo
	 * @author andy wang<br/>
	 */
	public String getSpecNo() {
		return specNo;
	}

	/**
	 * 属性 specNo setter方法
	 * @param specNo 设置属性specNo的值
	 * @author andy wang<br/>
	 */
	public void setSpecNo(String specNo) {
		this.specNo = specNo;
	}
    
}