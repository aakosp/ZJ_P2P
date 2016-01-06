package com.aako.zjp2p.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aako.zjp2p.R;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.viewholder.TjtzViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aako on 16-1-6.
 */
public class TjtzAdapter extends RecyclerView.Adapter<TjtzViewHolder> {

    private List<Tz> list = new ArrayList<>();

    public void setData(List<Tz> list){
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void addData(List<Tz> list){
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void addData(Tz tz){
        this.list.add(tz);
        this.notifyDataSetChanged();
    }

    @Override
    public TjtzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_loan, null);
        TjtzViewHolder tjtzViewHolder = new TjtzViewHolder(view);
        return tjtzViewHolder;
    }

    @Override
    public void onBindViewHolder(TjtzViewHolder holder, int position) {
        holder.UpdateUI(position, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
