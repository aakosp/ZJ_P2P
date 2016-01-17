package com.aako.zjp2p.util.net;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by aako on 2015/11/25.
 */
public class OkHttpStack extends HurlStack {
    private final OkUrlFactory mOkUrlFactory;

    public OkHttpStack(OkHttpClient okHttpClient) {
        this(new OkUrlFactory(okHttpClient));
    }

    public OkHttpStack(OkUrlFactory okUrlFactory) {
        if (null == okUrlFactory) {
            throw new NullPointerException("Client must not be null.");
        }
        this.mOkUrlFactory = okUrlFactory;
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return mOkUrlFactory.open(url);
    }
}
