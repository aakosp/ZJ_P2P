package com.aako.zjp2p.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseActivity;
import com.aako.zjp2p.api.Api;
import com.aako.zjp2p.entity.Message;
import com.aako.zjp2p.entity.User;
import com.aako.zjp2p.util.StringUtils;
import com.aako.zjp2p.util.ToastUtils;
import com.aako.zjp2p.widget.TopBar;
import com.cengalabs.flatui.views.FlatButton;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by aako on 16-1-20.
 */
public class ActivityRegister extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ActivityRegister";

    private TopBar topBar;
    private EditText etPhone, etCode, etPwd, etNick;
    private FlatButton btnReg, getCode;
    private ImageView ivPwdState;

    private String msgId;

    @Override
    protected void initViews() {
        topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setActivity(this);
        etPhone = (EditText) findViewById(R.id.phone);
        etCode = (EditText) findViewById(R.id.code);
        etPwd = (EditText) findViewById(R.id.pwd);
        etNick = (EditText) findViewById(R.id.nick);
        getCode = (FlatButton) findViewById(R.id.getCode);
        ivPwdState = (ImageView) findViewById(R.id.pwd_state);
        btnReg = (FlatButton) findViewById(R.id.btnRegister);
        btnReg.setOnClickListener(this);
        getCode.setOnClickListener(this);
        ivPwdState.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                register();
                break;
            case R.id.getCode:
                getCode();
                break;
            case R.id.pwd_state:

                break;
        }
    }

    public void getCode() {
        String phone = etPhone.getText().toString();
        if (!StringUtils.isPhone(phone)) {
            ToastUtils.show(getResources().getString(R.string.hint_phone_empty));
            return;
        }
        Api.identifyingCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Message>() {
                    @Override
                    public void call(Message msg) {
                        etCode.setText(msg.message_id);
                        msgId = msg.message_id;
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    public void register() {
        String phone = etPhone.getText().toString();
        if (!StringUtils.isPhone(phone)) {
            ToastUtils.show(getResources().getString(R.string.hint_phone_empty));
            return;
        }

        String code = etCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            ToastUtils.show(getResources().getString(R.string.hint_code_empty));
            return;
        }

        String nick = etNick.getText().toString();
        if (TextUtils.isEmpty(nick)) {
            ToastUtils.show(getResources().getString(R.string.hint_nick_empty));
            return;
        }

        String pwd = etPwd.getText().toString();
        String pwd2 = etPwd.getText().toString();
        if (pwd.equals(pwd2)) {
            ToastUtils.show(getResources().getString(R.string.hint_pwd_error));
            return;
        }


        Api.reg(phone, pwd, pwd2, msgId, code, nick)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User u) {
                        ToastUtils.show(getResources().getString(R.string.hint_reg_success));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }

}
