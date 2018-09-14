/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月14日下午2:56:11<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.yunkouan.saas.modules.sys.entity.SysSystemParam;
import com.yunkouan.saas.modules.sys.vo.SysParamVO;

/**
 * BBB外调接口<br/><br/>
 * @version 2017年3月14日下午2:56:11<br/>
 * @author andy wang<br/>
 */
public interface ISysParamExtlService {

	/**
	 * 获取页面显示参数
	 * @param listCacheName 参数名
	 * @return 参数集合
	 * @throws Exception
	 * @version 2017年3月22日 下午2:39:54<br/>
	 * @author andy wang<br/>
	 */
	public Map<String,List<Map<String,Object>>> showParam ( List<String> listCacheName ) throws Exception;
	
	/**
	 * 根据条件，分页查询BBB
	 * @param sysParamVO 查询条件
	 * @return 查询结果
	 * @throws Exception
	 * @version 2017年3月14日下午2:56:11<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings("rawtypes")
	public Page listSysParamByPage(SysParamVO sysParamVO) throws Exception;


	/**
	 * 保存BBB
	 * @param sysParam 要保存的BBB对象
	 * @return 保存后的BBB对象
	 * @throws Exception
	 * @version 2017年3月14日下午2:56:11<br/>
	 * @author andy wang<br/>
	 */
	public SysSystemParam insertSysParam(SysSystemParam sysParam, String loginUserId, Integer id2) throws Exception;

	/**
	 * 更新BBB
	 * @param areaVO 更新的BBB内容
	 * @return 更新的数量
	 * @throws Exception
	 * @version 2017年3月14日下午2:56:11<br/>
	 * @author andy wang<br/>
	 */
	public int updateSysParam(SysParamVO areaVO, String loginUserId) throws Exception;

	/**
	 * 根据BBBid，更新BBB状态
	 * @param areaId BBBid
	 * @param status BBB状态
	 * @return 更新的数量
	 * @throws Exception
	 * @version 2017年3月14日下午2:56:11<br/>
	 * @author andy wang<br/>
	 */
	public int updateSysParamStatusById(String areaId, Integer status, String loginUserId) throws Exception;

	/**
	 * 根据BBBid，查询BBB信息
	 * @param areaId BBBid
	 * @return BBB信息
	 * @throws Exception
	 * @version 2017年3月14日下午2:56:11<br/>
	 * @author andy wang<br/>
	 */
	public SysSystemParam findSysParamById(String areaId) throws Exception;

	/**
	 * 根据条件，查询BBB
	 * @param sysParamVO 查询条件
	 * @return BBB对象
	 * @throws Exception
	 * @version 2017年3月14日下午2:56:11<br/>
	 * @author andy wang<br/>
	 */
	public List<SysSystemParam> listSysParamByExample(SysParamVO sysParamVO) throws Exception;

	/** 
	* @Title: get 
	* @Description: 根据参数名称查询key-value
	* @auth tphe06
	* @time 2018 2018年8月6日 上午11:01:01
	* @param name param_name
	* @return <param_key,param_value>
	* Map<String,String>
	*/
	public Map<String, String> get(String name);
}