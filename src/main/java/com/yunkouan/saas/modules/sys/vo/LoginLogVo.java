package com.yunkouan.saas.modules.sys.vo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.modules.sys.entity.SysLoginLog;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
* @Description: 【系统登录日志】数据传输类
* @author tphe06
* @date 2017年3月16日
*/
public class LoginLogVo extends BaseVO {
	private static final long serialVersionUID = -253735753908407657L;

	private SysLoginLog entity;
	/**@Fields 排序字段 */
	private String orderBy = "login_time desc";

	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", locale="zh", timezone="GMT+8")
	private Date beginTime;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", locale="zh", timezone="GMT+8")
	private Date endTime;

	public LoginLogVo() {}
	public LoginLogVo(SysLoginLog entity) {
		this.entity = entity;
	}

	public Example getExample() {
		Example example = new Example(SysLoginLog.class);
		example.setOrderByClause(orderBy);
		if(entity == null) return example;
		Criteria c = example.createCriteria();
		if(StringUtils.isNoneBlank(entity.getUserNo())) {
			c.andEqualTo("userNo", entity.getUserNo());
		}
		if(beginTime != null) {
			c.andGreaterThan("loginTime", beginTime);
		}
		if(endTime != null) {
			c.andLessThan("loginTime", endTime);
		}
		if(entity.getOpType()!= null) {
			c.andEqualTo("opType", entity.getOpType());
		}
		return example;
	}

	public SysLoginLog getEntity() {
		return entity;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public LoginLogVo setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
		return this;
	}
	public LoginLogVo setEndTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}
}