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

public class AddrCFragment extends Fragment {
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

    private Activity activity;
    public static String CBEAN = "cBean";
    public static String PNAME = "pName";
    private List<CityBean.CitylistBean.CBean> cBean = new ArrayList<>();
    private AddrAdapterCAdaper adaper;
    private String pName;
    private View view;

    public AddrCFragment() {
        // Required empty public constructor
    }

    public static AddrCFragment newInstance(List<CityBean.CitylistBean.CBean> cBean, String pName) {
        AddrCFragment addrCFragment = new AddrCFragment();
        Bundle args = new Bundle();
        args.putSerializable(CBEAN, (Serializable) cBean);
        args.putString(PNAME, pName);
        addrCFragment.setArguments(args);
        return addrCFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cBean = (List<CityBean.CitylistBean.CBean>) getArguments().getSerializable(AddrCFragment.CBEAN);
            pName = getArguments().getString(PNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_addr_c, container, false);
            ButterKnife.bind(this, view);
            activity = getActivity();
            init();
        }
        return view;
    }

    public void init() {
        mTitleTv.setText("选择地区");
        final LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        LinearDividerItemDecoration decoration = new LinearDividerItemDecoration(activity, LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
        decoration.setDivider(getResources().getDrawable(R.drawable.home_grid_partline));
        mMyRv.setLayoutManager(layoutManager);
        mMyRv.addItemDecoration(decoration);
        adaper = new AddrAdapterCAdaper(cBean);
        mMyRv.setAdapter(adaper);
        mMyRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (cBean.get(i).getA() != null) {
                    getFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null) //将当前fragment加入到返回栈中
                            .replace(R.id.addr_container, new AddrSFragment().newInstance(cBean.get(i).getA(), pName, cBean.get(i).getN())).commit();
                } else {
                    ((BaseActivity) activity).showToast("" + pName + " " + cBean.get(i).getN());
                    EventBus.getDefault().postSticky(new EventBusMsg.AddrChoicMsg(pName, cBean.get(i).getN(), ""));
                    activity.finish();
                }


            }
        });
    }

    @OnClick(value = R.id.go_back)
    void onClick(View view) {
        //从栈中将当前fragment推出
        getFragmentManager().popBackStack();
    }

}
