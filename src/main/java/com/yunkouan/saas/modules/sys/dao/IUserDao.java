package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysUser;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * 用户数据层接口
 */
public interface IUserDao extends Mapper<SysUser> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysUser> selectByExample(Object example);

	/**
	* @Description: 根据用户姓名模糊查询所有id
	* @param name 用户姓名，不得随意更改，得与XML配置文件中名称一致
	* @return List<String>
	* @throws
	*/
	public List<String> list(String name);
}