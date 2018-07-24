package com.litton.ishirdaily.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.litton.ishirdaily.R;
import com.litton.ishirdaily.adapter.NumListAdapter;
import com.litton.ishirdaily.recyclerview.view.WrapperRecycleAdapter;
import com.litton.ishirdaily.recyclerview.view.WrapperRecyclerView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private WrapperRecyclerView recyclerView;
    private ArrayList<String> list = new ArrayList<>();
    private NumListAdapter adapter;


    /**
     * 增加头部底部展示
     * 使用装饰设计模式修改后的Adapter
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.rv_normal);
        initData();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        adapter = new NumListAdapter(this, list);
        recyclerView.setLayoutManager(gridLayoutManager);

//        WrapperRecycleAdapter wrapperRecycleAdapter = new WrapperRecycleAdapter(adapter);
//        View inflate = View.inflate(this, R.layout.item_num, recyclerView);
        View headerview = LayoutInflater.from(this).inflate(R.layout.rv_heder,recyclerView,false);
        View footview = LayoutInflater.from(this).inflate(R.layout.item_num,recyclerView,false);
        recyclerView.setAdapter(adapter);

        recyclerView.addHeaderView(headerview);
        recyclerView.addFootView(footview);

        adapter.notifyDataSetChanged();

    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            list.add("当前位置：" + i);
        }
    }
}
