package com.litton.ishirdaily.recyclerview.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by littonishir on 2018/7/20.
 * 装饰设计模式对RecyclerView.Adapter功能扩展
 */
public class WrapperRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final RecyclerView.Adapter mAdapter;
    ArrayList<View> mHeaderViews;
    ArrayList<View> mFooterViews;

    public WrapperRecycleAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {


        // 头部ViewHolder返回
        // Header (negative positions will throw an IndexOutOfBoundsException)
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return creatHeaderViewHolder(mHeaderViews.get(position));
        }

        // 中间ViewHolder返回
        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.onCreateViewHolder(parent, mAdapter.getItemViewType(position));
            }
        }

        // 底部ViewHolder返回
        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return creatFootViewHolder(mFooterViews.get(adjPosition - adapterCount));
    }

    private RecyclerView.ViewHolder creatHeaderViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };
    }

    private RecyclerView.ViewHolder creatFootViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        // Header (negative positions will throw an IndexOutOfBoundsException)
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return;
        }

        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, position - numHeaders);
            }
        }

        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
//        return creatFootViewHolder(mFooterViews.get(adjPosition - adapterCount));

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mHeaderViews.size() + mFooterViews.size() + mAdapter.getItemCount();
    }

    /**
     * 添加头部
     *
     * @param view
     */
    public void addHeaderView(View view) {
        if (!mHeaderViews.contains(view)) {
            mHeaderViews.add(view);
            notifyDataSetChanged();
        }

    }

    /**
     * 移除头部
     *
     * @param view
     */
    public void removeHeaderView(View view) {
        if (mHeaderViews.contains(view)) {
            mHeaderViews.remove(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加底部
     *
     * @param view
     */
    public void addFootView(View view) {
        if (!mFooterViews.contains(view)) {
            mFooterViews.add(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除底部
     *
     * @param view
     */
    public void removeFootView(View view) {
        if (mFooterViews.contains(view)) {
            mFooterViews.remove(view);
            notifyDataSetChanged();
        }
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFooterViews.size();
    }

}
