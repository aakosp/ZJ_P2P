package com.aako.zjp2p.activity;

import android.view.View;
import android.widget.EditText;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseActivity;
import com.gc.materialdesign.views.ButtonRectangle;

/**
 * Created by aako on 16-1-20.
 */
public class ActivityRegister extends BaseActivity implements View.OnClickListener {

    private EditText etPhone, etCode, etPwd, etNick;
    private ButtonRectangle btnReg;

    @Override
    protected void initViews() {
        etPhone = (EditText) findViewById(R.id.phone);
        etCode = (EditText) findViewById(R.id.code);
        etPwd = (EditText) findViewById(R.id.pwd);
        etNick = (EditText) findViewById(R.id.nick);
        btnReg = (ButtonRectangle) findViewById(R.id.btnRegister);
        btnReg.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                register();
                break;
        }
    }

    public void register(){
        String phone =
    }
}
