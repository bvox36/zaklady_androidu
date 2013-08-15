package com.kurzandroidu.zakladyandroidu;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityCustomAdapter extends ListActivity {

    private CustomAdapter mAdapter;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        mAdapter = new CustomAdapter(this, getViewTypes());
        setListAdapter(mAdapter);
    }

    private static ArrayList<ViewType> getViewTypes() {
        ArrayList<ViewType> viewTypes = new ArrayList<ViewType>();
        viewTypes.add(new ViewType("TextView", "Zobrazení textu", false, R.drawable.ic_launcher));
        viewTypes.add(new ViewType("EditText", "Zadávání textu", false, R.drawable.ic_launcher));
        viewTypes.add(new ViewType("RadioButton", "Pøepínání mezi nìkolika možnostmi", false, R.drawable.ic_launcher));
        viewTypes.add(new ViewType("WebView", "Zobrazení obsahu webové stránky", true, R.drawable.ic_launcher));
        viewTypes.add(new ViewType("MapView", "Zobrazení Google Maps", true, R.drawable.ic_launcher));
        return viewTypes;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ViewType viewType = mAdapter.getItem(position);
        Toast toast = Toast.makeText(this, viewType.getName() + ": " + viewType.getDescription(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
