package com.rongshoo.silkroadvh.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.rongshoo.silkroadvh.Adapter.GoodsMainAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.dinus.com.itemdecoration.GridDividerItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;


public class GoodsShopMainFragment extends BaseFragment {
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
    private List<Map<String, String>> data = new ArrayList<>();
    private GoodsMainAdaper mAdapter;


    public GoodsShopMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoodsShopMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoodsShopMainFragment newInstance(String param1, String param2) {
        GoodsShopMainFragment fragment = new GoodsShopMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_goods_shop_main, container, false);
            ButterKnife.bind(this, view);
            Bundle bundle = getArguments();
            mActivity = (BaseActivity) getActivity();
            init();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    void init() {
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        mRv.setLayoutManager(layoutManager);
        GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(mActivity,
                GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
        dividerItemDecoration.setVerticalDivider(mActivity.getResources().getDrawable(R.drawable.grid_view_white));
        dividerItemDecoration.setHorizontalDivider(mActivity.getResources().getDrawable(R.drawable.grid_view_white));
        mRv.addItemDecoration(dividerItemDecoration);
        for (int i = 0; i < 8; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", mActivity.getResources().getString(R.string.goods_test_name));
            data.add(map);
        }
        mAdapter = new GoodsMainAdaper(data);
        mRv.setAdapter(mAdapter);
        mRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
              //  GoodsDetailActivity.startGoodsDetailActivity(mActivity, 0);
            }
        });
    }


}
