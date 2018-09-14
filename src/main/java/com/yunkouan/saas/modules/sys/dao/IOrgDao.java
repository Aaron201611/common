package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.SysOrg;
import com.yunkouan.saas.modules.sys.vo.OrgVo;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * 【企业】数据层接口
 * @author tphe06
 */
public interface IOrgDao extends Mapper<SysOrg> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<SysOrg> selectByExample(Object example);

	/**
	* @Description: 根据企业名称模糊查询企业id
	* @param name 企业名称，不得随意更改，得与XML配置文件名称一致
	* @return List<String>
	* @throws
	*/
	public List<String> list(OrgVo orgVo);
}