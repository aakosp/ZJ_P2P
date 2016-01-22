package com.aako.zjp2p.entity;

import java.io.Serializable;

/**
 * Created by aako on 16-1-18.
 */
public class User implements Serializable {

    private int id;
    private String phone;
    private String password;
    private String nick;
    private String reg_time;
    private float money;
    private float income;
    private String id_card_name;
    private String id_card_number;
    private String id_card_pic1;
    private String id_card_pic2;
    private int is_identified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public String getId_card_name() {
        return id_card_name;
    }

    public void setId_card_name(String id_card_name) {
        this.id_card_name = id_card_name;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getId_card_pic1() {
        return id_card_pic1;
    }

    public void setId_card_pic1(String id_card_pic1) {
        this.id_card_pic1 = id_card_pic1;
    }

    public String getId_card_pic2() {
        return id_card_pic2;
    }

    public void setId_card_pic2(String id_card_pic2) {
        this.id_card_pic2 = id_card_pic2;
    }

    public int getIs_identified() {
        return is_identified;
    }

    public void setIs_identified(int is_identified) {
        this.is_identified = is_identified;
    }

    @Override
    public String toString() {
        return "id:" + id + " phone:" + phone + " nick:" + nick + " reg_time:" + reg_time;
    }
}
