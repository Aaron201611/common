/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年2月23日 下午2:10:16<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.entity;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.yunkouan.base.BaseEntity;
import com.yunkouan.valid.ValidSave;
import com.yunkouan.valid.ValidUpdate;

/**
 * 库位实体类<br/><br/>
 * @version 2017年2月23日 下午2:10:16<br/>
 * @author andy wang<br/>
 */
public class MetaLocation extends BaseEntity {
	
	private static final long serialVersionUID = -9061916742551780870L;

	/**
	 * 库位id
	 * @version 2017年2月23日下午2:10:42<br/>
	 * @author andy wang<br/>
	 */
	@Id
    private String locationId;

    /**
	 * 库位编号
	 * @version 2017年2月23日下午2:10:47<br/>
	 * @author andy wang<br/>
	 */
	@NotNull(message = "{valid_main_location_locationNo_notnull}",groups={ValidSave.class,ValidUpdate.class})
    @Length(max=32, message="{valid_main_location_locationNo_length}",groups={ValidSave.class,ValidUpdate.class})
    private String locationNo;

    /**
	 * 库位名称
	 * @version 2017年2月23日下午2:10:52<br/>
	 * @author andy wang<br/>
	 */
	@NotNull(message = "{valid_main_location_locationName_notnull}",groups={ValidSave.class,ValidUpdate.class})
    @Length(max=16, message="{valid_main_location_locationName_length}",groups={ValidSave.class,ValidUpdate.class})
    private String locationName;

    /**
	 * 库区id
	 * @version 2017年2月23日下午2:10:56<br/>
	 * @author andy wang<br/>
	 */
    private String areaId;

    /**
	 * 仓库id
	 * @version 2017年2月23日下午2:11:00<br/>
	 * @author andy wang<br/>
	 */
    private String warehouseId;

    /**
	 * 企业id
	 * @version 2017年2月23日下午2:11:04<br/>
	 * @author andy wang<br/>
	 */
    private String orgId;

    /**
	 * 包装id
	 * @version 2017年2月23日下午2:11:07<br/>
	 * @author andy wang<br/>
	 */
    private String packId;

    private String skuId;
    private String batchNo;
    /**
	 * 是否冻结
	 * @version 2017年2月23日下午2:11:11<br/>
	 * @author andy wang<br/>
	 */
    private Integer isBlock;

    /**
	 * 库位状态
	 * @version 2017年2月23日下午2:11:14<br/>
	 * @author andy wang<br/>
	 */
    private Integer locationStatus;

