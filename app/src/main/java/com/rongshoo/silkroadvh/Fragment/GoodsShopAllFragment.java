package com.rongshoo.silkroadvh.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.dinuscxj.itemdecoration.LinearDividerItemDecoration;
import com.rongshoo.silkroadvh.Adapter.GoodsAllAdaper;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GoodsShopAllFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rv)
    RecyclerView mRv;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private GoodsAllAdaper mAdaper;
    private List<Map<String, Object>> data = new ArrayList<>();
    private BaseActivity mActivity;
    private int choiceType = 0;
    private int priceFlag = 0;

    public GoodsShopAllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoodsShopAllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoodsShopAllFragment newInstance(String param1, String param2) {
        GoodsShopAllFragment fragment = new GoodsShopAllFragment();
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_goods_shop_all, container, false);
            ButterKnife.bind(this, view);
            Bundle bundle = getArguments();
            mActivity = (BaseActivity) getActivity();
            initRv();
            changeTextCholor();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }


    void initRv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRv.setLayoutManager(layoutManager);
        //多item 边界绘制异常
        LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(mActivity, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        dividerItemDecoration.setDivider(getResources().getDrawable(R.drawable.partline_goods));
        mRv.addItemDecoration(dividerItemDecoration);
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
        }
        mAdaper = new GoodsAllAdaper(data);
        mRv.setAdapter(mAdaper);
        mRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
           //     GoodsDetailActivity.startGoodsDetailActivity(mActivity, 0);
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

}
