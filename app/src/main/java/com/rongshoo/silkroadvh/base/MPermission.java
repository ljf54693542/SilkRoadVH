package com.rongshoo.silkroadvh.base;

import android.Manifest;

public class MPermission {
    /**摄像头权限*/
	public static final String CAMERA= Manifest.permission.CAMERA;
	/**读取联系人权限*/
	public static final String READ_CONTACTS=Manifest.permission.READ_CONTACTS;
//	/**写入联系人权限*/
//	public static final String MP_WRITE_CONTACTS="Manifest.permission.WRITE_CONTACTS";
//	/**获取账号权限*/
//	public static final String MP_GET_ACCOUNTS="Manifest.permission.GET_ACCOUNTS";
	/**定位权限*/
	public static final String ACCESS_FINE_LOCATION=Manifest.permission.ACCESS_FINE_LOCATION;//  ACCESS_COARSE_LOCATION
	public static final String ACCESS_COARSE_LOCATION=Manifest.permission.ACCESS_COARSE_LOCATION;
	/**录音权限*/
	public static final String RECORD_AUDIO=Manifest.permission.RECORD_AUDIO;//	
	/**拨打电话权限*/
	public static final String CALL_PHONE=Manifest.permission.CALL_PHONE;//
	/**发送短信权限*/
	public static final String SEND_SMS=Manifest.permission.SEND_SMS;//
	/**写入权限*/
	public static final String WRITE_EXTERNAL_STORAGE=Manifest.permission.WRITE_EXTERNAL_STORAGE;
}