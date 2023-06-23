package com.example.bagit_test_2.Processes;

import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Tools.Notifications;
import com.example.bagit_test_2.Users.Cart_Item;
import com.example.bagit_test_2.Users.Order_Item;
import com.example.bagit_test_2.Users.Order_Request_Item;
import com.example.bagit_test_2.Users.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Place_Order {
    public FireBase fb = new FireBase();
    public void getFutureDate(Date currentDate, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, days);

        Date futureDate = cal.getTime();
        futureDate.toString();
    }


public String get_message(Order_Request_Item or){
        String message = "";
    HashMap<String,User> Delivery_Person_List = Get_Delivery_Person_List();
        message = message + "You have a new order request from ";
            message = message +  or.Buyer_Name;
            message = message + " for " + or.Item_Ordered.Item_Name;
           // message = message + " , Quantity " + or.Item_Quantity;
           // message = message +" , Total price " + String.valueOf(or.Item_total_Price*or.Item_Quantity);
            message = message + ". Please check your order requests for more details. ";
            return  message;


}
    public void Update_Seller_Catalog_Requests(User b , ArrayList<User> Seller_list_all, Custom_Activity a){

        HashMap<String,User> Seller_list = new HashMap<String,User>(0);
        ArrayList<Cart_Item> current_items_in_cart = b.cart.Items_In_Cart;
        HashMap<String,User> Delivery_Person_List = Get_Delivery_Person_List();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        int my_orders_size = b.MyOrders.Orders.size();
        for(int i = 0 ; i < Seller_list_all.size();i++){
            Seller_list.put(Seller_list_all.get(i).ID,Seller_list_all.get(i));
        }
        for(int i =0; i < current_items_in_cart.size();i++) {
            String Seller_ID = current_items_in_cart.get(i).Seller_ID;
            Order_Request_Item or = new Order_Request_Item();
            or.Item_Ordered = current_items_in_cart.get(i).item;
            or.Buyer_ID = b.ID;
            or.Order_Status = "Order Placed successfully";
            or.Buyer_Name = b.Name;
            or.Order_ID =  b.ID + "ON"+ String.valueOf(my_orders_size  + i);
            or.Item_Quantity = current_items_in_cart.get(i).Quantity;
            or.Item_total_Price = current_items_in_cart.get(i).price*current_items_in_cart.get(i).Quantity;
            or.Order_Placed_On = currentDate ;
            or.Order_Estimated_Delivery_By =  "26-04-2021";
            or.Delivered_by = Delivery_Person_List.get(b.address.add_line_2).Name + " ( " + Delivery_Person_List.get(b.address.add_line_2).Phone_number + " )";


            Seller_list.get(Seller_ID).MyOrderRequests.Add_Order_Request(or);
            Seller_list.get(Seller_ID).MyCatalogue.Decrement_Item_In_Catalog(current_items_in_cart.get(i).item.Item_ID,current_items_in_cart.get(i).Quantity);
            System.out.println(Delivery_Person_List.get(b.address.add_line_2).Name + " ( " + Delivery_Person_List.get(b.address.add_line_2).Phone_number + " )");


            fb.Save_User(Seller_list.get(Seller_ID) ,Seller_ID);
            Notifications.send(get_message(or) ,Seller_list.get(Seller_ID).Phone_number,a);

        }

        return;


    }

    public void Update_User_Orders(User b){
        ArrayList<Cart_Item> current_items_in_cart = b.cart.Items_In_Cart;
        HashMap<String,User> Delivery_Person_List = Get_Delivery_Person_List();

       // Order_Request_Item or = new Order_Request_Item();
        String User_ID = b.ID;



        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String after3daysdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        int my_orders_size = b.MyOrders.Orders.size();
        for(int i =0; i < current_items_in_cart.size();i++){
            Order_Item oi = new Order_Item();
            oi.Item_Ordered = current_items_in_cart.get(i);
            oi.Order_Placed_On = currentDate; //current date
            oi.Order_Estimated_Delivery_By = "26-04-2021"; //current date + 3 days
            oi.Order_Status = "Order Placed successfully";
            oi.Order_ID =  b.ID + "ON"+ String.valueOf(my_orders_size  + i);
            oi.Seller_ID = current_items_in_cart.get(i).Seller_ID;
            oi.Seller_Name = current_items_in_cart.get(i).Seller_Name;
            oi.user_rating = (float)0.0;
            oi.rating_given = false;
            oi.delivered = false;
            oi.Delivered_by = Delivery_Person_List.get(b.address.add_line_2).Name + " ( " + Delivery_Person_List.get(b.address.add_line_2).Phone_number + " )";


            System.out.println(Delivery_Person_List.get(b.address.add_line_2).Name + " ( " + Delivery_Person_List.get(b.address.add_line_2).Phone_number + " )");
            b.MyOrders.Orders.add(oi);


        }
        for(int i =0; i < b.MyOrders.Orders.size();i++){
            System.out.println( b.MyOrders.Orders.get(i).Item_Ordered.item.Item_Name);

        }

        b.cart.Items_In_Cart.clear();
        fb.Save_User(b,User_ID);






    }

    public HashMap<String, User> Get_Delivery_Person_List(){
         HashMap<String,User> Delivery_Person_List = new HashMap<String, User>(0);
        User d = new User();
        d.Name = "Mahesh";
        d.Phone_number = "3773564754";
        Delivery_Person_List.put("Kukatpally",d);
        d = new User();
        d.Name = "Arjun";
        d.Phone_number = "3771234754";
        Delivery_Person_List.put("Ameerpet",d);
        d = new User();
        d.Name = "Surya";
        d.Phone_number = "3773556754";
        Delivery_Person_List.put("Amberpet",d);

        return Delivery_Person_List;




    }


}
