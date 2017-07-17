package com.rongshoo.silkroadvh.Addr;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.CityBean;
import com.rongshoo.silkroadvh.bean.EventBusMsg;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddrSFragment extends Fragment {
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
    Unbinder unbinder;

    private String pName;
    private String cName;
    private static String CNAME = "cName";
    private static String ABEAN = "aBean";
    private List<CityBean.CitylistBean.CBean.ABean> aBean = new ArrayList<>();
    private Activity activity;
    private AddrAdapterSAdaper adaper;
    private View view;

    public AddrSFragment() {
        // Required empty public constructor
    }


    public static AddrSFragment newInstance(List<CityBean.CitylistBean.CBean.ABean> aBean, String pName, String cName) {
        AddrSFragment addrSFragment = new AddrSFragment();
        Bundle ars = new Bundle();
        ars.putSerializable(ABEAN, (Serializable) aBean);
        ars.putString(AddrCFragment.PNAME, pName);
        ars.putString(CNAME, cName);
        addrSFragment.setArguments(ars);
        return addrSFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            aBean = (List<CityBean.CitylistBean.CBean.ABean>) getArguments().getSerializable(ABEAN);
            pName = getArguments().getString(AddrCFragment.PNAME);
            cName = getArguments().getString(CNAME);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_addr2, container, false);
            ButterKnife.bind(this, view);
            activity = getActivity();
            init();
        }
        return view;
    }

    public void init() {
        mTitleTv.setText("选择地区");
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        mMyRv.setLayoutManager(layoutManager);
        LinearDividerItemDecoration decoration = new LinearDividerItemDecoration(activity, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        decoration.setDivider(getResources().getDrawable(R.drawable.home_grid_partline));
        mMyRv.addItemDecoration(decoration);
        adaper = new AddrAdapterSAdaper(aBean);
        adaper.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mMyRv.setAdapter(adaper);
        mMyRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ((BaseActivity) activity).showToast("" + pName + " " + cName + " " + aBean.get(i).getS());
                EventBus.getDefault().postSticky(new EventBusMsg.AddrChoicMsg(pName, cName, aBean.get(i).getS()));
                activity.finish();
            }
        });
    }

    @OnClick(value = R.id.go_back)
    void onClick(View view) {
        //从栈中将当前fragment推出
        getFragmentManager().popBackStack();
    }

}
