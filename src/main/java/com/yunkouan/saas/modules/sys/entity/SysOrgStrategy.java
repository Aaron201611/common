package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;
import com.yunkouan.base.BaseEntity;

/**
 * @function 企业-策略关联实体
 * @author tphe06
 */
public class SysOrgStrategy extends BaseEntity {
	private static final long serialVersionUID = 4767516285301272981L;

	@Id
	private String orgStrategyId;
	/**orgId:企业id**/
	private String orgId;
	/**warehouseId:仓库id**/
	private String warehouseId;
	/**strategyImplId:策略实现id**/
	private String strategyImplId;

	public String getOrgStrategyId() {
		return orgStrategyId;
	}
	public SysOrgStrategy setOrgStrategyId(String orgStrategyId) {
		this.orgStrategyId = orgStrategyId;
		return this;
	}
	public String getOrgId() {
		return orgId;
	}
	public SysOrgStrategy setOrgId(String orgId) {
		this.orgId = orgId;
		return this;
	}
	public String getStrategyImplId() {
		return strategyImplId;
	}
	public SysOrgStrategy setStrategyImplId(String strategyImplId) {
		this.strategyImplId = strategyImplId;
		return this;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public SysOrgStrategy setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
		return this;
	}
}