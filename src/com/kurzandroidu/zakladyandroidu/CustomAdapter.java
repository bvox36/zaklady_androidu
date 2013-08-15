package com.kurzandroidu.zakladyandroidu;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<ViewType> {

    public CustomAdapter(Context context, List<ViewType> data) {
        super(context, -1);

        if (data != null) {
            for (ViewType description : data)
                add(description);
        }
    }

    static class ViewHolder {
        public TextView  textViewName;
        public TextView  textViewDescription;
        public TextView  textViewLevel;
        public ImageView imageViewIcon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.view_rowlayout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textViewName = (TextView) rowView.findViewById(R.id.textViewName);
            viewHolder.textViewDescription = (TextView) rowView.findViewById(R.id.textViewDescription);
            viewHolder.textViewLevel = (TextView) rowView.findViewById(R.id.textViewLevel);
            viewHolder.imageViewIcon = (ImageView) rowView.findViewById(R.id.imageViewIcon);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        ViewType viewDescription = getItem(position);

        holder.textViewName.setText(viewDescription.getName());
        holder.textViewDescription.setText(viewDescription.getDescription());
        holder.imageViewIcon.setImageResource(viewDescription.getIconId());

        if (!viewDescription.isAdvanced()) {
            holder.textViewLevel.setText(R.string.basic_view);
            holder.textViewLevel.setTextColor(getContext().getResources().getColor(R.color.green));
        }
        else {
            holder.textViewLevel.setText(R.string.advanced_view);
            holder.textViewLevel.setTextColor(getContext().getResources().getColor(R.color.red));
        }
        return rowView;
    }
}
