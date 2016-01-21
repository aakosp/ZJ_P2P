package com.aako.zjp2p.util.net;


import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;

/**
 * Created by aako on 15-12-24.
 */
public class OkHttpConfig {
    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getOkHttpClient(){
        if (null == mOkHttpClient){
            synchronized (OkHttpConfig.class){
                if(null == mOkHttpClient){
                    mOkHttpClient = new OkHttpClient();
                    //增加Cookie支持
//                    mOkHttpClient.setCookieHandler(new CookieManager(new MyCookieStore(), CookiePolicy.ACCEPT_ALL));
                }
            }
        }
        return mOkHttpClient;
    }

    static class MyCookieStore implements CookieStore {
        private Map<URI, List<HttpCookie>> map = new HashMap<>();

        public void add(URI uri, HttpCookie cookie) {
            List<HttpCookie> cookies = map.get(uri);
            if (cookies == null) {
                cookies = new ArrayList<>();
                map.put(uri, cookies);
            }
            cookies.add(cookie);
        }

        public List<HttpCookie> get(URI uri) {
            List<HttpCookie> cookies = map.get(uri);
            if (cookies == null) {
                cookies = new ArrayList<>();
                map.put(uri, cookies);
            }
            return cookies;
        }

        public List<HttpCookie> getCookies() {
            Collection<List<HttpCookie>> values = map.values();
            List<HttpCookie> result = new ArrayList<>();
            for (List<HttpCookie> value : values) {
                result.addAll(value);
            }
            return result;
        }

        public List<URI> getURIs() {
            Set<URI> keys = map.keySet();
            return new ArrayList<>(keys);

        }

        public boolean remove(URI uri, HttpCookie cookie) {
            List<HttpCookie> cookies = map.get(uri);
            if (cookies == null) {
                return false;
            }
            return cookies.remove(cookie);
        }

        public boolean removeAll() {
            map.clear();
            return true;
        }
    }
}
