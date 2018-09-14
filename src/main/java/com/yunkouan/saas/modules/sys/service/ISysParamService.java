/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月14日下午2:29:20<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.yunkouan.saas.modules.sys.vo.CountryVO;
import com.yunkouan.saas.modules.sys.vo.SysParamVO;

/**
 * 参数业务接口<br/><br/>
 * @version 2017年3月14日下午2:29:20<br/>
 * @author andy wang<br/>
 */
public interface ISysParamService {

	public SysParamVO searchCache(SysParamVO vo);

	/**
	 * 更新参数
	 * @param param_paramVO 参数信息
	 * @throws Exception
	 * @version 2017年3月14日下午2:29:20<br/>
	 * @author andy wang<br/>
	 */
	public void updateSysParam(SysParamVO param_paramVO, String loginUserId) throws Exception;

	/**
	 * 失效参数
	 * @param param_listParamId 参数id集合
	 * @throws Exception
	 * @version 2017年3月14日下午2:29:20<br/>
	 * @author andy wang<br/>
	 */
	public void inactiveSysParam(List<String> param_listParamId, String loginUserId) throws Exception;

	/**
	 * 生效参数
	 * @param param_listParamId 参数id集合
	 * @throws Exception
	 * @version 2017年3月14日下午2:29:20<br/>
	 * @author andy wang<br/>
	 */
	public void activeSysParam(List<String> param_listParamId, String loginUserId) throws Exception;

	/**
	 * 根据参数id，查询参数信息
	 * @param param_paramId 参数id
	 * @return 参数信息
	 * @throws Exception
	 * @version 2017年3月14日下午2:29:20<br/>
	 * @author andy wang<br/>
	 */
	public SysParamVO viewSysParam(String param_paramId) throws Exception;

	/**
	 * 保存参数
	 * @param param_paramVO 要保存的参数
	 * @return 保存后的参数（包含id）
	 * @throws Exception
	 * @version 2017年3月14日下午2:29:20<br/>
	 * @author andy wang<br/>
	 */
	public SysParamVO insertSysParam(SysParamVO param_paramVO, String loginUserId, Integer id2) throws Exception;

	/**
	 * 根据条件，分页查询参数
	 * @param SysParamVO 查询条件
	 * @return 查询结果
	 * @throws Exception
	 * @version 2017年3月14日下午2:29:20<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings({ "rawtypes" })
	public Page listSysParam(SysParamVO SysParamVO) throws Exception;

	/**
	 * 简单包装国家、省、市、区县
	 * @return
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月9日 下午1:47:23<br/>
	 * @author 王通<br/>
	 */
	public List<CountryVO> listCity() throws Exception;

	/** 
	* @Title: getValue 
	* @Description: 根据参数名称和KEY查询参数值
	* @auth tphe06
	* @time 2018 2018年8月6日 上午10:01:23
	* @param name param_name
	* @param key param_key
	* @return param_value
	* String
	*/
	public String getValue(String name, Object key);

	/** 
	* @Title: getKey 
	* @Description: 根据参数名称查询唯一的key
	* @auth tphe06
	* @time 2018 2018年8月6日 上午10:05:31
	* @param name param_name
	* @return param_key
	* String
	*/
	public String getKey(String name);
}