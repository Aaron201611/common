package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysStrategyType;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/** 
* @Description: 策略分类dao
* @author tphe06
* @date 2018年8月24日 上午9:53:11  
*/
public interface IStrategyTypeDao extends Mapper<SysStrategyType> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysStrategyType> selectByExample(Object example);

}