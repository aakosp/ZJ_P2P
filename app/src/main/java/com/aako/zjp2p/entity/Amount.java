package com.aako.zjp2p.entity;

import java.io.Serializable;

/**
 * Created by aako on 2016/1/20.
 */
public class Amount implements Serializable {
    public int id;
    public String type;
    public float amount;
    public String charge_time;
    public int user_id;
    public String submit_time;
    public int is_verified;
    public String dreawing_time;
}
