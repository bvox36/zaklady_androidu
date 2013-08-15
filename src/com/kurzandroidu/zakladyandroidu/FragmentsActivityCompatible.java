package com.kurzandroidu.zakladyandroidu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

public class FragmentsActivityCompatible extends FragmentActivity implements
        OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout_dynamic);
        PocitaciFragmentCompatible newFragment = PocitaciFragmentCompatible
                .newInstance(0);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmentContainer, newFragment, "PocitaciFragment")
                .commit();
        findViewById(R.id.buttonPridat).setOnClickListener(this);
        findViewById(R.id.buttonOdebrat).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPridat:
                FragmentTransaction ft = getSupportFragmentManager()
                        .beginTransaction();
                FragmentManager fm = getSupportFragmentManager();
                ft.addToBackStack("PocitaciFragment");
                fm.executePendingTransactions();
                int n = getFragmentManager().getBackStackEntryCount() + 1;
                PocitaciFragmentCompatible newFragment = PocitaciFragmentCompatible
                        .newInstance(n);
                ft.add(R.id.fragmentContainer, newFragment, "PocitaciFragment")
                        .commit();
                break;

            case R.id.buttonOdebrat:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
