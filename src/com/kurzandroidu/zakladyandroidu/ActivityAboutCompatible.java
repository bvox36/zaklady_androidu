package com.kurzandroidu.zakladyandroidu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;

public class ActivityAboutCompatible extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public Intent getSupportParentActivityIntent() {
        return new Intent(this, ActivityActionBarTabsPager.class);
    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder builder) {

        Intent upIntent = getSupportParentActivityIntent();
        builder.addParentStack(this).addNextIntentWithParentStack(upIntent);
    }
}
