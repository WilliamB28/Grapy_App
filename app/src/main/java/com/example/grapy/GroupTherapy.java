package com.example.grapy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class GroupTherapy extends AppCompatActivity {

    ArrayList<GTList> gtLists;
    RecyclerView rv_group;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    void initData(){
        gtLists = new ArrayList<>();
        gtLists.add(new GTList("Group 01", "INTJ", 20));
        gtLists.add(new GTList("Group 02", "ENTP", 20));
        gtLists.add(new GTList("Group 03", "ENFJ", 20));
        gtLists.add(new GTList("Group 04", "ISFP", 20));
        gtLists.add(new GTList("Group 05", "ISTJ", 20));
    }

    void init(){
        rv_group = findViewById(R.id.rv_group);
        mAdapter = new GroupAdapter(gtLists, this);
        layoutManager = new LinearLayoutManager(this);

        rv_group.setLayoutManager(layoutManager);
        rv_group.setAdapter(mAdapter);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_therapy);

        initData();
        init();



    }
}