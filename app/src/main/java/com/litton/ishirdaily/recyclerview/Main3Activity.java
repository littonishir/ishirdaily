package com.litton.ishirdaily.recyclerview;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.litton.ishirdaily.R;
import com.litton.ishirdaily.adapter.NumListAdapter;
import com.litton.ishirdaily.adapter.QuickAdapter;
import com.litton.ishirdaily.bean.Model;
import com.litton.ishirdaily.recyclerview.view.WrapperRecycleAdapter;
import com.litton.ishirdaily.recyclerview.view.WrapperRecyclerView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuickAdapter adapter;
    private SwipeRefreshLayout sr;
    private ArrayList<Model> datas;


    /**
     * 增加头部底部展示
     * 使用装饰设计模式修改后的Adapter
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        sr = findViewById(R.id.sr);
        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Model model;

                for (int i = 15; i < 25; i++) {
                    model = new Model();
                    model.title="我是第" + i + "条标题";
                    model.content="第" + i + "条内容";
                    datas.add(model);
                }
                adapter.notifyDataSetChanged();
                sr.setRefreshing(false);

            }
        });

        //初始化RecyclerView
        recyclerView = findViewById(R.id.rv_normal);

        //模拟的数据（实际开发中一般是从网络获取的）
        datas = new ArrayList<>();
        Model model;
        for (int i = 0; i < 15; i++) {
            model = new Model();
            model.title="我是第" + i + "条标题";
            model.content="第" + i + "条内容";
            datas.add(model);
        }

        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new QuickAdapter(R.layout.item_rv, datas);
        View headerview = LayoutInflater.from(this).inflate(R.layout.rv_heder,recyclerView,false);
        View footview = LayoutInflater.from(this).inflate(R.layout.item_num,recyclerView,false);

        adapter.addHeaderView(headerview);
        adapter.addFooterView(footview);


        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(Main3Activity.this, "点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();

            }
        });

        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(Main3Activity.this, "onItemChildLongClick点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()){
                    case R.id.tv_title:
                        TextView textView = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.center);
                        Toast.makeText(Main3Activity.this, datas.get(position).title, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.tv_content:
                        Toast.makeText(Main3Activity.this, datas.get(position).content, Toast.LENGTH_SHORT).show();


                        break;
                }

            }
        });


    }

}
