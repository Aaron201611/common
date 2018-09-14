package com.yunkouan.saas.modules.sys.entity;

import java.util.Date;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunkouan.base.BaseEntity;

/**
* @Description: 【登录日志】实体类
* @author tphe06
* @date 2017年3月16日
*/
public class SysLoginLog extends BaseEntity {
	private static final long serialVersionUID = -405587797161135531L;

	/**@Fields 登录日志id */
	@Id
	private String logId;
	/**@Fields 登录帐号 */
	private String userNo;
	/**@Fields 操作时间 */
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", locale="zh", timezone="GMT+8")
	private Date loginTime;
	/**@Fields 操作类型【0登录，1退出】 */
	private String opType;

	public String getLogId() {
		return logId;
	}
	public String getUserNo() {
		return userNo;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public String getOpType() {
		return opType;
	}
	public String getOpTypeTxt() {
		if("0".equals(opType)) return "登录";
		if("1".equals(opType)) return "退出";
		return "";
	}
	public SysLoginLog setLogId(String logId) {
		this.logId = logId;
		return this;
	}
	public SysLoginLog setUserNo(String userNo) {
		this.userNo = userNo;
		return this;
	}
	public SysLoginLog setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
		return this;
	}
	public SysLoginLog setOpType(String opType) {
		this.opType = opType;
		return this;
	}
}