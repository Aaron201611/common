package com.yunkouan.saas.modules.sys.service;

import java.util.List;

import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.DaoException;
import com.yunkouan.exception.ServiceException;
import com.yunkouan.saas.modules.sys.entity.SysLog;
import com.yunkouan.saas.modules.sys.vo.LogVo;
import com.yunkouan.saas.modules.sys.entity.SysEmergencyPerson;
import com.yunkouan.saas.modules.sys.entity.SysLoginLog;
import com.yunkouan.saas.modules.sys.entity.SysParam;
import com.yunkouan.saas.modules.sys.vo.LoginLogVo;

/**
* @Description: 【系统管理模块】服务接口
* @author tphe06
* @date 2017年3月16日
*/
public interface ISystemService {
	/**
	* @Description: 查询所有系统参数
	* @return
	* @throws DaoException
	* @throws ServiceException
	*/
	public ResultModel paramList() throws DaoException, ServiceException;
	/**
	* @Description: 添加/修改系统参数
	* @param params 系统参数列表（必填参数：paramId/paramValue）
	* @return
	* @throws DaoException
	* @throws ServiceException
	*/
	public ResultModel saveOrUpdateParamer(List<SysParam> params) throws DaoException, ServiceException;
	/**
	* @Description: 查询系统参数值
	* @param paramGroup 参数类型
	* @param paramKey 参数关键字
	* @return
	* @throws DaoException
	* @throws ServiceException
	*/
	public SysParam get(String paramGroup, String paramKey) throws DaoException, ServiceException;
	/**
	* @Description: 根据属性查询唯一系统参数
	* @param p 系统参数
	* @return
	* @throws DaoException
	* @throws ServiceException
	*/
	public SysParam get(SysParam p) throws DaoException, ServiceException;

	/**
	* @Description: 查询所有紧急联系人
	* @return
	* @throws DaoException
	* @throws ServiceException
	*/
	public ResultModel emergencyList() throws DaoException, ServiceException;
	/**
	* @Description: 添加/修改紧急联系人【先删除如何新增】
	* @param persons 紧急联系人列表
	* @return
	* @throws DaoException
	* @throws ServiceException
	*/
	public ResultModel saveOrUpdateEmergency(List<SysEmergencyPerson> persons) throws DaoException, ServiceException;

	/**
	* @Description: 保存操作日志
	* @throws DaoException
	* @throws ServiceException
	*/
	public ResultModel saveLog(SysLog log) throws DaoException, ServiceException;
	/**
	* @Description: 操作日志列表数据查询
	* @return
	* @throws DaoException
	* @throws ServiceException
	*/
	public ResultModel logList(LogVo vo) throws DaoException, ServiceException;

	/**
	* @Description: 保存登录日志
	* @throws DaoException
	* @throws ServiceException
	*/
	public ResultModel saveLoginLog(SysLoginLog log) throws DaoException, ServiceException;
	/**
	* @Description: 登录日志列表数据查询
	* @return
	* @throws DaoException
	* @throws ServiceException
	*/
	public ResultModel loginLogList(LoginLogVo vo) throws DaoException, ServiceException;
}