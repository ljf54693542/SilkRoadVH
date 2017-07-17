package com.rongshoo.silkroadvh.Addr;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.bean.AddrListBean;
import com.rongshoo.silkroadvh.bean.AddrNewBean;
import com.rongshoo.silkroadvh.bean.EventBusMsg;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.rongshoo.silkroadvh.BaseActivity.getCheckDialog;


public class AddrNewFragment extends Fragment {
    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.addr_new_name)
    EditText mAddrNewName;
    @BindView(R.id.addr_new_phonenum)
    EditText mAddrNewPhonenum;
    @BindView(R.id.addr_new_pcs_tv)
    TextView mAddrNewPcsTv;
    @BindView(R.id.addr_new_pcs_lin)
    LinearLayout mAddrNewPcsLin;
    @BindView(R.id.addr_new_detail_addr_tv)
    EditText mAddrNewDetailAddrTv;
    @BindView(R.id.addr_new_default_iv)
    ImageView mAddrNewDefaultIv;
    @BindView(R.id.default_lin)
    LinearLayout mDefaultLin;
    @BindView(R.id.default_part)
    View mDefaultPart;
    @BindView(R.id.addr_new_del_tv)
    TextView mAddrNewDelTv;
    private BaseActivity activity;
    private String pName;
    private String cName;
    private String sName;
    private View view;
    public static String dataBean = "data";
    private Boolean isDefault = false;
    private AddrListBean.DataBean data;
    private String str;

    public AddrNewFragment() {
        // Required empty public constructor
    }

    public static AddrNewFragment newInstance(@Nullable AddrListBean.DataBean data) {
        AddrNewFragment addrNewFragment = new AddrNewFragment();
        Bundle args = new Bundle();
        args.putSerializable(dataBean, (Serializable) data);
        addrNewFragment.setArguments(args);
        return addrNewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = (AddrListBean.DataBean) getArguments().getSerializable(dataBean);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_addr_new, container, false);
            ButterKnife.bind(this, view);
            this.activity = (BaseActivity) getActivity();
            EventBus.getDefault().register(this);
            init();

        }
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAddrChoicMsg(EventBusMsg.AddrChoicMsg msg) {
        pName = msg.getpName();
        cName = msg.getcName();
        sName = msg.getsName();
        item.setArea(pName + " " + cName + " " + sName);
        mAddrNewPcsTv.setText(pName + " " + cName + " " + sName);
    }

    public void init() {
        mAddrNewPcsTv.setText("请选择");
        myFocus();
        if (data != null) {
////            TimingLogger timings = new TimingLogger("TAG", "methodA"); //没有调试成功,据说可以测试时间
////            timings.addSplit("MethodA");
////            timings.addSplit("MethodB");
////            timings.dumpToLog();
//            addrNewName.setText(data.getReceiver());
//            item.setReceiver(data.getReceiver());
//            addrNewPhonenum.setText(data.getPhoneNumber());
//            item.setPhoneNumber(data.getPhoneNumber());
//            //  long startTime = System.nanoTime();
//            //long consumingTime = System.nanoTime() - startTime;
//            //  Log.d("spend", "" + consumingTime);
//            titleTv.setText("编辑收货地址");
//            addrNewPcsTv.setText(data.getArea());
//            item.setArea(data.getArea());
//            addrNewDetailAddrTv.setText(data.getAddress().replace(data.getArea(), ""));
//            item.setAddress(data.getAddress().replace(data.getArea(), ""));
//            addrNewDelTv.setVisibility(View.VISIBLE);
//            item.setStatus(data.getStatus());
//            if (data.getStatus().equals("0")) {
//                setDefault.setVisibility(View.GONE);
//                isDefault = true;
//                defaultIv.setVisibility(View.GONE);
//            } else {
//                setDefault.setVisibility(View.VISIBLE);
//                isDefault = false;
//                defaultIv.setVisibility(View.VISIBLE);
//                part.setVisibility(View.VISIBLE);
//            }
        } else {
            mTitleTv.setText("新增收货地址");
            mDefaultLin.setVisibility(View.VISIBLE);
        }


    }


