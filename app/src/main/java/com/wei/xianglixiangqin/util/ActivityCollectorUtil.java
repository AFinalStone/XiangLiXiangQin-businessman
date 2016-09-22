package com.wei.xianglixiangqin.util;


import com.wei.xianglixiangqin.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/***
 * activity管理工具类
 *
 * @author SHI
 *         2016-3-3 17:04:01
 */
public class ActivityCollectorUtil {

    public static List<BaseActivity> activities = new ArrayList<BaseActivity>();

    public static void addActivity(BaseActivity activity) {
        activities.add(activity);
    }

    public static void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (BaseActivity activity : activities) {
            if (!activity.isFinishing()) {
                activity.IfOpenFinishActivityAnim(false);
                activity.finish();
            }
        }
    }
}
