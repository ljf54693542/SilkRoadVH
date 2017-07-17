package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.rongshoo.silkroadvh.Adapter.RoomAdaper;
import com.rongshoo.silkroadvh.Adapter.RoomChoiceAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.bean.GoodsDetailBean;
import com.rongshoo.silkroadvh.bean.RoomBean;
import com.rongshoo.silkroadvh.utils.FrescoImageLoader;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.dinus.com.itemdecoration.GridDividerItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class HotelRoomDetailActivity extends BaseActivity {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.goods_name_tv)
    TextView mGoodsNameTv;
    @BindView(R.id.room_rv)
    RecyclerView mRoomRv;
    @BindView(R.id.choice_part_line)
    View mChoicePartLine;
    @BindView(R.id.choice_rv)
    RecyclerView mChoiceRv;
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
    @BindView(R.id.addr_tv)
    TextView mAddrTv;
    @BindView(R.id.phone_tv)
    TextView mPhoneTv;
    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.tab_img)
    ImageView mTabImg;
    @BindView(R.id.goods_shop_msg_tv)
    TextView mGoodsShopMsgTv;
    @BindView(R.id.goods_shop_msg_ll)
    LinearLayout mGoodsShopMsgLl;
    @BindView(R.id.go_buy_tv)
    TextView mGoBuyTv;
    @BindView(R.id.choice_ll)
    LinearLayout mChoiceLl;

    List<RoomBean.DataBean.RecordBean> rData = new ArrayList<>();
    List<RoomBean.DataBean.RecordBean> cData = new ArrayList<>();
    GoodsDetailBean.DataBean data;
    RoomAdaper mAdaper;
    @BindView(R.id.call_img)
    ImageView mCallImg;
    private String pID;
    private List<String> images = new ArrayList<>();
    private RoomChoiceAdaper mChoiceAdapter;


    private RoomBean.DataBean shopData;


    public static void startHotelRoomDetailActivity(Context context, String typeId) {
        Intent intent = new Intent(context, HotelRoomDetailActivity.class);
        intent.putExtra(SortActivity.TYPE, typeId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_room_detail);
        ButterKnife.bind(this);
        pID = getIntent().getExtras().getString(SortActivity.TYPE);
        mChoiceLl.setVisibility(View.GONE);
        initRv();
        initCrv();
        showLoadingDialog("加载中...", false);
        postData();
        postrData();
    }

    void initRv() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(this,
                GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
        dividerItemDecoration.setVerticalDivider(this.getResources().getDrawable(R.drawable.room_grid_partline));
        dividerItemDecoration.setHorizontalDivider(this.getResources().getDrawable(R.drawable.room_grid_partline));
        mRoomRv.setLayoutManager(layoutManager);
        mRoomRv.addItemDecoration(dividerItemDecoration);
        mRoomRv.setNestedScrollingEnabled(false);
        mAdaper = new RoomAdaper(rData);
        mRoomRv.setAdapter(mAdaper);
        mRoomRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (Integer.parseInt(rData.get(position).getIstock()) > 0) {
                    if (rData.get(position).getChecked()) {
                        rData.get(position).setChecked(false);
                        rData.get(position).setChoiceNum(0);
                        cData.remove(rData.get(position));
                    } else {
                        rData.get(position).setChecked(true);
                        rData.get(position).setChoiceNum(1);
                        cData.add(rData.get(position));
                    }
                    mAdaper.notifyDataSetChanged();
                  //  showToast("" + cData.size());
                    if (cData.size() > 0) {
                        mChoiceLl.setVisibility(View.VISIBLE);
                    } else {
                        mChoiceLl.setVisibility(View.GONE);
                    }
                    mChoiceAdapter.notifyDataSetChanged();
                } else {
                    rData.get(position).setChecked(false);
                }

            }
        });


    }


    void initCrv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mChoiceRv.setLayoutManager(layoutManager);
        mChoiceAdapter = new RoomChoiceAdaper(cData);
        mChoiceRv.setAdapter(mChoiceAdapter);
    }

    void buildBannerData(String str) {
        String[] strs = str.split(",");
        for (int i = 0, len = strs.length; i < len; i++) {
            images.add(strs[i]);
            //    System.out.println(strs[i].toString());
        }
        initBanner();
    }

    private void initBanner() {
        mBanner.setDelayTime(5000);
        mBanner.setImages(images).setImageLoader(new FrescoImageLoader()).start();

    }

    void bindingData() {
        if (data.getSpic().length() > 0) {
            buildBannerData(data.getSpic());
        }
        mGoodsNameTv.setText(data.getSproductname());
        mWeb.loadUrl(data.getSproducturl());
        String num = "<font color='#333333' size='10'>商品评价</font>" + "<font color='#888888' size='5'>(" + 500 + ")</font>";
        mAppraiseNumTv.setText(Html.fromHtml(num));
        // mAddrTv.setText(data.gets);
    }

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

    void postrData() {
        Map<String, String> map = new HashMap<>();
        map.put("iproductid", pID);
        String s = new Gson().toJson(map);
        OkHttpUtils.postString().url(Constant.HOTEL_ROOM)
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
                RoomBean bean = new Gson().fromJson(response, RoomBean.class);
                if (bean.getCode().equals("1")) {
                    shopData = bean.getData();
                    mAddrTv.setText(shopData.getSaddress());
                    mPhoneTv.setText(shopData.getSphone());
                    rData = bean.getData().getRecord();
                    for (int i = 0; i < rData.size(); i++) {
                        rData.get(i).setChecked(false);
                        rData.get(i).setChoiceNum(0);
                    }
                    mAdaper.setNewData(rData);
                }
            }
        });
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
