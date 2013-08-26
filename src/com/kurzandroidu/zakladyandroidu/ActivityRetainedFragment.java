package com.kurzandroidu.zakladyandroidu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ProgressBar;

public class ActivityRetainedFragment extends FragmentActivity implements
        TaskCallbacks {

    private ProgressBar  mProgressBarHoriz;
    private FragmentTask mTaskFragment;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_progress);
        mProgressBarHoriz = (ProgressBar) findViewById(R.id.progressBarHoriz);

        FragmentManager fm = getSupportFragmentManager();
        mTaskFragment = (FragmentTask) fm.findFragmentByTag("task");

        if (mTaskFragment == null) {
            mTaskFragment = new FragmentTask();
            fm.beginTransaction().add(mTaskFragment, "task").commit();
        }
        else
            mProgressBarHoriz.setVisibility(View.VISIBLE);
    }

    public void onPreExecute() {
        mProgressBarHoriz.setVisibility(View.VISIBLE);
    }

    public void onProgressUpdate(int percent) {
        mProgressBarHoriz.setProgress(percent);

        if (percent >= 100)
            mProgressBarHoriz.setVisibility(View.GONE);
    }

    public void onCancelled() {
        mProgressBarHoriz.setVisibility(View.GONE);
    }

    public void onPostExecute() {
        mProgressBarHoriz.setVisibility(View.GONE);
    }
}
