package com.rongshoo.silkroadvh.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import com.rongshoo.silkroadvh.base.AppFilePath;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;


public class ImageUtils {


    /**
     * 通过给出的bitmap进行质量压缩
     *
     * @param bitmap
     * @return
     */

    public static Bitmap compressBitmap(Bitmap bitmap) {
        System.out.println("bitmap==" + bitmap.getByteCount());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //通过这里改变压缩类型，其有不同的结果  
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        System.out.println("bos=====" + bos.size());
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        System.out.println("bis=====" + bis.available());
        return BitmapFactory.decodeStream(bis);
    }

    /**
     * 通过给出的图片路径进行图片压缩
     *
     * @param pathName
     * @return
     */
    public static Bitmap compressBitmap(String pathName) {
        return compressBitmap(decodeSampledBitmapFromFile(pathName));
    }


    public static Bitmap decodeSampledBitmapFromFile(String pathName) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;          //仅对大小进行限制，将bitmap加载至内存
        BitmapFactory.decodeFile(pathName, options);
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;//色彩模式   ARGB_8888  RGB565  ARGB_4444
        options.inSampleSize = calculateInSampleSize(options, 0, 0);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return Bitmap.createBitmap(src);
    }


    /**
     * 把图片写入sd卡
     *
     * @param bitmap 图片的bitmap形式
     */
    public static void writeImage(Bitmap bitmap, String name) {
        String storageState = Environment.getExternalStorageState();
        System.out.println();
        if (!storageState.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        //Environment.getExternalStorageDirectory().getAbsolutePath() + "/rr"
        File file = new File(AppFilePath.CAMERA_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // bitmap.compress(Bitmap.CompressFormat.WEBP, 70, bos);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        // bitmap.compress(Bitmap.CompressFormat.PNG, 80, bos);
        FileOutputStream fos;
        try {
            File f = new File(file, name);
            fos = new FileOutputStream(f);
            fos.write(bos.toByteArray());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把图片写入sd卡
     *
     * @param bitmap 图片的bitmap形式
     */
    public static File writeImageNoCompress(Bitmap bitmap) {
        String storageState = Environment.getExternalStorageState();
        System.out.println();
        if (!storageState.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        //Environment.getExternalStorageDirectory().getAbsolutePath() + "/rr"
        File file = new File(AppFilePath.CAMERA_PATH + "/ImageCache");
        if (!file.exists()) {
            file.mkdirs();
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        // bitmap.compress(Bitmap.CompressFormat.WEBP, 70, bos);
        // bitmap.compress(Bitmap.CompressFormat.PNG, 80, bos);
        FileOutputStream fos;
        String fileName = System.currentTimeMillis() + ".jpg";
        try {
            File f = new File(file, fileName);
            fos = new FileOutputStream(f);
            fos.write(bos.toByteArray());
            fos.flush();
            return f;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 清除发现上传文件
     */
    public static void CleanImgCache() {
        File file = new File(AppFilePath.CAMERA_PATH + "/ImageCache");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.exists()) {//判断文件是否存在
            if (file.isFile()) {//判断是否是文件
                file.delete();//删除文件
            } else if (file.isDirectory()) {//否则如果它是一个目录
                File[] files = file.listFiles();//声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) {//遍历目录下所有的文件
                    files[i].delete();
                }
                //file.delete();//删除文件夹
            }
        } else {
            System.out.println("所删除的文件不存在");
        }

    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        int small = (width > height ? height : width);
        if (small > 740) {
            float scale = small / 740;
            inSampleSize = (int) scale;
        }

//        int inSampleSize = 1;
//        if (height > reqHeight || width > reqWidth) {
//            // 计算出实际宽高和目标宽高的比率
//            final int heightRatio = Math.round((float) height / (float) reqHeight);
//            final int widthRatio = Math.round((float) width / (float) reqWidth);
//            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
//            // 一定都会大于等于目标的宽和高。
//            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
//        }
        return inSampleSize;
    }

//    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
//        // 源图片的高度和宽度
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        int inSampleSize = 1;
//        if (height > reqHeight || width > reqWidth) {
//            // 计算出实际宽高和目标宽高的比率
//            final int halfHeight = height / 2;
//            final int halfWidth = width / 2;
//            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
//                inSampleSize *= 2;
//            }
//        }
//        return inSampleSize;
//    }

//    public static Bitmap decodeSampledBitmapFromFile(String pathName, int reqWidth, int reqHeight) {
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(pathName, options);
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//        options.inJustDecodeBounds = false;
//        Bitmap src = BitmapFactory.decodeFile(pathName, options);
//        return Bitmap.createScaledBitmap(src, reqWidth, reqHeight, false);
//    }

    /**
     * Base64 转码图片
     *
     * @param path
     * @return
     */
    public static String encode(String path) {
        //decode to bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        //convert to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        //base64 encode
        byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
        //EncodeUtils.urlEncode(new String(encode))
        String encodeString = EncodeUtils.urlEncode(new String(encode));
        //解码部分
//        byte[] decode = Base64.decode(encodeString,Base64.DEFAULT);
//        Base64.decode(mTvShow.getText().toString(),Base64.DEFAULT);
//        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        return encodeString;
    }


}