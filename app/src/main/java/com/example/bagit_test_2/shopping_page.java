package com.example.bagit_test_2;

import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bagit_test_2.Adapters.AdapterItem;
import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.Filter;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Item;
import com.example.bagit_test_2.Users.Main;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;

public class shopping_page extends Custom_Activity {


    FireBase fb = new FireBase();
    Main m = new Main();
    public ArrayList<Item> all_items_list = new ArrayList<>(0);
    public ArrayList<Item> items_list_display = new ArrayList<Item>(0);
    public User u = new User();
    private TextView t_category,nameTv, emailTv, phoneTv, tabShopsTv, tabOrdersTv;
    ImageButton t_cart;
    private ImageButton logoutBtn,  cartBtn;
    private RelativeLayout shopRl, ordersRl;
    private ImageView profileIv;
    private RecyclerView ItemRv, orderRv;
    public AdapterItem mAdapterCart;
    public String category;
    public SearchView searchView;

    public String Current_User_ID , Current_User_Type , Purpose;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_page);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");
        category = bundle.getString("Category");
        Purpose = bundle.getString("Purpose");

        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data...");
        progressBar.setProgress(0);//initially progress is 0

        progressBar.show();
        ItemRv = findViewById(R.id.itemRV);
        t_category = findViewById(R.id.t_welcome_name);
        t_category.setText(category);
        searchView = findViewById(R.id.searchbar);
        t_cart = findViewById(R.id.t_cart);

        if(Purpose.equals("Catalogue")){
            t_cart.setImageResource(R.drawable.catalog_icon_mini_2);

        }


        searchView.getQuery();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                 {
                    Filter.Search_By_Name(items_list_display,query);
                    if(items_list_display.size()==0){
                        Toast.makeText(shopping_page.this, "No items found ", Toast.LENGTH_SHORT).show();
                    }
                    mAdapterCart.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    Filter.filter_By_Category(all_items_list,items_list_display,category);
                    mAdapterCart.notifyDataSetChanged();
                }

                return false;
            }
        });

        fb.Load_Items_List(all_items_list, shopping_page.this,progressBar );
        fb.Load_User(u,Current_User_ID,shopping_page.this,progressBar,Current_User_Type);

    }

    @Override
    public void run(ProgressDialog progressBar) {
        progressBar.dismiss();

        Filter.filter_By_Category(all_items_list,items_list_display,category);

        //this.progressBar.dismiss();
        System.out.println("Printing Items List");
        //String s = "";
        //ArrayList<String> Item_names_ = new ArrayList<String>(0);

        for(int i = 0; i< items_list_display.size(); i++){
            System.out.print(i);
            System.out.println(items_list_display.get(i).Item_Name);




        }

        mAdapterCart = new AdapterItem(shopping_page.this, items_list_display,u);

        ItemRv.setAdapter(mAdapterCart);








    }
    public void OC_go_to_cart(View v){

        if(Purpose.equals("Cart")){

            Intent I = new Intent(shopping_page.this,Cart_Page.class);
            I.putExtra("Current_User_ID", Current_User_ID );
            I.putExtra("Current_User_Type", Current_User_Type);
            startActivity(I);
            return;
        }
        if(Purpose.equals("Catalogue")){

            Intent I = new Intent(shopping_page.this,Catalogue_Page.class);
            I.putExtra("Current_User_ID", Current_User_ID );
            I.putExtra("Current_User_Type", Current_User_Type);
            startActivity(I);
            return;
        }


    }
}