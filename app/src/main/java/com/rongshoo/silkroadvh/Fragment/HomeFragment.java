package com.rongshoo.silkroadvh.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.rongshoo.silkroadvh.Activity.HotelActivity;
import com.rongshoo.silkroadvh.Activity.LoginActivity;
import com.rongshoo.silkroadvh.Activity.ShopListActivity;
import com.rongshoo.silkroadvh.Activity.SortActivity;
import com.rongshoo.silkroadvh.Adapter.HomeAdaper;
import com.rongshoo.silkroadvh.Adapter.HomeSortAdaper;
import com.rongshoo.silkroadvh.Adapter.HomeSpecialAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.View.VpSwipeRefreshLayout;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.bean.HomeBean;
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
import okhttp3.Call;
import okhttp3.MediaType;

import static com.rongshoo.silkroadvh.BaseActivity.hideDialog;

public class HomeFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    // Unbinder unbinder;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.msg_btn)
    ImageView mMsgBtn;
    @BindView(R.id.msg_tv)
    TextView mMsgTv;
    @BindView(R.id.home_search)
    LinearLayout mHomeSearch;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.home_swipeLayout)
    VpSwipeRefreshLayout mHomeSwipeLayout;
    private View view;

    //    private Banner mBanner;
    private RecyclerView mSortRv;
    private RecyclerView mSpecialRv;
    private HomeSpecialAdaper mSpecialAdaper;
//    private LinearLayout mHeadll;
//    private LinearLayout mHeadll2;
//    private SimpleDraweeView mADone;
//    private SimpleDraweeView mADtwo;
//    private SimpleDraweeView mADthree;
//    private SimpleDraweeView mADfour;

    private HomeSortAdaper mSortAdaper;
    private List<Map<String, Object>> mSortData = new ArrayList<>();
    private HomeAdaper mAdapter;
    // private List<Map<String, Object>> mData = new ArrayList<>();
    private List<HomeBean.DataBean.ShoptypeBean> mData = new ArrayList<>();
    private List<HomeBean.DataBean.SpecialtypeBean> sData = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    private BaseActivity context;

    String[] sName = new String[]{"购物", "餐饮", "娱乐", "酒店", "丝路文化"};
    int[] mImg = new int[]{R.drawable.ic_main_shopping, R.drawable.ic_main_cate, R.drawable.ic_main_play, R.drawable.ic_main_hotel, R.drawable.ic_main_culture};

    private int page = 1;
    private int pageSize = 40;
    private int delayMillis = 1000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            ButterKnife.bind(this, view);
            context = (BaseActivity) getActivity();
            init();
            initBanner();
            initRv();
            postData();
        }
//        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
//        ViewGroup parent = (ViewGroup) view.getParent();
//        if (parent != null) {
//            parent.removeView(view);
//        }
        //      unbinder = ButterKnife.bind(this, view);
        return view;
    }


    public void init() {
        mHomeSwipeLayout.setOnRefreshListener(this);
        mHomeSwipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mAppbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    mHomeSwipeLayout.setEnabled(true);
                } else {
                    mHomeSwipeLayout.setEnabled(false);
                }
            }
        });
    }

    void initHeader(View view) {
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", sName[i]);
            map.put("img", mImg[i]);
            mSortData.add(map);
        }
        mBanner = (Banner) view.findViewById(R.id.banner);
        mBanner.setVisibility(View.GONE);
        mSortRv = (RecyclerView) view.findViewById(R.id.sort_rv);
