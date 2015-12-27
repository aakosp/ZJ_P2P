package com.aako.zjp2p.ui.activity;

import android.net.Uri;
import android.view.LayoutInflater;
import android.widget.GridView;

import com.aako.zjp2p.R;
import com.aako.zjp2p.ui.adapter.ProjectTypeAdapter;
import com.aako.zjp2p.ui.base.BaseFragment;
import com.aako.zjp2p.ui.viewholder.BannerHolder;
import com.aako.zjp2p.ui.widget.BannerView;
import com.aako.zjp2p.ui.widget.loopviewpager.CircleIndicator;
import com.aako.zjp2p.ui.widget.loopviewpager.LoopViewPager;
import com.aako.zjp2p.ui.widget.loopviewpager.holder.ViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aako on 15-12-15.
 */
public class FragmentHome extends BaseFragment {

    private static final String TAG = " FragmentHome ";
    private BannerView bannerView;
    private GridView gridView;

    @Override
    protected void initViews(LayoutInflater inflater) {
        curView = inflater.inflate(R.layout.fragment_home, null);
        bannerView = (BannerView) findViewById(R.id.banners);
        gridView = (GridView) findViewById(R.id.grid);

        List<Uri> bannerUrl = new ArrayList<>();
        bannerUrl.add(Uri.parse("http://b.hiphotos.baidu.com/image/h%3D200/sign=9a3972dc65d9f2d33f1123ef99ed8a53/3b87e950352ac65cf1f52b4efcf2b21193138a1f.jpg"));
        bannerUrl.add(Uri.parse(""));
        bannerView.setPages(new ViewHolderCreator<BannerHolder>() {

            @Override
            public BannerHolder createHolder() {
                return new BannerHolder();
            }
        }, bannerUrl);
        bannerView.setCanLoop(true);

        gridView.setAdapter(new ProjectTypeAdapter(this.getContext()));
    }




}
