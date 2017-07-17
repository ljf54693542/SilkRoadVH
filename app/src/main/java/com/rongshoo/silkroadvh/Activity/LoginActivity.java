package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.MainActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.base.ShareUtils;
import com.rongshoo.silkroadvh.base.UserInfor;
import com.rongshoo.silkroadvh.utils.EncodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 大平台登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.go_back)
    ImageView mBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.progressBar1)
    ProgressBar mProgressBar1;
    @BindView(R.id.my_web)
    WebView mMyWeb;
    @BindView(R.id.web_root)
    LinearLayout mWebRoot;

    private int type = 0;
    private String token;
    private String refreshtoken;
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = msg.obj.toString();
            token = str.substring(str.indexOf('=') + 1, str.indexOf('&', 1));
            refreshtoken = str.substring(str.indexOf("&refreshtoken=") + 14);
            UserInfor user = new UserInfor();
            user.setToken(token);
            user.setRefreshToken(refreshtoken);
            ShareUtils.saveUser(LoginActivity.this, user);
            Log.d("asdfa", token + "\n" + refreshtoken);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("fromeLogin", true);
            intent.putExtra("token", token);
            startActivity(intent);
            LoginActivity.this.finish();
        }
    };

    public static void StartLoginActivity(Context context, int type) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        String url = EncodeUtils.urlEncode(Constant.LOGIN_RETURN_URL);
        type = getIntent().getExtras().getInt("type");
        initWebSetting();
        mMyWeb.clearHistory();
        mMyWeb.clearFormData();
        mMyWeb.clearCache(true);//清除缓存，清除
        switch (type) {
            case 0:
                url = Constant.BIG_lOGIN + url + "&view=m"; //登录
                break;
            case 1:
                url = Constant.BIG_REGISTER + url + "&view=m";//注册
                break;
            case 2:
                url = Constant.BIG_LOGOUT + url;//登出
                break;
        }

        mMyWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {//当页面加载完成
                super.onPageFinished(view, url);
                Log.d("地址", url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                switch (type) {
                    case 0:
                    case 1:
                        if (url.contains("?token=")) {
                            Log.d("adsfa", url);
                            Message msg = new Message();
                            msg.obj = url;
                            uiHandler.handleMessage(msg);
                        }
                        break;
                    case 2:
                        if (url.equals(Constant.LOGIN_RETURN_URL)) {
                            LoginActivity.this.finish();
                        }
                        break;

                }

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 接受所有网站的证书
            }

        });
        mMyWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar1.setProgress(newProgress);
                if (mProgressBar1 != null && newProgress != 100) {
                    mProgressBar1.setVisibility(View.VISIBLE);
                } else if (mProgressBar1 != null) {
                    mProgressBar1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }


        });
        mMyWeb.loadUrl(url);
    }


    void initWebSetting() {
        WebSettings webSettings = mMyWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);  //支持js
        webSettings.setPluginState(WebSettings.PluginState.ON);  //支持插件
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setAppCacheEnabled(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存

        //在Android 5.0上 Webview 默认不允许加载 Http 与 Https
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mMyWeb != null && mMyWeb.canGoBack()) {
                mMyWeb.goBack();
                return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //正确释放web
        mWebRoot.removeView(mMyWeb);
        mMyWeb.removeAllViews();
        mMyWeb.destroy();

    }

    @OnClick(value = R.id.go_back)
    void onClick(View view) {
        if (mMyWeb.canGoBack()) {
            mMyWeb.goBack();
        } else {
            finish();

        }

    }
}
