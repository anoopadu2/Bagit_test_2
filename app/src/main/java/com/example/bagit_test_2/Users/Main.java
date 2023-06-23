package com.example.bagit_test_2.Users;
import java.util.Vector;

public class Main {
    

    public static User get_dummy_user(){
        User u1 = new User();
        u1.Name = "Vishnu Vardhan";
        u1.Email = "vishnuvardhan7373@gmail.com";
        u1.Phone_number = "999999999";
        u1.Type = "Customer";
        u1.address.add_line_1 = "H.No 123";
        u1.address.add_line_2 = "ABC Colony";
        u1.address.City = "hyderabad";
        u1.address.State = "Telangana";
        u1.address.PinCode = "500090";


        return u1;


    }
    public static Item Get_Test_Item(String IN,String IID){
        Item I = new Item();
        I.Item_ID =IID;
        I.Item_Name = IN;
        //I.Item_Price = (float)IP;
        I.Item_Average_rating = (float)4.3;
        I.Item_Description = "Item is " + I.Item_Name;

        return I;


    }


    public static void main(String args[]){
        
        /*User u1 = get_dummy_user();
        u1.Show_Details();
        u1.Show_Cart();
        Vector<Item> I = new Vector<Item>(0);
        I.add(Get_Test_Item("Chocolate", 10,"1"));
        I.add(Get_Test_Item("Lays", 15,"2"));
        I.add(Get_Test_Item("Coke",25,"3"));
        

     for(Item i : I){
        u1.cart.Add_To_Cart(i);

       }
        
        u1.Show_Cart();



    }*/
        return;
    
}}
