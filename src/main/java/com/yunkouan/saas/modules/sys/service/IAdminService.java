package com.yunkouan.saas.modules.sys.service;

import java.util.List;

import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.DaoException;
import com.yunkouan.exception.ServiceException;
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.entity.SysAdmin;
import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.vo.AdminAuthVo;
import com.yunkouan.saas.modules.sys.vo.AdminVo;

/**
 * 【管理员帐号】服务接口
 * @author tphe06 2017年2月14日
 */
public interface IAdminService {
    /**
     * 帐号列表数据查询
     * @param vo 
     * @param page 
     * @return
     */
    public ResultModel list(AdminVo vo) throws DaoException, ServiceException;

    /**
     * 查询帐号详情
     * @param id 
     * @return
     */
    public ResultModel view(String id) throws DaoException, ServiceException;
    /**
     * 查询帐号详情
     * @param id 
     * @return
     */
    public SysAdmin get(String id);
    /**
     * 根据帐户编号和组织id查询帐户和用户信息
     * @param vo
     * @return
     */
    public AdminVo query(SysAdmin vo);
    /**
     * 根据帐号id查询该帐号的所有授权信息【限1级权限】
     */
    public List<SysAuth> query(String id);
	/**
	 * 查询授权的权限【不限级别权限】
	 */
	public List<SysAuth> query4all(AdminAuthVo vo);
    /**
     * 根据帐号id查询帐号相关完整信息
     */
    public AdminVo load(String id) throws DaoException, ServiceException;
    public AdminVo queryRole(SysAdmin entity) throws DaoException, ServiceException;

    /**
     * 添加【帐号和角色】
     * @param vo 
     * @return
     */
    public ResultModel add(AdminVo vo, Principal p) throws DaoException, ServiceException;
    /**
     * 添加【用户，帐号和角色】
     * @param vo 
     * @return
     */
    public ResultModel addAdminAndUser(AdminVo vo, Principal p) throws DaoException, ServiceException;

    /**
     * 修改【帐号和角色】信息
     * @param vo 
     * @return
     */
    public ResultModel update(AdminVo vo, Principal p) throws DaoException, ServiceException;
    /**
    * @Description: 仅修改帐号信息
    * @param entity
    * @throws DaoException
    * @throws ServiceException
    */
    public void update(SysAdmin entity, Principal p) throws DaoException, ServiceException;
    /**
     * 修改帐号登录密码
     * @param vo
     * @return
     */
    public void updatePwd(AdminVo vo, Principal p) throws DaoException, ServiceException;
    /**
    * @Description: 帐号密码重置【限用户-帐号一对一情况】
    * @param userId 用户id
    * @param password 帐号密码（明文，存储时用sha-1加密算法）
    * @throws DaoException
    * @throws ServiceException
    */
    public void resetPwd(String userId, String password) throws DaoException, ServiceException;
    /**
    * @Description: 帐号授权
    * @param vo（入参：entity和list）
    * @return
    * @throws DaoException
    * @throws ServiceException
    */
    public ResultModel auth(AdminVo vo) throws DaoException, ServiceException;

    /**
    * @Description: 修改登录帐号密码【限用户-帐号一对一情况】
    * @param userId 用户id
    * @param password 登录密码（密文）
    * @throws DaoException
    * @throws ServiceException
    */
    public void updatePwd(String userId, String password) throws DaoException, ServiceException;

    /**
    * @Description: 删除帐号信息
    * @param id
    * @throws DaoException
    * @throws ServiceException
    */
    public void delete(String id) throws DaoException, ServiceException;
    /**
    * @Description: 删除帐号信息
    * @param entity
    * @throws DaoException
    * @throws ServiceException
    */
    public void delete(SysAdmin entity) throws DaoException, ServiceException;

    /**
     * 生效帐号
     * @param id 
     * @return
     */
    public ResultModel enable(String id) throws DaoException, ServiceException;

    /**
     * 失效帐号
     * @param id 
     * @return
     */
    public ResultModel disable(String id) throws DaoException, ServiceException;

    /**
     * 取消帐号
     * @param id 
     * @return
     */
    public ResultModel cancel(String id) throws DaoException, ServiceException;

	/**
	 * @param vo
	 * @return
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月21日 下午2:48:13<br/>
	 * @author 王通<br/>
	 */
	public ResultModel addAdminAndUserAndEnable(AdminVo vo) throws Exception;
}