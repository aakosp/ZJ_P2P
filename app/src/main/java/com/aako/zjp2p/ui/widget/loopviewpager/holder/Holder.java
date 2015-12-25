package com.aako.zjp2p.ui.widget.loopviewpager.holder;

import android.content.Context;
import android.view.View;

/**
 * Created by aako on 15-12-25.
 */
public interface Holder<T> {
    View createView(Context context);

    void UpdateUI(Context context, int position, T data);
}
