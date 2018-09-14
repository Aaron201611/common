package com.yunkouan.saas.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.yunkouan.exception.BizException;
import com.yunkouan.saas.common.util.IdUtil;
import com.yunkouan.saas.modules.sys.dao.IWarehouseDao;
import com.yunkouan.saas.modules.sys.entity.MetaWarehouse;
import com.yunkouan.saas.modules.sys.service.ISysParamService;
import com.yunkouan.saas.modules.sys.service.IWarehouseExtlService;
import com.yunkouan.saas.modules.sys.service.IWarehouseService;
import com.yunkouan.saas.modules.sys.vo.MetaWarehouseVO;
import com.yunkouan.util.PubUtil;
import com.yunkouan.util.StringUtil;

/**
 * 仓库服务实现类
 */
@Service
@Transactional(readOnly=false,rollbackFor=Exception.class)
public class WarehouseServiceImpl implements IWarehouseService {
	/** 日志类 <br/> add by andy wang */
	private Logger log = LoggerFactory.getLogger(WarehouseServiceImpl.class);
	
	/** 仓库业务类 <br/> add by andy wang */
	@Autowired
	private IWarehouseExtlService wrhExtlService;
	
	@Autowired
	private ISysParamService paramService;

	@Autowired
	private IWarehouseDao dao;

	/**
	 * 更新并生效仓库
	 * @param param_warehouseVO 仓库对象
	 * @return 生效后的仓库
	 * @throws Exception
	 * @version 2017年6月19日 下午2:09:06<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouseVO updateAndEnable ( MetaWarehouseVO param_warehouseVO, String loginUserId, String loginOrgId) throws Exception {
		if ( param_warehouseVO == null || param_warehouseVO.getWarehouse() == null ) {
    		log.error("updateAndEnable --> param_warehouseVO is null!");
    		throw new NullPointerException("parameter is null!");
    	}
		this.updateWrh(param_warehouseVO, loginUserId, loginOrgId);
		this.activeWrh(Arrays.asList(param_warehouseVO.getWarehouse().getWarehouseId()), loginUserId, loginOrgId);
		param_warehouseVO.getWarehouse().setWarehouseStatus(20);
		return param_warehouseVO;
	}
	
	/**
	 * 保存并生效仓库
	 * @param param_warehouseVO 仓库对象
	 * @return 生效后的仓库
	 * @throws Exception
	 * @version 2017年6月19日 下午2:09:06<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouseVO addAndEnable ( MetaWarehouseVO param_warehouseVO, String loginUserId, String loginOrgId) throws Exception {
		if ( param_warehouseVO == null || param_warehouseVO.getWarehouse() == null ) {
    		log.error("addAndEnable --> param_warehouseVO is null!");
    		throw new NullPointerException("parameter is null!");
    	}
		MetaWarehouseVO insertWrhVO = this.insertWrh(param_warehouseVO, loginUserId, loginOrgId);
		if ( insertWrhVO == null 
				|| StringUtil.isTrimEmpty(insertWrhVO.getWarehouse().getWarehouseId()) ) {
			throw new NullPointerException("addAndEnable return is null!");
		}
		this.activeWrh(Arrays.asList(insertWrhVO.getWarehouse().getWarehouseId()), loginUserId, loginOrgId);
		insertWrhVO.getWarehouse().setWarehouseStatus(20);
		return insertWrhVO;
	}
	
	/**
	 * 查询页面显示仓库
	 * @param param_warehouseVO 查询条件
	 * @return
	 * @throws Exception
	 * @version 2017年4月4日 上午9:52:49<br/>
	 * @author andy wang<br/>
	 */
	public List<MetaWarehouseVO> showWrh ( MetaWarehouseVO param_warehouseVO, String loginOrgId ) throws Exception {
		if ( param_warehouseVO == null ) {
			log.error("showWrh --> param_warehouseVO or warehouse is null!");
    		throw new NullPointerException("parameter is null!");
		}
		if ( param_warehouseVO.getWarehouse() == null ) {
			param_warehouseVO.setWarehouse(new MetaWarehouse()); 
		}
		param_warehouseVO.getWarehouse().setWarehouseStatus(20);
//		param_warehouseVO.getWarehouse().setWarehouseId(loginUser.getWarehouseId());
		List<MetaWarehouse> listWrh = this.wrhExtlService.listWareHouseByExample(param_warehouseVO, loginOrgId);
		if ( PubUtil.isEmpty(listWrh) ) {
			return null;
		}
		List<MetaWarehouseVO> listWrhVO = new ArrayList<MetaWarehouseVO>();
		for (MetaWarehouse metaWarehouse : listWrh) {
			listWrhVO.add(new MetaWarehouseVO(metaWarehouse));
		}
		return listWrhVO;
	}

