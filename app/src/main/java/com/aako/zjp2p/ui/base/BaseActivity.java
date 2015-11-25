package com.aako.zjp2p.ui.base;

import android.app.Activity;
import android.os.Bundle;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by aako on 2015/11/25.
 */
public abstract class BaseActivity extends Activity {

    private WeakRefereneceHandler<BaseActivity> mHandler;
    private CompositeSubscription mCompositeSubscription;

    /**
     * 初始化窗体内Views
     */
    protected abstract void initViews();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initViews();
    }

    void setHandler(WeakRefereneceHandler handler){
        mHandler = handler;
    }

    public WeakRefereneceHandler getHandler(){
        if(null == mHandler){
            mHandler = new WeakRefereneceHandler<>(this);
        }
        return mHandler;
    }

    void setCompositeSubscription(CompositeSubscription compositeSubscription){
        mCompositeSubscription = compositeSubscription;
    }

    public CompositeSubscription getCompositeSubscription(){
        if(null == mCompositeSubscription){
            mCompositeSubscription = new CompositeSubscription();
        }
        return mCompositeSubscription;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if(null != mCompositeSubscription){
            mCompositeSubscription.unsubscribe();
        }
        super.onDestroy();
    }




}
