/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月14日下午2:37:25<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.yunkouan.exception.BizException;
import com.yunkouan.saas.common.util.IdUtil;
import com.yunkouan.saas.modules.sys.dao.ICityDao;
import com.yunkouan.saas.modules.sys.dao.ICountryDao;
import com.yunkouan.saas.modules.sys.dao.ISysParamDao;
import com.yunkouan.saas.modules.sys.entity.City;
import com.yunkouan.saas.modules.sys.entity.Country;
import com.yunkouan.saas.modules.sys.entity.SysSystemParam;
import com.yunkouan.saas.modules.sys.service.ISysParamExtlService;
import com.yunkouan.saas.modules.sys.service.ISysParamService;
import com.yunkouan.saas.modules.sys.vo.CityVO;
import com.yunkouan.saas.modules.sys.vo.CountryVO;
import com.yunkouan.saas.modules.sys.vo.CountyVO;
import com.yunkouan.saas.modules.sys.vo.ProvinceVO;
import com.yunkouan.saas.modules.sys.vo.SysParamVO;
import com.yunkouan.util.PubUtil;
import com.yunkouan.util.StringUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * 参数业务类<br/><br/>
 * @version 2017年3月14日下午2:37:25<br/>
 * @author andy wang<br/>
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class SysParamServiceImpl implements ISysParamService {
	/** 日志对象 <br/> add by andy wang */
	private Logger log = LoggerFactory.getLogger(SysParamServiceImpl.class);

	/** 参数外调业务类 <br/> add by andy */
	@Autowired
	private ISysParamExtlService paramExtlService;
	@Autowired
	private ISysParamDao sysParamDao;

	/** 外调 <br/> add by 王通 */
	@Autowired
	private ICityDao cityDao;
	/** 外调 <br/> add by  王通 */
	@Autowired
	private ICountryDao countryDao;

	/**
	 * 更新参数
	 * @param param_paramVO 参数信息
	 * @throws Exception
	 * @version 2017年3月14日下午2:37:25<br/>
	 * @author andy wang<br/>
	 */
	public void updateSysParam(SysParamVO param_paramVO, String loginUserId) throws Exception {
		if (param_paramVO == null || param_paramVO.getSysParam() == null) {
			log.error("updateSysParam --> param_paramVO or param is null!");
			throw new NullPointerException("parameter is null!");
		}
		SysSystemParam param_param = param_paramVO.getSysParam();
		SysSystemParam param = this.paramExtlService.findSysParamById(param_param.getParamId());
		if (param.getParamStatus() == null || 10 != param.getParamStatus()) {
			throw new BizException("err_main_param_update_statusNotOpen");
		}
		// 更新仓库
		this.paramExtlService.updateSysParam(param_paramVO, loginUserId);
	}

	/**
	 * 失效参数
	 * @param param_listSysParamId 参数id集合
	 * @throws Exception
	 * @version 2017年3月14日下午2:37:25<br/>
	 * @author andy wang<br/>
	 */
	public void inactiveSysParam(List<String> param_listSysParamId, String loginUserId) throws Exception {
		if (param_listSysParamId == null || param_listSysParamId.isEmpty()) {
			log.error("inactiveSysParam --> param_listSysParamId is null!");
			throw new NullPointerException("parameter is null!");
		}

		for (int i = 0; i < param_listSysParamId.size(); i++) {
			String paramId = param_listSysParamId.get(i);
			if (StringUtil.isTrimEmpty(paramId)) {
				throw new NullPointerException("parameter id is null");
			}
			// 查询仓库
			SysSystemParam param = this.paramExtlService.findSysParamById(paramId);
			if (param == null) {
				throw new BizException("err_main_param_null");
			}

			if (20 != param.getParamStatus()) {
				throw new BizException("err_main_param_inactive_statusNotActive");
			}

			// 更新状态
			this.paramExtlService.updateSysParamStatusById(paramId, 10, loginUserId);
		}
	}

	/**
	 * 生效参数
	 * @param param_listSysParamId 参数id集合
	 * @throws Exception
	 * @version 2017年3月14日下午2:37:25<br/>
	 * @author andy wang<br/>
	 */
	public void activeSysParam(List<String> param_listSysParamId, String loginUserId) throws Exception {
		if (param_listSysParamId == null || param_listSysParamId.isEmpty()) {
			log.error("activeSysParam --> param_listSysParamId is null!");
			throw new NullPointerException("parameter is null!");
		}

		for (int i = 0; i < param_listSysParamId.size(); i++) {
			String paramId = param_listSysParamId.get(i);
			if (StringUtil.isTrimEmpty(paramId)) {
				throw new NullPointerException("parameter id is null");
			}
			// 查询仓库
			SysSystemParam param = this.paramExtlService.findSysParamById(paramId);
			if (param == null) {
				throw new BizException("err_main_param_null");
			}

			if (10 != param.getParamStatus()) {
				throw new BizException("err_main_param_active_statusNotOpen");
			}

			// 更新状态
			this.paramExtlService.updateSysParamStatusById(paramId, 20, loginUserId);
		}
	}

	/**
	 * 根据参数id，查询参数信息
	 * @param param_paramId 参数id
	 * @return 参数信息
	 * @throws Exception
	 * @version 2017年3月14日下午2:37:25<br/>
	 * @author andy wang<br/>
	 */
	@Transactional(readOnly = true)
	public SysParamVO viewSysParam(String param_paramId) throws Exception {
		if (StringUtil.isTrimEmpty(param_paramId)) {
			log.error("viewSysParam --> param_paramId is null!");
			throw new NullPointerException("parameter is null!");
		}
		// 根据id，查询参数
		SysSystemParam param = this.paramExtlService.findSysParamById(param_paramId);
		if (param == null) {
			// 参数不存在
			throw new BizException("err_main_param_null");
		}
		return new SysParamVO(param);
	}

	/**
	 * 保存参数
	 * @param param_paramVO 要保存的参数
	 * @return 保存后的参数（包含id）
	 * @throws Exception
	 * @version 2017年3月14日下午2:37:25<br/>
	 * @author andy wang<br/>
	 */
	public SysParamVO insertSysParam(SysParamVO param_paramVO, String loginUserId, Integer id2) throws Exception {
		if (param_paramVO == null || param_paramVO.getSysParam() == null) {
			log.error("insertSysParam --> param_paramVO is null!");
			throw new NullPointerException("parameter is null!");
		}
		SysSystemParam param = param_paramVO.getSysParam();
		// 设置id
		String paramId = IdUtil.getUUID();
		param.setParamId(paramId);
		// 设置默认状态
		param.setParamStatus(20);
		// 保存参数
		SysSystemParam SysParam = this.paramExtlService.insertSysParam(param, loginUserId, id2);
		if (SysParam == null) {
			return null;
		}
		return new SysParamVO(SysParam);
	}

	/**
	 * 根据条件，分页查询参数
	 * @param param_SysParamVO 查询条件
	 * @return 查询结果
	 * @throws Exception
	 * @version 2017年3月14日下午2:37:25<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public Page listSysParam(SysParamVO param_SysParamVO) throws Exception {
		if (param_SysParamVO == null) {
			param_SysParamVO = new SysParamVO();
		}
		if (param_SysParamVO.getSysParam() == null) {
			param_SysParamVO.setSysParam(new SysSystemParam());
		}
		Page page = this.paramExtlService.listSysParamByPage(param_SysParamVO);
		if (PubUtil.isEmpty(page)) {
			return null;
		}
		List<SysParamVO> listSysParamVO = new ArrayList<SysParamVO>();
		for (Object obj : page) {
			SysSystemParam SysParam = (SysSystemParam) obj;
			listSysParamVO.add(searchCache(new SysParamVO(SysParam)));
		}
		page.clear();
		page.addAll(listSysParamVO);
		return page;
	}

	/**
	 * 简单包装国家、省、市、区县
	 * @return
	 * @throws Exception
	 * @required 
	 * @optional  
	 * @Description 
	 * @version 2017年6月9日 下午1:48:16<br/>
	 * @author 王通<br/>
	 */
	@Override
	public List<CountryVO> listCity() throws Exception {
		//根据当前数据库结构，构造了一个简单的包装方法 by 王通
		List<CountryVO> countryVoList = new ArrayList<CountryVO>();
		List<Country> countryList = countryDao.selectAll();
		for (Country c : countryList) {
			CountryVO cVo = new CountryVO(c.getCounCName());
			if (cVo.getCountryName().equals("中国")) {
				List<ProvinceVO> provinceList = new ArrayList<ProvinceVO>();
				Example example = new Example(City.class);
				example.setOrderByClause("id asc");
				List<City> cityList = cityDao.selectByExample(example);
				ProvinceVO nowPVo = null;
				CityVO nowCityVo = null;
				for (City city : cityList) {
					if (city.getLevel().equals("1")) {
						nowPVo = new ProvinceVO();
						nowPVo.setProvinceName(city.getCnName());
						provinceList.add(nowPVo);
					} else if (city.getLevel().equals("2")) {
						nowCityVo = new CityVO();
						nowCityVo.setCityName(city.getCnName());
						nowPVo.addCity(nowCityVo);
					} else if (city.getLevel().equals("3")) {
						CountyVO CountyVo = new CountyVO();
						CountyVo.setCountyName(city.getCnName());
						nowCityVo.addCounty(CountyVo);
					}
				}
				cVo.setProvinceList(provinceList);
			}
			countryVoList.add(cVo);
		}
		
		return countryVoList;
	}

	@Override
	public String getValue(String name, Object key) {
		List<SysSystemParam> list = sysParamDao.selectAll();
		String key1 = String.valueOf(key);
		for(SysSystemParam p : list) {
			if(name.equals(p.getParamName()) && p.getParamKey().equals(key1)) return p.getParamValue();
			if(!name.equals(p.getParamName()) || StringUtils.isBlank(p.getReverse())) continue;
			//查询reverse
			SysSystemParam p1 = new SysSystemParam();
			p1.setParamName(p.getReverse());
			List<SysSystemParam> list1 = sysParamDao.select(p1);
			for(SysSystemParam p2 : list1) {
				if(key1.equals(p2.getParamValue())) return p2.getParamKey();
			}
		}
		return "";
	}

	@Override
	public String getKey(String name) {
		List<SysSystemParam> list = sysParamDao.selectAll();
		for(SysSystemParam p : list) {
			if(name.equals(p.getParamName())) return p.getParamKey();
		}
		return "";
	}

	@Override
	public SysParamVO searchCache(SysParamVO vo) {
		if (vo.getSysParam() == null) return vo;
		if (vo.getSysParam().getParamStatus() == null) return vo;
		vo.setSysParamStatusComment(this.getValue("PARAMSTATUS", vo.getSysParam().getParamStatus()));
		return vo;
	}
}