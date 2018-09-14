package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysAdminRole;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * 【管理员角色】数据层接口
 * @author tphe06
 */
public interface IAdminRoleDao extends Mapper<SysAdminRole> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysAdminRole> selectByExample(Object example);

}