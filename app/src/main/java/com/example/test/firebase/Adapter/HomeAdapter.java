package com.example.test.firebase.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.firebase.Interface.OnClickListener;
import com.example.test.firebase.Objects.HomeObject;
import com.example.test.firebase.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    ArrayList<HomeObject> list = new ArrayList<HomeObject>();
    OnClickListener listener;

    public HomeAdapter(ArrayList<HomeObject> list, OnClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_child, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder v, int i) {

        HomeObject object =  list.get(i);
        v.menu_item_image.setImageResource(object.getMenu_item_image());;
        v.menu_name.setText(object.getMenu_name());
        v.favourite_checkBox.setChecked(object.getChecked());

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView menu_item_image;
        TextView menu_name;
        CheckBox favourite_checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            menu_item_image = (ImageView) itemView.findViewById(R.id.menu_item_image);
            menu_name = (TextView) itemView.findViewById(R.id.menu_name);
            favourite_checkBox  =(CheckBox) itemView.findViewById(R.id.favourite_checkBox);

        }
    }



}
