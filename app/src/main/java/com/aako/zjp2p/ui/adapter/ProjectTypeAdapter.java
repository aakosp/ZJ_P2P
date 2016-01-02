package com.aako.zjp2p.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import com.aako.zjp2p.R;
import com.aako.zjp2p.ui.util.UiUtils;
import com.aako.zjp2p.ui.widget.ImageTextButton;
import com.gc.materialdesign.views.ButtonRectangle;

/**
 * Created by aako on 2015/12/23.
 */
public class ProjectTypeAdapter extends BaseAdapter {

    private static final String TAG = " ProjectTypeAdapter ";

    private SparseArray<ImageTextButton> buttons = new SparseArray<>(6);
    private int[] imgs = {R.mipmap.home_p2p, R.mipmap.home_zc, R.mipmap.home_ct, R.mipmap.home_jj, R.mipmap.home_gy, R.mipmap.home_mx};
    private String[] strs = {"p2p", "众筹", "创投", "基金", "公益", "梦想"};

    public ProjectTypeAdapter(Context context) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                UiUtils.dpToPx(context.getResources().getDimension(R.dimen.button_width), context.getResources()),
                UiUtils.dpToPx(context.getResources().getDimension(R.dimen.button_height), context.getResources()));
        for (int i = 0; i < 6; i++) {
            ImageTextButton itb = new ImageTextButton(context);
            itb.setImg(imgs[i]);
            itb.setText(strs[i]);
            buttons.put(i, itb);
        }
    }

    @Override
    public int getCount() {
        return buttons.size();
    }

    @Override
    public Object getItem(int position) {
        return buttons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return buttons.get(position);
    }
}
