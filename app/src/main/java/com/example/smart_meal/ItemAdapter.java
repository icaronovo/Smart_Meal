package com.example.smart_meal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    List<ItemModel> itemList;

    public ItemAdapter(List<ItemModel> itemList){
        this.itemList = itemList;

    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.itemImage.setImageResource(itemList.get(position).getImageItem());
        holder.textTitle.setText(itemList.get(position).getTitleTxt());
        holder.textPrice.setText(itemList.get(position).getTxtPrice());
        holder.textDesc.setText(itemList.get(position).getTxtDescItem());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView textTitle, textPrice, textDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.leftImage);
            textTitle = itemView.findViewById(R.id.txtTitleItem);
            textPrice = itemView.findViewById(R.id.txtPriceItem);
            textDesc = itemView.findViewById(R.id.txtDescItem);
        }
    }
}
