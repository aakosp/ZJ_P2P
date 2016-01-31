package com.aako.zjp2p.api.service;

import com.aako.zjp2p.entity.Id;
import com.aako.zjp2p.entity.Page;
import com.aako.zjp2p.entity.Tz;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by aako on 2016/1/20.
 */
public interface IP2p {

    @POST("p2p/create")
    Observable<Id> create(@Body Map body);

    @POST("p2p/get_p2p_by_status")
    Observable<Page<Tz>> getP2pByStatus(@Body Map body);

    @POST("p2p/filter_p2p")
    Observable<Page<Tz>> p2pFilter(@Body Map body);

    @POST("p2p/get_recommend_p2p")
    Observable<Page<Tz>> getRecommendP2p(@Body Map body);

    @POST("p2p/get_one")
    Observable<Tz> getP2p(@Body Map body);
}
