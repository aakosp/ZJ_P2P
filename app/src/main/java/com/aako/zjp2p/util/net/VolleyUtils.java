package com.aako.zjp2p.util.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;
import java.util.Map;
import rx.Observable;

/**
 * Created by aako on 2015/11/25.
 */
public class VolleyUtils {

    private static VolleyUtils mVolleyUtils;
    private RequestQueue mVolleyRequestQueue;
    private Observable observable;

    private VolleyUtils() {
    }

    public static VolleyUtils getInstance() {
        if (null == mVolleyUtils) {
            synchronized (mVolleyUtils) {
                if (null == mVolleyUtils) {
                    mVolleyUtils = new VolleyUtils();
                }
            }
        }
        return mVolleyUtils;
    }

    public void init(Context context, OkHttpClient client) {
        mVolleyRequestQueue = Volley.newRequestQueue(context, new OkHttpStack(client));
        mVolleyRequestQueue.start();
    }

    private void doRequest(Request request, Object tag) {
        if (null != tag)
            request.setTag(tag);
        mVolleyRequestQueue.add(request);
    }

    public void removeRequest(Object tag) {
        mVolleyRequestQueue.cancelAll(tag);
    }

    private void doRequest(final int method, final String url, final Map<String, String> params, final HttpCallback callback, Object tag) {
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSucess(response);
                Observable.just(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (null != params) {
                    return params;
                }
                return super.getParams();
            }
        };
        doRequest(request, tag);
    }

    public void doGet(final String url, final HttpCallback callback, final Object tag) {
        doRequest(Request.Method.GET, url, null, callback, tag);
    }

    public void doPost(final String url, final Map<String, String> params, final HttpCallback callback, final Object tag){
        doRequest(Request.Method.POST, url, params, callback, tag);
    }

}
