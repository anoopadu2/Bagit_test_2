package com.example.bagit_test_2.Users;

import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Tools.*;
import com.example.bagit_test_2.Users.Item;
import com.example.bagit_test_2.Users.User;
import com.example.bagit_test_2.Users.User.*;

public class Order_Request_Item {
    public String Order_ID;
    public String Buyer_ID;
    public String Buyer_Name;
    public Item Item_Ordered;
    public int Item_Quantity;
    public float Item_total_Price;
    public String Order_Placed_On;
    public String Order_Status;
    public String Delivered_by;
    public String Order_Estimated_Delivery_By;

    public void Show_Order_Request_Details(){
        System.out.println("Order ID : "  + Order_ID );
        System.out.println("Buyer Name : "  + Buyer_Name );
        System.out.println("Buyer ID : "  + Buyer_ID );
        System.out.println("Item Ordered: "  + Item_Ordered.Item_Name );
        System.out.println("Item Quantity : "  + Item_Quantity );
        System.out.println("Item_total_Price : "  + Item_total_Price );
    }

    /*public void Update_Order_Status(FireBase fb,String Status,String Buyer_ID) {
        //User b = fb.Load_Customer(Buyer_ID);
        b.MyOrders.Update_Order_Status(Order_ID,Status);

    }*/


}

