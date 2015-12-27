package com.aako.zjp2p.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.ui.base.BaseAppCompatActivity;
import com.gc.materialdesign.views.Button;

/**
 * Created by ZL on 2015/11/23.
 */
public class LoginActivity extends BaseAppCompatActivity {
    private static final String TAG = "LoginActivity";

    TextView tv_login_title;
    AppCompatEditText acEditText_login_id, acEditText_login_pwd;
    TextInputLayout til_login_id;
    Button btn_login_login;
    Intent intent_login_toMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intent_login_toMain = new Intent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_login_title = (TextView) toolbar.findViewById(R.id.title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initViews() {
//        til_login_id = (TextInputLayout) findViewById(R.id.tilId);
        acEditText_login_id = (AppCompatEditText) findViewById(R.id.etId);
        acEditText_login_pwd = (AppCompatEditText) findViewById(R.id.etPwd);
        btn_login_login = (Button) findViewById(R.id.btnLogin);
        btn_login_login.setOnClickListener(new OnClickListenerImp());
    }


    class OnClickListenerImp implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnLogin:
                    intent_login_toMain.setClass(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent_login_toMain);
                    break;
            }
        }
    }
}
