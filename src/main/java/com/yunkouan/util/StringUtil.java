/**
 * Copyright &copy; YUNKOUAN Limited All rights reserved.
 */
package com.yunkouan.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunkouan.encrypt.Encodes;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * 
 * @author admin
 * @version 2013-05-22
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {

	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";

	private static PropertiesLoader loader;

	static {
		loader = new PropertiesLoader("message.properties");
	}
	
	
	/**
	 * 提示语替换
	 * 如 —— 
	 * message :货品{0},体积不能大于{1}立方米 , param: 水壶 ，200
	 * 结果： 货品水壶，体积不能大于200立方米 
	 * @param message
	 * @param param
	 * @return
	 * @throws Exception
	 * @version 2017年4月24日 下午4:24:21<br/>
	 * @author andy wang<br/>
	 */
	public static String messageReplace ( String message , Object... param) throws Exception {
		if ( param == null || param.length == 0 ) {
			return message;
		}
		for (int i = 0; i < param.length; i++) {
			if ( param[i] == null ) {
				param[i] = "";
			}
			message = message.replaceAll("\\{" + i +"\\}", String.valueOf(param[i]));
		}
		return message;
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static String toStringForRankPosition(double d) {
		DecimalFormat df = new DecimalFormat("0.00");
		if (d - Math.round(d) == 0) {
			df = new DecimalFormat("0");
		}

		return df.format(d);
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html) {
		if (html == null) {
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param txt
	 * @return
	 */
	public static String toHtml(String txt) {
		if (txt == null) {
			return "";
		}
		return replace(replace(Encodes.escapeHtml(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String abbr2(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML) {
				isHTML = false;
			}
			try {
				if (!isCode && !isHTML) {
					n += String.valueOf(temp).getBytes("GBK").length;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (n <= length - 3) {
				result.append(temp);
			} else {
				result.append("...");
				break;
			}
		}
		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result.replaceAll(
				"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
				"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = new ArrayList<String>();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}
		return result.toString();
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * 获得i18n字符串
	 */
	public static String getMessage(String code, Object... args) {
		if (!StringUtil.isEmpty(code)) {
			String value = loader.getProperty(code);
			if (!StringUtil.isEmpty(value)) {
				if (args != null) {
					MessageFormat mf = new MessageFormat(value);
					value = mf.format(args, new StringBuffer(), null).toString();
				}
			}
			return value;
		}
		return "";
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 如果不为空，则设置值
	 * 
	 * @param target
	 * @param source
	 */
	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)) {
			target = source;
		}
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果
	 * 
	 * @param objectString
	 *            对象串 例如：row.user.id
	 *            返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 */
	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i = 0; i < vals.length; i++) {
			val.append("." + vals[i]);
			result.append("!" + (val.substring(1)) + "?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果
	 * 
	 * @param objectString
	 *            对象串 例如：row.user.id
	 *            返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 */
	public static String getNumber(String objectString) {
		Pattern p = Pattern.compile("\\d+(\\.\\d+)?");
		Matcher matcher = p.matcher(objectString);

		while (matcher.find()) {
			return matcher.group(0);
		}

		return "";
	}
	
	/**
	 * 判断字符串是否为空(去掉空格)
	 * @param str - 需要判断的字符串
	 * @return
	 */
	public static boolean isTrimEmpty( String str ) {
		return str == null || str.trim().isEmpty();
	}
	
	/**
	 * 根据数字创造字符串
	 * @param number 给定的数字
	 * @param length 创造字符串的长度
	 * @return
	 * @Description 
	 * @version 2017年2月17日 下午5:36:42<br/>
	 * @author 王通<br/>
	 */
	public static String getString (BigDecimal number, Integer length) {
		String value = number.toString();
		Integer valueLength = value.length();
		if (valueLength >= length) {
			value = value.substring(valueLength - length);
		} else {
			for (int i = 0; i < length - valueLength; i++) {
				value = "0" + value;
			}
		}
		return value;
	}
	
	private static final String H = "#";  
    private static final String S = "$";  
  
    /** 
     * mapper.xml中的取值方式为#{}时 
     * @param str like的查询条件 
     * @return 
     */  
    public static String likeEscapeH(String str) {  
        return likeEscapeZ(str, H, true, true);  
    }  
      
    /** 
     * mapper.xml中的取值方式为${}时 
     * @param str like的查询条件 
     * @return 
     */  
    public static String likeEscapeS(String str) {  
        return likeEscapeZ(str, S, true, true);  
    }  
      
    /** 
     * @param str   like的查询条件 
     * @param type  mapper.xml中的取值方式，只能“#”
     * @param start 字符串前部是否拼接“%” 
     * @param end   字符串尾部是否拼接“%” 
     * @return 
     */  
    public static String likeEscapeZ(String str, String type, boolean start, boolean end) {  
        if (isTrimEmpty(str)) {  
            return null;  
        }  
        StringBuffer buffer = new StringBuffer();  
        // 拼接顺序不能改变  
        if (S.equals(type)) {  
            buffer.append(" '");  
        }  
        if (start) {  
            buffer.append("%");  
        }  
        int len = str.length();  
        //注意："]"不能处理  
        for (int i = 0; i < len; i++) {  
            char c = str.charAt(i);  
            switch (c) {  
            case '\'':  
                if (S.equals(type)) {  
                    buffer.append("''");// 单引号替换成两个单引号  
                } else {  
                    buffer.append(c);  
                }  
                break;  
            case '[':  
                buffer.append("[[]");  
                break;  
            case '_':  
                buffer.append("[_]");  
                break;  
            case '%':  
                buffer.append("[%]");  
                break;  
            case '^':  
                buffer.append("[^]");  
                break;  
            case '!':  
                buffer.append("[!]");  
                break;  
            default:  
                buffer.append(c);  
            }  
        }  
          
        if (end) {  
            buffer.append("%");  
        }  
        if (S.equals(type)) {  
            buffer.append("' ");  
        }  
        return buffer.toString();  
    }  
    
    
    /**
     * 格式化Json
     * @param jsonStr Json字符串
     * @return
     * @version 2017年3月21日 下午7:36:04<br/>
     * @author andy wang<br/>
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }
        return sb.toString();
    }
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
    
    /**
	 * 对象转Json字符串
	 * @param obj 要转换的对象
	 * @return 
	 * @throws Exception
	 * @version 2017年3月20日 下午2:51:52<br/>
	 * @author andy wang<br/>
	 */
	public static String toJson ( Object obj , boolean isFormat) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		String json = objectMapper.writeValueAsString(obj);
		if ( isFormat ) {
			json = StringUtil.formatJson(json);
		}
		return json;
	}
	public static String toJson ( Object obj ) throws Exception {
		return toJson(obj, false);
	}

	  public static String fillBlank(String input, int len)
	  {
	    StringBuilder builder = new StringBuilder(input);
	    while (builder.length() < len) {
	      builder.insert(0, " ");
	    }
	    return builder.toString();
	  }

	  public static String fillZero(String input, int len)
	  {
	    StringBuilder builder = new StringBuilder(input);
	    while (builder.length() < len) {
	      builder.insert(0, "0");
	    }
	    return builder.toString();
	  }

	  public static String fillZeroAfter(String input, int len)
	  {
	    StringBuilder builder = new StringBuilder(input);
	    while (builder.length() < len) {
	      builder.append("0");
	    }
	    return builder.toString();
	  }
}