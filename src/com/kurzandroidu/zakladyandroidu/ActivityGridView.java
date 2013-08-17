package com.kurzandroidu.zakladyandroidu;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class ActivityGridView extends Activity {
    private GridView mGridView;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_gridview);
        mGridView = (GridView) findViewById(R.id.gridView);
        CustomAdapter mAdapter = new CustomAdapter(this, getViewTypes());
        mGridView.setAdapter(mAdapter);
    }

    private static ArrayList<ViewType> getViewTypes() {
        ArrayList<ViewType> viewTypes = new ArrayList<ViewType>();
        viewTypes.add(new ViewType("TextView", "Zobrazen� textu", false, R.drawable.ic_launcher));
        viewTypes.add(new ViewType("EditText", "Zad�v�n� textu", false, R.drawable.ic_launcher));
        viewTypes.add(new ViewType("RadioButton", "P�ep�n�n� mezi mo�nostmi", false,
                R.drawable.ic_launcher));
        viewTypes.add(new ViewType("WebView", "Zobrazen� webov� str�nky", true,
                R.drawable.ic_launcher));
        viewTypes
                .add(new ViewType("MapView", "Zobrazen� Google Maps", true, R.drawable.ic_launcher));
        return viewTypes;
    }
}
