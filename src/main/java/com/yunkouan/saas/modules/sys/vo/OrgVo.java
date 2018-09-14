package com.yunkouan.saas.modules.sys.vo;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.common.constant.Constant;
import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.entity.SysOrg;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 【企业】数据传输对象
 * @author tphe06 2017年2月13日
 */
public final class OrgVo extends BaseVO {
	private static final long serialVersionUID = 8431819662559195839L;

	/**【企业】实体类*/
	@Valid
	private SysOrg entity;
	/**@Fields 排序字段 */
	private String orderBy = "org_id2 desc";
	/**@Fields 企业编号模糊查询字段 */
	private String orgNoLike;
	/**@Fields 企业名称模糊查询字段 */
	private String orgNameLike;
	/**@Fields 企业简称模糊查询字段 */
	private String orgShortNameLike;
	
	private String contactPersonLike;
	/**@Fields 企业类型中文名称 */
	private String orgTypeName;
	private Integer orgStatusNot;

	private String statusName;
	/**【企业授权权限】列表**/
	private List<SysAuth> list;
	private List<AuthVo> volist;

	private Boolean hasUsers;

	public OrgVo(){}
	public OrgVo(SysOrg entity) {
		this.entity = entity;
		if(this.entity == null) return;
		this.statusName = Constant.getStatus(this.entity.getOrgStatus());
		if("0".equals(this.entity.getOrgType())) this.orgTypeName = "园区";
		if("1".equals(this.entity.getOrgType())) this.orgTypeName = "企业";
	}

	@Override
	public Example getExample() {
		Example example = new Example(SysOrg.class);
		example.setOrderByClause(orderBy);
		if(entity == null) return example;
		Criteria c = example.createCriteria();
		if(StringUtils.isNoneBlank(orgNoLike)) {
			c.andLike("orgNo", "%"+orgNoLike+"%");
		}
		if(StringUtils.isNoneBlank(orgNameLike)) {
			c.andLike("orgName", "%"+orgNameLike+"%");
		}
		if(StringUtils.isNoneBlank(entity.getOrgType())) {
			c.andEqualTo("orgType", entity.getOrgType());
		}
		if(StringUtils.isNoneBlank(orgShortNameLike)) {
			c.andLike("orgShortName", "%"+orgShortNameLike+"%");
		}
		if(StringUtils.isNoneBlank(contactPersonLike)) {
			c.andLike("contactPerson", "%"+contactPersonLike+"%");
		}
		if(StringUtils.isNoneBlank(entity.getOrgNo())) {
			c.andEqualTo("orgNo", entity.getOrgNo());
		}
		if(StringUtils.isNoneBlank(entity.getOrgName())) {
			c.andEqualTo("orgName", entity.getOrgName());
		}
		if(StringUtils.isNoneBlank(entity.getOrgShortName())) {
			c.andEqualTo("orgShortName", entity.getOrgShortName());
		}
		if(StringUtils.isNoneBlank(entity.getContactPerson())) {
			c.andEqualTo("contactPerson", entity.getContactPerson());
		}
		if(entity.getOrgStatus() != null) {
			c.andEqualTo("orgStatus", entity.getOrgStatus());
		}
		if(orgStatusNot != null) {
			c.andNotEqualTo("orgStatus", orgStatusNot);
		}
		return example;
	}

	public SysOrg getEntity() {
		return entity;
	}

	public List<SysAuth> getList() {
		return list;
	}

	public OrgVo setList(List<SysAuth> list) {
		this.list = list;
		return this;
	}
	public String getStatusName() {
		return statusName;
	}
	public List<AuthVo> getVolist() {
		return volist;
	}
	public OrgVo setVolist(List<AuthVo> volist) {
		this.volist = volist;
		return this;
	}
	public OrgVo setOrgNoLike(String orgNoLike) {
		this.orgNoLike = orgNoLike;
		return this;
	}
	public OrgVo setOrgNameLike(String orgNameLike) {
		this.orgNameLike = orgNameLike;
		return this;
	}
	public OrgVo setOrgShortNameLike(String orgShortNameLike) {
		this.orgShortNameLike = orgShortNameLike;
		return this;
	}
	public OrgVo setOrgStatusNot(Integer orgStatusNot) {
		this.orgStatusNot = orgStatusNot;
		return this;
	}
	public String getOrgTypeName() {
		return orgTypeName;
	}
	public String getContactPersonLike() {
		return contactPersonLike;
	}
	public void setContactPersonLike(String contactPersonLike) {
		this.contactPersonLike = contactPersonLike;
	}
	public void setEntity(SysOrg entity) {
		this.entity = entity;
	}
	public Boolean getHasUsers() {
		return hasUsers;
	}
	public void setHasUsers(Boolean hasUsers) {
		this.hasUsers = hasUsers;
	}
}