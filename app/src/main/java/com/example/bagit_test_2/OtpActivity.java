package com.example.bagit_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {
    private String verificationId;
    private EditText otpField;
    private Button verifyButton, send;
    private FirebaseAuth mAuth;
    public String Current_User_ID , Current_User_Type;


    private static final String TAG = "__OTP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
       // Current_User_Type = bundle.getString("Current_User_Type");

        mAuth = FirebaseAuth.getInstance();
        otpField = findViewById(R.id.verification_code_entered_by_user);
        verifyButton = findViewById(R.id.verify_btn);
        send = findViewById(R.id.sendo);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOTP(otpField.getText().toString());
            }
        });
        new Thread(this::sendOTP).start();

        // Toast.makeText(OtpActivity.this, phone, Toast.LENGTH_LONG).show();
        //new Thread(this::sendOTP).start();

        // new Thread(this::sendOTP).start();
    }
    private String countryCodePrefix() {
        return "+91";
    }

    private void sendOTP() {
        String phoneNo = Objects.requireNonNull(getIntent().getExtras()).getString("PhoneNumber");
        System.out.println(phoneNo);
        if (phoneNo == null || phoneNo.trim().length() == 0) {
            Log.d(TAG, "sendOTP ... No Phone Number");
            return;
        }
        if (!phoneNo.startsWith("+")) {
            phoneNo = countryCodePrefix() + phoneNo;
        }

        Log.d(TAG, "phoneNo: " + phoneNo);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNo,60, TimeUnit.SECONDS,  this, verificationCallbacks);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential cred) {
            String code = cred.getSmsCode();
            Log.d(TAG, "sms code: " + code + ", signInMethod: " + cred.getSignInMethod() + ", provider: " + cred.getProvider());
            if (code != null) {
                otpField.setText(code);
            }
            signInWithPhoneCredential(cred);
        }
        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(verificationId, forceResendingToken);
            Log.d(TAG, "onCodeSent: " + verificationId);
            OtpActivity.this.verificationId = verificationId;
        }
    };


    private void checkOTP(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
        signInWithPhoneCredential(credential);
    }
    private void signInWithPhoneCredential(PhoneAuthCredential credential) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithCredential(credential)
                .addOnCompleteListener(OtpActivity.this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Phone Authentication successful");
                        Intent I = new Intent(OtpActivity.this,UserRoutingActivity.class);
                        I.putExtra("Current_User_ID", Current_User_ID );
                      //  I.putExtra("Current_User_Type", Current_User_Type);
                        I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(I);
                        finish();



                    } else {
                        String message = "User Authentication Failed";
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            message = "Incorrect OTP";
                        }
                        Toast.makeText(OtpActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                });
    }

}
    /*
    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            Intent i = new Intent(OtpActivity.this, MainBuyerActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(OtpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void sendOTP(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone, // first parameter is user's mobile number
                60, // second parameter is time limit for OTP
                // verification which is 60 seconds in our case.
                TimeUnit.SECONDS, // third parameter is for initializing units
                // for time period which is in seconds in our case.
                (Activity) TaskExecutors.MAIN_THREAD, // this task will be excuted on Main thread.
                mCallBack // we are calling callback method when we recieve OTP for
                // auto verification of user.
        );
    }
    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                otpField.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
    }
*/

