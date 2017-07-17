package com.rongshoo.silkroadvh.bean;

import com.google.gson.Gson;

/**
 * Created by RS-KXH on 2017/5/9.
 */

public class FindPostBean {

    /**
     * code : 200
     * data : 2ffa0ac245d04a4ca58329fa065ecd19
     * msg : 成功！
     */

    private String code;
    private String data;
    private String msg;

    public static FindPostBean objectFromData(String str) {

        return new Gson().fromJson(str, FindPostBean.class);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
