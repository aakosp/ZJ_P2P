package com.aako.zjp2p.ui;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;

import com.aako.zjp2p.R;
import com.aako.zjp2p.ui.base.BaseActivity;
import com.gc.materialdesign.views.Button;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by ZL on 2015/11/23.
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";

    AppCompatEditText acEditText_login_id, acEditText_login_pwd;
    Button btn_login_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews() {
        acEditText_login_id = (AppCompatEditText) findViewById(R.id.etId);
        acEditText_login_pwd = (AppCompatEditText) findViewById(R.id.etPwd);
        btn_login_login = (Button) findViewById(R.id.btnLogin);
        btn_login_login.setOnClickListener(view -> Log.d(TAG, "Lambda"));
    }

    public static Observable<String> sampleObservable() {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return null;
            }
        });
    }
}
