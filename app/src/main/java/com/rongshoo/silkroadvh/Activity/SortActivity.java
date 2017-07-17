package com.rongshoo.silkroadvh.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dinuscxj.itemdecoration.LinearDividerItemDecoration;
import com.google.gson.Gson;
import com.rongshoo.silkroadvh.Adapter.SortAdaper;
import com.rongshoo.silkroadvh.Adapter.SortChildAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.bean.GoodsKindBean;
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

public class SortActivity extends BaseActivity {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.go_cart)
    ImageView mGoCart;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.child_rv)
    RecyclerView mChildRv;
    private SortAdaper mAdaper;
    private SortChildAdaper mChildAdaper;
    //private List<SortBean> data = new ArrayList<>();
    private List<GoodsKindBean.DataBean.RecordBean> data = new ArrayList<>();
    // private List<Map<String, Object>> cData = new ArrayList<>();
    private List<GoodsKindBean.DataBean.RecordBean> cData = new ArrayList<>();
    private List<GoodsKindBean.DataBean.RecordBean> tData = new ArrayList<>();
    public static final String TYPE = "typeId";
    private Boolean isFather=true;
    private int[] img = {R.mipmap.sort_test1, R.mipmap.sort_test2, R.mipmap.sort_test3, R.mipmap.sort_test4, R.mipmap.sort_test1, R.mipmap.sort_test2, R.mipmap.sort_test3, R.mipmap.sort_test4};
    private String[] names = {"瓜子", "杏仁", "开心果", "南瓜子", "瓜子", "杏仁", "开心果", "南瓜子"};


    public static void startSortActivity(Context context, int type) {
        Intent intent = new Intent(context, SortActivity.class);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ButterKnife.bind(this);
        initRv();
        initChildRv();
        showLoadingDialog("加载中...",true);
        postData("1");
    }

    void postData(String sId) {
        Map<String, String> map = new HashMap<>();
        map.put("ictype",sId);
        String s = new Gson().toJson(map);
        OkHttpUtils.postString().url(Constant.GOODS_KIND)
                .content(s).mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                hideDialog();
                String a = e.getMessage();
                Log.d("Error", a);
            }

            @Override
            public void onResponse(String response, int id) {
                hideDialog();
                GoodsKindBean bean = new Gson().fromJson(response, GoodsKindBean.class);
                if (bean.getCode().equals("1")) {
                    if (bean.getData().getRecord().size() > 0) {
                        tData = bean.getData().getRecord();
                        for (int i = 0; i < tData.size(); i++) {
                            if (i == 0) {
                                tData.get(i).setChecked(true);
                            } else {
                                tData.get(i).setChecked(false);
                            }

                        }
                        if (isFather) {
                            data=tData;
                            mAdaper.setNewData(data);
                            isFather=false;
                            postData(data.get(0).getIctype());
                        } else {
                            cData=tData;
                            mChildAdaper.setNewData(cData);
                        }

                    }else {
                        if (isFather) {
                            data.clear();
                            mAdaper.notifyDataSetChanged();
                        } else {
                            cData.clear();
                            mChildAdaper.notifyDataSetChanged();
                        }
                    }
                }
                Log.d("Error", response);
            }
        });

    }

    void initRv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);
        //多item 边界绘制异常
        LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(
                this, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        dividerItemDecoration.setDivider(getResources().getDrawable(R.drawable.sort_partline));
        mRv.addItemDecoration(dividerItemDecoration);
//        for (int i = 0; i < 20; i++) {
//            SortBean bean;
//            if (i==0){
//                bean = new SortBean("一级分类" + i, i, true);
//            }else {
//                bean = new SortBean("一级分类" + i, i, false);
//            }
//            data.add(bean);
//
//        }
        mAdaper = new SortAdaper(data);
        mRv.setAdapter(mAdaper);
        mRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (data.get(position).getChecked()) return;
                isFather=false;
                showLoadingDialog("加载中...",true);
                for (int i = 0; i < data.size(); i++) {
                    if (position == i) {
                        data.get(i).setChecked(true);
                    } else {
                        data.get(i).setChecked(false);
                    }

                }
                //跳转至指定位置
                ((LinearLayoutManager) mRv.getLayoutManager()).scrollToPositionWithOffset(position, (int) (DensityUtil.getHeight(SortActivity.this) * 0.39));
                mAdaper.notifyDataSetChanged();
                postData(data.get(position).getIctype());
              //  mChildAdaper.setNewData(cData);
            }
        });

    }

    void initChildRv() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mChildRv.setLayoutManager(layoutManager);
//        for (int i = 0; i < img.length; i++) {
//            int imgId = img[i];
//            String name = names[i];
//            Map<String, Object> map = new HashMap<>();
//            map.put("img", imgId);
//            map.put("name", name);
//            cData.add(map);
//        }
        mChildAdaper = new SortChildAdaper(cData);
        mChildRv.setAdapter(mChildAdaper);
        mChildRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
               GoodsActivity.startGoodsActivity(SortActivity.this,cData.get(position).getIctype());
            }
        });

    }


    @OnClick(value = {R.id.go_back, R.id.search})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                this.finish();
                break;
            case R.id.search:
                showToast("搜索功能待开发");
                break;


        }

    }

}
