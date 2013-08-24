package com.kurzandroidu.zakladyandroidu;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class CustomIntentService extends IntentService {

    public CustomIntentService() {
        super("CustomIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "IntentService spuštìna!",
                Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        long endTime = System.currentTimeMillis() + 5 * 1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                }
                catch (Exception e) {
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "IntentService ukonèena!",
                Toast.LENGTH_LONG).show();
    }
}
