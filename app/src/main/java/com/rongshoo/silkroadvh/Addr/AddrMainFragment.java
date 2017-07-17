package com.rongshoo.silkroadvh.Addr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dinuscxj.itemdecoration.LinearDividerItemDecoration;
import com.google.gson.Gson;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.AddrListBean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddrMainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.addr_rv)
    RecyclerView mAddrRv;
    @BindView(R.id.addr_add_tv)
    TextView mAddrAddTv;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private BaseActivity context;
    private  AddrAdapterMainAdaper adaper;
    private List<AddrListBean.DataBean> data = new ArrayList<>();


    public AddrMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddrMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddrMainFragment newInstance(String param1, String param2) {
        AddrMainFragment fragment = new AddrMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_addr_main, container, false);
            // Inflate the layout for this fragment
            ButterKnife.bind(this, view);
            this.context = (BaseActivity) getActivity();
            // EventBus.getDefault().register(this);
            initJsonData();
            init();
        }
        return view;
    }

    public void init() {
        mTitleTv.setText("管理收货地址");
    //    showLoadingDialog("数据加载中......", false);
       // postData();
        final LinearLayoutManager linManager = new LinearLayoutManager(context);
        mAddrRv.setLayoutManager(linManager);
        // initJsonData();
        adaper = new AddrAdapterMainAdaper(data);
        //多item 边界绘制异常
        LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(
                context, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        dividerItemDecoration.setDivider(getResources().getDrawable(R.drawable.addr_partlspace));//grid_recommend_partline  rv_partline
        //adaper.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAddrRv.setAdapter(adaper);
        mAddrRv.addItemDecoration(dividerItemDecoration);
        mAddrRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.container, new AddrNewFragment().newInstance(data.get(i))).commit();
            }
        });
//        mAddrRv.addOnItemTouchListener(new OnItemChildClickListener() {
//            @Override
//            public void onSimpleItemChildClick(final BaseQuickAdapter baseQuickAdapter, View view, final int i) {
//                switch (view.getId()) {
//                    case R.id.item_addr_main_default_iv:
//                    case R.id.item_addr_main_default_tv:
//                        if (adaper.getSelectedPos() == i) return;
//                   //     postDefaultData(i);
////                        AddrListBean.DataBean ta;
////                        for (int j = 0; j < data.size(); j++) {
////                            ta = data.get(j);
////                            if (i == j) {
////                                data.get(i).setStatus("0");
////                            } else {
////                                data.get(j).setStatus("1");
////                            }
////                        }
////                        adaper.notifyDataSetChanged();
////                        context.showToast("设置默认地址成功");
//                        break;
//                    case R.id.item_addr_main_edit_tv:
//                    case R.id.item_addr_main_edit_iv:
//                        getFragmentManager()
//                                .beginTransaction()
//                                .addToBackStack(null)  //将当前fragment加入到返回栈中
//                                .replace(R.id.container, new AddrNewFragment().newInstance(data.get(i))).commit();
//                        //跳转至编辑
//                        break;
//                    case R.id.item_addr_main_del_tv:
//                    case R.id.item_addr_main_del_iv://删除
//                        getCheckDialog("确定删除？", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                postDelData(data.get(i).getShippingAddressId(), i);
//                            }
//                        }).show();
//
//                        break;
//                }
//            }
//        });
    }

        /**
     * 从assert文件夹中获取json数据
     */
    //模拟数据
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = context.getAssets().open("addr.json");//打开json数据
            byte[] by = new byte[is.available()];//转字节
            int len = -1;
            while ((len = is.read(by)) != -1) {
                sb.append(new String(by, 0, len, "gb2312"));//根据字节长度设置编码
            }
            is.close();//关闭流
            // jsonObject = new JSONObject(sb.toString());//为json赋值
            AddrListBean bean=new Gson().fromJson(sb.toString(),AddrListBean.class);
            data = bean.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(value = {R.id.go_back,R.id.addr_add_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                context.finish();
                break;
            case R.id.addr_add_tv:
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.container, new AddrNewFragment()).commit();
                break;
        }
    }

}
