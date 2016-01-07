package com.aako.zjp2p.activity;

import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.aako.zjp2p.R;
import com.aako.zjp2p.adapter.ProjectTypeAdapter;
import com.aako.zjp2p.activity.base.BaseFragment;
import com.aako.zjp2p.adapter.TjtzAdapter;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.util.UiUtils;
import com.aako.zjp2p.viewholder.BannerHolder;
import com.aako.zjp2p.widget.BannerView;
import com.aako.zjp2p.viewholder.ViewHolderCreator;
import com.aako.zjp2p.widget.FlowLayout;
import com.aako.zjp2p.widget.ImageTextButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by aako on 15-12-15.
 */
public class FragmentHome extends BaseFragment {

    private static final String TAG = " FragmentHome ";

    private int[] imgs = {R.mipmap.home_p2p, R.mipmap.home_zc, R.mipmap.home_ct, R.mipmap.home_jj, R.mipmap.home_gy, R.mipmap.home_mx};
    private String[] strs = {"p2p项目", "众筹", "创投", "基金", "公益", "梦想"};


    private BannerView bannerView;
    //    private GridView gridView;
    private FlowLayout flowLayout;
    private RecyclerView rvTjtz, rvTjzc;

    @Override
    protected void initViews() {
        bannerView = (BannerView) findViewById(R.id.banners);
//        gridView = (GridView) findViewById(R.id.grid);
        flowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        rvTjtz = (RecyclerView) findViewById(R.id.tjtz);
        rvTjzc = (RecyclerView) findViewById(R.id.tjzc);

        List<Uri> bannerUrl = new ArrayList<>();
        bannerUrl.add(Uri.parse("http://b.hiphotos.baidu.com/image/h%3D200/sign=9a3972dc65d9f2d33f1123ef99ed8a53/3b87e950352ac65cf1f52b4efcf2b21193138a1f.jpg"));
        bannerUrl.add(Uri.parse("http://d.hiphotos.baidu.com/image/h%3D200/sign=99e997010e24ab18ff16e63705fbe69a/267f9e2f07082838d4bfae33bf99a9014c08f163.jpg"));
        bannerUrl.add(Uri.parse("http://img04.taobaocdn.com/imgextra/i4/2076373656/TB2PI1LXVXXXXXNXXXXXXXXXXXX_!!2076373656.jpg_.webp"));
        bannerView.setPages(new ViewHolderCreator<BannerHolder>() {

            @Override
            public BannerHolder createHolder() {
                return new BannerHolder();
            }
        }, bannerUrl);
        bannerView.setCanLoop(true);

//        gridView.setAdapter(new ProjectTypeAdapter(this.getContext()));

        int width = (UiUtils.getWidth() - UiUtils.dp2px(getResources(), 1) * 2) / 3;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, UiUtils.dp2px(getResources(), 85));

        flowLayout.setNumColumns(3);
        for (int i = 0; i < 6; i++) {
            ImageTextButton itb = (ImageTextButton) View.inflate(this.getContext(), R.layout.item_program, null);
            itb.setLayoutParams(params);
            itb.setImg(imgs[i]);
            itb.setText(strs[i]);
            flowLayout.addView(itb);
        }


        TjtzAdapter adapter = new TjtzAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvTjtz.setLayoutManager(linearLayoutManager);
        rvTjtz.setAdapter(adapter);
        for (int i = 0; i < 3; i++) {
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
            adapter.addData(tz);
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }
}
