package com.example.bagit_test_2.Users;
import com.example.bagit_test_2.Users.*;

public class Cart_Item {
    public String Item_ID;
    public Item item;
    public  int  Quantity =0;
    public String Seller_ID;
    public String Seller_Name;
    public float price; //per quantity

    public void Display_Details(){
        System.out.println("Item ID : " + item.Item_ID);
        System.out.println("Item Name : " + item.Item_Name);
        System.out.println("Item Quantity : " + Quantity);
        System.out.println("Item Price : " + price);
        System.out.println("Seller Name : " + Seller_Name);
    }


}



