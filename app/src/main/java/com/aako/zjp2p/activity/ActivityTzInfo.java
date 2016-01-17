package com.aako.zjp2p.activity;

import android.view.View;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseActivity;
import com.aako.zjp2p.widget.CircleProgress;
import com.aako.zjp2p.widget.ImageTextButton;
import com.aako.zjp2p.widget.TopBar;

/**
 * Created by aako on 2016/1/17.
 */
public class ActivityTzInfo extends BaseActivity {
    private TopBar topBar;
    private CircleProgress cp;
    private TextView syje, ywc, yrz, zje, strLeftTime, strRzqx, strHkfs, strQtje;
    private View vLeftTime, rzqx, hkfs, qtje, tzjl, jkrxx;
    private ImageTextButton itbTzjl, itbJkrxx;

    @Override
    protected void initViews() {
        topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setActivity(this);

        cp = (CircleProgress) findViewById(R.id.cp);
        vLeftTime = findViewById(R.id.left_time);
        rzqx = findViewById(R.id.rzqx);
        hkfs = findViewById(R.id.hkfs);
        qtje = findViewById(R.id.qtje);
        qtje = findViewById(R.id.qtje);

        syje = (TextView) findViewById(R.id.syje);
        ywc = (TextView) findViewById(R.id.ywc);
        yrz = (TextView) findViewById(R.id.yrz);
        zje = (TextView) findViewById(R.id.zje);
        strLeftTime = (TextView) findViewById(R.id.str_lefttime);
        strRzqx = (TextView) findViewById(R.id.str_rzqx);
        strHkfs = (TextView) findViewById(R.id.str_hkfs);
        strQtje = (TextView) findViewById(R.id.str_qtje);
        tzjl = findViewById(R.id.tzjl);
        jkrxx = findViewById(R.id.jkxx);
        itbTzjl = (ImageTextButton) findViewById(R.id.str_tzjl);
        itbJkrxx = (ImageTextButton) findViewById(R.id.str_jkrxx);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_tz_info;
    }
}
