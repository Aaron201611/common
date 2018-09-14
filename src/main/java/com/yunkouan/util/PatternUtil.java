package com.yunkouan.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
	/** 
	* @Title: valid 
	* @Description: java正则表达式校验
	* @auth tphe06
	* @time 2018 2018年3月27日 下午12:02:14
	* @param str 待校验字符串
	* @param reg 正则表达式
	* @return
	* boolean
	*/
	public static boolean valid(String str, String reg) {
	    // 编译正则表达式（忽略大小写的写法）
	    Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(str);
	    // 查找字符串中是否有匹配正则表达式的字符/字符串
	    boolean result = matcher.find();
	    return result;
	}

	/** 
	* @Title: math 
	* @Description: 用正则表达式匹配一个规则
	* @auth tphe06
	* @time 2018 2018年4月2日 下午3:52:28
	* @param reg 正则表达式
	* @param str 字符串内容
	* @param group
	* @return
	* String
	*/
	public static String math(String reg, String str, int group) {
	    Pattern pattern = Pattern.compile(reg);
	    Matcher matcher = pattern.matcher(str);
	    if(matcher.find()) {
		    String result = matcher.group(group);
		    return result;
	    }
	    return null;
	}

	public static List<String> maths(String reg, String str, int group) {
	    Pattern pattern = Pattern.compile(reg);
	    Matcher matcher = pattern.matcher(str);
	    List<String> list = new ArrayList<String>();
	    while(matcher.find()) {
		    String result = matcher.group(group);
		    list.add(result);
	    }
	    return list;
	}
}