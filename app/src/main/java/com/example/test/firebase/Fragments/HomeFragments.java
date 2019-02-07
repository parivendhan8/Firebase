package com.example.test.firebase.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.firebase.Adapter.HomeAdapter;
import com.example.test.firebase.Interface.OnClickListener;
import com.example.test.firebase.Objects.HomeObject;
import com.example.test.firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragments extends Fragment implements OnClickListener {


    RecyclerView home_recyclerView;
    View view;
    HomeAdapter adapter;
    ArrayList<HomeObject> list = new ArrayList<HomeObject>();
    OnClickListener listener;
    FirebaseAuth auth;
    DatabaseReference databaseRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view  = inflater.inflate(R.layout.fragment_home, container, false);
        auth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-64548.firebaseio.com/");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        home_recyclerView = (RecyclerView) view.findViewById(R.id.home_recyclerView);
        adapter = new HomeAdapter(list, this);

    }

    @Override
    public void onClick(int position) {

    }
}
