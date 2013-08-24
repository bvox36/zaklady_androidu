package com.kurzandroidu.zakladyandroidu;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class ForegroundService extends Service {

    private final static int NOTIFICATION_ID = 0xA;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        NotificationCompat.Builder serviceNotificationBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Vyberte pro zobrazení aplikace");

        Intent notificationIntent = new Intent(this, ActivityServices.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        serviceNotificationBuilder.setContentIntent(pendingIntent);
        Notification notification = serviceNotificationBuilder.build();
        notification.flags |= Notification.FLAG_NO_CLEAR;

        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }
}
