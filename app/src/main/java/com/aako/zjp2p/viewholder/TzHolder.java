package com.aako.zjp2p.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.widget.CircleProgress;

/**
 * Created by aako on 2016/1/7.
 */
public class TzHolder implements Holder<Tz> {

    private TextView title, yqnh, qtje, sum, time, rs;
    private CircleProgress cp;
    private ImageView loanType;

    @Override
    public View createView(Context context) {
        View view = View.inflate(context, R.layout.item_loan, null);
        title = (TextView) view.findViewById(R.id.title);
        yqnh = (TextView) view.findViewById(R.id.yqnh);
        qtje = (TextView) view.findViewById(R.id.qtje);
        sum = (TextView) view.findViewById(R.id.sum);
        time = (TextView) view.findViewById(R.id.time);
        rs = (TextView) view.findViewById(R.id.rs);
        cp = (CircleProgress) view.findViewById(R.id.cp);
        loanType = (ImageView) view.findViewById(R.id.loanType);
        return view;
    }

    @Override
    public void UpdateUI(int position, Tz data) {
        title.setText(data.title);
        yqnh.setText(data.yqnh);
        qtje.setText(data.qtje);
        sum.setText(data.sum + "元");
        time.setText(data.time);
        rs.setText(data.rs);
        cp.setProgress(data.wcd);
//        cp.setText(data.wcd + "%");
        loanType.setImageResource(data.type == Tz.AY ? R.mipmap.loan_month : R.mipmap.loan_all);
    }
}
