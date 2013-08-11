package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ActivityProgressDialog extends Activity implements DialogInterface.OnClickListener {

    private ProgressDialog mProgressDialog;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.buttonAlert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog = new ProgressDialog(ActivityProgressDialog.this);
                mProgressDialog.setTitle(R.string.dlg_title_processing);
                mProgressDialog.setMessage(getString(R.string.dlg_msg_processing));
                mProgressDialog.setCancelable(false);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.dlg_btn_cancel), ActivityProgressDialog.this);
                mProgressDialog.show();
            }
        });
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        if (which == DialogInterface.BUTTON_NEGATIVE)
            mProgressDialog.cancel();
    }
}
