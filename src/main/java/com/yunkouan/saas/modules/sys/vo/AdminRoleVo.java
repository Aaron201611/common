package com.yunkouan.saas.modules.sys.vo;

import java.util.List;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.modules.sys.entity.SysAdminRole;
import com.yunkouan.saas.modules.sys.entity.SysAuth;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 【管理员角色】数据传输对象
 * @author tphe06 2017年2月13日
 */
public final class AdminRoleVo extends BaseVO {
	private static final long serialVersionUID = 4176086380884057204L;

	/**【管理员角色】实体类*/
	@Valid
	private SysAdminRole entity;
	/**@Fields 排序字段 */
	private String orderBy = "role_id2 desc";
	/**@Fields 角色编号模糊查询字段 */
	private String roleNoLike;
	/**@Fields 角色名称模糊查询字段 */
	private String roleNameLike;
	/**@Fields 角色描述模糊查询字段 */
	private String roleDescLike;
	/**@Fields 排除取消状态 */
	private Integer roleStatusNot;

	/**管理员授权【权限】列表**/
	private List<SysAuth> list;
	private List<AuthVo> volist;

	/**@Fields 配合前端页面使用，标识是否选中 */
	private Boolean selected;

	public AdminRoleVo(){}
	public AdminRoleVo(SysAdminRole entity) {
		this.entity = entity;
	}

	@Override
	public Example getExample() {
		Example example = new Example(SysAdminRole.class);
		example.setOrderByClause(orderBy);
		if(entity == null) return example;
		Criteria c = example.createCriteria();
		if(StringUtils.isNoneBlank(roleNoLike)) {
			c.andLike("roleNo", "%"+roleNoLike+"%");
		}
		if(StringUtils.isNoneBlank(roleNameLike)) {
			c.andLike("roleName", "%"+roleNameLike+"%");
		}
		if(StringUtils.isNoneBlank(roleDescLike)) {
			c.andLike("roleDesc", "%"+roleDescLike+"%");
		}
		if(StringUtils.isNoneBlank(entity.getRoleNo())) {
			c.andEqualTo("roleNo", entity.getRoleNo());
		}
		if(StringUtils.isNoneBlank(entity.getRoleName())) {
			c.andEqualTo("roleName", entity.getRoleName());
		}
		if(StringUtils.isNoneBlank(entity.getRoleDesc())) {
			c.andEqualTo("roleDesc", entity.getRoleDesc());
		}
		if(entity.getRoleStatus() != null) {
			c.andEqualTo("roleStatus", entity.getRoleStatus());
		}
		if(roleStatusNot != null) {
			c.andNotEqualTo("roleStatus", roleStatusNot);
		}
		return example;
	}

	public SysAdminRole getEntity() {
		return entity;
	}

	public List<SysAuth> getList() {
		return list;
	}

	public AdminRoleVo setList(List<SysAuth> list) {
		this.list = list;
		return this;
	}
	public List<AuthVo> getVolist() {
		return volist;
	}
	public AdminRoleVo setVolist(List<AuthVo> volist) {
		this.volist = volist;
		return this;
	}
	public AdminRoleVo setRoleNoLike(String roleNoLike) {
		this.roleNoLike = roleNoLike;
		return this;
	}
	public AdminRoleVo setRoleNameLike(String roleNameLike) {
		this.roleNameLike = roleNameLike;
		return this;
	}
	public AdminRoleVo setRoleDescLike(String roleDescLike) {
		this.roleDescLike = roleDescLike;
		return this;
	}
	public Boolean getSelected() {
		return selected;
	}
	public AdminRoleVo setSelected(Boolean selected) {
		this.selected = selected;
		return this;
	}
	public AdminRoleVo setRoleStatusNot(Integer roleStatusNot) {
		this.roleStatusNot = roleStatusNot;
		return this;
	}
}