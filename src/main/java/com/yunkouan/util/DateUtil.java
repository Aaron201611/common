/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月1日 上午11:12:10<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期工具类<br/><br/>
 * @version 2017年3月1日 上午11:12:10<br/>
 * @author andy wang<br/>
 */
public class DateUtil {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdf_time = new SimpleDateFormat("yyyyMMddHHmmss");
	static SimpleDateFormat sdf_hh = new SimpleDateFormat("yyyyMMddHH");


	/**
	 * 格式化日期
	 * @param date 需要格式化的日期
	 * @param format 格式化的格式
	 * @return 格式化后的日期字符串
	 * @version 2017年3月8日 下午5:41:38<br/>
	 * @author andy wang<br/>
	 */
	public static String formatDate(Date date , String format) {
		return DateFormatUtils.format(date, format);
	}

	/** 
	* @Title: formatHH 
	* @Description: 返回 yyyyMMddHH 格式
	* @auth tphe06
	* @time 2017 2017年12月8日 上午10:40:42
	* @param date 如果为空则返回当前时间 
	* @return
	* String
	*/
	public static String formatHH(Date date) {
		if(date == null) return DateFormatUtils.format(new Date(), "yyyyMMddHH");
		return DateFormatUtils.format(date, "yyyyMMddHH");
	}

	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 获取当前日期时间的字符串结果
	 * @return 当前日期时间的字符串结果
	 * @version 2017年3月1日 上午11:11:27<br/>
	 * @author andy wang<br/>
	 */
	@Deprecated
	public static String now() {
		return sdf_time.format(new Date());
	}

