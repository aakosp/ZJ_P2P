package com.aako.zjp2p.activity;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseActivity;
import com.aako.zjp2p.adapter.ConditionAdapter;
import com.aako.zjp2p.adapter.SortAdapter;
import com.aako.zjp2p.adapter.TjtzAdapter;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.widget.DropDownMenu;
import com.aako.zjp2p.widget.MultiSwipeRefreshLayout;
import com.aako.zjp2p.widget.TopBar;
import com.aako.zjp2p.widget.pull2refresh.PtrClassicFrameLayout;
import com.aako.zjp2p.widget.pull2refresh.PtrDefaultHandler;
import com.aako.zjp2p.widget.pull2refresh.PtrFrameLayout;
import com.aako.zjp2p.widget.pull2refresh.loadmore.OnLoadMoreListener;
import com.aako.zjp2p.widget.pull2refresh.recyclerview.RecyclerAdapterWithHF;
import com.aako.zjp2p.widget.superrecycler.swipe.SparseItemRemoveAnimator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aako on 16-1-11.
 */
public class ActivityTz extends BaseActivity {//implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener, SwipeDismissRecyclerViewTouchListener.DismissCallbacks {

    private static final String TAG = " ActivityTz ";

    private RecyclerView.LayoutManager mLayoutManager;
    //    private SuperRecyclerView mRecycler;

    private MultiSwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsRequestDataRefresh = false;

    private TopBar topBar;
    private SparseItemRemoveAnimator mSparseAnimator;
    private OnClickListenerImp onClickListenerImp;

    private DropDownMenu dropDownMenu;
    private String headers[] = {"排序", "帅选条件"};
    private List<View> popupViews = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private TjtzAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    Handler handler = new Handler();

    int page = 0;


    @Override
    protected void initViews() {
        topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setActivity(this);

        View container = View.inflate(this, R.layout.layout_refresh_recyclerview, null);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) container.findViewById(R.id.test_recycler_view_frame);
        mRecyclerView = (RecyclerView) container.findViewById(R.id.test_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        dropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);

        final RecyclerView sortView = new RecyclerView(this);
        sortView.setLayoutManager(new LinearLayoutManager(this));
        SortAdapter sortAdapter = new SortAdapter(this);
        sortView.setAdapter(sortAdapter);

        final RecyclerView conditionView = new RecyclerView(this);
        conditionView.setLayoutManager(new LinearLayoutManager(this));
        ConditionAdapter conditionAdapter = new ConditionAdapter();
        conditionView.setAdapter(conditionAdapter);

        popupViews.add(sortView);
        popupViews.add(conditionView);

        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, container);

        initData(true);
        init();
    }

    private void init() {
        mAdapter = new RecyclerAdapterWithHF(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        ptrClassicFrameLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                ptrClassicFrameLayout.autoRefresh(true);
            }
        }, 150);

        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 0;
                        initData(true);
                        mAdapter.notifyDataSetChanged();
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLoadMoreEnable(true);
                    }
                }, 2000);
            }
        });

        ptrClassicFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void loadMore() {
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        initData(false);
                        mAdapter.notifyDataSetChanged();
                        ptrClassicFrameLayout.loadMoreComplete(true);
                        page++;
                        Toast.makeText(ActivityTz.this, "load more complete", Toast.LENGTH_SHORT)
                                .show();
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_tz;
    }

    private void initData(boolean clear) {
        if (null == adapter) {
            adapter = new TjtzAdapter(this);
        }
        List<Tz> tzs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Tz tz = new Tz();
            tz.id = i;
            tz.wcd = (i + 1) * 10;
            tz.title = "title 标题 " + i + (clear?"头部":"尾部");
            tz.describe = "describe 详细描述 详细描述 " + i;
            tz.yqnh = (i + 1) * 2 + "";
            tz.qtje = (i + 1) * 1000 + "";
            tz.sum = (i + 1) * 200000 + "";
            tz.time = (i + 1) * 3 + "";
            tz.rs = (i + 1) * 4 + "";
            tz.type = i % 2;
            tzs.add(tz);
        }
        if (clear)
            adapter.setData(tzs);
        else
            adapter.addData(tzs);
    }

    /*@Override
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
        Toast.makeText(this, "onMoreAsked", Toast.LENGTH_LONG).show();
        dropDownMenu.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }, 200);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "onRefresh", Toast.LENGTH_LONG).show();
        dropDownMenu.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }, 200);
    }*/

    private class OnClickListenerImp implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

            }
        }
    }


}
