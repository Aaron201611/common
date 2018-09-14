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
import com.yunkouan.saas.common.constant.Constant;
import com.yunkouan.saas.common.constant.ErrorCode;
import com.yunkouan.saas.common.util.IdUtil;
import com.yunkouan.saas.common.util.LoginUtil;
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.dao.IOrgAuthDao;
import com.yunkouan.saas.modules.sys.dao.IOrgDao;
import com.yunkouan.saas.modules.sys.dao.IUserDao;
import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.entity.SysOrg;
import com.yunkouan.saas.modules.sys.entity.SysOrgAuth;
import com.yunkouan.saas.modules.sys.entity.SysUser;
import com.yunkouan.saas.modules.sys.service.IAuthService;
import com.yunkouan.saas.modules.sys.service.IOrgService;
import com.yunkouan.saas.modules.sys.vo.AuthVo;
import com.yunkouan.saas.modules.sys.vo.OrgVo;
import com.yunkouan.saas.modules.sys.vo.UserVo;
import com.yunkouan.util.StringUtil;

/**
 * 企业服务实现类
 * @author tphe06 2017年2月14日
 */
@Service
@Transactional(readOnly=true)
public class OrgServieImpl implements IOrgService {
    /**企业数据层接口*/
	@Autowired
    private IOrgDao dao;
	/**企业用户数据层接口**/
	@Autowired
	private IUserDao userDao;
	/**【企业-权限授权】数据层接口*/
	@Autowired
	private IOrgAuthDao orgAuthDao;
	/**@Fields 权限接口 */
	@Autowired
	private IAuthService service;

