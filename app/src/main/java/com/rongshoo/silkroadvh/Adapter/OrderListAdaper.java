package com.rongshoo.silkroadvh.Adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.OrderListBean;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class OrderListAdaper extends BaseQuickAdapter<OrderListBean.DataBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public OrderListAdaper(int layoutResId, List<OrderListBean.DataBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public OrderListAdaper(List<OrderListBean.DataBean> data) {
        super(R.layout.item_order_list, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, OrderListBean.DataBean map) {
        int size = getData().size();
        int index = baseViewHolder.getAdapterPosition();
        baseViewHolder.getView(R.id.ll_shopcart_header).setVisibility(View.GONE);
        baseViewHolder.getView(R.id.footer_ll).setVisibility(View.GONE);
        if (getData().size() > 1) {//显示头部
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
                    baseViewHolder.getView(R.id.footer_ll).setVisibility(View.GONE);
                } else {
                    baseViewHolder.getView(R.id.footer_ll).setVisibility(View.VISIBLE);
                }

            } else {
                baseViewHolder.getView(R.id.footer_ll).setVisibility(View.VISIBLE);
            }

        } else {
            baseViewHolder.getView(R.id.ll_shopcart_header).setVisibility(View.VISIBLE);
            baseViewHolder.getView(R.id.footer_ll).setVisibility(View.VISIBLE);
        }

        baseViewHolder.setText(R.id.tv_item_shopcart_shopname, map.getName());
      //  baseViewHolder.setText(R.id.distribution_price_tv, map.getYfText());
        baseViewHolder.addOnClickListener(R.id.status_left_tv).addOnClickListener(R.id.status_right_tv).addOnClickListener(R.id.item_ll);
        baseViewHolder.getView(R.id.status_left_tv).setVisibility(View.GONE);
        baseViewHolder.getView(R.id.status_right_tv).setVisibility(View.GONE);
        //订单状态
        switch (map.getIstate()) {
//            0，锁单等待付款，
//            1、付款成功，等待商家发货
//            2、付款失败，订单取消
//            3、商家已经发货
//            4、用户取消订单
//            5、买家已经收货
//            6、已经评价
            case 0:
                baseViewHolder.getView(R.id.status_right_tv).setVisibility(View.VISIBLE);
                baseViewHolder.setText(R.id.status_right_tv,"去支付");
                baseViewHolder.setText(R.id.right_tv,"待付款");
                baseViewHolder.getView(R.id.status_ll).setVisibility(View.VISIBLE);
                break;
            case 1:
                baseViewHolder.getView(R.id.status_ll).setVisibility(View.GONE);
                baseViewHolder.setText(R.id.right_tv,"待发货");
                break;
            case 2:
                baseViewHolder.setText(R.id.right_tv,"付款失败，交易关闭");
                baseViewHolder.getView(R.id.status_left_tv).setVisibility(View.VISIBLE);
                baseViewHolder.setText(R.id.status_left_tv,"删除订单");
                baseViewHolder.getView(R.id.status_ll).setVisibility(View.VISIBLE);
                break;
            case 3:
                baseViewHolder.setText(R.id.right_tv,"待收货");
                baseViewHolder.getView(R.id.status_left_tv).setVisibility(View.VISIBLE);
                baseViewHolder.setText(R.id.status_left_tv,"查看物流");
                baseViewHolder.getView(R.id.status_right_tv).setVisibility(View.VISIBLE);
                baseViewHolder.setText(R.id.status_right_tv,"确认收货");
                baseViewHolder.getView(R.id.status_ll).setVisibility(View.VISIBLE);

                break;
            case 4:
                baseViewHolder.setText(R.id.right_tv,"取消订单，交易关闭");
                baseViewHolder.getView(R.id.status_left_tv).setVisibility(View.VISIBLE);
                baseViewHolder.setText(R.id.status_left_tv,"删除订单");
                baseViewHolder.getView(R.id.status_ll).setVisibility(View.VISIBLE);
                break;
            case 5:
                baseViewHolder.setText(R.id.right_tv,"交易成功");
                baseViewHolder.getView(R.id.status_left_tv).setVisibility(View.VISIBLE);
                baseViewHolder.setText(R.id.status_left_tv,"评价晒单");
                baseViewHolder.getView(R.id.status_ll).setVisibility(View.VISIBLE);
                break;
            case 6:
                baseViewHolder.setText(R.id.right_tv,"交易成功");
                baseViewHolder.setText(R.id.status_left_tv,"删除订单");
                baseViewHolder.getView(R.id.status_ll).setVisibility(View.VISIBLE);
                break;
        }


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
