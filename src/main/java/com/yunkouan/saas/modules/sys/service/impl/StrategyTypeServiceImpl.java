package com.yunkouan.saas.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunkouan.exception.BizException;
import com.yunkouan.saas.common.constant.ErrorCode;
import com.yunkouan.saas.common.util.IdUtil;
import com.yunkouan.saas.common.vo.Principal;
import com.yunkouan.saas.modules.sys.dao.IStrategyTypeDao;
import com.yunkouan.saas.modules.sys.entity.SysStrategyType;
import com.yunkouan.saas.modules.sys.service.IStrategyTypeService;
import com.yunkouan.saas.modules.sys.vo.StrategyTypeVo;

/** 
* @Description: 策略分类service implement
* @author tphe06
* @date 2017年10月17日 下午1:57:08  
*/
@Service
@Transactional(readOnly=true, rollbackFor=Exception.class)
public class StrategyTypeServiceImpl implements IStrategyTypeService {
	@Autowired
	private IStrategyTypeDao dao;

	/** 
	* @Title: list 
	* @Description: 页面列表查询（分页）
	* @param @param vo
	* @param @param p
	* @param @return
	* @throws 
	*/
	@Override
	public List<StrategyTypeVo> list(StrategyTypeVo vo, Principal p) {
		Page<SysStrategyType> page = PageHelper.startPage(vo.getCurrentPage()+1, vo.getPageSize());
		List<StrategyTypeVo> list = select(vo, p);
		vo.setPageCount(page.getPages());//返回分页参数
		vo.setTotalCount(page.getTotal());
		return list;
	}

	/** 
	* @Title: list 
	* @Description: 页面列表查询（不分页）
	* @param @param vo
	* @param @param p
	* @param @return
	* @throws 
	*/
	@Override
	public List<StrategyTypeVo> select(StrategyTypeVo vo, Principal p) {
		List<SysStrategyType> list = dao.selectByExample(vo.getExample());
		List<StrategyTypeVo> voList = chg(list);
		return voList;
	}

	/**
	 * 批量把实体类转化成VO类
	 * @param list
	 * @return
	 */
	private List<StrategyTypeVo> chg(List<SysStrategyType> list) {
		if(list == null) return null;
		List<StrategyTypeVo> voList = new ArrayList<StrategyTypeVo>();
		for(SysStrategyType c : list) {
			voList.add(chg(c));
		}
		return voList;
	}
	/**
	 * 把实体类转化成VO类，补充扩展属性
	 * @param c
	 * @return
	 */
	private StrategyTypeVo chg(SysStrategyType c) {
		if(c == null) return null;
		StrategyTypeVo v = new StrategyTypeVo(c);
		v.getEntity().clear();
		v.retset();
		return v;
	}

	/** 
	* @Title: query 
	* @Description: 根据属性查询实体
	* @param @param entity
	* @param @return
	* @throws 
	*/
	@Override
	public List<SysStrategyType> query(SysStrategyType entity) {
		return dao.select(entity);
	}

	/** 
	* @Title: get 
	* @Description: 根据主键获取单个实体
	* @param @param id
	* @param @return
	* @throws 
	*/
	@Override
	public SysStrategyType get(String id) {
		return dao.selectByPrimaryKey(id);
	}

	/** 
	* @Title: view 
	* @Description: 页面查看详情
	* @param @param id
	* @param @return
	* @throws 
	*/
	@Override
	public StrategyTypeVo view(String id) {
		SysStrategyType entity = dao.selectByPrimaryKey(id);
		return chg(entity);
	}

	/** 
	* @Title: insert 
	* @Description: 新增方法
	* @param @param vo
	* @param @param p
	* @param @return
	* @throws 
	*/
	@Override
	@Transactional(readOnly = false)
	public int insert(StrategyTypeVo vo, Principal p) {
		vo.getEntity().setStrategyId(IdUtil.getUUID());
		vo.getEntity().setCreatePerson(p.getUserId());
		vo.getEntity().setCreateTime(new Date());
		int result = dao.insertSelective(vo.getEntity());
		if(result == 0) throw new BizException(ErrorCode.DB_EXCEPTION);
		return result;
	}

	/** 
	* @Title: update 
	* @Description: 修改方法
	* @param @param vo
	* @param @param p
	* @param @return
	* @throws 
	*/
	@Override
	@Transactional(readOnly = false)
	public int update(StrategyTypeVo vo, Principal p) {
		vo.getEntity().setUpdatePerson(p.getUserId());
		vo.getEntity().setUpdateTime(new Date());
		int result = dao.updateByPrimaryKeySelective(vo.getEntity());
		if(result == 0) throw new BizException(ErrorCode.DB_EXCEPTION);
		return result;
	}

	/** 
	* @Title: delete 
	* @Description: 删除方法
	* @param @param id
	* @throws 
	*/
	@Override
	@Transactional(readOnly = false)
	public int delete(String id) {
		int result = dao.deleteByPrimaryKey(id);
		if(result == 0) throw new BizException(ErrorCode.DB_EXCEPTION);
		return result;
	}

	@Override
	public SysStrategyType queryOne(SysStrategyType entity) {
		return dao.selectOne(entity);
	}

	@Override
	public long count(StrategyTypeVo vo, Principal p) {
		return dao.selectCountByExample(vo.getExample());
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String[] ids) {
		if(ids == null) return;
		for(String id : ids) {
			this.delete(id);
		}
	}
}