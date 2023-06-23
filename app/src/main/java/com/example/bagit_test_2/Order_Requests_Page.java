package com.example.bagit_test_2;

import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.bagit_test_2.Adapters.AdapterOrder;
import com.example.bagit_test_2.Adapters.AdapterOrderRequest;
import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Main;
import com.example.bagit_test_2.Users.Order_Request_Item;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;
import java.util.Collections;

public class Order_Requests_Page extends Custom_Activity {

    FireBase fb = new FireBase();
    Main m = new Main();
    public RecyclerView OrderRequestRv;
    ArrayList<Order_Request_Item> Order_Request_List = new ArrayList<Order_Request_Item>(0);
    public AdapterOrderRequest mAdapterOrder;
    public User u = new User();
    public ProgressDialog progressBar;

    public String Current_User_ID , Current_User_Type;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__requests__page);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");

        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0
        OrderRequestRv = findViewById(R.id.itemRV);
        progressBar.show();

        fb.Load_User(u,Current_User_ID,Order_Requests_Page.this,progressBar,Current_User_Type);



    }

    @Override
    public void run(ProgressDialog progressBar) {

        progressBar.dismiss();
        Order_Request_List = u.MyOrderRequests.OrderRequests;
        Collections.reverse(Order_Request_List);


        System.out.println("Displaying Order Requests ");
        mAdapterOrder = new AdapterOrderRequest(Order_Requests_Page.this, Order_Request_List, u);
        OrderRequestRv.setAdapter(mAdapterOrder);

    }
}