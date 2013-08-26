package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityRemoteService extends Activity {

    private IRemoteService          mRemoteService;
    private RemoteServiceConnection mConnection = new RemoteServiceConnection();

    class RemoteServiceConnection implements ServiceConnection {
        public void onServiceConnected(ComponentName className, IBinder binder) {
            mRemoteService = IRemoteService.Stub.asInterface((IBinder) binder);

            Toast.makeText(ActivityRemoteService.this, "Služba svázána!",
                    Toast.LENGTH_SHORT).show();

            try {
                ArrayAdapter<String> adapter = null;
                adapter = new ArrayAdapter<String>(ActivityRemoteService.this,
                        android.R.layout.simple_list_item_1,
                        mRemoteService.getViewTypes());
                ((ListView) findViewById(R.id.listView)).setAdapter(adapter);
            }
            catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            mRemoteService = null;
        }
    };

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_listview);
        Utils.getOverflowMenu(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu_remote_service, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        bindService(new Intent(this, RemoteService.class), mConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mConnection);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.actionStartRemoteService:

                Intent istart = new Intent();
                istart.setClassName("com.kurzandroidu.zakladyandroidu",
                        "com.kurzandroidu.zakladyandroidu.RemoteService");
                startService(istart);
                return true;
            case R.id.actionStopRemoteService:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
