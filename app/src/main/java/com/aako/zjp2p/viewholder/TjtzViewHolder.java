package com.aako.zjp2p.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.widget.CircleProgress;

/**
 * Created by aako on 16-1-6.
 */
public class TjtzViewHolder extends RecyclerView.ViewHolder implements Holder<Tz> {

    private TextView title, yqnh, qtje, sum, time, rs;
    private CircleProgress cp;
    private ImageView loanType;

    public TjtzViewHolder(View itemView) {
        super(itemView);
        createView(null);
    }

    @Override
    public View createView(Context context) {
        title = (TextView) this.itemView.findViewById(R.id.title);
        yqnh = (TextView) this.itemView.findViewById(R.id.yqnh);
        qtje = (TextView) this.itemView.findViewById(R.id.qtje);
        sum = (TextView) this.itemView.findViewById(R.id.sum);
        time = (TextView) this.itemView.findViewById(R.id.time);
        rs = (TextView) this.itemView.findViewById(R.id.rs);
        cp = (CircleProgress) this.itemView.findViewById(R.id.cp);
        loanType = (ImageView) this.itemView.findViewById(R.id.loanType);
        return this.itemView;
    }

    @Override
    public void UpdateUI(int position, Tz data) {
        title.setText(data.title);
        yqnh.setText(data.yqnh);
        qtje.setText(data.qtje);
        sum.setText(data.sum + "元");
        time.setText(data.time);
        rs.setText(data.rs);
        cp.setText(data.wcd + "%");
        loanType.setImageResource(data.type == Tz.AY ? R.mipmap.loan_month : R.mipmap.loan_all);
    }

}
