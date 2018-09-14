package com.yunkouan.saas.modules.sys.service;

/**
 * @function 策略服务接口
 * @author tphe06
 */
public interface IStrategyService {
	/**
	 * 根据组织id和策略编号查找组织对应的策略实现
	 * @param orgid 组织id
	 * @param strategyNo 策略编号
	 * @return
	 */
	public String getStrategy(String orgid, String strategyNo);

	/**
	 * 根据组织id，仓库id和策略编号查找组织对应的策略实现
	 * @param orgid 组织id
	 * @param warehouseid 仓库id
	 * @param strategyNo 策略编号
	 * @return
	 */
	public String getStrategy(String orgid, String warehouseid, String strategyNo);
}