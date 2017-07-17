package com.rongshoo.silkroadvh.Adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.GoodsKindBean;

import java.util.List;


public class SortChildAdaper extends BaseQuickAdapter<GoodsKindBean.DataBean.RecordBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public SortChildAdaper(int layoutResId, List<GoodsKindBean.DataBean.RecordBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public SortChildAdaper(List<GoodsKindBean.DataBean.RecordBean> data) {
        super(R.layout.item_sort_child, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsKindBean.DataBean.RecordBean map) {
//        ((SimpleDraweeView) baseViewHolder.getView(R.id.tmp_home_sort_img)).setImageURI("res://  /"+map.get("img"));
          if (!TextUtils.isEmpty(map.getSimgurl())){
              baseViewHolder.getView(R.id.tmp_home_sort_img).setVisibility(View.VISIBLE);
              ((SimpleDraweeView) baseViewHolder.getView(R.id.tmp_home_sort_img)).setImageURI(map.getSimgurl());
          }else {
              baseViewHolder.getView(R.id.tmp_home_sort_img).setVisibility(View.INVISIBLE);
          }
          baseViewHolder.setText(R.id.tmp_home_sort_tv,map.getScname());
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
