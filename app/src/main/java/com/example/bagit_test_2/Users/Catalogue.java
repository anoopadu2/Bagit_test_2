package com.example.bagit_test_2.Users;

import java.util.ArrayList;

public class Catalogue {
    public ArrayList<Catalogue_Item> catalog = new ArrayList<Catalogue_Item>(0);

    //c

    public void Add_Item_To_Catalog(String Item_ID, int q,float p,Item I){
        for(int i = 0; i < catalog.size(); i++){
            if(catalog.get(i).Item_ID.equals(Item_ID)){
                catalog.get(i).Quantity = catalog.get(i).Quantity+ q;
                catalog.get(i).Price = p;
                return;
            }
        }
        Catalogue_Item ci = new Catalogue_Item();
        ci.item = I;
        ci.Price = p;
        ci.Quantity = q;
        ci.Item_ID = Item_ID;
        catalog.add(ci);
        return;



    }
    public void Set_Item_To_Catalog(String Item_ID, int q,float p){
        for(int i = 0; i < catalog.size(); i++){
            if(catalog.get(i).Item_ID.equals(Item_ID)){
                catalog.get(i).Quantity = + q;
                catalog.get(i).Price = p;
                return;
            }
        }
        Catalogue_Item ci = new Catalogue_Item();
        ci.item = new Item();
        ci.Price = p;
        ci.Quantity = q;
        ci.Item_ID = Item_ID;
        catalog.add(ci);
        return;



    }
    public void Decrement_Item_In_Catalog(String Item_ID, int q){
        for(int i = 0; i < catalog.size(); i++){
            if(catalog.get(i).Item_ID.equals(Item_ID)){
                catalog.get(i).Quantity =catalog.get(i).Quantity - q;
                //catalog.get(i).Price = p;
                if(catalog.get(i).Quantity <= 0){
                    remove_item(catalog.get(i));
                }
                return;
            }
        }

        return;



    }

    public void remove_item(Catalogue_Item ci){
        catalog.remove(ci);
    }


    public float get_price(String Item_ID){
        for(int i=0 ; i < catalog.size();i++){
            if(catalog.get(i).Item_ID.equals(Item_ID)){
                return catalog.get(i).Price;
            }
        }
        return (float) 0.0;
    }


    public void Show_My_Catalog(){
        for(int i = 0; i < catalog.size(); i++){
          catalog.get(i).Show_Details();
        }
        System.out.println();
    }
    //Delete Item
}
