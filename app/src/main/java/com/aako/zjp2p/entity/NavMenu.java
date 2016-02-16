package com.aako.zjp2p.entity;

/**
 * Created by aako on 16-2-16.
 */
public class NavMenu {

    public NavMenu(int iconId,
                   int selectedIconId,
                   String menuStr) {
        this.iconId = iconId;
        this.selectedIconId = selectedIconId;
        this.menuStr = menuStr;
    }

    public int iconId;
    public int selectedIconId;
    public String menuStr;
}
