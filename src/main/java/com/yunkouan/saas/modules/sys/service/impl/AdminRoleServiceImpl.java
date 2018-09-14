package com.yunkouan.saas.modules.sys.service.impl;

import java.util.*;

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
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.dao.IAdminRoleAuthDao;
import com.yunkouan.saas.modules.sys.dao.IAdminRoleDao;
import com.yunkouan.saas.modules.sys.entity.SysAdminRole;
import com.yunkouan.saas.modules.sys.entity.SysAdminRoleAuth;
import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.service.IAdminRoleService;
import com.yunkouan.saas.modules.sys.service.IAuthService;
import com.yunkouan.saas.modules.sys.vo.AdminRoleVo;
import com.yunkouan.saas.modules.sys.vo.AuthVo;
import com.yunkouan.util.StringUtil;

/**
 * 角色服务实现类
 * @author tphe06 2017年2月14日
 */
@Service
@Transactional(readOnly=true)
public class AdminRoleServiceImpl implements IAdminRoleService {
    /**角色服务数据层接口*/
	@Autowired
    private IAdminRoleDao dao;
    /**角色授权数据层接口*/
	@Autowired
    private IAdminRoleAuthDao roleAuthDao;
	/**@Fields 权限接口 */
	@Autowired
	private IAuthService service;

