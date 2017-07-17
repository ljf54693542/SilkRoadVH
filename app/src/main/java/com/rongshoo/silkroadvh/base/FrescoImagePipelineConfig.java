package com.rongshoo.silkroadvh.base;

import android.content.Context;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * Created by RS-KXH on 2016/12/28.
 */

public class FrescoImagePipelineConfig {

    private static ImagePipelineConfig sImagePipelineConfig;
//    private static ImagePipelineConfig sOkHttpImagePipelineConfig;

    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_DISK_CACHE_SIZE = 200 * ByteConstants.MB;
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 3;

    /**
     * Creates config using android http stack as network backend.
     */
    public static ImagePipelineConfig getConfig(Context context, OkHttpClient okHttpClient) {
        if (sImagePipelineConfig == null) {
            ImagePipelineConfig.Builder configBuilder = OkHttpImagePipelineConfigFactory.newBuilder(context, okHttpClient);
            configureCaches(configBuilder, context);
            sImagePipelineConfig = configBuilder.build();
        }
        return sImagePipelineConfig;
    }

    /**
     * Configures disk and memory cache not to exceed common limits
     */
    private static void configureCaches(ImagePipelineConfig.Builder configBuilder, Context context) {
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
                Integer.MAX_VALUE,                     // Max entries in the cache
                MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
                Integer.MAX_VALUE,                     // Max length of eviction queue
                Integer.MAX_VALUE);                    // Max cache entry size
        configBuilder
                .setBitmapMemoryCacheParamsSupplier(
                        new Supplier<MemoryCacheParams>() {
                            public MemoryCacheParams get() {
                                return bitmapCacheParams;
                            }
                        })
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(context)
                        .setBaseDirectoryPath(createFile(AppFilePath.APP_SD_CARD_File_PATH))
                        .setBaseDirectoryName("ImageCache")
                        .setMaxCacheSize(MAX_DISK_CACHE_SIZE)
                        .build());
    }

    public static File createFile(String folderPath) {
        File destDir = new File(folderPath);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        return destDir;
    }

}
