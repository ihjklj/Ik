package com.ihjklj.ik.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.ihjklj.ik.application.MyApplication;

/**
 * Created by ihjklj on 2018/1/19.
 */

public class JobShedulerService extends JobService {

    private boolean mIsRun = false;

    @Override
    public void onCreate() {
        super.onCreate();
        startJobSheduler();
        Log.d(MyApplication.TAG, "JobShedulerService onCreate");
        Toast.makeText(getApplicationContext(), "service create", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(MyApplication.TAG, "onStartCommand");
        Toast.makeText(getApplicationContext(), "service start", Toast.LENGTH_LONG).show();
        return START_NOT_STICKY;
    }

    public void startJobSheduler() {
        try {
            JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(getPackageName(), JobShedulerService.class.getName()));
            builder.setPersisted(true);
            builder.setRequiresCharging(false);
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
            builder.setRequiresDeviceIdle(false);
            builder.setPeriodic(5000); //单位毫秒,国内系统这个修改比较多，太短的时间没作用，系统一般会弄成15分钟左右一次
            JobScheduler jobScheduler = (JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);
            int runRet = jobScheduler.schedule(builder.build());
            Log.d(MyApplication.TAG, "run jobSchedule result:" + runRet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(MyApplication.TAG, "onStartJob");
        boolean isTaskExit = false;
        if (!mIsRun) {
            isTaskExit = true;
            final JobParameters jobPara = jobParameters;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runTask(jobPara);
                }
            }).start();
        }
        return isTaskExit;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(MyApplication.TAG, "onStopJob");
        return false;
    }

    @Override
    public void onDestroy() {
        Log.d(MyApplication.TAG, "JobShedulerService onDestroy");
        super.onDestroy();
    }

    private void runTask(JobParameters jobParameters) {
        Log.d(MyApplication.TAG, "runTask");
        //Toast.makeText(getApplicationContext(), "JobShedule is running!", Toast.LENGTH_LONG).show();
        jobFinished(jobParameters, false);
        mIsRun = false;
    }
}
