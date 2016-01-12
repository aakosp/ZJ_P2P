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
 * Created by aako on 16-1-9.
 */
public class ZcHolder implements Holder<Zc> {

    private SimpleDraweeView img;
    private TextView title, describe, wcd, ycje, syts;

    @Override
    public View createView(Context context) {
        View view = View.inflate(context, R.layout.item_zc, null);
        img = (SimpleDraweeView) view.findViewById(R.id.img);
        title = (TextView) view.findViewById(R.id.title);
        describe = (TextView) view.findViewById(R.id.describe);
        wcd = (TextView) view.findViewById(R.id.wcd);
        ycje = (TextView) view.findViewById(R.id.ycje);
        syts = (TextView) view.findViewById(R.id.syts);
        return view;
    }

    @Override
    public void UpdateUI(int position, Zc data) {
        img.setImageURI(Uri.parse(data.img));
        title.setText(data.title);
        describe.setText(data.describe);
        wcd.setText(data.wcd + "%");
        ycje.setText(data.sum);
        syts.setText(data.remainingTime);
    }
}