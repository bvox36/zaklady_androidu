package com.kurzandroidu.zakladyandroidu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivitySimpleListView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        final ListView listview = (ListView) findViewById(R.id.listView);
        String[] values = new String[] { "TextView", "Button", "CheckBox",
                "RadioButton", "CheckedTextView", "Spinner", "ProgressBar",
                "RatingBar", "SeekBar", "Switch", "EditText", "ImageView",
                "ImageButton", "WebView", "MapView", "VideoView", "TimePicker", };

        final List<String> list = new ArrayList<String>(values.length);
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                list.remove(item);
                adapter.notifyDataSetChanged();
                view.setAlpha(1);
            }
        });
    }
}
