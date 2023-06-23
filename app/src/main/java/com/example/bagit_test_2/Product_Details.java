package com.example.bagit_test_2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagit_test_2.Tools.Add_Data;
import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.Filter;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Cart_Item;
import com.example.bagit_test_2.Users.Item;
import com.example.bagit_test_2.Users.Main;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;

public class Product_Details extends Custom_Activity {
    FireBase fb = new FireBase();
    Main m = new Main();

    public Item P;
    public User u = new User();
    public TextView t_p_name;
    public TextView t_p_desc;
    public TextView t_p_price;
    public RatingBar r_p_rating;
    public Button b_set_quantity;
    public Button b_select_s;
    public Button b_cart;
    public EditText b_quantity;
    public ArrayAdapter<String> arrayAdapter2;
    public Spinner Seller_Spinner;
    public ArrayList<String> sn;
    public ImageView img ;

    public User selected_seller = new User();
    public float selected_price = (float) 0.0;
    public int selected_quantity = 1;
    public boolean sellers_available = false;


    public ArrayList<User> Seller_list_All = new ArrayList<User>(0);
    public ArrayList<User> Seller_list_display = new ArrayList<User>(0);
    public ArrayList<String> Seller_List_dropdown = new ArrayList<String>(0);


    public ProgressDialog progressBar;
    public String Item_ID;
    public String Current_User_ID , Current_User_Type;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");
         Item_ID = bundle.getString("Item_ID");

        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0
        P = m.Get_Test_Item(Item_ID,"default name");

        progressBar.show();

        t_p_name = (TextView)findViewById(R.id.t_product_details);
        t_p_desc = (TextView)findViewById(R.id.t_product_desc);
        t_p_price = (TextView)findViewById(R.id.product_details_price);
        Seller_Spinner = findViewById(R.id.spinner2);
        r_p_rating = findViewById(R.id.r_product_rating);
        r_p_rating.setMax(5);
        r_p_rating.setNumStars(5);
        r_p_rating.setStepSize((float)0.1);
        b_quantity = findViewById(R.id.b_quantity);
        b_quantity.setText("1");
        t_p_price.setText("--/--");
        img = findViewById(R.id.product_details_image);

        b_quantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() ==KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    Toast.makeText(Product_Details.this, "Quantity changed to " + b_quantity.getText(), Toast.LENGTH_SHORT).show();
                    sn.clear();
                    int n = Integer.valueOf(b_quantity.getText().toString());
                    Filter.filter_by_quantity(Seller_list_All,Seller_list_display,Seller_List_dropdown,Item_ID,n);
                    if(Seller_list_display.size()==0){
                        t_p_price.setText("--/--");
                        sellers_available = false;
                    }
                    else{
                        sellers_available = true;
                        selected_seller = Seller_list_display.get(0);
                        selected_quantity = Integer.valueOf(b_quantity.getText().toString());
                        selected_price = (Seller_list_display.get(0).MyCatalogue.get_price(Item_ID));
                        t_p_price.setText(String.valueOf(selected_price));
                    }
                    arrayAdapter2.notifyDataSetChanged();
                    return true;

                }
                return false;
            }
        });



        //t_ = findViewById(R.id.p_address);

        fb.Load_Item(Item_ID,P,Product_Details.this,progressBar);
        fb.Load_User(u,Current_User_ID,Product_Details.this,progressBar,Current_User_Type);

        if(Current_User_Type.equals("Customer")){
            fb.Load_Retailers_List(Seller_list_All,Product_Details.this,progressBar);
        }
        if(Current_User_Type.equals("Retailer")){
            fb.Load_Wholesalers_List(Seller_list_All,Product_Details.this,progressBar);
        }


         sn = new ArrayList<String>(0);
        sn.add("Rs. 35 - Mark pvt Ltd.");
        sn.add("Rs. 25 - Jeff pvt Ltd.");
        sn.add("Rs. 45 - Elon pvt Ltd.");
        Seller_List_dropdown.add("Select Seller");
        arrayAdapter2 = new ArrayAdapter<String>(Product_Details.this,android.R.layout.simple_spinner_item, Seller_List_dropdown);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Seller_Spinner.setAdapter(arrayAdapter2);
        Seller_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                if(parent.getItemAtPosition(position).toString().equals("Select Seller")){
                    return;
                }
                else if(parent.getItemAtPosition(position).toString().equals("No sellers available")){

                    return;
                }
                else{
                float p = Seller_list_display.get(position).MyCatalogue.get_price(Item_ID);
                t_p_price.setText("Rs. "+ Float.toString(p));
                selected_price = p;
                selected_quantity = Integer.valueOf(b_quantity.getText().toString());
                selected_seller = Seller_list_display.get(position);

                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,  Toast.LENGTH_SHORT).show();}
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        try {

            String resourceName = "i" + Item_ID.substring(1);
            System.out.println("Resource is " + resourceName);
            int resID = Add_Data.getResId(resourceName, R.drawable.class);
            img.setImageResource(resID);
        }
        catch (Exception e){
            img.setImageResource(R.drawable.ic_cart_primary);
        }
    }

    @Override
    public void run(ProgressDialog progressBar) {

        progressBar.dismiss();
        System.out.println("Displaying Item Details");

        t_p_name.setText(P.Item_Name);
        t_p_desc.setText(P.Item_Description);
        t_p_price.setText("--/--");
        r_p_rating.setRating((float)P.Item_Average_rating);
        r_p_rating.setIsIndicator(true);

        Filter.filter_by_quantity(Seller_list_All,Seller_list_display,Seller_List_dropdown,Item_ID,1);

        if(Seller_list_display.size()==0){
            t_p_price.setText("--/--");
            sellers_available = false;
        }
        else{
            sellers_available = true;
            selected_seller = Seller_list_display.get(0);
            selected_quantity = Integer.valueOf(b_quantity.getText().toString());
            selected_price = (Seller_list_display.get(0).MyCatalogue.get_price(Item_ID));
            t_p_price.setText(String.valueOf(selected_price));
        }

        arrayAdapter2.notifyDataSetChanged();



        //p_a.setText("XYZ Colony , Hyderabad , Telangana , 500090");
        //p_a.setText(u.address.get_address());




    }



    public void OC_add_to_cart(View v){
        if(!sellers_available){
            Toast.makeText(Product_Details.this, "Invalid Selection ! ",  Toast.LENGTH_SHORT).show();
            return;
        }
        if(selected_quantity==0){
            Toast.makeText(Product_Details.this, "Invalid Selection ! ",  Toast.LENGTH_SHORT).show();
            return;

        }
        Cart_Item ci = new Cart_Item();
        ci.Item_ID = P.Item_ID;
        ci.item = P;
        ci.price = selected_price;
        ci.Quantity = selected_quantity;
        ci.Seller_Name = selected_seller.Seller_Name;
        ci.Seller_ID = selected_seller.ID;
        u.cart.Add_To_Cart(ci);
        fb.Save_User(u,Current_User_ID);
        Toast.makeText(getApplicationContext(),
                "Item added to cart",
                Toast.LENGTH_LONG)
                .show();

    }


}