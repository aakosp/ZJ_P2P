package com.aako.zjp2p.widget.loopviewpager.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.aako.zjp2p.R;
import com.aako.zjp2p.widget.loopviewpager.LoopViewPager;
import com.aako.zjp2p.viewholder.Holder;
import com.aako.zjp2p.viewholder.ViewHolderCreator;

import java.util.List;

/**
 * Created by aako on 2015/12/20.
 */
public class LoopPagerAdapter<T> extends PagerAdapter {

    private static final String TAG = " LoopPagerAdapter ";

    protected List<T> mDatas;
    protected ViewHolderCreator holderCreator;
    private View.OnClickListener onItemClickListener;
    private boolean canLoop = true;
    private LoopViewPager viewPager;
    private final int MULTIPLE_COUNT = 300;

    public int toRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount == 0)
            return 0;
        int realPosition = position % realCount;
        return realPosition;
    }

    @Override
    public int getCount() {
        return canLoop ? getRealCount() * MULTIPLE_COUNT : getRealCount();
    }

    public int getRealCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = toRealPosition(position);

        View view = getView(realPosition, null, container);
        if (onItemClickListener != null) view.setOnClickListener(onItemClickListener);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        int position = viewPager.getCurrentItem();
        if (position == 0) {
            position = viewPager.getFristItem();
        } else if (position == getCount() - 1) {
            position = viewPager.getLastItem();
        }
        try {
            viewPager.setCurrentItem(position, false);
        } catch (IllegalStateException e) {
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }

    public void setViewPager(LoopViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public LoopPagerAdapter(ViewHolderCreator holderCreator, List<T> datas) {
        this.holderCreator = holderCreator;
        this.mDatas = datas;
    }

    public View getView(int position, View view, ViewGroup container) {
        Holder holder = null;
        if (view == null) {
            holder = (Holder) holderCreator.createHolder();
            view = holder.createView(container.getContext());
            view.setTag(R.id.item_tag, holder);
        } else {
            holder = (Holder<T>) view.getTag(R.id.item_tag);
        }
        if (mDatas != null && !mDatas.isEmpty())
            holder.UpdateUI(position, mDatas.get(position));
        return view;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
