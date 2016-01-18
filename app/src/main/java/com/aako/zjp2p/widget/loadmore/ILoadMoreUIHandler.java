package com.aako.zjp2p.widget.loadmore;

/**
 * Created by aako on 16-1-18.
 */
public interface ILoadMoreUIHandler {
    public void onLoading(ILoadMoreContainer container);

    public void onLoadFinish(ILoadMoreContainer container, boolean empty, boolean hasMore);

    public void onWaitToLoadMore(ILoadMoreContainer container);

    public void onLoadError(ILoadMoreContainer container, int errorCode, String errorMessage);
}
