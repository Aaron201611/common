package com.yunkouan.saas.modules.sys.service;

import java.util.List;

import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.DaoException;
import com.yunkouan.exception.ServiceException;
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.entity.SysAdminRole;
import com.yunkouan.saas.modules.sys.vo.AdminRoleVo;
import com.yunkouan.saas.modules.sys.vo.AuthVo;

/**
 * 【管理员角色】服务接口
 * @author tphe06 2017年2月14日
 */
public interface IAdminRoleService {
    /**
     * 角色列表数据查询
     * @param role 
     * @param page 
     * @return
     */
    public ResultModel list(AdminRoleVo role) throws DaoException, ServiceException;

    /**
     * 查询角色详情
     * @param id 
     * @return
     * @throws ServiceException 
     */
    public ResultModel view(String id) throws DaoException, ServiceException;

    /**
     * 添加【角色和权限】
     * @param vo 
     * @return
     * @throws DaoException 
     * @throws ServiceException 
     */
    public ResultModel add(AdminRoleVo vo, Principal p) throws DaoException, ServiceException;
    public SysAdminRole query(SysAdminRole entity) throws DaoException, ServiceException;
    public List<SysAdminRole> queryList(SysAdminRole entity) throws DaoException, ServiceException;

    /**
     * 修改【角色和权限】
     * @param vo 
     * @return
     * @throws ServiceException 
     */
    public ResultModel update(AdminRoleVo vo, Principal p) throws DaoException, ServiceException;
    /**
    * @Description: 仅修改角色
    * @param entity
    * @throws DaoException
    * @throws ServiceException
    */
    public void update(SysAdminRole entity, Principal p) throws DaoException, ServiceException;

    /**
     * 生效角色
     * @param id 
     * @return
     */
    public ResultModel enable(String id) throws DaoException, ServiceException;

    /**
     * 角色失效
     * @param id 
     * @return
     */
    public ResultModel disable(String id) throws DaoException, ServiceException;

    /**
     * 失效角色
     * @param id 
     * @return
     */
    public ResultModel cancel(String id) throws DaoException, ServiceException;

    /**
     * 删除角色
     * @param id 
     * @return
     */
    public ResultModel delete(String id) throws DaoException, ServiceException;

    /**
    * @Description: 角色授权
    * @param id 角色id
    * @param list 权限列表
    * @return
    * @throws DaoException
    * @throws ServiceException
    */
    public ResultModel auth(String id, List<AuthVo> list, Principal p) throws DaoException, ServiceException;
}