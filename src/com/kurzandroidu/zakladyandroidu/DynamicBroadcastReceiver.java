package com.kurzandroidu.zakladyandroidu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DynamicBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context ctx, Intent i) {

        Toast.makeText(ctx, i.getAction() + ": " + i.getStringExtra("msg"),
                Toast.LENGTH_LONG).show();
    }
}
