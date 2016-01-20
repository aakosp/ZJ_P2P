package com.aako.zjp2p.util.net.retrofit;

import com.aako.zjp2p.entity.User;
import com.aako.zjp2p.util.LogUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.io.Reader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final String TAG = "GsonResponseBodyConverter";
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        /*Reader reader = value.charStream();
        try {
            return adapter.fromJson(reader);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }*/
        return adapter.fromJson(value.string());
    }
}
