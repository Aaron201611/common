/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月1日 下午2:03:32<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.yunkouan.saas.modules.sys.entity.MetaWarehouse;
import com.yunkouan.saas.modules.sys.vo.MetaWarehouseVO;

/**
 * 仓库外调接口<br/><br/>
 * @version 2017年3月1日 下午2:03:32<br/>
 * @author andy wang<br/>
 */
public interface IWarehouseExtlService {

	
	/**
	 * 根据id，更新仓库信息
	 * @param warehouseVO 更新信息
	 * —— 必须包含id
	 * @return 更新的数量
	 * @throws Exception
	 * @version 2017年3月11日 下午2:30:41<br/>
	 * @author andy wang<br/>
	 */
	public int updateWrh ( MetaWarehouseVO warehouseVO, String loginUserId, String loginOrgId ) throws Exception ;
	
	
	/**
	 * 根据仓库id，更新仓库状态
	 * @param wrhId 仓库id
	 * @param status 更新后的状态值
	 * @return 更新的数量
	 * @throws Exception
	 * @version 2017年3月11日 下午2:15:49<br/>
	 * @author andy wang<br/>
	 */
	public int updateWrhStatusById ( String wrhId , Integer status, String loginUserId, String loginOrgId ) throws Exception;
	
	/**
	 * 根据条件，分页查询仓库信息
	 * @param metaWarehouseVO 查询条件
	 * @return 分页数据
	 * @throws Exception
	 * @version 2017年3月11日 下午1:21:56<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings("rawtypes")
	public Page listWrhByPage ( MetaWarehouseVO metaWarehouseVO, String loginOrgId) throws Exception;
	
	/**
	 * 保存仓库
	 * @param metaWarehouse 仓库对象
	 * @return 保存后的仓库对象
	 * @version 2017年3月11日 上午10:52:46<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouse insertWrh ( MetaWarehouse metaWarehouse, String orgId, String loginUserId ) throws Exception;
	
	/**
	 * 根据仓库编号
	 * @param warehouseNo 仓库编号
	 * @return 仓库
	 * @throws Exception
	 * @version 2017年3月11日 上午10:22:53<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouse findWareHouseByNo ( String warehouseNo, String warehouseId, String loginOrgId ) throws Exception;
	
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
	public List<MetaWarehouse> listWareHouseByName ( String warehouseName , boolean isLike ) throws Exception;
	
	/**
	 * 根据条件，查询仓库列表
	 * @param metaWarehouseVO 查询条件
	 * @return 仓库集合
	 * @throws Exception
	 * @version 2017年3月8日 下午2:00:08<br/>
	 * @author andy wang<br/>
	 */
	public List<MetaWarehouse> listWareHouseByExample ( MetaWarehouseVO metaWarehouseVO, String loginOrgId ) throws Exception;
	
	/**
	 * 根据仓库名称，查询仓库
	 * @param warehouseName 仓库名称
	 * @return 仓库对象
	 * @throws Exception
	 * @version 2017年3月6日 下午4:14:39<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouse findWareHouseByName ( String warehouseName ) throws Exception;
	
	/**
	 * 根据仓库id，查询仓库
	 * @param warehouseId 仓库id
	 * @return 仓库信息
	 * @throws Exception
	 * @version 2017年3月1日 下午2:05:30<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouse findWareHouseById ( String warehouseId ) ;


	public MetaWarehouse findWareHouseByOrgId(String orgId) throws Exception;
	
}