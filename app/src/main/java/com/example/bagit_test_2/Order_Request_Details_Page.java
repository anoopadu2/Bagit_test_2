package com.example.bagit_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Tools.Notifications;
import com.example.bagit_test_2.Users.Order_Item;
import com.example.bagit_test_2.Users.Order_Request_Item;
import com.example.bagit_test_2.Users.User;

import java.net.UnknownServiceException;

public class Order_Request_Details_Page extends Custom_Activity {

    public String Current_User_ID , Current_User_Type, Order_ID, Buyer_ID;
    public TextView name, price, quantity,  total_price, seller_name,order_placed_on, order_status,t_delivery_by, t_estimated_delivery;;
    public EditText i_order_status;
    public User u = new User();
    public User b = new User();
    FireBase fb = new FireBase();
    Order_Request_Item or = new Order_Request_Item();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__request__details__page);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");
        Order_ID = bundle.getString("Order_ID");
        Buyer_ID = bundle.getString("Buyer_ID");


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
        i_order_status = findViewById(R.id.i_order_status);
        t_delivery_by = findViewById(R.id.t_delivery_by);
        t_estimated_delivery = findViewById(R.id.t_estimated_delivery_date);


        progressBar.show();
        if (Current_User_Type.equals("Retailer")) {

            fb.Load_User(b,Buyer_ID,Order_Request_Details_Page.this,progressBar,"Customer");

        }
        if (Current_User_Type.equals("Wholesaler")) {

            fb.Load_User(b,Buyer_ID,Order_Request_Details_Page.this,progressBar,"Retailer");

        }

        fb.Load_User(u,Current_User_ID,Order_Request_Details_Page.this,progressBar,Current_User_Type);
    }

    @Override
    public void run(ProgressDialog progressBar) {

        progressBar.dismiss();


        or = u.MyOrderRequests.Get_Order_Request(Order_ID);

        name.setText("Name : " + or.Item_Ordered.Item_Name);
        price.setText("Price : Rs. " + Float.toString(or.Item_total_Price));
        quantity.setText("Quantity : " + String.valueOf(or.Item_Quantity));

        total_price.setText("Total price : Rs. " + Float.toString(or.Item_total_Price *or.Item_Quantity));
        seller_name.setText("Buyer Name : " + or.Buyer_Name);
        order_placed_on.setText("Order placed on : " + or.Order_Placed_On);
        order_status.setText("Order Status : "+ or.Order_Status );
        t_estimated_delivery.setText("Estimated delivery date : " +or.Order_Estimated_Delivery_By);
        t_delivery_by.setText("Delivery by : " +or.Delivered_by);

        }

        public void OC_Update_Status(View v){
        String OS = i_order_status.getText().toString();
        if(OS.isEmpty()){
            Toast.makeText(Order_Request_Details_Page.this, "Enter Status ! ",  Toast.LENGTH_SHORT).show();
            return;
        }
        String message = "Order status update" + " for your order containing "+ or.Item_Ordered.Item_Name + " : " + OS;
        Notifications.send(message,b.Phone_number,Order_Request_Details_Page.this);
        if(OS.equals("Order delivered")){
            message = "Your order containing "+ or.Item_Ordered.Item_Name + " was successfully delivered! Please give your valuable feedback through the app. " ;
            Notifications.send(message,b.Phone_number,Order_Request_Details_Page.this);
        }
        System.out.println(OS);
        b.MyOrders.Update_Order_Status(Order_ID,OS);
        or.Order_Status = OS;
        fb.Save_User(b,b.ID);
        fb.Save_User(u,u.ID);
        order_status.setText("Order Status : "+ or.Order_Status );
        Toast.makeText(Order_Request_Details_Page.this, "Order status updated ! ",  Toast.LENGTH_SHORT).show();


        }


}

