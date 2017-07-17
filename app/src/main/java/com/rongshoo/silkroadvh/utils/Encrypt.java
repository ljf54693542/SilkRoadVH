package com.rongshoo.silkroadvh.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {

	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		// System.out.println("iRet="+iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 返回形式只为数字
	private static String byteToNum(byte bByte) {
		int iRet = bByte;
		System.out.println("iRet1=" + iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		return String.valueOf(iRet);
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		//return (new BASE64Decoder()).decodeBuffer(key);
		return Base64.encode(key.getBytes(), Base64.NO_WRAP);
	}

	public static byte[] decryptBASE64(byte[] key) throws Exception {
		//BASE64Decoder base64Decoder = new BASE64Decoder();
		return Base64.encode(key, Base64.NO_WRAP);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		//return (new BASE64Encoder()).encodeBuffer(key);
		return new String(Base64.encode(key, Base64.NO_WRAP)) ;
	}
	
	
	public static String MD5(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static class FrmkAES {

		/**
		 * aes加密
		 * 
		 * @param src
		 * @param sKey
		 * @return
		 */
		public static String encrypt(String src, String sKey) {
			if (sKey == null) {
				return null;
			}
			if (sKey.length() != 16) {
				return null;
			}
			try {
				byte[] raw = sKey.getBytes("utf-8");
				SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
				byte[] encrypted = cipher.doFinal(src.getBytes("utf-8"));

				String result = android.util.Base64.encodeToString(encrypted,
						android.util.Base64.DEFAULT);
				return result;

				// return new org.apache.commons.codec.binary.Base64()
				// .encodeToString(encrypted);

			} catch (UnsupportedEncodingException e) {

			} catch (NoSuchAlgorithmException e) {

			} catch (NoSuchPaddingException e) {

			} catch (InvalidKeyException e) {

			} catch (IllegalBlockSizeException e) {

			} catch (BadPaddingException e) {

			}
			return null;
		}

		/**
		 * aes解密
		 * 
		 * @param src
		 * @param sKey
		 * @return
		 */
		public static String decrypt(String src, String sKey) {
			if (sKey == null) {
				return null;
			}
			if (sKey.length() != 16) {
				return null;
			}
			try {
				byte[] raw = sKey.getBytes("utf-8");
				SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
				// byte[] encrypted1 = new Base64().decode(src);
				byte[] encrypted1 = Base64.decode(src, Base64.DEFAULT);

				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original, "utf-8");
				return originalString;

			} catch (UnsupportedEncodingException e) {

			} catch (NoSuchAlgorithmException e) {

			} catch (NoSuchPaddingException e) {

			} catch (IllegalBlockSizeException e) {

			} catch (BadPaddingException e) {

			} catch (InvalidKeyException e) {

			}
			return null;
		}

		/**
		 * 加密
		 * @param src
		 * @param key
		 * @param vector
		 * @return
		 */
		public static String encrypt(String src, String key, String vector) {
			try {

				if (key == null) {
					System.out.print("Key为空null");
					return null;
				}
				// 判断Key是否为16位
				if (key.length() != 16) {
					System.out.print("Key长度不是16位");
					return null;
				}
				byte[] raw = key.getBytes();
				SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"0102030405060708
				IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

				byte[] encrypted = cipher.doFinal(src.getBytes());

				return Base64.encodeToString(encrypted, Base64.DEFAULT);
				// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
			} catch (Exception e) {

			}
			return null;
		}

		/**
		 * 解密
		 * @param src
		 * @param key
		 * @param vector
		 * @return
		 */
		public static String decrypt(String src, String key, String vector) {
			try {
				// 判断Key是否正确
				if (key == null) {
					System.out.print("Key为空null");
					return null;
				}
				// 判断Key是否为16位
				if (key.length() != 16) {
					System.out.print("Key长度不是16位");
					return null;
				}
				byte[] raw = key.getBytes("ASCII");
				SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				IvParameterSpec iv = new IvParameterSpec(vector.getBytes());
				cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
				byte[] encrypted1 = Base64.decode(src, Base64.DEFAULT);// 先用base64解密
				try {
					byte[] original = cipher.doFinal(encrypted1);
					String originalString = new String(original);
					return originalString;
				} catch (Exception e) {

					return null;
				}
			} catch (Exception ex) {

				return null;
			}

		}
	}

}
