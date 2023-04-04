package com.example.smart_meal;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusinessAdapter extends ArrayAdapter<OrderModel> {

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
        TextView textView = convertView.findViewById(R.id.textView);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);

        // Populate the data into the template view using the data object
        textView.setText(item.toString());
        checkBox.setChecked(false);

        // Attach an OnClickListener to the checkbox to update the item's boolean property
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(true);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    public interface setOnClickListener {
    }
}