package com.aako.zjp2p.api;

import com.aako.zjp2p.entity.Amount;
import com.aako.zjp2p.entity.AuthResult;
import com.aako.zjp2p.entity.Id;
import com.aako.zjp2p.entity.Message;
import com.aako.zjp2p.entity.Page;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.entity.User;
import com.aako.zjp2p.util.AppUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by aako on 16-1-26.
 */
public class Api {

    /*****************************************
     * user                                  *
     ****************************************/
    public static Observable<User> reg(String phone, String pwd, String pwd2, String msgId, String code, String nick) {
        Map body = new HashMap<>();
        body.put("phone", phone);
        body.put("password", pwd);
        body.put("password_", pwd2);
        body.put("message_id", msgId);
        body.put("code", code);
        body.put("nick", nick);
        return ApiFactory.getIUserSingleton().reg(body);
    }

    public static Observable<User> get(String id) {
        Map body = new HashMap();
        body.put("user_id", id);
        return ApiFactory.getIUserSingleton().getUser(body);
    }

    public static Observable<Message> identifyingCode(String phone){
        Map body = new HashMap<>();
        body.put("phone", phone);
        return ApiFactory.getIUserSingleton().identifyingCode(body);
    }

    public static Observable<AuthResult> auth(String id, String cardId, String name, String pic1, String pic2){
        Map body = new HashMap<>();
        body.put("user_id", id);
        body.put("id_card_number", cardId);
        body.put("id_card_name", name);
        body.put("id_card_pic1", pic1);
        body.put("id_card_pic2", pic2);
        return ApiFactory.getIUserSingleton().auth(body);
    }

    public static Observable<Page<Amount>> getChargeLog(int page){
        Map body = new HashMap<>();
        body.put("user_id", AppUtils.getInstance().getId());
        body.put("page", page);
        return ApiFactory.getIUserSingleton().getChargeLog(body);
    }

    public static Observable<Page<Amount>> getDrawingLog(int page){
        Map body = new HashMap<>();
        body.put("user_id", AppUtils.getInstance().getId());
        body.put("page", page);
        return ApiFactory.getIUserSingleton().getDrawingLog(body);
    }

    /*****************************************
     * p2p                                   *
     ****************************************/
    public static Observable<Page<Tz>> getRecommendP2p(int number) {
        Map body = new HashMap();
        body.put("number", number);
        return ApiFactory.getIP2pSingleton().getRecommendP2p(body);
    }

    public static Observable<Id> create(int borrower, String name, float amount, int time, float rate, String type, float staringAmount){
        Map body = new HashMap();
        body.put("borrower", borrower);
        body.put("name", name);
        body.put("amount", amount);
        body.put("limit_time", time);
        body.put("interest_rate", rate);
        body.put("repayment_method", type);
        body.put("staring_investment_amount", staringAmount);
        return ApiFactory.getIP2pSingleton().create(body);
    }

    public static Observable<Page<Tz>> getP2pByStatus(int page, int pageSize, String type){
        Map body = new HashMap();
        body.put("page", page);
        body.put("page_size", pageSize);
        body.put("status", type);
        return ApiFactory.getIP2pSingleton().getP2pByStatus(body);
    }

    public static Observable<Page<Tz>> p2pFilter(int page, int pageSize, String type){
        Map body = new HashMap();
        body.put("page", page);
        body.put("page_size", pageSize);
        body.put("status", type);
        return ApiFactory.getIP2pSingleton().getP2pByStatus(body);
    }

    public static Observable<Tz> getP2p(int id){
        Map body = new HashMap();
        body.put("id", id);
        return ApiFactory.getIP2pSingleton().getP2p(body);
    }



}
