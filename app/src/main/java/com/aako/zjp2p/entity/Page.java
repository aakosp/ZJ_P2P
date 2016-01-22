package com.aako.zjp2p.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aako on 2016/1/20.
 */
public class Page<T> implements Serializable {
    public int per_page;
    public int current_page;
    public String next_page_url;
    public String prev_page_url;
    public int from;
    public int to;
    public List<T> data;
}
