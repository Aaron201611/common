package com.yunkouan.saas.modules.sys.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunkouan.saas.common.util.IdUtil;
import com.yunkouan.saas.modules.sys.dao.IAreaDao;
import com.yunkouan.saas.modules.sys.dao.ILocationDao;
import com.yunkouan.saas.modules.sys.dao.ILocationSpecDao;
import com.yunkouan.saas.modules.sys.dao.IWarehouseDao;
import com.yunkouan.saas.modules.sys.entity.MetaArea;
import com.yunkouan.saas.modules.sys.entity.MetaLocation;
import com.yunkouan.saas.modules.sys.entity.MetaLocationSpec;
import com.yunkouan.saas.modules.sys.entity.MetaWarehouse;
import com.yunkouan.saas.modules.sys.service.IWarehouseExtlService;
import com.yunkouan.saas.modules.sys.vo.MetaWarehouseVO;
import com.yunkouan.util.PubUtil;
import com.yunkouan.util.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 仓库服务实现类
 */
@Service
public class WarehouseExtlServiceImpl implements IWarehouseExtlService {
	/**
	 * 日志对象
	 * @version 2017年2月22日上午11:30:13<br/>
	 * @author andy wang<br/>
	 */
	private static Logger log = LoggerFactory.getLogger(WarehouseExtlServiceImpl.class);

	/**
	 * 仓库Dao
	 * @version 2017年3月1日下午2:07:24<br/>
	 * @author andy wang<br/>
	 */
	@Autowired
	private IWarehouseDao warehouseDao;
	@Autowired
	private IAreaDao areaDao;
	@Autowired
	private ILocationSpecDao locSpecDao;
	@Autowired
	private ILocationDao locDao;

