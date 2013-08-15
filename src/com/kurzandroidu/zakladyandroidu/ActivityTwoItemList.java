package com.kurzandroidu.zakladyandroidu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class ActivityTwoItemList extends ListActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ArrayList<Map<String, String>> list = createList();
        String[] from = { "name", "description" };
        int[] to = { android.R.id.text1, android.R.id.text2 };

        SimpleAdapter adapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_2, from, to);
        setListAdapter(adapter);
    }

    private static ArrayList<Map<String, String>> createList() {
        ArrayList<Map<String, String>> retList = new ArrayList<Map<String, String>>();
        retList.add(insertItem("TextView", "Zobrazen� textu"));
        retList.add(insertItem("EditText", "Zad�v�n� textu"));
        retList.add(insertItem("RadioButton", "P�ep�n�n� mezi n�kolika mo�nostmi"));
        retList.add(insertItem("ImageView", "Zobrazen� bitmapy"));
        retList.add(insertItem("WebView", "Zobrazen� obsahu webov� str�nky"));
        retList.add(insertItem("MapView", "Zobrazen� Google Maps"));
        return retList;
    }

    private static HashMap<String, String> insertItem(String name, String description) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("name", name);
        item.put("description", description);
        return item;
    }
}
