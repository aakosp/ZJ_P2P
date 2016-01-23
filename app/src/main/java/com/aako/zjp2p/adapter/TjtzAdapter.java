package com.aako.zjp2p.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.ActivityTzInfo;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.viewholder.TjtzViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aako on 16-1-6.
 */
public class TjtzAdapter extends RecyclerView.Adapter<TjtzViewHolder> {

    private Context context;
    private List<Tz> list = new ArrayList<>();
    private OnItemClickListenerImp onItemClickListenerImp;
    private Intent intent;

    public TjtzAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Tz> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void addData(List<Tz> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void addData(Tz tz) {
        this.list.add(tz);
        this.notifyDataSetChanged();
    }

    public void remove(int position) {
        this.list.remove(position);
        this.notifyItemRemoved(position);
    }

    @Override
    public TjtzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_loan, null);
        if (null == onItemClickListenerImp) {
            onItemClickListenerImp = new OnItemClickListenerImp();
        }
        view.setOnClickListener(onItemClickListenerImp);
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

    private class OnItemClickListenerImp implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            v.setEnabled(false);
            int positopn = (int) v.getTag(R.id.item_tag);
            if (null == intent) {
                intent = new Intent();
                intent.setClass(context, ActivityTzInfo.class);
            }
            context.startActivity(intent);
            v.setEnabled(true);
        }
    }
}
