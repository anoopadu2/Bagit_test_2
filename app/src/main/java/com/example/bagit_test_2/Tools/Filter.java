package com.example.bagit_test_2.Tools;

import com.example.bagit_test_2.Users.Item;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;

public class Filter {

   public static void  filter_By_Category(ArrayList<Item> all_items_list, ArrayList<Item> filtered_items_list , String Category){
        filtered_items_list.clear();
        for(int i =0 ; i < all_items_list.size();i++){
            if(all_items_list.get(i).Category.equals(Category) ){
                filtered_items_list.add(all_items_list.get(i));
            }
        }
    }

    public static void Search_By_Name(ArrayList<Item> filtered_items_list ,String Item_Name){
       for(int i =0; i < filtered_items_list.size();i++){
           if(filtered_items_list.get(i).Item_Name.toLowerCase().equals(Item_Name.toLowerCase())){
               Item p = filtered_items_list.get(i);
               filtered_items_list.clear();
               filtered_items_list.add(p);
               return;
           }
       }
        filtered_items_list.clear();
       return;
    }

    public static void filter_by_quantity(ArrayList<User> seller_list_all, ArrayList<User> filtered_seller_list,ArrayList<String> seller_list_dropdown, String Item_ID,int quantity){
       filtered_seller_list.clear();
       seller_list_dropdown.clear();
       for(int i =0; i < seller_list_all.size();i++){
           for(int j =0 ; j < seller_list_all.get(i).MyCatalogue.catalog.size();j++){
               if(seller_list_all.get(i).MyCatalogue.catalog.get(j).Item_ID.equals(Item_ID)){
                   if(seller_list_all.get(i).MyCatalogue.catalog.get(j).Quantity >= quantity){
                       filtered_seller_list.add(seller_list_all.get(i));
                       String row = "Rs." + seller_list_all.get(i).MyCatalogue.catalog.get(j).Price + " - " + seller_list_all.get(i).Seller_Name + " , " + seller_list_all.get(i).address.add_line_2;
                       seller_list_dropdown.add(row);
                   }
               }
           }
       }
       if(seller_list_dropdown.size()==0){
           seller_list_dropdown.add("No sellers available");
       }
       return;
    }




    //void Sort_By_Price(Item_List_all )

    //void Search_for_Item(Item_List_all , Item_Name);
}
