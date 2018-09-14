/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月14日下午2:52:33<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunkouan.saas.common.util.IdUtil;
import com.yunkouan.saas.modules.sys.dao.ISysParamDao;
import com.yunkouan.saas.modules.sys.entity.SysSystemParam;
import com.yunkouan.saas.modules.sys.service.ISysParamExtlService;
import com.yunkouan.saas.modules.sys.vo.SysParamVO;
import com.yunkouan.util.PubUtil;
import com.yunkouan.util.StringUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * 参数外调业务类<br/><br/>
 * @version 2017年3月14日下午2:52:33<br/>
 * @author andy wang<br/>
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SysParamExtlServiceImpl implements ISysParamExtlService {
	/** 日志类 <br/> add by andy wang */
	private Logger log = LoggerFactory.getLogger(SysParamExtlServiceImpl.class);

	/** 参数dao <br/> add by andy wang */
	@Autowired
	private ISysParamDao sysParamDao;

	/**
	 * 获取页面显示参数
	 * @param listCacheName 参数名
	 * @return 参数集合
	 * @throws Exception
	 * @version 2017年3月22日 下午2:39:54<br/>
	 * @author andy wang<br/>
	 */
	public Map<String,List<Map<String,Object>>> showParam ( List<String> listCacheName ) throws Exception {
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]*)?$");  
		Map<String,List<Map<String,Object>>> result = new HashMap<String,List<Map<String,Object>>>();
		for (String cacheName : listCacheName) {
			Map<String, String> cacheMap = get(cacheName);
			List<Map<String, Object>> listMapCache = new ArrayList<Map<String, Object>>();
			result.put(cacheName, listMapCache );
			if ( PubUtil.isEmpty(cacheMap) ) {
				continue;
			}
			for (String key : cacheMap.keySet()) {
				String value = cacheMap.get(key);
				Map<String, Object> mapCache = new HashMap<String, Object>();
				Matcher match=pattern.matcher(key);
				if ( match.matches() ) {
					mapCache.put("key", key);
				} else {
					mapCache.put("key", key);
				}
				mapCache.put("value", value);
				listMapCache.add(mapCache);
			}
		}
		return result;
	}
	
	
	/**
	 * 根据条件，分页查询参数
	 * @param SysParamVO 查询条件
	 * @return 查询结果
	 * @throws Exception
	 * @version 2017年3月14日下午2:52:33<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings("rawtypes")
	public Page listSysParamByPage(SysParamVO SysParamVO) throws Exception {
		if (SysParamVO == null || SysParamVO.getSysParam() == null) {
			log.error("insertSysParam --> SysParam or sysParam is null!");
			return null;
		}
//		Example example = SysParamVO.getExample();
		Page page = PageHelper.startPage(SysParamVO.getCurrentPage() + 1, SysParamVO.getPageSize());
		this.listSysParamByExample(SysParamVO);
		if (!PubUtil.isEmpty(page)) {
			for (Object obj : page) {
				SysSystemParam SysParam = (SysSystemParam) obj;
				SysParam.clear();
			}
		}
		return page;
	}

	/**
	 * 保存参数
	 * @param SysParam 要保存的参数对象
	 * @return 保存后的参数对象
	 * @throws Exception
	 * @version 2017年3月14日下午2:52:33<br/>
	 * @author andy wang<br/>
	 */
	public SysSystemParam insertSysParam(SysSystemParam SysParam, String loginUserId, Integer id2) throws Exception {
		if (SysParam == null) {
			log.error("insertSysParam --> SysParam is null!");
			return null;
		}
		if (StringUtil.isTrimEmpty(SysParam.getParamId())) {
			SysParam.setParamId(IdUtil.getUUID());
		}
		SysParam.setCreatePerson(loginUserId);
		SysParam.setUpdatePerson(loginUserId);
		SysParam.setCreateTime(null);
		SysParam.setUpdateTime(null);
		SysParam.setParamId2(id2);
		this.sysParamDao.insertSelective(SysParam);
		return SysParam;
	}

	/**
	 * 更新参数
	 * @param sysParamVO 更新的参数内容
	 * @return 更新的数量
	 * @throws Exception
	 * @version 2017年3月14日下午2:52:33<br/>
	 * @author andy wang<br/>
	 */
	public int updateSysParam(SysParamVO sysParamVO, String loginUserId) throws Exception {
		if (sysParamVO == null || sysParamVO.getSysParam() == null) {
			log.error("updateSysParam --> sysParamVO or sysParam is null!");
			return 0;
		}
		SysSystemParam sysParam = sysParamVO.getSysParam();
		// 拼接Where条件
		SysParamVO SysParamVO = new SysParamVO();
		SysParamVO.getSysParam().setParamId(sysParam.getParamId());
		Example example = SysParamVO.getExample();
		// 拼接更新内容
		sysParam.setParamId2(null);
		sysParam.setCreatePerson(null);
		sysParam.setCreateTime(null);
		sysParam.setUpdatePerson(loginUserId);
		sysParam.setUpdateTime(new Date());
		return this.sysParamDao.updateByExampleSelective(sysParam, example);
	}

	/**
	 * 根据参数id，更新参数状态
	 * @param sysParamId 参数id
	 * @param status 参数状态
	 * @return 更新的数量
	 * @throws Exception
	 * @version 2017年3月14日下午2:52:33<br/>
	 * @author andy wang<br/>
	 */
	public int updateSysParamStatusById(String sysParamId, Integer status, String loginUserId) throws Exception {
		if (StringUtil.isTrimEmpty(sysParamId)) {
			log.error("updateSysParamStatusById --> sysParamId is null!");
			return 0;
		}
		// 设置更新条件
		SysParamVO SysParamVO = new SysParamVO();
		SysParamVO.getSysParam().setParamId(sysParamId);
		Example example = SysParamVO.getExample();
		// 设置更新内容
		SysSystemParam record = new SysSystemParam();
		record.setParamStatus(status);
		record.setUpdatePerson(loginUserId);
		record.setUpdateTime(new Date());
		return this.sysParamDao.updateByExampleSelective(record, example);
	}

	/**
	 * 根据参数id，查询参数信息
	 * @param sysParamId 参数id
	 * @return 参数信息
	 * @throws Exception
	 * @version 2017年3月14日下午2:52:33<br/>
	 * @author andy wang<br/>
	 */
	public SysSystemParam findSysParamById(String sysParamId) throws Exception {
		if (StringUtil.isTrimEmpty(sysParamId)) {
			log.error("findSysParamById --> sysParamId is null!");
			return null;
		}
		SysParamVO SysParamVO = new SysParamVO();
		SysParamVO.getSysParam().setParamId(sysParamId);
		List<SysSystemParam> listSysParam = this.listSysParamByExample(SysParamVO);
		return PubUtil.isEmpty(listSysParam) ? null : listSysParam.get(0);
	}

	/**
	 * 根据条件，查询参数
	 * @param SysParamVO 查询条件
	 * @return 参数对象
	 * @throws Exception
	 * @version 2017年3月14日下午2:52:33<br/>
	 * @author andy wang<br/>
	 */
	public List<SysSystemParam> listSysParamByExample(SysParamVO SysParamVO) throws Exception {
		if (SysParamVO == null || SysParamVO.getSysParam() == null) {
			log.error("listSysParamByExample --> SysParamVO or sysParam is null!");
			return null;
		}
		Example example = SysParamVO.getExample();
		return this.sysParamDao.selectByExample(example);
	}

	@Override
	public Map<String, String> get(String name) {
		Map<String, String> map = new HashMap<String, String>();
		List<SysSystemParam> list = sysParamDao.selectAll();
		for(SysSystemParam p : list) {
			if(name.equals(p.getParamName())) map.put(p.getParamKey(), p.getParamValue());
		}
		return map;
	}
}