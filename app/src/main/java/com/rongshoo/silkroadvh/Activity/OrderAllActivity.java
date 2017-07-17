package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.Fragment.OrderListFragment;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderAllActivity extends BaseActivity {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.my_vp)
    ViewPager mMyVp;

    private String[] titles = new String[]{"全部", "待付款", "待发货", "待收货", "待评价"};
    private List<Fragment> mFragments = new ArrayList<>();
    private int index = 0;
    private static String INDEX = "index";

    public static void setIndex(Context context, int index) {
        Intent intent = new Intent(context, OrderAllActivity.class);
        intent.putExtra(OrderAllActivity.INDEX, index);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_all);
        ButterKnife.bind(this);
        mTitleTv.setText("我的订单");
        index = getIntent().getIntExtra(OrderAllActivity.INDEX, 0);
        init();
    }

    void init() {
        mFragments.add(new OrderListFragment());
        mFragments.add(new OrderListFragment());
        mFragments.add(new OrderListFragment());
        mFragments.add(new OrderListFragment());
        mFragments.add(new OrderListFragment());
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(this.getSupportFragmentManager(), mFragments, titles);
        mMyVp.setAdapter(adapter);
        mTabs.setupWithViewPager(mMyVp);
        mMyVp.setCurrentItem(index);
    }

    @OnClick(value = R.id.go_back)
    void onMainClick(View view) {
        this.finish();
    }
}