//        mHeadll = (LinearLayout) view.findViewById(R.id.banner_home_ll);
//        mHeadll2 = (LinearLayout) view.findViewById(R.id.banner_home_ll2);
//        mADone = (SimpleDraweeView) view.findViewById(R.id.one_iv);
//        mADtwo = (SimpleDraweeView) view.findViewById(R.id.two_iv);
//        mADthree = (SimpleDraweeView) view.findViewById(R.id.three_iv);
//        mADfour = (SimpleDraweeView) view.findViewById(R.id.four_iv);
//        mADone.setImageURI(Uri.parse("res://  /" + R.mipmap.ic_ad));
//        mADtwo.setImageURI(Uri.parse("res://  /" + R.mipmap.ic_ad));
//        mADthree.setImageURI(Uri.parse("res://  /" + R.mipmap.ic_ad));
//        mADfour.setImageURI(Uri.parse("res://  /" + R.mipmap.ic_ad));
        final GridLayoutManager layoutManager = new GridLayoutManager(context, 5);
        mSortRv.setLayoutManager(layoutManager);
        mSortRv.setNestedScrollingEnabled(false);
        mSortAdaper = new HomeSortAdaper(mSortData);
        mSortRv.setAdapter(mSortAdaper);
        mSpecialRv = (RecyclerView) view.findViewById(R.id.specail_rv);
        final GridLayoutManager layoutManagerT = new GridLayoutManager(context, 2);
        mSpecialRv.setLayoutManager(layoutManagerT);
        mSpecialRv.setNestedScrollingEnabled(false);
        GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(context,
                GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
        dividerItemDecoration.setVerticalDivider(context.getResources().getDrawable(R.drawable.home_grid_partline_view_s));
        dividerItemDecoration.setHorizontalDivider(context.getResources().getDrawable(R.drawable.home_grid_partline_view_s));
        mSpecialRv.addItemDecoration(dividerItemDecoration);
        mSpecialAdaper = new HomeSpecialAdaper(sData);
        mSpecialRv.setAdapter(mSpecialAdaper);
        mSortRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    // 购物1、酒店2、餐饮3、娱乐4
                    case 0:
                        SortActivity.startSortActivity(context, 1);
                        break;
                    case 1:
                        ShopListActivity.startShopListActivity(context, 3);
                        break;
                    case 2:
                        ShopListActivity.startShopListActivity(context, 4);
                        break;
                    case 3://酒店
                        HotelActivity.startHotelActivity(context);
                        break;
                    case 4://丝路文化
                        context.showToast("丝路文化待开发");
                        LoginActivity.StartLoginActivity(context, 0);
                        break;
                }


            }
        });

    }

    void initRv() {
//        for (int i = 0; i < 4; i++) {
//            Map<String, Object> map = new HashMap<>();
//            if (i == 0) {
//                // R.mipmap.ic_ad_shopping,R.mipmap.ic_ad_cate,R.mipmap.ic_ad_hotel,R.mipmap.ic_ad_play
//                map.put("img", R.mipmap.ic_ad_shopping);
//                map.put("name", "购物");
//            } else if (i == 1) {
//                map.put("img", R.mipmap.ic_ad_cate);
//                map.put("name", "餐饮");
//            } else if (i == 2) {
//                map.put("img", R.mipmap.ic_ad_play);
//                map.put("name", "娱乐");
//            } else if (i == 3) {
//                map.put("img", R.mipmap.ic_ad_hotel);
//                map.put("name", "酒店");
//            }
//
//            mData.add(map);
//        }
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.home_head, null);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(layoutManager);
        initHeader(headView);
        mAdapter = new HomeAdaper(mData);
        mAdapter.addHeaderView(headView);
        mRv.setAdapter(mAdapter);
    }

    private void initBanner() {
        images.add("res://  /" + R.mipmap.img_default_a);
        images.add("res://  /" + R.mipmap.img_default_b);
//        images.add("http://article.fd.zol-img.com.cn/t_s640x2000/g1/M07/0C/0D/Cg-4jVOpQWmIVPnYAAHFv0U3gXkAAOjOwAtyyUAAcXX661.jpg");
//        images.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        mBanner.setDelayTime(5000);
        mBanner.setImages(images).setImageLoader(new FrescoImageLoader()).start();

    }

    void postData() {
        final Map<String, String> map = new HashMap<>();
        String s = new Gson().toJson(map);
        OkHttpUtils.postString().url(Constant.HOME)
                .content(s).mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                hideDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                hideDialog();
                HomeBean bean = new Gson().fromJson(response, HomeBean.class);
                if (bean.getCode().equals("1")) {
                    //((LinearLayoutManager) mRv.getLayoutManager()).scrollToPosition(0);
                    if (bean.getData().getShoptype().size() > 0) {
                        mData = bean.getData().getShoptype();
                        mAdapter.setNewData(mData);
                    }
                    if (bean.getData().getSpecialtype().size() > 0) {
                        sData = bean.getData().getSpecialtype();
                        mSpecialAdaper.setNewData(sData);
                    }

                }
            }
        });


    }


    //刷新
    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 0;
                mHomeSwipeLayout.setRefreshing(false);
                mAdapter.setEnableLoadMore(true);
                //  mActivity.showToast("refresh");
            }
        }, delayMillis);
        mHomeSwipeLayout.setRefreshing(false);
    }

    //加载更多
    @Override
    public void onLoadMoreRequested() {

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
    public void onDestroyView() {
        super.onDestroyView();
        // unbinder.unbind();
    }
}
