package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dinuscxj.itemdecoration.LinearDividerItemDecoration;
import com.google.gson.Gson;
import com.rongshoo.silkroadvh.Adapter.ShopCateAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.bean.GoodsListBean;
import com.rongshoo.silkroadvh.bean.ShopBean;
import com.rongshoo.silkroadvh.utils.FrescoImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class ShopCateActivity extends BaseActivity implements OnBannerListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.shop_name_tv)
    TextView mShopNameTv;
    @BindView(R.id.shop_star)
    RatingBar mShopStar;
    @BindView(R.id.shop_score)
    TextView mShopScore;
    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.attention)
    ImageView mAttention;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.call_img)
    ImageView mCallImg;
    @BindView(R.id.goods_shop_detail_tv)
    TextView mGoodsShopDetailTv;
    @BindView(R.id.goods_shop_call_ll)
    LinearLayout mGoodsShopCallLl;
    @BindView(R.id.tab_img)
    ImageView mTabImg;
    @BindView(R.id.goods_shop_msg_tv)
    TextView mGoodsShopMsgTv;
    @BindView(R.id.goods_shop_msg_ll)
    LinearLayout mGoodsShopMsgLl;
    @BindView(R.id.addr_tv)
    TextView mAddrTv;
    @BindView(R.id.phone_tv)
    TextView mPhoneTv;
    private List<String> images = new ArrayList<>();
 //   private int typeId = 0;
    private ShopCateAdaper mAdaper;
    private String shopID;
    private ShopBean.DataBean.RowsBean data;

    private int pageIndex = 1;//从1开始
    private int pageSize = 20;
    private int delayMillis = 1000;
    private int recordcount = 0;
    private Boolean hasNext = false;
    private int usedCount = 0;
    private List<GoodsListBean.DataBean.RowsBean> gData = new ArrayList<>();

    public static void startShopCateActivity(Context context,  String shopID) {
       // int typeId,
        Intent intent = new Intent(context, ShopCateActivity.class);
      //   intent.putExtra(SortActivity.TYPE, typeId);
        intent.putExtra("shopID", shopID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cate);
        ButterKnife.bind(this);
        initRv();
        // initBanner();
        showLoadingDialog("加载中...", false);
        postData();
        postgData();
    }

    void initRv() {
        //typeId = getIntent().getExtras().getInt(SortActivity.TYPE);
        shopID = getIntent().getExtras().getString("shopID");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);
        //多item 边界绘制异常
        LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(
                this, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        dividerItemDecoration.setDivider(getResources().getDrawable(R.drawable.partline_goods));
        mRv.addItemDecoration(dividerItemDecoration);
//        for (int i = 0; i < 10; i++) {
//            Map<String, Object> map = new HashMap<>();
//            gData.add(map);
//        }
        mAdaper = new ShopCateAdaper(gData);
        mAdaper.setOnLoadMoreListener(this);
        mRv.setAdapter(mAdaper);
        mRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopCateDetailActivity.startShopCateDetailActivity(ShopCateActivity.this, gData.get(position).getIproductid());
            }
        });
    }

    void postData() {
        Map<String, String> map = new HashMap<>();
        map.put("ishopid", "" + shopID);
        String s = new Gson().toJson(map);
        OkHttpUtils.postString().url(Constant.SHOP_LIST)
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
                ShopBean bean = new Gson().fromJson(response, ShopBean.class);
                if (bean.getCode().equals("1")) {
                    if (bean.getData().getRows().size() > 0) {
                        List<ShopBean.DataBean.RowsBean> tData = new ArrayList<ShopBean.DataBean.RowsBean>();
                        tData = bean.getData().getRows();
                        data = tData.get(0);
                        bindingData();
                    }
                }
            }
        });

    }

    void postgData() {
        Map<String, String> map = new HashMap<>();
        map.put("ishopid", "" + shopID);
        map.put("pageindex", "" + pageIndex);
        map.put("pagesize", "" + pageSize);
        String s = new Gson().toJson(map);
        OkHttpUtils.postString().url(Constant.GOODS_LIST)
                .content(s).mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                hideDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                hideDialog();
                GoodsListBean bean = new Gson().fromJson(response, GoodsListBean.class);
                if (bean.getCode().equals("1")) {
                    if (pageIndex == 1) {
                        usedCount = 0;
                        recordcount = bean.getData().getRecordcount();
                        ((LinearLayoutManager) mRv.getLayoutManager()).scrollToPosition(0);
                    }
                    if (bean.getData().getRows().size() > 0) {
                        List<GoodsListBean.DataBean.RowsBean> tData = new ArrayList<GoodsListBean.DataBean.RowsBean>();
                        tData = bean.getData().getRows();
                        usedCount = usedCount + tData.size();
                        gData.addAll(tData);
                        if (recordcount > usedCount) {
                            hasNext = true;
                            mAdaper.loadMoreComplete();
                        } else {
                            hasNext = false;
                            mAdaper.loadMoreEnd();
                        }
                        mAdaper.setEnableLoadMore(hasNext);
                        mAdaper.notifyDataSetChanged();
                    }

                }
            }
        });


    }

    void bindingData() {
        mShopNameTv.setText(data.getSshopname());
        mShopStar.setRating(Float.parseFloat(data.getDscore()));
        mAddrTv.setText(data.getSaddress());
        mPhoneTv.setText(data.getSphone());
        mShopScore.setText("" + Float.parseFloat(data.getDscore()));
        initBanner();
    }

    private void initBanner() {
//        if (typeId == 3) {
//            images.add("http://pic.90sjimg.com/back_pic/qk/back_origin_pic/00/03/79/cde475c92fa805d0ce1688e563686aab.jpg");
//            // images.add("http://img.zcool.cn/community/01b9cc56c944af32f875520f21128d.jpg@900w_1l_2o_100sh.jpg");
//        } else {
//            images.add("http://www.springhill.com.tw/upload/banner_ins_list/ba843a650452e4e4c760d8e381a57564.jpg");
//            // images.add("http://pic116.nipic.com/file/20161122/24310187_190748206000_2.jpg");
//        }
        if (!TextUtils.isEmpty(data.getSimgurl())) {
            images.add(data.getSimgurl());
        } else {
            images.add("res://  /" + R.mipmap.default_middle_img);
        }
        mBanner.setDelayTime(5000);
        mBanner.setImages(images).setImageLoader(new FrescoImageLoader()).setOnBannerListener(this).start();
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @OnClick(value = {R.id.go_back, R.id.attention, R.id.search, R.id.goods_shop_msg_ll, R.id.goods_shop_detail_tv})
    void onMainClick(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                this.finish();
                break;
            case R.id.attention:
                showToast("关注店铺------待开发");
                break;
            case R.id.search:
                break;
            case R.id.goods_shop_detail_tv:
                showToast("拨打电话------待开发");
                break;

            case R.id.goods_shop_msg_ll:
                showToast("呼叫小二------待开发");
                break;

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public void onLoadMoreRequested() {
        mRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageIndex++;
                postData();
            }
        }, delayMillis);
    }
}
