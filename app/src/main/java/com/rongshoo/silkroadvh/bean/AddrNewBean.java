package com.rongshoo.silkroadvh.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RS-KXH on 2017/5/16.
 */

public class AddrNewBean implements Serializable {

    /**
     * userId : c2b21d4234874c579a804849ea0cd149
     * receiver : 吕秀才
     * address : 四川省成都市郫县郫筒镇12-3
     * phoneNumber : 1234567890
     * zipCode : 1234
     * status : 0
     */

    private String userId;
    private String receiver;
    private String address;
    private String phoneNumber;
    private String zipCode;
    private String status;
    private String area;

    public static AddrNewBean objectFromData(String str) {

        return new Gson().fromJson(str, AddrNewBean.class);
    }

    public static AddrNewBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), AddrNewBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<AddrNewBean> arrayAddrNewBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<AddrNewBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<AddrNewBean> arrayAddrNewBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<AddrNewBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
