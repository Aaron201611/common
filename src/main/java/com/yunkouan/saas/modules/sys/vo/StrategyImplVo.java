package com.yunkouan.saas.modules.sys.vo;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.modules.sys.entity.SysStrategyImpl;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 【策略】数据传输对象
 * @author tphe06 2017年2月13日
 */
public final class StrategyImplVo extends BaseVO {
	private static final long serialVersionUID = -8139661914276136360L;

	/**【策略分类】实体类*/
	@Valid
	private SysStrategyImpl entity;
	/**@Fields 排序字段 */
	private String orderBy = "create_time desc";

	public StrategyImplVo(){
		this.entity = new SysStrategyImpl();
	}
	public StrategyImplVo(SysStrategyImpl entity) {
		if(entity == null) return;
		this.entity = entity;
	}

	@Override
	public Example getExample() {
		Example example = new Example(SysStrategyImpl.class);
		example.setOrderByClause(orderBy);
		if(entity == null) return example;
		Criteria c = example.createCriteria();
		if(entity.getStrategyDefalut() != null) {
			c.andEqualTo("strategyDefalut", entity.getStrategyDefalut());
		}
		if(StringUtils.isNoneBlank(entity.getStrategyImplNo())) {
			c.andEqualTo("strategyImplNo", entity.getStrategyImplNo());
		}
		return example;
	}

	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public SysStrategyImpl getEntity() {
		return entity;
	}
	public void setEntity(SysStrategyImpl entity) {
		if(entity == null) return;
		this.entity = entity;
	}
}