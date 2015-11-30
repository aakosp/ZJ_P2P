package com.aako.zjp2p.api;

import com.aako.zjp2p.util.net.HttpCallback;
import com.aako.zjp2p.util.net.VolleyUtils;

/**
 * Created by aako on 2015/11/25.
 */
public class Api {

    public void login(String id, String pwd){
        VolleyUtils.getInstance().doPost("", null, new HttpCallback() {
            @Override
            public void onSucess(String result) {

            }

            @Override
            public void onError() {

            }
        }, null);

    }
}
