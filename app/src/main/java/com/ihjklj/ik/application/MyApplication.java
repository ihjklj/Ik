package com.ihjklj.ik.application;

import android.app.Application;
import android.util.Log;

/**
 * Created by Administrator on 2017/12/4.
 */

public class MyApplication extends Application {

    public static final String TAG = "ikUtil";

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        Log.d(TAG, "application init.");
        //
    }
}
