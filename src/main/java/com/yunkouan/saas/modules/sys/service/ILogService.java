package com.yunkouan.saas.modules.sys.service;

import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.DaoException;
import com.yunkouan.exception.ServiceException;
import com.yunkouan.saas.modules.sys.entity.SysLog;
import com.yunkouan.saas.modules.sys.vo.LogVo;

/**
* @Description: 日志服务接口
* @author tphe06
* @date 2017年3月15日
*/
public interface ILogService {
    /**
     * 系统日志列表数据查询
     * @param vo 
     * @param page 
     * @param r
     */
    public ResultModel list(LogVo vo) throws DaoException, ServiceException;

    /**
     * 查询日志详情
     * @param id 
     * @return
     */
    public ResultModel view(String id) throws DaoException, ServiceException;

    /**
    * @Description: 添加日志
    * @param entity
    * @return int 大于0表示成功
    * @throws
    */
    public int add(SysLog entity);
}