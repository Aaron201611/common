package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysEmergencyPerson;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
* @Description: 【紧急联系人】数据层接口
* @author tphe06
* @date 2017年3月20日
*/
public interface IEmergencyPersonDao extends Mapper<SysEmergencyPerson> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysEmergencyPerson> selectByExample(Object example);

}