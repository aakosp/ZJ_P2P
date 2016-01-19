package com.aako.zjp2p.api.service;

import com.aako.zjp2p.entity.User;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by aako on 16-1-18.
 */
public interface IUser {

    @POST("user/reg")
    Call<User> reg(@Body Map<String, String> body);

    @Headers({"Content-Type:Json",
            "TOKEN:10e8ae80b3d6fe0429d2e7df14679e44"})
    @POST("user/get_user")
    Call<User> getUser(@Body Map<String, String> body);

    @Headers({"Content-Type:Json",
            "TOKEN:10e8ae80b3d6fe0429d2e7df14679e44"})
    @POST("user/identify")
    Call<User> auth(@Body Map<String, String> body);

    @Headers({"Content-Type:Json",
            "TOKEN:10e8ae80b3d6fe0429d2e7df14679e44"})
    @POST("user/send_reg_message")
    Call<User> identifyingCode(@Body Map<String, String> body);
}
