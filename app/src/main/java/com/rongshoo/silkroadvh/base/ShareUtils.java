package com.rongshoo.silkroadvh.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by RS-KXH on 2017/5/12.
 */

public class ShareUtils {
    public static String authToken;//own
    public static void saveUser(Context context, UserInfor user) {
        SharedPreferences share = context.getSharedPreferences("UserInfor", Context.MODE_PRIVATE);
        Editor editor = share.edit();
        // editor.putString("userId", user.getUserId());
        editor.putString("userNickname", user.getUserNickname());
        editor.putString("avatar", user.getAvatar());
        editor.putString("token", user.getToken());
        editor.putString("refreshToken", user.getRefreshToken());
        // editor.putBoolean("sex", user.getSex());
        editor.putString("phoneNum", user.getUserPhone());
        editor.putString("wxopenid", user.getWxopenid());
        editor.putInt("id", user.getId());
        editor.putString("email", user.getEmail());
        editor.putString("addtime", user.getAddtime());
        editor.commit();
    }

    public static UserInfor getUser(Context context) {
        SharedPreferences share = context.getSharedPreferences("UserInfor", Context.MODE_PRIVATE);
        String token = share.getString("token", null);
        if (token == null) {
            return null;
        }
        UserInfor userInfor = new UserInfor();
        //  userInfor.setUserId(share.getString("userid", null));
        userInfor.setAvatar(share.getString("avatar", null));
        userInfor.setUserNickname(share.getString("userNickname", null));
        userInfor.setToken(share.getString("token", token));
        userInfor.setRefreshToken(share.getString("refreshToken", null));
        // userInfor.setSex(share.getBoolean("sex", false));
        userInfor.setUserPhone(share.getString("phoneNum", null));
        userInfor.setWxopenid(share.getString("wxopenid", null));
        userInfor.setId(share.getInt("id", 0));
        userInfor.setEmail(share.getString("email", null));
        userInfor.setAddtime(share.getString("addtime", null));
        return userInfor;
    }

    public static void cleanUser(Context context) {
        SharedPreferences share = context.getSharedPreferences("UserInfor", Context.MODE_PRIVATE);
        share.edit().clear().commit();
    }

}
