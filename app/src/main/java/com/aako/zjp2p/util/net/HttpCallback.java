package com.aako.zjp2p.util.net;

/**
 * Created by aako on 2015/11/25.
 */
public interface HttpCallback {
    void onSucess(String result);

    void onError();
}
