package com.aako.zjp2p.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.aako.zjp2p.R;
import com.aako.zjp2p.activity.base.ActivityZc;
import com.aako.zjp2p.activity.base.BaseFragment;
import com.aako.zjp2p.adapter.TjtzAdapter;
import com.aako.zjp2p.entity.Tz;
import com.aako.zjp2p.entity.Zc;
import com.aako.zjp2p.util.UiUtils;
import com.aako.zjp2p.viewholder.BannerHolder;
import com.aako.zjp2p.viewholder.TjtzViewHolder;
import com.aako.zjp2p.viewholder.TzHolder;
import com.aako.zjp2p.viewholder.ViewHolderCreator;
import com.aako.zjp2p.viewholder.ZcHolder;
import com.aako.zjp2p.widget.BannerView;
import com.aako.zjp2p.widget.FlowLayout;
import com.aako.zjp2p.widget.ImageTextButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aako on 15-12-15.
 */
public class FragmentHome extends BaseFragment {

    private static final String TAG = " FragmentHome ";

    private int[] imgs = {R.mipmap.home_p2p, R.mipmap.home_zc, R.mipmap.home_ct, R.mipmap.home_jj, R.mipmap.home_gy, R.mipmap.home_mx};
    private String[] strs = {"p2p项目", "众筹", "创投", "基金", "公益", "梦想"};


    private BannerView bannerView;
    //    private GridView gridView;
    //    private RecyclerView rvTjtz, rvTjzc;
    private FlowLayout flowLayout;//, flTjtz, flTjtz;
    private LinearLayout flTjtz, flTjzc;
    private View tzMore, zcMore;
    private Intent tzIntent, zcIntent;
    private OnCLickListenerImp onCLickListenerImp;

    @Override
    protected void initViews() {
        bannerView = (BannerView) findViewById(R.id.banners);
        flowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        flTjtz = (LinearLayout) findViewById(R.id.tjtz);
        flTjzc = (LinearLayout) findViewById(R.id.tjzc);
        tzMore = findViewById(R.id.label_tzmore);
        zcMore = findViewById(R.id.label_zcmore);

        onCLickListenerImp = new OnCLickListenerImp();

        tzMore.setOnClickListener(onCLickListenerImp);
        zcMore.setOnClickListener(onCLickListenerImp);

        tzIntent = new Intent();
        tzIntent.setClass(this.getActivity(), ActivityTz.class);
        zcIntent = new Intent();
        zcIntent.setClass(this.getActivity(), ActivityZc.class);


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

        int width = (UiUtils.getWidth() - UiUtils.dp2px(1) * 2) / 3;

        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(width, UiUtils.dp2px(85));

        flowLayout.setNumColumns(3);
        for (int i = 0; i < 6; i++) {
            ImageTextButton itb = (ImageTextButton) View.inflate(this.getActivity(), R.layout.item_program, null);
            itb.setLayoutParams(params);
            itb.setImg(imgs[i]);
            itb.setText(strs[i]);
            flowLayout.addView(itb);
        }

        ViewHolderCreator<TzHolder> tzCreator = new ViewHolderCreator<TzHolder>() {
            @Override
            public TzHolder createHolder() {
                return new TzHolder(FragmentHome.this.getActivity());
            }
        };

        ViewHolderCreator<ZcHolder> zcCreator = new ViewHolderCreator<ZcHolder>() {
            @Override
            public ZcHolder createHolder() {
                return new ZcHolder();
            }
        };

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

            TzHolder tzHolder = tzCreator.createHolder();
            View v = tzHolder.createView(this.getActivity());
            tzHolder.UpdateUI(i, tz);
            flTjtz.addView(v);

            Zc zc = new Zc();
            zc.id = i;
            zc.img = "http://img04.taobaocdn.com/imgextra/i4/2076373656/TB2PI1LXVXXXXXNXXXXXXXXXXXX_!!2076373656.jpg_.webp";
            zc.describe = "describe 详细描述 详细描述 " + i;
            zc.invested = (i + 1) * (i % 3 + 1);
            zc.sum = (i + 1) * (i % 3 + 1) * 1000 + "";
            zc.title = "title 标题 " + i;
            zc.wcd = (i + 1) * 10;
            zc.remainingTime = (i + 1) * 10 + "天";

            ZcHolder zcHolder = zcCreator.createHolder();
            View zcView = zcHolder.createView(this.getActivity());
            zcHolder.UpdateUI(i, zc);
            flTjzc.addView(zcView);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }


    private class OnCLickListenerImp implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            v.setEnabled(false);
            switch (v.getId()) {
                case R.id.label_tzmore:
                    FragmentHome.this.startActivity(tzIntent);
                    break;
                case R.id.label_zcmore:
                    FragmentHome.this.startActivity(zcIntent);
                    break;
            }
            v.setEnabled(true);
        }
    }
}
