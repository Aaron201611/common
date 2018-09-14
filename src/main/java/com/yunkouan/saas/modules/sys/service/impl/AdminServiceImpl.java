package com.yunkouan.saas.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.DaoException;
import com.yunkouan.exception.ServiceException;
import com.yunkouan.saas.common.constant.Constant;
import com.yunkouan.saas.common.constant.ErrorCode;
import com.yunkouan.saas.common.util.IdUtil;
import com.yunkouan.saas.common.util.LoginUtil;
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.dao.IAdminDao;
import com.yunkouan.saas.modules.sys.dao.IAdministratorRoleDao;
import com.yunkouan.saas.modules.sys.dao.IOrgDao;
import com.yunkouan.saas.modules.sys.dao.IUserDao;
import com.yunkouan.saas.modules.sys.entity.SysAdmin;
import com.yunkouan.saas.modules.sys.entity.SysAdminRole;
import com.yunkouan.saas.modules.sys.entity.SysAdministratorRole;
import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.entity.SysOrg;
import com.yunkouan.saas.modules.sys.entity.SysUser;
import com.yunkouan.saas.modules.sys.service.IAdminService;
import com.yunkouan.saas.modules.sys.vo.AdminAuthVo;
import com.yunkouan.saas.modules.sys.vo.AdminVo;
import com.yunkouan.saas.modules.sys.vo.UserVo;
import com.yunkouan.util.StringUtil;
import com.yunkouan.util.UserUtil;

/**
 * 企业帐号服务实现类
 * @author tphe06 2017年2月14日
 */
@Service
@Transactional(readOnly=true)
public class AdminServiceImpl implements IAdminService {
	private static Log log = LogFactory.getLog(AdminServiceImpl.class);

	/**企业帐号数据层接口*/
	@Autowired
	private IAdminDao dao;
	/**企业用户数据层接口**/
	@Autowired
	private IUserDao userDao;
	/**企业帐号角色授权数据层接口**/
	@Autowired
	private IAdministratorRoleDao accountRoleDao;
    /**企业数据层接口*/
	@Autowired
    private IOrgDao orgDao;

