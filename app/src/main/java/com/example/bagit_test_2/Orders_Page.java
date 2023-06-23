package com.example.bagit_test_2;

import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.bagit_test_2.Adapters.AdapterOrder;
import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Main;
import com.example.bagit_test_2.Users.Order_Item;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;
import java.util.Collections;

public class Orders_Page extends Custom_Activity {

    FireBase fb = new FireBase();
    Main m = new Main();
    public RecyclerView OrderItemRv;
    ArrayList<Order_Item> Order_Item_list = new ArrayList<Order_Item>(0);
    public AdapterOrder mAdapterOrder;
    public User u = new User();
    public ProgressDialog progressBar;

    public String Current_User_ID , Current_User_Type;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders__page);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");

        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0
        OrderItemRv = findViewById(R.id.itemRV);
        progressBar.show();
        fb.Load_User(u,Current_User_ID,Orders_Page.this,progressBar,Current_User_Type);



    }

    @Override
    public void run(ProgressDialog progressBar) {

        progressBar.dismiss();
        Order_Item_list = u.MyOrders.Orders;
        Collections.reverse(Order_Item_list);


        System.out.println("Displaying Order Items ");
        mAdapterOrder = new AdapterOrder(Orders_Page.this, Order_Item_list, u);
        OrderItemRv.setAdapter(mAdapterOrder);

    }
    }