	/**
	 * 根据id，更新仓库信息
	 * @param warehouseVO 更新信息
	 * —— 必须包含id
	 * @return 更新的数量
	 * @throws Exception
	 * @version 2017年3月11日 下午2:30:41<br/>
	 * @author andy wang<br/>
	 */
	public int updateWrh ( MetaWarehouseVO warehouseVO, String loginUserId, String loginOrgId) throws Exception {
		if ( warehouseVO == null || warehouseVO.getWarehouse() == null ) {
			log.error("updateWrh --> warehouseVO or warehouse is null!");
			return 0;
		}
		MetaWarehouse warehouse = warehouseVO.getWarehouse();
		// 拼接Where条件
		MetaWarehouseVO metaWarehouseVO = new MetaWarehouseVO(); 
		metaWarehouseVO.getWarehouse().setWarehouseId(warehouse.getWarehouseId());
		Example example = metaWarehouseVO.getExample();
		this.defWhere(example, loginOrgId);
		// 拼接更新内容
		warehouse.setWarehouseId2(null);
		warehouse.setCreatePerson(null);
		warehouse.setCreateTime(null);
		warehouse.setUpdatePerson(loginUserId);
		warehouse.setUpdateTime(new Date());
		return this.warehouseDao.updateByExampleSelective(warehouse, example);
	}
	
	
	/**
	 * 根据仓库id，更新仓库状态
	 * @param wrhId 仓库id
	 * @param status 更新后的状态值
	 * @return 更新的数量
	 * @throws Exception
	 * @version 2017年3月11日 下午2:15:49<br/>
	 * @author andy wang<br/>
	 */
	public int updateWrhStatusById ( String wrhId, Integer status, String loginUserId, String loginOrgId) throws Exception {
		if ( StringUtil.isTrimEmpty(wrhId) ) {
			log.error("updateWrhStatusById --> wrhId is null!");
			return 0;
		}
		// 设置更新条件
		MetaWarehouseVO metaWarehouseVO = new MetaWarehouseVO();
		metaWarehouseVO.getWarehouse().setWarehouseId(wrhId);
		Example example = metaWarehouseVO.getExample();
		this.defWhere(example, loginOrgId);
		// 设置更新内容
		MetaWarehouse record = new MetaWarehouse();
		record.setWarehouseStatus(status);
		record.setUpdatePerson(loginUserId);
		record.setUpdateTime(new Date());
		return this.warehouseDao.updateByExampleSelective(record, example);
	}
	
	
	/**
	 * 根据条件，分页查询仓库信息
	 * @param metaWarehouseVO 查询条件
	 * @return 分页数据
	 * @throws Exception
	 * @version 2017年3月11日 下午1:21:56<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings("rawtypes")
	public Page listWrhByPage ( MetaWarehouseVO metaWarehouseVO, String loginOrgId) throws Exception {
		if ( metaWarehouseVO == null || metaWarehouseVO.getWarehouse() == null ) {
			log.error("metaWarehouseVO --> metaWarehouseVO is null!");
			return null;
		}
		Example example = metaWarehouseVO.getExample();
		example.setOrderByClause("warehouse_id2 desc");
		defWhere(example, loginOrgId);
		Page page = PageHelper.startPage(metaWarehouseVO.getCurrentPage()+1, metaWarehouseVO.getPageSize());
		this.warehouseDao.selectByExample(example);
		if ( !PubUtil.isEmpty(page) ) {
			for (Object obj : page) {
				MetaWarehouse metaWarehouse = (MetaWarehouse) obj;
				metaWarehouse.clear();
			}
		}
		return page;
	}

	/**
	 * 保存仓库
	 * @param metaWarehouse 仓库对象
	 * @return 保存后的仓库对象
	 * @version 2017年3月11日 上午10:52:46<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouse insertWrh ( MetaWarehouse metaWarehouse, String orgId, String loginUserId ) throws Exception {
		if ( metaWarehouse == null ) {
			log.error("insertWrh --> metaWarehouse is null!");
			return null;
		}
		if ( StringUtil.isTrimEmpty(metaWarehouse.getWarehouseId()) ) {
			metaWarehouse.setWarehouseId(IdUtil.getUUID());
		}
		metaWarehouse.setOrgId(orgId);
		metaWarehouse.setCreatePerson(loginUserId);
		metaWarehouse.setUpdatePerson(loginUserId);
		metaWarehouse.setWarehouseId2(null);
		metaWarehouse.setCreateTime(null);
		metaWarehouse.setUpdateTime(null);
//		metaWarehouse.setWarehouseId2(context.getStrategy4Id().getWarehouseSeq());
		this.warehouseDao.insertSelective(metaWarehouse);

		//20180508根据新增需求，后续步骤，插入默认库区，创建库位规格，插入默认库位（暂存区及库位、发货区及库位）
		String warehouseId = metaWarehouse.getWarehouseId();
		MetaArea area = new MetaArea();
		area.setWarehouseId(warehouseId);
		area.setOrgId(orgId);
		area.setAreaStatus(20);
		area.setCreatePerson(loginUserId);
		//UUID
		String tempAreaId = IdUtil.getUUID();
		area.setAreaId(tempAreaId);
		//库区代码
		area.setAreaNo("S");
		//库区类型
		area.setAreaType(10);
		//库区名称
		area.setAreaName("暂存区");
		areaDao.insertSelective(area);
		//UUID
		String sendAreaId = IdUtil.getUUID();
		area.setAreaId(sendAreaId);
		//库区代码
		area.setAreaNo("F");
		//库区类型
		area.setAreaType(60);
		//库区名称
		area.setAreaName("发货区");
		areaDao.insertSelective(area);
		//UUID
		String tAreaId = IdUtil.getUUID();
		area.setAreaId(tAreaId);
		//库区代码
		area.setAreaNo("T");
		//库区类型
		area.setAreaType(80);
		//库区名称
		area.setAreaName("退货区");
		areaDao.insertSelective(area);

		//创建库位规格
		String specId = IdUtil.getUUID();
		MetaLocationSpec mls = new MetaLocationSpec();
		mls.setWarehouseId(warehouseId);
		mls.setOrgId(orgId);
		mls.setSpecStatus(20);
		mls.setCreatePerson(loginUserId);
		mls.setSpecId(specId);
		mls.setSpecName("不限容量");
		mls.setMaxCapacity(new BigDecimal(9999999));
		this.locSpecDao.insertSelective(mls);

		//创建库位，公用参数
		MetaLocation location = new MetaLocation();
		location.setWarehouseId(warehouseId);
		location.setOrgId(orgId);
		location.setSpecId(specId);
		location.setCreatePerson(loginUserId);
		location.setMaxCapacity(mls.getMaxCapacity());
		location.setLocationStatus(20);
		//独立参数，暂存区库位
		location.setLocationId(IdUtil.getUUID());
		location.setAreaId(tempAreaId);
		location.setLocationName("暂存区");
		location.setLocationNo("S001");
		location.setLocationType(10);
		locDao.insertSelective(location);
		//独立参数，发货区区库位
		location.setLocationId(IdUtil.getUUID());
		location.setAreaId(sendAreaId);
		location.setLocationName("发货区");
		location.setLocationNo("F001");
		location.setLocationType(60);
		locDao.insertSelective(location);
		//独立参数，退货区库位
		location.setLocationId(IdUtil.getUUID());
		location.setAreaId(tAreaId);
		location.setLocationName("退货区");
		location.setLocationNo("T");
		location.setLocationType(80);
		locDao.insertSelective(location);
		return metaWarehouse;
	}
	
	/**
	 * 根据仓库编号
	 * —— 查询当前登录用户的企业下是否已经有相同的仓库编号存在
	 * @param warehouseNo 仓库编号
	 * @return 仓库
	 * @throws Exception
	 * @version 2017年3月11日 上午10:22:53<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouse findWareHouseByNo ( String warehouseNo ,String warehouseId, String loginOrgId ) throws Exception {
		if ( StringUtil.isTrimEmpty(warehouseNo) ) {
			log.error("findWareHouseByNo --> warehouseNo is null!");
			return null;
		}
		MetaWarehouseVO metaWarehouseVO = new MetaWarehouseVO();
		metaWarehouseVO.addNotId(warehouseId);
		metaWarehouseVO.getWarehouse().setWarehouseNo(warehouseNo);
		metaWarehouseVO.getWarehouse().setOrgId(loginOrgId);
		List<MetaWarehouse> listWareHouse = this.listWareHouseByExample(metaWarehouseVO, loginOrgId);
		return PubUtil.isEmpty(listWareHouse)? null : listWareHouse.get(0);
	}
	
	/**
	 * 根据仓库名，模糊查询仓库集合
	 * @param warehouseName 仓库名
	 * @param isLike 是否模糊查询
	 * —— true 模糊查询
	 * —— false 普通查询
	 * @return 仓库集合
	 * @throws Exception
	 * @version 2017年3月8日 下午2:02:26<br/>
	 * @author andy wang<br/>
	 */
	public List<MetaWarehouse> listWareHouseByName ( String warehouseName , boolean isLike ) throws Exception {
		if ( StringUtil.isTrimEmpty(warehouseName) ) {
			log.error("listWareHouseByName --> warehouseName is null!");
			return null;
		}
		MetaWarehouseVO metaWarehouseVO = new MetaWarehouseVO();
		if ( isLike ) {
			metaWarehouseVO.setLikeWrhName(warehouseName);
		} else {
			metaWarehouseVO.getWarehouse().setWarehouseName(warehouseName);
		}
		return this.listWareHouseByExample(metaWarehouseVO, null);
	}
	
	
	/**
	 * 根据条件，查询仓库列表
	 * @param metaWarehouseVO 查询条件
	 * @return 仓库集合
	 * @throws Exception
	 * @version 2017年3月8日 下午2:00:08<br/>
	 * @author andy wang<br/>
	 */
	public List<MetaWarehouse> listWareHouseByExample ( MetaWarehouseVO metaWarehouseVO, String loginOrgId) {
		if ( metaWarehouseVO == null ) {
			log.error("listWareHouseByExample --> metaWarehouseVO is null!");
			return null;
		}
		Example example = metaWarehouseVO.getExample();
		example.setOrderByClause("warehouse_id2 DESC");
		if ( metaWarehouseVO.getWhereOrg() ) {
			this.defWhere(example, loginOrgId);
		}
		return this.warehouseDao.selectByExample(example);
	}

