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
import com.gc.materialdesign.views.ButtonRectangle;

/**
 * Created by aako on 2015/12/23.
 */
public class ProjectTypeAdapter extends BaseAdapter {

    private static final String TAG = " ProjectTypeAdapter ";

    private SparseArray<ButtonRectangle> buttons = new SparseArray<>(6);
    private String[] strs = {"p2p", "众筹", "创投", "基金", "公益", "梦想"};

    public ProjectTypeAdapter(Context context) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                UiUtils.dpToPx(context.getResources().getDimension(R.dimen.button_width), context.getResources()),
                UiUtils.dpToPx(context.getResources().getDimension(R.dimen.button_height), context.getResources()));
        for (int i = 0; i < 6; i++) {
            ButtonRectangle button = (ButtonRectangle) View.inflate(context, R.layout.item_project_type, null);
//            ButtonRectangle button = (ButtonRectangle) view.findViewById(R.id.btn);
            Log.d(TAG, "button==null : "+(button==null));
            button.setText(strs[i]);
            buttons.put(i, button);
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
