package com.example.smart_meal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Cursor> {
    private int layoutResource;
    private CheckBox check;
    public MyAdapter(Context context, int layoutResource, Cursor items) {
        super(context, 0);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }

        // getting textView and setting text
        TextView textView = view.findViewById(R.id.text_view);
        check = view.findViewById(R.id.checkOrder);
        Cursor cursor = getItem(position);
        if (cursor != null) {
            @SuppressLint("Range") String orderText = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ORDER_ID));
            textView.setText(orderText);
        }
        return view;
    }
}