    /**
     * 企业列表数据查询
     * @param vo 
     * @param page 
     * @return
     */
    public ResultModel list(OrgVo vo) throws DaoException, ServiceException {
    	if(vo.getEntity() == null || vo.getEntity().getOrgStatus() == null) vo.setOrgStatusNot(Constant.STATUS_CANCEL);
		Page<SysOrg> page = PageHelper.startPage(vo.getCurrentPage()+1, vo.getPageSize());
    	List<SysOrg> list = dao.selectByExample(vo.getExample());
    	List<OrgVo> r = new ArrayList<OrgVo>();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		r.add(new OrgVo(list.get(i)));
    	}
    	return new ResultModel().setPage(page).setList(r);
    }

    /**
     * 查看企业详情
     * @param id 
     * @return
     * @throws ServiceException 
     */
	public ResultModel view(String id) throws DaoException, ServiceException {
    	/**查询企业详情**/
    	SysOrg entity =  dao.selectByPrimaryKey(id);
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	OrgVo vo = new OrgVo(entity);
    	/**查询企业授权信息**/
    	vo.setVolist(queryChilds(id, vo.getEntity().getOrgType()));
    	//查询企业下是否存在非取消状态的管理员
    	UserVo userVo = new UserVo();
    	SysUser checkUser = new SysUser();
    	userVo.setEntity(checkUser);
    	checkUser.setOrgId(entity.getOrgId());
        userVo.setUserStatusNot(Constant.STATUS_CANCEL);
    	List<SysUser> oldUser = userDao.selectByExample(userVo.getExample());
    	if (oldUser != null && oldUser.size() > 0) {
    		vo.setHasUsers(true);
    	} else {
    		vo.setHasUsers(false);
    	}
    	return new ResultModel().setObj(vo);
    }
	/**
	* @Description: 查询企业授权权限数据【返回所有权限数据，只是对该企业有权限的做标志】
	* @param a
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	private List<AuthVo> queryChilds(String orgid, String orgType) throws DaoException, ServiceException {
		AuthVo vo = new AuthVo();
		if(SysOrg.TYPE_PARK.equals(orgType)) {
			vo.addLevels(SysAuth.AUTH_LEVEL_PARK_USER);
		} else if(SysOrg.TYPE_ORG.equals(orgType)) {
			vo.addLevels(SysAuth.AUTH_LEVEL_ORG_USER);
		} else {
			vo.addLevels(SysAuth.AUTH_LEVEL_PLATFORM_ADMIN);
		}
		List<AuthVo> list = all(vo);
		List<SysAuth> selects = query(new SysOrgAuth().setOrgId(orgid));
		//遍历树形结构的权限数据，把有权限的打上标识
		exists(list, selects);
		return list;
	}
	/**
	* @Description: 遍历树形结构的权限数据，把有权限的打上标识
	* @param list
	* @param selects
	*/
	private static void exists(List<AuthVo> list, List<SysAuth> selects) {
		if(list == null || selects == null) return;
		if(list.size() == 0 || selects.size() == 0) return;
		for(int i=0; i<list.size(); ++i) {
			AuthVo vo = list.get(i);
			if(exists(selects, vo)) vo.setSelectStatus(true);
			exists(vo.getList(), selects);
		}
	}
	/**
	* @Description: 判断列表中是否存在该节点
	* @param list
	* @param vo
	* @return
	*/
	private static boolean exists(List<SysAuth> list, AuthVo vo) {
		if(list == null || vo == null || vo.getEntity() == null) return false;
		for(int i=0; i<list.size(); ++i) {
			if(list.get(i).getAuthId().equals(vo.getEntity().getAuthId())) return true;
		}
		return false;
	}
	/**
	* @Description: 按照树形结构查询所有权限数据
	* @param vo
	* @return
	* @throws Exception
	*/
	private List<AuthVo> all(AuthVo vo) throws DaoException, ServiceException {
		//查询根节点
		SysAuth entity = new SysAuth();
		entity.setParentId(IAuthService.ROOT);
		entity.setAuthStatus(Constant.STATUS_ACTIVE);
		vo.setEntity(entity);
//		AuthVo vo1 = new AuthVo(entity);
//		vo1.addLevels(SysAuth.AUTH_LEVEL_ORG_ADMIN);
//		vo1.addLevels(SysAuth.AUTH_LEVEL_ORG_USER);
		List<SysAuth> list = service.listAll(vo);
		vo.setList(chg(list));
		tree1(vo);
		return vo.getList();
	}
	/**
	* @Description: 查找所有树形结构的权限数据
	* @param vo
	*/
	private void tree1(AuthVo vo) {
		if(vo.getList() == null || vo.getList().size() == 0) return;
		for(int i=0; i<vo.getList().size(); ++i) {
			AuthVo c = vo.getList().get(i);
			vo.getEntity().setParentId(c.getEntity().getAuthId());
//			SysAuth entity = new SysAuth();
//			entity.setParentId(c.getEntity().getAuthId());
//			entity.setAuthStatus(Constant.STATUS_ACTIVE);
//			AuthVo vo1 = new AuthVo(entity);
//			vo1.addLevels(SysAuth.AUTH_LEVEL_ORG_ADMIN);
//			vo1.addLevels(SysAuth.AUTH_LEVEL_ORG_USER);
			List<SysAuth> list = service.listAll(vo);
			c.setList(chg(list));
			tree1(c);
		}
	}
	/**
	* @Description: 把权限实体列表转化成权限数据传输对象列表
	* @param list
	* @return
	*/
	private static List<AuthVo> chg(List<SysAuth> list) {
		if(list == null) return null;
		List<AuthVo> r = new ArrayList<AuthVo>();
		for(int i=0; i<list.size(); ++i) {
			r.add(new AuthVo(list.get(i)));
		}
		return r;
	}


	/**
     * 添加企业
     * @param org 
     * @return
     * @throws ServiceException 
     * @throws DaoException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel add(OrgVo vo, Principal p) throws DaoException, ServiceException {
    	/**新增企业信息**/
    	SysOrg entity = vo.getEntity();
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	entity.clear();
    	//检查编号是否唯一
    	SysOrg old = dao.selectOne(new SysOrg().setOrgNo(entity.getOrgNo()));
    	if(old != null) throw new ServiceException(ErrorCode.NO_NOT_UNIQUE);
    	entity.setOrgStatus(Constant.STATUS_OPEN);
    	String id = IdUtil.getUUID();
    	entity.setOrgId(id);
    	entity.setCreatePerson(p.getUserId());
    	int r = dao.insertSelective(entity);
    	if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    	/**批量新增企业授权信息**/
    	List<AuthVo> list = vo.getVolist();
    	List<AuthVo> volist = new ArrayList<AuthVo>();
    	tree(list, volist);
    	for(int i=0; i<volist.size(); ++i) {
    		AuthVo v = volist.get(i);
    		SysAuth auth = v.getEntity();
    		if(v.getSelectStatus() == null || !v.getSelectStatus() 
    			|| auth == null || StringUtil.isTrimEmpty(auth.getAuthId())) continue;
    		insertOrgAuth(auth, id, p);
    	}
    	/**返回企业信息**/
        return view(id);
    }
    /**
    * @Description: 批量添加树形结构的授权权限
    * @param list
    * @param orgid
    * @param p
    * @throws DaoException
    */
    private void tree(List<AuthVo> list, List<AuthVo> volist) throws DaoException {
    	if(list == null || list.size() == 0) return;
    	for(int i=0; i<list.size(); ++i) {
    		AuthVo v = list.get(i);
    		volist.add(v);
    		tree(v.getList(), volist);
    	}
    }
    private void insertOrgAuth(SysAuth auth, String orgid, Principal p) throws DaoException {
		SysOrgAuth child = new SysOrgAuth();
		child.setOrgAuthId(IdUtil.getUUID());
		child.setAuthId(auth.getAuthId());
		child.setOrgId(orgid);
		child.setCreatePerson(p.getUserId());
		child.setUpdatePerson(p.getUserId());
		int r = orgAuthDao.insertSelective(child);
		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    }

    /**
     * 修改企业
     * @param org 
     * @return
     * @throws ServiceException 
     * @throws DaoException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel update(OrgVo vo, Principal p) throws DaoException, ServiceException {
    	/**修改企业信息**/
    	SysOrg entity = vo.getEntity();
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	//检查编号是否唯一
    	SysOrg old = dao.selectOne(new SysOrg().setOrgNo(entity.getOrgNo()));
    	if(old != null && !old.getOrgId().equals(entity.getOrgId())) throw new ServiceException(ErrorCode.NO_NOT_UNIQUE);
    	entity.clear();
    	entity.setUpdatePerson(p.getUserId());
    	//检查是否有非取消状态的管理员，如果有，则不能修改企业类型（园区、企业）
