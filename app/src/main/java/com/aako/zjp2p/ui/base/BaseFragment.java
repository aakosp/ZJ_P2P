package com.aako.zjp2p.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aako.zjp2p.R;

/**
 * Created by aako on 15-12-23.
 */
public abstract class BaseFragment extends Fragment {

    public View curView;

    /**
     * 初始化 curView
     */
    protected abstract void initViews(LayoutInflater inflater);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != curView) {
            ((ViewGroup) curView.getParent()).removeView(curView);
            return curView;
        }
        initViews(inflater);
        return curView;
    }

    public View findViewById(int resId){
        return curView.findViewById(resId);
    }
}