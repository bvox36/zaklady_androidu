package com.kurzandroidu.zakladyandroidu;

import java.lang.reflect.Field;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ActivityActionBarDropDown extends Activity implements
        OnNavigationListener {

    private ArrayAdapter<CharSequence> mAdapter;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setTheme(android.R.style.Theme_Holo_Light);
        setContentView(R.layout.activity_actionbar);
        getOverflowMenu();
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        mAdapter = ArrayAdapter.createFromResource(this, R.array.mesta_array,
                android.R.layout.simple_spinner_item);

        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        getActionBar().setListNavigationCallbacks(mAdapter, this);
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
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        Toast toast = Toast.makeText(this,
                "Vybráno mìsto" + ": " + mAdapter.getItem(itemPosition),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionRotate:
                return true;
            case R.id.actionCall:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
