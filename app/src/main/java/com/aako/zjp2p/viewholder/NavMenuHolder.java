package com.aako.zjp2p.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.MainActivity;
import com.aako.zjp2p.entity.NavMenu;
import com.aako.zjp2p.util.LogUtil;

/**
 * Created by aako on 16-2-16.
 */
public class NavMenuHolder extends RecyclerView.ViewHolder implements Holder<NavMenu> {

    private ImageView img;
    private TextView tv;

    public NavMenuHolder(View itemView) {
        super(itemView);
        createView(null);
    }

    @Override
    public View createView(Context context) {
        img = (ImageView) itemView.findViewById(R.id.img);
        tv = (TextView) itemView.findViewById(R.id.menuStr);
        return itemView;
    }

    @Override
    public void UpdateUI(int position, NavMenu data) {
        LogUtil.d("item", "item width:"+itemView.getWidth());
        if (position == MainActivity.menuSelectPosition)
            img.setImageResource(data.selectedIconId);
        else
            img.setImageResource(data.iconId);
        tv.setText(data.menuStr);
        itemView.setSelected(position == MainActivity.menuSelectPosition);
        itemView.setTag(R.id.item_tag, position);
    }


}
