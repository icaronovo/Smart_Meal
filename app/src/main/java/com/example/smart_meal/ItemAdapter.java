package com.example.smart_meal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter {
    List<CustomerModel> itemList;
    LayoutInflater mInflater;
    ItemClickListener itemClickListener;

    public ItemAdapter(Context context, List<CustomerModel> itemList) {
        this.itemList = itemList;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textNameRestaurant.setText(itemList.get(position).getCustomerName());
        String nameWithCity = itemList.get(position).getCustomerCity() + " - " + itemList.get(position).getCustomerProvince();
        ((ViewHolder) holder).textCityAndProvince.setText(nameWithCity);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    void setClickListener(ItemClickListener mItemClickListener) {
        itemClickListener = mItemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView textNameRestaurant;
        TextView textCityAndProvince;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.leftImage);
            textNameRestaurant = itemView.findViewById(R.id.txtTitleRestaurant);
            textCityAndProvince = itemView.findViewById(R.id.txtCityAndProvince);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
