package com.kurzandroidu.zakladyandroidu;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ActivityActionBarTabsPager extends Activity {

    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar_pager);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new CustomFragmentAdapter(this
                .getFragmentManager()));

        mViewPager.setPageTransformer(true, new DepthPageTransformer());

        Utils.getOverflowMenu(this);
        getActionBar().setTitle(R.string.action_bar_title);

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabReselected(Tab tab, FragmentTransaction ft) {
            }

            @Override
            public void onTabSelected(Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            }
        };

        for (int i = 0; i < 3; i++) {
            getActionBar().addTab(
                    getActionBar().newTab().setText("Tab " + (i + 1))
                            .setTabListener(tabListener));
        }

        mViewPager
                .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
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
            case R.id.actionHelp:
                startActivity(new Intent(this, ActivityAboutCompatible.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
