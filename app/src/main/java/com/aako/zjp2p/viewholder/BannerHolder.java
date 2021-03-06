package com.aako.zjp2p.viewholder;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.aako.zjp2p.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by aako on 2015/12/27.
 */
public class BannerHolder implements Holder<Uri> {
    private SimpleDraweeView simpleDraweeView;

    @Override
    public View createView(Context context) {
        simpleDraweeView = (SimpleDraweeView) View.inflate(context, R.layout.item_banner, null);
        return simpleDraweeView;
    }

    @Override
    public void UpdateUI(int position, Uri data) {
        simpleDraweeView.setImageURI(data);
    }
}
