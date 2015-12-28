package com.aako.zjp2p.ui.activity;

import android.view.LayoutInflater;
import android.widget.GridView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.ui.adapter.ProjectTypeAdapter;
import com.aako.zjp2p.ui.base.BaseFragment;
import com.aako.zjp2p.ui.widget.BannerView;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by aako on 15-12-15.
 */
public class FragmentHome extends BaseFragment {

    private static final String TAG = " FragmentHome ";
    private BannerView<SimpleDraweeView> bannerView;
    private GridView gridView;

    @Override
    protected void initViews(LayoutInflater inflater) {
        curView = inflater.inflate(R.layout.fragment_home, null);
        bannerView = (BannerView) findViewById(R.id.banners);
        gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(new ProjectTypeAdapter(this.getContext()));
    }


}
