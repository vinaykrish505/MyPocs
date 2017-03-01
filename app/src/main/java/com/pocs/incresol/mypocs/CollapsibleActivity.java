package com.pocs.incresol.mypocs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.CollapsingToolbarLayout;

import java.util.ArrayList;

public class CollapsibleActivity extends AppCompatActivity {
    private ArrayList<String> stringArrayList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsible);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setTitle(getString(R.string.expand));

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setData(); //adding data to array list
        adapter = new RecyclerAdapter(this, stringArrayList);
        recyclerView.setAdapter(adapter);

    }

    private void setData() {
        stringArrayList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            stringArrayList.add("Item " + (i + 1));
        }
    }
}
