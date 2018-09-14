/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月11日 下午3:29:47<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.yunkouan.saas.modules.sys.entity.MetaWarehouse;
import com.yunkouan.saas.modules.sys.vo.MetaWarehouseVO;

/**
 * 仓库业务接口<br/><br/>
 * @version 2017年3月11日 下午3:29:47<br/>
 * @author andy wang<br/>
 */
public interface IWarehouseService {

	public MetaWarehouseVO searchCache (MetaWarehouseVO vo);

	/**
	 * 保存并生效仓库
	 * @param param_warehouseVO 仓库对象
	 * @return 生效后的仓库
	 * @throws Exception
	 * @version 2017年6月19日 下午2:09:06<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouseVO addAndEnable ( MetaWarehouseVO param_warehouseVO, String loginUserId, String loginOrgId) throws Exception;
	
	
	/**
	 * 更新并生效仓库
	 * @param param_warehouseVO 仓库对象
	 * @return 生效后的仓库
	 * @throws Exception
	 * @version 2017年6月19日 下午2:09:06<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouseVO updateAndEnable ( MetaWarehouseVO param_warehouseVO, String loginUserId, String loginOrgId ) throws Exception;
	
	
	/**
	 * 查询页面显示仓库
	 * @param param_warehouseVO 查询条件
	 * @return
	 * @throws Exception
	 * @version 2017年4月4日 上午9:52:49<br/>
	 * @author andy wang<br/>
	 */
	public List<MetaWarehouseVO> showWrh ( MetaWarehouseVO param_warehouseVO, String loginOrgId) throws Exception;
	
	/**
     * 更新仓库
     * @param param_warehouseVO 仓库信息
     * @throws Exception
     * @version 2017年2月18日 下午2:44:59<br/>
     * @author andy wang<br/>
     */
    public void updateWrh ( MetaWarehouseVO param_warehouseVO, String loginUserId, String loginOrgId) throws Exception;
	
	/**
	 * 失效仓库
	 * @param param_listWrhId 仓库id集合
	 * @throws Exception
	 * @version 2017年3月11日 下午2:17:57<br/>
	 * @author andy wang<br/>
	 */
    public void inactiveWrh ( List<String> param_listWrhId, String loginUserId, String loginOrgId ) throws Exception;
	
	/**
	 * 生效仓库
	 * @param param_listWrhId 仓库id集合
	 * @throws Exception
	 * @version 2017年3月11日 下午2:16:53<br/>
	 * @author andy wang<br/>
	 */
    public void activeWrh ( List<String> param_listWrhId, String loginUserId, String loginOrgId) throws Exception;
	
	/**
	 * 根据仓库id，查询仓库信息
	 * @param param_wrhId 仓库id
	 * @return 仓库信息
	 * @throws Exception
	 * @version 2017年3月11日 下午1:57:06<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouseVO viewWrh ( String param_wrhId ) throws Exception;
	
	/**
	 * 保存仓库
	 * @param param_warehouseVO 要保存的仓库
	 * @return 保存后的仓库（包含id）
	 * @throws Exception
	 * @version 2017年3月11日 上午9:43:25<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouseVO insertWrh ( MetaWarehouseVO param_warehouseVO, String loginUserId, String loginOrgId) throws Exception;
	
	/**
	 * 根据条件，分页查询仓库
	 * @param param_metaWarehouseVO 查询条件
	 * @return 查询结果
	 * @throws Exception
	 * @version 2017年3月11日 下午1:33:09<br/>
	 * @author andy wang<br/>
	 */
	@SuppressWarnings({ "rawtypes"})
	public Page listWrh ( MetaWarehouseVO param_metaWarehouseVO, String loginOrgId) throws Exception;


	public MetaWarehouseVO viewFirstWrh(String loginOrgId) throws Exception;
	
	
	public List<MetaWarehouse>getWarehouseList(MetaWarehouseVO vo);
}