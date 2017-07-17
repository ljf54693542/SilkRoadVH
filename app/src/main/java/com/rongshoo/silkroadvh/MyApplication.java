package com.rongshoo.silkroadvh;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.rongshoo.silkroadvh.base.AppFilePath;
import com.rongshoo.silkroadvh.base.FrescoImagePipelineConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by RS-KXH on 2016/12/26.
 */

public class MyApplication extends Application {
    private static Context mContext;
    private final int HANDLER_UPLOAD_LOGCAT = 0x01;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (msg.what==HANDLER_UPLOAD_LOGCAT) {
                File file = new File(AppFilePath.LOGCAT_PATH);
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    postLogcat(files[i]);
                }
            }
        };
    };
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //网络请求基本框架配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        //Fresco 图片加载框架配置
        Fresco.initialize(this, FrescoImagePipelineConfig.getConfig(this, okHttpClient));
//        // 错误日志收集
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
//        //检查错误日志并上传
//        new Thread(new Runnable(){
//
//            @Override
//            public void run() {
//                uploadLogcat();
//            }
//        }).start();

    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 检查错误日志文件
     */
    private void uploadLogcat(){
        File file = new File(AppFilePath.LOGCAT_PATH);
        String[] filesName = null;
        if (!file.exists())
            return;
        filesName = file.list();
        if (filesName == null) {
            return;
        }
        handler.sendEmptyMessage(HANDLER_UPLOAD_LOGCAT);
    }

    /**
     * @param file
     * 上传错误日志
     */
    private void postLogcat(File file) {
        OkHttpUtils.post().addFile("file", file.getName(), file)
                .url("文件下载地址").build()
                .execute(new StringCallback() {

                    @Override
                    public void onResponse(String result, int arg1) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String jString=jsonObject.getString("data");
                            JSONObject jsObject=new JSONObject(jString);
                            if (jsObject.getBoolean("result")) {
                                String name = jsObject.getString("fileName");
                                String path = AppFilePath.LOGCAT_PATH + name;
                                File file = new File(path);
                                if (file.exists())
                                    file.delete();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Log.d("BUG", arg1.toString());
                    }
                });
    }
}
