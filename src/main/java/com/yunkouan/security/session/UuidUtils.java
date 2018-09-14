/**
 * Copyright &copy; YUNKOUAN Limited All rights reserved.
 */
package com.yunkouan.security.session;

import java.security.SecureRandom;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import com.yunkouan.encrypt.Encodes;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 * @author admin
 * @version 2013-01-15
 */
public class UuidUtils {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return StringUtils.upperCase(UUID.randomUUID().toString().replaceAll("-", ""));
	}

	public static boolean validateUUID(String uuid) {
		RegularExpression regex = new RegularExpression("[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}");
		return regex.matches(uuid);
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	public static void main(String[] args) {
		System.out.println(UuidUtils.uuid());
		System.out.println(UuidUtils.uuid().length());
		for (int i = 0; i < 1000; i++) {
			System.out.println(UuidUtils.randomLong() + "  " + UuidUtils.randomBase62(5));
		}
	}

}
