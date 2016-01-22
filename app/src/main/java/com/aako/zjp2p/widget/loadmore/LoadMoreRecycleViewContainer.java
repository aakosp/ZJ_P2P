package com.aako.zjp2p.widget.loadmore;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.aako.zjp2p.util.LogUtil;

/**
 * Created by aako on 16-1-18.
 */
public class LoadMoreRecycleViewContainer extends LinearLayout implements ILoadMoreContainer {

    private static final String TAG = "LoadMoreRecycleViewContainer";

//    private RecyclerView.OnScrollListener mOnScrollListener;
    private ILoadMoreUIHandler mLoadMoreUIHandler;
    private ILoadMoreHandler mLoadMoreHandler;

    private boolean mIsLoading;
    private boolean mHasMore = false;
    private boolean mAutoLoadMore = true;
    private boolean mLoadError = false;

    private boolean mListEmpty = true;
    private boolean mShowLoadingForFirstPage = false;
    private View mFooterView;
    private View mHeaderView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItem = -1;

    public LoadMoreRecycleViewContainer(Context context) {
        super(context);
    }

    public LoadMoreRecycleViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    public void useDefaultFooter() {
        LoadMoreDefaultFooterView footerView = new LoadMoreDefaultFooterView(getContext());
        footerView.setVisibility(GONE);
        setLoadMoreView(footerView);
        setLoadMoreUIHandler(footerView);
    }

    private void init() {
        mRecyclerView = (RecyclerView) getChildAt(0);
        if (null == mRecyclerView) {
            throw new NullPointerException("RecyclerView is null");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mHeaderView = new View(this.getContext());
        mFooterView = new View(this.getContext());

        this.addView(mHeaderView, 0);
//        this.addView(mRecyclerView);
        this.useDefaultFooter();

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LogUtil.d(TAG, "onScrollStateChanged ==================");
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mRecyclerView.getAdapter().getItemCount()) {

                    LogUtil.d(TAG, "scroll 2 end");
                    onReachBottom();
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
        });

        /*mRecyclerView.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                LogUtil.d(TAG, "onChanged=======");
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                LogUtil.d(TAG, "onItemRangeChanged 2 =======");
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
                LogUtil.d(TAG, "onItemRangeChanged 3 =======");
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                LogUtil.d(TAG, "onItemRangeInserted=======");
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                LogUtil.d(TAG, "onItemRangeRemoved=======");
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                LogUtil.d(TAG, "onItemRangeMoved=======");
            }
        });*/
    }

    private void tryToPerformLoadMore() {
        if (mIsLoading) {
            return;
        }

        // no more content and also not load for first page
        if (!mHasMore && !(mListEmpty && mShowLoadingForFirstPage)) {
            return;
        }

        mIsLoading = true;

        if (mLoadMoreUIHandler != null) {
            mLoadMoreUIHandler.onLoading(this);
        }
        if (null != mLoadMoreHandler) {
            mLoadMoreHandler.onLoadMore(this);
        }
    }

    private void onReachBottom() {
        // if has error, just leave what it should be
        if (mLoadError) {
            return;
        }
        if (mAutoLoadMore) {
            tryToPerformLoadMore();
        } else {
            if (mHasMore) {
                mLoadMoreUIHandler.onWaitToLoadMore(this);
            }
        }
    }

    @Override
    public void setShowLoadingForFirstPage(boolean showLoading) {
        mShowLoadingForFirstPage = showLoading;
    }

    @Override
    public void setAutoLoadMore(boolean autoLoadMore) {
        mAutoLoadMore = autoLoadMore;
    }

    @Deprecated
    @Override
    public void setOnScrollListener(AbsListView.OnScrollListener l) {
    }

    @Override
    public void setLoadMoreView(View view) {
        // has not been initialized
        if (mRecyclerView == null) {
            mFooterView = view;
            return;
        }
        // remove previous
        if (mFooterView != null && mFooterView != view) {
            this.addFooterView(view);
        }

        // add current
//        mFooterView = view;
        mFooterView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tryToPerformLoadMore();
            }
        });

//        addFooterView(view);
    }

    @Override
    public void setLoadMoreUIHandler(ILoadMoreUIHandler handler) {
        mLoadMoreUIHandler = handler;
    }

    @Override
    public void setLoadMoreHandler(ILoadMoreHandler handler) {
        mLoadMoreHandler = handler;
    }

    /**
     * page has loaded
     *
     * @param emptyResult
     * @param hasMore
     */
    @Override
    public void loadMoreFinish(boolean emptyResult, boolean hasMore) {
        mLoadError = false;
        mListEmpty = emptyResult;
        mIsLoading = false;
        mHasMore = hasMore;

        if (mLoadMoreUIHandler != null) {
            mLoadMoreUIHandler.onLoadFinish(this, emptyResult, hasMore);
        }
    }

    @Override
    public void loadMoreError(int errorCode, String errorMessage) {
        mIsLoading = false;
        mLoadError = true;
        if (mLoadMoreUIHandler != null) {
            mLoadMoreUIHandler.onLoadError(this, errorCode, errorMessage);
        }
    }

    protected void addFooterView(View view) {
        this.removeView(mFooterView);
        this.mFooterView = view;
        this.addView(mFooterView, 2);
    }

    protected void removeFooterView() {
        this.removeView(mFooterView);
    }

    public void addHeaderView(View view) {
        this.removeView(mHeaderView);
        this.mHeaderView = view;
        this.addView(mHeaderView, 0);
    }

    protected void removeHeaderView() {
        this.removeView(mHeaderView);
    }

    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
