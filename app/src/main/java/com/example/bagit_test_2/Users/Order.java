package com.example.bagit_test_2.Users;

import java.util.ArrayList;
import java.util.Vector;

public class Order {

    public ArrayList<Order_Item> Orders = new ArrayList<>(0);


    public void Update_Order_Status(String Order_ID,String status) {

        for(int i =0 ; i < Orders.size();i++){
            if(Orders.get(i).Order_ID.equals(Order_ID)){
                Orders.get(i).Order_Status = status;
            }
        }
    }

    public void Show_All_Orders(){
        for(int i =0 ; i < Orders.size();i++){
            Orders.get(i).Show_Order_Details();
            System.out.println();
        }
    }
    public Order_Item get_order_item(String Order_ID){
        for(int i =0 ; i < Orders.size();i++){
           if(Orders.get(i).Order_ID.equals(Order_ID)){
               return Orders.get(i);
           }
        }
        return new Order_Item();
    }


    public void Add_Order(Order_Item OI){
        Orders.add(OI);
    }
    //Delete Order



    
}
