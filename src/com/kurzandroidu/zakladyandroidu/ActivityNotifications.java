package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class ActivityNotifications extends Activity {
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
                sendNotification();
                return true;
            case R.id.actionShowBigNotification:
                return true;
            case R.id.actionShowNotificationProgress:
                return true;
            case R.id.actionShowNotificationActivity:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendNotification() {
        //        Intent intent = new Intent(this, ActivityIntents.class);
        //        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //
        //        Notification notification = new NotificationCompat.Builder(this)
        //                .setContentTitle("Nová objednávka").setContentText(
        //                        "Vyberte pro vyøízení objednávky").s
        //                .setSmallIcon(R.drawable.ic_launcher).setContentIntent(pIntent)
        //                .build();
        //
        //        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //
        //        notification.flags |= Notification.f;
        //
        //        notificationManager.notify(0, notification);

    }
}
