package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysAdminRole;
import com.yunkouan.saas.modules.sys.entity.SysAdministratorRole;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * 【管理员帐号授权（管理员帐号-角色中间表）】数据层接口
 * @author tphe06
 */
public interface IAdministratorRoleDao extends Mapper<SysAdministratorRole> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysAdministratorRole> selectByExample(Object example);

	/**
	 * 帐号授权角色列表数据查询
	 */
	public List<SysAdminRole> list(SysAdministratorRole entity);
}