    /**
     * 企业帐号列表数据查询
     * @param vo 
     * @param page 
     * @return
     */
    public ResultModel list(AdminVo vo) throws DaoException, ServiceException {
    	if(vo.getEntity() == null || vo.getEntity().getAdminStatus() == null) vo.setAdminStatusNot(Constant.STATUS_CANCEL);
		Page<SysAdmin> page = PageHelper.startPage(vo.getCurrentPage()+1, vo.getPageSize());
    	List<SysAdmin> list = dao.selectByExample(vo.getExample());
    	List<AdminVo> r = new ArrayList<AdminVo>();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		AdminVo n = new AdminVo(list.get(i));
    		r.add(fill(n));
    	}
    	return new ResultModel().setPage(page).setList(r);
    }

    /**
     * 查询企业帐号详情
     * @param id 
     * @return
     * @throws ServiceException 
     */
    public ResultModel view(String id) throws DaoException, ServiceException {
    	//查询帐号详情
    	SysAdmin entity =  dao.selectByPrimaryKey(id);
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	AdminVo vo = new AdminVo(entity);
    	//填充授权角色列表
    	List<SysAdminRole> list = accountRoleDao.list(new SysAdministratorRole().setAdminId(id));
    	vo.setList(list);
        return new ResultModel().setObj(fill(vo));
    }

    /**
    * @Description: 填充企业信息，用户信息
    * @param vo
    * @return
    */
    private AdminVo fill(AdminVo vo) {
    	SysAdmin entity = vo.getEntity();
//    	entity.setLoginPwd(null);
    	//填充企业信息
		if(StringUtils.isNoneBlank(entity.getOrgId())) {
			SysOrg org = orgDao.selectByPrimaryKey(entity.getOrgId());
			vo.setOrg(org);
		}
		//填充用户信息
		if(StringUtils.isNoneBlank(entity.getUserId())) {
			if(log.isDebugEnabled()) log.debug("1");
			SysUser user = userDao.selectByPrimaryKey(entity.getUserId());
			vo.setUser(user);
		}
    	return vo;
    }

    /**
     * 添加企业帐号
     * @param entity 
     * @return
     * @throws ServiceException 
     * @throws DaoException 
     */
    public ResultModel add(AdminVo vo, Principal p) throws DaoException, ServiceException {
    	/**新增企业帐号**/
    	SysAdmin entity = vo.getEntity();
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	entity.clear();
    	//检查编号是否唯一
    	SysAdmin old = dao.selectOne(new SysAdmin().setAdminNo(entity.getAdminNo()));
    	if(old != null) throw new ServiceException(ErrorCode.NO_NOT_UNIQUE);
    	//赋值其他属性
    	String id = IdUtil.getUUID();
    	entity.setAdminId(id);
    	entity.setAdminStatus(Constant.STATUS_OPEN);
    	/**密码为【帐号uuid+密码】明文做SHA1加密**/
    	if(vo.getPwdEncrypted() != null &&
    		vo.getPwdEncrypted()) entity.setLoginPwd(UserUtil.entryptPassword(id.concat(entity.getLoginPwd())));
    	entity.setCreatePerson(p.getUserId());
    	int r = dao.insertSelective(entity);
    	if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    	/**批量新增帐号授权**/
    	List<SysAdminRole> list = vo.getList();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		SysAdminRole role = list.get(i);
    		if(role == null || StringUtils.isBlank(role.getRoleId())) continue;
    		SysAdministratorRole obj = new SysAdministratorRole();
    		obj.setAdminId(id);
    		obj.setAdminRoleId(IdUtil.getUUID());
    		obj.setCreatePerson(p.getUserId());
    		obj.setRoleId(role.getRoleId());
    		obj.setUpdatePerson(p.getUserId());
    		r = accountRoleDao.insertSelective(obj);
    		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    	}
    	/**返回企业帐号信息**/
        return view(id);
    }

    /**
     * 修改企业帐号
     * @param vo 
     * @return
     * @throws ServiceException 
     * @throws DaoException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel update(AdminVo vo, Principal p) throws DaoException, ServiceException {
    	/**修改企业帐号信息**/
    	SysAdmin entity = vo.getEntity();
    	if(entity == null || StringUtils.isEmpty(entity.getAdminId())) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	entity.clear();
    	if("******".equals(entity.getLoginPwd())) entity.setLoginPwd(null);
    	//检查编号是否唯一
    	SysAdmin old = dao.selectOne(new SysAdmin().setAdminNo(entity.getAdminNo()));
    	if(old != null && !old.getAdminId().equals(entity.getAdminId())) throw new ServiceException(ErrorCode.NO_NOT_UNIQUE);
    	/**密码为【帐号uuid+密码】明文做SHA1加密**/
    	if(!StringUtil.isTrimEmpty(entity.getAdminId()) && !StringUtil.isTrimEmpty(entity.getLoginPwd())) {
    		entity.setLoginPwd(UserUtil.entryptPassword(entity.getAdminId().concat(entity.getLoginPwd())));
    	}
    	entity.setUpdatePerson(p.getUserId());
    	int r = dao.updateByPrimaryKeySelective(entity);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        /**批量修改企业帐号授权角色数据，先删除后新增**/
        r = accountRoleDao.delete(new SysAdministratorRole().setAdminId(entity.getAdminId()));
//        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    	List<SysAdminRole> list = vo.getList();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		SysAdminRole role = list.get(i);
    		if(role == null || StringUtil.isTrimEmpty(role.getRoleId())) continue;
    		SysAdministratorRole obj = new SysAdministratorRole();
    		obj.setAdminId(entity.getAdminId());
    		obj.setAdminRoleId(IdUtil.getUUID());
    		obj.setCreatePerson(p.getUserId());
    		obj.setRoleId(role.getRoleId());
    		obj.setUpdatePerson(p.getUserId());
    		r = accountRoleDao.insertSelective(obj);
    		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    	}
    	/**返回帐号信息**/