	/**
     * 更新仓库
     * @param param_warehouseVO 仓库信息
     * @throws Exception
     * @version 2017年2月18日 下午2:44:59<br/>
     * @author andy wang<br/>
     */
    public void updateWrh ( MetaWarehouseVO param_warehouseVO, String loginUserId, String loginOrgId ) throws Exception {
    	if ( param_warehouseVO == null || param_warehouseVO.getWarehouse() == null ) {
			log.error("updateWrh --> param_warehouseVO or warehouse is null!");
    		throw new NullPointerException("parameter is null!");
		}
    	MetaWarehouse existsWarehouse = this.wrhExtlService.findWareHouseByNo(param_warehouseVO.getWarehouse().getWarehouseNo(), param_warehouseVO.getWarehouse().getWarehouseId(), loginOrgId);
    	if ( existsWarehouse != null ) {
			throw new BizException("err_main_warehouse_no_exists");
		}
    	MetaWarehouse param_warehouse = param_warehouseVO.getWarehouse();
    	MetaWarehouse warehouse = this.wrhExtlService.findWareHouseById(param_warehouse.getWarehouseId());
    	if ( warehouse == null ) {
    		throw new BizException("err_main_warehouse_null");
    	}
    	if ( warehouse.getWarehouseStatus() == null || 10 != warehouse.getWarehouseStatus() ) {
    		throw new BizException("err_main_warehouse_update_statusNotOpen");
    	}
    	// 更新仓库
    	this.wrhExtlService.updateWrh(param_warehouseVO, loginUserId, loginOrgId);
    }
	
	/**
	 * 失效仓库
	 * @param param_listWrhId 仓库id集合
	 * @throws Exception
	 * @version 2017年3月11日 下午2:17:57<br/>
	 * @author andy wang<br/>
	 */
    public void inactiveWrh ( List<String> param_listWrhId, String loginUserId, String loginOrgId ) throws Exception {
    	if ( param_listWrhId == null || param_listWrhId.isEmpty() ) {
    		log.error("inactiveWrh --> param_listWrhId is null!");
    		throw new NullPointerException("parameter is null!");
    	}
    	
    	for (int i = 0; i < param_listWrhId.size(); i++) {
    		String wrhId = param_listWrhId.get(i);
    		if ( StringUtil.isTrimEmpty(wrhId) ) {
    			throw new NullPointerException("parameter id is null");
    		}
    		// 查询仓库
        	MetaWarehouse warehouse = this.wrhExtlService.findWareHouseById(wrhId);
        	if ( warehouse == null ) {
        		throw new BizException("err_main_warehouse_null");
        	}
        	
        	if ( 20 != warehouse.getWarehouseStatus() ) {
        		throw new BizException("err_main_warehouse_inactive_statusNotActive");
        	}

			// 20170619 林总决定如果仓库底下有生效的库区，则不能对仓库进行失效操作
        	// 20170620 林总决定为方便用户操作，取消这段逻辑
//        	MetaAreaVO p_areaVO = new MetaAreaVO();
////        	p_areaVO.getArea().setAreaStatus(Constant.AREA_STATUS_ACTIVE);
//        	p_areaVO.getArea().setWarehouseId(wrhId);
//        	List<MetaArea> listArea = this.areaExtlService.listAreaByExample(p_areaVO);
//        	if ( listArea != null && listArea.size() > 0 ) {
//        		throw new BizException("err_main_warehouse_inactive_hasArea");
//        	}
        	
        	// 更新状态
        	this.wrhExtlService.updateWrhStatusById(wrhId, 10, loginUserId, loginOrgId);
		}
    }
	
	
	/**
	 * 生效仓库
	 * @param param_listWrhId 仓库id集合
	 * @throws Exception
	 * @version 2017年3月11日 下午2:16:53<br/>
	 * @author andy wang<br/>
	 */
    public void activeWrh ( List<String> param_listWrhId, String loginUserId, String loginOrgId ) throws Exception {
    	if ( param_listWrhId == null || param_listWrhId.isEmpty() ) {
    		log.error("activeWrh --> param_listWrhId is null!");
    		throw new NullPointerException("parameter is null!");
    	}
    	
    	for (int i = 0; i < param_listWrhId.size(); i++) {
    		String wrhId = param_listWrhId.get(i);
    		if ( StringUtil.isTrimEmpty(wrhId) ) {
    			throw new NullPointerException("parameter id is null");
    		}
    		// 查询仓库
        	MetaWarehouse warehouse = this.wrhExtlService.findWareHouseById(wrhId);
        	if ( warehouse == null ) {
        		throw new BizException("err_main_warehouse_null");
        	}
        	
        	if ( 10 != warehouse.getWarehouseStatus() ) {
        		throw new BizException("err_main_warehouse_active_statusNotOpen");
        	}
        	
        	// 更新状态
        	this.wrhExtlService.updateWrhStatusById(wrhId, 20, loginUserId, loginOrgId);
		}
    }
	
