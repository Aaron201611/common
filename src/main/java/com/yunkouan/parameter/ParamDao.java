package com.yunkouan.parameter;

import java.util.List;
import java.util.Map;

public interface ParamDao {
	
	/**
	 * 查询所有参数数据
	 * @return
	 * <P>@author andy</P>
	 * <P>Date 2017年1月3日 下午10:13:40</P>
	 */
	public List<Map<String,String>> selectAllParam();
	
	/**
	 * 根据条件查询参数数据（跨表查询）
	 * @param param - 条件
	 * @return
	 * <P>@author andy</P>
	 * <P>Date 2017年1月3日 下午10:14:00</P>
	 */
	public List<Map<String,String>> selectAllParamByTable( Map<String,String> param );
}
