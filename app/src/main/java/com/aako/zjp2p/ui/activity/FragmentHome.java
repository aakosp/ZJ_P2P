package com.aako.zjp2p.ui.activity;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.ui.adapter.BannerAdapter;
import com.aako.zjp2p.ui.adapter.ProjectTypeAdapter;
import com.aako.zjp2p.ui.base.BaseFragment;
import com.aako.zjp2p.ui.widget.loopviewpager.CircleIndicator;
import com.aako.zjp2p.ui.widget.loopviewpager.LoopViewPager;
import java.util.Random;

/**
 * Created by aako on 15-12-15.
 */
public class FragmentHome extends BaseFragment {

    private static final String TAG = " FragmentHome ";
    private LoopViewPager loopViewPager;
    private CircleIndicator circleIndicator;
    private GridView gridView;

    @Override
    protected void initViews(LayoutInflater inflater) {
        curView = inflater.inflate(R.layout.fragment_home, null);
        loopViewPager = (LoopViewPager) findViewById(R.id.lvp);
        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        gridView = (GridView) findViewById(R.id.grid);

        loopViewPager.setAdapter(new BannerAdapter(this.getContext()));
        circleIndicator.setViewPager(loopViewPager);

        gridView.setAdapter(new ProjectTypeAdapter(this.getContext()));
    }




}
