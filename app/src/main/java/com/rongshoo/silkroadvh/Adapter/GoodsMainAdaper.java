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
public class GoodsMainAdaper extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    //alt+insert constructor 快速构造方法
    public GoodsMainAdaper(int layoutResId, List<Map<String, String>> data) {
        super(layoutResId, data);
    }

    // 第二种
    public GoodsMainAdaper(List<Map<String, String>> data) {
        super(R.layout.item_goods_main, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Map<String, String> map) {
        baseViewHolder.setText(R.id.name_tv,map.get("name"));

//        if (!TextUtils.isEmpty(map.get("img"))) {
//            ((SimpleDraweeView) baseViewHolder.getView(R.id.banner)).setImageURI("res://  /" + map.get("img"));
//        }
//        baseViewHolder.setText(R.id.name_tv,map.get("name"));
//
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
