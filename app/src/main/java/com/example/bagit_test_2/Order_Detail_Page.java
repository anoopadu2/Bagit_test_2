package com.example.bagit_test_2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Item;
import com.example.bagit_test_2.Users.Order_Item;
import com.example.bagit_test_2.Users.User;

public class Order_Detail_Page extends Custom_Activity {
    FireBase fb = new FireBase();
    public User u = new User();
    public TextView name, price, quantity,  total_price, seller_name,order_placed_on, order_status,t_delivery_by, t_estimated_delivery;
    public Button add_rating;
    public RatingBar rating , rating_d;
    public String Order_ID;
    public String Item_ID;
    public Order_Item o;
    public Item I = new Item();

    public String Current_User_ID , Current_User_Type;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__detail__page);


        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");
        Order_ID = bundle.getString("Order_ID");
        Item_ID = bundle.getString("Item_ID");

        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0
        name = findViewById(R.id.t_name);
        price = findViewById(R.id.t_price);
        quantity = findViewById(R.id.t_quantity);

        seller_name = findViewById(R.id.t_buyer_name);
        total_price = findViewById(R.id.t_total_price);
        order_placed_on = findViewById(R.id.t_order_placed_on);
        order_status = findViewById(R.id.t_order_status);
        rating = findViewById(R.id.ratingBar);
        rating_d = findViewById(R.id.ratingBar_delivery);
        t_delivery_by = findViewById(R.id.t_delivery_by);
        t_estimated_delivery = findViewById(R.id.t_estimated_delivery_date);



        progressBar.show();

        fb.Load_User(u,Current_User_ID,Order_Detail_Page.this,progressBar,Current_User_Type);

        fb.Load_Item(Item_ID,I,Order_Detail_Page.this,progressBar);
    }
    @Override
    public void run(ProgressDialog progressBar) {

        progressBar.dismiss();

         o =  new Order_Item();
        o = u.MyOrders.get_order_item(Order_ID);

        name.setText("Name : " + o.Item_Ordered.item.Item_Name);
        price.setText("Price : Rs. " + Float.toString(o.Item_Ordered.price));
        quantity.setText("Quantity : " + String.valueOf(o.Item_Ordered.Quantity));

        total_price.setText("Total price : Rs. " + Float.toString(o.Item_Ordered.Quantity*o.Item_Ordered.price));
        seller_name.setText("Seller Name : " + o.Seller_Name);
        order_placed_on.setText("Order placed on : " + o.Order_Placed_On);
        order_status.setText("Order Status : "+ o.Order_Status );
        rating.setMax(5);
        rating.setStepSize((float)0.5);
        rating.setRating(o.user_rating);

        rating_d.setMax(5);
        rating_d.setStepSize((float)0.5);
        rating_d.setRating(o.delivery_rating);
        if(o.rating_given){rating.setIsIndicator(true);}
        if(o.rating_given){rating_d.setIsIndicator(true);}
        t_estimated_delivery.setText("Estimated delivery date : " +o.Order_Estimated_Delivery_By);
        t_delivery_by.setText("Delivery by : " +o.Delivered_by);


    }

    public void OC_Add_Rating(View view){
        if(o.Order_Status.equals("Order delivered")){
            o.delivered = true;
        }
        if(!o.delivered){
            Toast.makeText(getApplicationContext(),
                    "Rating can only be given after order delivery is complete",
                    Toast.LENGTH_LONG)
                    .show();
            return;

        }
        if(o.rating_given){
            Toast.makeText(getApplicationContext(),
                    "Rating already given",
                    Toast.LENGTH_LONG)
                    .show();
            return;

        }

        o.rating_given = true;
        o.user_rating = rating.getRating();
        o.delivery_rating = rating_d.getRating();
        float new_item_rating = (I.Item_Average_rating*I.Item_Total_ratings + o.user_rating)/(I.Item_Total_ratings +1);
        int new_total_rating = (I.Item_Total_ratings +1);
        I.Item_Average_rating = new_item_rating;
        I.Item_Total_ratings = new_total_rating;

        rating.setIsIndicator(true);
        rating_d.setIsIndicator(true);
        fb.Save_User(u,Current_User_ID);
        fb.Save_Item(I,I.Item_ID);

        Toast.makeText(getApplicationContext(),
                "Rating Added",
                Toast.LENGTH_LONG)
                .show();
        return;
    }
}