package com.example.smart_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CustomerMain extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ArrayList<ItemModel> itemList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

    }

    //Created a list to tryout the recyclervier
    //Latter figure out how to import from database :)
    private List<ItemModel> initData(){
        itemList = new ArrayList<>();
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));
        itemList.add(new ItemModel(R.drawable.ic_launcher_background,"Title Item","$9.99","Description, description bla bla bla"));

        return itemList;
    }
}