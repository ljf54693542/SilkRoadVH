package com.rongshoo.silkroadvh.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.RoomBean;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class RoomChoiceAdaper extends BaseQuickAdapter<RoomBean.DataBean.RecordBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public RoomChoiceAdaper(int layoutResId, List<RoomBean.DataBean.RecordBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public RoomChoiceAdaper(List<RoomBean.DataBean.RecordBean> data) {
        super(R.layout.item_room_choice, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RoomBean.DataBean.RecordBean map) {
        //baseViewHolder.setIsRecyclable(false);//关闭复用
        baseViewHolder.setText(R.id.time_tv, map.getSdes());
        baseViewHolder.setText(R.id.tv_item_shopcart_cloth_price, map.getDprice());
        baseViewHolder.setText(R.id.et_item_shopcart_cloth_num,""+ map.getChoiceNum());
        baseViewHolder.addOnClickListener(R.id.iv_item_shopcart_cloth_minus).addOnClickListener(R.id.iv_item_shopcart_cloth_add);
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
