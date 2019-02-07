package com.example.test.firebase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.test.firebase.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ExapandableAdapter extends BaseExpandableListAdapter {

    ArrayList<String> group_list = new ArrayList<String>();
    HashMap<String, ArrayList<String>> map_child = new HashMap<String, ArrayList<String>>();
    GroupHolder groupHolder;
    Child_Holder child_holder;

    public ExapandableAdapter(ArrayList<String> group_list, HashMap<String, ArrayList<String>> map_child) {
        this.group_list = group_list;
        this.map_child = map_child;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        groupHolder =  new GroupHolder();

        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.elv_group, null);
            convertView.setTag(groupHolder);
            groupHolder.group_name = (TextView) convertView.findViewById(R.id.group_name);
        }
        else
        {
            groupHolder = (GroupHolder) convertView.getTag();
            groupHolder.group_name.setText(group_list.get(groupPosition));

        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        child_holder = new Child_Holder();
        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.elv_child, null);
            convertView.setTag(child_holder);
            child_holder.child_name = (TextView) convertView.findViewById(R.id.child_name);

        }
        else
        {
        child_holder =  (Child_Holder) convertView.getTag();
        child_holder.child_name.setText(map_child.get(group_list.get(groupPosition)).get(childPosition));

        }

        return convertView;
    }

    @Override
    public int getGroupCount() {
        return group_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map_child.get(group_list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return map_child.get(group_list.get(groupPosition)).get(childPosition);
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
        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public int getGroupType(int groupPosition) {
        return super.getGroupType(groupPosition);
    }

    class GroupHolder
    {
        TextView  group_name;
    }
    class Child_Holder
    {
        TextView child_name;
    }
}
