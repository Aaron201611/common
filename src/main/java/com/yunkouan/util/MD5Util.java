package com.yunkouan.util;

import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MD5Util {
	private static Log log = LogFactory.getLog(MD5Util.class);

	/**
	 * MD5加密算法
	 * @param data
	 * @param key
	 * @return
	 */
	public static String md5(String sign) {
	   try {
		   MessageDigest md = MessageDigest.getInstance("MD5");
		   md.update(sign.getBytes());
		   byte b[] = md.digest();
		   int i;
		   StringBuilder buf = new StringBuilder();
		   for (int offset = 0; offset < b.length; offset++) {
			    i = b[offset];
			    if (i < 0) {
			    	i += 256;
			    }
			    if (i < 16) {
			    	buf.append("0");
			    }
			    buf.append(Integer.toHexString(i));
		   }
		   return buf.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			if(log.isErrorEnabled()) log.error(e.getMessage());
		}
	    return "";
	}
}