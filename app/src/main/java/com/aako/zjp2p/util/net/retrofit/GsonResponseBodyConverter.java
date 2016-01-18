package com.aako.zjp2p.util.net.retrofit;

import com.aako.zjp2p.util.LogUtil;
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
        LogUtil.d(TAG, value.string());
        Reader reader = value.charStream();
        try {
            return adapter.fromJson(reader);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
