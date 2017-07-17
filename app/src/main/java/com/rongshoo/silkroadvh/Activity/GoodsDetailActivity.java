package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rongshoo.silkroadvh.Adapter.GoodsAttrAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.bean.AttrBean;
import com.rongshoo.silkroadvh.bean.GoodsDetailBean;
import com.rongshoo.silkroadvh.bean.SortBean;
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

public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener, GoodsAttrAdaper.UpdateListener {

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
    @BindView(R.id.attr_iv)
    ImageView mAttrIv;
    @BindView(R.id.attr_ll)
    LinearLayout mAttrLl;
    @BindView(R.id.addr_tv)
    TextView mAddrTv;
    @BindView(R.id.addr_iv)
    ImageView mAddrIv;
    @BindView(R.id.addr_lin)
    LinearLayout mAddrLin;
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
    @BindView(R.id.appraise_num_tv)
    TextView mAppraiseNumTv;
    @BindView(R.id.root_ll)
    LinearLayout mRootLl;
    @BindView(R.id.tab_img)
    ImageView mTabImg;
    @BindView(R.id.tab_name)
    TextView mTabName;
    @BindView(R.id.shop_ll)
    LinearLayout mShopLl;
    @BindView(R.id.collect_ll)
    LinearLayout mCollectLl;
    @BindView(R.id.add_cart_tv)
    TextView mAddCartTv;
    @BindView(R.id.buy_tv)
    TextView mBuyTv;
    @BindView(R.id.service_ll)
    LinearLayout mServiceLl;
    private GoodsAttrAdaper mAttrAdaper;
    private List<SortBean> aData = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    private PopupWindow pop;
    private SimpleDraweeView mDraweeView;
    private TextView pPriceTv;
    private TextView pNameTv;
    private RecyclerView pRv;
    private ImageView minusIv;
    private ImageView addIv;
    private TextView numTv;
    private TextView confirmTv;
    private String pID;

    public static void startGoodsDetailActivity(Context context, String pid) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra(SortActivity.TYPE, pid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        pID = getIntent().getExtras().getString(SortActivity.TYPE);
        //    init();
        initPop();
        showLoadingDialog("加载中...", false);
        postData();
    }

//    void init() {
//        mWeb.loadUrl("http://view.inews.qq.com/a/20170313A022QT00");
//        String num = "<font color='#333333' size='10'>商品评价</font>" + "<font color='#888888' size='5'>(" + 500 + ")</font>";
//        mAppraiseNumTv.setText(Html.fromHtml(num));
////        initBanner();
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


    void initPopView(View view) {
        mDraweeView = (SimpleDraweeView) view.findViewById(R.id.goods_img);
        pPriceTv = (TextView) view.findViewById(R.id.price_tv);
        pNameTv = (TextView) view.findViewById(R.id.goods_name_tv);
        pRv = (RecyclerView) view.findViewById(R.id.my_rv);
        confirmTv = (TextView) view.findViewById(R.id.buy_tv);
        confirmTv.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        pRv.setLayoutManager(layoutManager);
//        for (int i = 0; i < 3; i++) {
//            SortBean bean = new SortBean("规格" + i, 0, false);
//            aData.add(bean);
//        }
        mAttrAdaper = new GoodsAttrAdaper(attrData);
        View footer = LayoutInflater.from(this).inflate(R.layout.goods_attr_footer, null);
        minusIv = (ImageView) footer.findViewById(R.id.iv_item_shopcart_cloth_minus);
        addIv = (ImageView) footer.findViewById(R.id.iv_item_shopcart_cloth_add);
        numTv = (TextView) footer.findViewById(R.id.et_item_shopcart_cloth_num);
        mAttrAdaper.addFooterView(footer);
        pRv.setAdapter(mAttrAdaper);
    }

    void initPop() {
        final View popView = LayoutInflater.from(this).inflate(R.layout.goods_detail_attr, null);
        int height = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.7);   //ViewGroup.LayoutParams.WRAP_CONTENT
        pop = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, height);
        initPopView(popView);
        //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
        pop.setFocusable(true);
        //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
        pop.setBackgroundDrawable(new BitmapDrawable());
        //防止虚拟软键盘被弹出菜单遮住
        pop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        pop.setOutsideTouchable(true);
        pop.setOnDismissListener(new poponDismissListener());
    }

    GoodsDetailBean.DataBean data;
    private String choiceStr;
    List<AttrBean> attrData = new ArrayList<>();

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
        attrData = new Gson().fromJson(data.getSsubsign(), listType);
        mAttrAdaper.setNewData(attrData);
        pNameTv.setText(data.getSproductname());
        pPriceTv.setText(data.getDrealprice());
        if (data.getSimgurl() != null && data.getSimgurl().length() > 0) {
            mDraweeView.setImageURI(Uri.parse(data.getSimgurl()));
        }
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


    @Override
    public void update() {
        mAttrChoice();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_tv:
                break;
        }
    }

    GoodsDetailBean.DataBean.SubBean sBean;
    private int maxStock = 0;

    private void mAttrChoice() {
        choiceStr = null;
        for (int i = 0; i < mAttrAdaper.getData().size(); i++) {
            AttrBean bean = mAttrAdaper.getData().get(i);
            if (choiceStr == null && bean.getChoiceStr() != null) {
                choiceStr = bean.getChoiceStr();
            } else {
                choiceStr = choiceStr + "," + bean.getChoiceStr();
            }

        }
        showToast(choiceStr);
        showLoadingDialog("", false);
        for (int i = 0; i < data.getSub().size(); i++) {
            if (choiceStr != null && data.getSub().get(i).getSversion().equals(choiceStr)) {
                sBean = data.getSub().get(i);
                break;
            }
        }
        if (sBean != null) {
            if (!TextUtils.isEmpty(sBean.getSimgurrl())) {
                mDraweeView.setImageURI(Uri.parse(sBean.getSimgurrl()));
            } else {
                mDraweeView.setImageURI("res://  /"+R.mipmap.default_middle_img);
            }
            maxStock = Integer.parseInt(sBean.getIstock());
        }
        hideDialog();
    }


    @OnClick(value = {R.id.go_back, R.id.go_cart, R.id.addr_lin, R.id.attr_ll, R.id.appraise_all_tv, R.id.shop_ll, R.id.service_ll, R.id.collect_ll, R.id.add_cart_tv, R.id.buy_tv})
    void onMainClick(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                this.finish();
                break;
            case R.id.go_cart:
                break;
            case R.id.addr_lin:
                showToast("收货地址------待开发");
                break;
            case R.id.attr_ll:
                //showToast("商品规格------待开发");
                if (pop != null && !pop.isShowing()) {
                    pop.showAtLocation(findViewById(R.id.root_ll), Gravity.BOTTOM, 0, 0);
                    backgroundAlpha(0.5f);
                }
                break;
            case R.id.appraise_all_tv:
                showToast("商品评价------待开发");
                break;
            case R.id.shop_ll:
                GoodsShopActivity.startGoodsShopActivity(this, 0);
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


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    /**
     * 将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

}
