package com.kurzandroidu.zakladyandroidu;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class CustomService extends Service {

    private final IBinder mBinder = new CustomBinder();
    private String[]      mViewTypes;

    public class CustomBinder extends Binder {
        CustomService getService() {
            return CustomService.this;
        }
    }

    @Override
    public void onCreate() {
        mViewTypes = getResources().getStringArray(R.array.view_types);
    }

    @Override
    public int onStartCommand(Intent i, int flags, int id) {
        Toast.makeText(getApplicationContext(), "Služba spuštìna!",
                Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent i) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "Služba ukonèena!",
                Toast.LENGTH_LONG).show();
    }

    public String[] getViewTypes() {
        return mViewTypes;
    }
}
