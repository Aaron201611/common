/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年2月15日 下午3:24:21<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * 对象工具类<br/><br/>
 * @version 2017年2月15日 下午3:24:21<br/>
 * @author andy wang<br/>
 */
public class BeanUtil {

	/**
	 * 拷贝对象
	 * @param sourceBean 源对象 <br/>与新对象属性冲突时，设置时使用源对象属性
	 * @param object 被替换对象
	 * @return
	 * @version 2017年2月15日 下午3:26:41<br/>
	 * @author andy wang<br/>
	 */
	public static<T> T bean2Obj(Object sourceBean, T object) {
		if (sourceBean != null) {
			Class<?> sourceClass = sourceBean.getClass();
			Class<?> objectClass = object.getClass();
			for (Method m : objectClass.getMethods()) {
				if (m.getName().startsWith("set")) {
					try {
						String methodName = m.getName();
						methodName = methodName.substring(3);
//						Method declaredMethod = sourceClass.getDeclaredMethod("get" + methodName);
						Object invoke = sourceClass.getMethod(
								"get" + methodName).invoke(sourceBean);
						if (invoke != null && !"".equals(invoke.toString())) {
							if (object == null) {
								object = (T) objectClass.newInstance();
							}
							m.invoke(object, invoke);
						}
					} catch (NoSuchMethodException se) {
//						se.printStackTrace();
					} catch (Exception e) {
						// 忽略所有设置失败方法
						e.printStackTrace();
					}
				}
			}
		}
		return object;
	}

	/**
	 * 复制对象属性，把sourceBean属性复制到Map中
	 * @param sourceBean - 源对象
	 * @param object - 新对象
	 * @return
	 * <P>@author andy</P>
	 * <P>Date 2016年12月13日 上午11:50:10</P>
	 */
	public static Map<String, Object> bean2Map(Object sourceBean) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		if (sourceBean == null) {
			return result_map;
		}
		Class<?> sourceClass = sourceBean.getClass();
		for (Method m : sourceClass.getMethods()) {
			if (m.getName().startsWith("get")) {
				try {
					String methodName = m.getName();
					Object invoke = sourceClass.getMethod(methodName)
							.invoke(sourceBean);
					methodName = methodName.substring(3,4).toLowerCase()+methodName.substring(4);
					if (invoke != null && !"".equals(invoke.toString())) {
						result_map.put(methodName, String.valueOf(invoke));
					}
				} catch (NoSuchMethodException se) {

				} catch (Exception e) {
					// 忽略所有设置失败方法
					e.printStackTrace();
				}
			}
		}
		return result_map;
	}
	
}
