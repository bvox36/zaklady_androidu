package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class FragmentsActivity extends Activity implements OnClickListener {

    private int mStackLevel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout_dynamic);
        PocitaciFragment newFragment = PocitaciFragment.newInstance(mStackLevel);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragmentContainer, newFragment, "PocitaciFragment").commit();
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
                PocitaciFragment newFragment = PocitaciFragment.newInstance(getFragmentManager().getBackStackEntryCount() + 1);
                ft.add(R.id.fragmentContainer, newFragment, "PocitaciFragment").commit();
                break;

            case R.id.buttonOdebrat:
                getFragmentManager().popBackStack();
                break;
        }

    }
}
