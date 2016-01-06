package com.aako.zjp2p.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aako.zjp2p.R;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.entity.Zc;
import com.aako.zjp2p.viewholder.TjzcViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aako on 16-1-6.
 */
public class TjzcAdapter extends RecyclerView.Adapter<TjzcViewHolder> {

    private List<Zc> list = new ArrayList<>();

    public void setData(List<Zc> list){
        this.list.clear();
        this.list.addAll(list);
    }

    public void addData(List<Zc> list){
        this.list.addAll(list);
    }

    @Override
    public TjzcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_zc, null);
        TjzcViewHolder tjzcViewHolder = new TjzcViewHolder(view);
        return tjzcViewHolder;
    }

    @Override
    public void onBindViewHolder(TjzcViewHolder holder, int position) {
        holder.UpdateUI(position, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
