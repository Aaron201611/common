package com.yunkouan.saas.modules.sys.service;

import java.util.List;
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.entity.SysStrategyImpl;
import com.yunkouan.saas.modules.sys.vo.StrategyImplVo;

/** 
* @Description: 策略service
* @author tphe06
* @date 2017年10月17日 下午1:55:43  
*/
public interface IStrategyImplService {
	/**
	 * 页面列表查询（分页）
	 * @param vo
	 * @param p 当前登录帐号
	 * @return
	 */
	public List<StrategyImplVo> list(StrategyImplVo vo, Principal p);
	/**
	 * 页面列表查询（不分页）
	 * @param vo
	 * @param p
	 * @return
	 */
	public List<StrategyImplVo> select(StrategyImplVo vo, Principal p);
	/**
	 * 根据属性查询实体
	 * @param entity
	 * @return
	 */
	public List<SysStrategyImpl> query(SysStrategyImpl entity);
	/**
	 * 根据属性查询单个实体
	 * @param entity
	 * @return
	 */
	public SysStrategyImpl queryOne(SysStrategyImpl entity);
	/**
	 * 根据主键获取单个实体
	 * @param id
	 * @return
	 */
	public SysStrategyImpl get(String id);
	/**
	 * 统计数量方法
	 * @param vo
	 * @param p
	 * @return
	 */
	public long count(StrategyImplVo vo, Principal p);
	/**
	 * 页面查看详情
	 * @param id
	 * @return
	 */
	public StrategyImplVo view(String id);
	/**
	 * 新增方法
	 * @param vo
	 * @param p
	 * @return
	 */
	public int insert(StrategyImplVo vo, Principal p);
	/**
	 * 修改方法
	 * @param vo
	 * @param p
	 * @return
	 */
	public int update(StrategyImplVo vo, Principal p);
	/**
	 * 删除方法
	 * @param id
	 */
	public int delete(String id);
	/** 
	* @Title: delete 
	* @Description: 批量删除
	* @auth tphe06
	* @time 2018 2018年8月24日 上午9:33:40
	* @param ids
	* void
	*/
	public void delete(String[] ids);
}