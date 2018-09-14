package com.yunkouan.saas.modules.sys.service;

import java.util.List;

import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.DaoException;
import com.yunkouan.exception.ServiceException;
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.entity.SysFinger;
import com.yunkouan.saas.modules.sys.entity.SysUser;
import com.yunkouan.saas.modules.sys.vo.UserVo;

/**
 * 【用户/实体人员】服务接口
 * @author tphe06 2017年2月14日
 */
public interface IUserService {
    /**
     * 用户列表数据查询
     * @param vo 
     * @param page 
     * @return
     */
    public ResultModel list(UserVo vo) throws DaoException, ServiceException;
    /**
    * @Description: 根据用户名称模糊查询用户id列表
    * @param name
    * @return
    */
    public List<String> list(String name);
    /**
     * 查询所有用户信息
     * @return
     */
    public List<SysUser> listAll();

    /**
     * 查询用户详情
     * @param id 
     * @return
     * @throws ServiceException 
     */
    public ResultModel view(String id) throws DaoException, ServiceException;
    /**
     * 查询用户详情
     * @param id 用户id（UUID）
     * @return
     * @throws ServiceException 
     */
    public SysUser get(String id);
    public SysUser query(SysUser user);

    /**
     * 添加【用户和指纹】
     * @param user 
     * @return
     * @throws ServiceException 
     * @throws DaoException 
     */
    public ResultModel add(UserVo user, Principal p) throws DaoException, ServiceException;

    /**
     * 修改【用户和指纹】
     * @param user 
     * @return
     * @throws ServiceException 
     * @throws DaoException 
     */
    public ResultModel update(UserVo user, Principal p) throws DaoException, ServiceException;
    /**
    * @Description: 修改用户信息
    * @param user
    * @return
    */
    public void update(SysUser user) throws DaoException, ServiceException;
    /**
     * @Description: 修改用户状态
     * @param user
     * @return
     */
     public void updateStatus(String userId, Integer userStatus) throws DaoException, ServiceException;
    /**
     * 生效用户
     * @param id 
     * @return
     * @throws ServiceException 
     * @throws DaoException 
     */
    public ResultModel enable(String id) throws DaoException, ServiceException;

    /**
     * 失效用户
     * @param id 
     * @return
     * @throws DaoException 
     * @throws ServiceException 
     */
    public ResultModel disable(String id) throws DaoException, ServiceException;

    /**
     * 取消用户
     * @param id 
     * @return
     * @throws DaoException 
     * @throws ServiceException 
     */
    public ResultModel cancel(String id) throws DaoException, ServiceException;

    /**
    * @Description: 根据主键删除用户信息
    * @param id
    * @return
    * @throws DaoException
    * @throws ServiceException
    */
    public ResultModel delete(String id) throws DaoException, ServiceException;

    /**
    * @Description: 指纹采集
    * @param entity.userId 用户id
    * @param fingers 指纹列表
    * @return
    * @throws DaoException
    * @throws ServiceException
    */
    public ResultModel fingerCollect(UserVo vo) throws DaoException, ServiceException;

    /**
    * @Description: 指纹认证【在数据库中比对指纹，返回最匹配的用户和帐号信息】
    * @param fg 指纹模板
    * @return
    * @throws DaoException
    * @throws ServiceException
    */
    public SysFinger fingerVerify(SysFinger fg) throws DaoException, ServiceException;
	/**
	 * @param vo
	 * @return
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月21日 上午11:03:13<br/>
	 * @author 王通<br/>
	 */
	public ResultModel saveAndEnable(UserVo vo) throws Exception;
	/**
	 * @param vo
	 * @param p
	 * @param isEnable
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年9月5日 上午9:13:47<br/>
	 * @author 王通<br/>
	 */
	public ResultModel update(UserVo vo, Principal p, boolean isEnable) throws Exception;
	/**
	 * @param vo
	 * @param p
	 * @param isEnable
	 * @return
	 * @throws ServiceException
	 * @throws DaoException
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年9月5日 上午9:14:22<br/>
	 * @author 王通<br/>
	 */
	public ResultModel add(UserVo vo, Principal p, boolean isEnable) throws ServiceException, DaoException;
}