package com.example.bagit_test_2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagit_test_2.Tools.*;
import com.example.bagit_test_2.Users.*;

import java.util.ArrayList;

public class MainActivity2 extends Custom_Activity {

    FireBase fb = new FireBase();
    Main m = new Main();
    public ArrayList<Item> items_vector = new ArrayList<Item>(0);


    ArrayList<Cart_Item> CI = new ArrayList<Cart_Item>(0);
    public User u = new User();

    public ProgressDialog progressBar;
    public TextView tv;
    public ListView L;
    public Button b , b2;
    public Spinner spn ;
    public ArrayAdapter<String> arrayAdapter2;
    public ArrayList<String> sn;
    public EditText t_id , t_type;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b = findViewById(R.id.btn);
        b.setText("Loading");
        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0

        progressBar.show();
         tv = findViewById(R.id.TVI);
         L = findViewById(R.id.ls);
         spn = findViewById(R.id.spinner1);
         b2 = findViewById(R.id.button2);

         t_id = findViewById(R.id.t_id);
        t_type = findViewById(R.id.t_type);


          sn = new ArrayList<String>(0);
         sn.add("Rs. 35 - Mark pvt Ltd.");
         sn.add("Rs. 25 - Jeff pvt Ltd.");
         sn.add("Rs. 45 - Elon pvt Ltd.");
        arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sn);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(arrayAdapter2);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();

                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,  Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });








        // To dismiss the dialog
        Add_Data ad = new Add_Data();
        //ad.Add_Customer();
        //ad.Add_Items();
        fb.Load_Items_List(items_vector, MainActivity2.this,progressBar );
        fb.Load_User(u,"C0001",MainActivity2.this,progressBar,"Customer");











        /*User u = m.get_dummy_user();
        u.Name = "Jeff";
        u.Seller_Name = u.Name + "-Sales pvt limited";
        u.Type = "Retailer";
        u.Email = u.Name + "@gmail.com";
        fb.Save_User(u,"R0001");

        u.Name = "Mark";
        u.Seller_Name = u.Name + "-Sales pvt limited";
        u.Type = "Retailer";
        u.Email = u.Name + "@gmail.com";
        fb.Save_User(u,"R0002");

        u.Name = "Elon";
        u.Type = "Retailer";
        u.Seller_Name = u.Name + "-Sales pvt limited";
        u.Email = u.Name + "@gmail.com";
        fb.Save_User(u,"R0003");
        */


    }
    public void OC_Login(View v){
        String Uid = t_id.getText().toString();
        String Utype = t_type.getText().toString();

        if(Utype.equals("Customer")){
            Intent I = new Intent(MainActivity2.this,Dashboard_Customer.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Customer");
            startActivity(I);

        }
        if(Utype.equals("Retailer")){
            Intent I = new Intent(MainActivity2.this,Dashboard_Page_Retailer.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Retailer");
            startActivity(I);

        }
        if(Utype.equals("Wholesaler")){
            Intent I = new Intent(MainActivity2.this,Dashboard_Page_Wholeseller.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Wholesaler");
            startActivity(I);

        }






    }


    @Override
    public void run(ProgressDialog progressBar) {
        progressBar.dismiss();
        b.setText("Place Order");


        //this.progressBar.dismiss();
        System.out.println("Printing List");
        String s = "";
        ArrayList<String> Item_names_ = new ArrayList<String>(0);

        for(int i = 0; i< items_vector.size(); i++){
            System.out.print(i);
            System.out.println(items_vector.get(i).Item_Name);
            s = s + items_vector.get(i).Item_Name + " ";
            Item_names_.add(items_vector.get(i).Item_Name + "( " + items_vector.get(i).Item_ID +" )");



        }

        ArrayAdapter<String>arrayAdapter = new ArrayAdapter<String>(this, R.layout.name_list,Item_names_);
        L.setAdapter(arrayAdapter);



        System.out.println(s);
        tv.setText("Cart of :" + u.Name);




    }

    public void OC_reload(View v){ //To dash Board
        //System.out.println("Hello");

        String Uid = "C0001";
        String Utype = "Customer";

        if(Utype.equals("Customer")){
            Intent I = new Intent(MainActivity2.this,Dashboard_Customer.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Customer");
            startActivity(I);

        }
        if(Utype.equals("Retailer")){
            Intent I = new Intent(MainActivity2.this,Dashboard_Page_Retailer.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Retailer");
            startActivity(I);

        }
        if(Utype.equals("Wholesaler")){
            Intent I = new Intent(MainActivity2.this,Dashboard_Page_Wholeseller.class);
            I.putExtra("Current_User_ID", Uid);
            I.putExtra("Current_User_Type", "Wholesaler");
            startActivity(I);

        }










      /*  System.out.print(u.Name);

        Item I = m.Get_Test_Item("Dairy Milk","I0001");
        //fb.Load_Item("I0001");
        Cart_Item CI = new Cart_Item();
        CI.item = I;
        CI.Item_ID = I.Item_ID;
        CI.price = 10;
        CI.Quantity = 1;
        //Vector <User> S = fb.Load_Retailers_List();
        CI.Seller_Name = "Mark-Sales pvt limited";
        CI.Seller_ID = "R0002";
        u.cart.Add_To_Cart(CI);
        //fb.Save_User(u,"C0001");*/


    }

    public void OC_show_item_details(View v){
        Intent i = new Intent(MainActivity2.this,Dashboard_Page_Retailer.class);
        startActivity(i);


    }
    public void OC_clear_spinner(View v){
        return;
    }



}