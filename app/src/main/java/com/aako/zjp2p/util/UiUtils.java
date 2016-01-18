package com.aako.zjp2p.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by ZL on 2015/11/23.
 */
public class UiUtils {

    private static DisplayMetrics displaysMetrics = null;
    private static float scale = -1.0f;

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
        final float scale = AppUtils.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static int dp2px(float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, AppUtils.getInstance().getResources().getDisplayMetrics());
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
     * @return
     */
    public static int getHeight() {
        if (null == displaysMetrics) {
            getDisplayMetrics();
        }
        return displaysMetrics.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getWidth() {
        if (null == displaysMetrics) {
            getDisplayMetrics();
        }
        return displaysMetrics.widthPixels;
    }
}
