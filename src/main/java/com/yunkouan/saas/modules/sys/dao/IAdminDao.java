package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysAdmin;
import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.vo.AdminAuthVo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * 【管理员帐号】数据层接口
 * @author tphe06
 */
public interface IAdminDao extends Mapper<SysAdmin> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysAdmin> selectByExample(Object example);

	/**
	 * 查询授权的权限【限1级权限】
	 */
	public List<SysAuth> query(SysAdmin account);
	/**
	 * 查询授权的权限【不限级别权限】
	 */
	public List<SysAuth> query4all(AdminAuthVo vo);
}