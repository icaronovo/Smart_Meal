package com.example.smart_meal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<ItemModel> {
    private final LayoutInflater mInflater;
    private final ArrayList<ItemModel> mItems;
    private HashMap<Integer,Integer> itemCountMap = new HashMap<>();

    public CustomListAdapter(Context context, ArrayList<ItemModel> items) {
        super(context, R.layout.list_item_layout, items);
        mInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_layout, parent, false);
            holder = new ViewHolder();
            holder.itemName = convertView.findViewById(R.id.list_item_text);
            holder.quantityItem = convertView.findViewById(R.id.count);
            holder.addItem = convertView.findViewById(R.id.buttonAdd);
            holder.removeItem = convertView.findViewById(R.id.buttonRemove);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String textDisplay = mItems.get(position).getItemName() + " - $" +  mItems.get(position).getItemPrice();
        holder.itemName.setText(textDisplay);

        //Add quantity of item on the card
        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(String.valueOf(holder.quantityItem.getText()));
                number++;
                holder.quantityItem.setText(String.valueOf(number));
                updateItems(mItems.get(position).getItemID(),number);
            }
        });

        //Retrive quantity of items on the card
        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(String.valueOf(holder.quantityItem.getText()));
                if(number > 0){
                    number--;
                }
                holder.quantityItem.setText(String.valueOf(number));
                if (number > 0){
                    updateItems(mItems.get(position).getItemID(),number);
                }else {
                    String item = String.valueOf(mItems.get(position).getItemID());
                    updateItems(mItems.get(position).getItemID(),number);
                    if(itemCountMap.containsKey(item)){
                        itemCountMap.remove(item);
                    }
                }
            }
        });
        return convertView;
    }

    public HashMap<Integer,Integer> getItems(){
        return itemCountMap;
    }

    private static class ViewHolder {
        TextView itemName, quantityItem;
        ImageButton addItem;
        ImageButton removeItem;
    }

    private void updateItems(int itemID, int number){
        itemCountMap.put(itemID,number);
    }
}
