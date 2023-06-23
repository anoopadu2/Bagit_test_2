package com.example.bagit_test_2;

import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.bagit_test_2.Adapters.AdapterCart;
import com.example.bagit_test_2.Adapters.AdapterCatalogue;
import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Catalogue_Item;
import com.example.bagit_test_2.Users.Main;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;

public class Catalogue_Page extends Custom_Activity {

    FireBase fb = new FireBase();
    Main m = new Main();
    public RecyclerView catalogueRv;
    ArrayList<Catalogue_Item> Catalogue_items_list = new ArrayList<Catalogue_Item>(0);
    public AdapterCatalogue mAdapterCatalogue;
    public TextView grandTotal, cartIsEmpty, detailCost, detailDelivery, detailTotal;
    public Button placeOrder;

    public User u = new User();
    public String User_ID;





    public ProgressDialog progressBar;

    public String Current_User_ID , Current_User_Type;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue__page);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");


        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0
        catalogueRv = findViewById(R.id.catalogueItemRV);
        progressBar.show();
        //User_ID = fb.get_current_User_ID();

        fb.Load_User(u,Current_User_ID,Catalogue_Page.this,progressBar,Current_User_Type);

    }

    @Override
    public void run(ProgressDialog progressBar) {

        progressBar.dismiss();
        Catalogue_items_list = u.MyCatalogue.catalog;


        System.out.println("Displaying Catalogue Items Details");
        mAdapterCatalogue = new AdapterCatalogue(Catalogue_Page.this, Catalogue_items_list,u);
        catalogueRv.setAdapter(mAdapterCatalogue);








        //p_a.setText("XYZ Colony , Hyderabad , Telangana , 500090");
        //p_a.setText(u.address.get_address());




    }
}