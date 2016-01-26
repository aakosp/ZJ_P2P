package com.aako.zjp2p.util;

import android.widget.Toast;

/**
 * Created by aako on 16-1-26.
 */
public class ToastUtils {

    private static Toast mToast;

    public static void show(String str) {
        if (null == mToast)
            mToast = Toast.makeText(AppUtils.getInstance(), str, Toast.LENGTH_SHORT);
        else {
            mToast.cancel();
            mToast.setText(str);
        }
        mToast.show();
    }
}
