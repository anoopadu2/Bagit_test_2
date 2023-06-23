package com.example.bagit_test_2.Adapters;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bagit_test_2.Dashboard_Customer;
import com.example.bagit_test_2.MainActivity2;
import com.example.bagit_test_2.Order_Detail_Page;
import com.example.bagit_test_2.Order_Request_Details_Page;
import com.example.bagit_test_2.Order_Requests_Page;
import com.example.bagit_test_2.R;
import com.example.bagit_test_2.Tools.Add_Data;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Order_Item;
import com.example.bagit_test_2.Users.Order_Request_Item;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;

public class AdapterOrderRequest extends RecyclerView.Adapter<AdapterOrderRequest.HolderCart> {

    public Context context;
    public ArrayList<Order_Request_Item> order_request_list, filtered_items_list;
    public User U;
    public FireBase fb = new FireBase();
    public AdapterOrderRequest adapter;

    public AdapterOrderRequest(Context context, ArrayList<Order_Request_Item> itemList, User u) {
        this.context = context;
        this.order_request_list = itemList;
        this.U = u;
        this.adapter = this;
    }

    @NonNull
    @Override
    public HolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_order_request, parent, false);
        return new HolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCart holder, int position) {

        Order_Request_Item order_request_item = order_request_list.get(position);
        final String title = order_request_item.Item_Ordered.Item_Name;
        String category = "Ordered by : " + order_request_item.Buyer_Name;
        //String itemImage = cart_item.getProfileImage();
        String quantity = "";
        String actualPrice = "";
        String finalPrice = Float.toString(order_request_item.Item_Quantity);
        String discountPercent = Float.toString(order_request_item.Item_total_Price);
        final String productId = order_request_item.Order_ID;
        //final String shopId =  item.Seller_ID;

        holder.titleTv.setText(title);
        holder.categoryTv.setText(category);
        holder.orignalPriceTv.setText(finalPrice);
        holder.discountPriceTv.setText("Rs. " +discountPercent  );
        holder.quantityTv.setText(quantity);
        holder.discountPercentTv.setText(order_request_item.Order_Placed_On);

        /*if (false){
            holder.discountPercentTv.setVisibility(View.VISIBLE);
            holder.discountPriceTv.setVisibility(View.VISIBLE);
            holder.orignalPriceTv.setPaintFlags(holder.orignalPriceTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            holder.discountPercentTv.setVisibility(View.GONE);
            holder.discountPriceTv.setVisibility(View.GONE);
            holder.orignalPriceTv.setTextColor(Color.BLACK);
            holder.orignalPriceTv.setTextSize(20);
        }*/
        try {

            String resourceName = "i" + order_request_item.Item_Ordered.Item_ID.substring(1);
            System.out.println("Resource is " + resourceName);
            int resID = Add_Data.getResId(resourceName, R.drawable.class);
            holder.productIconIv.setImageResource(resID);
        }
        catch (Exception e){
            holder.productIconIv.setImageResource(R.drawable.ic_cart_primary);
        }

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent I = new Intent(context, Order_Request_Details_Page.class);
                I.putExtra("Current_User_ID", ((Order_Requests_Page)context).Current_User_ID );
                I.putExtra("Current_User_Type", ((Order_Requests_Page)context).Current_User_Type);
                I.putExtra("Order_ID", order_request_item.Order_ID);
                I.putExtra("Buyer_ID", order_request_item.Buyer_ID);
                I.putExtra("Item_ID", order_request_item.Item_Ordered.Item_ID);
                context.startActivity(I);

                return;

                //Go to order page

                /*Intent I = new Intent(context, Product_Details.class);
                I.putExtra("Item_ID", order_item.Item_ID);
                context.startActivity(I);*/

                /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("DELETE")
                        .setMessage("Sure want to delete product " + title + "?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent I = new Intent(context, Product_Details.class);
                                context.startActivity(I);


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();*/
            }
        });
    }

    /*private void deleteProduct(String id, String shopId){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(mAuth.getUid()).child("CartItem").child(shopId).child(id).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Product Deleted...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }*/

    @Override
    public int getItemCount() {
        return order_request_list.size();
    }

    class HolderCart extends RecyclerView.ViewHolder{

        private ImageView productIconIv;
        private TextView titleTv, categoryTv, discountPriceTv, orignalPriceTv, discountPercentTv, quantityTv;
        private ImageButton deleteBtn;

        public HolderCart(@NonNull View itemView) {
            super(itemView);

            productIconIv = itemView.findViewById(R.id.productIconIV);
            titleTv = itemView.findViewById(R.id.titleTV);
            categoryTv = itemView.findViewById(R.id.categoryTV);
            discountPriceTv = itemView.findViewById(R.id.discountPriceTV);
            orignalPriceTv = itemView.findViewById(R.id.orignalPriceTV);
            discountPercentTv = itemView.findViewById(R.id.t_rating);
            quantityTv = itemView.findViewById(R.id.quantityTV);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }

}
