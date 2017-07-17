package com.rongshoo.silkroadvh.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.bean.YunFeiBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaoj on 2017/7/4.
 */

public class Oadapter extends RecyclerView.Adapter<Oadapter.ViewHolder>implements View.OnClickListener {

    private List<YunFeiBean.DataBean.YfBean> data = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener = null;
    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    //R.layout.item_order_confirm_p
    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rootll;
        TextView nameTv;
        ImageView checkIv;

        public ViewHolder(View itemView) {
            super(itemView);
            rootll = (LinearLayout) itemView.findViewById(R.id.root_ll);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            checkIv = (ImageView) itemView.findViewById(R.id.check_iv);
        }
    }

    public Oadapter(List<YunFeiBean.DataBean.YfBean> data) {
        this.data = data;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_confirm_p, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       // Map<String, Object> map = data.get(position);
        YunFeiBean.DataBean.YfBean map=data.get(position);
        holder.nameTv.setText(map.getName());
        holder.checkIv.setImageResource(R.drawable.shopcart_unselected);
        if (map.isIsCheck()) {
            holder.checkIv.setImageResource(R.drawable.shopcart_selected);
        } else {
            holder.checkIv.setImageResource(R.drawable.shopcart_unselected);
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setNewData(List<YunFeiBean.DataBean.YfBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }


}
