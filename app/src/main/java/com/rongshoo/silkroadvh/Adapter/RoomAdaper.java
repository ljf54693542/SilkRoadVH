package com.rongshoo.silkroadvh.Adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.RoomBean;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class RoomAdaper extends BaseQuickAdapter<RoomBean.DataBean.RecordBean, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public RoomAdaper(int layoutResId, List<RoomBean.DataBean.RecordBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public RoomAdaper(List<RoomBean.DataBean.RecordBean> data) {
        super(R.layout.item_hotel_room, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RoomBean.DataBean.RecordBean map) {
        //baseViewHolder.setIsRecyclable(false);//关闭复用
//        CheckBox box = baseViewHolder.getView(R.id.infor);
//        box.setChecked(false);
//        if (Integer.parseInt(map.getIstock()) > 0) {
//            box.setEnabled(true);
//            if (map.getChecked()) {
//                box.setChecked(true);
//                box.setText(map.getSdes() + "\n" + "预订数量" + "\n" + map.getChoiceNum());
//            } else {
//                box.setText(map.getSdes() + "\n" + "剩余:" + map.getIstock() + "\n￥" + map.getDprice());
//                box.setChecked(false);
//            }
//        } else {
//            box.setChecked(false);
//            box.setEnabled(false);
//            box.setText(map.getSdes() + "\n" + "售罄" + "\n￥" + map.getDprice());
//        }

        if (Integer.parseInt(map.getIstock()) > 0) {
            baseViewHolder.getView(R.id.infor).setEnabled(true);
            if (map.getChecked()) {
                baseViewHolder.getView(R.id.infor).setBackgroundResource(R.drawable.yellow_hotel);
                ((TextView) baseViewHolder.getView(R.id.infor)).setText(map.getSdes() + "\n" + "预订数量" + "\n" + map.getChoiceNum());
            } else {
                baseViewHolder.getView(R.id.infor).setBackgroundResource(R.drawable.color_blue);
                ((TextView) baseViewHolder.getView(R.id.infor)).setText(map.getSdes() + "\n" + "剩余:" + map.getIstock() + "\n￥" + map.getDprice());
            }
            ((TextView) baseViewHolder.getView(R.id.infor)).setTextColor(baseViewHolder.getConvertView().getResources().getColor(R.color.white));
        } else {
            baseViewHolder.getView(R.id.infor).setBackgroundResource(R.drawable.gray_hotel);
            baseViewHolder.getView(R.id.infor).setEnabled(false);
            ((TextView) baseViewHolder.getView(R.id.infor)).setText(map.getSdes() + "\n" + "售罄" + "\n￥" + map.getDprice());
            ((TextView) baseViewHolder.getView(R.id.infor)).setTextColor(baseViewHolder.getConvertView().getResources().getColor(R.color.text_color_gray));
        }


        //yellow_hotel      checked
        //gray_hotel     etEnabled(false);
        //color_blue          false
        //text_gray       etEnabled(false);


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
