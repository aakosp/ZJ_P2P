package com.aako.zjp2p.viewholder;

import android.content.Context;
import android.view.View;

import com.aako.zjp2p.R;

/**
 * Created by aako on 16-1-6.
 */
public class TjtzViewHolder implements Holder {
    @Override
    public View createView(Context context) {
        return View.inflate(context, R.layout.item_loan, null);
    }

    @Override
    public void UpdateUI(Context context, int position, Object data) {

    }
}
