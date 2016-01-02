package com.aako.zjp2p.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aako.zjp2p.R;


/**
 * Created by yajuanyin on 16/1/2.
 */
public class ImageTextButton extends LinearLayout {
    private TextView mText;
    private ImageView mImg;
    private int resId_img;
    private int resId_txt;
    private int imgWidth;
    private int imgHeight;
    private int textSize;
    private int textColor;
    private String strText;
    private int space;

    public ImageTextButton(Context context) {
        this(context, null);
    }

    public ImageTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.imagetextbutton);
            if (typedArray != null) {
                resId_img = typedArray.getResourceId(
                        R.styleable.imagetextbutton_img_src, 0);
                imgWidth = typedArray.getDimensionPixelOffset(
                        R.styleable.imagetextbutton_img_width, 20);
                imgHeight = typedArray.getDimensionPixelOffset(
                        R.styleable.imagetextbutton_img_height, 20);
                textSize = typedArray.getDimensionPixelSize(
                        R.styleable.imagetextbutton_text_size, 12);
                textColor = typedArray.getResourceId(
                        R.styleable.imagetextbutton_text_color, Color.BLACK);
                resId_txt = typedArray.getResourceId(
                        R.styleable.imagetextbutton_text_src, 0);
                space = typedArray.getResourceId(
                        R.styleable.imagetextbutton_space, 2);
            }
            typedArray.recycle();
        }

        mImg = new ImageView(context);
        LayoutParams imgParams = new LayoutParams(imgWidth, imgHeight);
        mImg.setLayoutParams(imgParams);
        mImg.setScaleType(ImageView.ScaleType.CENTER_CROP);

        mText = new TextView(context);
        LayoutParams txtParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (LinearLayout.HORIZONTAL == this.getOrientation()) {
            txtParams.leftMargin = space;
        } else {
            txtParams.topMargin = space;
        }
        mText.setLayoutParams(txtParams);
        mText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        //mText.setTextColor(this.getResources().getColor(textColor));
        this.addView(mImg);
        this.addView(mText);

        if (resId_img != 0) {
            mImg.setImageResource(resId_img);
        }

        if (resId_txt != 0) {
            mText.setText(resId_txt);
        }
    }

    public TextView getTextView() {
        return mText;
    }

    public void setTextView(TextView mText) {
        this.mText = mText;
    }

    public ImageView getmImg() {
        return mImg;
    }

    public void setImg(ImageView mImg) {
        this.mImg = mImg;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getText() {
        return strText;
    }

    public void setText(String text) {
        this.strText = text;
        this.mText.setText(text);
    }

    public void setText(String text, String defaultVal) {
        if (TextUtils.isEmpty(text)) {
            this.setText(defaultVal);
        } else {
            this.setText(text);
        }
    }

    public void setImg(int resId) {
        if (resId_img != resId && null != mImg) {
            mImg.setImageResource(resId);
            this.resId_img = resId;
        }
    }
}
