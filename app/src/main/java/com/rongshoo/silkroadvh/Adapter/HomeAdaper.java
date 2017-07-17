package com.rongshoo.silkroadvh.Adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

import app.dinus.com.itemdecoration.GridDividerItemDecoration;

/**
 * Adapter 主页item
 * Created by RS-KXH on 2017/6/24
 */
public class HomeAdaper extends BaseQuickAdapter<HomeBean.DataBean.ShoptypeBean, BaseViewHolder> {

    List<HomeBean.DataBean.ShoptypeBean.ProductBean> data = new ArrayList<>();
    HomeChildAdaper homeAdaper;

    //alt+insert constructor 快速构造方法
    public HomeAdaper(int layoutResId, List<HomeBean.DataBean.ShoptypeBean> data) {
        super(layoutResId, data);
    }

    // 第二种
    public HomeAdaper(List<HomeBean.DataBean.ShoptypeBean> data) {
        super(R.layout.item_home, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeBean.DataBean.ShoptypeBean map) {
        if (map.getAd().size() > 0) {
            if (!TextUtils.isEmpty(map.getAd().get(0).getSimgurl())) {
                ((SimpleDraweeView) baseViewHolder.getView(R.id.banner)).setImageURI(map.getAd().get(0).getSimgurl());
            } else {
                ((SimpleDraweeView) baseViewHolder.getView(R.id.banner)).setImageURI("res://  /" + R.mipmap.default_img);
            }
        } else {
            ((SimpleDraweeView) baseViewHolder.getView(R.id.banner)).setImageURI("res://  /" + R.mipmap.default_img);
        }


//        if (map.get("img") != null) {
//            ((SimpleDraweeView) baseViewHolder.getView(R.id.banner)).setImageURI("res://  /" + map.get("img"));
//        }

        baseViewHolder.setText(R.id.name_tv, map.getTitle());
        GridLayoutManager layoutManager = new GridLayoutManager(baseViewHolder.getConvertView().getContext(), 3);
        ((RecyclerView) baseViewHolder.getView(R.id.home_item_rv)).setNestedScrollingEnabled(false);
        ((RecyclerView) baseViewHolder.getView(R.id.home_item_rv)).setLayoutManager(layoutManager);
        baseViewHolder.setIsRecyclable(false);//复用导致布局错乱故禁止
        // List<Map<String, String>> data = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            Map<String, String> item = new HashMap<>();
//            data.add(item);
//
//        }
        data = map.getProduct();
        if (data.size() > 6) {
            List<HomeBean.DataBean.ShoptypeBean.ProductBean> tData = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                tData.add(data.get(i));
            }
            homeAdaper = new HomeChildAdaper(tData);
        } else {
            homeAdaper = new HomeChildAdaper(data);
        }
        GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(baseViewHolder.getConvertView().getContext(),
                GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
        dividerItemDecoration.setVerticalDivider(baseViewHolder.getConvertView().getContext().getResources().getDrawable(R.drawable.home_grid_partline));
        dividerItemDecoration.setHorizontalDivider(baseViewHolder.getConvertView().getContext().getResources().getDrawable(R.drawable.home_grid_partline_view));
        ((RecyclerView) baseViewHolder.getView(R.id.home_item_rv)).addItemDecoration(dividerItemDecoration);
        ((RecyclerView) baseViewHolder.getView(R.id.home_item_rv)).setAdapter(homeAdaper);
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
