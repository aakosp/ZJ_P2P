package com.aako.zjp2p.api.service;


import com.aako.zjp2p.entity.Amount;
import com.aako.zjp2p.entity.Message;
import com.aako.zjp2p.entity.Page;
import com.aako.zjp2p.entity.User;

import java.util.Map;

import rx.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by aako on 16-1-18.
 */
public interface IUser {
    @POST("user/reg")
    Call<User> reg(@Body Map<String, String> body);

    @POST("user/get_user")
    Observable<User> getUser(@Body Map<String, String> body);

    @POST("user/identify")
    Call<User> auth(@Body Map<String, String> body);

    @POST("user/send_reg_message")
    Observable<Message> identifyingCode(@Body Map<String, String> body);

    @POST("user/chargeLog")
    Call<Page<Amount>> getAmountLog(@Body Map<String, String> body);

    @POST("user/drawingLog")
    Call<Page<Amount>> getDrawingLog(@Body Map<String, String> body);
}
