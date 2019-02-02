package com.example.eventscheduler.utils;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.eventscheduler.R;
import com.example.eventscheduler.activities.MainActivity;

public class EventNotificationService extends IntentService {

    public static final int NOTIFICATION_ID=1;

    public EventNotificationService(){
        super("EventNotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        sendNotification("Event Notification");
    }

    private void sendNotification(String msg) {

        NotificationManager eventNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        //get pending intent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        //Create notification
        NotificationCompat.Builder eventNotificationBuilder = new NotificationCompat.Builder(this,"Alarm");

        eventNotificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setTicker("Hearty365")
                .setContentTitle("Alarm")
                .setContentText("Event Started")
                .setContentInfo("Check Event Scheduler");

        if (eventNotificationManager != null) {
            eventNotificationManager.notify(NOTIFICATION_ID, eventNotificationBuilder.build());
        }


    }


}
