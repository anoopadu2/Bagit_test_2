package com.example.bagit_test_2.Tools;


import android.app.ProgressDialog;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bagit_test_2.Users.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Vector;


public class FireBase {
    private static final String TAG = "firebase";
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public  DatabaseReference myRef = database.getReference();

    //User_ID Authenticate_User(Email,Password);

    //public String Register_User_Credentials(String email,String password)


    public void Register_User_Details(User u ,String User_ID){
            Save_User(u,User_ID);

    }

   /* public String get_current_User_ID(){
        return "R0002";

    }*/






    public void Save_User(User u,String User_ID){
        myRef.child("Users").child(u.Type+"s").child(User_ID).setValue(u);

    }

    public void Get_User_Type( String User_ID, Custom_Activity a, ProgressDialog progressBar){
        a.counter++;

        myRef.child("Users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting all User data", task.getException());
                    System.out.println("Error");

                } else {
                    Log.d("firebase", "Successfully retrieved all  User data");
                    System.out.println("Passed");

                    //Check in customers
                    DataSnapshot dc = task.getResult().child("Customers");
                    System.out.println("Checking customers");
                    for( DataSnapshot d : dc.getChildren()){
                        if(d.getKey().toString().equals(User_ID)){

                            System.out.println("Matched as customer");
                            a.counter--;
                            if(a.counter ==0){
                                a.run2(progressBar,"Customer");}
                            return;
                        }
                    }
                    //Check in retailers
                    DataSnapshot dr = task.getResult().child("Retailers");
                    System.out.println("Checking retailers");
                    for( DataSnapshot d : dr.getChildren()) {
                        if (d.getKey().toString().equals(User_ID)) {
                            System.out.println("Matched as Retailer");
                            a.counter--;
                            if (a.counter == 0) {
                                a.run2(progressBar, "Retailer");
                            }
                            return;
                        }
                    }
                    //Check in wholesalers
                    DataSnapshot dw = task.getResult().child("Wholesalers");
                        System.out.println("Checking wholesalers");
                    for( DataSnapshot d : dw.getChildren()){
                        if(d.getKey().toString().equals(User_ID)){
                            System.out.println("Matched as Wholesaler");
                            a.counter--;
                            if(a.counter ==0){
                                a.run2(progressBar,"Wholesaler");}
                            return;
                        }
                    }




                }
            }
        });

        return;
    }

    public void Get_User_PhNumber( String User_ID, Custom_Activity a, ProgressDialog progressBar){
        a.counter++;

        myRef.child("Users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting all User data", task.getException());
                    System.out.println("Error");

                } else {
                    Log.d("firebase", "Successfully retrieved all  User data");
                    System.out.println("Passed");

                    //Check in customers
                    DataSnapshot dc = task.getResult().child("Customers");
                    System.out.println("Checking customers");
                    for( DataSnapshot d : dc.getChildren()){
                        if(d.getKey().toString().equals(User_ID)){

                            System.out.println("Matched as customer");
                            String phno = d.child("Phone_number").getValue().toString();
                            System.out.println(phno);

                            a.counter--;
                            if(a.counter ==0){
                                a.run2(progressBar,phno);}
                            return;
                        }
                    }
                    //Check in retailers
                    DataSnapshot dr = task.getResult().child("Retailers");
                    System.out.println("Checking retailers");
                    for( DataSnapshot d : dr.getChildren()) {
                        if (d.getKey().toString().equals(User_ID)) {
                            System.out.println("Matched as Retailer");
                            String phno = d.child("Phone_number").getValue().toString();
                            System.out.println(phno);

                            a.counter--;
                            if(a.counter ==0){
                                a.run2(progressBar,phno);}
                            return;
                        }
                    }
                    //Check in wholesalers
                    DataSnapshot dw = task.getResult().child("Wholesalers");
                    System.out.println("Checking wholesalers");
                    for( DataSnapshot d : dw.getChildren()){
                        if(d.getKey().toString().equals(User_ID)){
                            System.out.println("Matched as Wholesaler");
                            String phno = d.child("Phone_number").getValue().toString();
                            System.out.println(phno);

                            a.counter--;
                            if(a.counter ==0){
                                a.run2(progressBar,phno);}
                            return;
                        }
                    }




                }
            }
        });

        return;
    }






    public void Load_User(User user, String User_ID, Custom_Activity a, ProgressDialog progressBar , String Utype){
        //User C = new User();
        a.counter++;




         myRef.child("Users").child(Utype+ "s").child(User_ID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting User data", task.getException());
                    System.out.println("Error");

                } else {
                    Log.d("firebase", "Successfully retrieved User data");
                    System.out.println("Passed");
                    DataSnapshot d = task.getResult();

                    User c = d.getValue(User.class);
                    //c.cart.Items_In_Cart = (Vector<Cart_Item>) c.cart.Items_In_Cart;
                    System.out.println(d.toString());

                    user.copy(c);
                    a.counter--;
                    if(a.counter ==0){
                    a.run(progressBar);}

                }
            }
        });
         return ;

        }

    public User Load_User(String User_ID,String User_type){
        User U = new User();

        myRef.child("Users").child(User_type+"s").child(User_ID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting customer data", task.getException());
                    System.out.println("Error");

                } else {
                    Log.d("firebase", "Successfully retrieved customer data");
                    System.out.println("Passed");
                    DataSnapshot d = task.getResult();

                    User u = d.getValue(User.class);

                    U.copy(u);

                }
            }
        });
        return U;

    }



    public void Load_Item(String Item_ID,Item I, Custom_Activity a, ProgressDialog progressBar){
        a.counter++;

        myRef.child("Items").child(Item_ID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting customer data", task.getException());
                    System.out.println("Error");

                } else {
                    Log.d("firebase", "Successfully retrieved item data");

                    DataSnapshot d = task.getResult();

                    Item i= d.getValue(Item.class);
                    System.out.println(i.Item_Name);

                    I.copy(i);
                    a.counter--;
                    if(a.counter ==0){
                        a.run(progressBar);
                    }

                }
            }
        });
        return ;

    }

    public void Load_Items_List(ArrayList<Item> Items, Custom_Activity a, ProgressDialog progressBar){
        //Vector<Item> Items = new Vector<Item>(0);
       // CountDownLatch done = new CountDownLatch(1);
        //AppCompatActivity ap = a;
        a.counter++;

        myRef.child("Items").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting items data", task.getException());
                    System.out.println("Error");

                } else {
                    Log.d("firebase", "Retrieved items successfully");
                    System.out.println("Passed");


                    Vector<Item> I = new Vector<Item>(0);
                    for(DataSnapshot d : task.getResult().getChildren()){



                        Item i =   d.getValue(Item.class);
                       // Log.d("firebase", i.Item_Name);
                        I.add(i);

                    }

                    for(int j =0; j < I.size();j++){
                        Items.add(I.get(j));
                        //Log.d("items", I.get(j).Item_Name);
                    }
                    System.out.println(Items.size());
                    for(int j =0; j < Items.size();j++){
                       // All_Items_List.add(VI.get(j));
                       // Log.d("items2", Items.get(j).Item_Name);
                       // System.out.println(Items.get(j).Item_Name);
                    }
                   // done.countDown();





                    a.counter--;
                    if(a.counter==0){
                    a.run(progressBar);}



                    return;



                }
            }


        });
       /* try {
            done.await(); //it will wait till the response is received from firebase.
        } catch(InterruptedException e) {
            e.printStackTrace();
        }*/


        //try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}

        return ;
    }


    public void Save_Item(Item I,String Item_ID){
        myRef.child("Items").child(Item_ID).setValue(I);
    }



    public void Save_Retailer(User r, String User_ID){
        myRef.child("Users").child("Retailers").child(User_ID).setValue(r);

    }

    public void Load_Retailers_List(ArrayList<User> retailers, Custom_Activity a, ProgressDialog progressBar){

        retailers.clear();
        a.counter++;
        myRef.child("Users").child("Retailers").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting retailers data", task.getException());
                    System.out.println("Error");

                } else {
                    Log.d("firebase", "Retrieved retailers data successfully");
                    System.out.println("Passed");

                    for(DataSnapshot d : task.getResult().getChildren()){


                        User r = d.getValue(User.class);
                        retailers.add(r);


                    }


                    for(int j =0; j < retailers.size();j++){
                        // All_Items_List.add(VI.get(j));
                        Log.d("sellers", retailers.get(j).Name);
                        //System.out.println();
                    }

                    a.counter--;
                    if(a.counter==0){ a.run(progressBar);}


                    return;



                }
            }
        });

        return ;
        //return a vector of all seller objects
    }

    public void Load_Wholesalers_List(ArrayList<User> sellers, Custom_Activity a, ProgressDialog progressBar){

        sellers.clear();
        a.counter++;
        myRef.child("Users").child("Wholesalers").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting wholesalers data", task.getException());
                    System.out.println("Error");

                } else {
                    Log.d("firebase", "Retrieved wholesalers data successfully");
                    System.out.println("Passed");

                    for(DataSnapshot d : task.getResult().getChildren()){


                        User w = d.getValue(User.class);
                        sellers.add(w);


                    }


                    for(int j =0; j < sellers.size();j++){
                        // All_Items_List.add(VI.get(j));
                        Log.d("sellers", sellers.get(j).Name);
                        //System.out.println();
                    }

                    a.counter--;
                    if(a.counter==0){ a.run(progressBar);}


                    return;



                }
            }
        });

        return ;
        //return a vector of all seller objects
    }










  //
}
