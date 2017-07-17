package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.Fragment.GoodsShopAllFragment;
import com.rongshoo.silkroadvh.Fragment.GoodsShopMainFragment;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.BaseFragmentAdapter;
import com.rongshoo.silkroadvh.utils.FrescoImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsShopActivity extends BaseActivity implements OnBannerListener {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.attention)
    ImageView mAttention;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.my_vp)
    ViewPager mMyVp;
    @BindView(R.id.goods_shop_detail_tv)
    TextView mGoodsShopDetailTv;
    @BindView(R.id.goods_shop_msg_tv)
    TextView mGoodsShopMsgTv;
    @BindView(R.id.tab_img)
    ImageView mTabImg;
    @BindView(R.id.goods_shop_msg_ll)
    LinearLayout mGoodsShopMsgLl;
    @BindView(R.id.shop_name_tv)
    TextView mShopNameTv;
    private List<String> images = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = new String[]{"首页推荐", "全部商品"};


    public static void startGoodsShopActivity(Context context, int typeId) {
        Intent intent = new Intent(context, GoodsShopActivity.class);
        intent.putExtra(SortActivity.TYPE, typeId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_shop);
        ButterKnife.bind(this);
        initBanner();
        setupViewPager();
    }


    /**
     * 推荐引入其他Fragment
     */
    private void setupViewPager() {
        Bundle bundle = new Bundle();
        // bundle.putSerializable("data", shopData);
        GoodsShopMainFragment shopMainFragment = new GoodsShopMainFragment();
        GoodsShopAllFragment shopAllFragment = new GoodsShopAllFragment();
        shopAllFragment.setArguments(bundle);
        shopMainFragment.setArguments(bundle);
        mFragments.add(shopMainFragment);
        mFragments.add(shopAllFragment);
        // 第二步：为ViewPager设置适配器
        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(this.getSupportFragmentManager(), mFragments, mTitles);
        mMyVp.setAdapter(adapter);
        //  第三步：将ViewPager与TableLayout 绑定在一起
        mTabs.setupWithViewPager(mMyVp);

    }

    private void initBanner() {
        //http://123.56.24.149:7079/GatewayPCBackstage/index.jpg
        //https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489230086050&di=ff9c5915d99ffa03265e5c038df4095d&imgtype=0&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F15%2F95%2F50%2F81%2F543bannerDQ4_1200.jpg
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489230086050&di=ff9c5915d99ffa03265e5c038df4095d&imgtype=0&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F15%2F95%2F50%2F81%2F543bannerDQ4_1200.jpg\n");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489230119923&di=894f603aa8a39ee1e575e504841427a9&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01903955448a250000019ae9351fcc.jpg");
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
                showToast("店铺详情------待开发");
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

}



