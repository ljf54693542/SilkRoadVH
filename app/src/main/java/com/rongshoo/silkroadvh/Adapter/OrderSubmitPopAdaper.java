package com.rongshoo.silkroadvh.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.YunFeiBean;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class OrderSubmitPopAdaper extends BaseQuickAdapter<YunFeiBean.DataBean.YfBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public OrderSubmitPopAdaper(int layoutResId, List<YunFeiBean.DataBean.YfBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public OrderSubmitPopAdaper(List<YunFeiBean.DataBean.YfBean> data) {
        super(R.layout.item_order_confirm_p, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, YunFeiBean.DataBean.YfBean map) {
        baseViewHolder.setText(R.id.name_tv, map.getName());
        baseViewHolder.setImageResource(R.id.check_iv, R.drawable.shopcart_unselected);
        if (map.isIsCheck()) {
            baseViewHolder.setImageResource(R.id.check_iv, R.drawable.shopcart_selected);
        } else {
            baseViewHolder.setImageResource(R.id.check_iv, R.drawable.shopcart_unselected);
        }
        baseViewHolder.addOnClickListener(R.id.root_ll);

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
