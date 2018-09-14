package com.yunkouan.saas.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.DaoException;
import com.yunkouan.exception.ServiceException;
import com.yunkouan.saas.common.constant.ErrorCode;
import com.yunkouan.saas.common.util.IdUtil;
import com.yunkouan.saas.modules.sys.dao.IEmergencyPersonDao;
import com.yunkouan.saas.modules.sys.dao.ILogDao;
import com.yunkouan.saas.modules.sys.dao.ILoginLogDao;
import com.yunkouan.saas.modules.sys.dao.IParamDao;
import com.yunkouan.saas.modules.sys.entity.SysEmergencyPerson;
import com.yunkouan.saas.modules.sys.entity.SysLog;
import com.yunkouan.saas.modules.sys.entity.SysLoginLog;
import com.yunkouan.saas.modules.sys.entity.SysParam;
import com.yunkouan.saas.modules.sys.entity.SysUser;
import com.yunkouan.saas.modules.sys.service.ISystemService;
import com.yunkouan.saas.modules.sys.vo.LogVo;
import com.yunkouan.saas.modules.sys.vo.LoginLogVo;

/**
* @Description: 【系统管理模块】服务实现
* @author tphe06
* @date 2017年3月21日
*/
@Service
@Transactional(readOnly=false, rollbackFor=Exception.class)
public class SystemService implements ISystemService {
	/**@Fields 紧急联系人数据层接口 */
	@Autowired
	private IEmergencyPersonDao emergencyDao;
	/**@Fields 操作日志数据层接口 */
	@Autowired
	private ILogDao logDao;
	/**@Fields 登录日志数据层接口 */
	@Autowired
	private ILoginLogDao loginLogDao;
	/**@Fields 系统参数数据层接口 */
	@Autowired
	private IParamDao paramDao;

	/**
	* @Description: 添加/修改系统参数
	* @param params
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel saveOrUpdateParamer(List<SysParam> params) throws DaoException, ServiceException {
		ResultModel m = new ResultModel();
		if(params != null) for(int i=0; i<params.size(); ++i) {
			SysParam p = params.get(i);
			int r = paramDao.updateByPrimaryKeySelective(p);
			if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
		}
		return m;
	}

	/**
	* @Description: 添加/修改紧急联系人
	* @param persons
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel saveOrUpdateEmergency(List<SysEmergencyPerson> persons) throws DaoException, ServiceException {
		ResultModel m = new ResultModel();
//		emergencyDao.delete(new SysEmergencyPerson());
		if(persons != null) for(int i=0; i<persons.size(); ++i) {
			SysEmergencyPerson p = persons.get(i);
			if(StringUtils.isEmpty(p.getEmergencyId())) {
				p.setEmergencyId(IdUtil.getUUID());
				int r = emergencyDao.insert(p);
				if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
			} else {
				int r = emergencyDao.updateByPrimaryKeySelective(p);
				if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
			}
		}
		return m;
	}

	/**
	* @Description: 保存操作日志
	* @param log
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel saveLog(SysLog log) throws DaoException, ServiceException {
		log.setLogId(IdUtil.getUUID());
		int r = logDao.insert(log);
		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
		return new ResultModel();
	}

	/**
	* @Description: 操作日志列表数据查询
	* @param vo
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel logList(LogVo vo) throws DaoException, ServiceException {
		Page<SysUser> page = PageHelper.startPage(vo.getCurrentPage()+1, vo.getPageSize());
		List<SysLog> list = logDao.selectByExample(vo.getExample());
    	List<LogVo> r = new ArrayList<LogVo>();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		r.add(new LogVo(list.get(i)));
    	}
		return new ResultModel().setPage(page).setList(r);
	}

	/**
	* @Description: 保存登录日志
	* @param log
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel saveLoginLog(SysLoginLog log) throws DaoException, ServiceException {
		log.setLogId(IdUtil.getUUID());
		int r = loginLogDao.insert(log);
		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
		return new ResultModel();
	}

	/**
	* @Description: 登录日志列表数据查询
	* @param vo
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel loginLogList(LoginLogVo vo) throws DaoException, ServiceException {
		Page<SysUser> page = PageHelper.startPage(vo.getCurrentPage()+1, vo.getPageSize());
		List<SysLoginLog> list = loginLogDao.selectByExample(vo.getExample());
    	List<LoginLogVo> r = new ArrayList<LoginLogVo>();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		r.add(new LoginLogVo(list.get(i)));
    	}
		return new ResultModel().setPage(page).setList(r);
	}

	/**
	* @Description: 查询系统参数值
	* @param paramGroup
	* @param paramKey
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public SysParam get(String paramGroup, String paramKey) throws DaoException, ServiceException {
		if(StringUtils.isBlank(paramGroup) || StringUtils.isBlank(paramKey)) throw new ServiceException(ErrorCode.PARAM_EMPTY);
		//在有缓存的情况下，可以提高性能
		List<SysParam> list = paramDao.selectAll();
		if(list != null) for(int i=0; i<list.size(); ++i) {
			SysParam p = list.get(i);
			if(paramGroup.equals(p.getParamGroup())
				&& paramKey.equals(p.getParamKey())) return p;
		}
		throw new ServiceException(ErrorCode.DATA_EMPTY);
	}

	/**
	* @Description: 查询所有系统参数
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel paramList() throws DaoException, ServiceException {
		List<SysParam> list = paramDao.selectAll();
		return new ResultModel().setList(list);
	}

	/**
	* @Description: 查询所有紧急联系人
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel emergencyList() throws DaoException, ServiceException {
		List<SysEmergencyPerson> list = emergencyDao.selectAll();
		return new ResultModel().setList(list);
	}

	/**
	* @Description: TODO
	* @param p
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public SysParam get(SysParam p) throws DaoException, ServiceException {
		return paramDao.selectOne(p);
	}
}