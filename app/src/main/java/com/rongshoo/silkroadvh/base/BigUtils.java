package com.rongshoo.silkroadvh.base;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rongshoo.silkroadvh.bean.BGetUserBean;
import com.rongshoo.silkroadvh.bean.BRefreshToken;
import com.rongshoo.silkroadvh.utils.EncodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by RS-KXH on 2017/5/12.
 */

public class BigUtils {
    public static void checkToken() {
        if (ActivityCollector.getTopActivity() == null) return;
        final String refreshToken = ShareUtils.getUser(ActivityCollector.getTopActivity()).getRefreshToken();
        OkHttpUtils.get().url(Constant.BIG_REFRESH_TOKEN + refreshToken).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                String a = e.getMessage();
                Log.d("Error", a);
            }

            @Override
            public void onResponse(String response, int id) {
                BRefreshToken bean = new Gson().fromJson(response, BRefreshToken.class);
                if (ActivityCollector.getTopActivity() == null) return;
                if (bean.getCode().equals("000000")) {
                    UserInfor userInfor = ShareUtils.getUser(ActivityCollector.getTopActivity());
                    if (userInfor != null) {
                        userInfor.setToken(bean.getResult().getToken());
                        userInfor.setRefreshToken(bean.getResult().getRefreshtoken());
                        ShareUtils.saveUser(ActivityCollector.getTopActivity(), userInfor);
                        getUserInforFromB(userInfor.getToken());
                    }
                } else {
                    Toast.makeText(ActivityCollector.getTopActivity(), "它是一个假的Token,请登录！！！", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    /**
     * 从大平台获取用户信息
     */
    public static void getUserInforFromB(String token) {
        OkHttpUtils.get().url(Constant.BIG_GET_USERINFOR + token)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                String a = e.getMessage();
                Log.d("Error", a);
            }

            @Override
            public void onResponse(String response, int id) {
                BGetUserBean bean = new Gson().fromJson(response, BGetUserBean.class);
                if (ActivityCollector.getTopActivity() == null) return;
                if (bean.getCode().equals("000000")) {
                    BGetUserBean.ResultBean myBean = bean.getResult();
                    UserInfor user = ShareUtils.getUser(ActivityCollector.getTopActivity());
                    user.setAddtime(myBean.getAddtime());
                    user.setUserNickname(EncodeUtils.urlDecode(myBean.getNickname()));
                    user.setEmail(myBean.getEmail());
                    user.setWxopenid(myBean.getWxopenid());
                    user.setAvatar(EncodeUtils.urlDecode(myBean.getAvatar()));
                    user.setId(myBean.getId());
                    user.setUserPhone(myBean.getMobile());
                    ShareUtils.saveUser(ActivityCollector.getTopActivity(), user);
                } else {
                    Toast.makeText(ActivityCollector.getTopActivity(), "服务器出问题啦，程序猿正在修复中...", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}
