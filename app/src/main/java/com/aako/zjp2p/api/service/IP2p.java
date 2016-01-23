package com.aako.zjp2p.api.service;

import com.aako.zjp2p.entity.Id;
import com.aako.zjp2p.entity.Page;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.entity.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by aako on 2016/1/20.
 */
public interface IP2p {

    @POST("p2p/create")
    Call<Id> create(@Body Map<String, String> body);

    @POST("p2p/get_p2p_by_status")
    Call<Page<Tz>> getP2pByStatus(@Body Map<String, String> body);

    @POST("p2p/filter_p2p")
    Call<Page<Tz>> p2pFilter(@Body Map<String, String> body);

    @POST("p2p/get_recommend_p2p")
    Call<Page<Tz>> getRecommendP2p(@Body Map<String, String> body);

    @POST("p2p/get_recommend_p2p")
    Call<Tz> getP2p(@Body Map<String, String> body);
}
