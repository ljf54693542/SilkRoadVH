package com.rongshoo.silkroadvh.Activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rongshoo.silkroadvh.Adapter.OrderDetailAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.utils.DateTimeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.paytime_tv)
    TextView mPaytimeTv;
    @BindView(R.id.status_left_tv)
    TextView mStatusLeftTv;
    @BindView(R.id.status_right_tv)
    TextView mStatusRightTv;
    @BindView(R.id.status_ll)
    LinearLayout mStatusLl;

    private OrderDetailAdaper mAdaper;
    List<Map<String, Object>> data = new ArrayList<>();
    private TextView nameTv;
    private TextView phoneNumTv;
    private TextView addrTv;
    private TextView orderNum;
    private TextView orderTime;
    private TextView orderStatus;
    private TextView shopName;
    private TextView orderNumCopy;
    private ClipboardManager cm;
    private String startTime = "2017-03-22 18:15:36";
    private String endTime = "2017-03-24 18:15:36";
    private String showTime;
    Timer timer = new Timer();
    private TextView goodsNum;
    private TextView goodsPriceSum;
    private TextView priceCarriage;
    private TextView orderPrice;

    public static void startOrderDetailActivity(Context context, int typeId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(SortActivity.TYPE, typeId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        init();
    }

    void init() {
        mTitleTv.setText("订单详情");
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        timer.schedule(task, 1000, 1000);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
        }
        mAdaper = new OrderDetailAdaper(data);
        View header = LayoutInflater.from(this).inflate(R.layout.order_detail_header, null);
        initHeader(header);
        View footer = LayoutInflater.from(this).inflate(R.layout.order_detail_footer, null);
        initFooter(footer);
        mAdaper.addHeaderView(header);
        mAdaper.addFooterView(footer);
        mRv.setAdapter(mAdaper);
    }

    void initHeader(View view) {
        nameTv = (TextView) view.findViewById(R.id.p_name);
        phoneNumTv = (TextView) view.findViewById(R.id.p_phone_num);
        addrTv = (TextView) view.findViewById(R.id.p_addr_tv);
        orderNum = (TextView) view.findViewById(R.id.order_detail_num);
        orderTime = (TextView) view.findViewById(R.id.order_detail_time);
        orderStatus = (TextView) view.findViewById(R.id.order_detail_status);
        shopName = (TextView) view.findViewById(R.id.order_detail_shopname);
        shopName.setOnClickListener(this);
        orderNumCopy = (TextView) view.findViewById(R.id.order_detail_num_copy);
        orderNumCopy.setOnClickListener(this);
    }


    void initFooter(View view) {
        goodsNum = (TextView) view.findViewById(R.id.order_detail_goods_num);
        goodsPriceSum = (TextView) view.findViewById(R.id.order_price_goods_sum_tv);
        priceCarriage = (TextView) view.findViewById(R.id.order_price_carriage_tv);
        orderPrice = (TextView) view.findViewById(R.id.order_price_sum_tv);
    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    //获取本地时间
                    // startTime= DateTimeUtil.getFormatTime();
                    //通过服务器获取倒计时时间
                    startTime = DateTimeUtil.formatDateType(DateTimeUtil.getDateAddOneSecond(DateTimeUtil.formatDateType(startTime)));
                    showTime = DateTimeUtil.getRemainTime("2017-03-02 18:25:36", startTime);
                    if (!startTime.equals(endTime)) {
                        mPaytimeTv.setText("剩余时间:\n" + showTime);
                    } else {
                        timer.cancel();
                        mPaytimeTv.setVisibility(View.INVISIBLE);
                    }

                }
            });
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_detail_shopname://店铺跳转
                break;
            case R.id.order_detail_num_copy://复制订单
                // 将文本内容放到系统剪贴板里。
                cm.setText(orderNum.getText());
                showToast("已复制" + cm.getText());
                break;
        }
    }

    @OnClick(value = R.id.go_back)
    void onMainClick() {
        this.finish();
    }
}
