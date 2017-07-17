package com.rongshoo.silkroadvh.Addr;

import android.app.Activity;
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
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.CityBean;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddrPFragment extends Fragment {
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
    @BindView(R.id.myRv)
    RecyclerView mMyRv;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Activity activity;
    private CityBean cityBean;
    private AddrAdapterPAdaper pAdaper;
    private View view;


    public AddrPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddrPFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddrPFragment newInstance(String param1, String param2) {
        AddrPFragment fragment = new AddrPFragment();
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
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_addr, container, false);
            ButterKnife.bind(this, view);
            activity = getActivity();
            initJsonData();
            initRv();
        }
        return view;
    }

    /**
     * 从assert文件夹中获取json数据
     */
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = activity.getAssets().open("city.json");//打开json数据
            byte[] by = new byte[is.available()];//转字节
            int len = -1;
            while ((len = is.read(by)) != -1) {
                sb.append(new String(by, 0, len, "gb2312"));//根据字节长度设置编码
            }
            is.close();//关闭流
            // jsonObject = new JSONObject(sb.toString());//为json赋值
            cityBean = new Gson().fromJson(sb.toString(), CityBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRv() {
        mTitleTv.setText("选择地区");
        final LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        mMyRv.setLayoutManager(layoutManager);
        pAdaper = new AddrAdapterPAdaper(cityBean.getCitylist());
        pAdaper.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        LinearDividerItemDecoration decoration = new LinearDividerItemDecoration(activity, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        decoration.setDivider(getResources().getDrawable(R.drawable.home_grid_partline));
        mMyRv.addItemDecoration(decoration);
        mMyRv.setAdapter(pAdaper);
        mMyRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.addr_container, new AddrCFragment().newInstance(cityBean.getCitylist().get(i).getC(), cityBean.getCitylist().get(i).getP())).commit();
            }
        });
    }

    @OnClick(value = R.id.go_back)
    void onClick(View view) {
        activity.finish();
    }

}
