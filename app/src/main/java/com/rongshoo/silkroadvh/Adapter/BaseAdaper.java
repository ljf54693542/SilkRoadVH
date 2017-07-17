package com.rongshoo.silkroadvh.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Adapter 样例使用
 * Created by RS-KXH on 2016/12/30.
 */

public class BaseAdaper extends BaseQuickAdapter<String, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public BaseAdaper(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    //第二种
    //    public BaseAdaper() {
    //        super(R.layout.layout_fresco, data);
    //    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
//        helper.addOnClickListener(img).addOnClickListener(R.id.tweetText).addOnClickListener(R.id.tweetName);
//        switch (helper.getLayoutPosition()%
//                3){
//            case 0:
//                //  img.setImageURI(Uri.parse("http://img1.3lian.com/img013/v4/96/d/49.jpg"));
//                ((SimpleDraweeView)helper.getView(R.id.img)).setImageURI(Uri.parse("http://img1.3lian.com/img013/v4/96/d/49.jpg"));
//                // helper.setImageResource(img,R.mipmap.animation_img1);
//                break;
//            case 1:
//                helper.setImageResource(img,R.mipmap.animation_img2);
//                break;
//            case 2:
//                helper.setImageResource(img,R.mipmap.animation_img3);
//                break;
//        }
//        helper.setText(R.id.tweetName,"Hoteis in Rio de Janeiro");
//        String msg="\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
//        ( (TextView)helper.getView(R.id.tweetText)).setText(SpannableStringUtils.getBuilder(msg).append("landscapes and nedes").setClickSpan(clickableSpan).create());
//        ( (TextView)helper.getView(R.id.tweetText)).setMovementMethod(LinkMovementMethod.getInstance());

    }
}
