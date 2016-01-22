package com.aako.zjp2p.entity;

import java.io.Serializable;

/**
 * p2p投资
 * Created by aako on 16-1-6.
 */


public class Tz implements Serializable {

    public static int AY = 1;
    public static int YC = 2;
    //3种方式
    public static final String MONTH = "MONTH";
    public static final String LIMIT = "LIMIT";
    public static final String TIME = "TIME";

    public int id;
    public String name;
    public int page_view;    //浏览次数
    public float amount;       //融资金额
    public float interest_rate;//年华利率
    public int limit_time;   //融资期限
    public int borrower;     //借款人
    public String repayment_method;//还款方式
    public float staring_investment_amount;//起投金额
    public int submit_time;     //项目提交时间
    public String stauts;//状态
    public int is_recommend;//是否推荐

    public int wcd;
    public String title;
    public String describe;
    //预期年化
    public String yqnh;
    //起投金额
    public String qtje;
    //已投金额
    public String sum;
    public String time;
    //人数
    public String rs;
    public int type;

}
