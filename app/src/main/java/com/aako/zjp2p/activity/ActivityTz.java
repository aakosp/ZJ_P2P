package com.aako.zjp2p.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseActivity;
import com.aako.zjp2p.adapter.TjtzAdapter;
import com.aako.zjp2p.animation.ViewAnimator;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.widget.TopBar;
import com.aako.zjp2p.widget.superrecycler.OnMoreListener;
import com.aako.zjp2p.widget.superrecycler.SuperRecyclerView;
import com.aako.zjp2p.widget.superrecycler.swipe.SparseItemRemoveAnimator;
import com.aako.zjp2p.widget.superrecycler.swipe.SwipeDismissRecyclerViewTouchListener;
import com.gc.materialdesign.views.ButtonFlat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aako on 16-1-11.
 */
public class ActivityTz extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener, SwipeDismissRecyclerViewTouchListener.DismissCallbacks {

    private static final String TAG = " ActivityTz ";

    private RecyclerView.LayoutManager mLayoutManager;
    private SuperRecyclerView mRecycler;
    private TopBar topBar;
    private SparseItemRemoveAnimator mSparseAnimator;
    private TjtzAdapter adapter;
    private View sort;
    private ButtonFlat btnSort, btnCondition;
    private OnClickListenerImp onClickListenerImp;


    @Override
    protected void initViews() {
        topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setActivity(this);
        mRecycler = (SuperRecyclerView) findViewById(R.id.list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setupSwipeToDismiss(this);
        mSparseAnimator = new SparseItemRemoveAnimator();
        mRecycler.getRecyclerView().setItemAnimator(mSparseAnimator);
        mRecycler.setRefreshListener(this);
        mRecycler.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        mRecycler.setupMoreListener(this, 1);

        onClickListenerImp = new OnClickListenerImp();

        btnSort = (ButtonFlat) findViewById(R.id.sort);
        btnCondition = (ButtonFlat) findViewById(R.id.condition);
        btnSort.setOnClickListener(onClickListenerImp);
        btnCondition.setOnClickListener(onClickListenerImp);
        sort = View.inflate(this, R.layout.menu_sort, null);
        Log.d(TAG, "btnSort == null" + (null == btnSort));

//        sort.setOnClickListener(onClickListenerImp);

        initData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_tz;
    }

    private void initData() {
        adapter = new TjtzAdapter();
        List<Tz> tzs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Tz tz = new Tz();
            tz.id = i;
            tz.wcd = (i + 1) * 10;
            tz.title = "title 标题 " + i;
            tz.describe = "describe 详细描述 详细描述 " + i;
            tz.yqnh = (i + 1) * 2 + "";
            tz.qtje = (i + 1) * 1000 + "";
            tz.sum = (i + 1) * 200000 + "";
            tz.time = (i + 1) * 3 + "";
            tz.rs = (i + 1) * 4 + "";
            tz.type = i % 2;
            tzs.add(tz);
        }
        adapter.addData(tzs);
        mRecycler.setAdapter(adapter);
    }

    @Override
    public boolean canDismiss(int position) {
        return true;
    }

    @Override
    public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
        for (int position : reverseSortedPositions) {
            mSparseAnimator.setSkipNext(true);
            adapter.remove(position);
        }
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        Toast.makeText(this, "Refresh", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRefresh() {

    }

    private class OnClickListenerImp implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.sort:
                    Log.d(TAG, "OnClickListenerImp =====");
                    sort.setVisibility(View.VISIBLE);
                    ViewAnimator
                            .animate(sort)
                            .translationY(-1000, 0)
                            .alpha(0, 1)
                            .andAnimate(sort)
                            .dp().translationX(-20, 0)
                            .descelerate()
                            .duration(2000)
                            .thenAnimate(sort)
                            .scale(1f, 0.5f, 1f)
                            .accelerate()
                            .duration(1000)
                            .start();
                    break;
                case R.id.condition:
                    break;
            }
        }
    }


}
