package com.rongshoo.silkroadvh.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;

import java.util.List;
import java.util.Map;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class AttentionAdaper extends BaseQuickAdapter<Map<String,Object>, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public AttentionAdaper(int layoutResId, List<Map<String,Object>> data) {
        super(layoutResId, data);
    }

    // 第二种
    public AttentionAdaper(List<Map<String,Object>> data) {
        super(R.layout.item_attention, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Map<String,Object> map) {

        baseViewHolder.addOnClickListener(R.id.cancel_tv);

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
