package com.aako.zjp2p.ui.base;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/**
 * Created by aako on 2015/11/25.
 */
public class WeakRefereneceHandler<T> extends Handler {
    private static final String TAG = "WeakRefereneceHandler";

    private WeakReference<T> t;

    public WeakRefereneceHandler(T t){
        this.t = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        if(null != t && null != t.get()){
            super.handleMessage(msg);
        }
    }
}
