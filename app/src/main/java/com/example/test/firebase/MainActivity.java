package com.example.test.firebase;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.firebase.Utils.MaterialProgressBar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText phone_no;
    Button sign_in;
    View view;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        mAuth = FirebaseAuth.getInstance();
        phone_no = (EditText) findViewById(R.id.phone_no);
        sign_in = (Button) findViewById(R.id.sign_in);
        view = LayoutInflater.from(MainActivity.this).inflate(R.layout.progress_bar, null);

        progressBar = (ProgressBar)findViewById(R.id.progress_bar_id);



        phone_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        phone_no.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    Log.d("IME ACTION", "DONE");
                    progressBar.setVisibility(View.INVISIBLE);
                }

                return false;

            }
        });



        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phone_no.getText().toString().trim().isEmpty())
                    return;

                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                intent.putExtra("mobile", phone_no.getText().toString().trim());
                startActivity(intent);

            }
        });

    }



}
