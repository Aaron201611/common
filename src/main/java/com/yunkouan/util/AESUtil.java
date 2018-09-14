/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 YUNKOUAN Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2015年9月19日                                                      
 *                                                                              
 *******************************************************************************/

package com.yunkouan.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * @author admin
 *
 */
public class AESUtil {
	public static String encrypt(String password, String strKey) {
		try {
			byte[] keyBytes = Arrays.copyOf(strKey.getBytes("ASCII"), 16);

			SecretKey key = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] cleartext = password.getBytes("UTF-8");
			byte[] ciphertextBytes = cipher.doFinal(cleartext);

			return new String(Hex.encodeHex(ciphertextBytes)).toUpperCase();

		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String encryptResult, String strKey) {
		try {
			byte[] keyBytes = Arrays.copyOf(strKey.getBytes("ASCII"), 16);

			SecretKey key = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);

			byte[] cleartext = Hex.decodeHex(encryptResult.toCharArray());
			byte[] ciphertextBytes = cipher.doFinal(cleartext);

			return new String(ciphertextBytes, "UTF-8");

		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException | DecoderException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String content = "320921194402223000";
		String password = "yunkouan";
		// 加密
		System.out.println("加密前：" + content);
		String encryptResult = encrypt(content, password);
		System.out.println("加密后：" + encryptResult);
		// 解密
		String decryptResult = decrypt(encryptResult, password);
		System.out.println("解密后：" + decryptResult);

	}
}
