package com.example.bagit_test_2.Users;

import java.util.ArrayList;
import java.util.Vector;


public class Cart {
    public int Total_Orders =0;
    public float Total_Price =0;
    public float delivery_fee = 0;
    public ArrayList<Cart_Item> Items_In_Cart = new ArrayList<Cart_Item>(0);


    public void update_cart(){
        Total_Orders = Items_In_Cart.size();
        Total_Price =0;

        for(int i =0; i < Items_In_Cart.size();i++){
            Total_Price = Total_Price + Items_In_Cart.get(i).price;
            
        }
    }
    public void Add_To_Cart(Cart_Item I){
        for(int i =0; i < Items_In_Cart.size();i++){
            if((Items_In_Cart.get(i).item.Item_ID.equals(I.item.Item_ID)) && (Items_In_Cart.get(i).Seller_ID.equals(I.Seller_ID)) ){
                Items_In_Cart.get(i).Quantity = I.Quantity + Items_In_Cart.get(i).Quantity;
                update_cart();
                return;
            }
        }
        
        Items_In_Cart.add(I);
        //Items_In_Cart.get(Items_In_Cart.size()-1).Quantity ++;
        
        
        update_cart();
        return;
    }
    public void Remove_from_Cart(Cart_Item I){

        for(int i =0; i < Items_In_Cart.size();i++){
            if(Items_In_Cart.get(i).item.Item_ID == I.item.Item_ID){
                Items_In_Cart.remove(i);
                /*
                Items_In_Cart.get(i).Quantity --;
                if(Items_In_Cart.get(i).Quantity ==0){
                    Items_In_Cart.remove(i);
                }*/
                update_cart();
                return;
            }
        }
    }
    public void Display_cart(){
        System.out.println("USER CART");
        System.out.println("Total_Orders = " + Total_Orders );
        int i =1;
        for(Cart_Item I : Items_In_Cart){
            System.out.print(i + ". ");
            System.out.println();
            I.Display_Details();
            //System.out.println("---");
            i++;

        }
        System.out.println("Total_Price = " + Total_Price );
        System.out.println();
        

    }
    public float get_total(){
        float total =0;
        for(Cart_Item I : Items_In_Cart){
           total = total + I.Quantity*I.price;

        }
        return total;

    }


    
}
