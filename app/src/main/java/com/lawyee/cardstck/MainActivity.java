package com.lawyee.cardstck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindViewData();
    }

    private void bindViewData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("小米");
        strings.add("小明");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(strings,this);
        mRecyclerview.setAdapter(recyclerViewAdapter);
    }

    private void initView() {
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(linearLayoutManager);
    }
}
