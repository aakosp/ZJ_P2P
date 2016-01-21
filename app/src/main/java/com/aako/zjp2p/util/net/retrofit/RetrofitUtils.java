package com.aako.zjp2p.util.net.retrofit;

import android.util.Base64;

import com.aako.zjp2p.util.LogUtil;
import com.aako.zjp2p.util.MD5Util;
import com.google.gson.stream.JsonWriter;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.Headers;

/**
 * Created by aako on 16-1-18.
 */
public class RetrofitUtils {

    private static final String TAG = " RetrofitUtils ";

    private static Retrofit retrofit;

    private RetrofitUtils() {

    }

    public static void init(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new ErrorHandlingCallAdapter.ErrorHandlingCallAdapterFactory())
                .client(getOkHttpClient())

                .build();
    }

    public static Retrofit getInstance() {
        if (null == retrofit) {
            throw new NullPointerException("retrofit is null");
        }
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new HeaderInterceptor()).build();
    }

    private static class HeaderInterceptor implements Interceptor {

        public static final String key = "7fef6171469e80d32c0559f88b377245";

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            request.header("Content-Type:Json");
            request.header("TOKEN:10e8ae80b3d6fe0429d2e7df14679e44");

            char[] body = bodyToString(request).toCharArray();
            Arrays.sort(body);
            StringBuffer sb = new StringBuffer(key);
            sb.append(body);
            LogUtil.d(TAG, sb.toString());
            String token = Base64.encodeToString(MD5Util.md5(sb.toString()), Base64.DEFAULT);
            Request newRequest = request.newBuilder()
                    .addHeader("Content-Type", "Json")
                    .addHeader("TOKEN", "10e8ae80b3d6fe0429d2e7df14679e44")
                    .addHeader("AUTH:", token)
                    .build();


            long t1 = System.nanoTime();
            LogUtil.d(TAG, request.body() + "   " + String.format("Sending request %s on %s%n%s",
                    newRequest.url(), chain.connection(), newRequest.headers()));

            Response response = chain.proceed(newRequest);

            long t2 = System.nanoTime();
            LogUtil.d(TAG, String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    private static String bodyToString(final Request request){

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
