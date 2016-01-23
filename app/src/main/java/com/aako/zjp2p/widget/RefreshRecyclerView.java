package com.aako.zjp2p.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.aako.zjp2p.R;

/**
 * Created by aako on 16-1-23.
 */
public class RefreshRecyclerView extends MultiSwipeRefreshLayout {

    private boolean mIsRequestDataRefresh = false;

    public interface IOnRefreshListener {
        void loadMore();

        void refresh();
    }

    private static final String TAG = " RefreshRecyclerView ";

    private RecyclerView mRecyclerView;
    private int lastVisibleItem = -1;
    private LinearLayoutManager linearLayoutManager;
    private boolean mIsFirstTimeTouchBottom = true;
    //    private int mPage = 0;
    private IOnRefreshListener onRefresh;

    public RefreshRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView();
        this.trySetupSwipeRefresh();
    }

    void trySetupSwipeRefresh() {
        this.setColorSchemeResources(R.color.refresh_progress_3,
                R.color.refresh_progress_2, R.color.refresh_progress_1);
        // do not use lambda!!
        this.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        requestDataRefresh();
                    }
                });
    }

    public void initView() {
        mRecyclerView = (RecyclerView) getChildAt(0);
        if (null == mRecyclerView) {
            throw new NullPointerException("RecyclerView is null");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.addOnScrollListener(getOnBottomListener());
    }

    public void setOnRefreshListener(IOnRefreshListener iOnRefreshListener) {
        this.onRefresh = iOnRefreshListener;
    }


    public RecyclerView.OnScrollListener getOnBottomListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!RefreshRecyclerView.this.isRefreshing()) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mRecyclerView.getAdapter().getItemCount()) {
                        onReachBottom();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (null == linearLayoutManager) {
                    linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                }
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        };
    }

    public void onReachBottom() {
        if (!mIsFirstTimeTouchBottom) {
            this.setRefreshing(true);
            if (null != onRefresh) {
                onRefresh.loadMore();
            }
        } else {
            mIsFirstTimeTouchBottom = false;
        }
    }

    public void setRequestDataRefresh(boolean requestDataRefresh) {
        if (!requestDataRefresh) {
            mIsRequestDataRefresh = false;
            // 防止刷新消失太快，让子弹飞一会儿.
            this.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RefreshRecyclerView.this.setRefreshing(false);
                }
            }, 1000);
        } else {
            this.setRefreshing(true);
        }
    }

    public void setProgressViewOffset(boolean scale, int start, int end) {
        this.setProgressViewOffset(scale, start, end);
    }


    public void setSwipeableChildren(MultiSwipeRefreshLayout.CanChildScrollUpCallback canChildScrollUpCallback) {
        this.setCanChildScrollUpCallback(canChildScrollUpCallback);
    }


    public boolean isRequestDataRefresh() {
        return mIsRequestDataRefresh;
    }


    public void requestDataRefresh() {
        mIsRequestDataRefresh = true;
        if (null != onRefresh) {
            onRefresh.refresh();
        }
    }


}
