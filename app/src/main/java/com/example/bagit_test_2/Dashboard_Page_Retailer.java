package com.example.bagit_test_2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.User;


public class Dashboard_Page_Retailer extends Custom_Activity {
    FireBase fb = new FireBase();
    public User u = new User();
    public TextView t_n;
    public String Current_User_ID , Current_User_Type;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard__page__retailer);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");
        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0

        progressBar.show();

        t_n = findViewById(R.id.t_welcome_name);

        fb.Load_User(u,Current_User_ID,Dashboard_Page_Retailer.this,progressBar,Current_User_Type);



    }

    @Override
    public void run(ProgressDialog progressBar) {

        progressBar.dismiss();
        System.out.println("Displaying User name");
        t_n.setText("Hello " + u.Name + " !");





    }
    public void  OC_my_account(View v){
        Intent I = new Intent(Dashboard_Page_Retailer.this,account.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        startActivity(I);


    }
    public void  OC_my_orders(View v){
        Intent I = new Intent(Dashboard_Page_Retailer.this,Orders_Page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        startActivity(I);
        return;


    }
    public void  OC_purchase_Items(View v){

        Intent I = new Intent(Dashboard_Page_Retailer.this,Categories_Page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        I.putExtra("Purpose", "Cart");
        startActivity(I);


    }
    public void  OC_go_to_cart(View v){
        Intent I = new Intent(Dashboard_Page_Retailer.this,Cart_Page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        I.putExtra("Purpose", "Cart");
        startActivity(I);

    }

    public void OC_logout(View v){
       // Toast.makeText(Dashboard_Page_Retailer.this, "Logging out user...", Toast.LENGTH_SHORT).show();
        Intent I = new Intent(Dashboard_Page_Retailer.this,LoginActivity.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        startActivity(I);
        finish();

       return;
    }

    public void OC_my_catalogue(View v){
        Intent I = new Intent(Dashboard_Page_Retailer.this,Catalogue_Page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        I.putExtra("Purpose", "Catalogue");
        startActivity(I);


        return;
    }

    public void OC_my_order_requests(View v){
        Intent I = new Intent(Dashboard_Page_Retailer.this,Order_Requests_Page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
       // I.putExtra("Purpose", "Catalogue");
        startActivity(I);
        return;
    }

    public void OC_edit_catlogue(View v){
        Intent I = new Intent(Dashboard_Page_Retailer.this,Categories_Page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        I.putExtra("Purpose", "Catalogue");
        startActivity(I);
        return;
    }

}


