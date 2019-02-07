package com.example.test.firebase.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.firebase.Interface.OnClickListener;
import com.example.test.firebase.Objects.DrawableObject;
import com.example.test.firebase.R;

import java.util.ArrayList;

public class DrawableAdapter extends RecyclerView.Adapter<DrawableAdapter.ViewHolder> {

    ArrayList<DrawableObject> list = new ArrayList<DrawableObject>();
    OnClickListener onClickListener;

    public DrawableAdapter(ArrayList<DrawableObject> list, OnClickListener onClickListener) {
        this.list = list;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawable_child, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder v, final int i) {

        DrawableObject object = list.get(i);
        v.title.setText(object.getTitle());
        v.imageView.setImageResource(object.getImage());

        v.parent_layout.setBackgroundColor(object.isSelected() ? Color.GRAY : Color.WHITE);

        v.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickListener.onClick(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;
        LinearLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.title);
            parent_layout = (LinearLayout) itemView.findViewById(R.id.parent_layout);

            }
    }

    public void  setSelected(int position)
    {
        for (int i=0; i< list.size(); i++)
        {
            list.get(i).setSelected(i == position);
        }
        notifyDataSetChanged();

    }


}
