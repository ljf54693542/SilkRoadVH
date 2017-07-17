package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.rongshoo.silkroadvh.Adapter.HotelAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.bean.GoodsListBean;
import com.rongshoo.silkroadvh.bean.ShopBean;
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

public class HotelActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
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

    private HotelAdaper mAdaper;
    private ShopBean.DataBean.RowsBean data;
    private List<GoodsListBean.DataBean.RowsBean> gData = new ArrayList<>();
    private SimpleDraweeView mDraweeView;
    private TextView titleTv;
    private TextView inforTv;

    public static void startHotelActivity(Context context) {
        // int typeId,
        Intent intent = new Intent(context, HotelActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        ButterKnife.bind(this);
        mTitleTv.setText("集散中心酒店");
        initRv();
        showLoadingDialog("加载中...", false);
        postData();
    }

    void initRv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);
        View header = LayoutInflater.from(this).inflate(R.layout.hotel_head, null);
        initHeader(header);
        mAdaper = new HotelAdaper(gData);
        mAdaper.addHeaderView(header);
        mAdaper.setOnLoadMoreListener(this);
        mRv.setAdapter(mAdaper);
        mRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                HotelRoomDetailActivity.startHotelRoomDetailActivity(HotelActivity.this, gData.get(position).getIproductid());
            }
        });
    }

    void initHeader(View view) {
        mDraweeView = (SimpleDraweeView) view.findViewById(R.id.img_sdv);
        titleTv = (TextView) view.findViewById(R.id.title_tv);
        inforTv = (TextView) view.findViewById(R.id.infor_tv);
    }

    void postData() {
        Map<String, String> map = new HashMap<>();
        map.put("ishoptype", "2");
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

    private int pageIndex = 1;//从1开始
    private int pageSize = 20;
    private int delayMillis = 1000;
    private int recordcount = 0;
    private Boolean hasNext = false;
    private int usedCount = 0;

    void postgData() {
        Map<String, String> map = new HashMap<>();
        map.put("ishopid", "" + data.getIshopid());
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
        mTitleTv.setText(data.getSshopname());
        titleTv.setText(data.getSshopname());
        inforTv.setText(data.getSdes());
        if (!TextUtils.isEmpty(data.getSimgurl())) {
            mDraweeView.setImageURI(data.getSimgurl());
        } else {
            mDraweeView.setImageURI("res://  /" + R.mipmap.default_middle_img);
        }
        showLoadingDialog("加载中...", false);
        postgData();
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

    @OnClick(value = {R.id.go_back})
    void onMainClick(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                this.finish();
                break;
        }
    }
}
