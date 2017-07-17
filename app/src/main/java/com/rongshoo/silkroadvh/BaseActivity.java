package com.rongshoo.silkroadvh;


import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.rongshoo.silkroadvh.View.LoadingDialog;
import com.rongshoo.silkroadvh.base.ActivityCollector;
import com.rongshoo.silkroadvh.base.PermissionListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RS-KXH on 2016/12/26.
 */
public class BaseActivity extends AppCompatActivity {
    private static PermissionListener mListener;
//    private SystemBarTintManager tintManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //状态栏透明设置
        Window window = getWindow();
        //4.4版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //5.0版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
//        tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
        //将Activity加入管理器
        ActivityCollector.addActivity(this);
     //   getWindow().setBackgroundDrawableResource(android.R.color.transparent);//移除窗口背景


    }

//    public void setStatusBarColor(int rId) {
//        tintManager.setStatusBarTintResource(rId);
//    }
//
//    public void setStatusBarDrawable(Drawable drawable) {
//        tintManager.setTintDrawable(drawable);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将Activity移除管理器
        ActivityCollector.removeActivity(this);
    }

    //---------------------------------------Android 6.0 运行时权限封装--------------------------------------


    /**
     * 请求运行时权限
     *
     * @param permissions
     * @param listener
     */
    public static void requestRuntimePermission(String[] permissions, PermissionListener listener) {
        Activity topActivity = ActivityCollector.getTopActivity();
        if (topActivity == null) return;
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(topActivity, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermission = new ArrayList<>();
                    for (int i = 0; i < permissions.length; i++) {
                        String permission = permissions[i];
                        int grantResult = grantResults[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermission.add(permission);
                        }
                    }
                    if (deniedPermission.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermission);
                    }

                }
                break;
        }
    }

    /**
     * 弹窗
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(ActivityCollector.getTopActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    static AlertDialog.Builder mDialog;// 权限提示弹窗

    public static AlertDialog.Builder getAlertDialog(final Activity activity, String msg, DialogInterface.OnClickListener mListener) {
        mDialog = new android.support.v7.app.AlertDialog.Builder(
                activity);
        mDialog.setTitle("权限被禁！");
        mDialog.setMessage(msg).setCancelable(false)
                .setPositiveButton("确认", mListener);
        return mDialog;
    }

    static AlertDialog.Builder dialog;

    public static AlertDialog.Builder getCheckDialog(String msg, DialogInterface.OnClickListener mListener) {
        dialog = new AlertDialog.Builder(ActivityCollector.getTopActivity());
        dialog.setMessage(msg);
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确认", mListener);
        return dialog;
    }


    static Dialog loadingDialog;

    public static void showLoadingDialog(String msg, boolean isCancel) {
        loadingDialog = LoadingDialog.createLoadingDialog(ActivityCollector.getTopActivity(), msg, isCancel);
        loadingDialog.show();
    }

    public static void hideDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

//    /**
//     * 验证固话号码
//     *
//     * @param telephone
//     * @return
//     */
//    public static boolean checkTelephone(String telephone) {
//        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
//        return check(telephone, regex);
//    }


    //2017-05-08 版号码
    //移动：134 135 136 137 138 139 147 150 151 152 157 158 159 172 178 182 183 184 187 188
    //联通：130 131 132 145 155 156 171 175 176 185 186
    //电信：133 149 153 173 177 180 181 189
    //虚拟运营商：170
    //
    private final static String REGEX_MOBILEPHONE = "^((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17([0-2]|[5-8]))|(18[0-9]))\\d{8}$";
    private final static String NICKNAME_OK = "^[a-zA-Z0-9\u4e00-\u9fa5~!@#$%^&*()_+]+$";
    private final static String PWD_OK = "^[a-zA-Z0-9~!@#$%^&*()_+]+$";
    private static Pattern PATTERN_MOBILEPHONE;// 调用系统样式

    static {
        PATTERN_MOBILEPHONE = Pattern.compile(REGEX_MOBILEPHONE);
    }

    private static Pattern code_Pattern;

    static {
        code_Pattern = Pattern.compile(NICKNAME_OK);
    }

    private static Pattern PWD_PATTERN;

    static {
        PWD_PATTERN = Pattern.compile(PWD_OK);
    }

    /**
     * 判断是否为手机号码
     *
     * @param number
     * @return
     */
    public static boolean isCellPhone(String number) {
        Matcher match = PATTERN_MOBILEPHONE.matcher(number);
        boolean b = match.matches();
        return b;
    }

    /**
     * 是否是合法的昵称（不包含特殊表情字符）
     *
     * @param nickName
     * @return
     */
    protected static boolean isNickOK(String nickName) {
        Matcher match = code_Pattern.matcher(nickName);
        boolean b = match.matches();
        return b;
    }

    /**
     * 检查用户密码输入
     *
     * @param pwd
     * @return
     */
    protected static boolean isPWDOK(String pwd) {
        Matcher match = PWD_PATTERN.matcher(pwd);
        boolean b = match.matches();
        return b;
    }

    /**
     * 日期统一格式
     */
    protected final static SimpleDateFormat format = new SimpleDateFormat(
            "yyyy-MM-dd");

    // yyyy-MM-dd HH:mm:ss

    /**
     * 获取今日日期
     *
     * @return
     */
    protected String getToday() {
        // Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String date = format.format(new java.util.Date());
        return date;
    }


    /**
     * 获取去重复的Lists
     *
     * @param fromList
     * @param referList
     * @return
     */
    public List<Map<String, Object>> getNoRepetitionList(
            List<Map<String, Object>> fromList,
            List<Map<String, Object>> referList) {
        for (Map<String, Object> map : fromList) {
            if (referList.contains(map)) {
                fromList.remove(map);
            }
        }
        return fromList;
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                v.clearFocus();
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 隐藏键盘
     *
     * @param v
     */
    protected void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    /**
     * 判断当前网络是否是wifi网络
     * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) { //判断3G网
     *
     * @param context
     * @return boolean
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前网络是否是移动网络
     *
     * @param context
     * @return boolean
     */
    public static boolean isMobileNet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否有网
     *
     * @param context
     * @return
     */
    public static boolean checkNet(Context context) {

        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {

                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {

                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * 安卓7.0裁剪根据文件路径获取uri
     */
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

}