//    	if (!StringUtil.isTrimEmpty(entity.getOrgType()) && !StringUtil.equals(entity.getOrgType(), old.getOrgType())) {
//    		//判断是否有修改企业类型
//    		//检查账号表是否有非取消状态的管理员
//        	UserVo userVo = new UserVo();
//        	SysUser checkUser = new SysUser();
//        	userVo.setEntity(checkUser);
//        	checkUser.setOrgId(entity.getOrgId());
//            userVo.setUserStatusNot(Constant.STATUS_CANCEL);
//        	List<SysUser> oldUser = userDao.selectByExample(userVo.getExample());
//        	if (oldUser != null && oldUser.size() > 0) {
//        		throw new ServiceException("valid_org_user_not_empty");
//        	}
//    	}
        int r = dao.updateByPrimaryKeySelective(entity);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        /**修改企业授权信息，先删除后新增**/
    	List<AuthVo> volist = vo.getVolist();
    	List<AuthVo> list = new ArrayList<AuthVo>();
    	tree(volist, list);
    	updateOrgAuth(list, entity.getOrgId(), p);
    	/**返回企业信息**/
        return view(entity.getOrgId());
    }
    /**
    * @Description: 批量添加树形结构的授权权限
    * @param list
    * @param orgid
    * @param p
    * @throws DaoException
     * @throws ServiceException 
    */
    private void updateOrgAuth(List<AuthVo> list, String orgid, Principal p) throws DaoException, ServiceException {
    	if(list == null || list.size() == 0 || StringUtils.isBlank(orgid)) return;
    	for(int i=0; i<list.size(); ++i) {
    		AuthVo v = list.get(i);
    		SysAuth auth = v.getEntity();
    		if(auth == null || StringUtils.isBlank(auth.getAuthId())) continue;
    		//前端取消授权，后台判断是否被授权
    		if(v.getSelectStatus() == null || !v.getSelectStatus()) {
    			//如果已经不存在则不做任何修改
	    		SysOrgAuth obj = orgAuthDao.selectOne(new SysOrgAuth().setAuthId(auth.getAuthId()).setOrgId(orgid));
	    		if(obj != null) {
	    			try {
	    				int r = orgAuthDao.deleteByPrimaryKey(obj.getOrgAuthId());
	    				if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
	    			} catch(Exception e) {
	    				e.printStackTrace();
	    				throw new ServiceException(ErrorCode.AUTH_NOT_CANCEL);
	    			}
	    		}
    		} else {
	    		SysOrgAuth child = new SysOrgAuth();
	    		child.setOrgAuthId(IdUtil.getUUID());
	    		child.setAuthId(auth.getAuthId());
	    		child.setOrgId(orgid);
	    		child.setCreatePerson(p.getUserId());
	    		child.setUpdatePerson(p.getUserId());
	    		//如果已经存在则不做任何修改
	    		SysOrgAuth obj = orgAuthDao.selectOne(new SysOrgAuth().setAuthId(auth.getAuthId()).setOrgId(orgid));
	    		if(obj == null) {
		    		int r = orgAuthDao.insertSelective(child);
		    		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
	    		}
    		}
    	}
    }

    @Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel enable(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysOrg old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getOrgStatus() != Constant.STATUS_OPEN) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysOrg obj = new SysOrg();
    	obj.setOrgId(id);
    	obj.setOrgStatus(Constant.STATUS_ACTIVE);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
    }

    @Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel disable(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysOrg old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getOrgStatus() != Constant.STATUS_ACTIVE) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysOrg obj = new SysOrg();
    	obj.setOrgId(id);
    	obj.setOrgStatus(Constant.STATUS_OPEN);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
    }

    @Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel cancel(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysOrg old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getOrgStatus() != Constant.STATUS_OPEN) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysOrg obj = new SysOrg();
    	obj.setOrgId(id);
    	obj.setOrgStatus(Constant.STATUS_CANCEL);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
	}

    /**
     * 根据企业信息在数据库查询是否存在此数据
     * @param entity
     * @return
     */
    @Override
	public SysOrg query(SysOrg entity) {
    	return dao.selectOne(entity);
	}

	/**
	* @Description: 根据企业名称模糊查询所有id
	* @param entity
	* @return
	 */
	@Override
	public List<String> list(String name, String orgType) {
		if(StringUtils.isBlank(name)) name = "";
		OrgVo orgVo = new OrgVo();		
		orgVo.setOrgNameLike(StringUtil.likeEscapeH(name));
		SysOrg entity = new SysOrg();
		entity.setOrgType(orgType);
		orgVo.setEntity(entity);
		return dao.list(orgVo);
	}

	/**
	* @Description: 企业列表数据查询
	* @param entity
	* @return
	 */
	@Override
	public List<SysOrg> list(SysOrg entity) {
		List<SysOrg> list = dao.selectByExample(new OrgVo(entity).getExample());
		return list;
	}

	/**
	* @Description: 查询企业授权权限列表
	* @param entity
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public List<SysAuth> query(SysOrgAuth entity) throws DaoException, ServiceException {
		return orgAuthDao.list(entity);
	}

	/**
	* @Description: 按照树形结构查询所有权限列表
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public ResultModel tree(List<SysAuth> auths) throws DaoException, ServiceException {
		AuthVo vo = new AuthVo();
		if(auths != null) for(int i=0; i<auths.size(); ++i) {
			vo.addLevels(auths.get(i).getAuthLevel());
		}
		List<AuthVo> list = all(vo);
		return new ResultModel().setList(list);
	}

	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月21日 上午10:54:18<br/>
	 * @author 王通<br/>
	 */
	@Override
    @Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel saveAndEnable(OrgVo vo) throws Exception {
    	Principal p = LoginUtil.getLoginUser();
    	ResultModel r = null;
		if (StringUtil.isTrimEmpty(vo.getEntity().getOrgId())) {
			r = this.add(vo, p);
			String skuId = vo.getEntity().getOrgId();
			this.enable(skuId);
		} else {
			r = this.update(vo, p);
			String skuId = vo.getEntity().getOrgId();
			this.enable(skuId);
		}
		return r;
	}
}