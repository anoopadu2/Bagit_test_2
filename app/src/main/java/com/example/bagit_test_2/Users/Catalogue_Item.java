package com.example.bagit_test_2.Users;

public class Catalogue_Item {
    public Item item;
    public String Item_ID;
    public int Quantity;
    public float Price;

    public void Show_Details(){
        System.out.println("Item Name : " + item.Item_Name);
        System.out.println("Item Quantity : " + Quantity);
        System.out.println("Item Price : " + Price);

    }

}

