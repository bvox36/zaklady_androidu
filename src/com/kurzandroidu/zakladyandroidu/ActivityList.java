package com.kurzandroidu.zakladyandroidu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityList extends ListActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.default_listview);

        String[] values = new String[] { "TextView", "Button", "CheckBox",
                "RadioButton", "CheckedTextView", "Spinner", "ProgressBar",
                "RatingBar", "SeekBar", "Switch", "EditText", "ImageView",
                "ImageButton", "WebView", "MapView", "VideoView", "TimePicker", };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

    }
}
