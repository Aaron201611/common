/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月13日 下午2:23:45<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import com.yunkouan.saas.modules.sys.entity.MetaLocationSpec;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.provider.ExampleProvider;

/**
 * 库位规格Dao<br/><br/>
 * @version 2017年3月13日 下午2:23:45<br/>
 * @author andy wang<br/>
 */
public interface ILocationSpecDao extends Mapper<MetaLocationSpec> {
	@Options(useCache = false)
    @SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
    public List<MetaLocationSpec> selectByExample(Object example);

}
