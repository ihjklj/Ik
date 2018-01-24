package com.ihjklj.ik.cppnative;

import android.widget.Toast;

import com.ihjklj.ik.myinterface.NativeInterface;

/**
 * Created by ihjklj on 2017/12/29.
 */

public class MyNative {

    private static MyNative mInstance;
    private NativeInterface mInterface;

    public static MyNative getInstance() {
        if (mInstance == null) {
            synchronized (MyNative.class) {
                if (mInstance == null) {
                    mInstance = new MyNative();
                }
            }
        }
        return mInstance;
    }

    public native void start();
    public native int copyLogFile(String file);

    public void setInterface(NativeInterface pInterface) {
        mInterface = pInterface;
    }
    
    public void showMsg(String data) {
        mInterface.showMsg(data);
    }

    static {
        System.loadLibrary("ikUtil");
    }
}
