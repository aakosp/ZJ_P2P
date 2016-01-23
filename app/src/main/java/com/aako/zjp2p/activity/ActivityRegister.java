package com.aako.zjp2p.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseActivity;
import com.aako.zjp2p.api.ApiFactory;
import com.aako.zjp2p.api.service.IUser;
import com.aako.zjp2p.util.net.retrofit.RetrofitUtils;
import com.aako.zjp2p.widget.TopBar;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.HashMap;

import retrofit2.Call;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by aako on 16-1-20.
 */
public class ActivityRegister extends BaseActivity implements View.OnClickListener {

    private IUser iUser = ApiFactory.getIUserSingleton();
    private TopBar topBar;
    private EditText etPhone, etCode, etPwd, etNick;
    private ButtonRectangle btnReg, getCode;

    @Override
    protected void initViews() {
        topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setActivity(this);
        etPhone = (EditText) findViewById(R.id.phone);
        etCode = (EditText) findViewById(R.id.code);
        etPwd = (EditText) findViewById(R.id.pwd);
        etNick = (EditText) findViewById(R.id.nick);
        getCode = (ButtonRectangle) findViewById(R.id.getCode);
        btnReg = (ButtonRectangle) findViewById(R.id.btnRegister);
        btnReg.setOnClickListener(this);
        getCode.setOnClickListener(this);
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
            case R.id.getCode:
                Subscription s = Observable
                        .doOnNext(iUser.identifyingCode(new HashMap<String, String>()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(meizhis -> {
                            if (clean) mMeizhiList.clear();
                            mMeizhiList.addAll(meizhis);
                            mMeizhiListAdapter.notifyDataSetChanged();
                            setRequestDataRefresh(false);
                        }, throwable -> loadError(throwable));
                // @formatter:on
                addSubscription(s);
                break;
        }
    }

    public void register(){
        String phone = etPhone.getText().toString();
        if(TextUtils.isEmpty(phone)){

        }

        String code = etCode.getText().toString();
        if(TextUtils.isEmpty(code)){

        }
    }
}
