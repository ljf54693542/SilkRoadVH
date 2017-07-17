package com.rongshoo.silkroadvh.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.rongshoo.silkroadvh.Activity.OrderDetailActivity;
import com.rongshoo.silkroadvh.Adapter.OrderListAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.BaseFragment;
import com.rongshoo.silkroadvh.bean.OrderListBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderListFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rv)
    RecyclerView mRv;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private BaseActivity mActivity;
    private OrderListAdaper mAdaper;
    private List<OrderListBean.DataBean> data = new ArrayList<>();


    public OrderListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderListFragment newInstance(String param1, String param2) {
        OrderListFragment fragment = new OrderListFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_order_list, container, false);
            ButterKnife.bind(this, view);
            mActivity = (BaseActivity) getActivity();
            initTestJson();
            init();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    private void initTestJson() {
        StringBuffer sb = new StringBuffer();
        try {
            InputStream in = getActivity().getAssets().open("order.json");
            byte[] bytes = new byte[in.available()];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            in.close();
            OrderListBean orderListBean = new Gson().fromJson(sb.toString(), OrderListBean.class);
            data = orderListBean.getData();
        } catch (IOException e) {
            mActivity.showToast("读取异常");
        }


    }

    void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(layoutManager);
        mAdaper = new OrderListAdaper(data);
        mRv.setAdapter(mAdaper);
        mRv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.item_ll:
                        OrderDetailActivity.startOrderDetailActivity(mActivity, 0);
                        break;
                    case R.id.status_right_tv:
                        break;
                    case R.id.status_left_tv:
                        break;
                }
            }
        });
    }

}
