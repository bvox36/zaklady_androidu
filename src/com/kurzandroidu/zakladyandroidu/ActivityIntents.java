package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kurzandroidu.zakladyandroidu.Intents.ShowMsg1Intent;
import com.kurzandroidu.zakladyandroidu.Intents.ShowMsg2Intent;

public class ActivityIntents extends Activity implements OnClickListener {

    private Button                   mButtonSend;
    private EditText                 mEditTextMsgToSend;
    private TextView                 mTextViewReceived;

    private DynamicBroadcastReceiver mDynamicBroadcastReceiver;
    private IntentFilter             mIntentFilter;

    private final static int         REQUEST_CODE = 0x2;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_intents);
        mButtonSend = (Button) findViewById(R.id.buttonSend);
        mButtonSend.setOnClickListener(this);
        mEditTextMsgToSend = (EditText) findViewById(R.id.editTextMsgToSend);
        mTextViewReceived = (TextView) findViewById(R.id.textViewReceived);
        Utils.getOverflowMenu(this);

        if (getIntent().hasExtra("msg")) {
            mTextViewReceived.setVisibility(View.VISIBLE);
            mButtonSend.setVisibility(View.VISIBLE);
            mEditTextMsgToSend.setVisibility(View.VISIBLE);
            mEditTextMsgToSend
                    .setHint(R.string.label_set_msg_for_parent_activity);
            mTextViewReceived.setText(getIntent().getStringExtra("msg"));
            mButtonSend.setText(R.string.label_return_to_activity);
        }
        if (getIntent().hasExtra("viewtype")) {
            ParcelableViewType pv = getIntent().getParcelableExtra("viewtype");
        }

        mDynamicBroadcastReceiver = new DynamicBroadcastReceiver();
        mIntentFilter = new IntentFilter(Intents.INTENT_ACTION_SEND_MSG2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mDynamicBroadcastReceiver, mIntentFilter);

        //    LocalBroadcastManager.getInstance(getApplicationContext())
        //             .registerReceiver(mDynamicBroadcastReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mDynamicBroadcastReceiver);

        //LocalBroadcastManager.getInstance(getApplicationContext())
        //        .unregisterReceiver(mDynamicBroadcastReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("msg")) {
                mTextViewReceived.setVisibility(View.VISIBLE);
                mTextViewReceived.setText(getIntent().getStringExtra("msg"));
            }
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("msg", "Potomek taky posila zpravu!");
        setResult(RESULT_OK, data);
        super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu_intents, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionStartListActivity:

                Intent ia = new Intent(this, ActivityList.class);
                String[] strings = new String[10];

                for (int i = 0; i < 10; i++)
                    strings[i] = "String cislo: " + (i + 1);

                ia.putExtra("strings", strings);
                startActivity(ia);

                return true;
            case R.id.actionStartService:
                Intent is = new Intent(this, CustomService.class);
                startService(is);
                return true;

            case R.id.actionStartBrowser:
                Intent ib = new Intent(Intent.ACTION_VIEW);
                ib.setData(Uri.parse("http://www.google.com"));
                startActivity(ib);
                return true;
            case R.id.actionStartForResult:
                mButtonSend.setVisibility(View.VISIBLE);
                mButtonSend.setText(R.string.label_start_activity);
                mButtonSend.setTag(R.id.actionStartForResult);
                mEditTextMsgToSend.setVisibility(View.VISIBLE);
                return true;
            case R.id.actionIntentShare:
                mButtonSend.setVisibility(View.VISIBLE);
                mButtonSend.setText(R.string.label_share);
                mButtonSend.setTag(R.id.actionIntentShare);
                mEditTextMsgToSend.setVisibility(View.VISIBLE);
                return true;
            case R.id.actionStartCamera:
                Intent ic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(ic);
                return true;
            case R.id.actionSendIntentStatic:

                try {
                    //Intent iMsg1 = new ShowMsg1Intent(this,
                    //CustomBroadcastReceiver.class,
                    //"Zprava pro staticky registrovany receiver!");
                    // sendBroadcast(iMsg1);

                    Intent iMsg1 = new ShowMsg1Intent(
                            "Zprava pro staticky registrovany receiver!");

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(
                            this.getApplicationContext(),
                            PendingIntent.FLAG_CANCEL_CURRENT, iMsg1, 0);

                    pendingIntent.send();

                }
                catch (CanceledException e) {
                    e.printStackTrace();
                }

                return true;

            case R.id.actionSendIntentDynamic:
                Intent iMsg2 = new ShowMsg2Intent(
                        "Zprava pro dynamicky registrovany receiver!");

                sendBroadcast(iMsg2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSend:

                if (((Integer) v.getTag()).intValue() == R.id.actionIntentShare) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(android.content.Intent.EXTRA_TEXT,
                            mEditTextMsgToSend.getText().toString());
                    startActivity(intent);
                }
                else if (((Integer) v.getTag()).intValue() == R.id.actionStartForResult) {
                    Intent ir = new Intent(this, ActivityIntents.class);
                    ir.putExtra("msg", "Rodicovska aktivita posila zpravu!");
                    ParcelableViewType pv = new ParcelableViewType("TextView",
                            "Zobrazeni textu", false, R.drawable.ic_launcher);
                    ir.putExtra("viewtype", pv);
                    startActivityForResult(ir, REQUEST_CODE);
                }
                break;
        }
    }
}
