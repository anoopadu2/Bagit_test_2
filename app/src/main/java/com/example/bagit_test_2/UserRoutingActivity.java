package com.example.bagit_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.User;

public class UserRoutingActivity extends Custom_Activity {

    public User u = new User();
    FireBase fb = new FireBase();
    public ProgressDialog progressBar;
    public String Current_User_ID , Current_User_Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_routing);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");
        if(Current_User_Type != null){
            redirect();
        }



        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0
        progressBar.show();
        fb.Get_User_Type(Current_User_ID,UserRoutingActivity.this,progressBar);
        //fb.Load_User(u,Current_User_ID,UserRoutingActivity.this,progressBar,Current_User_Type);

    }
    @Override
    public void run2(ProgressDialog progressBar, String utype) {
        String Uid = Current_User_ID;
        String Utype = utype;


        if(Utype.equals("Customer")){
            System.out.println("Redirecting to customer dashboard page");
            Intent I = new Intent(UserRoutingActivity.this,Dashboard_Customer.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Customer");
            startActivity(I);
            finish();
            return;

        }
        if(Utype.equals("Retailer")){
            System.out.println("Redirecting to retailer dashboard page");
            Intent I = new Intent(UserRoutingActivity.this,Dashboard_Page_Retailer.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Retailer");
            startActivity(I);
            finish();
            return;

        }
        if(Utype.equals("Wholesaler")){
            System.out.println("Redirecting to wholesaler dashboard page");
            Intent I = new Intent(UserRoutingActivity.this,Dashboard_Page_Wholeseller.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Wholesaler");
            startActivity(I);
            finish();
            return;

        }
    }
    public void redirect(){
        String Uid = Current_User_ID;
        String Utype = Current_User_Type;


        if(Utype.equals("Customer")){
            System.out.println("Redirecting to customer dashboard page");
            Intent I = new Intent(UserRoutingActivity.this,Dashboard_Customer.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Customer");
            startActivity(I);
            finish();
            return;

        }
        if(Utype.equals("Retailer")){
            System.out.println("Redirecting to retailer dashboard page");
            Intent I = new Intent(UserRoutingActivity.this,Dashboard_Page_Retailer.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Retailer");
            startActivity(I);
            finish();
            return;

        }
        if(Utype.equals("Wholesaler")){
            System.out.println("Redirecting to wholesaler dashboard page");
            Intent I = new Intent(UserRoutingActivity.this,Dashboard_Page_Wholeseller.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Wholesaler");
            startActivity(I);
            finish();
            return;

        }

    }
}