package com.example.bagit_test_2.Adapters;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bagit_test_2.Cart_Page;
import com.example.bagit_test_2.R;
import com.example.bagit_test_2.Tools.Add_Data;
import com.example.bagit_test_2.Tools.Custom_Activity;
import com.example.bagit_test_2.Tools.FireBase;
import com.example.bagit_test_2.Users.Cart_Item;
import com.example.bagit_test_2.Users.User;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.HolderCart> {

    public Context context;
    public ArrayList<Cart_Item> Cart_Item_list;
    public User U;
    int total = 0;
    public FireBase fb = new FireBase();
    public AdapterCart adapter;

    public AdapterCart(Context context, ArrayList<Cart_Item> cartList, User u) {
        this.context = context;
        this.Cart_Item_list = cartList;
        this.U = u;
        this.adapter = this;
    }

    @NonNull
    @Override
    public HolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_product_cart, parent, false);
        return new HolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCart holder, int position) {

        Cart_Item cart_item = Cart_Item_list.get(position);
        final String title = cart_item.item.Item_Name;
        String category = cart_item.Seller_Name;
        //String itemImage = cart_item.getProfileImage();
        String quantity = String.valueOf( cart_item.Quantity);
        String actualPrice = String.valueOf(cart_item.price);
        String finalPrice = String.valueOf(cart_item.price * cart_item.Quantity);
        String discountPercent = "";
        final String productId = cart_item.item.Item_ID;
        final String shopId =  cart_item.Seller_ID;

        holder.titleTv.setText(title);
        holder.categoryTv.setText(category);
        holder.orignalPriceTv.setText(actualPrice);
        holder.discountPriceTv.setText(finalPrice);
        holder.quantityTv.setText(quantity);
        holder.discountPercentTv.setText(discountPercent);
        if (false){
            holder.discountPercentTv.setVisibility(View.VISIBLE);
            holder.discountPriceTv.setVisibility(View.VISIBLE);
            holder.orignalPriceTv.setPaintFlags(holder.orignalPriceTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            holder.discountPercentTv.setVisibility(View.GONE);
            holder.discountPriceTv.setVisibility(View.GONE);
            holder.orignalPriceTv.setTextColor(Color.BLACK);
            holder.orignalPriceTv.setTextSize(20);
        }
        try {

            String resourceName = "i" + cart_item.item.Item_ID.substring(1);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("DELETE")
                        .setMessage("Sure want to delete product " + title + "?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              U.cart.Remove_from_Cart(cart_item);
                              fb.Save_User(U, ((Cart_Page)context).Current_User_ID);
                                Toast.makeText(context, "Product Deleted...", Toast.LENGTH_SHORT).show();


                                adapter.notifyDataSetChanged();
                                ((Cart_Page)context).update_cart_values();


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
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
        return Cart_Item_list.size();
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
