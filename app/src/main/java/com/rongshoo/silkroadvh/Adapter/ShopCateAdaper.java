package com.rongshoo.silkroadvh.Adapter;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.GoodsListBean;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class ShopCateAdaper extends BaseQuickAdapter<GoodsListBean.DataBean.RowsBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public ShopCateAdaper(int layoutResId, List<GoodsListBean.DataBean.RowsBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public ShopCateAdaper(List<GoodsListBean.DataBean.RowsBean> data) {
        super(R.layout.item_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsListBean.DataBean.RowsBean map) {
        ((TextView) baseViewHolder.getView(R.id.price_old_tv)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        baseViewHolder.getView(R.id.cart_iv).setVisibility(View.VISIBLE);
        baseViewHolder.getView(R.id.price_old_tv).setVisibility(View.VISIBLE);
        baseViewHolder.setText(R.id.name_tv, map.getSproductname());
        baseViewHolder.setText(R.id.price_old_tv, "￥" + map.getDprice());
        baseViewHolder.setText(R.id.price_tv, "￥" + map.getDrealprice());
        baseViewHolder.setText(R.id.num_tv, "已售" + map.getIsales());
        if (!TextUtils.isEmpty(map.getSimgurl())) {
            ((SimpleDraweeView) baseViewHolder.getView(R.id.avatar_sdv)).setImageURI(map.getSimgurl());
        }else {
            ((SimpleDraweeView) baseViewHolder.getView(R.id.avatar_sdv)).setImageURI("res://  /"+R.mipmap.default_middle_img);
        }
//        ((SimpleDraweeView) baseViewHolder.getView(R.id.tmp_home_sort_img)).setImageURI("res://  /"+map.get("img"));
//        baseViewHolder.setText(R.id.tmp_home_sort_tv,baseViewHolder.getConvertView().getResources().getString(Integer.parseInt(map.get("name").toString())));
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
