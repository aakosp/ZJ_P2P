package com.aako.zjp2p.event;

import android.view.View;

import com.aako.zjp2p.util.rxbus.RxBus;

/**
 * Created by aako on 16-2-1.
 */
public abstract class OnClickListenerImp implements View.OnClickListener{

    public OnClickListenerImp(){
        RxBus.get().register(this);
    }

    @Override
    public void onClick(View v) {

    }
}
