package com.litton.ishirdaily.recyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by littonishir on 2018/7/23.
 */
public class WrapperRecyclerView extends RecyclerView {
    private WrapperRecycleAdapter mAdapter;

    public WrapperRecyclerView(Context context) {
        super(context);
    }

    public WrapperRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mAdapter = new WrapperRecycleAdapter(adapter);
        super.setAdapter(mAdapter);
    }


    /**
     * 添加头部
     *
     * @param view
     */
    public void addHeaderView(View view) {
        if (mAdapter != null) {
            mAdapter.addHeaderView(view);
        }

    }

    /**
     * 移除头部
     *
     * @param view
     */
    public void removeHeaderView(View view) {
        if (mAdapter != null) {
            mAdapter.removeHeaderView(view);
        }
    }

    /**
     * 添加底部
     *
     * @param view
     */
    public void addFootView(View view) {
        if (mAdapter != null) {
            mAdapter.addFootView(view);
        }
    }

    /**
     * 移除底部
     *
     * @param view
     */
    public void removeFootView(View view) {
        if (mAdapter != null) {
            mAdapter.removeFootView(view);
        }
    }

}
