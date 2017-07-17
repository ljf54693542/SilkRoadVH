package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dinuscxj.itemdecoration.LinearDividerItemDecoration;
import com.rongshoo.silkroadvh.Adapter.CollectedAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineCollectedActivity extends BaseActivity {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.rv)
    RecyclerView mRv;

    private CollectedAdaper mAdaper;
    private List<Map<String, Object>> data = new ArrayList<>();

    public static void startMainCollectedActivity(Context context, int typeId) {
        Intent intent = new Intent(context, MineCollectedActivity.class);
        intent.putExtra(SortActivity.TYPE, typeId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_collected);
        ButterKnife.bind(this);
        init();
    }

    void init() {
        mTitleTv.setText("我的收藏");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);
        //多item 边界绘制异常
        LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(
                this, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        dividerItemDecoration.setDivider(getResources().getDrawable(R.drawable.partline_goods));
        mRv.addItemDecoration(dividerItemDecoration);
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
        }
        mAdaper=new CollectedAdaper(data);
        mRv.setAdapter(mAdaper);
        mRv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.goods_del:
                        showToast("删除收藏");
                        break;
                }
            }
        });
        mRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
             //   GoodsDetailActivity.startGoodsDetailActivity(MineCollectedActivity.this,0);
            }
        });
    }
    @OnClick(value = R.id.go_back)
    void onMainClick(View view){
        this.finish();
    }

}
