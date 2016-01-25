package com.aako.zjp2p.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aako on 15-12-23.
 */
public abstract class BaseFragment extends Fragment {

    private boolean isCreated = false;
    private boolean isInitView = false;

    public View curView;

    /**
     * 初始化窗体内Views
     */
    protected abstract void initViews();

    /**
     * 获取布局 layoutResID
     *
     * @return id
     */
    protected abstract int getContentViewId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != curView) {
            ((ViewGroup) curView.getParent()).removeView(curView);
            return curView;
        }

        curView = inflater.inflate(getContentViewId(), null);
        isCreated = true;
        onVisible();
        return curView;
    }

    public View findViewById(int resId) {
        return curView.findViewById(resId);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint())
            onVisible();
        else
            onInvisible();
    }

    protected void onVisible() {
        if (isInitView || (!isCreated)) {
            return;
        }
        initViews();
        isInitView = true;
    }

    protected void onInvisible() {
    }
}
