package com.aako.zjp2p.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import com.aako.zjp2p.R;

/**
 * Created by ZL on 2015/11/23.
 */
public class UiUtils {

    private static DisplayMetrics displaysMetrics = null;

    public static DisplayMetrics getDisplayMetrics() {
        if (null == displaysMetrics) {
            displaysMetrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) AppUtils.getInstance()
                    .getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(displaysMetrics);
        }
        return displaysMetrics;
    }

    /**
     * Convert Dp to Pixel
     */
    public static float sp2px(float sp) {
        return sp * getDisplayMetrics().scaledDensity;
    }

    public static int dp2px(float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getDisplayMetrics());
        return (int) px;
    }

    public static int getRelativeTop(View myView) {
        if (myView.getId() == android.R.id.content)
            return myView.getTop();
        else
            return myView.getTop() + getRelativeTop((View) myView.getParent());
    }

    public static int getRelativeLeft(View myView) {
        if (myView.getId() == android.R.id.content)
            return myView.getLeft();
        else
            return myView.getLeft() + getRelativeLeft((View) myView.getParent());
    }

    /**
     * 获取屏幕高度
     *
     * @return int
     */
    public static int getHeight() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @return int
     */
    public static int getWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取色值
     * @param colorRes
     * @return
     */
    public static int getColorFromRes(int colorRes){
        return AppUtils.getInstance().getResources().getColor(colorRes);
    }

    public static int getTextSizeFromRes(int dimenRes){
        return AppUtils.getInstance().getResources().getDimensionPixelSize(dimenRes);
    }
}
