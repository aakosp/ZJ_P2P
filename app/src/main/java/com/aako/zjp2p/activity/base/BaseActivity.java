package com.aako.zjp2p.activity.base;

import android.app.Activity;
import android.os.Bundle;

import com.aako.zjp2p.debug.ViewServer;
import com.aako.zjp2p.util.rxbus.RxBus;

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

    /**
     * 获取布局 layoutResID
     * @return id
     */
    protected abstract int getContentViewId();

    void setHandler(WeakRefereneceHandler handler){
        mHandler = handler;
    }

    public WeakRefereneceHandler getHandler(){
        if(null == mHandler){
            mHandler = new WeakRefereneceHandler<>(this);
        }
        return mHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
//        ViewServer.get(this).addWindow(this);
        setContentView(getContentViewId());
        initViews();
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        if(null != mCompositeSubscription){
            mCompositeSubscription.unsubscribe();
        }
        super.onDestroy();
//        ViewServer.get(this).removeWindow(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        ViewServer.get(this).setFocusedWindow(this);
    }
}
