package com.wl.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.wl.hnb.R;


import java.util.HashMap;
import java.util.List;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private HashMap<String, List<String>> mExpandableListData;
    public int abc=Color.RED;
    public MyExpandableListAdapter(Context context, HashMap<String, List<String>> expandableListData) {
        mContext = context;
        mExpandableListData = expandableListData;
    }

    @Override
    public int getGroupCount() {
        return mExpandableListData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = (String) getGroup(groupPosition);
        return mExpandableListData.get(key).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mExpandableListData.keySet().toArray()[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = (String) getGroup(groupPosition);
        return mExpandableListData.get(key).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.group_item, parent, false);
        }

        TextView groupName = convertView.findViewById(R.id.group_name);
        groupName.setText(getGroup(groupPosition).toString());
        groupName.setTextSize(20); // Set the text size to 20sp
        groupName.setTextColor(abc);
        groupName.setTypeface(null, Typeface.BOLD);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setText(getChild(groupPosition, childPosition).toString());
        textView.setTextSize(16); // Set the text size to 20sp
        textView.setTypeface(null, Typeface.BOLD);
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
