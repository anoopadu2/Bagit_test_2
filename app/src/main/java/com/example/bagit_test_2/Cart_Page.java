package com.example.bagit_test_2;

import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagit_test_2.Adapters.AdapterCart;
import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Cart_Item;
import com.example.bagit_test_2.Users.Main;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;

public class Cart_Page extends Custom_Activity {

    FireBase fb = new FireBase();
    Main m = new Main();
    public RecyclerView cartItemRv;
    ArrayList<Cart_Item> Cart_Item_list = new ArrayList<Cart_Item>(0);
    public AdapterCart mAdapterCart;
    public TextView grandTotal, cartIsEmpty, detailCost, detailDelivery, detailTotal;
    public Button placeOrder;

    public User u = new User();
    public String User_ID;
    public String Current_User_ID , Current_User_Type;








    public ProgressDialog progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart__page);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");



        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0
        progressBar.show();

        cartItemRv = findViewById(R.id.catalogueItemRV);
        grandTotal = findViewById(R.id.grandTotalTV);
        placeOrder = findViewById(R.id.placeOrder);
        cartIsEmpty = findViewById(R.id.cartIsEmpty);
        detailCost = findViewById(R.id.detailCost);
        detailDelivery = findViewById(R.id.detailDelivery);
        detailTotal = findViewById(R.id.detailTotal);



        //User_ID = fb.get_current_User_ID();

        fb.Load_User(u,Current_User_ID,Cart_Page.this,progressBar,Current_User_Type);

    }

    @Override
    public void run(ProgressDialog progressBar) {

        progressBar.dismiss();
        Cart_Item_list = u.cart.Items_In_Cart;


        System.out.println("Displaying Cart Items Details");
        mAdapterCart = new AdapterCart(Cart_Page.this, Cart_Item_list,u);
        cartItemRv.setAdapter(mAdapterCart);
        update_cart_values();






        //p_a.setText("XYZ Colony , Hyderabad , Telangana , 500090");
        //p_a.setText(u.address.get_address());




    }

    public void update_cart_values(){
        float delivery_fee = u.cart.delivery_fee;
        if(u.cart.get_total() == 0){
            delivery_fee =0;

        }
        detailTotal.setText(Float.toString(u.cart.get_total() + delivery_fee ));
        detailCost.setText(Float.toString(u.cart.get_total()));
        detailDelivery.setText(Float.toString(delivery_fee));
        grandTotal.setText(Float.toString(u.cart.get_total() + delivery_fee));

    }

    public void OC_Place_Order(View view){
        if(u.cart.get_total() == 0){
            Toast.makeText(getApplicationContext(),
                    "No items in cart!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        Intent I = new Intent(Cart_Page.this,Order_Summary_Page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        startActivity(I);

    }

}