//    private void showBackDialog() {
//        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
//        dialog.setMessage("您还没有保存，确定退出？");
//        dialog.setNegativeButton("取消", null);
//        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                //从栈中将当前fragment推出
//                getFragmentManager().popBackStack();
//            }
//        });
//        dialog.show();
//
//    }

    //防止监听失效
    View.OnKeyListener mKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK
                    && event.getAction() == KeyEvent.ACTION_UP) {
                //关闭软键盘
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                //使得根View重新获取焦点，以监听返回键
                getFocus();
            }
            return false;
        }

    };

//    View.OnFocusChangeListener mChangeListener=new View.OnFocusChangeListener() {
//        @Override
//        public void onFocusChange(View v, boolean hasFocus) {
//            if (!hasFocus){
//                //使得根View重新获取焦点，以监听返回键
//                getFocus();
//            }
//        }
//    };

    public void myFocus() {
        mAddrNewName.setOnKeyListener(mKeyListener);
        mAddrNewPhonenum.setOnKeyListener(mKeyListener);
        mAddrNewDetailAddrTv.setOnKeyListener(mKeyListener);
//        addrNewName.setOnFocusChangeListener(mChangeListener);
//        addrNewPhonenum.setOnFocusChangeListener(mChangeListener);
//        addrNewDetailAddrTv.setOnFocusChangeListener(mChangeListener);
    }

    @OnClick(value = {R.id.go_back, R.id.addr_new_pcs_lin, R.id.default_lin, R.id.right_tv, R.id.addr_new_del_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                goback();
                break;
            case R.id.addr_new_pcs_lin:
                Intent intent = new Intent(activity, AddrChoiceActivity.class);
                activity.startActivity(intent);
                break;
            case R.id.default_lin:
                str = "0";//辅助长度判断
                if (isDefault) {
                    mAddrNewDefaultIv.setImageResource(R.drawable.ic_addr_defualt_not);
                    isDefault = false;
                } else {
                    mAddrNewDefaultIv.setImageResource(R.drawable.ic_addr_defualt);
                    isDefault = true;
                }
                break;
            case R.id.right_tv:
                item = makeData();
                if (item.getReceiver() == null) {
                    activity.showToast("请输入收货人");
                    return;
                }
                if (item.getPhoneNumber() == null) {
                    activity.showToast("请输入联系方式");
                    return;
                }
                if (!activity.isCellPhone(item.getPhoneNumber())) {
                    activity.showToast("请正确的联系方式");
                    return;
                }
                if (item.getArea() == null || item.getArea().length() == 0) {
                    activity.showToast("请选择地区");
                    return;
                }
                if (item.getAddress() == null) {
                    activity.showToast("请输入明细地址");
                    return;
                }
                //  item.setAddress(item.getArea()+);
                if (item.getAddress() != null && item.getAddress().length() < 5) {
                    activity.showToast("明细地址最少不得低于5个字，最多不得超过60个字");
                    return;
                }
                if (isDefault) {
                    item.setStatus("0");
                } else {
                    item.setStatus("1");
                }
                item.setUserId(Constant.TEST_USER);
//                if (data == null) {
//                    showLoadingDialog("上传中......", false);
//                    postAddrData();
//                } else {
//                    showLoadingDialog("上传中......", false);
//                    updateAddrData();
//                }

                break;
            case R.id.addr_new_del_tv://del
//                getCheckDialog("确定删除？", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        showLoadingDialog("上传中......", false);
//                        postDelData(data.getShippingAddressId());
//                    }
//                }).show();

                break;
        }
    }

//    void postDelData(String shippingAddressId) {
//        Map<String, String> map = new HashMap<>();
//        map.put("shippingAddressId", shippingAddressId);
//        OkHttpUtils.postString().url(Constant.ADDRESS_DEL).content(new Gson().toJson(map)).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                hideDialog();
//                // Log.d("TAG", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                // Log.d("TAG", response);
//                hideDialog();
//                FindPostBean wData = FindPostBean.objectFromData(response);
//                if (wData.getCode().equals("200")) {
//                    EventBus.getDefault().postSticky(new EventBusMsg.AddrDelMsg(data.getShippingAddressId()));
//                    getFragmentManager().popBackStack();
//                } else {
//                    activity.showToast("删除失败！请检查，重试。");
//                }
//            }
//        });
//
//    }

    AddrListBean.DataBean addr = new AddrListBean.DataBean();

