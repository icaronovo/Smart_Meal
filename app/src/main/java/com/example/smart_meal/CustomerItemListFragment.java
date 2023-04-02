package com.example.smart_meal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomerItemListFragment extends Fragment {
    private Button btnConfirmOrder;
    private Communicator communicator;
    private TextView quantity;
    private ListView mListView;
    private List<String> mItems;
    private CustomListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_item_list, container, false);
        mListView = view.findViewById(R.id.listView);

        adapter = new CustomListAdapter(getContext(), addData());
        mListView.setAdapter(adapter);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        communicator = (Communicator) getActivity();
        btnConfirmOrder = getActivity().findViewById(R.id.btnFrag1);
        quantity = getActivity().findViewById(R.id.count);
        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.respond(adapter.getItems());
            }
        });
    }

    //Create the list of the products of this restaurant
    private List<String> addData(){
        mItems = new ArrayList<>();
        mItems.add("Item 1 -$1.90");
        mItems.add("Item 2 -$2.90");
        mItems.add("Item 3 -$3.90");
        mItems.add("Item 4 -$4.90");
        mItems.add("Item 5 -$5.90");
        mItems.add("Item 6 -$6.90");
        mItems.add("Item 7 -$7.90");
        mItems.add("Item 8 -$8.90");
        mItems.add("Item 9 -$9.90");
        mItems.add("Item 10 -$10.90");
        mItems.add("Item 11 -$6.90");
        mItems.add("Item 12 -$7.90");
        mItems.add("Item 13 -$8.90");
        mItems.add("Item 14 -$9.90");
        mItems.add("Item 15 -$10.90");

        return mItems;
    }
}