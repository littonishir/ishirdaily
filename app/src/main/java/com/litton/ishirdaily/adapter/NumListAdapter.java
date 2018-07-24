package com.litton.ishirdaily.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.litton.ishirdaily.R;

import java.util.List;

/**
 * Created by littonishir on 2018/7/23.
 */
public class NumListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final List<String> mList;

    public NumListAdapter(Context context, List<String> list){
        mContext = context;
        mList = list;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_num,parent,false);
        return new NumViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NumViewHolder){
            ((NumViewHolder) holder).textView.setText(mList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class NumViewHolder extends RecyclerView.ViewHolder{

    TextView textView;

    public NumViewHolder(View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.tv_num);
    }
}