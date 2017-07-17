package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.bean.AttrBean;
import com.rongshoo.silkroadvh.bean.GoodsDetailBean;
import com.rongshoo.silkroadvh.utils.FrescoImageLoader;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class ShopCateDetailActivity extends BaseActivity {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.go_cart)
    ImageView mGoCart;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.goods_name_tv)
    TextView mGoodsNameTv;
    @BindView(R.id.price_tv)
    TextView mPriceTv;
    @BindView(R.id.price_old_tv)
    TextView mPriceOldTv;
    @BindView(R.id.sale_tv)
    TextView mSaleTv;
    @BindView(R.id.call_img)
    ImageView mCallImg;
    @BindView(R.id.appraise_num_tv)
    TextView mAppraiseNumTv;
    @BindView(R.id.avatar_sdv)
    SimpleDraweeView mAvatarSdv;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.content_tv)
    TextView mContentTv;
    @BindView(R.id.time_tv)
    TextView mTimeTv;
    @BindView(R.id.appraise_all_tv)
    TextView mAppraiseAllTv;
    @BindView(R.id.appraise_ll)
    LinearLayout mAppraiseLl;
    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.tab_img)
    ImageView mTabImg;
    @BindView(R.id.tab_name)
    TextView mTabName;
    @BindView(R.id.service_ll)
    LinearLayout mServiceLl;
    @BindView(R.id.shop_ll)
    LinearLayout mShopLl;
    @BindView(R.id.collect_ll)
    LinearLayout mCollectLl;
    @BindView(R.id.add_cart_tv)
    TextView mAddCartTv;
    @BindView(R.id.buy_tv)
    TextView mBuyTv;
    @BindView(R.id.root_ll)
    LinearLayout mRootLl;
    private List<String> images = new ArrayList<>();
    private String pID;

    public static void startShopCateDetailActivity(Context context, String typeId) {
        Intent intent = new Intent(context, ShopCateDetailActivity.class);
        intent.putExtra(SortActivity.TYPE, typeId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cate_detail);
        ButterKnife.bind(this);
        pID = getIntent().getExtras().getString(SortActivity.TYPE);
        // init();
        showLoadingDialog("加载中...", false);
        postData();
    }

//    void init() {
//        mWeb.loadUrl("http://view.inews.qq.com/a/20170313A022QT00");
//        String num = "<font color='#333333' size='10'>商品评价</font>" + "<font color='#888888' size='5'>(" + 500 + ")</font>";
//        mAppraiseNumTv.setText(Html.fromHtml(num));
//        initBanner();
//        mPriceOldTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
//    }

    private void initBanner() {
//        images.add("res://  /" + R.mipmap.ic_goods_test1);
//        images.add("res://  /" + R.mipmap.ic_goods_test2);
//        images.add("res://  /" + R.mipmap.ic_goods_test3);
//        images.add("res://  /" + R.mipmap.ic_goods_test4);
//        images.add("http://article.fd.zol-img.com.cn/t_s640x2000/g1/M07/0C/0D/Cg-4jVOpQWmIVPnYAAHFv0U3gXkAAOjOwAtyyUAAcXX661.jpg");
//        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        mBanner.setDelayTime(5000);
        mBanner.setImages(images).setImageLoader(new FrescoImageLoader()).start();

    }


    GoodsDetailBean.DataBean data;

    void postData() {
        Map<String, String> map = new HashMap<>();
        map.put("iproductid", pID);
        String s = new Gson().toJson(map);
        OkHttpUtils.postString().url(Constant.GOODS_DETAIL)
                .content(s).mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                hideDialog();
                String a = e.getMessage();
            }

            @Override
            public void onResponse(String response, int id) {
                hideDialog();
                GoodsDetailBean bean = new Gson().fromJson(response, GoodsDetailBean.class);
                if (bean.getCode().equals("1")) {
                    data = bean.getData();
                    bindingData();
                }
            }
        });
    }


    void bindingData() {
        if (data.getSpic().length() > 0) {
            buildBannerData(data.getSpic());
        }
        mGoodsNameTv.setText(data.getSproductname());
        mPriceTv.setText(data.getDrealprice());
        mPriceOldTv.setText(data.getDprice());
        mSaleTv.setText("已售" + data.getIsales());
        mWeb.loadUrl(data.getSproducturl());
        String num = "<font color='#333333' size='10'>商品评价</font>" + "<font color='#888888' size='5'>(" + 500 + ")</font>";
        mAppraiseNumTv.setText(Html.fromHtml(num));
        mPriceOldTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        Type listType = new TypeToken<ArrayList<AttrBean>>() {
        }.getType();
        //Log.d("a", jsonArray.toString());
    }

    void buildBannerData(String str) {
        String[] strs = str.split(",");
        for (int i = 0, len = strs.length; i < len; i++) {
            images.add(strs[i]);
            //    System.out.println(strs[i].toString());
        }
        initBanner();
    }

    @OnClick(value = {R.id.go_back, R.id.go_cart, R.id.appraise_all_tv, R.id.shop_ll, R.id.service_ll, R.id.collect_ll, R.id.add_cart_tv, R.id.buy_tv})
    void onMainClick(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                this.finish();
                break;
            case R.id.go_cart:
                break;

            case R.id.appraise_all_tv:
                showToast("商品评价------待开发");
                break;
            case R.id.shop_ll:
                ShopCateActivity.startShopCateActivity(ShopCateDetailActivity.this, data.getIshopid());
                break;
            case R.id.service_ll:
                showToast("联系小二------待开发");
                break;
            case R.id.collect_ll:
                showToast("收藏------待开发");
                break;
            case R.id.add_cart_tv:
                showToast("添加至购物车------待开发");
                break;
            case R.id.buy_tv:
                OrderConfirmActivity.startOrderConfirmActivity(this, 0);
                break;


        }

    }
}
