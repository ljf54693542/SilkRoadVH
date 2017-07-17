package com.rongshoo.silkroadvh.Adapter;

import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.GoodsKindBean;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class ShopSortAdaper extends BaseQuickAdapter<GoodsKindBean.DataBean.RecordBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public ShopSortAdaper(int layoutResId, List<GoodsKindBean.DataBean.RecordBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public ShopSortAdaper(List<GoodsKindBean.DataBean.RecordBean> data) {
        super(R.layout.item_brand, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsKindBean.DataBean.RecordBean map) {
        //baseViewHolder.setIsRecyclable(false);//关闭复用
        CheckBox box = baseViewHolder.getView(R.id.name_tv);
        box.setText(map.getScname());
        if (map.getChecked()) {
            box.setChecked(true);
        } else {
            box.setChecked(false);
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
