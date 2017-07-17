package com.rongshoo.silkroadvh.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rongshoo.silkroadvh.Adapter.ShopCartJDAdapter;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.BaseFragment;
import com.rongshoo.silkroadvh.bean.ShopCartBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rlv_shopcart)
    RecyclerView mRlvShopcart;
    @BindView(R.id.iv_shopcart_addselect)
    ImageView mIvShopcartAddselect;
    @BindView(R.id.tv_shopcart_addselect)
    TextView mTvShopcartAddselect;
    @BindView(R.id.tv_shopcart_totalprice)
    TextView mTvShopcartTotalprice;
    @BindView(R.id.tv_shopcart_totalnum)
    TextView mTvShopcartTotalnum;
    @BindView(R.id.tv_shopcart_submit)
    TextView mTvShopcartSubmit;
    @BindView(R.id.ll_pay)
    LinearLayout mLlPay;
   // Unbinder unbinder;

    //  Unbinder unbinder1;
    private BaseActivity mBaseActivity;
    private ShopCartJDAdapter mShopCartAdapter;
    private int mCount, mPosition;
    private float mTotalPrice1;
    private boolean mSelect;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private List<ShopCartBean.CartlistBean> mAllOrderList = new ArrayList<>();
    private ArrayList<ShopCartBean.CartlistBean> mGoPayList = new ArrayList<>();
    private List<String> mHotProductsList = new ArrayList<>();

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_cart, container, false);
            ButterKnife.bind(this, view);
            mBaseActivity = (BaseActivity) getActivity();
//            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//            lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//            mLlPay.setLayoutParams(lp);
            mRlvShopcart.setLayoutManager(new LinearLayoutManager(mBaseActivity));
            mShopCartAdapter = new ShopCartJDAdapter(mBaseActivity, mAllOrderList);
            mRlvShopcart.setAdapter(mShopCartAdapter);
            //删除商品接口
            mShopCartAdapter.setOnDeleteClickListener(new ShopCartJDAdapter.OnDeleteClickListener() {
                @Override
                public void onDeleteClick(View view, int position, int cartid) {
                    mShopCartAdapter.notifyDataSetChanged();
                }
            });
            //修改数量接口
            mShopCartAdapter.setOnEditClickListener(new ShopCartJDAdapter.OnEditClickListener() {
                @Override
                public void onEditClick(int position, int cartid, int count) {
                    mCount = count;
                    mPosition = position;
                }
            });
            //实时监控全选按钮
            mShopCartAdapter.setResfreshListener(new ShopCartJDAdapter.OnResfreshListener() {
                @Override
                public void onResfresh(boolean isSelect) {
                    mSelect = isSelect;
                    if (isSelect) {
                        mIvShopcartAddselect.setImageResource(R.drawable.shopcart_selected);
                        //  Drawable left = getResources().getDrawable(R.drawable.shopcart_selected);
                        //mTvShopcartAddselect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                    } else {
                        //   Drawable left = getResources().getDrawable(R.drawable.shopcart_unselected);
                        //   mTvShopcartAddselect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                        mIvShopcartAddselect.setImageResource(R.drawable.shopcart_unselected);
                    }
                    float mTotalPrice = 0;
                    int mTotalNum = 0;
                    mTotalPrice1 = 0;
                    mGoPayList.clear();
                    for (int i = 0; i < mAllOrderList.size(); i++)
                        if (mAllOrderList.get(i).getIsSelect()) {
                            mTotalPrice += Float.parseFloat(mAllOrderList.get(i).getPrice()) * mAllOrderList.get(i).getCount();
                            mTotalNum += 1;
                            mGoPayList.add(mAllOrderList.get(i));
                        }
                    mTotalPrice1 = mTotalPrice;
                    mTvShopcartTotalprice.setText("总价：￥" + mTotalPrice);
                    mTvShopcartTotalnum.setText("共" + mTotalNum + "件商品");
                }
            });

            //全选
            mTvShopcartAddselect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAllOnClick();

                }
            });
            mIvShopcartAddselect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAllOnClick();
                }
            });

            initData();
            mShopCartAdapter.notifyDataSetChanged();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        //     unbinder1 = ButterKnife.bind(this, view);
        return view;
    }

    private void checkAllOnClick() {
        mSelect = !mSelect;
        if (mSelect) {
            mIvShopcartAddselect.setImageResource(R.drawable.shopcart_selected);
//                        Drawable left = getResources().getDrawable(R.drawable.shopcart_selected);
//                        mTvShopcartAddselect.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
            for (int i = 0; i < mAllOrderList.size(); i++) {
                mAllOrderList.get(i).setSelect(true);
                mAllOrderList.get(i).setShopSelect(true);
            }
        } else {
            mIvShopcartAddselect.setImageResource(R.drawable.shopcart_unselected);
//                        Drawable left = getResources().getDrawable(R.drawable.shopcart_unselected);
//                        mTvShopcartAddselect.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
            for (int i = 0; i < mAllOrderList.size(); i++) {
                mAllOrderList.get(i).setSelect(false);
                mAllOrderList.get(i).setShopSelect(false);
            }
        }
        mShopCartAdapter.notifyDataSetChanged();
    }

    private void initData() {
        for (int k= 0; k < 4; k++) {
            for (int i = 0; i < 2; i++) {
                ShopCartBean.CartlistBean sb = new ShopCartBean.CartlistBean();
                sb.setShopId(1);
                sb.setPrice("6588");
                sb.setDefaultPic("https://img14.360buyimg.com/n0/jfs/t4366/334/2617378521/84788/3a8c10eb/58d4af7fNb48cee59.jpg");
                sb.setProductName("苹果(Apple) iPhone 7 Plus 苹果7p手机 红色特别版 128GB");
                sb.setShopName("京东自营");
                sb.setColor("红色");
                sb.setCount(10);
                mAllOrderList.add(sb);
            }

            for (int i = 0; i < 2; i++) {
                ShopCartBean.CartlistBean sb = new ShopCartBean.CartlistBean();
                sb.setShopId(2);
                sb.setPrice("349");
                sb.setDefaultPic("https://img14.360buyimg.com/n0/jfs/t3070/190/5270715477/271503/dcfa3ae/586b5a2fNe0edb9a0.jpg");
                sb.setProductName("【终身保修】旅行大师 万向轮拉杆密码行李箱 T2023-铝合金拉杆");
                sb.setShopName("旅行大师官方旗舰店");
                sb.setColor("绿色");
                sb.setCount(2);
                mAllOrderList.add(sb);
            }

        }

        isSelectFirst(mAllOrderList);
    }

    public static void isSelectFirst(List<ShopCartBean.CartlistBean> list) {
        if (list.size() > 0) {
            list.get(0).setIsFirst(1);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).getShopId() == list.get(i - 1).getShopId()) {
                    list.get(i).setIsFirst(2);
                } else {
                    list.get(i).setIsFirst(1);
                }
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //    unbinder1.unbind();
    }
}
