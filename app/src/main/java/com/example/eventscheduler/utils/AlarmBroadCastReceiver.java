package com.example.eventscheduler.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.eventscheduler.R;

import static androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService;

public class AlarmBroadCastReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmBroadCastReceiver";

    private MediaPlayer mediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive: Alarm received");

        mediaPlayer = MediaPlayer.create(context, R.raw.downtempobeatloop);
        mediaPlayer.start();

//        context.startService(new Intent(context, AlarmSoundService.class));
//
//        ComponentName componentName = new ComponentName(context.getPackageName(),
//                EventNotificationService.class.getName());
//        startWakefulService(context, (intent.setComponent(componentName)));

    }
}
