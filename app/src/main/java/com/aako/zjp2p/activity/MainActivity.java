package com.aako.zjp2p.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseAppCompatActivity;
import com.aako.zjp2p.api.service.IUser;
import com.aako.zjp2p.entity.User;
import com.aako.zjp2p.util.LogUtil;
import com.aako.zjp2p.util.net.retrofit.RetrofitUtils;
import com.aako.zjp2p.widget.ImageTextButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = " MainActivity ";

    private TextView tv_mainActivity_title;
    private ImageTextButton home, profile, initiate, spreading, participated, integral;

    private Map<Integer, Fragment> mFragments = new HashMap<>();
    private OnClickLinstenerImp onClickLinstenerImp;

    @Override
    protected void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_mainActivity_title = (TextView) toolbar.findViewById(R.id.title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        home = (ImageTextButton) findViewById(R.id.home);
        profile = (ImageTextButton) findViewById(R.id.profile);

        onClickLinstenerImp = new OnClickLinstenerImp();
        home.setOnClickListener(onClickLinstenerImp);
        profile.setOnClickListener(onClickLinstenerImp);

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        mFragments.put(R.id.home, getSupportFragmentManager().findFragmentById(R.id.fragmentHome));
        mFragments.put(R.id.profile, getSupportFragmentManager().findFragmentById(R.id.fragmentProfile));
        mFragments.put(R.id.initiate, getSupportFragmentManager().findFragmentById(R.id.fragmentInitiate));
        mFragments.put(R.id.spreading, getSupportFragmentManager().findFragmentById(R.id.fragmentSpreading));
        mFragments.put(R.id.participated, getSupportFragmentManager().findFragmentById(R.id.fragmentParticipated));
        mFragments.put(R.id.integral, getSupportFragmentManager().findFragmentById(R.id.fragmentIntegral));

        navigationToFragment(R.id.home);

        IUser iUser = RetrofitUtils.getInstance().create(IUser.class);
        final Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Map<String, String> params = new HashMap<>();

        String tm = "{\"id\":1,\"phone\":\"13838102572\",\"password\":\"62bc1de1ed50474dbdeb7cb18df666b3\",\"nick\":\"\",\"reg_time\":\"0000-00-00 00:00:00\",\"money\":0,\"income\":0,\"id_card_name\":\"\\u674e\\u4e1c\",\"id_card_number\":\"412728198502124919\",\"id_card_pic1\":\"id_card_pic1.jpg\",\"id_card_pic2\":\"id_card_pic3.jpg\",\"is_identified\":1}";
        User u = gson.fromJson(tm, User.class);
        LogUtil.d(TAG, "gson : " + u.getId());

        params.put("user_id", "1");
        LogUtil.d(TAG, "gson : " + gson.toJson(params));
        Call<User> call = iUser.getUser(params);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                LogUtil.d(TAG, "id : " + response.body().getId());
            }

            @Override
            public void onFailure(Throwable t) {
                LogUtil.d(TAG, "onFailure", t);
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        navigationToFragment(item.getItemId());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navigationToFragment(int id) {
        if (!mFragments.containsKey(id)) {
            return;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
        for (int key : mFragments.keySet()) {
            fragmentTransaction = fragmentTransaction.hide(mFragments.get(key));
        }
        fragmentTransaction.show(mFragments.get(id)).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private class OnClickLinstenerImp implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            v.setEnabled(false);
            navigationToFragment(v.getId());
            v.setEnabled(true);
        }
    }

/*    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra(TAG, false);
            if (isExit) {
                this.finish();
            }
        }
    }*/
}
