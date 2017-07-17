package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.dinuscxj.itemdecoration.LinearDividerItemDecoration;
import com.google.gson.Gson;
import com.rongshoo.silkroadvh.Adapter.OrderSubmitAdaper;
import com.rongshoo.silkroadvh.Adapter.OrderSubmitPopAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.YunFeiBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderConfirmActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.price_tv)
    TextView mPriceTv;
    @BindView(R.id.buy_tv)
    TextView mBuyTv;
    @BindView(R.id.root_ll)
    LinearLayout mRootLl;


    private LinearLayout pll;
    private TextView pName;
    private TextView pPhoneNum;
    private TextView pAddr;
    private TextView pAdd;
    private OrderSubmitAdaper mAdaper;
    private YunFeiBean mYunFeiBean;
    private List<YunFeiBean.DataBean> data = new ArrayList<>();
    private List<YunFeiBean.DataBean.YfBean> tData = new ArrayList<>();
    private int pIndex = 0;
    private PopupWindow pop;
    private RecyclerView pRv;
    //  private Oadapter mPopAdaper;
    private OrderSubmitPopAdaper mPopAdaper;

    public static void startOrderConfirmActivity(Context context, int typeId) {
        Intent intent = new Intent(context, OrderConfirmActivity.class);
        intent.putExtra(SortActivity.TYPE, typeId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        ButterKnife.bind(this);
        initTestJson();
        init();
        initPop();
    }

    private void initTestJson() {
        StringBuffer sb = new StringBuffer();
        try {
            InputStream in = getAssets().open("yf.json");
            byte[] bytes = new byte[in.available()];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            in.close();
            mYunFeiBean = new Gson().fromJson(sb.toString(), YunFeiBean.class);
            data = mYunFeiBean.getData();
        } catch (IOException e) {
            showToast("读取异常");
        }


    }


    void init() {
        mTitleTv.setText("确认订单");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);
        LayoutInflater inflater = LayoutInflater.from(this);
        View header = inflater.inflate(R.layout.order_confirm_head, null);
        initHeader(header);
        mAdaper = new OrderSubmitAdaper(data);//填入数据
        mAdaper.addHeaderView(header);
        mRv.setAdapter(mAdaper);
        mRv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.distribution_price_ll:
                        pIndex = position;
                        tData = data.get(pIndex).getYf();//默认装载
                        //弹出运费选框
                        if (pop != null && !pop.isShowing()) {
                            mPopAdaper.setNewData(tData);
                            pop.showAtLocation(findViewById(R.id.root_ll), Gravity.BOTTOM, 0, 0);
                            backgroundAlpha(0.5f);
                        }
                        break;
                }
            }
        });


    }


    void initHeader(View view) {
        pll = (LinearLayout) view.findViewById(R.id.p_infor_tv);
        pName = (TextView) view.findViewById(R.id.p_name);
        pPhoneNum = (TextView) view.findViewById(R.id.p_phone_num);
        pAddr = (TextView) view.findViewById(R.id.p_addr_tv);
        pAdd = (TextView) view.findViewById(R.id.add_addr_tv);
        pll.setOnClickListener(this);
        pAdd.setOnClickListener(this);
        pAddr.setOnClickListener(this);
    }


    void initPop() {
        final View popView = LayoutInflater.from(this).inflate(R.layout.order_submit_p, null);
        int height = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.5);   //ViewGroup.LayoutParams.WRAP_CONTENT
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


    void initPopView(View view) {
        pRv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        pRv.setLayoutManager(layoutManager);
        //多item 边界绘制异常
        LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(
                this, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        dividerItemDecoration.setDivider(getResources().getDrawable(R.drawable.partline_goods));
        pRv.addItemDecoration(dividerItemDecoration);
        //  mPopAdaper = new Oadapter(tData);
        mPopAdaper = new OrderSubmitPopAdaper(tData);
        pRv.setAdapter(mPopAdaper);
//        mPopAdaper.setOnItemClickListener(new Oadapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                for (int i = 0; i < tData.size(); i++) {
//                    YunFeiBean.DataBean.YfBean map = tData.get(i);
//                    if (i == position) {
//                        tData.get(i).setIsCheck(true);
//                        data.get(pIndex).setYfText(map.getName());
//                        // map.put("isCheck", true);
//                    } else {
//                        //  map.put("isCheck", false);
//                        tData.get(i).setIsCheck(false);
//                    }
//                    //  bData.add(map);
//                }
//                mPopAdaper.setNewData(tData);
//                mAdaper.notifyDataSetChanged();
//            }
//        });

        pRv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.root_ll:
                        for (int i = 0; i < tData.size(); i++) {
                            YunFeiBean.DataBean.YfBean map = tData.get(i);
                            if (i == position) {
                                tData.get(i).setIsCheck(true);
                                data.get(pIndex).setYfText(map.getName());
                                // map.put("isCheck", true);
                            } else {
                                //  map.put("isCheck", false);
                                tData.get(i).setIsCheck(false);
                            }
                            //  bData.add(map);
                        }
                        mPopAdaper.setNewData(tData);
                        mAdaper.notifyDataSetChanged();
                        break;
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.p_infor_tv:
                break;
            case R.id.p_addr_tv:
                break;
            case R.id.add_addr_tv:
                break;
        }
    }

    @OnClick(value = {R.id.go_back, R.id.right_tv})
    void onMainClick(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                this.finish();
                break;
            case R.id.buy_tv:
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
