package com.aako.zjp2p.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.BaseActivity;
import com.aako.zjp2p.adapter.ConditionAdapter;
import com.aako.zjp2p.adapter.SortAdapter;
import com.aako.zjp2p.adapter.TjtzAdapter;
import com.aako.zjp2p.animation.ViewAnimator;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.event.EventCenter;
import com.aako.zjp2p.util.LogUtil;
import com.aako.zjp2p.util.UiUtils;
import com.aako.zjp2p.widget.DropDownMenu;
import com.aako.zjp2p.widget.TopBar;
import com.aako.zjp2p.widget.loadmore.ILoadMoreContainer;
import com.aako.zjp2p.widget.loadmore.ILoadMoreHandler;
import com.aako.zjp2p.widget.loadmore.LoadMoreRecycleViewContainer;
import com.aako.zjp2p.widget.loadmore.PtrDefaultHandler;
import com.aako.zjp2p.widget.loadmore.PtrFrameLayout;
import com.aako.zjp2p.widget.loadmore.PtrHandler;
import com.aako.zjp2p.widget.superrecycler.OnMoreListener;
import com.aako.zjp2p.widget.superrecycler.SuperRecyclerView;
import com.aako.zjp2p.widget.superrecycler.swipe.SparseItemRemoveAnimator;
import com.aako.zjp2p.widget.superrecycler.swipe.SwipeDismissRecyclerViewTouchListener;
import com.gc.materialdesign.views.ButtonFlat;

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
    private RecyclerView mRecycler;
    private TopBar topBar;
    private SparseItemRemoveAnimator mSparseAnimator;
    private TjtzAdapter adapter;
    private DropDownMenu dropDownMenu;
    private OnClickListenerImp onClickListenerImp;

    private String headers[] = {"排序", "帅选条件"};
    private List<View> popupViews = new ArrayList<>();


    @Override
    protected void initViews() {
        topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setActivity(this);

        /*mRecycler = new SuperRecyclerView(this);
        mRecycler.setEmptyres(R.layout.emptyview);
        mRecycler.setMoreProgressId(R.layout.layout_more_progress);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setupSwipeToDismiss(this);
        mSparseAnimator = new SparseItemRemoveAnimator();
        mRecycler.getRecyclerView().setItemAnimator(mSparseAnimator);
        mRecycler.setRefreshListener(this);
        mRecycler.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        mRecycler.setupMoreListener(this, 10);*/

//        View container = View.inflate(this, R.layout.layout_refresh_recyclerview, null);
//        mRecycler = (RecyclerView) container.findViewById(R.id.rv);
//        PtrFrameLayout mPtrFrameLayout = (PtrFrameLayout) container.findViewById(R.id.load_more_list_view_ptr_frame);
        PtrFrameLayout mPtrFrameLayout = (PtrFrameLayout) View.inflate(this, R.layout.layout_refresh_recyclerview, null);
        mRecycler = (RecyclerView) mPtrFrameLayout.findViewById(R.id.rv);
        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecycler, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                LogUtil.d(TAG, "onRefreshBegin ======");
            }
        });

        View headerMarginView = new View(this);
        headerMarginView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UiUtils.dp2px(20)));

        // load more container
        final LoadMoreRecycleViewContainer loadMoreListViewContainer = (LoadMoreRecycleViewContainer) mPtrFrameLayout.findViewById(R.id.load_more_list_view_container);
        loadMoreListViewContainer.useDefaultFooter();
        loadMoreListViewContainer.addHeaderView(headerMarginView);

        loadMoreListViewContainer.setLoadMoreHandler(new ILoadMoreHandler() {
            @Override
            public void onLoadMore(ILoadMoreContainer loadMoreContainer) {
                LogUtil.d(TAG, "LoadMoreRecycleViewContainer onLoadMore");
            }
        });


        // auto load data
//        mPtrFrameLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPtrFrameLayout.autoRefresh(false);
//            }
//        }, 150);

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

        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, mPtrFrameLayout);

        initData();

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_tz;
    }

    private void initData() {
        adapter = new TjtzAdapter(this);
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
