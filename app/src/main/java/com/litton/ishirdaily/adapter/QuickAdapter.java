package com.litton.ishirdaily.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.litton.ishirdaily.R;
import com.litton.ishirdaily.bean.Model;

import java.util.List;


public class QuickAdapter extends BaseQuickAdapter<Model, BaseViewHolder> {

    public QuickAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Model item) {
//可链式调用赋值
        helper.setText(R.id.tv_title, item.title).addOnClickListener(R.id.tv_title)
                .setText(R.id.tv_content, item.content).addOnClickListener(R.id.tv_content);
    }
}
