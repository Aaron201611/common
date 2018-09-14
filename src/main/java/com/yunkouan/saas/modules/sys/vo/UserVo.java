package com.yunkouan.saas.modules.sys.vo;

import java.util.List;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;

import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.modules.sys.entity.SysFinger;
import com.yunkouan.saas.modules.sys.entity.SysUser;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 用户数据传输对象
 * @author tphe06 2017年2月13日
 */
public class UserVo extends BaseVO {
	private static final long serialVersionUID = 6028562564078022577L;

	/**@Fields 用户实体类 */
	@Valid
	private SysUser entity;
	/**@Fields 排序字段 */
	private String orderBy = "user_id2 desc";
	/**@Fields 用户编号模糊查询字段 */
	private String userNoLike;
	/**@Fields 用户姓名模糊查询字段 */
	private String userNameLike;
	/**@Fields 电话模糊查询字段 */
	private String phoneLike;
	/**@Fields 邮箱模糊查询字段 */
	private String emailLike;
	private Integer userStatusNot;


	/**@Fields 班组名称 */
	private String groupName;
	/**@Fields 工作区域名称 */
	private String areaName;
	/**@Fields 用户指纹 */
	private List<SysFinger> fingers;

	public UserVo() {}
	public UserVo(SysUser entity) {
		this.entity = entity;
	}

	@Override
	public Example getExample() {
		Example example = new Example(SysUser.class);
		example.setOrderByClause(orderBy);
		if(entity == null) return example;
		Criteria c = example.createCriteria();
		if (StringUtils.isNoneBlank(entity.getOrgId())) {
			c.andEqualTo("orgId", entity.getOrgId());
		}
		if(StringUtils.isNoneBlank(userNoLike)) {
			c.andLike("userNo", "%"+userNoLike+"%");
		}
		if(StringUtils.isNoneBlank(userNameLike)) {
			c.andLike("userName", "%"+userNameLike+"%");
		}
		if(StringUtils.isNoneBlank(phoneLike)) {
			c.andLike("phone", "%"+phoneLike+"%");
		}
		if(StringUtils.isNoneBlank(emailLike)) {
			c.andLike("email", "%"+emailLike+"%");
		}
		if(StringUtils.isNoneBlank(entity.getUserNo())) {
			c.andEqualTo("userNo", entity.getUserNo());
		}
		if(StringUtils.isNoneBlank(entity.getUserName())) {
			c.andEqualTo("userName", entity.getUserName());
		}
		if(StringUtils.isNoneBlank(entity.getPhone())) {
			c.andEqualTo("phone", entity.getPhone());
		}
		if(StringUtils.isNoneBlank(entity.getEmail())) {
			c.andEqualTo("email", entity.getEmail());
		}
		if(entity.getUserStatus() != null) {
			c.andEqualTo("userStatus", entity.getUserStatus());
		}
		if(entity.getIsEmployee() != null) {
			c.andEqualTo("isEmployee", entity.getIsEmployee());
		}
		if(userStatusNot != null) {
			c.andNotEqualTo("userStatus", userStatusNot);
		}
		return example;
	}

	public SysUser getEntity() {
		return entity;
	}
	public List<SysFinger> getFingers() {
		return fingers;
	}
	public UserVo setFingers(List<SysFinger> fingers) {
		this.fingers = fingers;
		return this;
	}
	public String getGroupName() {
		return groupName;
	}
	public String getAreaName() {
		return areaName;
	}
	public UserVo setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}
	public UserVo setAreaName(String areaName) {
		this.areaName = areaName;
		return this;
	}
	public UserVo setEntity(SysUser entity) {
		this.entity = entity;
		return this;
	}
	public UserVo setUserNoLike(String userNoLike) {
		this.userNoLike = userNoLike;
		return this;
	}
	public UserVo setUserNameLike(String userNameLike) {
		this.userNameLike = userNameLike;
		return this;
	}
	public UserVo setPhoneLike(String phoneLike) {
		this.phoneLike = phoneLike;
		return this;
	}
	public UserVo setEmailLike(String emailLike) {
		this.emailLike = emailLike;
		return this;
	}
	public UserVo setUserStatusNot(Integer userStatusNot) {
		this.userStatusNot = userStatusNot;
		return this;
	}
}