package com.aako.zjp2p.widget.loopviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.aako.zjp2p.widget.loopviewpager.adapter.LoopPagerAdapter;

/**
 * Created by aako on 2015/12/20.
 */
public class LoopViewPager extends ViewPager {

    private static final String TAG = " LoopViewPager ";

    OnPageChangeListener mOuterPageChangeListener;
    private LoopPagerAdapter mAdapter;

    private boolean isCanScroll = true;
    private boolean canLoop = true;

    public void setAdapter(PagerAdapter adapter, boolean canLoop) {
        mAdapter = (LoopPagerAdapter) adapter;
        mAdapter.setCanLoop(canLoop);
        mAdapter.setViewPager(this);
        super.setAdapter(mAdapter);

        setCurrentItem(getFristItem(), false);
    }

    public int getFristItem() {
        return canLoop ? mAdapter.getRealCount() : 0;
    }

    public int getLastItem() {
        return mAdapter.getRealCount() - 1;
    }

    public boolean isCanScroll() {
        return isCanScroll;
    }

    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll)
            return super.onTouchEvent(ev);
        else
            return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll)
            return super.onInterceptTouchEvent(ev);
        else
            return false;
    }

    public LoopPagerAdapter getAdapter() {
        return mAdapter;
    }

    public int getRealItem() {
        return mAdapter != null ? mAdapter.toRealPosition(super.getCurrentItem()) : 0;
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOuterPageChangeListener = listener;
    }


    public LoopViewPager(Context context) {
        super(context);
        init();
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        super.setOnPageChangeListener(onPageChangeListener);
    }

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        private float mPreviousPosition = -1;

        @Override
        public void onPageSelected(int position) {
            int realPosition = mAdapter.toRealPosition(position);
            if (mPreviousPosition != realPosition) {
                mPreviousPosition = realPosition;
                if (mOuterPageChangeListener != null) {
                    mOuterPageChangeListener.onPageSelected(realPosition);
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            int realPosition = position;

            if (mOuterPageChangeListener != null) {
                if (realPosition != mAdapter.getRealCount() - 1) {
                    mOuterPageChangeListener.onPageScrolled(realPosition,
                            positionOffset, positionOffsetPixels);
                } else {
                    if (positionOffset > .5) {
                        mOuterPageChangeListener.onPageScrolled(0, 0, 0);
                    } else {
                        mOuterPageChangeListener.onPageScrolled(realPosition,
                                0, 0);
                    }
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (mOuterPageChangeListener != null) {
                mOuterPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    };

    public boolean isCanLoop() {
        return canLoop;
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
        if (canLoop == false) {
            setCurrentItem(getRealItem(), false);
        }
        if (mAdapter == null) return;
        mAdapter.setCanLoop(canLoop);
        mAdapter.notifyDataSetChanged();
    }
}
