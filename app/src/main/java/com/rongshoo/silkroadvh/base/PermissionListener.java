package com.rongshoo.silkroadvh.base;

import java.util.List;

/**
 * 权限申请接口
 * Created by RS-KXH on 2016/12/28.
 */

public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermission);
}
