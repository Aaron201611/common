/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年2月23日 下午2:43:41<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.MetaLocation;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * 库位数据层接口<br/><br/>
 * @version 2017年2月23日 下午2:43:41<br/>
 * @author andy wang<br/>
 */
//@CacheNamespace(implementation = (RedisCache4Mybatis.class))
public interface ILocationDao extends Mapper<MetaLocation> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<MetaLocation> selectByExample(Object example);

	/**
	 * 根据id，更新库容度
	 * @param metaLocationVO 库位VO
	 * @return
	 * @throws Exception
	 * @version 2017年5月14日 上午11:46:08<br/>
	 * @author andy wang<br/>
	 */
//	public int updateCapacity ( MetaLocationVO metaLocationVO ) throws Exception ;

	/**
	 * 根据id，更新最大库容
	 * @param metaLocationVO 库位VO
	 * @return 
	 * @throws Exception
	 * @version 2017年3月14日 上午11:49:56<br/>
	 * @author andy wang<br/>
	 */
//	public int updateMaxCapacity ( MetaLocationVO metaLocationVO ) throws Exception;

	/**
	 * 增加/减少预分配库容量
	 * @param metaLocationVO 条件
	 * @return
	 * @throws Exception
	 * @version 2017年2月27日 下午2:28:25<br/>
	 * @author andy wang<br/>
	 */
//	public int addPreusedCapacity ( MetaLocationVO metaLocationVO ) throws Exception ;

	/**
	 * 增加/减少库容量
	 * @param metaLocationVO 条件
	 * @return
	 * @throws Exception
	 * @version 2017年2月24日 下午2:02:17<br/>
	 * @author andy wang<br/>
	 */
//	public int addCapacity ( MetaLocationVO metaLocationVO ) throws Exception ;
}