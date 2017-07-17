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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dinuscxj.itemdecoration.LinearDividerItemDecoration;
import com.rongshoo.silkroadvh.Adapter.MineTalkAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineTalksActivity extends BaseActivity {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.write_iv)
    ImageView mWriteIv;
    @BindView(R.id.rv)
    RecyclerView mRv;
    MineTalkAdaper mTalkAdaper;
    private List<Map<String, Object>> data = new ArrayList<>();

    public static void startMineTalksActivity(Context context) {
        Intent intent = new Intent(context, MineTalksActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_talks);
        ButterKnife.bind(this);
        init();
    }

    void init(){
        mTitleTv.setText("我的说说");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);
        //多item 边界绘制异常
        LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(
                this, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        dividerItemDecoration.setDivider(getResources().getDrawable(R.drawable.partline_goods));
        mRv.addItemDecoration(dividerItemDecoration);
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
        }
        mTalkAdaper = new MineTalkAdaper(data);
        mRv.setAdapter(mTalkAdaper);
        mRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //ShopCateActivity.startShopCateActivity(MineTalksActivity.this);
            }
        });
    }
}
