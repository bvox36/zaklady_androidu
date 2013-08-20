package com.kurzandroidu.zakladyandroidu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class ActivityTabHost extends FragmentActivity {

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("Tab1").setIndicator("Záložka 1"),
                FragmentTabSupport.class, createTagArgument("Tab1"));

        mTabHost.addTab(mTabHost.newTabSpec("Tab2").setIndicator("Záložka 2"),
                FragmentTabSupport.class, createTagArgument("Tab2"));

        mTabHost.addTab(mTabHost.newTabSpec("Tab3").setIndicator("Záložka 3"),
                FragmentTabSupport.class, createTagArgument("Tab3"));

        mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {

                Toast toast = Toast.makeText(ActivityTabHost.this,
                        "Vybrána záložka" + ": " + tabId, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    private Bundle createTagArgument(String tag) {
        Bundle bundle = new Bundle(1);
        bundle.putString("tag", tag);
        return bundle;
    }
}
