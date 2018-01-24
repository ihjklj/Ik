package com.ihjklj.ik.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ihjklj.ik.activity.MainActivity;

/**
 * Created by ihjklj on 2018/1/18.
 */

public class BootReceiver extends BroadcastReceiver {

    private static final String action_boot = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(action_boot)) {
            Toast.makeText(context, "开机启动", Toast.LENGTH_SHORT).show();
            Intent startActivityIntent = new Intent(context, MainActivity.class);
            startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(startActivityIntent);
        }
    }
}
