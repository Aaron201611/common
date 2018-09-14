package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.entity.SysOrgAuth;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * 【企业授权（企业-权限中间表）】数据层接口
 * @author tphe06
 */
public interface IOrgAuthDao extends Mapper<SysOrgAuth> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysOrgAuth> selectByExample(Object example);

	/**
	 * 企业授权列表数据查询
	 */
	public List<SysAuth> list(SysOrgAuth entity);
}