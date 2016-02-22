package com.aako.zjp2p.viewholder;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.entity.Zc;
import com.aako.zjp2p.util.UiUtils;
import com.aako.zjp2p.widget.CustomText;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by aako on 16-1-9.
 */
public class ZcHolder implements Holder<Zc> {

    private SimpleDraweeView img;
    private TextView title, describe;
    private CustomText wcd, ycje, syts;

    @Override
    public View createView(Context context) {
        View view = View.inflate(context, R.layout.item_zc, null);
        img = (SimpleDraweeView) view.findViewById(R.id.img);
        title = (TextView) view.findViewById(R.id.title);
        describe = (TextView) view.findViewById(R.id.describe);
        wcd = (CustomText) view.findViewById(R.id.wcd);
        ycje = (CustomText) view.findViewById(R.id.ycje);
        syts = (CustomText) view.findViewById(R.id.syts);
        return view;
    }

    @Override
    public void UpdateUI(int position, Zc data) {
        img.setImageURI(Uri.parse(data.img));
        title.setText(data.title);
        describe.setText(data.describe);
//        wcd.setText(data.wcd + "%");

        wcd.addPiece(new CustomText.Piece.Builder(data.wcd + "")
                .textColor(UiUtils.getColorFromRes(R.color.loan_yqnh))
                .textSize(UiUtils.getTextSizeFromRes(R.dimen.text_small_size))
                .build());
        wcd.addPiece(new CustomText.Piece.Builder("%")
                .textColor(UiUtils.getColorFromRes(R.color.loan_yqnh))
                .textSizeRelative(0.6f)
                .build());
        wcd.display();

        ycje.addPiece(new CustomText.Piece.Builder("￥")
                .textColor(UiUtils.getColorFromRes(R.color.loan_yqnh))
                .textSizeRelative(0.6f)
                .build());
        ycje.addPiece(new CustomText.Piece.Builder(data.sum+"万")
                .textColor(UiUtils.getColorFromRes(R.color.loan_yqnh))
                .build());
        ycje.addPiece(new CustomText.Piece.Builder("已筹")
                .textColor(UiUtils.getColorFromRes(R.color.loan_text))
                .textSizeRelative(0.8f)
                .build());
        ycje.display();

        syts.addPiece(new CustomText.Piece.Builder("剩")
                .textColor(UiUtils.getColorFromRes(R.color.loan_text))
                .textSizeRelative(0.8f)
                .build());
        syts.addPiece(new CustomText.Piece.Builder(data.remainingTime)
                .textColor(UiUtils.getColorFromRes(R.color.loan_yqnh))
                .build());
        syts.addPiece(new CustomText.Piece.Builder("天")
                .textColor(UiUtils.getColorFromRes(R.color.loan_yqnh))
                .textSizeRelative(0.8f)
                .build());
        syts.display();
    }
}