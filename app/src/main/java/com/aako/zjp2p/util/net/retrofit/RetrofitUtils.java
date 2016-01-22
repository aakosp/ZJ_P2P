package com.aako.zjp2p.util.net.retrofit;

import android.util.Base64;

import com.aako.zjp2p.util.LogUtil;
import com.aako.zjp2p.util.MD5Util;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

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
//                .addCallAdapterFactory(new ErrorHandlingCallAdapter.ErrorHandlingCallAdapterFactory())
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
        return new OkHttpClient.Builder().addInterceptor(new HeaderInterceptor()).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)).build();
    }

    private static class HeaderInterceptor implements Interceptor {

        public static final String key = "7fef6171469e80d32c0559f88b377245";

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            char[] body = bodyToString(request).toCharArray();
            Arrays.sort(body);
            StringBuffer sb = new StringBuffer(key);
            sb.append(body);
            String token = MD5Util.md5(sb.toString());

            Request newRequest = request.newBuilder()
                    .addHeader("Content-Type", "Json")
                    .addHeader("TOKEN", token)
                    .build();

            Response response = chain.proceed(newRequest);

            return response;
        }
    }

    private static String bodyToString(final Request request) {
        Buffer buffer = null;
        try {
            final Request copy = request.newBuilder().build();
            buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        } finally {
            if (null != buffer) {
                buffer.close();
            }
        }
    }
}
