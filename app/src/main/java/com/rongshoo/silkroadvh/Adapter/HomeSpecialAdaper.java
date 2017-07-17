package com.rongshoo.silkroadvh.Adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.HomeBean;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class HomeSpecialAdaper extends BaseQuickAdapter<HomeBean.DataBean.SpecialtypeBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public HomeSpecialAdaper(int layoutResId, List<HomeBean.DataBean.SpecialtypeBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public HomeSpecialAdaper(List<HomeBean.DataBean.SpecialtypeBean> data) {
        super(R.layout.item_home_special, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeBean.DataBean.SpecialtypeBean map) {
        if (!TextUtils.isEmpty(map.getSimgurl())) {
            ((SimpleDraweeView) baseViewHolder.getView(R.id.banner)).setImageURI(map.getSimgurl());
        } else {
            ((SimpleDraweeView) baseViewHolder.getView(R.id.banner)).setImageURI("res://  /" + R.mipmap.default_middle_img);
        }

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