	/**
	 * 根据仓库名称，查询仓库
	 * @param warehouseName 仓库名称
	 * @return 仓库对象
	 * @throws Exception
	 * @version 2017年3月6日 下午4:14:39<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouse findWareHouseByName ( String warehouseName ) throws Exception {
		List<MetaWarehouse> listWareHouse = this.listWareHouseByName(warehouseName, false);
		return PubUtil.isEmpty(listWareHouse)? null : listWareHouse.get(0);
	}
	
	
	/**
	 * 根据仓库id，查询仓库
	 * @param warehouseId 仓库id
	 * @return 仓库信息
	 * @throws Exception
	 * @version 2017年3月1日 下午2:05:30<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouse findWareHouseById ( String warehouseId )  {
		if ( StringUtil.isTrimEmpty(warehouseId) ) {
			log.error("findWareHouseById --> warehouseId is null!");
			return null;
		}
//		MetaWarehouseVO metaWarehouseVO = new MetaWarehouseVO();
//		metaWarehouseVO.getWarehouse().setWarehouseId(warehouseId);
//		List<MetaWarehouse> listWarehouse = this.listWareHouseByExample(metaWarehouseVO);
//		return PubUtil.isEmpty(listWarehouse)?null:listWarehouse.get(0);
		return warehouseDao.selectByPrimaryKey(warehouseId);
	}

	/**
	 * 设置默认查询条件
	 * @param example 
	 * @version 2017年3月8日 下午1:59:29<br/>
	 * @author andy wang<br/>
	 */
	private void defWhere ( Example example, String loginOrgId) {
		if ( example == null || example.getOredCriteria() == null || example.getOredCriteria().isEmpty() ) {
			return;
		}
		if(StringUtils.isBlank(loginOrgId)) {
			return;
		}
		Criteria criteria = example.getOredCriteria().get(0);
		criteria.andEqualTo("orgId", loginOrgId);
	}

	@Override
	public MetaWarehouse findWareHouseByOrgId(String orgId) throws Exception {
		MetaWarehouseVO metaWarehouseVO = new MetaWarehouseVO();
		metaWarehouseVO.getWarehouse().setOrgId(orgId);
		List<MetaWarehouse> listWareHouse = this.listWareHouseByExample(metaWarehouseVO, orgId);
		return PubUtil.isEmpty(listWareHouse)? null : listWareHouse.get(0);
	
	}
}