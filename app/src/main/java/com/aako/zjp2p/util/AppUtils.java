package com.aako.zjp2p.util;

import android.app.Application;
import android.util.Log;

import com.aako.zjp2p.util.net.retrofit.RetrofitUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by aako on 15-12-24.
 */
public class AppUtils extends Application {

    private static final String TAG = " AppUtils ";

    private static AppUtils mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
        mInstance = this;
    }

    private void initApplication() {
        Fresco.initialize(this, FrescoConfig.getImagePipelineConfig(this));
        RetrofitUtils.init("http://zhongjin.w3php.com/api/");
    }

    public static Application getInstance(){
        return mInstance;
    }
}
