package com.example.test.firebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.test.firebase.Objects.UserObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserRegisteration extends AppCompatActivity {

    EditText nick_name, address, company, dob, user_name;
    Button register;
    DatabaseReference databaseRef;
    String phone_no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_registeration);

        databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-64548.firebaseio.com");


        user_name = (EditText) findViewById(R.id.user_name);
        nick_name = (EditText) findViewById(R.id.nick_name);
        address = (EditText) findViewById(R.id.address);
        company = (EditText) findViewById(R.id.company);
        dob = (EditText) findViewById(R.id.dob);

        phone_no = getIntent().getStringExtra("mobile");

        register = (Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


    }

    private void register() {

        final UserObject object = new UserObject();
        object.setUser_name(user_name.getText().toString());
        object.setAddress(address.getText().toString());
        object.setNick_name(nick_name.getText().toString());
        object.setCompany(company.getText().toString());
        object.setDob(dob.getText().toString());
        if (databaseRef.child("users").push().getKey() != null)
        object.setUid(databaseRef.child("users").push().getKey());

        databaseRef.child("users").child(phone_no).setValue(object).addOnCompleteListener(UserRegisteration.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                 if (task.isSuccessful())
                     Log.d("TASK", "success");
            }
        });


        final DataSnapshot[] dataSnapshot1 = new DataSnapshot[1];

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               dataSnapshot1[0] = dataSnapshot;
                    databaseRef.child("users").child(dataSnapshot.getKey()).setValue(object);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

