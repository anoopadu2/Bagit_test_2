package com.example.bagit_test_2.Users;
import java.util.Vector;

public class User{
   
    public String Name = new String("Default Name");
    public String ID ;
    public String Email = new String("Default Email");
    public String Phone_number = new String("Default phone number");
    public Address address = new Address();
    public String Type = new String("Customer");
    //public Vector<Order> orders = new Vector<Order>(0);
    public Cart cart = new Cart();
    public Order MyOrders = new Order();
    public Order_Request MyOrderRequests = new Order_Request();
    public Catalogue MyCatalogue = new Catalogue();


    public String Seller_Name;
    public Location location = new Location();



    //Location



    public void Show_Details(){
        System.out.println("USER DETAILS : ");
        System.out.println("Name = " + this.Name);
        System.out.println("Email = " + this.Email);
        System.out.println("Phone Number = " + this.Phone_number);
        System.out.println("Type = " + this.Type);
        this.address.Show_Address();
        System.out.println();

    }

    public void Show_Cart(){
        this.cart.Display_cart();
    }

    public void copy(User u1){
        this.Name = u1.Name;
        this.Email = u1.Email;
        this.Phone_number = u1.Phone_number;
        this.Type = u1.Type;
        this.address = u1.address;
        this.cart = u1.cart;
        this.MyOrders = u1.MyOrders;
        this.ID = u1.ID;
        this.MyCatalogue = u1.MyCatalogue;
        this.MyOrderRequests = u1.MyOrderRequests;
        this.Seller_Name = u1.Seller_Name;
        this.location = u1.location;
        //this.My = u1.orders;
    }
}