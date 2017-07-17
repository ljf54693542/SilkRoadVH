package com.rongshoo.silkroadvh.Addr;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.CityBean;

import java.util.List;

/**
 * Adapter 推荐列表
 * Created by RS-KXH on 2017/1/4
 */

public class AddrAdapterCAdaper extends BaseQuickAdapter<CityBean.CitylistBean.CBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public AddrAdapterCAdaper(int layoutResId, List<CityBean.CitylistBean.CBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public AddrAdapterCAdaper(List<CityBean.CitylistBean.CBean> data) {
        super(R.layout.item_addr_p, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CityBean.CitylistBean.CBean s) {
        baseViewHolder.setText(R.id.item_addr_p_nameTv, s.getN());
    }
}
