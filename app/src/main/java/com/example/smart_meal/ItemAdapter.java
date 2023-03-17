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

public class ItemAdapter extends RecyclerView.Adapter{
    List<ItemModel> itemList;
    LayoutInflater mInflater;
    ItemClickListener itemClickListener;

    public ItemAdapter(Context context, List<ItemModel> itemList){
        this.itemList = itemList;
        mInflater = LayoutInflater.from(context);
    }

    Integer getItem(int id){
        return itemList.get(id).getImageItem();
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).itemImage.setImageResource(itemList.get(position).getImageItem());
        ((ViewHolder)holder).textTitle.setText(itemList.get(position).getTitleTxt());
        ((ViewHolder)holder).textPrice.setText(itemList.get(position).getTxtPrice());
        ((ViewHolder)holder).textDesc.setText(itemList.get(position).getTxtDescItem());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    void setClickListener(ItemClickListener mItemClickListener){
        itemClickListener = mItemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView textTitle, textPrice, textDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.leftImage);
            textTitle = itemView.findViewById(R.id.txtTitleItem);
            textPrice = itemView.findViewById(R.id.txtPriceItem);
            textDesc = itemView.findViewById(R.id.txtDescItem);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener != null){
                itemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }
}
