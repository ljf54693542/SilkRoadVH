package com.rongshoo.silkroadvh.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rongshoo.silkroadvh.R;

import butterknife.ButterKnife;

public class TestFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_find, container, false);
            ButterKnife.bind(this, view);

        }
//        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
//        ViewGroup parent = (ViewGroup) view.getParent();
//        if (parent != null) {
//            parent.removeView(view);
//        }
        return view;
    }


}
