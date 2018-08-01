package com.litton.ishirdaily.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.litton.ishirdaily.R;
import com.litton.ishirdaily.adapter.QuickAdapter;
import com.litton.ishirdaily.bean.Model;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuickAdapter adapter;
    private ArrayList<Model> datas = new ArrayList<>();
    private int mStart = 0;
    private int mEnd = 10;


    /**
     * BaseRecyclerViewAdapterHelper
     * 单条目布局
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        //  初始化RecyclerView
        recyclerView = findViewById(R.id.rv_normal);
        initData(mStart, mEnd);

        //  创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //  创建适配器
        adapter = new QuickAdapter(R.layout.item_rv, datas);
        //  添加HeaderView FooterView
        View headerview = LayoutInflater.from(this).inflate(R.layout.rv_heder, recyclerView, false);
        View footview = LayoutInflater.from(this).inflate(R.layout.item_num, recyclerView, false);
        adapter.addHeaderView(headerview);
        adapter.addFooterView(footview);

        //  设置适配器
        recyclerView.setAdapter(adapter);

        // 设置Item点击事件
        adapter.setOnItemClickListener((adapter, view, position) -> {
            //删除条目
            datas.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(Main3Activity.this, "点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
        });
        // 设置ItemLongClick事件
        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            Toast.makeText(Main3Activity.this, "onItemChildLongClick点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
            //长按事件需要返回true,不然会触发Item点击事件
            return true;
        });
        // 设置Item的子view点击事件,需要在适配器中addOnClickListener(R.id.xx)
        adapter.setOnItemChildClickListener((adapter, view, position) -> {

            switch (view.getId()) {
                case R.id.tv_title:
//                    TextView textView = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.center);
                    Toast.makeText(Main3Activity.this, datas.get(position).title, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_content:
                    Toast.makeText(Main3Activity.this, datas.get(position).content, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
//下拉刷新可用不好用
//        adapter.setUpFetchEnable(true);
//        adapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
//            @Override
//            public void onUpFetch() {
//                Toast.makeText(Main3Activity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
//            }
//        });
        //上拉加载更多
        adapter.setOnLoadMoreListener(() -> new Handler().postDelayed(() -> {
            initData(mStart, mEnd);
            adapter.notifyDataSetChanged();
            adapter.loadMoreComplete();
            if (datas.size() > 500) {
                adapter.loadMoreEnd();
            }
//            empty();
        }, 3000), recyclerView);


    }


    private void initData(int start, int end) {
        //模拟的数据（实际开发中一般是从网络获取的）
        Model model;
        for (int i = start; i < end; i++) {
            model = new Model();
            model.title = "我是第" + i + "条标题";
            model.content = "第" + i + "条内容";
            datas.add(model);
        }
        mStart = end;
        mEnd += end;
    }

    //设置空布局
    private void empty() {
        View inflate = LayoutInflater.from(Main3Activity.this).inflate(R.layout.item_empty, new RelativeLayout(Main3Activity.this));
        datas.clear();
        adapter.loadMoreComplete();
        adapter.setEmptyView(inflate);
        adapter.notifyDataSetChanged();
    }

}
