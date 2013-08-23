package com.kurzandroidu.zakladyandroidu;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class KurzApplication extends Application {
    private static KurzApplication sInstance;
    private final List<String>     mYourList = new ArrayList<String>();

    public static synchronized KurzApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public List<String> getYourList() {
        return mYourList;
    }
}
