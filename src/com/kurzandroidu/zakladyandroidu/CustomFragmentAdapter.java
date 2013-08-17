package com.kurzandroidu.zakladyandroidu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

public class CustomFragmentAdapter extends FragmentPagerAdapter {

    public CustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fr = new FragmentTab();
        Bundle args = new Bundle(1);

        if (position == 0)
            args.putString("tag", "Tab1");
        else if (position == 1)
            args.putString("tag", "Tab2");
        else
            args.putString("tag", "Tab3");

        fr.setArguments(args);
        return fr;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
