package com.rongshoo.silkroadvh.Adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.YunFeiBean;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class OrderSubmitAdaper extends BaseQuickAdapter<YunFeiBean.DataBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public OrderSubmitAdaper(int layoutResId, List<YunFeiBean.DataBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public OrderSubmitAdaper(List<YunFeiBean.DataBean> data) {
        super(R.layout.item_order_submit, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, YunFeiBean.DataBean map) {
        int size = getData().size();
        int index = baseViewHolder.getAdapterPosition()-1;
        baseViewHolder.getView(R.id.ll_shopcart_header).setVisibility(View.GONE);
        baseViewHolder.getView(R.id.distribution_ll).setVisibility(View.GONE);
        if (getData().size() > 0) {//显示头部
            //getData().get(baseViewHolder.getAdapterPosition()- 2).get("name").equals(getData().get(baseViewHolder.getAdapterPosition()-1).get("name"))
            if (index > 0) {
                if (map.getName().equals(getData().get(index - 1).getName())) {
                    baseViewHolder.getView(R.id.ll_shopcart_header).setVisibility(View.GONE);
                } else {
                    baseViewHolder.getView(R.id.ll_shopcart_header).setVisibility(View.VISIBLE);
                }

            } else {
                baseViewHolder.getView(R.id.ll_shopcart_header).setVisibility(View.VISIBLE);
            }
            if (index < getData().size() - 1) {
                if (map.getName().equals(getData().get(index + 1).getName())) {
                    baseViewHolder.getView(R.id.distribution_ll).setVisibility(View.GONE);
                } else {
                    baseViewHolder.getView(R.id.distribution_ll).setVisibility(View.VISIBLE);
                }

            } else {
                baseViewHolder.getView(R.id.distribution_ll).setVisibility(View.VISIBLE);
            }

        } else {
            baseViewHolder.getView(R.id.ll_shopcart_header).setVisibility(View.VISIBLE);
            baseViewHolder.getView(R.id.distribution_ll).setVisibility(View.VISIBLE);
        }

        baseViewHolder.setText(R.id.tv_item_shopcart_shopname, map.getName());
        baseViewHolder.setText(R.id.distribution_price_tv, map.getYfText());
        baseViewHolder.addOnClickListener(R.id.distribution_price_ll);

//        if (!TextUtils.isEmpty(map.get("img"))) {
//            ((SimpleDraweeView) baseViewHolder.getView(R.id.banner)).setImageURI("res://  /" + map.get("img"));
//        }
//        baseViewHolder.setText(R.id.name_tv,map.get("name"));
//
//        RecyclerView recyclerView=baseViewHolder.getView(R.id.home_item_rv);


        //setImageURI("res://  /"+map.get("img"))
//        if (map.getFilePaths()!=null&&map.getFilePaths().size()>0){
//            ((SimpleDraweeView) baseViewHolder.getView(R.id.tmp_home_sort_img)).setImageURI(Constant.BASE_IMG_URL+map.getFilePaths().get(0).getFilePath());
//        }
//        baseViewHolder.setText(R.id.tmp_home_sort_tv,map.getTypeName());
        //   baseViewHolder.addOnClickListener(R.id.tmp_home_sort_tv).addOnClickListener(R.id.tmp_home_sort_img);
//        String msg="\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
//        ( (TextView)helper.getView(R.id.tweetText)).setText(SpannableStringUtils.getBuilder(msg).append("landscapes and nedes").setClickSpan(clickableSpan).create());
//        ( (TextView)helper.getView(R.id.tweetText)).setMovementMethod(LinkMovementMethod.getInstance());

    }
}
