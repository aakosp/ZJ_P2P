package com.aako.zjp2p.entity;

import java.io.Serializable;

/**
 * 众筹
 * Created by aako on 16-1-6.
 */
public class Zc implements Serializable {
    public int id;
    public String img;
    public String title;
    public String describe;
    //已筹金额
    public String sum;
    public String remainingTime;
    public int invested;
    //完成度
    public int wcd;


}