    /**
	 * 当前包装最大容量
	 * @version 2017年2月23日下午2:11:18<br/>
	 * @author andy wang<br/>
	 */
    @DecimalMax(value="99999999999",message="{valid_main_location_maxCapacity_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private BigDecimal maxCapacity;

    /**
	 * 已使用的库容量
	 * @version 2017年2月23日下午2:11:21<br/>
	 * @author andy wang<br/>
	 */
    @DecimalMax(value="99999999999",message="{valid_main_location_usedCapacity_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private BigDecimal usedCapacity;

    /**
	 * 预分配库容量
	 * @version 2017年2月23日下午2:11:25<br/>
	 * @author andy wang<br/>
	 */
    @DecimalMax(value="99999999999",message="{valid_main_location_preusedCapacity_decimalMax}",groups={ValidSave.class,ValidUpdate.class})
    private BigDecimal preusedCapacity;

    /**
	 * 区
	 * @version 2017年2月23日下午2:11:28<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=32, message="{valid_main_location_locationZone_length}",groups={ValidSave.class,ValidUpdate.class})
    private String locationZone;

    /**
	 * 排
	 * @version 2017年2月23日下午2:11:34<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=32, message="{valid_main_location_locationRow_length}",groups={ValidSave.class,ValidUpdate.class})
    private String locationRow;

    
    /**
	 * 列
	 * @version 2017年2月23日下午2:11:37<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=32, message="{valid_main_location_locationColumn_length}",groups={ValidSave.class,ValidUpdate.class})
    private String locationColumn;

    /**
	 * 层
	 * @version 2017年2月23日下午2:11:41<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=32, message="{valid_main_location_layer_length}",groups={ValidSave.class,ValidUpdate.class})
    private String layer;

    /**
	 * 备注
	 * @version 2017年2月23日下午2:11:44<br/>
	 * @author andy wang<br/>
	 */
    @Length(max=512, message="{valid_main_location_note_length}",groups={ValidSave.class,ValidUpdate.class})
    private String note;

    /**
	 * 库位规格id
	 * @version 2017年2月23日下午2:11:48<br/>
	 * @author andy wang<br/>
	 */
    private String specId;

	/**
	 * 库位备用id
	 * @version 2017年2月23日下午2:12:01<br/>
	 * @author andy wang<br/>
	 */
    private Integer locationId2;

    /**
	 * 库容占比
	 * @version 2017年2月23日下午2:12:06<br/>
	 * @author andy wang<br/>
	 */
    private BigDecimal capacityUseRate;

    /**
	 * X轴
	 * @version 2017年3月7日下午8:20:10<br/>
	 * @author andy wang<br/>
	 */
    @Max(value=99999999999l,message="valid_main_location_x_max",groups={ValidSave.class,ValidUpdate.class})
    private Integer x;
    
    /**
	 * Y轴
	 * @version 2017年3月7日下午8:20:25<br/>
	 * @author andy wang<br/>
	 */
    @Max(value=99999999999l,message="valid_main_location_y_max",groups={ValidSave.class,ValidUpdate.class})
    private Integer y;
    
    /**
	 * Z轴
	 * @version 2017年3月7日下午8:20:32<br/>
	 * @author andy wang<br/>
	 */
    @Max(value=99999999999l,message="valid_main_location_z_max",groups={ValidSave.class,ValidUpdate.class})
    private Integer z;
    
    /**
	 * 货主id
	 * @version 2017年3月14日下午4:05:14<br/>
	 * 林总组织讨论后决定添加
	 * 讨论地点：张代龙桌子
	 * 讨论结果：全体通过
	 * @author andy wang<br/>
	 */
    private String owner;
    
    /**
	 * 是否默认库位
	 * @version 2017年3月22日上午11:02:00<br/>
	 * @author andy wang<br/>
	 */
    private Integer isDefault;
    
    /**
	 * 库位类型
	 * @version 2017年3月22日上午11:04:26<br/>
	 * @author andy wang<br/>
	 */
    private Integer locationType;
    
    /* getset ************************************************/
    
	/**
	 * 属性 locationId getter方法
	 * @return 属性locationId
	 * @author andy wang<br/>
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * 属性 locationId setter方法
	 * @param locationId 设置属性locationId的值
	 * @author andy wang<br/>
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * 属性 x getter方法
	 * @return 属性x
	 * @author andy wang<br/>
	 */
	public Integer getX() {
		return x;
	}

	/**
	 * 属性 x setter方法
	 * @param x 设置属性x的值
	 * @author andy wang<br/>
	 */
	public void setX(Integer x) {
		this.x = x;
	}

	/**
	 * 属性 y getter方法
	 * @return 属性y
	 * @author andy wang<br/>
	 */
	public Integer getY() {
		return y;
	}

	/**
	 * 属性 y setter方法
	 * @param y 设置属性y的值
	 * @author andy wang<br/>
	 */
	public void setY(Integer y) {
		this.y = y;
	}

	/**
	 * 属性 z getter方法
	 * @return 属性z
	 * @author andy wang<br/>
	 */
	public Integer getZ() {
		return z;
	}

	/**
	 * 属性 z setter方法
	 * @param z 设置属性z的值
	 * @author andy wang<br/>
	 */
	public void setZ(Integer z) {
		this.z = z;
	}

	/**
	 * 属性 locationNo getter方法
	 * @return 属性locationNo
	 * @author andy wang<br/>
	 */
	public String getLocationNo() {
		return locationNo;
	}

	/**
	 * 属性 locationNo setter方法
	 * @param locationNo 设置属性locationNo的值
	 * @author andy wang<br/>
	 */
	public void setLocationNo(String locationNo) {
		this.locationNo = locationNo;
	}

	/**
	 * 属性 locationName getter方法
	 * @return 属性locationName
	 * @author andy wang<br/>
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 属性 locationName setter方法
	 * @param locationName 设置属性locationName的值
	 * @author andy wang<br/>
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

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
	 * 属性 packId getter方法
	 * @return 属性packId
	 * @author andy wang<br/>
	 */
	public String getPackId() {
		return packId;
	}

	/**
	 * 属性 packId setter方法
	 * @param packId 设置属性packId的值
	 * @author andy wang<br/>
	 */
	public void setPackId(String packId) {
		this.packId = packId;
	}

	/**
	 * 属性 isBlock getter方法
	 * @return 属性isBlock
	 * @author andy wang<br/>
	 */
	public Integer getIsBlock() {
		return isBlock;
	}

	/**
	 * 属性 isBlock setter方法
	 * @param isBlock 设置属性isBlock的值
	 * @author andy wang<br/>
	 */
	public void setIsBlock(Integer isBlock) {
		this.isBlock = isBlock;
	}

	/**
	 * 属性 locationStatus getter方法
	 * @return 属性locationStatus
	 * @author andy wang<br/>
	 */
	public Integer getLocationStatus() {
		return locationStatus;
	}

	/**
	 * 属性 locationStatus setter方法
	 * @param locationStatus 设置属性locationStatus的值
	 * @author andy wang<br/>
	 */
	public void setLocationStatus(Integer locationStatus) {
		this.locationStatus = locationStatus;
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
	 * 属性 usedCapacity getter方法
	 * @return 属性usedCapacity
	 * @author andy wang<br/>
	 */
	public BigDecimal getUsedCapacity() {
		return usedCapacity;
	}

	/**
	 * 属性 usedCapacity setter方法
	 * @param usedCapacity 设置属性usedCapacity的值
	 * @author andy wang<br/>
	 */
	public void setUsedCapacity(BigDecimal usedCapacity) {
		this.usedCapacity = usedCapacity;
	}

	/**
	 * 属性 preusedCapacity getter方法
	 * @return 属性preusedCapacity
	 * @author andy wang<br/>
	 */
	public BigDecimal getPreusedCapacity() {
		return preusedCapacity;
	}

	/**
	 * 属性 preusedCapacity setter方法
	 * @param preusedCapacity 设置属性preusedCapacity的值
	 * @author andy wang<br/>
	 */
	public void setPreusedCapacity(BigDecimal preusedCapacity) {
		this.preusedCapacity = preusedCapacity;
	}

	/**
	 * 属性 locationZone getter方法
	 * @return 属性locationZone
	 * @author andy wang<br/>
	 */
	public String getLocationZone() {
		return locationZone;
	}

	/**
	 * 属性 locationZone setter方法
	 * @param locationZone 设置属性locationZone的值
	 * @author andy wang<br/>
	 */
	public void setLocationZone(String locationZone) {
		this.locationZone = locationZone;
	}

	/**
	 * 属性 locationRow getter方法
	 * @return 属性locationRow
	 * @author andy wang<br/>
	 */
	public String getLocationRow() {
		return locationRow;
	}

	/**
	 * 属性 locationRow setter方法
	 * @param locationRow 设置属性locationRow的值
	 * @author andy wang<br/>
	 */
	public void setLocationRow(String locationRow) {
		this.locationRow = locationRow;
	}

	/**
	 * 属性 locationColumn getter方法
	 * @return 属性locationColumn
	 * @author andy wang<br/>
	 */
	public String getLocationColumn() {
		return locationColumn;
	}

	/**
	 * 属性 locationColumn setter方法
	 * @param locationColumn 设置属性locationColumn的值
	 * @author andy wang<br/>
	 */
	public void setLocationColumn(String locationColumn) {
		this.locationColumn = locationColumn;
	}

	/**
	 * 属性 layer getter方法
	 * @return 属性layer
	 * @author andy wang<br/>
	 */
	public String getLayer() {
		return layer;
	}

	/**
	 * 属性 layer setter方法
	 * @param layer 设置属性layer的值
	 * @author andy wang<br/>
	 */
	public void setLayer(String layer) {
		this.layer = layer;
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
	 * 属性 locationId2 getter方法
	 * @return 属性locationId2
	 * @author andy wang<br/>
	 */
	public Integer getLocationId2() {
		return locationId2;
	}

	/**
	 * 属性 locationId2 setter方法
	 * @param locationId2 设置属性locationId2的值
	 * @author andy wang<br/>
	 */
	public void setLocationId2(Integer locationId2) {
		this.locationId2 = locationId2;
	}

	/**
	 * 属性 capacityUseRate getter方法
	 * @return 属性capacityUseRate
	 * @author andy wang<br/>
	 */
	public BigDecimal getCapacityUseRate() {
		return capacityUseRate;
	}

	/**
	 * 属性 capacityUseRate setter方法
	 * @param capacityUseRate 设置属性capacityUseRate的值
	 * @author andy wang<br/>
	 */
	public void setCapacityUseRate(BigDecimal capacityUseRate) {
		this.capacityUseRate = capacityUseRate;
	}

	/**
	 * 属性 owner getter方法
	 * @return 属性owner
	 * @author andy wang<br/>
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * 属性 owner setter方法
	 * @param owner 设置属性owner的值
	 * @author andy wang<br/>
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * 属性 isDefault getter方法
	 * @return 属性isDefault
	 * @author andy wang<br/>
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * 属性 isDefault setter方法
	 * @param isDefault 设置属性isDefault的值
	 * @author andy wang<br/>
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * 属性 locationType getter方法
	 * @return 属性locationType
	 * @author andy wang<br/>
	 */
	public Integer getLocationType() {
		return locationType;
	}

	/**
	 * 属性 locationType setter方法
	 * @param locationType 设置属性locationType的值
	 * @author andy wang<br/>
	 */
	public void setLocationType(Integer locationType) {
		this.locationType = locationType;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
   
}