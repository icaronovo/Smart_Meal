package com.example.smart_meal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BusinessAdapter extends ArrayAdapter<OrderModel> {

    TextView orderTxtView;

    public BusinessAdapter(Context context, ArrayList<OrderModel> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        OrderModel item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_checkbox, parent, false);
        }

        // Lookup view for data population
        orderTxtView = convertView.findViewById(R.id.textView);

        // Populate the data into the template view using the data object
        orderTxtView.setText(item.toString());

        // Return the completed view to render on screen
        return convertView;
    }
}