	/**
	 * 设置时间为00:00:00
	 * @param date
	 * @return
	 * @version 2017年3月1日 上午11:11:12<br/>
	 * @author 王通<br/>
	 */
	public static Date getStartTime(Date date ) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		return cl.getTime();
	}

	/**
	 * 设置时间为00:00:00
	 * @param date
	 * @return
	 * @version 2017年3月1日 上午11:11:12<br/>
	 * @author 王通<br/>
	 */
	public static Date getStartDateOnMonth(Date date ) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		return cl.getTime();
	}

	public static Date getEndDateOnMonth(Date date ) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		cl.add(Calendar.MONTH, 1);
		cl.add(Calendar.MILLISECOND, -1);
		return cl.getTime();
	}
	/**
	 * 设置时间为23:59:59
	 * @param date 日期
	 * @return
	 * @version 2017年3月1日 上午11:10:39<br/>
	 * @author 王通<br/>
	 */
	public static Date getEndTime(Date date ) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		cl.add(Calendar.DAY_OF_MONTH, 1);
		cl.add(Calendar.MILLISECOND, -1);
		return date;
	}

	/**
	 * 获取指定日期之后的某个日期(默认为天)
	 * @return
	 * @required inDate, addValue
	 * @optional addType
	 * @Description 
	 * @version 2017年2月28日 下午3:08:23<br/>
	 * @author 王通<br/>
	 */
	public static Date passDate(Date inDate, Integer addType, Integer addValue) {
		Calendar cl = Calendar.getInstance();
		if (addType == null || addType == 0) {
			addType = Calendar.DAY_OF_MONTH;
		}
		cl.setTime(inDate);
		cl.add(addType, addValue);
		return cl.getTime();
	}

	/**
	 * 给出制定时间，创建日期
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return 制定的日期
	 * @version 2017年3月8日 下午5:19:44<br/>
	 * @author andy wang<br/>
	 */
	@Deprecated
	public static Date createDate ( Integer year , Integer month , Integer day ) {
		Date date = new Date(year-1900, month-1, day);
		return date;
	}

	public static String getDayOfWeek (Date date) {
		String dayOfWeekStr = null;
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int dayOfWeek = cl.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 1:
			dayOfWeekStr = "星期天";
			break;
		case 2:
			dayOfWeekStr = "星期一";
			break;
		case 3:
			dayOfWeekStr = "星期二";
			break;
		case 4:
			dayOfWeekStr = "星期三";
			break;
		case 5:
			dayOfWeekStr = "星期四";
			break;
		case 6:
			dayOfWeekStr = "星期五";
			break;
		case 7:
			dayOfWeekStr = "星期六";
			break;			
		}
		return dayOfWeekStr;
	}

	/** 
	 * @Title: getIntervalDays 
	 * @Description: 比较两个时间相差的天数（按照时间差转换成天数）
	 * @auth tphe06
	 * @time 2017 2017年10月25日 下午2:56:33
	 * @param fDate 开始天数
	 * @param oDate 结束天数
	 * @return
	 * int
	 */
	public static int getIntervalDays(Date fDate, Date oDate) {
		if (null == fDate || null == oDate)  return -1;
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}
	
	/** 
	 * @Title: getIntervalMonths 
	 * @Description: 比较两个时间相差的月数（按照年份月份换成月数）
	 * @auth 王通
	 * @time 2018年4月27日18:33:48
	 * @param fDate 开始天数
	 * @param oDate 结束天数
	 * @return
	 * int
	 * @throws ParseException 
	 */
	public static int getIntervalMonths(Date fDate, Date oDate) throws ParseException {
		if (null == fDate || null == oDate)  return -1;
		Calendar startDate = Calendar.getInstance();  
		startDate.setTime(fDate);
		Calendar endDate = Calendar.getInstance();  
		endDate.setTime(oDate);
        int result = yearsBetween(startDate, endDate) * 12 + endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);  
        return result == 0 ? 1 : Math.abs(result);  
	}

	public static int yearsBetween(Calendar startDate, Calendar endDate) throws ParseException {  
        return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));  
    }  
	/**
	 * 计算间隔小时数
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int getIntervalHours(Date fDate, Date oDate) {
		if (null == fDate || null == oDate)  return -1;
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (60 * 60 * 1000));
	}
	/** 
	 * @Title: daysOfTwo 
	 * @Description: 比较两个日期相差的天数（按照日期直接相减）
	 * 当跨年时，该方法返回值错误
	 * @auth tphe06
	 * @time 2017 2017年10月25日 下午2:56:36
	 * @param fDate 开始天数
	 * @param oDate 结束天数
	 * @return
	 * int
	 */
	//	@Deprecated
	//	public static int daysOfTwo(Date fDate, Date oDate) {
	//		if (null == fDate || null == oDate)  return -1;
	//       Calendar aCalendar = Calendar.getInstance();
	//       aCalendar.setTime(fDate);
	//       int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
	//       aCalendar.setTime(oDate);
	//       int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
	//       return day2 - day1;
	//    }

	/**
	 * 获取当前月份天数
	 * @param paramDate
	 * @return
	 */
	public static int getDaysOnMonth(Date paramDate) {
		paramDate = getStartDateOnMonth(paramDate);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(paramDate);
		c1.add(Calendar.MONTH, 1);
		return getIntervalDays(paramDate, c1.getTime());
	}

	/** 
	 * @Title: getToday 
	 * @Description: 获取今天日期（格式：yyyy-MM-dd）
	 * @auth tphe06
	 * @time 2017 2017年11月1日 下午2:38:44
	 * @return
	 * String
	 */
	public static String getToday() {
		String day = DateFormatUtils.format(new Date(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
		return day;
	}

	/** 
	 * @Title: getThisMonth 
	 * @Description: 得到本月1号（格式：yyyy-MM-dd）
	 * @auth tphe06
	 * @time 2017 2017年11月1日 下午1:37:52
	 * @return
	 * String
	 */
	public static String getThisMonth() {
		String day = DateFormatUtils.format(new Date(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
		String[] times = day.split("-");
		return new StringBuffer(times[0]).append("-").append(times[1]).append("-").append("01").toString();
	}

	/** 
	 * @Title: getThisMonth 
	 * @Description: 得到本年1号（格式：yyyy-MM-dd）
	 * @auth tphe06
	 * @time 2017 2017年11月1日 下午1:37:52
	 * @return
	 * String
	 */
	public static String getThisYear() {
		String day = DateFormatUtils.format(new Date(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
		String[] times = day.split("-");
		return new StringBuffer(times[0]).append("-").append("01").append("-").append("01").toString();
	}

	/** 
	 * @Title: format 
	 * @Description: 格式化日期（格式：yyyy-MM-dd）
	 * @auth tphe06
	 * @time 2017 2017年11月1日 下午1:44:20
	 * @param d
	 * @return
	 * String
	 */
	public static String formatDate(Date d) {
		return DateFormatUtils.format(d, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
	}

	/** 
	 * @Title: formatTime 
	 * @Description: 格式化时间（格式：HH:mm:ss）
	 * @auth tphe06
	 * @time 2017 2017年11月1日 下午1:47:05
	 * @param d
	 * @return
	 * String
	 */
	public static String formatTime(Date d) {
		return DateFormatUtils.format(d, DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.getPattern());
	}

	/** 
	* @Title: formatDateTime 
	* @Description: 格式化时间（格式：yyyy-MM-dd HH:mm:ss）
	* @auth tphe06
	* @time 2018 2018年2月24日 下午1:47:32
	* @param d
	* @return
	* String
	*/
	public static String formatDateTime(Date d) {
		if(d == null) return null;
		return new StringBuffer(formatDate(d)).append(" ").append(formatTime(d)).toString();
	}

	/** 
	* @Title: formatMonth 
	* @Description: 格式化月份（格式：yyyy-MM）
	* @auth tphe06
	* @time 2018 2018年2月24日 下午1:49:54
	* @param d
	* @return
	* String
	*/
	public static String formatMonth(Date d) {
		if(d == null) return null;
		String[] date = formatDate(d).split("-");
		return new StringBuffer(date[0]).append("-").append(date[1]).toString();
	}

	/** 
	 * @Title: parseDate 
	 * @Description: 把字符串日期转化成日期
	 * @auth tphe06
	 * @time 2017 2017年11月1日 下午1:51:51
	 * @param d （格式：yyyy-MM-dd）
	 * @return
	 * Date
	 */
	public static Date parseDate(String d) {
		try {
			return DateUtils.parseDate(d, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseMonth(String m) {
		try {
			return DateUtils.parseDate(m, "yyyy/MM");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 
	 * @Title: addDays 
	 * @Description: 添加天数
	 * @auth tphe06
	 * @time 2017 2017年11月1日 下午2:41:07
	 * @param d
	 * @param days
	 * @return
	 * Date
	 */
	public static Date addDays(Date d, int days) {
		return DateUtils.addDays(d, days);
	}

	/** 
	 * @Title: addMonths 
	 * @Description: 添加月份
	 * @auth tphe06
	 * @time 2017 2017年11月1日 下午2:41:20
	 * @param d
	 * @param months
	 * @return
	 * Date
	 */
	public static Date addMonths(Date d, int months) {
		return DateUtils.addMonths(d, months);
	}
	public static int getMonthDiff(Date d1, Date d2) {  
		Calendar c1 = Calendar.getInstance();  
		Calendar c2 = Calendar.getInstance();  
		c1.setTime(d1);  
		c2.setTime(d2);  
		if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;  
		int year1 = c1.get(Calendar.YEAR);  
		int year2 = c2.get(Calendar.YEAR);  
		int month1 = c1.get(Calendar.MONTH);  
		int month2 = c2.get(Calendar.MONTH);  
		int day1 = c1.get(Calendar.DAY_OF_MONTH);  
		int day2 = c2.get(Calendar.DAY_OF_MONTH);  
		// 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30  
		int yearInterval = year1 - year2;  
		// 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数  
		if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;  
		// 获取月数差值  
		int monthInterval =  (month1 + 12) - month2  ;  
		if(day1 < day2) monthInterval --;  
		monthInterval %= 12;  
		return yearInterval * 12 + monthInterval;  
	}

	public static Date getEndDateOnYear(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.MILLISECOND, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		cl.set(Calendar.MONTH, 0);
		cl.add(Calendar.YEAR, 1);
		cl.add(Calendar.MILLISECOND, -1);
		return cl.getTime();
	}

	public static Date getStartDateOnYear(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.MILLISECOND, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		cl.set(Calendar.MONTH, 0);
		return cl.getTime();
	}

	// 获得当天0点时间
	public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();


	}
	// 获得昨天0点时间
	public static Date getYesterdaymorning() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getTimesmorning().getTime()-3600*24*1000);
		return cal.getTime();
	}
	// 获得当天近7天时间
	public static Date getWeekFromNow() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( getTimesmorning().getTime()-3600*24*1000*7);
		return cal.getTime();
	}

	// 获得当天24点时间
	public static Date getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/** 
	* @Title: getTimesWeekmorning 
	* @Description: 获得本周一0点时间
	* @auth tphe06
	* @time 2018 2018年3月2日 下午1:26:36
	* @return
	* Date
	*/
	public static Date getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/** 
	* @Title: getTimesWeeknight 
	* @Description: 获得本周日24点时间
	* @auth tphe06
	* @time 2018 2018年3月2日 下午1:26:45
	* @return
	* Date
	*/
	public static Date getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekmorning());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		return cal.getTime();
	}

	// 获得本月第一天0点时间
	public static Date getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	// 获得本月最后一天24点时间
	public static Date getTimesMonthnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return cal.getTime();
	}

	public static Date getLastMonthStartMorning() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesMonthmorning());
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	public static Date getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前季度的结束时间，即2012-03-31 23:59:59
	 *
	 * @return
	 */
	public static Date getCurrentQuarterEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentQuarterStartTime());
		cal.add(Calendar.MONTH, 3);
		return cal.getTime();
	}


	public static Date getCurrentYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
		return cal.getTime();
	}

	public static Date getCurrentYearEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentYearStartTime());
		cal.add(Calendar.YEAR, 1);
		return cal.getTime();
	}

	public static Date getLastYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentYearStartTime());
		cal.add(Calendar.YEAR, -1);
		return cal.getTime();
	}

	/**
	 * 设置时间为00:00:00
	 * @param date
	 * @return
	 * @version 2017年3月1日 上午11:11:12<br/>
	 * @author andy wang<br/>
	 */
	public static Date setStartTime(Date date ) {
		Date clone = (Date) date.clone();
		clone.setHours(0);
		clone.setMinutes(0);
		clone.setSeconds(0);
		return clone;
	}
	
	/**
	 * 设置时间为23:59:59
	 * @param date 日期
	 * @return
	 * @version 2017年3月1日 上午11:10:39<br/>
	 * @author andy wang<br/>
	 */
	public static Date setEndTime(Date date ) {
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date;
	}

	public static void main(String[] args) {
		Date d = parseMonth("2018/03");
		String t = formatDateTime(d);
		System.out.println(t);
	}
}