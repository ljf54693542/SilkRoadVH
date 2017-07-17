package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dinuscxj.itemdecoration.LinearDividerItemDecoration;
import com.google.gson.Gson;
import com.rongshoo.silkroadvh.Adapter.ShopListAdaper;
import com.rongshoo.silkroadvh.Adapter.ShopSortAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.bean.GoodsKindBean;
import com.rongshoo.silkroadvh.bean.ShopBean;
import com.rongshoo.silkroadvh.utils.DensityUtil;
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

public class ShopListActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.go_cart)
    ImageView mGoCart;
    @BindView(R.id.store_list_all_btn)
    Button mStoreListAllBtn;
    @BindView(R.id.store_list_num_btn)
    Button mStoreListNumBtn;
    @BindView(R.id.store_list_price_btn)
    Button mStoreListPriceBtn;
    @BindView(R.id.store_list_fresh_btn)
    Button mStoreListFreshBtn;
    @BindView(R.id.shop_all_lin)
    LinearLayout mShopAllLin;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.root_ll)
    LinearLayout mRootLl;
    @BindView(R.id.close_v)
    View mCloseV;
    private int choiceType = 0;
    private int priceFlag = 0;
    private int typeId = 0;
    // private List<Map<String, Object>> data = new ArrayList<>();
    private List<ShopBean.DataBean.RowsBean> data = new ArrayList<>();
    private RecyclerView brandRv;
    private TextView resetTv;
    private TextView confirmTv;
    private PopupWindow pop;
    private ShopSortAdaper mBrandAdaper;
    private ShopListAdaper mAdaper;
    private List<GoodsKindBean.DataBean.RecordBean> brandData = new ArrayList<>();


    private int iorderby = 0;
    private int pageIndex = 1;//从1开始
    private int pageSize = 10;
    private int delayMillis = 1000;
    private int recordcount = 0;
    private Boolean hasNext = false;
    private int usedCount = 0;
    private String ictype = null;//分类

    public static void startShopListActivity(Context context, int typeId) {
        Intent intent = new Intent(context, ShopListActivity.class);
        intent.putExtra(SortActivity.TYPE, typeId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);
        changeTextCholor();
        mGoCart.setVisibility(View.VISIBLE);
        initRv();
        initPop();
        showLoadingDialog("加载中...", false);
        postData();
        postSortData();
    }

    void initRv() {
        typeId = getIntent().getExtras().getInt(SortActivity.TYPE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);
        //多item 边界绘制异常
        LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(
                this, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        dividerItemDecoration.setDivider(getResources().getDrawable(R.drawable.partline_goods));
        mRv.addItemDecoration(dividerItemDecoration);
//        for (int i = 0; i < 20; i++) {
//            Map<String, Object> map = new HashMap<>();
//            data.add(map);
//        }
        mAdaper = new ShopListAdaper(data);
        mRv.setAdapter(mAdaper);
        mRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    ShopCateActivity.startShopCateActivity(ShopListActivity.this,data.get(position).getIshopid());
                }

            }
        });
    }

    public void changeTextCholor() {
        for (int i = 0; i < 4; i++) {
            if (i == choiceType) {
                mShopAllLin.getChildAt(choiceType).setSelected(true);
            } else {
                mShopAllLin.getChildAt(i).setSelected(false);
            }

        }

    }

    void postSortData() {
        Map<String, String> map = new HashMap<>();
        map.put("ictype", "" + typeId);
        String s = new Gson().toJson(map);
        OkHttpUtils.postString().url(Constant.GOODS_KIND)
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
                GoodsKindBean bean = new Gson().fromJson(response, GoodsKindBean.class);
                if (bean.getCode().equals("1")) {
                    if (bean.getData().getRecord().size() > 0) {
                        brandData = bean.getData().getRecord();
                        for (int i = 0; i < brandData.size(); i++) {
                            brandData.get(i).setChecked(false);
                        }
                        mBrandAdaper.setNewData(brandData);
                    }
                }
            }
        });

    }


    void postData() {
        Map<String, String> map = new HashMap<>();
        map.put("ishoptype", "" + typeId);
        map.put("orderby", "" + iorderby);
        map.put("pageindex", "" + pageIndex);
        map.put("pagesize", "" + pageSize);
        if (ictype != null && ictype.length() > 0) {
            map.put("ictype", ictype);
        }
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
                    if (pageIndex == 1) {
                        usedCount = 0;
                        recordcount = bean.getData().getRecordcount();
                        ((LinearLayoutManager) mRv.getLayoutManager()).scrollToPosition(0);
                    }
                    if (bean.getData().getRows().size() > 0) {
                        List<ShopBean.DataBean.RowsBean> tData = new ArrayList<ShopBean.DataBean.RowsBean>();
                        tData = bean.getData().getRows();
                        usedCount = usedCount + tData.size();
                        data.addAll(tData);
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


    private String checkIndex = "";

    void initBrandRv() {
        GridLayoutManager layoutManager = new GridLayoutManager(ShopListActivity.this, 3);
        brandRv.setLayoutManager(layoutManager);
//        for (int i = 0; i < 10; i++) {
//            SortBean bean = new SortBean("欧莱雅" + i, 0, false);
//            brandData.add(bean);
//        }
        mBrandAdaper = new ShopSortAdaper(brandData);
        brandRv.setAdapter(mBrandAdaper);
        brandRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                updateSortData(position);
            }
        });

    }

    private void updateSortData(int position) {
        if (pop.isShowing()) {
            pop.dismiss();
        }
        if (checkIndex.length() > 0 && Integer.parseInt(checkIndex) == position) return;
        checkIndex = "" + position;
        for (int i = 0; i < brandData.size(); i++) {
            if (i == position) {
                brandData.get(position).setChecked(true);
            } else {
                brandData.get(i).setChecked(false);
            }

        }
        mBrandAdaper.notifyDataSetChanged();
        ictype = brandData.get(position).getIctype();
        pageIndex = 1;

        data.clear();
        showPost();
    }

    private void showPost() {
        showLoadingDialog("加载中...", false);
        postData();
    }

    void initPop() {
        View brandView = LayoutInflater.from(this).inflate(R.layout.goods_pop, null);
        brandRv = (RecyclerView) brandView.findViewById(R.id.brand_rv);
        resetTv = (TextView) brandView.findViewById(R.id.reset_tv);
        confirmTv = (TextView) brandView.findViewById(R.id.confirm_tv);
        TextView title = (TextView) brandView.findViewById(R.id.name_tv);
        brandView.findViewById(R.id.bottom_ll).setVisibility(View.GONE);
        title.setText("分类");
//        resetTv.setOnClickListener(this);
//        confirmTv.setOnClickListener(this);
        initBrandRv();
        pop = new PopupWindow(brandView, (int) (DensityUtil.getWidth(ShopListActivity.this) * 0.7), ViewGroup.LayoutParams.MATCH_PARENT);
        //  pop.setOutsideTouchable(true);
        pop.setOnDismissListener(new poponDismissListener());
    }

    private void showPop() {
        if (pop != null && !pop.isShowing()) {
            pop.showAtLocation(mRootLl, Gravity.BOTTOM, (int) (DensityUtil.getWidth(ShopListActivity.this) * 0.3), 0);
            backgroundAlpha(0.5f);
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

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.reset_tv:
//                List<SortBean> tData = new ArrayList<>();
////                for (int i = 0; i < brandData.size(); i++) {
////                    SortBean bean = brandData.get(i);
////                    bean.setChecked(false);
////                    tData.add(bean);
////                }
////                brandData = tData;
//                mBrandAdaper.setNewData(brandData);
//                break;
//            case R.id.confirm_tv:
//                if (pop != null && pop.isShowing()) {
//                    pop.dismiss();
//                    backgroundAlpha(1f);
//                }
//                Log.d("CONFIRM", brandData.toString());
//                break;
//        }
//    }

    @OnClick(value = {R.id.close_v, R.id.go_back, R.id.search, R.id.go_cart, R.id.store_list_all_btn, R.id.store_list_num_btn, R.id.store_list_price_btn, R.id.store_list_fresh_btn})
    void OnViewclick(View view) {
        switch (view.getId()) {
            case R.id.close_v:
                if (pop.isShowing()) {
                    pop.dismiss();
                }
                break;
            case R.id.go_back:
                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    this.finish();
                }
                break;
            case R.id.search:
                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    showToast("搜索功能待开发");
                }
                break;
            case R.id.go_cart:
                break;
            case R.id.store_list_all_btn:
                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    choiceType = 0;
                    priceFlag = 0;
                    iorderby = 0;
                    pageIndex = 1;
                    mStoreListPriceBtn.setText("价格");
                    changeTextCholor();
                    data.clear();
                    showPost();
                }
                break;
            case R.id.store_list_num_btn:
                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    choiceType = 1;
                    priceFlag = 0;
                    iorderby = 1;
                    pageIndex = 1;
                    mStoreListPriceBtn.setText("价格");
                    changeTextCholor();
                    data.clear();
                    showPost();
                }
                break;
            case R.id.store_list_price_btn:
                choiceType = 2;
                changeTextCholor();
                switch (priceFlag) {
                    case 0:
                        priceFlag = 1;
                        iorderby = 2;
                        pageIndex = 1;
                        mStoreListPriceBtn.setText("价格↑");
                        break;
                    case 1:
                        priceFlag = 2;
                        iorderby = 3;
                        pageIndex = 1;
                        mStoreListPriceBtn.setText("价格↓");
                        break;
                    case 2:
                        priceFlag = 1;
                        iorderby = 2;
                        pageIndex = 1;
                        mStoreListPriceBtn.setText("价格↑");
                        break;
                }
                data.clear();
                showPost();
                break;
            case R.id.store_list_fresh_btn://品牌
                choiceType = 3;
                showPop();
                break;
        }

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
