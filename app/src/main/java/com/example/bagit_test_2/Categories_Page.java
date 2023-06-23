package com.example.bagit_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Categories_Page extends AppCompatActivity {
    public String Current_User_ID , Current_User_Type , Purpose;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories__page);

        Bundle bundle = getIntent().getExtras();
        Current_User_ID = bundle.getString("Current_User_ID");
        Current_User_Type = bundle.getString("Current_User_Type");
        Purpose = bundle.getString("Purpose");
    }

    public void OC_Vegetables(View v){
        Intent I = new Intent(Categories_Page.this,shopping_page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        I.putExtra("Purpose", Purpose);
        I.putExtra("Category", "Vegetables");

        startActivity(I);
    }

    public void OC_Dairy_Products(View v){
        Intent I = new Intent(Categories_Page.this,shopping_page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        I.putExtra("Purpose", Purpose);
        I.putExtra("Category", "Dairy Products");
        startActivity(I);
    }

    public void OC_Groceries(View v){
        Intent I = new Intent(Categories_Page.this,shopping_page.class);
        I.putExtra("Current_User_ID", Current_User_ID );
        I.putExtra("Current_User_Type", Current_User_Type);
        I.putExtra("Purpose", Purpose);
        I.putExtra("Category", "Groceries");
        startActivity(I);
    }

}