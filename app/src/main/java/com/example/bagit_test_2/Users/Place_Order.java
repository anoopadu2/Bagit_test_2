package com.example.bagit_test_2.Users;

public class Place_Order {

    User U;
    boolean Payment_Status = false;
    boolean Order_Confirmed = false;
    public Place_Order(User u){
        U =u;
    }

    public void place_order_now(){
        Show_Order_Summary();
        Show_Payment_Options();
        Make_Payment();
        Confirm_Order();
       // Update_User_Orders();
       //Update_Seller_Order_Requests();
    }

    
    
    public void Show_Order_Summary(){
        System.out.println(U.Name);
        System.out.println(U.Phone_number);
        U.address.Show_Address();
        U.cart.Display_cart();

    }

    public void Show_Payment_Options()
    {
        System.out.println("PayTm");
        System.out.println("Gpay");
        System.out.println("COD");
        System.out.println();

    }
    
    

    public void Make_Payment(){
        Payment_Status = true;
        System.out.println("PAYMENT SUCCESSFULL");

    }

    void Confirm_Order(){
        if(Payment_Status){

            Order_Confirmed = true;
            System.out.println("ORDER CONFIRMED");

        }

    } 

    /*void Update_User_Orders(){
        int n =12345;
        for(int i =0; i < U.cart.Total_Orders;i++){
        Order order = new Order();
        order.Order_Number = n+i;
        order.Order_Placed_On = "4-4-21"; //Current date
        order.Order_Estimated_Delivery_On = "5-5-21"; //+ days to deliver
      //  order.Item_Ordered = U.cart.Items_In_Cart.get(i);
        U.orders.add(order);

        }



    }
    // Notify_User()

*/
    
}
