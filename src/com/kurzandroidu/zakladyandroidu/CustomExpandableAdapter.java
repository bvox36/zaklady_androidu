package com.kurzandroidu.zakladyandroidu;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomExpandableAdapter extends BaseExpandableListAdapter {

    private final Context        mContext;
    private final List<ViewType> mBasicViews    = new ArrayList<ViewType>();
    private final List<ViewType> mAdvancedViews = new ArrayList<ViewType>();

    static class GroupViewHolder {
        public TextView textViewCaption;
    }

    static class ChildViewHolder {
        public TextView  textViewName;
        public TextView  textViewDescription;
        public ImageView imageViewIcon;
    }

    public CustomExpandableAdapter(Context context, List<ViewType> data) {
        mContext = context;
        updateData(data, false);
    }

    public void updateData(List<ViewType> data, boolean notify) {

        mBasicViews.clear();
        mAdvancedViews.clear();

        for (ViewType viewType : data) {
            if (viewType.isAdvanced())
                mAdvancedViews.add(viewType);
            else
                mBasicViews.add(viewType);
        }

        if (notify)
            notifyDataSetChanged();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (groupPosition == 0)
            return mBasicViews.get(childPosition);
        else
            return mAdvancedViews.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {

        View childView = convertView;

        if (childView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            childView = inflater.inflate(R.layout.view_exp_list_child, null);
            ChildViewHolder childViewHolder = new ChildViewHolder();
            childViewHolder.textViewName = (TextView) childView.findViewById(R.id.textViewName);
            childViewHolder.textViewDescription = (TextView) childView
                    .findViewById(R.id.textViewDescription);
            childViewHolder.imageViewIcon = (ImageView) childView.findViewById(R.id.imageViewIcon);
            childView.setTag(childViewHolder);
        }

        ChildViewHolder childViewHolder = (ChildViewHolder) childView.getTag();
        ViewType viewType = (ViewType) getChild(groupPosition, childPosition);

        childViewHolder.textViewName.setText(viewType.getName());
        childViewHolder.textViewDescription.setText(viewType.getDescription());
        childViewHolder.imageViewIcon.setImageResource(viewType.getIconId());

        return childView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0)
            return mBasicViews.size();
        else
            return mAdvancedViews.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (groupPosition == 0)
            return mBasicViews;
        else
            return mAdvancedViews;
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {

        View groupView = convertView;

        if (groupView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            groupView = inflater.inflate(R.layout.view_exp_list_group, null);
            GroupViewHolder groupViewHolder = new GroupViewHolder();
            groupViewHolder.textViewCaption = (TextView) groupView
                    .findViewById(R.id.textViewCaption);
            groupView.setTag(groupViewHolder);
        }

        GroupViewHolder groupViewHolder = (GroupViewHolder) groupView.getTag();

        if (groupPosition == 0)
            groupViewHolder.textViewCaption.setText(R.string.basic_view);
        else
            groupViewHolder.textViewCaption.setText(R.string.advanced_view);

        return groupView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
