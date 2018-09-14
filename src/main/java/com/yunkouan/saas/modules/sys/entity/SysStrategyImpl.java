package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.yunkouan.base.BaseEntity;

/**
 * @function 策略entity
 * @author tphe06
 */
@Table(name="sys_strategy_impl")
public class SysStrategyImpl extends BaseEntity {
	private static final long serialVersionUID = 6019558416445113885L;

	@Id
	private String strategyImplId;
	/**strategyImplNo:策略实现编号**/
	private String strategyImplNo;
	/**strategyImplName:策略实现名称**/
	private String strategyImplName;
	/**strategyDefalut:是否默认实现，0否1是**/
	private Integer strategyDefalut;
	/**strategyId:策略id**/
	private String strategyId;

	public String getStrategyImplId() {
		return strategyImplId;
	}
	public SysStrategyImpl setStrategyImplId(String strategyImplId) {
		this.strategyImplId = strategyImplId;
		return this;
	}
	public String getStrategyImplNo() {
		return strategyImplNo;
	}
	public SysStrategyImpl setStrategyImplNo(String strategyImplNo) {
		this.strategyImplNo = strategyImplNo;
		return this;
	}
	public String getStrategyImplName() {
		return strategyImplName;
	}
	public SysStrategyImpl setStrategyImplName(String strategyImplName) {
		this.strategyImplName = strategyImplName;
		return this;
	}
	public Integer getStrategyDefalut() {
		return strategyDefalut;
	}
	public SysStrategyImpl setStrategyDefalut(Integer strategyDefalut) {
		this.strategyDefalut = strategyDefalut;
		return this;
	}
	public String getStrategyId() {
		return strategyId;
	}
	public SysStrategyImpl setStrategyId(String strategyId) {
		this.strategyId = strategyId;
		return this;
	}
}