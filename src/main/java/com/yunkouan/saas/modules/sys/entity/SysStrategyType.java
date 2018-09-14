package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.yunkouan.base.BaseEntity;

/**
 * @function 策略分类entity
 * @author tphe06
 */
@Table(name="sys_strategy")
public class SysStrategyType extends BaseEntity {
	private static final long serialVersionUID = -956937326227578792L;

	@Id
	private String strategyId;
	/**strategyNo:策略编号**/
	private String strategyNo;
	/**strategyName:策略名称**/
	private String strategyName;

	public String getStrategyId() {
		return strategyId;
	}
	public SysStrategyType setStrategyId(String strategyId) {
		this.strategyId = strategyId;
		return this;
	}
	public String getStrategyNo() {
		return strategyNo;
	}
	public SysStrategyType setStrategyNo(String strategyNo) {
		this.strategyNo = strategyNo;
		return this;
	}
	public String getStrategyName() {
		return strategyName;
	}
	public SysStrategyType setStrategyName(String strategyName) {
		this.strategyName = strategyName;
		return this;
	}
}