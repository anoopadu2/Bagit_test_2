package com.example.bagit_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends Custom_Activity {
    private EditText emailEt, passwordEt;
    private Button forgot, sign_in, reg_user, reg_whole, reg_retail;
    private String email, password;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    FireBase fb = new FireBase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Please wait");
        mProgressDialog.setCanceledOnTouchOutside(false);
        emailEt = findViewById(R.id.EmailLoginEditText);
        passwordEt = findViewById(R.id.PasswordLoginEditText);
        sign_in = findViewById(R.id.LoginButton);
        forgot = findViewById(R.id.forgotButton);
        reg_user = findViewById(R.id.RegisterButton);
        reg_whole = findViewById(R.id.RegisterWholeseller);
        reg_retail = findViewById(R.id.RegisterRetailer);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        reg_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        reg_whole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SellerSignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        reg_retail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RetailerSignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void loginUser() {
        email = emailEt.getText().toString().trim();
        password = passwordEt.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter Pasword...", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(LoginActivity.this, "Verifying credentials..", Toast.LENGTH_SHORT).show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LoginActivity.this,"Successful",Toast.LENGTH_SHORT).show();

/*
                       ProgressDialog progressBar = new ProgressDialog(LoginActivity.this);
                        progressBar.setCancelable(true);//you can cancel it by pressing back button
                        progressBar.setMessage("Fetching data...");
                        progressBar.setProgress(0);//initially progress is 0

                        progressBar.show();
                        fb.Get_User_PhNumber(mAuth.getUid(),LoginActivity.this,progressBar);
*/
                         Intent I = new Intent(LoginActivity.this,UserRoutingActivity.class);
                        I.putExtra("Current_User_ID", mAuth.getUid() );
                        //I.putExtra("Current_User_Type", Current_User_Type);
                        startActivity(I);
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mProgressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    public void run2(ProgressDialog progressBar,String phno) {
        String phoneNumber=phno;
        Intent otpIntent = new Intent(LoginActivity.this, OtpActivity.class);
        otpIntent.putExtra("Current_User_ID", mAuth.getUid() );
        otpIntent.putExtra("PhoneNumber", phoneNumber);
        Toast.makeText(LoginActivity.this,"Successful",Toast.LENGTH_SHORT).show();
        startActivity(otpIntent);
        finish();

    }




        private void getData() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child("Customers").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Log.d(TAG, "MyClass");
                User c =  dataSnapshot.getValue(User.class);
                //c.cart.Items_In_Cart = (Vector<Cart_Item>) c.cart.Items_In_Cart;
                assert c != null;
                String phoneNumber=c.Phone_number;
                Intent otpIntent = new Intent(LoginActivity.this, MainActivity2.class);
                otpIntent.putExtra("PhoneNumber", phoneNumber);
                Toast.makeText(LoginActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                startActivity(otpIntent);
                finish();
                // do your stuff here with value

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this,"Failure",Toast.LENGTH_SHORT).show();

            }
        });
    }
}

