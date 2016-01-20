package com.aako.zjp2p.activity;

import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseAppCompatActivity;
import com.aako.zjp2p.widget.TopBar;
import com.gc.materialdesign.views.Button;

/**
 * Created by ZL on 2015/11/23.
 */
public class LoginActivity extends BaseAppCompatActivity implements OnClickListener {
    private static final String TAG = "LoginActivity";

    EditText acEditText_login_id, acEditText_login_pwd;
    Button btn_login_login;
    Intent intent_login_toMain, intent_login_toRegister;

    @Override
    protected void initViews() {
//        til_login_id = (TextInputLayout) findViewById(R.id.tilId);
        intent_login_toMain = new Intent();
        intent_login_toMain.setClass(LoginActivity.this, MainActivity.class);
        intent_login_toRegister = new Intent();
        intent_login_toRegister.setClass(LoginActivity.this, ActivityRegister.class);

        TopBar topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setActivity(this);
        topBar.getRightButton().setOnClickListener(this);
        acEditText_login_id = (EditText) findViewById(R.id.etId);
        acEditText_login_pwd = (EditText) findViewById(R.id.etPwd);
        btn_login_login = (Button) findViewById(R.id.btnLogin);
        btn_login_login.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                LoginActivity.this.startActivity(intent_login_toMain);
                LoginActivity.this.finish();
                break;
            case R.id.btnTopBarRight:
                LoginActivity.this.startActivity(intent_login_toRegister);
                LoginActivity.this.finish();
                break;
        }
    }
}
