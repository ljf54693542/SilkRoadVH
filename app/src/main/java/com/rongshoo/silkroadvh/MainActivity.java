package com.rongshoo.silkroadvh;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.rongshoo.silkroadvh.Fragment.CartFragment;
import com.rongshoo.silkroadvh.Fragment.HomeFragment;
import com.rongshoo.silkroadvh.Fragment.MineFragment;
import com.rongshoo.silkroadvh.Fragment.TestFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {

    @BindView(R.id.main_content_fl)
    FrameLayout mainContentFl;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;

    private final String TAG = "MainActivity";
    //标题数组
    private int titles[] = {R.string.home,R.string.find,R.string.cart,R.string.mine};
    //图标数组
    private int imgbtn[] = {R.drawable.ic_home_selector,R.drawable.ic_home_find_selector,R.drawable.ic_cart_selector ,R.drawable.ic_home_my_selector};
    //fragment数组
    private Class fragmentArray[] = {HomeFragment.class, TestFragment.class,CartFragment.class,MineFragment.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //底部导航栏引入
        tabhost.setup(this, getSupportFragmentManager(), R.id.main_content_fl);
        initTab();
        updateColor();
    }

    private void initTab() {
        for (int i = 0; i < titles.length; i++) {
            TabHost.TabSpec spec = tabhost.newTabSpec(getResources().getString(titles[i])).setIndicator(getView(i));
            tabhost.addTab(spec, fragmentArray[i], null);
        }
        //设置tabs之间的分隔线不显示
        tabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        tabhost.setOnTabChangedListener(this);
    }

    /**
     * 初始化导航菜单
     *
     * @param i
     * @return
     */
    private View getView(int i) {
        //取得布局实例
        View view = View.inflate(MainActivity.this, R.layout.main_tabcontent, null);
        //取得布局对象
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_img);
        TextView textView = (TextView) view.findViewById(R.id.tab_name);

        //设置图标
        imageView.setImageResource(imgbtn[i]);
        //设置标题
        textView.setText(getResources().getString(titles[i]));
        return view;
    }

    @Override
    public void onTabChanged(final String s) {

//        //获取当前页面
//        Fragment fg = getSupportFragmentManager().findFragmentByTag(s);
//        Log.d(TAG, "onTabChanged(): " + s + ", fragment " + fg);
//        if (fg == null) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Fragment fg = getSupportFragmentManager().findFragmentByTag(s);
//                    Log.d(TAG, "onTabChanged() delay 50ms: " + s + ", fragment " + fg);
//                }
//            }, 50);
//        }
        updateColor();
    }

    /**
     * 更新导航字体颜色
     */
    private void updateColor() {
        for (int i = 0; i < titles.length; i++) {
            TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(R.id.tab_name);

            if (tabhost.getCurrentTab() == i) {//选中
                tv.setTextColor(this.getResources().getColorStateList(R.color.colorPrimary));

            } else {//不选中
                tv.setTextColor(this.getResources().getColorStateList(R.color.nav_text_gray));

            }

        }
    }


}