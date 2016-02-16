package com.aako.zjp2p.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.event.Event;
import com.aako.zjp2p.util.LogUtil;
import com.aako.zjp2p.util.rxbus.RxBus;
import com.aako.zjp2p.util.rxbus.annotation.Produce;
import com.aako.zjp2p.util.rxbus.annotation.Tag;
import com.aako.zjp2p.util.rxbus.thread.EventThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aako on 16-1-15.
 */
public class SortAdapter extends RecyclerView.Adapter<SortAdapter.SortViewHolder> implements View.OnClickListener{

    private static final String TAG = " SortAdapter ";

    private Context context;
    private List<String> sortStr = new ArrayList<>();
    private int checkItemPosition = 0;

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

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
        sortViewHolder.itemView.setOnClickListener(this);
        return sortViewHolder;
    }

    @Override
    public void onBindViewHolder(SortViewHolder holder, int position) {
        holder.textView.setText(sortStr.get(position));
        holder.itemView.setTag(R.id.item_tag, position);
        fillValue(position, holder);
    }

    @Override
    public int getItemCount() {
        return sortStr.size();
    }

    public String getSort(int position){
        return sortStr.get(position);
    }

    private void fillValue(int position, SortViewHolder viewHolder) {
        viewHolder.textView.setText(sortStr.get(position));
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.red));
                viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.mipmap.drop_down_checked), null);
            } else {
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.text));
                viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag(R.id.item_tag);
        setCheckItem(position);
        RxBus.get().post(Event.SORT, position);
    }

    public static class SortViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public SortViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
