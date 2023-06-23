package com.example.bagit_test_2.Users;
import java.util.*;



public class Item {
   // public String Item_Number;
    public String Item_ID;
    public String Item_Name ;
    public String Category = "Grocery";
   // public float Item_Price ;
    public String Item_Description ;
    //public Vector<Seller> Item_Seller;
    public ArrayList<Review> Item_Reviews = new ArrayList<Review>(0);
    public float Item_Average_rating;
    public int Item_Total_ratings;
   // public int Item_Quantity = 0;




    public void Display_Item_Detail_all(){
        System.out.println("ITEM DETAILS");
        System.out.println(this.Item_ID);
        System.out.println(this.Item_Name);
        //System.out.println(this.Item_Price);
        System.out.println(this.Item_Description);
        System.out.println(this.Item_Average_rating);
        //Quantity

    }

    public void copy(Item i1){
        this.Item_ID = i1.Item_ID;
        this.Item_Name = i1.Item_Name;
        this.Item_Description = i1.Item_Description;
        //this.Item_Price = i1.Item_Price;
        this.Item_Average_rating = i1.Item_Average_rating;
       // this.Item_Reviews = i1.Item_Reviews;
        this.Item_Total_ratings = i1.Item_Total_ratings;
        this.Category = i1.Category;

    }


    
}
