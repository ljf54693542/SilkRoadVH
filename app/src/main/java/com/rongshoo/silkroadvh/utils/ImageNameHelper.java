package com.rongshoo.silkroadvh.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageNameHelper{

	/**
	 * 创建图片名字
	 * 
	 * @param uid
	 *            用户名
	 */
	@SuppressLint("SimpleDateFormat")
	public static String createNewImgName(long uid) {
		String formartString = "yyyyMMddHHmmss";
		SimpleDateFormat format = new SimpleDateFormat(formartString);
		String dateString = format.format(new Date());
		String randomString = "" + new Random().nextDouble();
		String srcFileName = "" + uid + dateString + randomString;
		String imgNameString = Encrypt.MD5(srcFileName);
		return imgNameString + ".jpg";
	}
	/**
	 * 
	 * 
	 * @param uid
	 *            用户名
	 */
	/**
	 * 创建新文件名字
	 * 
	 * @param uid
	 *            用户名
	 * @param suffix
	 *            文件后缀
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String createNewFileName(long uid, String suffix) {
		String formartString = "yyyyMMddHHmmss";
		SimpleDateFormat format = new SimpleDateFormat(formartString);
		String dateString = format.format(new Date());
		String randomString = "" + new Random().nextDouble();
		String srcFileName = "" + uid + dateString + randomString;
		String imgNameString = Encrypt.MD5(srcFileName);
		return imgNameString + "." + suffix;
	}
}