//        m.setObj(fill(new AdminVo(dao.selectByPrimaryKey(entity.getAdminId()))));
        return view(entity.getAdminId());
    }

    /**
     * 生效企业帐号
     * @param id 
     * @return
     * @throws DaoException 
     * @throws ServiceException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel enable(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysAdmin old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getAdminStatus() != Constant.STATUS_OPEN) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysAdmin obj = new SysAdmin();
    	obj.setAdminId(id);
    	obj.setAdminStatus(Constant.STATUS_ACTIVE);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
    }

    /**
     * 失效企业帐号
     * @param id 
     * @return
     * @throws DaoException 
     * @throws ServiceException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel disable(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysAdmin old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getAdminStatus() != Constant.STATUS_ACTIVE) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysAdmin obj = new SysAdmin();
    	obj.setAdminId(id);
    	obj.setAdminStatus(Constant.STATUS_OPEN);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
    }

    /**
     * 取消企业帐号
     * @throws DaoException 
     * @throws ServiceException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel cancel(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysAdmin old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getAdminStatus() != Constant.STATUS_OPEN) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysAdmin obj = new SysAdmin();
    	obj.setAdminId(id);
    	obj.setAdminStatus(Constant.STATUS_CANCEL);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
	}

	@Override
	public SysAdmin get(String id) {
		return dao.selectByPrimaryKey(id);
	}

    /**
     * 根据帐户编号和组织id查询帐户和用户信息
     * @param account
     * @return
     */
	@Override
	public AdminVo query(SysAdmin account) {
		SysAdmin entity = dao.selectOne(account);
		if(entity == null) return null;
		AdminVo vo = new AdminVo(entity);
		if(log.isDebugEnabled()) log.debug("2");
		SysUser user = userDao.selectByPrimaryKey(entity.getUserId());
		if(user == null) return null;
		vo.setUser(user);
		return vo;
	}

    /**
     * 根据帐号id查询该帐号的所有授权信息
     */
	@Override
	public List<SysAuth> query(String id) {
		return dao.query(new SysAdmin().setAdminId(id));
	}

	@Override
	public AdminVo load(String id) {
		SysAdmin entity = dao.selectByPrimaryKey(id);
//		entity.setLoginPwd("");
		AdminVo vo = new AdminVo(entity);
		if(log.isDebugEnabled()) log.debug("3");
		SysUser user = userDao.selectByPrimaryKey(entity.getUserId());
		vo.setUser(user);
		SysOrg org = orgDao.selectByPrimaryKey(entity.getOrgId());
		vo.setOrg(org);
		List<SysAdminRole> list = accountRoleDao.list(new SysAdministratorRole().setAdminId(id));
		vo.setList(list);
		return vo;
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void updatePwd(AdminVo vo, Principal p) throws DaoException, ServiceException {
		/**查询当前登录帐号信息**/
    	SysAdmin old = dao.selectByPrimaryKey(p.getAccountId());
    	if(old == null) throw new ServiceException(ErrorCode.DATA_NO_EXISTS);
    	/**修改企业帐号信息**/
    	SysAdmin entity = new SysAdmin();
    	entity.setAdminId(old.getAdminId());
    	/**如果新旧密码都不为空则修改密码**/
    	if(vo.getEntity() != null 
    		&& !StringUtils.isBlank(vo.getOldPwd()) 
    		&& !StringUtils.isBlank(vo.getEntity().getLoginPwd())) {
        	/**校验旧密码是否正确，密码为【帐号uuid+密码】明文做SHA1加密**/
        	String oldPwd = UserUtil.entryptPassword(old.getAdminId().concat(vo.getOldPwd()));
        	if(!oldPwd.equals(old.getLoginPwd())) throw new ServiceException(ErrorCode.PWD_NOT_EQUAL);
    		entity.setLoginPwd(UserUtil.entryptPassword(old.getAdminId().concat(vo.getEntity().getLoginPwd())));
    	}
    	entity.setUpdatePerson(p.getUserId());
    	if(vo.getEntity() != null) entity.setNote(vo.getEntity().getNote());
    	int r = dao.updateByPrimaryKeySelective(entity);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        /**修改企业用户信息**/
        if(vo.getUser() != null && !StringUtils.isBlank(vo.getUser().getEmail())) {
	        SysUser user = new SysUser();
	        user.setUserId(old.getUserId());
	        user.setEmail(vo.getUser().getEmail());
	        if(log.isDebugEnabled()) log.debug("4");
	        r = userDao.updateByPrimaryKeySelective(user);
	        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        }
    }
	/**
	* @Description: 重置用户密码
	* @param userId 用户id
	* @param password 用户密码（明文）
	* @return
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void resetPwd(String userId, String password) throws DaoException, ServiceException {
		SysAdmin admin = dao.selectOne(new SysAdmin().setUserId(userId));
		SysAdmin a = new SysAdmin();
		a.setAdminId(admin.getAdminId());
		a.setLoginPwd(UserUtil.entryptPassword(admin.getAdminId().concat(password)));
		int r = dao.updateByPrimaryKeySelective(a);
		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
	}

	/**
	* @Description: 查询授权的权限【不限级别权限】
	* @param vo
	* @return
	 */
	@Override
	public List<SysAuth> query4all(AdminAuthVo vo) {
		return dao.query4all(vo);
	}

	/**
	* @Description: 根据条件修改帐号信息
	* @param entity
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void update(SysAdmin entity, Principal p) throws DaoException, ServiceException {
    	/**修改企业帐号信息**/
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	entity.clear();
    	/**密码为【帐号uuid+密码】明文做SHA1加密**/
    	String pwd = entity.getLoginPwd();
    	entity.setLoginPwd(null);
    	entity = dao.selectOne(entity);
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(!StringUtil.isTrimEmpty(pwd)) {
    		entity.setLoginPwd(UserUtil.entryptPassword(entity.getAdminId().concat(pwd)));
    	}
    	entity.setUpdatePerson(p.getUserId());
    	int r = dao.updateByPrimaryKeySelective(entity);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    }

	/**
	* @Description: 根据主键删除帐号信息
	* @param id
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void delete(String id) throws DaoException, ServiceException {
		int r = dao.deleteByPrimaryKey(id);
		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
	}

	/**
	* @Description: 根据条件删除帐号信息
	* @param entity
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void delete(SysAdmin entity) throws DaoException, ServiceException {
		SysAdmin old = dao.selectOne(entity);
		if(old == null) throw new DaoException(ErrorCode.DATA_NO_EXISTS);
		accountRoleDao.delete(new SysAdministratorRole().setAdminId(old.getAdminId()));
		int r = dao.deleteByPrimaryKey(old.getAdminId());
		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
	}

	/**
	* @Description: 帐号授权
	* @param vo
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel auth(AdminVo vo) throws DaoException, ServiceException {
		ResultModel m = new ResultModel();
		SysAdmin entity = vo.getEntity();
		entity = dao.selectOne(entity);
        /**批量修改企业帐号授权角色数据，先删除后新增**/
        int r = accountRoleDao.delete(new SysAdministratorRole().setAdminId(entity.getAdminId()));
