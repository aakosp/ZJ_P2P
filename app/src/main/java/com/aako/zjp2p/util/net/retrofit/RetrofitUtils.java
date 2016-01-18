package com.aako.zjp2p.util.net.retrofit;

import retrofit2.Retrofit;

/**
 * Created by aako on 16-1-18.
 */
public class RetrofitUtils {

    private static Retrofit retrofit;

    private RetrofitUtils(String baseUrl) {

    }

    public static void init(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new ErrorHandlingCallAdapter.ErrorHandlingCallAdapterFactory())
                .build();
    }

    public static Retrofit getInstance(){
        if(null == retrofit){
            throw new NullPointerException("retrofit is null");
        }
        return retrofit;
    }


}
