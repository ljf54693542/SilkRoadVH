package com.rongshoo.silkroadvh.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;

import com.rongshoo.silkroadvh.base.ActivityCollector;

import java.io.File;

/**
 * 相机调用 支持Android N=24
 * Created by Jane on 2017/3/31.
 */

public class CameraUtils {

    public static Intent callLocalImgIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        return intent;
    }


    /**
     * 调用相机拍照
     *
     * @return
     */
    public static Intent callCamearIntent(File imgFile) {
        Uri imgUri = null;
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= 24) {
            imgUri = FileProvider.getUriForFile(ActivityCollector.getTopActivity(), "com.rongshoo.silkroadvh.fileprovider", imgFile);
        } else {
            imgUri = Uri.fromFile(imgFile);
        }
        if (imgUri == null) return null;
        // 设置照片的默认路径
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        return cameraIntent;
    }

    /**
     * 调用裁剪
     *
     * @param fromUri
     * @param outUri
     * @param width
     * @param height
     * @param fromFile
     * @return
     */
    public static Intent cropIntent(Uri fromUri, Uri outUri, int width, int height, @Nullable File fromFile) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= 24) {
            //主要修改这行代码,不再使用Uri.fromFile()方法获取文件的Uri
            intent.setDataAndType(getImageContentUri(ActivityCollector.getTopActivity(), fromFile), "image/*");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            intent.setDataAndType(fromUri, "image/*");
        }
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);//生成对应文件
        return intent;
    }


    /**
     * 安卓7.0裁剪根据文件路径获取uri
     */
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

}
