/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年4月20日 上午9:56:35<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.util;

import java.math.BigDecimal;

/**
 * 数值型的工具类<br/><br/>
 * @version 2017年4月20日 上午9:56:35<br/>
 * @author andy wang<br/>
 */
public class NumberUtil {
	
	/**
	 * 加法
	 * @param v1 加数
	 * @param v2 被加数
	 * @return 两数之和
	 * @throws Exception
	 * @version 2017年4月20日 上午9:59:48<br/>
	 * @author andy wang<br/>
	 */
	public static Double add ( Number v1 , Number v2 ) {
		if ( v1 == null || v2 == null ) {
			return 0d;
		}
		BigDecimal d1 = new BigDecimal(v1.toString()); 
		BigDecimal d2 = new BigDecimal(v2.toString()); 
		return d1.add(d2).doubleValue();
	}
	
	
	/**
	 * 减法
	 * @param v1 减数
	 * @param v2 被减数
	 * @param scale 精确后保留小数的位数
	 * @return 两书之差
	 * @throws Exception
	 * @version 2017年4月20日 上午10:00:49<br/>
	 * @author andy wang<br/>
	 */
	public static Double subScale ( Number v1 , Number v2 , int scale ) {
		if ( v1 == null || v2 == null ) {
			return 0d;
		}
		BigDecimal d1 = new BigDecimal(v1.toString()); 
		BigDecimal d2 = new BigDecimal(v2.toString()); 
		return d1.subtract(d2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 减法
	 * @param v1 减数
	 * @param v2 被减数
	 * @return 两书之差
	 * @throws Exception
	 * @version 2017年4月20日 上午10:00:49<br/>
	 * @author andy wang<br/>
	 */
	public static Double sub ( Number v1 , Number v2 ) {
		if ( v1 == null || v2 == null ) {
			return 0d;
		}
		BigDecimal d1 = new BigDecimal(v1.toString()); 
		BigDecimal d2 = new BigDecimal(v2.toString()); 
		return d1.subtract(d2).doubleValue();
	}
	
	
	/**
	 * 减法
	 * @param vs 执行减法操作的数组
	 * @return 多个数之差
	 * @version 2017年4月20日 上午11:41:00<br/>
	 * @author andy wang<br/>
	 */
	public static Double sub ( Number... vs ) {
		if ( vs == null || vs.length == 0 ) {
			return 0d;
		}
		BigDecimal d = new BigDecimal(vs[0].toString());
		for (int i = 1; i < vs.length; i++) {
			d = d.subtract(new BigDecimal(vs[i].toString()));
		}
		return d.doubleValue();
	}
	
	
	/**
	 * 乘法
	 * —— 精确度使用四舍五入的方式
	 * @param v1 乘数
	 * @param v2 被乘数
	 * @param scale 精确后保留小数的位数
	 * @return 两数相乘结果
	 * @throws Exception
	 * <br/>—— 参数scale小于0时，抛出RuntimeException
	 * @version 2017年4月20日 上午10:07:07<br/>
	 * @author andy wang<br/>
	 */
	public static Double mul ( Number v1 , Number v2 , int scale ) throws Exception {
		if ( v1 == null || v2 == null ) {
			return 0d;
		}
		if ( scale < 0 ) {
			throw new RuntimeException("NumberUtil div -> The parameter scale must be a positive integer or zero");
		}
		BigDecimal d1 = new BigDecimal(v1.toString());
		BigDecimal d2 = new BigDecimal(v2.toString());
		return d1.multiply(d2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 乘法
	 * —— 直接返回结果，不进行小数截取
	 * @param v1 乘数
	 * @param v2 被乘数
	 * @return 两数相乘结果
	 * @throws Exception
	 * @version 2017年4月20日 上午10:07:07<br/>
	 * @author andy wang<br/>
	 */
	public static Double mul ( Number v1 , Number v2 ) {
		if ( v1 == null || v2 == null ) {
			return 0d;
		}
		BigDecimal d1 = new BigDecimal(v1.toString());
		BigDecimal d2 = new BigDecimal(v2.toString());
		return d1.multiply(d2).doubleValue();
	}
	
	
	/**
	 * 除法
	 * —— 精确度使用四舍五入的方式
	 * @param v1 除数
	 * @param v2 被除数
	 * @param scale 精确后保留小数的位数
	 * @return 两数相除结果
	 * @throws Exception 
	 * <br/>—— 参数scale小于0时，抛出RuntimeException
	 * @version 2017年4月20日 上午10:17:31<br/>
	 * @author andy wang<br/>
	 */
	public static BigDecimal div ( Number v1 , Number v2 , int scale ) {
		if ( v1 == null || v2 == null ) {
			return new BigDecimal(0);
		}
		if ( scale < 0 ) {
			throw new RuntimeException("NumberUtil div -> The parameter scale must be a positive integer or zero");
		}
		BigDecimal d1 = new BigDecimal(v1.toString());
		BigDecimal d2 = new BigDecimal(v2.toString());
		return d1.divide(d2, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 四舍五入
	 * @param v1 要精确的数值
	 * @param scale 精确后保留小数的位数
	 * @return
	 * @throws Exception
	 * <br/>—— 参数scale小于0时，抛出RuntimeException
	 * @version 2017年4月20日 上午10:19:34<br/>
	 * @author andy wang<br/>
	 */
	public static double round ( Double v1 , int scale ) {
		if ( v1 == null ) {
			return 0d;
		}
		if ( scale < 0 ) {
			throw new RuntimeException("NumberUtil div -> The parameter scale must be a positive integer or zero");
		}
		BigDecimal d1 = new BigDecimal(v1.toString());
		return d1.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 四舍五入并转化成字符串
	 * @param d 待转换数字
	 * @param scale 保留小数点位数
	 * @return
	 */
	public static String rounded(Double d, int scale) {
		BigDecimal d1 = new BigDecimal(d);
		return d1.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
	}

	public static BigDecimal rounding( Double v1 , int scale ) {
		if ( v1 == null ) {
			return new BigDecimal(0);
		}
		if ( scale < 0 ) {
			throw new RuntimeException("NumberUtil div -> The parameter scale must be a positive integer or zero");
		}
		BigDecimal d1 = new BigDecimal(v1.toString());
		return d1.setScale(scale,BigDecimal.ROUND_HALF_UP);
	}

	public static double parse(String num) {
		return new BigDecimal(num).doubleValue();
	}

	public static void main(String[] args) {
		String ret = rounded(6.626222, 2);
		System.out.println(ret);
	}


	public static Integer compairTo( Number v1 , Number v2 , int scale) {
		if ( v1 == null || v2 == null ) {
			return null;
		}
		BigDecimal d1 = new BigDecimal(v1.toString()); 
		BigDecimal d2 = new BigDecimal(v2.toString()); 
		return d1.setScale(scale, BigDecimal.ROUND_HALF_UP).compareTo(d2.setScale(scale, BigDecimal.ROUND_HALF_UP));
	}
}