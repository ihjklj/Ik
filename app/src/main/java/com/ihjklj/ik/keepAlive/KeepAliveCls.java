package com.ihjklj.ik.keepAlive;

import android.content.Context;
import android.content.Intent;

import com.ihjklj.ik.service.JobShedulerService;
import com.ihjklj.ik.util.UtilTool;

/**
 * Created by ihjklj on 2018/1/25.
 */

public class KeepAliveCls {

    private Context mContext;

    public KeepAliveCls(Context context) {
        this.mContext = context;
    }

    public boolean run() {
        if (!UtilTool.isServiceRunning(mContext, "com.ihjklj.ik.service.JobShedulerService")) {
            Intent startServiceIntent = new Intent(mContext, JobShedulerService.class);
            mContext.startService(startServiceIntent);
        }
        return true;
    }
}
