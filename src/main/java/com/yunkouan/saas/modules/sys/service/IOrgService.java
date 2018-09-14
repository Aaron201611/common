package com.yunkouan.saas.modules.sys.service;

import java.util.List;

import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.DaoException;
import com.yunkouan.exception.ServiceException;
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.entity.SysOrg;
import com.yunkouan.saas.modules.sys.entity.SysOrgAuth;
import com.yunkouan.saas.modules.sys.vo.OrgVo;

/**
 * 企业服务接口
 * @author tphe06 2017年2月14日
 */
public interface IOrgService {
    /**
     * 企业列表数据查询
     * @param vo 
     * @param page 
     * @return
     */
    public ResultModel list(OrgVo vo) throws DaoException, ServiceException;
    /**
     * 企业列表数据查询
     * @param entity 
     * @return
     */
    public List<SysOrg> list(SysOrg entity);
    /**
     * 根据企业名称模糊查询所有id
     */
    public List<String> list(String name, String orgType);

    /**
     * 查看企业详情
     * @param id 
     * @return
     * @throws ServiceException 
     */
    public ResultModel view(String id) throws DaoException, ServiceException;
    /**
     * 根据企业信息在数据库查询是否存在此数据
     * @param entity 
     * @return
     */
    public SysOrg query(SysOrg entity);
    /**
    * @Description: 查询企业授权权限列表
    * @param entity
    * @return
    * @throws DaoException
    * @throws ServiceException
    */
    public List<SysAuth> query(SysOrgAuth entity) throws DaoException, ServiceException;

    /**
     * 添加企业
     * @param vo 
     * @return
     */
    public ResultModel add(OrgVo vo, Principal p) throws DaoException, ServiceException;

    /**
     * 修改企业
     * @param vo 
     * @return
     */
    public ResultModel update(OrgVo vo, Principal p) throws DaoException, ServiceException;

    /**
     * 生效企业信息
     * @param id 
     * @return
     */
    public ResultModel enable(String id) throws DaoException, ServiceException;

    /**
     * 失效企业信息
     * @param id 
     * @return
     */
    public ResultModel disable(String id) throws DaoException, ServiceException;

    /**
     * 取消企业信息
     * @param id 
     * @return
     */
    public ResultModel cancel(String id) throws DaoException, ServiceException;

    /**
    * @Description: 按照树形结构查询所有权限列表
    * @return
    * @throws DaoException
    * @throws ServiceException
    */
    public ResultModel tree(List<SysAuth> auths) throws DaoException, ServiceException;
	/**
	 * @param vo
	 * @return
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月21日 上午10:53:51<br/>
	 * @author 王通<br/>
	 */
	public ResultModel saveAndEnable(OrgVo vo) throws Exception;
}