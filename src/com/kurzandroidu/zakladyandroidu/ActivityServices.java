package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityServices extends Activity {

    private DynamicBroadcastReceiver mDynamicBroadcastReceiver;
    private IntentFilter             mIntentFilter;

    private CustomService            mCustomService;
    private CustomConnection         mConnection = new CustomConnection();

    private class CustomConnection implements ServiceConnection {

        public void onServiceConnected(ComponentName className, IBinder binder) {
            mCustomService = ((CustomService.CustomBinder) binder).getService();
            Toast.makeText(ActivityServices.this, "Služba svázána!",
                    Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    ActivityServices.this, android.R.layout.simple_list_item_1,
                    mCustomService.getViewTypes());

            ((ListView) findViewById(R.id.listView)).setAdapter(adapter);
        }

        public void onServiceDisconnected(ComponentName className) {
            mCustomService = null;
        }
    };

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_listview);
        Utils.getOverflowMenu(this);

        mDynamicBroadcastReceiver = new DynamicBroadcastReceiver();
        mIntentFilter = new IntentFilter(Intents.INTENT_ACTION_SEND_MSG1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mDynamicBroadcastReceiver, mIntentFilter);

        bindService(new Intent(this, CustomService.class), mConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mDynamicBroadcastReceiver);
        unbindService(mConnection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu_services, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.actionStartService:

                Intent istart = new Intent(this, CustomService.class);
                startService(istart);

                return true;
            case R.id.actionStopService:

                Intent istop = new Intent(this, CustomService.class);
                stopService(istop);

                return true;
            case R.id.actionStartForeground:
                Intent istartf = new Intent(this, ForegroundService.class);
                startService(istartf);
                return true;
            case R.id.actionStopForeground:
                Intent istopf = new Intent(this, ForegroundService.class);
                stopService(istopf);
                return true;

            case R.id.actionStartIntentService:
                Intent istartis = new Intent(this, CustomIntentService.class);
                startService(istartis);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
