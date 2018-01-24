package com.ihjklj.ik.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;
import com.ihjklj.ik.keepAlive.KeepAliveCls;
import com.ihjklj.ik.R;
import com.ihjklj.ik.application.MyApplication;
import com.ihjklj.ik.cppnative.MyNative;
import com.ihjklj.ik.myinterface.NativeInterface;

public class MainActivity extends AppCompatActivity {

    private MyNative mNative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        init();

        run();

        keepAlive();
    }

    private void init() {
        Log.d(MyApplication.TAG, "init");
        mNative = MyNative.getInstance();
    }

    private void run () {
        Log.d(MyApplication.TAG, "run");
        mNative.setInterface(new NativeInterface() {
            @Override
            public void showMsg(final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mNative.start();
    }

    private void keepAlive () {
        new KeepAliveCls(this).run();
    }
}