//        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    	List<SysAdminRole> list = vo.getList();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		SysAdminRole role = list.get(i);
    		if(role == null || StringUtil.isTrimEmpty(role.getRoleId())) continue;
    		SysAdministratorRole obj = new SysAdministratorRole();
    		obj.setAdminId(entity.getAdminId());
    		obj.setAdminRoleId(IdUtil.getUUID());
    		obj.setRoleId(role.getRoleId());
    		r = accountRoleDao.insertSelective(obj);
    		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    	}
    	/**返回帐号信息**/
        m.setObj(new AdminVo(dao.selectByPrimaryKey(entity.getAdminId())));
        return m;
	}

	/**
	* @Description: 查询帐号角色列表
	* @param entity
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public AdminVo queryRole(SysAdmin entity) throws DaoException, ServiceException {
		SysAdmin a = dao.selectOne(entity);
		if(a == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
		SysUser user = userDao.selectByPrimaryKey(a.getUserId());
		List<SysAdminRole> list = accountRoleDao.list(new SysAdministratorRole().setAdminId(a.getAdminId()));
		return new AdminVo(a).setUser(user).setList(list);
	}

	/**
	* @Description: 修改登录帐号密码【限用户-帐号一对一情况】
	* @param userId
	* @param password
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public void updatePwd(String userId, String password) throws DaoException, ServiceException {
		SysAdmin a = dao.selectOne(new SysAdmin().setUserId(userId));
		int r = dao.updateByPrimaryKeySelective(new SysAdmin().setAdminId(a.getAdminId()).setLoginPwd(password));
		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
	}

	/**
	* @Description: 添加【用户，帐号和角色】
	* @param vo
	* @param p
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
    @Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel addAdminAndUser(AdminVo vo, Principal p) throws DaoException, ServiceException {
    	//检查账号表是否唯一
    	UserVo userVo = new UserVo();
    	SysUser checkUser = new SysUser();
    	userVo.setEntity(checkUser);
    	checkUser.setOrgId(vo.getEntity().getOrgId());
    	checkUser.setUserNo(vo.getEntity().getAdminNo());
    	List<SysUser> oldUser = userDao.selectByExample(userVo.getExample());
    	if (oldUser != null && oldUser.size() > 0) {
    		throw new ServiceException(ErrorCode.NO_NOT_UNIQUE);
    	}
		SysUser user = new SysUser();
		String id = IdUtil.getUUID();
		user.setUserId(id);
		user.setOrgId(vo.getEntity().getOrgId());
		user.setCreatePerson(p.getUserId());
		user.setIsEmployee(1);
		user.setUserNo(vo.getEntity().getAdminNo());
		user.setUserName(vo.getEntity().getAdminName());
		user.setUserStatus(Constant.STATUS_ACTIVE);
		int r = userDao.insertSelective(user);
		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
		vo.getEntity().setUserId(id);
		//添加用户
		ResultModel rm = this.add(vo, p);
		return rm;
	}

	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月21日 下午2:49:20<br/>
	 * @author 王通<br/>
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel addAdminAndUserAndEnable(AdminVo vo) throws Exception {
		Principal p = LoginUtil.getLoginUser();
    	ResultModel r = null;
		if (StringUtil.isTrimEmpty(vo.getEntity().getAdminId())) {
			r = this.addAdminAndUser(vo, p);
			String skuId = vo.getEntity().getAdminId();
			this.enable(skuId);
		} else {
			r = this.update(vo, p);
			String skuId = vo.getEntity().getAdminId();
			this.enable(skuId);
		}
		return r;
	}
}