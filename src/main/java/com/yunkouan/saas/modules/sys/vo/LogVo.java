package com.yunkouan.saas.modules.sys.vo;

import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.modules.sys.entity.SysLog;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 日志数据传输对象
 * @author tphe06 2017年2月13日
 */
public final class LogVo extends BaseVO {
	private static final long serialVersionUID = -6778943337165368631L;

	/**@Fields 日志实体类 */
	@Valid
	private SysLog entity;
	/**@Fields 排序字段 */
	private String orderBy = "log_id2 desc";

	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", locale="zh", timezone="GMT+8")
	private Date startTime;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", locale="zh", timezone="GMT+8")
	private Date endTime;

	public LogVo() {}
	public LogVo(SysLog entity) {
		this.entity = entity;
	}

	@Override
	public Example getExample() {
		Example example = new Example(SysLog.class);
		example.setOrderByClause(orderBy);
		if(entity == null) return example;
		Criteria c = example.createCriteria();
		if(StringUtils.isNoneBlank(entity.getOpPerson())) {
			c.andEqualTo("opPerson", entity.getOpPerson());
		}
		if(StringUtils.isNoneBlank(entity.getOpModel())) {
			c.andEqualTo("opModel", entity.getOpModel());
		}
		if(StringUtils.isNoneBlank(entity.getOrgId())) {
			c.andEqualTo("orgId", entity.getOrgId());
		}
		if(StringUtils.isNoneBlank(entity.getWarehouseId())) {
			c.andEqualTo("warehouseId", entity.getWarehouseId());
		}
		if(startTime != null) c.andGreaterThan("opTime", startTime);
		if(endTime != null) c.andLessThan("opTime", endTime);
		return example;
	}

	public SysLog getEntity() {
		return entity;
	}
	public Date getStartTime() {
		return startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public LogVo setStartTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}
	public LogVo setEndTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}
}