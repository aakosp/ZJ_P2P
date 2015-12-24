package com.aako.zjp2p.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aako on 2015/12/23.
 */
public class BannerAdapter extends PagerAdapter {

    private Context context;
    private List<String> bannerUrl = new ArrayList<>();
    private SparseArray<View> banners = new SparseArray<>();

    public BannerAdapter(Context context) {
        this.context = context;
    }

    public void setUrls(@NonNull List<String> bannerUrl) {
        if (bannerUrl.isEmpty()) {
            return;
        }
        this.bannerUrl.clear();
        this.bannerUrl.addAll(bannerUrl);
    }

    @Override
    public int getCount() {
        return bannerUrl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView(banners.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View banner = banners.get(position);
        if (null == banner) {
            banner = View.inflate(context, R.layout.item_banner, view);
        }
        SimpleDraweeView img = (SimpleDraweeView) banner.findViewById(R.id.img);
        img.setImageURI(Uri.parse(bannerUrl.get(position)));
        banners.put(position, banner);
        return banner;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
