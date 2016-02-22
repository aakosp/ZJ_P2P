package com.aako.zjp2p.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aako.zjp2p.R;
import com.aako.zjp2p.entity.NavMenu;
import com.aako.zjp2p.event.Event;
import com.aako.zjp2p.util.LogUtil;
import com.aako.zjp2p.util.UiUtils;
import com.aako.zjp2p.util.rxbus.RxBus;
import com.aako.zjp2p.viewholder.NavMenuHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aako on 16-2-16.
 */
public class NavMenuAdapter extends RecyclerView.Adapter<NavMenuHolder>{

    private final static String TAG = "NavMenuAdapter";

    private Context context;
    private List<NavMenu> menus = new ArrayList();
    private OnMenuCLick onMenuCLick;

    public NavMenuAdapter(Context context){
        this.context = context;
        menus.add(new NavMenu(R.mipmap.drawer_home, R.mipmap.drawer_home_click, "首页"));
        menus.add(new NavMenu(R.mipmap.drawer_profile, R.mipmap.drawer_profile_click, "我的账户"));
        menus.add(new NavMenu(R.mipmap.drawer_initiate, R.mipmap.drawer_initiate_click, "我发起的"));
        menus.add(new NavMenu(R.mipmap.drawer_spreading, R.mipmap.drawer_spreading_click, "我的推广"));
        menus.add(new NavMenu(R.mipmap.drawer_participated, R.mipmap.drawer_participated_click, "我的参与"));
        menus.add(new NavMenu(R.mipmap.drawer_integral, R.mipmap.drawer_integral_click, "我的积分"));
        onMenuCLick = new OnMenuCLick();
    }

    @Override
    public NavMenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.nav_menu, null);
        if(null != parent){
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(parent.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
        }
        view.setOnClickListener(onMenuCLick);
        NavMenuHolder holder = new NavMenuHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NavMenuHolder holder, int position) {
        holder.UpdateUI(position, menus.get(position));
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    static class OnMenuCLick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag(R.id.item_tag);
            RxBus.get().post(Event.MENU, position);
        }
    }
}
