package com.aako.zjp2p.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.ui.base.BaseFragment;
import com.aako.zjp2p.ui.widget.loopviewpager.CircleIndicator;
import com.aako.zjp2p.ui.widget.loopviewpager.LoopViewPager;
import com.aako.zjp2p.ui.widget.loopviewpager.adapter.LoopPagerAdapter;

import java.util.Random;

/**
 * Created by aako on 15-12-15.
 */
public class FragmentHome extends BaseFragment {

    private static final String TAG = " FragmentHome ";
    private LoopViewPager loopViewPager;
//    private ViewPager loopViewPager;
    private CircleIndicator circleIndicator;

    @Override
    protected void initViews(LayoutInflater inflater) {
        curView = inflater.inflate(R.layout.fragment_home, null);
        loopViewPager = (LoopViewPager) findViewById(R.id.lvp);
        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        loopViewPager.setAdapter(new DemoPagerAdapter(), true);
        circleIndicator.setViewPager(loopViewPager);
//        loopViewPager.setCurrentItem(2);
    }


    public class DemoPagerAdapter extends PagerAdapter {

        private final Random random = new Random();
        private final SparseArray<TextView> mHolderArray = new SparseArray<>();
        private int mSize;

        public DemoPagerAdapter() {
            mSize = 5;
        }

        public DemoPagerAdapter(int count) {
            mSize = count;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(mHolderArray.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            TextView textView = new TextView(view.getContext());
            textView.setText(String.valueOf(position + 1));
            textView.setBackgroundColor(0xff000000 | random.nextInt(0x00ffffff));
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(48);
            view.addView(textView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            mHolderArray.put(position, textView);
            return textView;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public void addItem() {
            mSize++;
            notifyDataSetChanged();
        }

        public void removeItem() {
            mSize--;
            mSize = mSize < 0 ? 0 : mSize;

            notifyDataSetChanged();
        }
    }

}
