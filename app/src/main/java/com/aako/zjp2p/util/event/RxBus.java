package com.aako.zjp2p.util.event;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by aako on 2015/11/26.
 */
public class RxBus {
    private final String TAG = "RxBus";

    private static RxBus mInstance;
    private ConcurrentHashMap<Object, List<Subject>> subsercibers;

    private RxBus() {
        subsercibers = new ConcurrentHashMap<>();
    }

    public static RxBus getInstance() {
        if (null == mInstance) {
            synchronized (mInstance) {
                if (null == mInstance) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    public <T> Observable<T> regist(@NonNull Object tag, @NonNull Class<T> tClass) {
        List<Subject> subjectList = subsercibers.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subsercibers.put(tag, subjectList);
        }
        Subject<T, T> subject = PublishSubject.create();
        subjectList.add(subject);
        return subject;
    }

    public void unRegist(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjectList = subsercibers.get(tag);
        if (null != subjectList) {
            subjectList.remove(observable);
            if (subjectList.size() == 0)
                subsercibers.remove(subjectList);
        }
    }

    public void post(@NonNull Object tag, @NonNull Object content){
        List<Subject> subjectList = subsercibers.get(tag);
        if(null != subjectList){
            for(Subject subject : subjectList){
                subject.onNext(content);
            }
        }
    }

}