	/**
	 * 根据仓库id，查询仓库信息
	 * @param param_wrhId 仓库id
	 * @return 仓库信息
	 * @throws Exception
	 * @version 2017年3月11日 下午1:57:06<br/>
	 * @author andy wang<br/>
	 */
    @Transactional(readOnly=true)
	public MetaWarehouseVO viewWrh ( String param_wrhId ) throws Exception {
		if ( StringUtil.isTrimEmpty(param_wrhId) ) {
    		log.error("viewWrh --> param_wrhId is null!");
    		throw new NullPointerException("parameter is null!");
    	}
		
		// 根据id，查询仓库
		MetaWarehouse warehouse = this.wrhExtlService.findWareHouseById(param_wrhId);
		if ( warehouse == null ) {
			// 仓库不存在
			throw new BizException("err_main_warehouse_null");
		}
		return searchCache(new MetaWarehouseVO(warehouse));
	}
	
    /**
	 * 根据仓库id，查询仓库信息
	 * @param param_wrhId 仓库id
	 * @return 仓库信息
	 * @throws Exception
	 * @version 2017年3月11日 下午1:57:06<br/>
	 * @author andy wang<br/>
	 */
    @Transactional(readOnly=true)
	public MetaWarehouseVO viewFirstWrh (String loginOrgId) throws Exception {
		// 根据id，查询仓库
		MetaWarehouse warehouse = this.wrhExtlService.findWareHouseByOrgId(loginOrgId);
		if ( warehouse == null ) {
			// 仓库不存在
			throw new BizException("err_main_warehouse_null");
		}
		return searchCache(new MetaWarehouseVO(warehouse));
	}
    
	/**
	 * 根据条件，分页查询仓库
	 * @param param_metaWarehouseVO 查询条件
	 * @return 查询结果
	 * @throws Exception
	 * @version 2017年3月11日 下午1:33:09<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly=true)
	public Page listWrh ( MetaWarehouseVO param_metaWarehouseVO, String loginOrgId) throws Exception {
		if ( param_metaWarehouseVO == null ) {
			param_metaWarehouseVO = new MetaWarehouseVO();
		}
		if ( param_metaWarehouseVO.getWarehouse() == null ) {
			param_metaWarehouseVO.setWarehouse(new MetaWarehouse());
		}
		Page page = this.wrhExtlService.listWrhByPage(param_metaWarehouseVO, loginOrgId);
		if ( PubUtil.isEmpty(page) ) {
			return null;
		}
		List<MetaWarehouseVO> listWrhVO = new ArrayList<MetaWarehouseVO>();
		for (Object obj : page) {
			MetaWarehouse metaWarehouse = (MetaWarehouse) obj;
			listWrhVO.add(searchCache(new MetaWarehouseVO(metaWarehouse)));
		}
		page.clear();
		page.addAll(listWrhVO);
		return page;
	}
	
	
	/**
	 * 保存仓库
	 * @param param_warehouseVO 要保存的仓库
	 * @return 保存后的仓库（包含id）
	 * @throws Exception
	 * @version 2017年3月11日 上午9:43:25<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouseVO insertWrh ( MetaWarehouseVO param_warehouseVO, String loginUserId, String loginOrgId) throws Exception {
		if ( param_warehouseVO == null || param_warehouseVO.getWarehouse() == null ) {
    		log.error("insertWrh --> param_warehouseVO is null!");
    		throw new NullPointerException("parameter is null!");
    	}
		MetaWarehouse warehouse = param_warehouseVO.getWarehouse();
		// 查询仓库编号是否有重复
		MetaWarehouse existsWarehouse = this.wrhExtlService.findWareHouseByNo(warehouse.getWarehouseNo(), null, loginOrgId);
		if ( existsWarehouse != null ) {
			throw new BizException("err_main_warehouse_no_exists");
		}
		// 设置id
		String warehouseId = IdUtil.getUUID();
		warehouse.setWarehouseId(warehouseId);
		// 设置默认状态
		warehouse.setWarehouseStatus(10);
		// 保存仓库
		MetaWarehouse metaWarehouse = this.wrhExtlService.insertWrh(warehouse, loginOrgId, loginUserId);
		if ( metaWarehouse == null ) {
			return null;
		}
		return new MetaWarehouseVO(metaWarehouse);
	}

	@Override
	public List<MetaWarehouse>getWarehouseList(MetaWarehouseVO vo){
		if(vo.getWarehouse().getOrgId()==null) return null;
		return dao.selectByExample(vo.getExample());
	}

	public MetaWarehouseVO searchCache (MetaWarehouseVO vo) {
		if ( vo.getWarehouse() == null ) {
			return vo;
		}
		if ( vo.getWarehouse().getWarehouseStatus() != null ) {
			vo.setWrhStatusComment(paramService.getValue("WAREHOUSESTATUS", vo.getWarehouse().getWarehouseStatus()));
		}
		if ( vo.getWarehouse().getWarehouseType() != null ) {
			vo.setWrhTypeComment(paramService.getValue("WAREHOUSETYPE", vo.getWarehouse().getWarehouseType()));
		}
		return vo;
	}
}