package com.example.bagit_test_2.Users;
import com.example.bagit_test_2.Users.User.*;

import com.example.bagit_test_2.Tools.FireBase;

import java.util.ArrayList;
import java.util.Vector;

public class Order_Request {

    public ArrayList<Order_Request_Item> OrderRequests = new ArrayList<Order_Request_Item>(0);

    public void Add_Order_Request(Order_Request_Item ORI){
        OrderRequests.add(ORI);
    }

    public void Show_Order_Requests(){
        for(int i =0; i< OrderRequests.size();i++){
            OrderRequests.get(i).Show_Order_Request_Details();
        }
    }

    public void Update_Order_Request_Status(FireBase fb, String status, String Order_ID){
        for(int i =0; i< OrderRequests.size();i++){
            if(OrderRequests.get(i).Order_ID.equals(Order_ID)){
                //OrderRequests.get(i).Update_Order_Status(fb,status);
            }
        }
    }

    public Order_Request_Item Get_Order_Request(String Order_ID){
        for(int i =0; i< OrderRequests.size();i++){
            if(OrderRequests.get(i).Order_ID.equals(Order_ID)){
                return OrderRequests.get(i);
            }
        }
        return new Order_Request_Item();

    }



    // Delete Request


}
