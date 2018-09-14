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
import com.yunkouan.saas.modules.sys.dao.IFingerDao;
import com.yunkouan.saas.modules.sys.dao.IUserDao;
import com.yunkouan.saas.modules.sys.entity.SysFinger;
import com.yunkouan.saas.modules.sys.entity.SysUser;
import com.yunkouan.saas.modules.sys.service.IUserService;
import com.yunkouan.saas.modules.sys.vo.UserVo;
import com.yunkouan.util.StringUtil;
import com.zkteco.biometric.ZKFVMatch;

/**
 * 用户服务实现类
 * @author tphe06 2017年2月14日
 */
@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements IUserService {
	private static Log log = LogFactory.getLog(UserServiceImpl.class);

	/**用户数据层接口*/
	@Autowired
    private IUserDao dao;
	/**@Fields 指纹数据层接口 */
	@Autowired
	private IFingerDao fingerDao;

    /**
     * 用户列表数据查询
     * @param vo 
     * @param page 
     * @return
     */
    public ResultModel list(UserVo vo) {
    	if(vo.getEntity() == null || vo.getEntity().getUserStatus() == null) vo.setUserStatusNot(Constant.STATUS_CANCEL);
		Page<SysUser> page = PageHelper.startPage(vo.getCurrentPage()+1, vo.getPageSize());
		if(log.isDebugEnabled()) log.debug("5");
    	List<SysUser> list = dao.selectByExample(vo.getExample());
    	List<UserVo> r = new ArrayList<UserVo>();
    	if(list != null) for(int i=0; i<list.size(); ++i) {
    		r.add(new UserVo(list.get(i)));
    	}
    	return new ResultModel().setPage(page).setList(r);
    }

    /**
     * 查询用户详情
     * @param id 用户id
     * @return
     * @throws ServiceException 
     */
	public ResultModel view(String id) throws ServiceException, DaoException {
    	/**查询用户详情**/
		if(log.isDebugEnabled()) log.debug("6");
    	SysUser entity =  dao.selectByPrimaryKey(id);
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	UserVo vo = new UserVo(entity);
    	List<SysFinger> list = fingerDao.select(new SysFinger().setUserId(id));
    	vo.setFingers(list);
        return new ResultModel().setObj(vo);
    }

    /**
     * 添加用户
     * @param vo 
     * @return
     * @throws ServiceException 
     * @throws DaoException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    @Override
    public ResultModel add(UserVo vo, Principal p, boolean isEnable) throws ServiceException, DaoException {
    	SysUser entity = vo.getEntity();
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	Integer status = entity.getUserStatus();
    	entity.clear();
    	//判断编号是否重复
    	SysUser old = dao.selectOne(new SysUser().setUserNo(entity.getUserNo()).setOrgId(entity.getOrgId()));
//    	SysUser old = dao.selectOne(new SysUser().setUserNo(entity.getUserNo()).setOrgId(entity.getOrgId()).setIsEmployee(entity.getIsEmployee()));
    	if(old != null) throw new ServiceException(ErrorCode.NO_NOT_UNIQUE);
    	//添加用户信息
    	if (isEnable) {
        	entity.setUserStatus(Constant.STATUS_ACTIVE);
    	} else {
    		if (status != Constant.STATUS_ACTIVE) {
            	entity.setUserStatus(Constant.STATUS_OPEN);
    		} else {
    			entity.setUserStatus(Constant.STATUS_ACTIVE);
    		}
    	}
    	String id = IdUtil.getUUID();
    	entity.setUserId(id);
    	entity.setOrgId(p.getOrgId());
    	entity.setCreatePerson(p.getUserId());
    	if(log.isDebugEnabled()) log.debug("7");
    	int r = dao.insertSelective(entity);
    	if(r == 0) throw new DaoException(ErrorCode.NO_NOT_UNIQUE);
    	//批量添加用户指纹
    	SysFinger f;
    	if(vo.getFingers() != null) for(int i=0; i<vo.getFingers().size(); ++i) {
    		f = vo.getFingers().get(i);
    		if(StringUtils.isEmpty(f.getFingerPhoto()) || StringUtils.isEmpty(f.getFingerVein())) continue;
    		f.setFingerId(IdUtil.getUUID());
    		f.setUserId(id);
    		r = fingerDao.insert(f);
    		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    	}
    	//返回用户数据
    	if(log.isDebugEnabled()) log.debug("8");
        return view(id);
    }

    /**
     * 修改【用户和指纹】
     * @param vo 
     * @return
     * @throws ServiceException 
     * @throws DaoException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    @Override
    public ResultModel update(UserVo vo, Principal p, boolean isEnable) throws ServiceException, DaoException {
    	/**修改用户详细信息**/
    	SysUser entity = vo.getEntity();
    	if(entity == null) throw new ServiceException(ErrorCode.DATA_EMPTY);
    	entity.clear();
    	entity.setUpdatePerson(p.getUserId());
    	Integer status = entity.getUserStatus();
    	if(log.isDebugEnabled()) log.debug("9");
    	//检查编号是否唯一
    	SysUser old = dao.selectOne(new SysUser().setUserNo(entity.getUserNo()).setOrgId(p.getOrgId()));
    	if(old != null && !old.getUserId().equals(entity.getUserId())) throw new ServiceException(ErrorCode.NO_NOT_UNIQUE);
    	if (isEnable) {
    		entity.setUserStatus(Constant.STATUS_ACTIVE);
    	} else {
    		if (status != Constant.STATUS_ACTIVE) {
            	entity.setUserStatus(Constant.STATUS_OPEN);
    		} else {
    			entity.setUserStatus(Constant.STATUS_ACTIVE);
    		}
    	}
        int r = dao.updateByPrimaryKeySelective(entity);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    	//批量添加用户指纹，先删除然后新增
        r = fingerDao.delete(new SysFinger().setUserId(entity.getUserId()));
    	SysFinger f;
    	if(vo.getFingers() != null) for(int i=0; i<vo.getFingers().size(); ++i) {
    		f = vo.getFingers().get(i);
    		if(StringUtils.isEmpty(f.getFingerPhoto()) || StringUtils.isEmpty(f.getFingerVein())) continue;
    		f.setFingerId(IdUtil.getUUID());
    		f.setUserId(entity.getUserId());
    		r = fingerDao.insert(f);
    		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
    	}
        /**返回用户详细信息**/
    	if(log.isDebugEnabled()) log.debug("10");
        return view(entity.getUserId());
    }

    
    
    /**
     * 生效用户
     * @param id 
     * @return
     * @throws ServiceException 
     * @throws DaoException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel enable(String id) throws ServiceException, DaoException {
    	ResultModel m = new ResultModel();
    	if(log.isDebugEnabled()) log.debug("11");
    	SysUser old =  dao.selectByPrimaryKey(id);
    	if(old == null || old.getUserStatus() != Constant.STATUS_OPEN) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysUser obj = new SysUser();
    	obj.setUserId(id);
    	obj.setUserStatus(Constant.STATUS_ACTIVE);
    	if(log.isDebugEnabled()) log.debug("12");
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
    }

    /**
     * 失效用户
     * @param id 
     * @return
     * @throws DaoException 
     * @throws ServiceException 
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public ResultModel disable(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	if(log.isDebugEnabled()) log.debug("13");
    	SysUser old =  dao.selectByPrimaryKey(id);
    	if(old == null || old.getUserStatus() != Constant.STATUS_ACTIVE) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysUser obj = new SysUser();
    	obj.setUserId(id);
    	obj.setUserStatus(Constant.STATUS_OPEN);
    	if(log.isDebugEnabled()) log.debug("14");
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
    }

    /**
     * 取消用户
     */
    @Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel cancel(String id) throws DaoException, ServiceException {
    	ResultModel m = new ResultModel();
    	if(log.isDebugEnabled()) log.debug("15");
    	SysUser old =  dao.selectByPrimaryKey(id);
    	if(old == null || old.getUserStatus() != Constant.STATUS_OPEN) throw new ServiceException(ErrorCode.STATUS_NO_RIGHT);
    	SysUser obj = new SysUser();
    	obj.setUserId(id);
    	obj.setUserStatus(Constant.STATUS_CANCEL);
    	if(log.isDebugEnabled()) log.debug("16");
        int r = dao.updateByPrimaryKeySelective(obj);
        if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
        return m;
	}

    /**
     * 查询用户详情
     * @param id 用户id（UUID）
     * @return
     * @throws ServiceException 
     */
    @Override
	public SysUser get(String id) {
    	if(log.isDebugEnabled()) log.debug("17");
		return dao.selectByPrimaryKey(id);
	}

    /**
    * @Description: 根据用户名称模糊查询用户id列表
    * @param name
    * @return
    */
	@Override
	public List<String> list(String name) {
		if(StringUtils.isBlank(name)) name = "";
		return dao.list("%".concat(name).concat("%"));
	}

    /**
    * @Description: 修改用户信息
    * @param user
    * @return
    */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void update(SysUser user) throws DaoException, ServiceException {
		if(log.isDebugEnabled()) log.debug("18");
		int r = dao.updateByPrimaryKeySelective(user);
		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION); 
	}

	/**
	* @Description: 删除用户信息
	* @param id
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel delete(String id) throws DaoException, ServiceException {
		if(log.isDebugEnabled()) log.debug("19");
		int r = dao.deleteByPrimaryKey(id);
		if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
		return new ResultModel();
	}

	/**
	* @Description: 用户指纹采集
	* @param id 用户id
	* @param list 指纹列表
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultModel fingerCollect(UserVo vo) throws DaoException, ServiceException {
		//先批量删除然后新增
		fingerDao.delete(new SysFinger().setUserId(vo.getEntity().getUserId()));
		if(vo.getFingers() != null) for(int i=0; i<vo.getFingers().size(); ++i) {
			SysFinger f = vo.getFingers().get(i);
			if(f == null || StringUtils.isEmpty(f.getFingerPhoto())
				|| StringUtils.isEmpty(f.getFingerVein())) continue;
			f.setFingerId(IdUtil.getUUID());
			int r = fingerDao.insert(f);
			if(r == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
		}
		return new ResultModel();
	}

	/**
	* @Description: 指纹认证【在数据库中比对指纹，返回最匹配的用户和帐号信息】
	* @param fg
	* @return
	* @throws DaoException
	* @throws ServiceException
	 */
	@Override
	public SysFinger fingerVerify(SysFinger fg) throws DaoException, ServiceException {
		List<SysFinger> list = fingerDao.selectAll();
		if(list == null || list.size() == 0) return null;
		int fgScore = 0;
		int fvScore = 0;
		int tempScore = 0;
		SysFinger f;
		SysFinger r = null;
		for(int i=0; i<list.size(); ++i) {
			f = list.get(i);
			tempScore = ZKFVMatch.VerifyFP(f.getFingerPhoto(), fg.getFingerPhoto());
			if(tempScore > fgScore) {
				fgScore = tempScore;
				tempScore = ZKFVMatch.VerifyFV(f.getFingerVein(), fg.getFingerVein());
				if(tempScore > fvScore) {
					fvScore = tempScore;
					r = f;
				}
			}
		}
		if(fgScore >= 35 || fvScore >= 60) return r;
		return null;
	}

	/**
	* @Description: 根据属性查找唯一对象
	* @param user
	* @return
	 */
	@Override
	public SysUser query(SysUser user) {
		return dao.selectOne(user);
	}

	/**
	 * @param userId
	 * @param userStatus
	 * @throws DaoException
	 * @throws ServiceException
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月6日 下午6:13:22<br/>
	 * @author 王通<br/>
	 */
	@Override
	public void updateStatus(String userId, Integer userStatus) throws DaoException, ServiceException {
		SysUser user = new SysUser();
    	user.setUserId(userId);
    	user.setUserStatus(userStatus);
    	int ret = dao.updateByPrimaryKeySelective(user);
    	if(ret == 0) throw new DaoException(ErrorCode.DB_EXCEPTION);
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
	public ResultModel saveAndEnable(UserVo vo) throws Exception {
    	Principal p = LoginUtil.getLoginUser();
    	ResultModel r = null;
		if (StringUtil.isTrimEmpty(vo.getEntity().getUserId())) {
			r = this.add(vo, p, true);
		} else {
			r = this.update(vo, p, true);
		}
		return r;
	}

	/**
	 * @param user
	 * @param p
	 * @return
	 * @throws DaoException
	 * @throws ServiceException
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月21日 下午6:19:45<br/>
	 * @author 王通<br/>
	 */
	@Override
	public ResultModel add(UserVo vo, Principal p) throws DaoException, ServiceException {
		return this.add(vo, p, false);
	}

	/**
	 * @param user
	 * @param p
	 * @return
	 * @throws DaoException
	 * @throws ServiceException
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月21日 下午6:19:45<br/>
	 * @author 王通<br/>
	 */
	@Override
	public ResultModel update(UserVo vo, Principal p) throws DaoException, ServiceException {
		return this.update(vo, p, false);
	}

	@Override
	public List<SysUser> listAll() {
		SysUser entity = new SysUser();
		entity.setUserStatus(Constant.STATUS_ACTIVE);
		UserVo vo = new UserVo(entity);
		List<SysUser> list = dao.selectByExample(vo.getExample());
		return list;
	}
}