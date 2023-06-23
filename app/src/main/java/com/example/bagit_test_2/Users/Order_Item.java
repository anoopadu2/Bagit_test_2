package com.example.bagit_test_2.Users;

public class Order_Item {

    public String Order_ID;
    public String Order_Placed_On;
    public String Order_Estimated_Delivery_By;
    public String Order_Delivered_on;
    public Cart_Item Item_Ordered;
    public String Order_Status = "Order Placed Successfully!";
    public String Seller_ID;
    public String Seller_Name;
    public boolean rating_given;
    public boolean delivered;
    public float user_rating;
    public float delivery_rating;
    public String Delivered_by;




    public void Show_Order_Details() {
        System.out.println("Order_ID"  + Order_ID);
        System.out.println("Item Name"  + Item_Ordered.item.Item_Name);
        System.out.println("Item Quantity"  + Item_Ordered.Quantity);
        System.out.println("Order_Placed_On"  +Order_Placed_On);
        System.out.println("Order_Estimated_Delivery_By"  +Order_Estimated_Delivery_By);
        System.out.println("Order_Status"  + Order_Status);
        System.out.println("Seller Name"  + Seller_Name);
        //Item_Ordered.Display_Item_Detail_Cart();
        System.out.println();


    }
}
