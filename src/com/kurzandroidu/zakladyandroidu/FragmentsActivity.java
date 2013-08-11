package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class FragmentsActivity extends Activity implements OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout_static);
        findViewById(R.id.buttonPridat).setOnClickListener(this);
        findViewById(R.id.buttonOdebrat).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPridat:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                FragmentManager fm = getFragmentManager();
                ft.addToBackStack("PocitaciFragment");
                fm.executePendingTransactions();
                int n = getFragmentManager().getBackStackEntryCount() + 1;
                PocitaciFragment newFragment = PocitaciFragment.newInstance(n);
                ft.add(R.id.fragment, newFragment, "PocitaciFragment").commit();
                break;

            case R.id.buttonOdebrat:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
