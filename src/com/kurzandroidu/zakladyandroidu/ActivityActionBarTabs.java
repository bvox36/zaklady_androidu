package com.kurzandroidu.zakladyandroidu;

import java.lang.reflect.Field;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;

public class ActivityActionBarTabs extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);
        getOverflowMenu();
        getActionBar().setTitle(R.string.action_bar_title);

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab tab1 = getActionBar()
                .newTab()
                .setText(String.valueOf(1))
                .setTabListener(
                        new TabListener<FragmentTab>(this, "Tab1",
                                FragmentTab.class));

        Tab tab2 = getActionBar()
                .newTab()
                .setText(String.valueOf(2))
                .setTabListener(
                        new TabListener<FragmentTab>(this, "Tab2",
                                FragmentTab.class));

        Tab tab3 = getActionBar()
                .newTab()
                .setText(String.valueOf(3))
                .setTabListener(
                        new TabListener<FragmentTab>(this, "Tab3",
                                FragmentTab.class));

        getActionBar().addTab(tab1);
        getActionBar().addTab(tab2);
        getActionBar().addTab(tab3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    private void getOverflowMenu() {

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionHelp:
                startActivity(new Intent(this, ActivityAboutCompatible.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
