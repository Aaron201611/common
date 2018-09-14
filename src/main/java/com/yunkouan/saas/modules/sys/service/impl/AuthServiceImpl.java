package com.yunkouan.saas.modules.sys.service.impl;

import java.util.*;

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
import com.yunkouan.saas.modules.sys.dao.IAuthDao;
import com.yunkouan.saas.modules.sys.entity.SysAuth;
import com.yunkouan.saas.modules.sys.service.IAuthService;
import com.yunkouan.saas.modules.sys.vo.AuthVo;
import com.yunkouan.util.StringUtil;

/**
 * 权限服务实现类
 * @author tphe06 2017年2月14日
 */
@Service
@Transactional(readOnly=true)
public class AuthServiceImpl implements IAuthService {
    /**权限数据层接口*/
    @Autowired
    private IAuthDao dao;

    /**
     * 权限列表数据查询
     * @param auth 
     * @param page 
     * @return
     */
    public ResultModel list(AuthVo vo) throws DaoException, ServiceException {
    	if(vo.getEntity() == null || vo.getEntity().getAuthStatus() == null) vo.setAuthStatusNot(Constant.STATUS_CANCEL);
		Page<SysAuth> page = PageHelper.startPage(vo.getCurrentPage()+1, vo.getPageSize());
    	List<SysAuth> list = dao.selectByExample(vo.getExample());
    	List<AuthVo> r = new ArrayList<AuthVo>();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		AuthVo n = new AuthVo(list.get(i));
        	if(StringUtils.isNoneBlank(n.getEntity().getParentId())) {
        		SysAuth parent =  dao.selectByPrimaryKey(n.getEntity().getParentId());
        		n.setParent(parent);
        	}
    		r.add(n);
    	}
    	return new ResultModel().setPage(page).setList(r);
    }

    /**
     * 查询权限详情
     * @param id 
     * @return
     */
    public ResultModel view(String id) throws DaoException, ServiceException {
    	SysAuth obj =  dao.selectByPrimaryKey(id);
    	obj.setAuthLevelTxt(String.valueOf(obj.getAuthLevel())).setAuthTypeTxt(String.valueOf(obj.getAuthType()));
    	AuthVo vo = new AuthVo(obj);
    	if(StringUtils.isNoneBlank(obj.getParentId())) {
    		SysAuth parent =  dao.selectByPrimaryKey(obj.getParentId());
    		vo.setParent(parent);
    	}
        return new ResultModel().setObj(vo);
    }

    /**
     * 添加权限
     * @param vo 
     * @return
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel add(AuthVo vo, Principal p) throws DaoException, ServiceException {
    	SysAuth entity = vo.getEntity();
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	entity.clear();
    	//检查编号是否唯一
    	SysAuth old = dao.selectOne(new SysAuth().setAuthNo(entity.getAuthNo()));
    	if(old != null) throw new ServiceException(ErrorCode.NO_NOT_UNIQUE);
    	entity.setAuthLevel(Integer.parseInt(entity.getAuthLevelTxt()));
    	entity.setAuthType(Integer.parseInt(entity.getAuthTypeTxt()));
    	entity.setAuthStatus(Constant.STATUS_OPEN);
    	String id = IdUtil.getUUID();
    	entity.setAuthId(id);
    	if(StringUtils.isBlank(entity.getParentId())) entity.setParentId(ROOT);
    	entity.setCreatePerson(p.getUserId());
    	int r = dao.insertSelective(entity);
    	if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return view(id);
    }

    /**
     * 修改权限
     * @param vo 
     * @return
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel update(AuthVo vo, Principal p) throws DaoException, ServiceException {
    	SysAuth entity = vo.getEntity();
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	entity.clear();
    	entity.setAuthLevel(Integer.parseInt(entity.getAuthLevelTxt()));
    	entity.setAuthType(Integer.parseInt(entity.getAuthTypeTxt()));
    	//检查编号是否唯一
    	SysAuth old = dao.selectOne(new SysAuth().setAuthNo(entity.getAuthNo()));
    	if(old != null && !old.getAuthId().equals(entity.getAuthId())) throw new ServiceException(ErrorCode.NO_NOT_UNIQUE);
    	if(StringUtils.isBlank(entity.getParentId())) entity.setParentId(ROOT);
    	entity.setUpdatePerson(p.getUserId());
    	int r = dao.updateByPrimaryKeySelective(entity);
    	if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return view(entity.getAuthId());
    }

    /**
     * 生效权限
     * @param id 
     * @return
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel enable(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysAuth old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getAuthStatus() != Constant.STATUS_OPEN) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysAuth obj = new SysAuth();
    	obj.setAuthId(id);
    	obj.setAuthStatus(Constant.STATUS_ACTIVE);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
    }

    /**
     * 失效权限
     * @param id 
     * @return
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel disable(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysAuth old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getAuthStatus() != Constant.STATUS_ACTIVE) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysAuth obj = new SysAuth();
    	obj.setAuthId(id);
    	obj.setAuthStatus(Constant.STATUS_OPEN);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
    }

    /**
     * 取消权限
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel cancel(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	SysAuth old =  dao.selectByPrimaryKey(id);
    	if(old == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	if(old.getAuthStatus() != Constant.STATUS_OPEN) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysAuth obj = new SysAuth();
    	obj.setAuthId(id);
    	obj.setAuthStatus(Constant.STATUS_CANCEL);
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
	}

    /**
     * 按照条件查询所有权限数据
     * @param entity 
     * @return
     */
	@Override
	public List<SysAuth> query(SysAuth entity) {
		return dao.select(entity);
	}

	@Override
	public List<SysAuth> query(AuthVo vo) throws DaoException, ServiceException {
		vo.setOrderBy(" ah.auth_no asc ");
		return dao.query(vo);
	}

	@Override
	public List<SysAuth> query4admin(AuthVo vo) throws DaoException, ServiceException {
		vo.setOrderBy(" ah.auth_no asc ");
		return dao.query4admin(vo);
	}

	/**
	* @Description: TODO
	* @param entity
	* @return
	 */
	@Override
	public SysAuth get(SysAuth entity) {
		return dao.selectOne(entity);
	}

	/**
	* @Description: TODO
	* @param vo
	* @return
	 */
	@Override
	public List<SysAuth> listAll(AuthVo vo) {
		vo.setOrderBy(" ah.auth_no asc ");
		return dao.listAll(vo);
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
	public ResultModel saveAndEnable(AuthVo vo) throws Exception {
    	Principal p = LoginUtil.getLoginUser();
    	ResultModel r = null;
		if (StringUtil.isTrimEmpty(vo.getEntity().getAuthId())) {
			r = this.add(vo, p);
			String skuId = vo.getEntity().getAuthId();
			this.enable(skuId);
		} else {
			r = this.update(vo, p);
			String skuId = vo.getEntity().getAuthId();
			this.enable(skuId);
		}
		return r;
	}
}