package com.example.bagit_test_2.Tools;

import com.example.bagit_test_2.Users.Catalogue_Item;
import com.example.bagit_test_2.Users.Item;
import com.example.bagit_test_2.Users.Main;
import com.example.bagit_test_2.Users.User;

import java.lang.reflect.Field;

public class Add_Data {

    public FireBase fb = new FireBase();
    public Main m = new Main();

    public void  Add_Retailer(){

        int i =1;

        User u ;
        u= new User();
        u.Name = "Jeff";
        u.Phone_number = "949052391" + String.valueOf(i);
        u.Email = u.Name + "123@gmail.com";
        u.address.add_line_1 = "xyz colony";
        u.address.add_line_2 = "Kukatpally";
        u.address.City = "Hyderabad";
        u.address.State = "Telangana";
        u.address.PinCode = "50009" + String.valueOf(i);
        u.Type = "Retailer";
        u.Seller_Name = u.Name + "-sales, Pvt Ltd.";
        u.ID = "R000" + String.valueOf(i);


        //Item item = m.Get_Test_Item("Dairy Milk", "I0001");
       // u.MyCatalogue.Add_Item_To_Catalog("I0001",15, (float) 11.0);

         //item = m.Get_Test_Item("Lays", "I0002");
      //  u.MyCatalogue.Add_Item_To_Catalog("I0002",15, (float) 10.5);

         //item = m.Get_Test_Item("Pepsi", "I0003");
       // u.MyCatalogue.Add_Item_To_Catalog("I0003",13, (float) 21.0);


       // fb.Save_User(u,u.ID);
        i++;


    }

    public void  Add_Customer(){

        int i =1;

        User u ;
        u= new User();
        u.Name = "Vishnu Vardhan";
        u.Phone_number = "949052391" + String.valueOf(i);
        u.Email = u.Name + "123@gmail.com";
        u.address.add_line_1 = "xyz colony";
        u.address.add_line_2 = "Kukatpally";
        u.address.City = "Hyderabad";
        u.address.State = "Telangana";
        u.address.PinCode = "50009" + String.valueOf(i);
        u.Type = "Customer";
        u.cart.delivery_fee = (float) 10.0;
        //u.Seller_Name = u.Name + "-sales, Pvt Ltd.";
        u.ID = "C000" + String.valueOf(i);




        fb.Save_User(u,u.ID);
        i++;


    }
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void Add_Items(){
        int n = 6;
        for(int i = 10 ; i <=25;i++){
            Item I = new Item();
            I.Item_ID = "I00" + String.valueOf(i);
            I.Item_Name = "Default Name";
            I.Item_Description = "Default Item_Description";
            I.Category = "Default Category";
            I.Item_Total_ratings = 1;
            I.Item_Average_rating = (float) 4.0;
            fb.Save_Item(I,I.Item_ID);



        }
    }

}
