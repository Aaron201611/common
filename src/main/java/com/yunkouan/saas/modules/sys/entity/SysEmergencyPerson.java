package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;

import com.yunkouan.base.BaseEntity;

/**
* @Description: 【紧急联系人】实体类
* @author tphe06
* @date 2017年3月16日
*/
public class SysEmergencyPerson extends BaseEntity {
	private static final long serialVersionUID = 166329531986342136L;

	/**@Fields 紧急联系人id */
	@Id
	private String emergencyId;
	/**@Fields 紧急联系人姓名 */
	private String userName;
	/**@Fields 紧急联系人电话 */
	private String phone;

	public String getEmergencyId() {
		return emergencyId;
	}
	public String getUserName() {
		return userName;
	}
	public String getPhone() {
		return phone;
	}
	public SysEmergencyPerson setEmergencyId(String emergencyId) {
		this.emergencyId = emergencyId;
		return this;
	}
	public SysEmergencyPerson setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public SysEmergencyPerson setPhone(String phone) {
		this.phone = phone;
		return this;
	}
}