package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class ActivityNotifications extends Activity {

    private final static int NORMAL_NOTIFICATION_ID = 1;
    private final static int BIG_NOTIFICATION_ID    = 2;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        RelativeLayout layout = new RelativeLayout(this);
        setContentView(layout);
        Utils.getOverflowMenu(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu_notifications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.actionShowNormalNotification:
                //sendNormalNotification();
                sendNormalNotificationNoNavigation();
                return true;
            case R.id.actionShowBigNotification:
                sendBigNotification();
                return true;
            case R.id.actionShowNotificationProgress:
                sendProgressNotification();
                return true;
            case R.id.actionShowNotificationActivity:
                sendProgressActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendNormalNotification() {

        String[] viewTypes = getResources().getStringArray(R.array.view_types);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        Intent resultIntent = new Intent(this, ActivityListCustom.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ActivityListCustom.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher).setContentTitle("Seznam views")
                .setContentText("Vyberte pro zobrazení seznamu typù views").setLargeIcon(largeIcon)
                .setContentIntent(resultPendingIntent)
                .setContentInfo(String.valueOf(viewTypes.length));

        builder.setDefaults(Notification.DEFAULT_ALL);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(NORMAL_NOTIFICATION_ID, notification);
    }

    private void sendNormalNotificationNoNavigation() {

        String[] viewTypes = getResources().getStringArray(R.array.view_types);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        Intent resultIntent = new Intent(this, ActivityListCustom.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher).setContentTitle("Seznam views")
                .setContentText("Vyberte pro zobrazení seznamu typù views").setLargeIcon(largeIcon)
                .setContentIntent(resultPendingIntent)
                .setContentInfo(String.valueOf(viewTypes.length));

        builder.setDefaults(Notification.DEFAULT_ALL);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(NORMAL_NOTIFICATION_ID, notification);

    }

    private void sendBigNotification() {

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        String[] viewTypes = getResources().getStringArray(R.array.view_types);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher).setContentTitle("Seznam views")
                .setContentText("Vyberte pro zobrazení seznamu typù views");

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.setBigContentTitle("Typy views:");

        for (int i = 0; i < 4; i++) {
            inboxStyle.addLine(viewTypes[i]);
        }

        inboxStyle.setSummaryText("Poèet views");
        builder.setStyle(inboxStyle);
        builder.setContentInfo(String.valueOf(4));
        builder.setLargeIcon(largeIcon);

        Intent showListItent = new Intent(this, ActivityListCustom.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, showListItent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent homeItent = new Intent(this, ActivityNotifications.class);
        PendingIntent pIntenth = PendingIntent.getActivity(this, 0, homeItent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.addAction(R.drawable.ic_menu_forward, "Zobraz seznam", pIntent);
        builder.addAction(R.drawable.ic_menu_home, "Domù", pIntenth);
        builder.setPriority(Notification.PRIORITY_MAX);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(BIG_NOTIFICATION_ID, notification);
    }

    private void sendProgressNotification() {

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher).setContentTitle("Stahování souboru")
                .setContentText("Stahuji soubor kurz Androidu...");

        final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int incr = 0; incr <= 50; incr += 5) {
                    builder.setProgress(50, incr, false);
                    notificationManager.notify(0, builder.build());

                    try {
                        Thread.sleep(5 * 1000);
                    }
                    catch (InterruptedException e) {
                    }
                }
                builder.setContentText("Stahování dokonèeno")

                .setProgress(0, 0, false);
                notificationManager.notify(NORMAL_NOTIFICATION_ID, builder.build());
            }
        }).start();
    }

    private void sendProgressActivity() {

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher).setContentTitle("Stahování souboru")
                .setContentText("Stahuji soubor kurz Androidu...");

        final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int incr = 0; incr <= 50; incr += 5) {
                    builder.setProgress(50, incr, true);
                    notificationManager.notify(0, builder.build());

                    try {
                        Thread.sleep(5 * 1000);
                    }
                    catch (InterruptedException e) {
                    }
                }
                builder.setContentText("Stahování dokonèeno")

                .setProgress(0, 0, true);
                notificationManager.notify(NORMAL_NOTIFICATION_ID, builder.build());
            }
        }).start();
    }

}
