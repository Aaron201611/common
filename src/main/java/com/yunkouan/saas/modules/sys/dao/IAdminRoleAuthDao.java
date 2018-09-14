package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysAdminRoleAuth;
import com.yunkouan.saas.modules.sys.entity.SysAuth;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * 【角色授权（角色-权限中间表）】数据层接口
 */
public interface IAdminRoleAuthDao extends Mapper<SysAdminRoleAuth> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysAdminRoleAuth> selectByExample(Object example);

	/**
	 * 角色授权列表数据查询
	 */
	public List<SysAuth> list(SysAdminRoleAuth entity);
}