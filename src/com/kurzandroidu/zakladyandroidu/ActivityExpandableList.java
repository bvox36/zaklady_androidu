package com.kurzandroidu.zakladyandroidu;

import java.util.ArrayList;

import android.app.ExpandableListActivity;
import android.os.Bundle;

public class ActivityExpandableList extends ExpandableListActivity {

    private CustomExpandableAdapter mExpandableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_listview);
        mExpandableAdapter = new CustomExpandableAdapter(this, getViewTypes());
        setListAdapter(mExpandableAdapter);
    }

    private static ArrayList<ViewType> getViewTypes() {
        ArrayList<ViewType> viewTypes = new ArrayList<ViewType>();
        viewTypes.add(new ViewType("TextView", "Zobrazen� textu", false, R.drawable.ic_launcher));
        viewTypes.add(new ViewType("EditText", "Zad�v�n� textu", false, R.drawable.ic_launcher));
        viewTypes.add(new ViewType("RadioButton", "P�ep�n�n� mezi n�kolika mo�nostmi", false,
                R.drawable.ic_launcher));
        viewTypes.add(new ViewType("WebView", "Zobrazen� obsahu webov� str�nky", true,
                R.drawable.ic_launcher));
        viewTypes
                .add(new ViewType("MapView", "Zobrazen� Google Maps", true, R.drawable.ic_launcher));
        return viewTypes;
    }
}
