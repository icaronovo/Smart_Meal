package com.example.smart_meal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflater;
    private final List<String> mItems;
    private HashMap<String, Double[]> itemCountMap = new HashMap<>();

    public CustomListAdapter(Context context, List<String> items) {
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

        String item = mItems.get(position);
        holder.itemName.setText(item);

        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(String.valueOf(holder.quantityItem.getText()));
                number++;
                holder.quantityItem.setText(String.valueOf(number));
                updateItems(holder,number);
            }
        });

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(String.valueOf(holder.quantityItem.getText()));
                if(number > 0){
                    number--;
                }
                holder.quantityItem.setText(String.valueOf(number));
                if (number > 0){
                    updateItems(holder,number);
                }else {
                    String item = String.valueOf(holder.itemName.getText());
                    if(itemCountMap.containsKey(item)){
                        itemCountMap.remove(item);
                    }
                }
            }
        });
        return convertView;
    }

    public HashMap<String, Double[]> getItems(){
        return itemCountMap;
    }

    private static class ViewHolder {
        TextView itemName, quantityItem;
        ImageButton addItem;
        ImageButton removeItem;
    }

    private void updateItems(CustomListAdapter.ViewHolder holder, int number){
        String itemName = String.valueOf(holder.itemName.getText());
        String [] temp = itemName.split("\\$");
        Double[] tempNumber = {Double.parseDouble(temp[1]),Double.valueOf(number)};
        itemCountMap.put(temp[0],tempNumber);
    }
}
