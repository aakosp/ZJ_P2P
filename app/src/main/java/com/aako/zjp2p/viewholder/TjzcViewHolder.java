package com.aako.zjp2p.viewholder;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.entity.Zc;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by aako on 16-1-6.
 */
public class TjzcViewHolder extends RecyclerView.ViewHolder implements Holder<Zc> {

    private SimpleDraweeView img;
    private TextView title, describe, wcd, ycje, syts;

    public TjzcViewHolder(View itemView) {
        super(itemView);
        createView(null);
    }

    @Override
    public View createView(Context context) {
        img = (SimpleDraweeView) this.itemView.findViewById(R.id.img);
        title = (TextView) this.itemView.findViewById(R.id.title);
        describe = (TextView) this.itemView.findViewById(R.id.describe);
        wcd = (TextView) this.itemView.findViewById(R.id.wcd);
        ycje = (TextView) this.itemView.findViewById(R.id.ycje);
        syts = (TextView) this.itemView.findViewById(R.id.syts);
        return this.itemView;
    }

    @Override
    public void UpdateUI(int position, Zc data) {
        img.setImageURI(Uri.parse(data.img));
        title.setText(data.title);
        describe.setText(data.describe);
        wcd.setText(data.wcd+"%");
        ycje.setText(data.sum);
        syts.setText(data.remainingTime);
    }

}
