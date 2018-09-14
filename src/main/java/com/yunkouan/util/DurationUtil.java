/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月3日 下午1:17:31<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.util;

/**
 * 用作时长测量<br/><br/>
 * @version 2017年3月3日 下午1:17:31<br/>
 * @author andy wang<br/>
 */
public class DurationUtil {

	/**
	 * 
	 * @version 2017年3月3日下午1:19:42<br/>
	 * @author andy wang<br/>
	 */
	private long startTime;
	
	/**
	 * 
	 * @version 2017年3月3日下午1:19:45<br/>
	 * @author andy wang<br/>
	 */
	private long endTime;
	
	/**
	 * 开始测量
	 * @return 起始时间
	 * @version 2017年3月3日 下午1:19:49<br/>
	 * @author andy wang<br/>
	 */
	public long start() {
		this.startTime = System.currentTimeMillis();
		return this.startTime;
	}
	
	/**
	 * 结束测量
	 * @param isPrint 是否打印使用的时长
	 * @return 结束时间
	 * @version 2017年3月3日 下午1:21:11<br/>
	 * @author andy wang<br/>
	 */
	public long end( boolean isPrint ) {
		this.endTime = System.currentTimeMillis();
		if ( isPrint ) {
			System.out.println("时长："+ (this.endTime - this.startTime));
			System.out.println("--------------------------------------");
		}
		return this.endTime;
	}
	
}
