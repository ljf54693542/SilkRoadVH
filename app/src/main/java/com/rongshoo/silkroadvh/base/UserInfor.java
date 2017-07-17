package com.rongshoo.silkroadvh.base;

/**
 * Created by RS-KXH on 2017/5/12.
 */

public class UserInfor {
//    private String userId;
//    private Boolean sex = false;
    private String userNickname;//大平台
    private String userPhone;//大平台
    private String avatar;//大平台
    private String token;//大平台
    private String refreshToken;//大平台
    private String wxopenid;//大平台
    private int id = 0;//大平台
    private String email;//大平台
    private String addtime;//大平台


    //    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//    public Boolean getSex() {
//        return sex;
//    }
//
//    public void setSex(Boolean sex) {
//        this.sex = sex;
//    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }



    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
