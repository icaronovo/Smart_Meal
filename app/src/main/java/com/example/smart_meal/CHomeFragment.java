package com.example.smart_meal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link CHomeFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class CHomeFragment extends Fragment {

    RecyclerView recyclerView;
    List<ItemModel> itemList;
    
    // DELETE THESE COMMENT LATTER (não sei se ele importa por agora LOL)
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public CHomeFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CHomeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static CHomeFragment newInstance(String param1, String param2) {
//        CHomeFragment fragment = new CHomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c_home, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(initData()));
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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