//    void updateAddrData() {
//        addr.setShippingAddressId(data.getShippingAddressId());
//        addr.setAddress(item.getAddress());
//        addr.setStatus(item.getStatus());
//        addr.setReceiver(item.getReceiver());
//        addr.setPhoneNumber(item.getPhoneNumber());
//        addr.setUserId(Constant.TEST_USER);
//        addr.setArea(item.getArea());
//        OkHttpUtils.postString().url(Constant.ADDRESS_UPDATE).content(new Gson().toJson(addr)).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                hideDialog();
//                Log.d("TAG", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                FindPostBean mdata = FindPostBean.objectFromData(response);
//                if (mdata.getCode().equals("200")) {
//                    EventBus.getDefault().postSticky(new EventBusMsg.AddrAddMsg(addr));
//                    //从栈中将当前fragment推出
//                    getFragmentManager().popBackStack();
//                } else {
//                    activity.showToast("提交失败");
//                }
//                hideDialog();
//            }
//
//        });
//    }

//    void postAddrData() {
//        OkHttpUtils.postString().url(Constant.ADDRESS_ADD).content(new Gson().toJson(item)).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.d("TAG", e.getMessage());
//                hideDialog();
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Log.d("TAG", response);
//                FindPostBean data = FindPostBean.objectFromData(response);
//                if (data.getCode().equals("200")) {
//                    AddrListBean.DataBean addr = new AddrListBean.DataBean();
//                    addr.setShippingAddressId(data.getData());
//                    addr.setAddress(item.getAddress());
//                    addr.setStatus(item.getStatus());
//                    addr.setReceiver(item.getReceiver());
//                    addr.setPhoneNumber(item.getPhoneNumber());
//                    addr.setUserId(Constant.TEST_USER);
//                    addr.setArea(item.getArea());
//                    EventBus.getDefault().postSticky(new EventBusMsg.AddrAddMsg(addr));
//                    //从栈中将当前fragment推出
//                    getFragmentManager().popBackStack();
//                } else {
//                    activity.showToast("提交失败");
//                }
//                hideDialog();
//            }
//        });
//
//    }

    AddrNewBean item = new AddrNewBean();

    private AddrNewBean makeData() {
        if (mAddrNewName.getText().toString().trim().length() > 0) {
            item.setReceiver(mAddrNewName.getText().toString().trim());
        } else {
            item.setReceiver(null);
        }
        if (mAddrNewPhonenum.getText().toString().length() > 0) {
            item.setPhoneNumber(mAddrNewPhonenum.getText().toString());
        } else {
            item.setPhoneNumber(null);
        }
        if (mAddrNewDetailAddrTv.getText().toString().trim().length() > 0) {
            item.setAddress(item.getArea() + " " + mAddrNewDetailAddrTv.getText().toString().trim());
        } else {
            item.setAddress(null);
        }
        return item;
    }

    private void goback() {
        if (data != null) {
            //从栈中将当前fragment推出
            getFragmentManager().popBackStack();
        } else {
            if (mAddrNewName.getText().length() > 0) {
                str = str + mAddrNewName.getText();
            } else if (mAddrNewPhonenum.getText().length() > 0) {
                str = str + mAddrNewPhonenum.getText();
            } else if (mAddrNewDetailAddrTv.getText().length() > 0) {
                str = str + mAddrNewDetailAddrTv.getText();
            }
            if (str != null && str.length() > 0) {
                // showBackDialog();
                getCheckDialog("您还没有保存，确定退出？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //从栈中将当前fragment推出
                        getFragmentManager().popBackStack();
                    }
                }).show();
            } else {
                //从栈中将当前fragment推出
                getFragmentManager().popBackStack();
            }

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getFocus();
    }


    //主界面获取焦点
    private void getFocus() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // 监听到返回按钮点击事件
                    goback();
                    return true;
                }
                return false;
            }
        });
    }


}
