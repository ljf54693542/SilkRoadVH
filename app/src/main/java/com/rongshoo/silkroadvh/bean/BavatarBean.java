package com.rongshoo.silkroadvh.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RS-KXH on 2017/5/15.
 */

public class BavatarBean {

    /**
     * code : 000000
     * msg : success
     * result : http%3A%2F%2Fimg.oborttc.com%2Favatar%2F2017%2F05%2F15%2F2017051510390700726.jpg
     */

    private String code;
    private String msg;
    private String result;

    public static BavatarBean objectFromData(String str) {

        return new Gson().fromJson(str, BavatarBean.class);
    }

    public static BavatarBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), BavatarBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<BavatarBean> arrayBavatarBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<BavatarBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<BavatarBean> arrayBavatarBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<BavatarBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
