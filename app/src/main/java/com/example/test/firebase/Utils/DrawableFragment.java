package com.example.test.firebase.Utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.test.firebase.Adapter.DrawableAdapter;
import com.example.test.firebase.Objects.DrawableObject;
import com.example.test.firebase.R;

import java.util.ArrayList;

public class DrawableFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<DrawableObject> list = new ArrayList<DrawableObject>();
    DrawableAdapter adapter;
    String[] title = {"Orders", "Carts", "Profile", "Setting"};
    int[] image = {R.drawable.user
            , R.drawable.user
            , R.drawable.user
            , R.drawable.user};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static DrawableFragment newInstance() {
        DrawableFragment drawableFragment = new DrawableFragment();
        Bundle args = new Bundle();
        drawableFragment.setArguments(args);
        return drawableFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.drawable_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < title.length; i++) {
            DrawableObject object = new DrawableObject();
            object.setImage(image[i]);
            object.setTitle(title[i]);
            list.add(object);
        }

        adapter = new DrawableAdapter(list, null);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }
}
