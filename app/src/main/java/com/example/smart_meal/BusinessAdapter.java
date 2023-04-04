package com.example.smart_meal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter {
    List<OrderModel> mData;
    LayoutInflater mInflater;
    ItemClickListener itemCLickListener;

    public BusinessAdapter (Context context, List<OrderModel> mData){
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_checkbox,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).orderID.setText(mData.get(position).getOrderID());
        ((ViewHolder)holder).orderStatus.setText(mData.get(position).getOrderStatus());
        ((ViewHolder)holder).orderDate.setText(mData.get(position).getDate());
        ((ViewHolder)holder).customerID.setText(mData.get(position).getCustomerID());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    void setCLickListener(ItemClickListener mItemClickListener){
        itemCLickListener = mItemClickListener;
    }

    public interface ItemClickListener{void onItemClick(View view, int position);}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView orderID;
        TextView orderStatus;
        TextView orderDate;
        TextView customerID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderID = itemView.findViewById(R.id.bOrderID);
            orderStatus = itemView.findViewById(R.id.bStatusID);
            orderDate = itemView.findViewById(R.id.bDate);
            customerID = itemView.findViewById(R.id.bCustID);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemCLickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
