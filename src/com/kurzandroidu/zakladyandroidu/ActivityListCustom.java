package com.kurzandroidu.zakladyandroidu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityListCustom extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        String[] values = new String[] { "TextView", "Button", "CheckBox", "RadioButton",
                "CheckedTextView", "Spinner", "ProgressBar", "RatingBar", "SeekBar", "Switch",
                "EditText", "ImageView", "ImageButton", "WebView", "MapView", "VideoView",
                "TimePicker", };

        ListView lv = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        lv.setAdapter(adapter);
    }
}
