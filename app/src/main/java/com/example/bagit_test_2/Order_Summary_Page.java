package com.example.bagit_test_2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagit_test_2.Processes.Place_Order;
import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Cart_Item;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;

public class Order_Summary_Page extends Custom_Activity {
    FireBase fb = new FireBase();
    public User u = new User();
    public TextView name, email, phone_num, address, total_price, total_items,delivery_charge;
    public Button confirm_order;
    public RadioButton cod;
    public String User_ID;

    public String Current_User_ID , Current_User_Type;
    public ArrayList<User> Seller_list_All = new ArrayList<User>(0);







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__summary__page);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");

        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0

        progressBar.show();
        name = findViewById(R.id.t_name);
        email = findViewById(R.id.t_email);
        phone_num = findViewById(R.id.t_pn);
        address = findViewById(R.id.t_add);
        total_items = findViewById(R.id.t_total_items);
        total_price = findViewById(R.id.t_total_price);
        delivery_charge = findViewById(R.id.t_delivery_charges);
        confirm_order = findViewById(R.id.b_confirm_order);
        cod = findViewById(R.id.b_cod);



        //User_ID = fb.get_current_User_ID();
        if(Current_User_Type.equals("Customer")){
            fb.Load_Retailers_List(Seller_list_All,Order_Summary_Page.this,progressBar);
        }
        if(Current_User_Type.equals("Retailer")){
            fb.Load_Wholesalers_List(Seller_list_All,Order_Summary_Page.this,progressBar);
        }
        fb.Load_User(u,Current_User_ID,Order_Summary_Page.this,progressBar,Current_User_Type);

    }
    @Override
    public void run(ProgressDialog progressBar) {

        progressBar.dismiss();
        int total_items_ = 0;
        for(Cart_Item ci: u.cart.Items_In_Cart){
            total_items_ = total_items_ + ci.Quantity;

        }


        name.setText("Name : " + u.Name);
        address.setText("Address : " + u.address.get_address());
        email.setText("Email : " + u.Email);
        phone_num.setText("Phone : " + u.Phone_number);
        total_items.setText("Total Items : " + String.valueOf(total_items_));
        total_price.setText("Total Price : Rs." + Float.toString(u.cart.get_total() + u.cart.delivery_fee));
        delivery_charge.setText("Delivery Charges : "+ u.cart.delivery_fee );
        cod.setChecked(true);

    }

    public void OC_Confirm_Order(View view){
        Place_Order pb = new Place_Order();
        pb.Update_Seller_Catalog_Requests(u,Seller_list_All,Order_Summary_Page.this);
        pb.Update_User_Orders(u);
        Toast.makeText(getApplicationContext(),
                "Order Placed successfully !",
                Toast.LENGTH_LONG)
                .show();


        Intent I = new Intent(Order_Summary_Page.this,Orders_Page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        startActivity(I);
        finish();


    }
}