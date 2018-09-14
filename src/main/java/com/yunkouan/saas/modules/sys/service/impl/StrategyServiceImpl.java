package com.yunkouan.saas.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yunkouan.saas.modules.sys.dao.IOrgStrategyDao;
import com.yunkouan.saas.modules.sys.dao.IStrategyTypeDao;
import com.yunkouan.saas.modules.sys.dao.IStrategyImplDao;
import com.yunkouan.saas.modules.sys.entity.SysOrgStrategy;
import com.yunkouan.saas.modules.sys.entity.SysStrategyType;
import com.yunkouan.saas.modules.sys.entity.SysStrategyImpl;
import com.yunkouan.saas.modules.sys.service.IStrategyService;

@Service
@Transactional(readOnly=false)
public class StrategyServiceImpl implements IStrategyService {
	@Autowired
	private IOrgStrategyDao orgStraDao;
	@Autowired
	private IStrategyTypeDao straDao;
	@Autowired
	private IStrategyImplDao straImplDao;

	@Override
	public String getStrategy(String orgid, String strategyNo) {
		return getStrategy(orgid, null, strategyNo);
	}

	@Override
	public String getStrategy(String orgid, String warehouseid, String strategyNo) {
		//查询策略实现列表
		SysStrategyType strategy = straDao.selectOne(new SysStrategyType().setStrategyNo(strategyNo));
		List<SysStrategyImpl> strlist = straImplDao.select(new SysStrategyImpl().setStrategyId(strategy.getStrategyId()));
		SysStrategyImpl def = null;
		if(strlist != null) for(SysStrategyImpl str : strlist) {
			//查询策略授权
			SysOrgStrategy obj = orgStraDao.selectOne(new SysOrgStrategy().setOrgId(orgid).setWarehouseId(warehouseid).setStrategyImplId(str.getStrategyId()));
			if(obj != null) return str.getStrategyImplNo();
			if(str.getStrategyDefalut() == 1) def = str;
		};
		if(def == null) return null;
		return def.getStrategyImplNo();
	}
}