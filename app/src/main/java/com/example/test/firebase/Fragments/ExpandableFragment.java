package com.example.test.firebase.Fragments;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.test.firebase.Adapter.ExapandableAdapter;
import com.example.test.firebase.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpandableFragment extends Fragment {

    View view;
    ExpandableListView expandableListView;
    ExapandableAdapter adapter;
    ArrayList<String> group_list = new ArrayList<String>();
    HashMap<String, ArrayList<String>> map_child = new HashMap<String, ArrayList<String>>();
    String[][] Fruits = {{"Fruit_1", "Fruit_2"},{"Biscuit_1", "Biscuit_2"},
            {"Bread_1", "Bread_2"}};
    String[] group = {"Fruits", "Biscuit", "Bread"};



    public static ExpandableFragment newInstance()
    {
        ExpandableFragment fragment = new ExpandableFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.expandable_fragment, container, false);

        for (int i=0; i<group.length; i++)
        {
            ArrayList<String> list = new ArrayList<String>();
            group_list.add(group[i]);
            for (int j=0; j< 2; j++)
            {
                String s = Fruits[i][j];
                list.add(s);
                map_child.put(group[i], list);
            }
        }

        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        adapter = new ExapandableAdapter(group_list, map_child);
        expandableListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
