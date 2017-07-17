package com.rongshoo.silkroadvh.Addr;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.AddrListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter 推荐列表
 * Created by RS-KXH on 2017/1/4
 */

public class AddrAdapterMainAdaper extends BaseQuickAdapter<AddrListBean.DataBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public AddrAdapterMainAdaper(int layoutResId, List<AddrListBean.DataBean> data) {
        super(layoutResId, data);
    }

    private List<AddrListBean.DataBean> data = new ArrayList<>();
    private int mSelectedPos = -1;//实现单选  变量保存当前选中的position

    public int getSelectedPos() {
        return mSelectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        mSelectedPos = selectedPos;
    }

    // 第二种
    public AddrAdapterMainAdaper(List<AddrListBean.DataBean> data) {
        super(R.layout.item_addr_main, data);
        this.data = data;

    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, AddrListBean.DataBean s) {
        TextView mTv = baseViewHolder.getView(R.id.item_addr_main_default_tv);
        if (s.isIsDefault()) {
            baseViewHolder.setImageResource(R.id.item_addr_main_default_iv, R.drawable.ic_addr_defualt);
            mTv.setText("默认地址");
            mTv.setTextColor(baseViewHolder.getConvertView().getContext().getResources().getColor(R.color.colorPrimary));
        } else {
            baseViewHolder.setImageResource(R.id.item_addr_main_default_iv, R.drawable.ic_addr_defualt_not);
            mTv.setText("设为默认");
            mTv.setTextColor(baseViewHolder.getConvertView().getContext().getResources().getColor(R.color.text_color_gray));
        }
        baseViewHolder.addOnClickListener(R.id.item_addr_main_default_iv).addOnClickListener(R.id.item_addr_main_default_tv)
                .addOnClickListener(R.id.item_addr_main_edit_tv).addOnClickListener(R.id.item_addr_main_edit_iv)
                .addOnClickListener(R.id.item_addr_main_del_tv).addOnClickListener(R.id.item_addr_main_del_iv);
//        baseViewHolder.setText(R.id.item_addr_main_name_tv, s.getReceiver())
//                .setText(R.id.item_addr_main_phonenum_tv, s.getPhoneNumber())
//                .setText(R.id.item_addr_main_addr_tv, s.getAddress());

//        ((SimpleDraweeView) baseViewHolder.getView(R.id.tmp_home_recommend_img)).setImageURI(Uri.parse("res://  /"+R.drawable.img_xiaomian));
//        baseViewHolder.addOnClickListener(R.id.tmp_home_recommend_tv).addOnClickListener(R.id.tmp_home_recommend_img);

    }
}
