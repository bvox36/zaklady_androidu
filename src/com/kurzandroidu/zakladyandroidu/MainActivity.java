package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @SuppressWarnings("unused")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);

        String text = String.format(getResources().getString(R.string.uvitani), "Jméno", 10);

        String[] mesta = getResources().getStringArray(R.array.mesta_array);

        String carsInGarage = getResources().getQuantityString(R.plurals.numberOfCars, 2, 2);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
