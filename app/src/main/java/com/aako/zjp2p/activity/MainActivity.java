package com.aako.zjp2p.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseAppCompatActivity;
import com.aako.zjp2p.adapter.NavMenuAdapter;
import com.aako.zjp2p.event.Event;
import com.aako.zjp2p.util.LogUtil;
import com.aako.zjp2p.util.rxbus.annotation.Subscribe;
import com.aako.zjp2p.util.rxbus.annotation.Tag;
import com.aako.zjp2p.widget.ImageTextButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = " MainActivity ";

    private TextView tv_mainActivity_title;
    private RecyclerView menuList;

    private List<Fragment> mFragments = new ArrayList<>();
    private NavMenuAdapter adapter;
    public static int menuSelectPosition = 0;

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

        menuList = (RecyclerView) findViewById(R.id.menus);
        menuList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NavMenuAdapter(this);
        menuList.setAdapter(adapter);

        mFragments.add(getSupportFragmentManager().findFragmentById(R.id.fragmentHome));
        mFragments.add(getSupportFragmentManager().findFragmentById(R.id.fragmentProfile));
        mFragments.add(getSupportFragmentManager().findFragmentById(R.id.fragmentInitiate));
        mFragments.add(getSupportFragmentManager().findFragmentById(R.id.fragmentSpreading));
        mFragments.add(getSupportFragmentManager().findFragmentById(R.id.fragmentParticipated));
        mFragments.add(getSupportFragmentManager().findFragmentById(R.id.fragmentIntegral));

        navigationToFragment(0);
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

    private void navigationToFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
        for (Fragment fragment : mFragments) {
            fragmentTransaction = fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.show(mFragments.get(position)).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Subscribe(tags={@Tag(Event.MENU)})
    public void onItemClick(int position) {
        LogUtil.d(TAG, "onItemClick:" + position);
        adapter.notifyItemChanged(menuSelectPosition);
        menuSelectPosition = position;
        adapter.notifyItemChanged(position);
        navigationToFragment(position);
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
