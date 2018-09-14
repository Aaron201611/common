package com.yunkouan.util;

import com.yunkouan.encrypt.Digests;
import com.yunkouan.encrypt.Encodes;

public class UserUtil {
	public static final int HASH_INTERATIONS = 1024;
	public static final String SALT = "12345678abcdefgh";

	/**
	 * 生成安全的密码，固定的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), SALT.getBytes(), HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}
}