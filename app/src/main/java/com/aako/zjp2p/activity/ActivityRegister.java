package com.aako.zjp2p.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseActivity;
import com.aako.zjp2p.api.ApiFactory;
import com.aako.zjp2p.entity.Message;
import com.aako.zjp2p.entity.User;
import com.aako.zjp2p.util.LogUtil;
import com.aako.zjp2p.widget.TopBar;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
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
    private Button btnReg, getCode;
    private ImageView ivPwdState;

    @Override
    protected void initViews() {
        topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setActivity(this);
        etPhone = (EditText) findViewById(R.id.phone);
        etCode = (EditText) findViewById(R.id.code);
        etPwd = (EditText) findViewById(R.id.pwd);
        etNick = (EditText) findViewById(R.id.nick);
        getCode = (Button) findViewById(R.id.getCode);
        ivPwdState = (ImageView) findViewById(R.id.pwd_state);
        btnReg = (Button) findViewById(R.id.btnRegister);
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
                Map map = new HashMap<>();
                map.put("phone", 18503860933L);
                ApiFactory.getIUserSingleton().identifyingCode(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Message>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtil.d(TAG, e.getMessage());
                            }

                            @Override
                            public void onNext(Message msg) {

                            }
                        });

                /*Map<String, String> u = new HashMap<>();
                u.put("user_id", "1");
                ApiFactory.getIUserSingleton().getUser(u)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<User>() {
                            @Override
                            public void call(User user) {
                                Log.d(TAG, "user:" + user);
                                etCode.setText(user.toString());
                            }
                        });*/
                break;
            case R.id.pwd_state:

                break;
        }
    }

    public void register() {
        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {

        }

        String code = etCode.getText().toString();
        if (TextUtils.isEmpty(code)) {

        }
    }
}
