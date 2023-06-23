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
import com.example.bagit_test_2.Users.Catalogue_Item;
import com.example.bagit_test_2.Users.Item;
import com.example.bagit_test_2.Users.Main;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;

public class Item_Details_Catalogue_Page extends Custom_Activity {
    FireBase fb = new FireBase();
    Main m = new Main();

    public Item P;
    public User u = new User();
    public TextView t_p_name;
    public TextView t_p_desc;
    public TextView t_p_price;
    public RatingBar r_p_rating;
    public Button b_set_quantity ;
    public Button b_select_s;
    public Button b_cart;
    public EditText b_quantity,b_set_price;
    public ArrayAdapter<String> arrayAdapter2;
    public Spinner Seller_Spinner;
    public ArrayList<String> sn;
    public ImageView img ;

    public User selected_seller = new User();
    public float selected_price = (float) 0.0;
    public int selected_quantity = 0;
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
        setContentView(R.layout.activity_item__details__catalogue__page);

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
        b_set_price =findViewById(R.id.b_price);
        //b_quantity.setText("1");
        //b_set_price.setText("0");
       // b_set_price.setText("0.0");
        //t_p_price.setText("--/--");
        img = findViewById(R.id.product_details_image);





        //t_ = findViewById(R.id.p_address);

        fb.Load_Item(Item_ID,P,Item_Details_Catalogue_Page.this,progressBar);
        fb.Load_User(u,Current_User_ID,Item_Details_Catalogue_Page.this,progressBar,Current_User_Type);
        fb.Load_Retailers_List(Seller_list_All,Item_Details_Catalogue_Page.this,progressBar);



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
       // t_p_price.setText("--/--");
        r_p_rating.setRating((float)P.Item_Average_rating);
        r_p_rating.setIsIndicator(true);

       /* Filter.filter_by_quantity(Seller_list_All,Seller_list_display,Seller_List_dropdown,Item_ID,1);

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

*/

        //p_a.setText("XYZ Colony , Hyderabad , Telangana , 500090");
        //p_a.setText(u.address.get_address());




    }



    public void OC_add_to_catalogue(View v){
        selected_price = Float.valueOf(String.valueOf(b_set_price.getText()));
        selected_quantity = Integer.valueOf(String.valueOf(b_quantity.getText()));
        if(selected_price <= 0 || selected_quantity <0 )
        {
            Toast.makeText(getApplicationContext(),
                    "Invalid selection!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        Catalogue_Item ci = new Catalogue_Item();
        ci.Item_ID = Item_ID;
        ci.item = P;
        ci.Price = selected_price;
        ci.Quantity = selected_quantity;

        u.MyCatalogue.Add_Item_To_Catalog(ci.Item_ID,ci.Quantity,ci.Price,ci.item);
        fb.Save_User(u,Current_User_ID);
        Toast.makeText(getApplicationContext(),
                "Item added to catalogue",
                Toast.LENGTH_LONG)
                .show();

    }


}