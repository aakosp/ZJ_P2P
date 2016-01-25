package com.aako.zjp2p.api;

import com.aako.zjp2p.api.service.IP2p;
import com.aako.zjp2p.api.service.IUser;
import com.aako.zjp2p.util.LogUtil;
import com.aako.zjp2p.util.MD5Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by aako on 16-1-23.
 */
public class RetrofitUtil {
    private static final String TAG = " RetrofitUtils ";

    private static final String baseUrl = "http://zhongjin.w3php.com/api/";

    private final IUser userService;
    private final IP2p p2pService;

    public RetrofitUtil() {
        com.squareup.okhttp.OkHttpClient client = new com.squareup.okhttp.OkHttpClient();
        client.setReadTimeout(12, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(new ErrorHandlingCallAdapter.ErrorHandlingCallAdapterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();

        userService = retrofit.create(IUser.class);
        p2pService = retrofit.create(IP2p.class);
    }

    public IUser getIUserService() {
        return userService;
    }

    public IP2p getIP2pService() {
        return p2pService;
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new HeaderInterceptor()).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)).build();
    }

    private class HeaderInterceptor implements Interceptor {

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

            LogUtil.d(TAG, "token:"+token);

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
