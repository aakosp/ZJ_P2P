package com.aako.zjp2p.ui.widget.loopviewpager.adapter;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aako on 2015/12/20.
 */
public class LoopPagerAdapter extends PagerAdapter {

    private PagerAdapter mPagerAdapter;
    private SparseArray<ToDestroy> mToDestroy = new SparseArray<>();
    private boolean mBoundaryCaching;
    private boolean canLoop;

    public LoopPagerAdapter(PagerAdapter adapter, boolean canLoop) {
        this.mPagerAdapter = adapter;
        this.canLoop = canLoop;
        adapter.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void notifyDataSetChanged() {
        mToDestroy.clear();
        super.notifyDataSetChanged();
    }

    public int toRealPosition(int position) {
        if (!canLoop) return position;
        int realCount = getRealCount();
        if (realCount == 0)
            return 0;
        int realPosition = (position - 1) % realCount;
        if (realPosition < 0)
            realPosition += realCount;

        return realPosition;
    }

    public int toInnerPosition(int realPosition) {
        if (!canLoop) return realPosition;
        int position = (realPosition + 1);
        return position;
    }

    private int getRealFirstPosition() {
        return canLoop ? 1 : 0;
    }

    private int getRealLastPosition() {
        return canLoop ? getRealFirstPosition() + getRealCount() - 1 : getRealCount() - 1;
    }

    @Override
    public int getCount() {
        return canLoop ? mPagerAdapter.getCount() + 2 : getRealCount();
    }

    public int getRealCount() {
        return mPagerAdapter.getCount();
    }

    public PagerAdapter getRealAdapter() {
        return mPagerAdapter;
    }


    public void setBoundaryCaching(boolean flag) {
        mBoundaryCaching = flag;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = (mPagerAdapter instanceof FragmentPagerAdapter || mPagerAdapter instanceof FragmentStatePagerAdapter)
                ? position
                : toRealPosition(position);

        if (mBoundaryCaching) {
            ToDestroy toDestroy = mToDestroy.get(position);
            if (toDestroy != null) {
                mToDestroy.remove(position);
                return toDestroy.object;
            }
        }
        return mPagerAdapter.instantiateItem(container, realPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int realFirst = getRealFirstPosition();
        int realLast = getRealLastPosition();
        int realPosition = (mPagerAdapter instanceof FragmentPagerAdapter || mPagerAdapter instanceof FragmentStatePagerAdapter)
                ? position
                : toRealPosition(position);

        if (mBoundaryCaching && (position == realFirst || position == realLast)) {
            mToDestroy.put(position, new ToDestroy(object));
        } else {
            mPagerAdapter.destroyItem(container, realPosition, object);
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        mPagerAdapter.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return mPagerAdapter.isViewFromObject(view, object);
    }

    @Override
    public void restoreState(Parcelable bundle, ClassLoader classLoader) {
        mPagerAdapter.restoreState(bundle, classLoader);
    }

    @Override
    public Parcelable saveState() {
        return mPagerAdapter.saveState();
    }

    @Override
    public void startUpdate(ViewGroup container) {
        mPagerAdapter.startUpdate(container);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mPagerAdapter.setPrimaryItem(container, position, object);
    }


    /**
     * Container class for caching the boundary views
     */
    static class ToDestroy {
        Object object;

        public ToDestroy(Object object) {
            this.object = object;
        }
    }
}
