package com.yunkouan.saas.modules.sys.vo;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.modules.sys.entity.SysStrategyType;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 【策略分类】数据传输对象
 * @author tphe06 2017年2月13日
 */
public final class StrategyTypeVo extends BaseVO {
	private static final long serialVersionUID = 4212517506752297012L;

	/**【策略分类】实体类*/
	@Valid
	private SysStrategyType entity;
	/**@Fields 排序字段 */
	private String orderBy = "create_time desc";

	public StrategyTypeVo(){
		this.entity = new SysStrategyType();
	}
	public StrategyTypeVo(SysStrategyType entity) {
		if(entity == null) return;
		this.entity = entity;
	}

	@Override
	public Example getExample() {
		Example example = new Example(SysStrategyType.class);
		example.setOrderByClause(orderBy);
		if(entity == null) return example;
		Criteria c = example.createCriteria();
		if(StringUtils.isNoneBlank(entity.getStrategyNo())) {
			c.andEqualTo("strategyNo", entity.getStrategyNo());
		}
		return example;
	}

	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public SysStrategyType getEntity() {
		return entity;
	}
	public void setEntity(SysStrategyType entity) {
		if(entity == null) return;
		this.entity = entity;
	}
}