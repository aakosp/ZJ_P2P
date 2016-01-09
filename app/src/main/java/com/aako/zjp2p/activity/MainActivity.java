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
import com.aako.zjp2p.widget.ImageTextButton;

import java.util.HashMap;
import java.util.Map;

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
