package com.aako.zjp2p.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aako.zjp2p.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aako on 16-1-15.
 */
public class SortAdapter extends RecyclerView.Adapter<SortAdapter.SortViewHolder> {

    private static final String TAG = " SortAdapter ";

    private Context context;
    private List<String> sortStr = new ArrayList<>();

    public SortAdapter(Context context) {
        this.context = context;
        sortStr.add("sort1");
        sortStr.add("sort2");
        sortStr.add("sort3");
    }

    @Override
    public SortAdapter.SortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View sortItem = View.inflate(context, R.layout.item_sort, null);
        SortViewHolder sortViewHolder = new SortViewHolder(sortItem);
        return sortViewHolder;
    }

    @Override
    public void onBindViewHolder(SortViewHolder holder, int position) {
        holder.textView.setText(sortStr.get(position));
    }


    @Override
    public int getItemCount() {
        return sortStr.size();
    }


    public static class SortViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public SortViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
