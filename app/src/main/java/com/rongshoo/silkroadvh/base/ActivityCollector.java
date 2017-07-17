package com.rongshoo.silkroadvh.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RS-KXH on 2016/12/28.
 * Activity管理器
 */

public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 获取栈顶Activity
     * @return
     */
    public  static  Activity  getTopActivity(){
        if (activityList.isEmpty()){
            return  null;
        }else {
            return activityList.get(activityList.size()-1);
        }
    }
}
