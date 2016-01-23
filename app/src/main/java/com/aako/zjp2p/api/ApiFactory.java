package com.aako.zjp2p.api;

import com.aako.zjp2p.api.service.IP2p;
import com.aako.zjp2p.api.service.IUser;

/**
 * Created by aako on 2015/11/25.
 */
public class ApiFactory {

    protected static final Object monitor = new Object();

    private static IUser iUser;
    private static IP2p ip2p;

    public static IUser getIUserSingleton() {
        synchronized (monitor) {
            if (null == iUser) {
                iUser = new RetrofitUtil().getIUserService();
            }
        }
        return iUser;
    }

    public static IP2p getIP2pSingleton() {
        synchronized (monitor) {
            if (null == ip2p) {
                ip2p = new RetrofitUtil().getIP2pService();
            }
        }
        return ip2p;
    }
}
