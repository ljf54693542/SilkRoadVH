package com.rongshoo.silkroadvh.Adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.GoodsKindBean;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class SortAdaper extends BaseQuickAdapter<GoodsKindBean.DataBean.RecordBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public SortAdaper(int layoutResId, List<GoodsKindBean.DataBean.RecordBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public SortAdaper(List<GoodsKindBean.DataBean.RecordBean> data) {
        super(R.layout.item_sort, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsKindBean.DataBean.RecordBean map) {
        TextView textView = baseViewHolder.getView(R.id.name_tv);
        textView.setText(map.getScname());
        if (map.getChecked()) {
            textView.setBackgroundColor(baseViewHolder.getConvertView().getResources().getColor(R.color.white));
            textView.setTextColor(baseViewHolder.getConvertView().getResources().getColor(R.color.colorPrimary));
        } else {
            textView.setBackgroundColor(baseViewHolder.getConvertView().getResources().getColor(R.color.transparent));
            textView.setTextColor(baseViewHolder.getConvertView().getResources().getColor(R.color.text_color_gray));
        }
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
