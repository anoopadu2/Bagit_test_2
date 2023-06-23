package com.example.bagit_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bagit_test_2.Users.Address;
import com.example.bagit_test_2.Users.Location;
import com.example.bagit_test_2.Users.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    //String locationStr = "";
    private static int LOCATION_PICK_REQUEST = 1001;
    EditText txtFullName, txtEmail, txtPassword, txtRetypePassword, txtMobileNumber, cityEt,stateEt,pinEt, areaEt,streetEt;
    Button SignUp;
    FirebaseAuth mAuth;
    private ImageButton backBtn, gpsBtn;
    ProgressDialog mProgressDialog;
    // private double latitude, longitude;
    //private String[] locationPermission;
    User user;
    Address ad;
    float lat= (float) 1.1;
    float lon= (float) 2.3;

    //location permission
   /* private LocationManager locationManager;
    private static final int LOCATION_REQUEST_CODE = 100; */
    private String name, phoneNumber,state,city,area,pin,street, email, password, confermPassword;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtEmail = (EditText) findViewById(R.id.EmailEditText);
        txtFullName = (EditText) findViewById(R.id.NameEditText);
        txtMobileNumber = (EditText) findViewById(R.id.MobileNumberEditText);
        txtPassword = (EditText) findViewById(R.id.PasswordEditText);
        txtRetypePassword = (EditText) findViewById(R.id.RetypePasswordEditText);
        cityEt = findViewById(R.id.City);
        areaEt=findViewById(R.id.area);
        stateEt=findViewById(R.id.state);
        pinEt=findViewById(R.id.pin);
        streetEt=findViewById(R.id.street);
        SignUp = (Button) findViewById(R.id.SignUpButton);
        gpsBtn = findViewById(R.id.gpsBtn);
        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Please wait");
        mProgressDialog.setCanceledOnTouchOutside(false);
        /*backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        gpsBtn.setOnClickListener(v -> {
            Intent mapIntent = new Intent(getApplicationContext(), MainActivity2.class);
            // mapIntent.putExtra("locationStr", locationEt.getText().toString().trim());
            startActivityForResult(mapIntent, LOCATION_PICK_REQUEST);
        });

       /* new LocationAccess(this).findLocation(location -> {
            String locationStr = location == null ? "" : "" + location.getLatitude() + ", " + location.getLongitude();
            if (locationEt.getText().toString().length() == 0) {
                Toast.makeText(SignUpActivity.this, "Current Location: " + locationStr, Toast.LENGTH_LONG).show();
                locationEt.setText(locationStr);
            }
        });*/

        //init permission

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });


    }



    private void inputData(){
        name = txtFullName.getText().toString();
        phoneNumber = txtMobileNumber.getText().toString();
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();
        confermPassword = txtRetypePassword.getText().toString();
        city=cityEt.getText().toString();
        area=areaEt.getText().toString();
        state=stateEt.getText().toString();
        pin=pinEt.getText().toString();
        street=streetEt.getText().toString();


        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Enter Name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(this, "Enter Phone Number...", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (latitude==0.0 && longitude==0.0 ){
//            Toast.makeText(this, "Please click gps button to detect location", Toast.LENGTH_SHORT).show();
//            return;
//        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Enter correct email...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length()<6){
            Toast.makeText(this, "Pasword must be atleast 6 charecter long...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confermPassword)){
            Toast.makeText(this, "Pasword doesn't match...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(area)){
            Toast.makeText(this, "Enter area Name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(city)){
            Toast.makeText(this, "Enter City Name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(street)){
            Toast.makeText(this, "Enter Street Name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(state)){
            Toast.makeText(this, "Enter State Name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pin)){
            Toast.makeText(this, "Enter pincode...", Toast.LENGTH_SHORT).show();
            return;
        }
        createAccount();
    }

    private void createAccount() {
        mProgressDialog.setMessage("Creating Account...");
        mProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        saveFirebaseData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mProgressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveFirebaseData() {

        mProgressDialog.setMessage("Saving Account Details..");
        mProgressDialog.show();
       // ad=new Address(state,city,pin,area,street);
       // user1=new User(name,mAuth.getUid(),email,phoneNumber,ad,"Customer",new Location(lat,lon));
        User user = new User();
        user.Name = name;
        user.ID = mAuth.getUid();
        user.Email = email;
        user.Type = "Customer";
        user.Phone_number = phoneNumber;

        user.address.add_line_1 = street;
        user.address.add_line_2 = area;
        user.address.City = city;
        user.address.State = state;
        user.address.PinCode = pin;

        user.location.Latitude = lat;
        user.location.Longitude = lon;




        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child("Customers").child(mAuth.getUid()).setValue(user) .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mProgressDialog.dismiss();
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mProgressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        finish();
                    }
                });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        Log.d("__SIGNUP", "onActivityResult; requestCode=" + requestCode + ", resultCode=" + resultCode + ", extras=" + data.getExtras());
        if (requestCode == LOCATION_PICK_REQUEST) {
            String locStr = data.getStringExtra("locationStr");
            if (locStr != null) {
                String[] arrSplit = locStr.split(", ");
                lat=Float.parseFloat(arrSplit[0]);
                lon=Float.parseFloat(arrSplit[1]);

                // locationEt.setText(locStr);
            }
        }
    }

}