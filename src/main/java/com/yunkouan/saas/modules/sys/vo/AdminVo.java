package com.yunkouan.saas.modules.sys.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.common.constant.Constant;
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.entity.SysAdmin;
import com.yunkouan.saas.modules.sys.entity.SysAdminRole;
import com.yunkouan.saas.modules.sys.entity.SysOrg;
import com.yunkouan.saas.modules.sys.entity.SysUser;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 【管理员帐号】数据传输对象
 * @author tphe06 2017年2月14日
 */
public final class AdminVo extends BaseVO {
	private static final long serialVersionUID = -6973306387564367005L;

	/**【管理员帐号】实体类**/
	@Valid
	private SysAdmin entity;
	/**@Fields 排序字段 */
	private String orderBy = "admin_id2 desc";
	/**@Fields 编号模糊查询字段 */
	private String adminNoLike;
	/**@Fields 名称模糊查询字段 */
	private String adminNameLike;
	/**@Fields 排除取消状态 */
	private Integer adminStatusNot;

	/**修改密码时的旧密码**/
	private String oldPwd;
	/**管理员帐号对应的【企业】实体类**/
	private SysOrg org;
	/**管理员帐号绑定的【用户】实体类**/
	private SysUser user;
	/**@Fields 【用户】数据传输类 */
	private UserVo vo;
	/**管理员帐号授权【角色】列表信息**/
	private List<SysAdminRole> list;
	private List<AdminRoleVo> volist;
	/**@Fields 状态名称 */
	private String statusName;
	/**@Fields 当前登录帐号信息 */
	private Principal p;

	private Boolean pwdEncrypted;

	public AdminVo(){}
	public AdminVo(SysAdmin entity) {
		this.entity = entity;
		if(this.entity == null) return;
		this.statusName = Constant.getStatus(this.entity.getAdminStatus());
	}

	@Override
	public Example getExample() {
		Example example = new Example(SysAdmin.class);
		example.setOrderByClause(orderBy);
		Criteria c = example.createCriteria();
		if(StringUtils.isNoneBlank(adminNoLike)) {
			c.andLike("adminNo", "%"+adminNoLike+"%");
		}
		if(StringUtils.isNoneBlank(adminNameLike)) {
			c.andLike("adminName", "%"+adminNameLike+"%");
		}
		if(entity != null) {
			if(StringUtils.isNoneBlank(entity.getAdminNo())) {
				c.andEqualTo("adminNo", entity.getAdminNo());
			}
			if(StringUtils.isNoneBlank(entity.getAdminName())) {
				c.andEqualTo("adminName", entity.getAdminName());
			}
			if(StringUtils.isNoneBlank(entity.getUserId())) {
				c.andEqualTo("userId", entity.getUserId());
			}
			if(StringUtils.isNoneBlank(entity.getOrgId())) {
				c.andEqualTo("orgId", entity.getOrgId());
			}
			if(entity.getAdminStatus() != null) {
				c.andEqualTo("adminStatus", entity.getAdminStatus());
			}
		}
		if(adminStatusNot != null) {
			c.andNotEqualTo("adminStatus", adminStatusNot);
		}
		return example;
	}

	public SysAdmin getEntity() {
		return entity;
	}

	public List<SysAdminRole> getList() {
		return list;
	}

	public AdminVo setList(List<SysAdminRole> list) {
		this.list = list;
		return this;
	}
	public AdminVo addRole(String roleid) {
		if(this.list == null) this.list = new ArrayList<SysAdminRole>();
		this.list.add(new SysAdminRole().setRoleId(roleid));
		return this;
	}

	public SysUser getUser() {
		return user;
	}

	public AdminVo setUser(SysUser user) {
		this.user = user;
		return this;
	}

	public SysOrg getOrg() {
		return org;
	}

	public AdminVo setOrg(SysOrg org) {
		this.org = org;
		return this;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public AdminVo setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
		return this;
	}
	public String getStatusName() {
		return statusName;
	}
	public Principal getP() {
		return p;
	}
	public AdminVo setP(Principal p) {
		this.p = p;
		return this;
	}
	public UserVo getVo() {
		return vo;
	}
	public AdminVo setVo(UserVo vo) {
		this.vo = vo;
		return this;
	}
	public AdminVo setAdminNoLike(String adminNoLike) {
		this.adminNoLike = adminNoLike;
		return this;
	}
	public AdminVo setAdminNameLike(String adminNameLike) {
		this.adminNameLike = adminNameLike;
		return this;
	}
	public Boolean getPwdEncrypted() {
		return pwdEncrypted;
	}
	public AdminVo setPwdEncrypted(Boolean pwdEncrypted) {
		this.pwdEncrypted = pwdEncrypted;
		return this;
	}
	public List<AdminRoleVo> getVolist() {
		return volist;
	}
	public AdminVo setVolist(List<AdminRoleVo> volist) {
		this.volist = volist;
		return this;
	}
	public AdminVo setEntity(SysAdmin entity) {
		this.entity = entity;
		return this;
	}
	public AdminVo setAdminStatusNot(Integer adminStatusNot) {
		this.adminStatusNot = adminStatusNot;
		return this;
	}
}