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
        retList.add(insertItem("TextView", "Zobrazení textu"));
        retList.add(insertItem("EditText", "Zadávání textu"));
        retList.add(insertItem("RadioButton", "Pøepínání mezi nìkolika možnostmi"));
        retList.add(insertItem("ImageView", "Zobrazení bitmapy"));
        retList.add(insertItem("WebView", "Zobrazení obsahu webové stránky"));
        retList.add(insertItem("MapView", "Zobrazení Google Maps"));
        return retList;
    }

    private static HashMap<String, String> insertItem(String name, String description) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("name", name);
        item.put("description", description);
        return item;
    }
}
