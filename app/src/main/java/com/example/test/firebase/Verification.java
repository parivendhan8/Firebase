package com.example.test.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.test.firebase.Objects.UserObject;
import com.example.test.firebase.Utils.MaterialProgressBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {


    FirebaseAuth mAuth;
    EditText code;
    String mVerificationId;
    Button verify;
    RelativeLayout layout;
    DatabaseReference databaseRef;
    String phone_no;
    Boolean isExist = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.verification);

        init();
    }

    private void init() {

        mAuth = FirebaseAuth.getInstance();
        code = (EditText) findViewById(R.id.sms_code);
        verify = (Button) findViewById(R.id.verify);
        layout = (RelativeLayout) findViewById(R.id.layout);

        MaterialProgressBar.show(Verification.this);

        databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-64548.firebaseio.com");

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verification(code.getText().toString().trim());

            }
        });

        phone_no = getIntent().getStringExtra("mobile");
        sendVerificationCode(phone_no.trim());

    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    if (phoneAuthCredential.getSmsCode() != null)
                    {
                        MaterialProgressBar.dismiss();
                        code.setText(phoneAuthCredential.getSmsCode());
                        verification(phoneAuthCredential.getSmsCode());
                    }

                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(Verification.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    mVerificationId = s;

                }

            };

     private void verification(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        if (credential == null)
            return;

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Verification.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                       String message = "Somthing is wrong, we will fix it soon..";

                        if (task.isSuccessful())
                        {
                            check_user();

                        }
                        else
                        {
                            message = "Invalid code entered...";
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                            {
                                Snackbar.make(layout, message, Snackbar.LENGTH_SHORT)
                                .setAction("Dismiss", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).show();

                            }
                        }

                    }
                });


    }

    private void check_user() {


        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<UserObject> list = new ArrayList<UserObject>();

//                Log.d("key", dataSnapshot.getKey());

                for (DataSnapshot dataSnapshot : snapshot.child("users").getChildren())
                {
                    UserObject object = dataSnapshot.getValue(UserObject.class);
                    if (dataSnapshot.getKey().contains(phone_no))
                    {
                        Log.d("---", "it contains");
                        isExist = true;
                    }
                    list.add(object);
                }

                if (!isExist)
                {
                    startActivity(new Intent(Verification.this, UserRegisteration.class).putExtra("mobile", phone_no.trim()));
                }

//                UserObject object = dataSnapshot.child(phone_no).getValue(UserObject.class);
//                if (dataSnapshot.child(phone_no).exists())
//                {
//                    Snackbar.make(layout, "Already Exist", Snackbar.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    startActivity(new Intent(Verification.this, UserRegisteration.class));
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void sendVerificationCode(String mobile) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + mobile, 60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, mCallback);
    }


}
