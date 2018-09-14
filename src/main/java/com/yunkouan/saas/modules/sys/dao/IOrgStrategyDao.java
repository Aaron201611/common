package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysOrgStrategy;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

public interface IOrgStrategyDao extends Mapper<SysOrgStrategy> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysOrgStrategy> selectByExample(Object example);

}