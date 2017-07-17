package com.rongshoo.silkroadvh.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.AttrBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Adapter 主页头部分类
 * Created by RS-KXH on 2017/6/24
 */
public class GoodsAttrAdaper extends BaseQuickAdapter<AttrBean, BaseViewHolder> {

    // RecyclerView mRv;
    //  List<SortBean> cData = new ArrayList<>();
    //  String[] title = {"红色", "蓝", "黄绿色", "紫红", "白", "黑紫色", "红绿", "湖底蓝", "火红"};

    //alt+insert constructor 快速构造方法
    public GoodsAttrAdaper(int layoutResId, List<AttrBean> data) {
        super(layoutResId, data);
    }

    UpdateListener mUpdateListener;

    // 第二种
    public GoodsAttrAdaper(List<AttrBean> data) {
        super(R.layout.item_goods_attr, data);
//        for (int i = 0; i < 9; i++) {
//            SortBean bean;
//            if (i == 0) {
//                bean = new SortBean(title[i], 0, true);
//            } else {
//                bean = new SortBean(title[i], 0, false);
//            }
//            cData.add(bean);
//        }
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final AttrBean map) {
        mUpdateListener = (UpdateListener) baseViewHolder.getConvertView().getContext();
        baseViewHolder.setText(R.id.tmp_attr_name, map.getKey());
        final TagFlowLayout tagFlowLayout = baseViewHolder.getView(R.id.my_tagflow);
        tagFlowLayout.setAdapter(new TagAdapter<String>(map.getValue()) {
            @Override
            public View getView(FlowLayout parent, int position, String item) {
                TextView tv = (TextView) LayoutInflater.from(baseViewHolder.getConvertView().getContext()).inflate(R.layout.item_goods_attr_child,
                        tagFlowLayout, false);
                tv.setText(item);
                return tv;
            }
        });
        //        //预先设置选中
//        mAdapter.setSelectedList(1,3,5,7,8,9);
//        //获得所有选中的pos集合
//        flowLayout.getSelectedList();
        tagFlowLayout.getAdapter().setSelectedList();
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (tagFlowLayout.getSelectedList().size() > 0) {
                    getData().get(baseViewHolder.getAdapterPosition()).setChoiceStr(map.getValue().get(position));
                } else {
                    getData().get(baseViewHolder.getAdapterPosition()).setChoiceStr(null);
                }
                // AttrBean bean = getData().get(baseViewHolder.getAdapterPosition());
                //   Toast.makeText(baseViewHolder.getConvertView().getContext(), "" + map.getValue().get(position), Toast.LENGTH_SHORT).show();
                mUpdateListener.update();
                return true;
            }
        });
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

    public interface UpdateListener {

        public void update();
    }
}
