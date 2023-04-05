package com.example.smart_meal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerSearchAdapter extends RecyclerView.Adapter<CustomerSearchAdapter.MyViewHolder> {
    Context context;
    ArrayList<CustomerModel> businessList;
    private final CustomerSearchRecyclerViewInterface customerSearchRecyclerViewInterface;

    public CustomerSearchAdapter (Context context, ArrayList<CustomerModel> businessList, CustomerSearchRecyclerViewInterface customerSearchRecyclerViewInterface) {
        this.context = context;
        this.businessList = businessList;
        this.customerSearchRecyclerViewInterface = customerSearchRecyclerViewInterface;
    }

    @NonNull
    @Override
    public CustomerSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customer_search_recyclerview_row, parent, false);
        return new CustomerSearchAdapter.MyViewHolder(view, customerSearchRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerSearchAdapter.MyViewHolder holder, int position) {
        holder.businessName.setText(businessList.get(position).getCustomerName());
        holder.businessCity.setText(businessList.get(position).getCustomerCity());
        holder.businessAddress.setText(businessList.get(position).getCustomerAddress());
        holder.businessImg.setImageResource(businessList.get(position).getCustomerImage());
    }

    @Override
    public int getItemCount() {
        return businessList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView businessImg;
        TextView businessName, businessCity, businessAddress;

        public MyViewHolder(@NonNull View itemView, CustomerSearchRecyclerViewInterface customerSearchRecyclerViewInterface) {
            super(itemView);
            businessImg = itemView.findViewById(R.id.customerSeacrhBusinessImage);
            businessName = itemView.findViewById(R.id.customerSearchBusinessName);
            businessCity = itemView.findViewById(R.id.customerSearchBusinessCity);
            businessAddress = itemView.findViewById(R.id.customerSearchBusinessAddress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (customerSearchRecyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            customerSearchRecyclerViewInterface.onSelectCustomerSearch(position);
                        }
                    }
                }
            });
        }
    }
}