    /**
     * 角色列表数据查询
     * @param vo 
     * @param page 
     * @return
     */
    public ResultModel list(AdminRoleVo vo) throws DaoException, ServiceException {
    	if(vo.getEntity() == null || vo.getEntity().getRoleStatus() == null) vo.setRoleStatusNot(Constant.STATUS_CANCEL);
		Page<SysAdminRole> page = PageHelper.startPage(vo.getCurrentPage()+1, vo.getPageSize());
    	List<SysAdminRole> list = dao.selectByExample(vo.getExample());
    	List<AdminRoleVo> r = new ArrayList<AdminRoleVo>();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		r.add(new AdminRoleVo(list.get(i)));
    	}
    	return new ResultModel().setPage(page).setList(r);
    }

    /**
     * 查询角色详情
     * @param id 
     * @return
     * @throws ServiceException 
     */
    public ResultModel view(String id) throws DaoException, ServiceException {
    	/**查询角色详情**/
    	SysAdminRole entity =  dao.selectByPrimaryKey(id);
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	AdminRoleVo vo = new AdminRoleVo(entity);
    	/**查询角色授权信息**/
//    	List<SysAuth> list = roleAuthDao.list(new SysAdminRoleAuth().setRoleId(entity.getRoleId()));
//    	vo.setList(list);
    	vo.setVolist(queryChilds(id));
        return new ResultModel().setObj(vo);
    }
	/**
	* @Description: 查询企业授权权限数据【返回所有权限数据，只是对该企业有权限的做标志】
	* @param a
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	private List<AuthVo> queryChilds(String roleid) throws DaoException, ServiceException {
		AuthVo vo = new AuthVo();
		List<AuthVo> list = all(vo);
		List<SysAuth> selects = roleAuthDao.list(new SysAdminRoleAuth().setRoleId(roleid));
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
		List<SysAuth> list = service.query(new SysAuth().setParentId(IAuthService.ROOT).setAuthStatus(Constant.STATUS_ACTIVE));
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
			List<SysAuth> list = service.query(new SysAuth().setParentId(c.getEntity().getAuthId()).setAuthStatus(Constant.STATUS_ACTIVE));
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
     * 添加角色
     * @param vo 
     * @return
     * @throws ServiceException 
     * @throws Exception 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel add(AdminRoleVo vo, Principal p) throws DaoException, ServiceException {
    	/**新增角色数据**/
    	SysAdminRole entity = vo.getEntity();
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	entity.clear();
    	entity.setRoleStatus(Constant.STATUS_OPEN);
    	String id = IdUtil.getUUID();
    	entity.setRoleId(id);
    	entity.setCreatePerson(p.getUserId());
    	int r = dao.insertSelective(entity);
    	if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        /**批量新增角色授权数据**/
        List<AuthVo> list = vo.getVolist();
        tree(list, id, p);
        /**返回角色数据**/
        return view(id);
    }
    /**
    * @Description: 批量添加树形结构的授权权限
    * @param list
    * @param orgid
    * @param p
    * @throws DaoException
    */
    private void tree(List<AuthVo> list, String roleid, Principal p) throws DaoException {
    	if(list == null || list.size() == 0) return;
    	for(int i=0; i<list.size(); ++i) {
    		AuthVo v = list.get(i);
    		SysAuth auth = v.getEntity();
    		if(v.getSelectStatus() == null || !v.getSelectStatus() 
    			|| auth == null || StringUtil.isTrimEmpty(auth.getAuthId())) continue;
        	AuthVo obj = list.get(i);
        	if(obj == null) continue;
        	SysAdminRoleAuth child = new SysAdminRoleAuth();
        	child.setRoleAuthId(IdUtil.getUUID());
        	child.setRoleId(roleid);
        	child.setAuthId(obj.getEntity().getAuthId());
        	child.setCreatePerson(p.getUserId());
        	int r = roleAuthDao.insertSelective(child);
        	if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    		tree(v.getList(), roleid, p);
    	}
    }

    /**
     * 修改角色
     * @param vo 
     * @return
     * @throws ServiceException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel update(AdminRoleVo vo, Principal p) throws DaoException, ServiceException {
    	/**修改角色**/
    	SysAdminRole entity = vo.getEntity();
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	//检查编号是否唯一
    	SysAdminRole old = dao.selectOne(new SysAdminRole().setRoleNo(entity.getRoleNo()));
    	if(old != null && !old.getRoleId().equals(entity.getRoleId())) throw new ServiceException(ErrorCode.NO_NOT_UNIQUE);
    	entity.clear();
    	entity.setUpdatePerson(p.getUserId());
        int r = dao.updateByPrimaryKeySelective(entity);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        /**修改角色授权，先删除然后新增**/
        roleAuthDao.delete(new SysAdminRoleAuth().setRoleId(entity.getRoleId()));
        List<AuthVo> list = vo.getVolist();
        tree(list, entity.getRoleId(), p);
        /**查询角色**/
        return view(entity.getRoleId());
    }

    /**
     * 生效角色
     * @param id 
     * @return
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel enable(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysAdminRole old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getRoleStatus() != Constant.STATUS_OPEN) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysAdminRole obj = new SysAdminRole();
    	obj.setRoleId(id);
    	obj.setRoleStatus(Constant.STATUS_ACTIVE);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
    }

    /**
     * 失效角色
     * @param id 
     * @return
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel disable(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysAdminRole old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getRoleStatus() != Constant.STATUS_ACTIVE) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysAdminRole obj = new SysAdminRole();
    	obj.setRoleId(id);
    	obj.setRoleStatus(Constant.STATUS_OPEN);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
    }

    /**
     * 取消角色
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel cancel(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysAdminRole old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getRoleStatus() != Constant.STATUS_OPEN) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysAdminRole obj = new SysAdminRole();
    	obj.setRoleId(id);
    	obj.setRoleStatus(Constant.STATUS_CANCEL);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
	}

	/**
	* @Description: 删除角色
	* @param id
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel delete(String id) throws DaoException, ServiceException {
    	SysAdminRole obj = new SysAdminRole();
    	obj.setRoleId(id);
        int r = dao.deleteByPrimaryKey(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return new ResultModel();
	}

	/**
	* @Description: 角色授权
	* @param id 角色id
	* @param list 权限列表
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel auth(String id, List<AuthVo> list, Principal p) throws DaoException, ServiceException {
        /**先删除然后新增**/
        roleAuthDao.delete(new SysAdminRoleAuth().setRoleId(id));
        tree(list, id, p);
        return new ResultModel();
	}

	/**
	* @Description: 仅修改角色
	* @param entity
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void update(SysAdminRole entity, Principal p) throws DaoException, ServiceException {
    	/**修改角色**/
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	entity.clear();
    	entity.setUpdatePerson(p.getUserId());
        int r = dao.updateByPrimaryKeySelective(entity);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    }

	/**
	* @Description: TODO
	* @param entity
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public SysAdminRole query(SysAdminRole entity) throws DaoException, ServiceException {
		return dao.selectOne(entity);
	}

	/**
	* @Description: TODO
	* @param entity
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public List<SysAdminRole> queryList(SysAdminRole entity) throws DaoException, ServiceException {
		return dao.select(entity);
	}
}