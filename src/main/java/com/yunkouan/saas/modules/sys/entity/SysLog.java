package com.yunkouan.saas.modules.sys.entity;

import java.util.Date;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunkouan.base.BaseEntity;

/**
* @Description: 【系统操作日志】实体类
* @author tphe06
* @date 2017年3月15日
*/
public class SysLog extends BaseEntity {
	private static final long serialVersionUID = 2234032090915260913L;

	/**@Fields 日志id */
	@Id
	private String logId;

    /**@Fields 企业id */
    private String orgId;

    /**@Fields 作业时间 */
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", locale="zh", timezone="GMT+8")
    private Date opTime;

    /**@Fields 操作人id */
    private String opPerson;

    /**@Fields 操作模块 */
    private String opModel;

    /**@Fields 操作按钮 */
    private String opButton;

    /**@Fields 是否系统错误，0非，1是 */
    private Integer isError;

    /**@Fields 日志内容 */
    private String logContent;

    /**@Fields 仓库id */
    private String warehouseId;

    private Integer logId2;

    @Override
	public void clear() {
    	/**不能由前端修改，添加时候都赋空值**/
    	this.logId2 = null;
		super.clear();
	}

    public String getLogId() {
        return logId;
    }

    public SysLog setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
        return this;
    }

    public String getOrgId() {
        return orgId;
    }

    public SysLog setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
        return this;
    }

    public Date getOpTime() {
        return opTime;
    }

    public SysLog setOpTime(Date opTime) {
        this.opTime = opTime;
        return this;
    }

    public String getOpPerson() {
        return opPerson;
    }

    public SysLog setOpPerson(String opPerson) {
        this.opPerson = opPerson == null ? null : opPerson.trim();
        return this;
    }

    public String getOpModel() {
        return opModel;
    }

    public SysLog setOpModel(String opModel) {
        this.opModel = opModel == null ? null : opModel.trim();
        return this;
    }

    public String getOpButton() {
        return opButton;
    }

    public SysLog setOpButton(String opButton) {
        this.opButton = opButton == null ? null : opButton.trim();
        return this;
    }

    public Integer getIsError() {
        return isError;
    }

    public SysLog setIsError(Integer isError) {
        this.isError = isError;
        return this;
    }

    public String getLogContent() {
        return logContent;
    }

    public SysLog setLogContent(String logContent) {
        this.logContent = logContent == null ? null : logContent.trim();
        return this;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public SysLog setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
        return this;
    }

    public Integer getLogId2() {
        return logId2;
    }

    public SysLog setLogId2(Integer logId2) {
        this.logId2 = logId2;
        return this;
    }
}