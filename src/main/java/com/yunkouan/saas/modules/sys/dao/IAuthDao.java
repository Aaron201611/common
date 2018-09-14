package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.vo.AuthVo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * 【权限】数据层接口
 * @author tphe06
 */
public interface IAuthDao extends Mapper<SysAuth> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysAuth> selectByExample(Object example);

	/**
	 * 查询授权的权限【不限级别】
	 */
	public List<SysAuth> listAll(AuthVo vo);
	/**
	 * 查询授权的权限【限企业普通帐号】
	 */
	public List<SysAuth> query(AuthVo vo);
	/**
	 * 查询授权的权限【限平台/企业管理员帐号】
	 */
	public List<SysAuth> query4admin(AuthVo vo);
}