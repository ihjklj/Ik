package com.ihjklj.ik.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import java.util.List;

/**
 * Created by ihjklj on 2018/1/25.
 */

public class UtilTool {

    public static boolean isServiceRunning(Context context, String className) {
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = manager.getRunningServices(Integer.MAX_VALUE);
        if (serviceList.size() <= 0) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            ActivityManager.RunningServiceInfo serviceInfo = serviceList.get(i);
            ComponentName serviceName = serviceInfo.service;
            if (serviceName.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }
}
