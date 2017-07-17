package com.rongshoo.silkroadvh.base;

import android.os.Environment;

import com.rongshoo.silkroadvh.MyApplication;

import java.io.File;

/**
 * Created by RS-KXH on 2016/12/28.
 */

public class AppFilePath {

    /**
     * 应用保存本地数据路径
     */
    public static String APP_ROOT_PATH_NAME = "/SilkRoadVH/";

    /**
     * 主文件夹
     */
    public static String APP_SD_CARD_File_PATH = getRootDirectory(APP_ROOT_PATH_NAME);

    /**
     * 图片缓存路径
     */
    public static String IMAGE_CACHE_PATH = getDirectory( "ImageCache/");
    /**
     * 下载路径(如果下载的文件需要由其他调用，比如安装程序，择不能使用私有文件Androd/DATA/)
     */
    public static String DOWNLOAD_PATH = getDirectory("Download");

    /**
     * 照相机照片地址
     */
    public static String CAMERA_PATH = getDirectory("Camera/");

    /**
     * 日志路径
     */
    public static String LOGCAT_PATH = getDirectory("Logcat/");

    /**
     * 获取sd card目录
     *
     * @param relativePath sd卡相对路径，如“/”表示根目录
     * @return
     */
    public static String getRootDirectory(String relativePath) {
        String dir = getSDPath() + relativePath;
        File destDir = new File(dir);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        return dir;
    }
    /**
     * 获取其他目录
     *
     * @param relativePath sd卡相对路径，如“/”表示根目录
     * @return
     */
    public static String getDirectory(String relativePath) {
        String dir =APP_SD_CARD_File_PATH  + relativePath;
        File destDir = new File(dir);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        return dir;
    }


    //SD卡东西不会随着程序卸载删除，且存放的东西可供其他程序访问
    /**
     * 取SD卡路径
     *
     * @return
     */
    private static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否挂载
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
        }
        if (sdDir != null) {
            return sdDir.toString();
        } else {
            return "";
        }
    }

    /**
     * 获取文件私有路径(内容仅自己可访问)//数据会随着应用卸载而消失
     * @return
     */
    private static String getDataPath(){
        File file= MyApplication.getContext().getFilesDir();//file.getAbsolutePath() 绝对路径
        String relativePath=file.getPath();//相对路径
        return  relativePath;
    }
}
