package com.kurzandroidu.zakladyandroidu;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentList extends ListFragment {

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] values = new String[] { "TextView", "Button", "CheckBox",
                "RadioButton", "CheckedTextView", "Spinner", "ProgressBar",
                "RatingBar", "SeekBar", "Switch", "EditText", "ImageView",
                "ImageButton", "WebView", "MapView", "VideoView", "TimePicker", };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.default_listview, container,
                false);

        return layout;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }
}
