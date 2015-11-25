package com.aako.zjp2p.util.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by aako on 2015/11/25.
 */
public class RxRequestAdapter<T> implements Response.Listener<T>, Response.ErrorListener {

    private final Observable<T> mObservable;
    private T mResponse;
    private VolleyError mVolleyError;

    public RxRequestAdapter(){
        mObservable = Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

            }
        });
    }

    @Override
    public void onResponse(T response) {
        mResponse = response;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        mVolleyError = error;
    }
}
