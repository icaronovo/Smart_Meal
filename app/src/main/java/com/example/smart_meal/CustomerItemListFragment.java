package com.example.smart_meal;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CustomerItemListFragment extends Fragment {
    private Button btnConfirmOrder;
    private Communicator communicator;
    private TextView quantity;
    private ListView mListView;
    private CustomListAdapter adapter;
    private DBHelper DB;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_item_list, container, false);
        mListView = view.findViewById(R.id.listView);

        //Start the db
        DB = new DBHelper(getActivity());

        //Get the restaurant ID on the Customer Restaurant activity
        CustomerRestaurant activity = (CustomerRestaurant) getActivity();
        int selectedRestaurant = activity.restaurantId;

        //Get the items of this restaurant
        Cursor c = DB.itemsDisplay(String.valueOf(selectedRestaurant));
        Boolean hasNoItems = getItems(c);
        if(hasNoItems == true){
            Toast.makeText(getActivity(),"No items available",Toast.LENGTH_LONG).show();
        }
        c.close();
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        //Get the business ID
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

    //Get the data from DB
    //If the customer doesn't has any data
    //It will show that he has no data
    public boolean getItems(Cursor c) {
        List<String> list = new ArrayList<>();
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                list.add(c.getString(0)); //ItemID
                list.add(c.getString(1)); //ItemValue
                list.add(c.getString(2)); //ItemQuantity
                list.add(c.getString(3)); //ItemName
                list.add(c.getString(4)); //ItemDescription
                list.add(c.getString(5)); //BusinessID
            }
        } else {
            return true;
        }
        addData(list);
        return false;
    }

    //In case the customer has orders
    //Makes a ItemModel object for display
    public void addData(List<String> list) {
        Stack<ItemModel> stackItems = new Stack<>();
        //Make the data being add into the list
        int index = 0;
        while (index < list.size()) {
            ItemModel item = new ItemModel(
                    Integer.parseInt(list.get(index)), //itemID -  ARRAY 0
                    list.get(index + 3), //itemName ARRAY 3
                    list.get(index + 4), //itemDescription
                    Double.parseDouble(list.get(index + 1)),//itemPrice itemPrice 5
                    Integer.parseInt(list.get(index + 2)), //itemQuantity
                    Integer.parseInt(list.get(index + 5)) //businessID
            );
            stackItems.push(item);
            index += 6;
        }
        ArrayList<ItemModel> firstInLastOut = new ArrayList<>();
        while (!stackItems.isEmpty()) {
            firstInLastOut.add(stackItems.pop());
        }
        createListView(firstInLastOut);
    }

    //Create the view on Listview
    public void createListView(ArrayList<ItemModel> listItems) {
        adapter = new CustomListAdapter(getContext(), listItems);
        mListView.setAdapter(adapter);
    }
}