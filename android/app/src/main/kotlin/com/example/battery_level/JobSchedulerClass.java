package com.example.battery_level;
import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@SuppressLint("NewApi")
public class JobSchedulerClass extends JobService {

    private Context mContext;
    private static final int ASJOBSERVICE_JOB_ID = 999;

    // A pre-built JobInfo we use for scheduling our job.
    private static JobInfo JOB_INFO = null;

    public static int a(Context context) {
        int schedule = ((JobScheduler) context.getSystemService(JobScheduler.class)).schedule(JOB_INFO);
        Log.i("PhotosContentJob", "JOB SCHEDULED!");
        return schedule;
    }

    // Schedule this job, replace any existing one.
    public static void scheduleJob(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler == null) {
            return;
        }

        ComponentName serviceComponent = new ComponentName(context, JobSchedulerClass.class);
        JobInfo.Builder builder = new JobInfo.Builder(ASJOBSERVICE_JOB_ID, serviceComponent);
        builder.addTriggerContentUri(new JobInfo.TriggerContentUri(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, JobInfo.TriggerContentUri.FLAG_NOTIFY_FOR_DESCENDANTS));
        builder.addTriggerContentUri(new JobInfo.TriggerContentUri(MediaStore.Images.Media.INTERNAL_CONTENT_URI, JobInfo.TriggerContentUri.FLAG_NOTIFY_FOR_DESCENDANTS));
        builder.setTriggerContentMaxDelay(500);

        JobInfo jobInfo = builder.build();
        jobScheduler.schedule(jobInfo);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onStartJob(final JobParameters params) {
        mContext = this;
        // Did we trigger due to a content change?
        if (params.getTriggeredContentAuthorities() != null) {
            if (params.getTriggeredContentUris() != null) {
                // If we have details about which URIs changed, then iterate through them
                // and collect either the ids that were impacted or note that a generic
                // change has happened.
                ArrayList<String> ids = new ArrayList<>();
                for (Uri uri : params.getTriggeredContentUris()) {
                    if (uri != null) {
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Perform your task here
                                Log.i("Media Created", "Media Captured!");
                                    ShowNotification.showNotification(mContext,"Sunil sir",uri.toString());
                            }
                        });
                    }
                }
                jobFinished(params, true); // see this, we are saying we just finished the job
                // We will emulate taking some time to do this work, so we can see batching happen.
                scheduleJob(this);
            }
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


}