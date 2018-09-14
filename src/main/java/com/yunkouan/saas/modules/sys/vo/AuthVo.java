package com.yunkouan.saas.modules.sys.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.common.constant.Constant;
import com.yunkouan.saas.modules.sys.entity.SysAuth;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
* @Description: 权限数据传输对象
* @author tphe06
* @date 2017年3月29日
*/
public class AuthVo extends BaseVO {
	private static final long serialVersionUID = 7551758152976941974L;

	/**@Fields 权限实体类 */
	@Valid
	private SysAuth entity;
	/**@Fields 排序字段 */
	private String orderBy = "auth_id2 desc";
	/**@Fields 权限编号模糊查询字段 */
	private String authNoLike;
	/**@Fields 权限名称模糊查询字段 */
	private String authNameLike;
	/**@Fields 权限简称模糊查询字段 */
	private String authShortnameLike;
	private Integer authStatusNot;

	/**@Fields 配合前端显示，用来标识是否勾选 */
	private Boolean selectStatus;
	/**@Fields 上级权限 */
	private SysAuth parent;
	/**@Fields 状态名称 */
	private String statusName;
	/**@Fields 权限类型名称 */
	private String typeName;
	/**@Fields 权限级别名称 */
	private String levelName;
	/**子权限列表**/
	private List<AuthVo> list;
	/**权限类型**/
	private List<Integer> types;
	private List<Integer> levels;
	/**帐号id**/
	private String accountId;
	/**组织id**/
	private String orgId;
	/**仓库id**/
	private String warehouseId;

	public AuthVo clearAllNull() {
		if(warehouseId == null) warehouseId = "";
		if(orgId == null) orgId = "";
		if(accountId == null) accountId = "";
		if(levelName == null) levelName = "";
		if(typeName == null) typeName = "";
		if(statusName == null) statusName = "";
		if(authShortnameLike == null) authShortnameLike = "";
		if(authNameLike == null) authNameLike = "";
		if(authNoLike == null) authNoLike = "";
		if(authStatusNot == null) authStatusNot = 0;
		if(selectStatus == null) selectStatus = false;
		if(levels == null) levels = new ArrayList<Integer>();
		if(types == null) types = new ArrayList<Integer>();
		if(list == null) list = new ArrayList<AuthVo>();
		if(parent == null) {
			parent = new SysAuth().clearNull();
		} else {
			parent.clearNull();
		}
		if(entity == null) {
			entity= new SysAuth().clearNull();
		} else {
			entity.clearNull();
		}
		return this;
	}

	public AuthVo(){}
	public AuthVo(SysAuth entity){
		this.entity = entity;
		if(this.entity == null) return;
		this.statusName = Constant.getStatus(this.entity.getAuthStatus());
		this.typeName = SysAuth.getType(this.entity.getAuthType());
		this.levelName = SysAuth.getLevel(this.entity.getAuthLevel());
	}

	@Override
	public Example getExample() {
		Example example = new Example(SysAuth.class);
		example.setOrderByClause(orderBy);
		if(entity == null) return example;
		Criteria c = example.createCriteria();
		if(StringUtils.isNoneBlank(authNoLike)) {
			c.andLike("authNo", "%"+authNoLike+"%");
		}
		if(StringUtils.isNoneBlank(authNameLike)) {
			c.andLike("authName", "%"+authNameLike+"%");
		}
		if(StringUtils.isNoneBlank(authShortnameLike)) {
			c.andLike("authShortname", "%"+authShortnameLike+"%");
		}
		if(StringUtils.isNoneBlank(entity.getAuthNo())) {
			c.andEqualTo("authNo", entity.getAuthNo());
		}
		if(StringUtils.isNoneBlank(entity.getAuthName())) {
			c.andEqualTo("authName", entity.getAuthName());
		}
		if(StringUtils.isNoneBlank(entity.getAuthShortname())) {
			c.andEqualTo("authShortname", entity.getAuthShortname());
		}
		if(entity.getAuthStatus() != null) {
			c.andEqualTo("authStatus", entity.getAuthStatus());
		}
		if(authStatusNot != null) {
			c.andNotEqualTo("authStatus", authStatusNot);
		}
		return example;
	}

	public List<Integer> getTypes() {
		return types;
	}

