package com.yunkouan.saas.modules.sys.service;

import java.util.List;

import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.DaoException;
import com.yunkouan.exception.ServiceException;
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.vo.AuthVo;

/**
 * 【系统权限】服务接口：
 * 1 WMS系统只有平台管理员和平台普通用户权限
 * 2 权限管理平台才有完整3级权限
 * 3 WMS系统只能授权平台普通用户权限，无法添加修改权限
 * @author tphe06 2017年2月14日
 */
public interface IAuthService {
	public static final String ROOT = "0";
    /**
     * 权限列表数据查询【前端接口，不限权限级别】
     * @param auth 
     * @param page 
     * @return
     */
    public ResultModel list(AuthVo auth) throws DaoException, ServiceException;
    /**
     * 按照条件查询所有权限数据【不限权限级别】
     * @param entity 
     * @return
     */
    public List<SysAuth> query(SysAuth entity);
    /**
    * @Description: 按照条件查询所有权限数据【不限权限级别】
    * @param vo
    * @return
    */
    public List<SysAuth> listAll(AuthVo vo);
    public SysAuth get(SysAuth entity);
    /**
     * 按照条件查询所有权限数据【限企业普通帐号】
     * @param vo 
     * @return
     */
    public List<SysAuth> query(AuthVo vo) throws DaoException, ServiceException;
    /**
     * 按照条件查询所有权限数据【限平台/企业管理员帐号】
     * @param vo 
     * @return
     */
    public List<SysAuth> query4admin(AuthVo vo) throws DaoException, ServiceException;
    /**
    * @Description: 按照条件查询所有权限数据【限企业普通帐号，供前端使用】
    * @param vo
    * @return
    * @throws DaoException
    * @throws ServiceException
    */
//    public ResultModel tree(AuthVo vo) throws DaoException, ServiceException;
    /**
    * @Description: 按照条件查询所有权限数据【限平台/企业管理员帐号，供前端使用】
    * @param vo
    * @return
    * @throws DaoException
    * @throws ServiceException
    */
//    public ResultModel tree4admin(AuthVo vo) throws DaoException, ServiceException;

    /**
     * 查询权限详情
     * @param id 
     * @return
     */
    public ResultModel view(String id) throws DaoException, ServiceException;

    /**
     * 添加权限
     * @param auth 
     * @return
     */
    public ResultModel add(AuthVo auth, Principal p) throws DaoException, ServiceException;

    /**
     * 修改权限
     * @param auth 
     * @return
     */
    public ResultModel update(AuthVo auth, Principal p) throws DaoException, ServiceException;

    /**
     * 生效权限
     * @param id 
     * @return
     */
    public ResultModel enable(String id) throws DaoException, ServiceException;

    /**
     * 失效权限
     * @param id 
     * @return
     */
    public ResultModel disable(String id) throws DaoException, ServiceException;

    /**
     * 取消权限
     * @param id 
     * @return
     */
    public ResultModel cancel(String id) throws DaoException, ServiceException;
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月21日 上午11:06:12<br/>
	 * @author 王通<br/>
	 */
	public ResultModel saveAndEnable(AuthVo vo) throws Exception;
}