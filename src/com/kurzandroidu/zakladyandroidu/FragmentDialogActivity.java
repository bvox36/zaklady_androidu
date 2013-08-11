package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

public class FragmentDialogActivity extends Activity implements DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        FragmentAlertDialog dialogFragment = FragmentAlertDialog.newInstance(R.string.dlg_title, R.string.dlg_msg);
        getFragmentManager().beginTransaction().add(dialogFragment, "FragmentAlertDialog").commit();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