	public AuthVo addTypes(Integer type) {
		if(this.types == null) this.types = new ArrayList<Integer>();
		this.types.add(type);
		return this;
	}

	public List<Integer> getLevels() {
		return levels;
	}

	public AuthVo addLevels(Integer level) {
		if(level == null) return this;
		if(this.levels == null) this.levels = new ArrayList<Integer>();
		this.levels.add(level);
		return this;
	}

	public String getAccountId() {
		return accountId;
	}

	public AuthVo setAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public AuthVo setOrgId(String orgId) {
		this.orgId = orgId;
		return this;
	}

	public AuthVo setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
		return this;
	}

	public List<AuthVo> getList() {
		return list;
	}

	public AuthVo setList(List<AuthVo> list) {
		this.list = list;
		return this;
	}

	public SysAuth getEntity() {
		return entity;
	}
	public SysAuth getParent() {
		return parent;
	}
	public AuthVo setParent(SysAuth parent) {
		this.parent = parent;
		return this;
	}
	public String getStatusName() {
		return statusName;
	}
	public String getTypeName() {
		return typeName;
	}
	public String getLevelName() {
		return levelName;
	}
	public Boolean getSelectStatus() {
		return selectStatus;
	}
	public AuthVo setSelectStatus(Boolean selectStatus) {
		this.selectStatus = selectStatus;
		return this;
	}
	public AuthVo setAuthStatusNot(Integer authStatusNot) {
		this.authStatusNot = authStatusNot;
		return this;
	}

	public AuthVo setEntity(SysAuth entity) {
		this.entity = entity;
		return this;
	}

	/**
	 * 属性 orderBy getter方法
	 * @return 属性orderBy
	 * @author 王通<br/>
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 属性 orderBy setter方法
	 * @param orderBy 设置属性orderBy的值
	 * @author 王通<br/>
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 属性 authNoLike getter方法
	 * @return 属性authNoLike
	 * @author 王通<br/>
	 */
	public String getAuthNoLike() {
		return authNoLike;
	}

	/**
	 * 属性 authNoLike setter方法
	 * @param authNoLike 设置属性authNoLike的值
	 * @author 王通<br/>
	 */
	public void setAuthNoLike(String authNoLike) {
		this.authNoLike = authNoLike;
	}

	/**
	 * 属性 authNameLike getter方法
	 * @return 属性authNameLike
	 * @author 王通<br/>
	 */
	public String getAuthNameLike() {
		return authNameLike;
	}

	/**
	 * 属性 authNameLike setter方法
	 * @param authNameLike 设置属性authNameLike的值
	 * @author 王通<br/>
	 */
	public void setAuthNameLike(String authNameLike) {
		this.authNameLike = authNameLike;
	}

	/**
	 * 属性 authShortnameLike getter方法
	 * @return 属性authShortnameLike
	 * @author 王通<br/>
	 */
	public String getAuthShortnameLike() {
		return authShortnameLike;
	}

	/**
	 * 属性 authShortnameLike setter方法
	 * @param authShortnameLike 设置属性authShortnameLike的值
	 * @author 王通<br/>
	 */
	public void setAuthShortnameLike(String authShortnameLike) {
		this.authShortnameLike = authShortnameLike;
	}

	/**
	 * 属性 authStatusNot getter方法
	 * @return 属性authStatusNot
	 * @author 王通<br/>
	 */
	public Integer getAuthStatusNot() {
		return authStatusNot;
	}

	/**
	 * 属性 statusName setter方法
	 * @param statusName 设置属性statusName的值
	 * @author 王通<br/>
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * 属性 typeName setter方法
	 * @param typeName 设置属性typeName的值
	 * @author 王通<br/>
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 属性 levelName setter方法
	 * @param levelName 设置属性levelName的值
	 * @author 王通<br/>
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * 属性 types setter方法
	 * @param types 设置属性types的值
	 * @author 王通<br/>
	 */
	public void setTypes(List<Integer> types) {
		this.types = types;
	}

	/**
	 * 属性 levels setter方法
	 * @param levels 设置属性levels的值
	 * @author 王通<br/>
	 */
	public void setLevels(List<Integer> levels) {
		this.levels = levels;
	}
	
	
}