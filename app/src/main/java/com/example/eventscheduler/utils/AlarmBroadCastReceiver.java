package com.example.eventscheduler.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.eventscheduler.R;

public class AlarmBroadCastReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmBroadCastReceiver";

    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive: Alarm received");

        mp = MediaPlayer.create(context, R.raw.downtempobeatloop);
        mp.start();

    }
}
