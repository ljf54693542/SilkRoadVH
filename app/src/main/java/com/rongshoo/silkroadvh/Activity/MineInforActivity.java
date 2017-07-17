package com.rongshoo.silkroadvh.Activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;
import com.rongshoo.silkroadvh.View.ActionSheetDialog;
import com.rongshoo.silkroadvh.base.AppFilePath;
import com.rongshoo.silkroadvh.base.Constant;
import com.rongshoo.silkroadvh.base.MPermission;
import com.rongshoo.silkroadvh.base.PermissionListener;
import com.rongshoo.silkroadvh.base.ShareUtils;
import com.rongshoo.silkroadvh.base.UserInfor;
import com.rongshoo.silkroadvh.bean.BavatarBean;
import com.rongshoo.silkroadvh.bean.EventBusMsg;
import com.rongshoo.silkroadvh.utils.CameraUtils;
import com.rongshoo.silkroadvh.utils.EncodeUtils;
import com.rongshoo.silkroadvh.utils.ImageUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class MineInforActivity extends BaseActivity {

    @BindView(R.id.go_back)
    ImageView mGoBack;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.mine_head_sdv)
    SimpleDraweeView mMineHeadSdv;
    @BindView(R.id.personal_headicon)
    LinearLayout mPersonalHeadicon;
    @BindView(R.id.phone_tv)
    TextView mPhoneTv;
    @BindView(R.id.personal_phone)
    LinearLayout mPersonalPhone;
    @BindView(R.id.nick_tv)
    TextView mNickTv;
    @BindView(R.id.personal_nick)
    LinearLayout mPersonalNick;
    private Boolean hasCamera;
    private final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private final int PHOTO_REQUEST_CUT = 3;// 结果
    private Uri userIconUri;// 用户头像
    private int iconHeight = 300;



    public static void startMineInforActivity(Context context) {
        Intent intent = new Intent(context, MineInforActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_infor);
        ButterKnife.bind(this);
        init();
    }

    void init(){
        mTitleTv.setText("个人资料");
        if (Build.VERSION.SDK_INT >= 23) {
            hasCamera = false;
            requestRuntimePermission(new String[]{MPermission.CAMERA, MPermission.WRITE_EXTERNAL_STORAGE},
                    new PermissionListener() {

                        @Override
                        public void onGranted() {
                            // 选择图片或拍照
                            hasCamera = true;
                        }

                        @Override
                        public void onDenied(List<String> deniedPermission) {
                            showMyDialog("摄像头或存储卡访问");
                        }
                    });

        } else {
            hasCamera = true;
        }
    }

    /**
     * 权限被拒后提示
     *
     * @param msg
     */
    private void showMyDialog(String msg) {
        getAlertDialog(this,
                msg + "权限被禁，拍照功能无法正常使用！！！\n请前往设置允许权限后重新开启。",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                }).show();
    }

    /**
     * 调用相机或相册的dialog
     */
    private void uploadPhoto() {

        // 去调用相机或相册的意图
        ActionSheetDialog dialog = new ActionSheetDialog(this).builder();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        try {
                            File imgFile = new File(AppFilePath.CAMERA_PATH, "temp1.jpg");
                            if (CameraUtils.callCamearIntent(imgFile) == null) return;
                            startActivityForResult(CameraUtils.callCamearIntent(imgFile),
                                    PHOTO_REQUEST_TAKEPHOTO);
                        } catch (Exception e) {
                            showToast(getResources().getString(
                                    R.string.takephoto_erro));
                        }

                    }
                });
        dialog.addSheetItem("本地上传", ActionSheetDialog.SheetItemColor.Blue,
                new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        try {
                            startActivityForResult(CameraUtils.callLocalImgIntent(), PHOTO_REQUEST_GALLERY);
                        } catch (Exception e) {
                            showToast(getResources().getString(
                                    R.string.takephoto_erro));
                        }

                    }
                });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_OK) {
            switch (requestCode) {
                case PHOTO_REQUEST_TAKEPHOTO:
                    userIconUri = Uri.fromFile(new File(AppFilePath.CAMERA_PATH + "userLog.jpg"));//頭像文件
                    Uri uri2 = Uri.fromFile(new File(AppFilePath.CAMERA_PATH + "temp1.jpg"));//源文件
                    if (uri2 != null) {
                        startActivityForResult(CameraUtils.cropIntent(uri2, userIconUri, iconHeight, iconHeight, new File(AppFilePath.CAMERA_PATH + "temp1.jpg")), PHOTO_REQUEST_CUT);
                    }
                    break;
                case PHOTO_REQUEST_GALLERY:
                    userIconUri = Uri.fromFile(new File(AppFilePath.CAMERA_PATH + "userLog.jpg"));
                    Uri uri = data.getData();
                    File srcFile = null;
                    if (uri != null) {
                        if (Build.VERSION.SDK_INT >= 24) {
                            String[] proj = {MediaStore.Images.Media.DATA};
                            // 好像是android多媒体数据库的封装接口，具体的看Android文档
                            Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
                            if (cursor == null) return;
                            // 按我个人理解 这个是获得用户选择的图片的索引值
                            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                            cursor.moveToFirst();
                            // 最后根据索引值获取图片路径
                            String imgPath = cursor.getString(column_index);
                            srcFile = new File(imgPath);
                        }
                        startActivityForResult(CameraUtils.cropIntent(uri, userIconUri, iconHeight, iconHeight, srcFile), PHOTO_REQUEST_CUT);
                    }
                    break;
                case PHOTO_REQUEST_CUT:
                    try {
                        ContentResolver contentResolver = this.getContentResolver();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                contentResolver, userIconUri);
                        int a = bitmap.getByteCount();
                        //旧方法
                        // ImageHelper.saveBitmap(bitmap, AppFilePath.CAMERA_PATH, "testid" + ".jpg");
                        ImageUtils.writeImage(bitmap, "userLog.jpg");
                        //File iconFile = new File(AppFilePath.CAMERA_PATH + "userLog.jpg");
                        String token = ShareUtils.getUser(MineInforActivity.this).getToken();
                        String b = "token=" + token + "&imgext=jpg&base64img=" + ImageUtils.encode(AppFilePath.CAMERA_PATH + "userLog.jpg");
                        //知识补充  Content-type： application/x-www-form-urlencoded  表单数据   application/x-javascript text/xml->xml数据  application/x-javascript->json对象   application/json;charset=utf-8 -> json数据
                        showLoadingDialog("上传中...", false);
                        OkHttpUtils.postString()
                                .url(Constant.BIG_UPDATEAVATAR)
                                .content(b)
                                .mediaType(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"))
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                hideDialog();
                                Log.d("Error", e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                hideDialog();
                                Log.d("Response", response);
                                BavatarBean avatar = BavatarBean.objectFromData(response);
                                if (avatar.getCode().equals("000000")) {
                                    UserInfor user = ShareUtils.getUser(MineInforActivity.this);
                                    if (user == null) return;
                                    String icon = EncodeUtils.urlDecode(avatar.getResult());
                                    user.setAvatar(icon);
                                    ShareUtils.saveUser(MineInforActivity.this, user);
                                    mMineHeadSdv.setImageURI(Uri.parse(icon));
                                    File file = new File(AppFilePath.CAMERA_PATH + "temp1.jpg");// 删除设置头像源文件
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                    EventBus.getDefault().postSticky(new EventBusMsg.UserAvatarMsg());
                                    // EventBus.getDefault().postSticky(new EventBusMsg.UserAvatarMsg());
                                } else {
                                    showToast("图片上传失败");
                                }

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        }
    }

    @OnClick(value = {R.id.go_back,R.id.mine_head_sdv})
    void onClick(View view){
        switch (view.getId()){
            case R.id.go_back:
                this.finish();
                break;
            case R.id.mine_head_sdv:
                if (hasCamera) {
                    uploadPhoto();
                } else {
                    showMyDialog("摄像头或存储卡访问");
                }
                break;
        }
    }
}
