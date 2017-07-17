package com.rongshoo.silkroadvh.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.rongshoo.silkroadvh.Activity.MineAttentionActivity;
import com.rongshoo.silkroadvh.Activity.MineCollectedActivity;
import com.rongshoo.silkroadvh.Activity.MineInforActivity;
import com.rongshoo.silkroadvh.Activity.OrderAllActivity;
import com.rongshoo.silkroadvh.Adapter.MineAdaper;
import com.rongshoo.silkroadvh.Addr.AddrActivity;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.UserInfor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.dinus.com.itemdecoration.GridDividerItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.mine_rv)
    RecyclerView mMineRv;
    @BindView(R.id.setting_iv)
    ImageView mSettingIv;
    @BindView(R.id.msg_btn)
    ImageView mMsgBtn;
    @BindView(R.id.msg_tv)
    TextView mMsgTv;
    private View view;
    private BaseActivity context;
    private UserInfor user;

    private SimpleDraweeView headV;
    private TextView nameTv;
    private LinearLayout payLl;
    private LinearLayout transportLl;
    private LinearLayout orderLl;
    private LinearLayout orderShare;
    private TextView payMsg;
    private TextView transportMsg;
    private TextView orderShareMsg;

    private MineAdaper mAdaper;
    private int[] img = {R.mipmap.ic_mine_collect, R.mipmap.ic_mine_attention, R.mipmap.ic_mine_talks, R.mipmap.ic_mine_addr, R.mipmap.ic_mine_about, R.mipmap.ic_mine_service};
    private int[] names = {R.string.mine_collect, R.string.mine_attention, R.string.mine_talks, R.string.mine_addr, R.string.mine_about, R.string.mine_service};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, container, false);
            ButterKnife.bind(this, view);
            context = (BaseActivity) getActivity();
            //  EventBus.getDefault().register(this);
            init();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        //状态栏设置
        //  context.setStatusBarDrawable(getResources().getDrawable(R.drawable.mine_head_bg_shape));
        return view;
    }


    void init() {
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        mMineRv.setLayoutManager(layoutManager);
        GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(context, GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
        dividerItemDecoration.setVerticalDivider(getResources().getDrawable(R.drawable.home_grid_partline));
        dividerItemDecoration.setHorizontalDivider(getResources().getDrawable(R.drawable.home_grid_partline));
        mMineRv.addItemDecoration(dividerItemDecoration);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.mine_head, null);
        initHeader(headView);
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            int imgId = img[i];
            int nameId = names[i];
            Map<String, Object> map = new HashMap<>();
            map.put("img", imgId);
            map.put("name", nameId);
            data.add(map);
        }
        mAdaper = new MineAdaper(data);
        mAdaper.addHeaderView(headView);
        mMineRv.setAdapter(mAdaper);
        mMineRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        MineCollectedActivity.startMainCollectedActivity(context, 0);
                        break;
                    case 1:
                        MineAttentionActivity.startMineAttentionActivity(context, 0);
                        break;
                    case 2:
                       // MineTalksActivity.startMineTalksActivity(context);  已经好了
                        break;
                    case 3:
                        AddrActivity.startAddrActivity(context);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        });
    }

    void initHeader(View view) {
        headV = (SimpleDraweeView) view.findViewById(R.id.mine_head_sdv);
        nameTv = (TextView) view.findViewById(R.id.mine_name);
        payLl = (LinearLayout) view.findViewById(R.id.mine_pay);
        transportLl = (LinearLayout) view.findViewById(R.id.mine_transport);
        orderLl = (LinearLayout) view.findViewById(R.id.mine_order);
        orderShare = (LinearLayout) view.findViewById(R.id.mine_order_share);
        payMsg = (TextView) view.findViewById(R.id.mine_pay_msg);
        transportMsg = (TextView) view.findViewById(R.id.mine_transport_msg);
        orderShareMsg = (TextView) view.findViewById(R.id.mine_order_share_msg);
        headV.setOnClickListener(this);
        nameTv.setOnClickListener(this);
        transportLl.setOnClickListener(this);
        orderLl.setOnClickListener(this);
        orderShare.setOnClickListener(this);
        payLl.setOnClickListener(this);
    }

//    void showAvatar() {
//        user = ShareUtils.getUser(context);
//        if (user!=null&&user.getAvatar() != null && user.getAvatar().length() > 0) {
//            mineHeadSdv.setImageURI(Uri.parse(user.getAvatar()));
//            mineName.setText(user.getUserNickname());
//        }
//
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getAvatarMsg(EventBusMsg.UserAvatarMsg msg) {
//        showAvatar();
//    }


    @OnClick(value = {R.id.setting_iv, R.id.msg_btn})
    void onMineClick(View v) {
        switch (v.getId()) {
            case R.id.setting_iv:
                //设置
                break;
            case R.id.msg_btn:
                //消息
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_head_sdv:
            case R.id.mine_name:
                //个人中心
                MineInforActivity.startMineInforActivity(context);
                break;
            case R.id.mine_pay:
                OrderAllActivity.setIndex(context, 1);
                //待支付
                break;
            case R.id.mine_transport:
                OrderAllActivity.setIndex(context, 3);
                //待收货
                break;
            case R.id.mine_order:
                //全部
                OrderAllActivity.setIndex(context, 0);
                break;
            case R.id.mine_order_share:
                //评价
                OrderAllActivity.setIndex(context, 4);
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}


