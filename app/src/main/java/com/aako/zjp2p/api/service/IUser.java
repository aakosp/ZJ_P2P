package com.aako.zjp2p.api.service;


import com.aako.zjp2p.entity.Amount;
import com.aako.zjp2p.entity.AuthResult;
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
    Observable<User> reg(@Body Map body);

    @POST("user/get_user")
    Observable<User> getUser(@Body Map body);

    @POST("user/identify")
    Observable<AuthResult> auth(@Body Map body);

    @POST("user/send_reg_message")
    Observable<Message> identifyingCode(@Body Map body);

    @POST("user/chargeLog")
    Observable<Page<Amount>> getChargeLog(@Body Map body);

    @POST("user/drawingLog")
    Observable<Page<Amount>> getDrawingLog(@Body Map body);
}
