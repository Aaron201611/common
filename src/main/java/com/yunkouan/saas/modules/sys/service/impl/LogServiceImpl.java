package com.yunkouan.saas.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.yunkouan.saas.modules.sys.dao.ILogDao;
import com.yunkouan.saas.modules.sys.entity.SysLog;
import com.yunkouan.saas.modules.sys.service.ILogService;
import com.yunkouan.saas.modules.sys.vo.LogVo;

/**
* @Description: 日志服务实现类
* @author tphe06
* @date 2017年3月15日
*/
@Service
@Transactional(readOnly=true)
public class LogServiceImpl implements ILogService {
	/**@Fields 日志服务接口 */
	@Autowired
	private ILogDao dao;

	/**
	* @Description: 日志列表数据查询
	* @param vo
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel list(LogVo vo) throws DaoException, ServiceException {
		Page<SysLog> page = PageHelper.startPage(vo.getCurrentPage()+1, vo.getPageSize());
    	List<SysLog> list = dao.selectByExample(vo.getExample());
    	List<LogVo> r = new ArrayList<LogVo>();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		r.add(new LogVo(list.get(i)));
    	}
    	return new ResultModel().setPage(page).setList(r);
    }

	/**
	* @Description: 查看日志详情
	* @param id
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel view(String id) throws DaoException, ServiceException {
    	/**查询用户详情**/
    	SysLog entity =  dao.selectByPrimaryKey(id);
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	LogVo vo = new LogVo(entity);
        return new ResultModel().setObj(vo);
    }

	/**
	* @Description: 添加日志接口
	* @param entity 日志实体类
	* @return
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int add(SysLog entity) {
    	/**添加日志信息**/
    	if(entity == null) return 0;
    	String id = IdUtil.getUUID();
    	entity.setLogId(id);
    	int r = dao.insertSelective(entity);
        return r;
    }
}