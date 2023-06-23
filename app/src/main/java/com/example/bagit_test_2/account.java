package com.example.bagit_test_2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Main;
import com.example.bagit_test_2.Users.User;

public class account extends Custom_Activity {

    FireBase fb = new FireBase();
    Main m = new Main();
    public String Current_User_ID , Current_User_Type;

    public User u = new User();
    public TextView p_n;
    public TextView p_e;
    public TextView p_p;
    public TextView p_a;

    public ProgressDialog progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");


        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0

        progressBar.show();

        p_n = findViewById(R.id.p_name);
        p_e = findViewById(R.id.p_email);
        p_p = findViewById(R.id.p_number);
        p_a = findViewById(R.id.p_address);


        fb.Load_User(u,Current_User_ID,account.this,progressBar,Current_User_Type);
    }

    @Override
    public void run(ProgressDialog progressBar) {

        progressBar.dismiss();
        System.out.println("Displaying User Details");
        p_n.setText(u.Name);
        p_e.setText(u.Email);
        p_p.setText(u.Phone_number);
        p_a.setText(u.address.get_address());
        //p_a.setText(u.address.get_address());




    }




}