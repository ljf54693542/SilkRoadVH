package com.rongshoo.silkroadvh.Adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.SortBean;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class GoodsAttrChildAdaper extends BaseQuickAdapter<SortBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public GoodsAttrChildAdaper(int layoutResId, List<SortBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public GoodsAttrChildAdaper(List<SortBean> data) {
        super(R.layout.item_goods_attr_child, data);
    }

    private int checkPosition = 0;

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SortBean map) {
        TextView text = baseViewHolder.getView(R.id.name_tv);
        text.setText(map.getName());
//        if (map.getChecked()) {
//            box.setChecked(true);
//            checkPosition = baseViewHolder.getAdapterPosition();
//        } else {
//            box.setChecked(false);
//        }
        text.setTag(checkPosition);
//brand_text_bg_selector
      //  brand_text_selector
        if (map.getChecked()){
            checkPosition = baseViewHolder.getAdapterPosition();
            text.setBackground(baseViewHolder.getConvertView().getResources().getDrawable(R.drawable.brand_text_bg_blue));
            text.setTextColor(baseViewHolder.getConvertView().getResources().getColor(R.color.white));
        }else {
            text.setBackground(baseViewHolder.getConvertView().getResources().getDrawable(R.drawable.brand_text_bg_normal));
            text.setTextColor(baseViewHolder.getConvertView().getResources().getColor(R.color.text_color_black));
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
